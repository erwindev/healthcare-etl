package com.erwin.health.etl;

import com.erwin.health.util.ApplicationSettings;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static java.lang.System.exit;

public class ParquetFileReader  {

    public static void main(String args[]) {
        ParquetFileReader pfr = new ParquetFileReader();
        pfr.run();
    }

    public void run() {

        String fileLocation = ApplicationSettings.getInstance().getProperty("app.parquet_file_location");

        // Set Spark Configuration
        SparkConf conf = new
                SparkConf().setMaster("local[2]").setAppName("DataIngestProcess");

        // Create Spark Session
        SparkSession session = SparkSession.builder().config(conf).getOrCreate();

        session.sparkContext().hadoopConfiguration().set("dfs.client.use.datanode.hostname", "true");

        Dataset<Row> aggregatedLoanDataParquet =
                session.read().parquet(fileLocation);
        aggregatedLoanDataParquet.show();

        exit(0);
    }
}
