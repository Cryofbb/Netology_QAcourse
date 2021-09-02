package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.Connection;

public class Database {
    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static void cleanBD() {
        var connection = Database.getConnection();
//        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        connection.createStatement().executeUpdate("TRUNCATE credit_request_entity;");
        connection.createStatement().executeUpdate("TRUNCATE payment_entity;");
        connection.createStatement().executeUpdate("TRUNCATE order_entity;");
//        connection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
    }

    @SneakyThrows
    private static String getData(String query) {
        val runner = new QueryRunner();
        String data = "";
        try (val conn = Database.getConnection()) {
            data = runner.query(conn, query, new ScalarHandler<>());
        }
        return data;
    }

    @SneakyThrows
    public static String paymentStatus() {
        return getData("SELECT status FROM payment_entity;");
    }

    @SneakyThrows
    public static String creditStatus() {
        return getData("SELECT status FROM credit_request_entity;");
    }

    @SneakyThrows
    public static String countRecords(){
        val runner = new QueryRunner();
        Long count;

        try (val conn = Database.getConnection()) {
            count = runner.query(conn, "SELECT COUNT(*) FROM order_entity;", new ScalarHandler<>());
        }
        return Long.toString(count);
    }
}
