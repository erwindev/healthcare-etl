package com.erwin.health.etl;

import com.erwin.health.util.ApplicationSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PrestoDBReader {

    public static void main(String args[]){
        String jdbcUrl = ApplicationSettings.getInstance().getProperty("app.presto.jdbc.url");
        String jdbcPort = ApplicationSettings.getInstance().getProperty("app.presto.jdbc.port");
        String db = ApplicationSettings.getInstance().getProperty("app.presto.jdbc.db");
        String dbUser = ApplicationSettings.getInstance().getProperty("app.presto.jdbc.user");;
        String dbPassword = ApplicationSettings.getInstance().getProperty("app.presto.jdbc.password");;
        try {
            Connection conn = DriverManager.getConnection(jdbcUrl + ":" + jdbcPort + "/" + db, dbUser, dbPassword);
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
