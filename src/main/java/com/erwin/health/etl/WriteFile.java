package com.erwin.health.etl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

public class WriteFile {
    public static void main(String[] args) throws Exception {

        //Source file in the local file system
        String localSrc = "/Users/erwinalberto/config.xml";
        //Destination file in HDFS
        String dst = "hdfs://test.erwin.com:9000/user/erwinalberto/test";

        //Input stream for the file in local file system to be written to HDFS
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        //Get configuration of Hadoop system
        Configuration conf = new Configuration();
        conf.set ("dfs.client.use.datanode.hostname","true");
        System.out.println("Connecting to -- "+conf.get("fs.defaultFS"));

        //Destination file in HDFS
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        OutputStream out = fs.create(new Path(dst));

        //Copy file from local to HDFS
        IOUtils.copyBytes(in, out, 4096, true);

        System.out.println(dst + " copied to HDFS");

    }
}