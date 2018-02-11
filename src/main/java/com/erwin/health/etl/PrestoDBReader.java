package com.erwin.health.etl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PrestoDBReader {

    public static void main(String args[]){
        try {
            Connection conn = DriverManager.getConnection("jdbc:presto://presto.erwin.com:8081/hive/default", "presto", "");
            Statement stmt = conn.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("SELECT payerid, " +
                        "payername, payeramount, providerid, providername " +
                        "from open_payment_record limit 50");
                while(rs.next()) {
                    String payerId = rs.getString(1);
                    String payerName = rs.getString(2);
                    String payerAmount = rs.getString(3);
                    String providerId = rs.getString(4);
                    String providerName = rs.getString(5);
                    System.out.println(String.format("payerId=%s, " +
                            "payerName=%s, payerAmount=%s, " +
                            "providerId=%s, providerName=%s ",
                            payerId, payerName, payerAmount, providerId, providerName));
                }
            }
            finally {
                stmt.close();
                conn.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
