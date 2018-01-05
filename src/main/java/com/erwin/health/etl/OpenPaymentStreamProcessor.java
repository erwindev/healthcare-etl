package com.erwin.health.etl;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class OpenPaymentStreamProcessor {
    private static final Pattern SPACE = Pattern.compile(" ");
    private static String dataIngestTopic = null;

    public static void main(String[] args) throws Exception {
        dataIngestTopic = "openpayments";
        SparkConf conf = new
                SparkConf().setMaster("local[2]").setAppName("DataIngestProcess");
        OpenPaymentStreamProcessor dp = new OpenPaymentStreamProcessor();
        dp.process(conf);
    }

    public void process(SparkConf conf) {
        //Read messages in batch of 5 seconds
        JavaStreamingContext jssc = new JavaStreamingContext(conf,
                Durations.seconds(5));

        // Start reading messages from Kafka and get DStream
        final JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(jssc,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(Arrays.asList(dataIngestTopic),
                                getKafkaParam()));

        stream.foreachRDD((JavaRDD<ConsumerRecord<String, String>>
                                   opePaymentConsumerRecordRDD) -> {
            JavaRDD<OpenPaymentDataRecord> openPaymentDataRecordRDD =
                    opePaymentConsumerRecordRDD.map((openPaymentConsumerRecord) -> {
                        OpenPaymentDataRecord openPaymenDatatRecord = new OpenPaymentDataRecord();
                        String openPaymentRecord = openPaymentConsumerRecord.value();
                        if (!openPaymentRecord.isEmpty()) {
                            String data[] = openPaymentRecord.split("\\s*,\\s*");
                            openPaymenDatatRecord.setProviderId(data[0]);
                            openPaymenDatatRecord.setProviderName(data[1]);
                            openPaymenDatatRecord.setPayerId(data[2]);
                            openPaymenDatatRecord.setPayerName(data[3]);
                            openPaymenDatatRecord.setPayerAmount(data[4]);
                        } else {
                            System.out.println("Invalid record");
                        }
                        return openPaymenDatatRecord;
                    }).filter(record -> record.getPayerAmount() != null);
            SparkSession session = SparkSession.builder().config(conf).getOrCreate();
            Dataset<Row> openPaymentDataset =
                    session.createDataFrame(openPaymentDataRecordRDD, OpenPaymentDataRecord.class);
            openPaymentDataset.show();
            if (!openPaymentDataset.rdd().isEmpty()) {
                openPaymentDataset
                        .write()
                        .mode(SaveMode.Append)
                        .parquet("file:///Users/ealberto/temp/spark");
            }
        });
        try {
            jssc.start();
            jssc.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> getKafkaParam() {
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaParams.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        kafkaParams.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        kafkaParams.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        kafkaParams.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return kafkaParams;
    }
}
