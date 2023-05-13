package ru.netology.helpers;


import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();
    public static final String mySQLAddress = "jdbc:mysql://localhost:3306/app";
    public static final String mySqlDBName = "app";
    public static final String mySQLPassword = "pass";
    public static final String postgreAddress = "jdbc:postgresql://localhost:5432/app";
    public static final String postgreDBname = "app";
    public static final String postgrePassword = "pass";


    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection getMySQLConn() {
        return DriverManager.getConnection(mySQLAddress, mySqlDBName, mySQLPassword);
    }

    @SneakyThrows
    private static Connection getPostgreConn() {
        return DriverManager.getConnection(postgreAddress, postgreDBname, postgrePassword);
    }

    @SneakyThrows
    public static String returnStatusOfTransactionMysql() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getMySQLConn();
        var status = runner.query(conn, codeSQL, new ScalarHandler<>());
        return String.valueOf(status);
            }

    @SneakyThrows
    public static String returnStatusOfTransactionPostgre() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getPostgreConn();
        var status = runner.query(conn, codeSQL, new ScalarHandler<>());
        return String.valueOf(status);
    }

    @BeforeAll
    @SneakyThrows
    public static void cleanMySQLDataBase() {
        var connection = getMySQLConn();
        runner.update(connection, "DELETE FROM order_entity");
        runner.update(connection, "DELETE FROM payment_entity");
        runner.update(connection, "DELETE FROM credit_request_entity");
    }

    @BeforeAll
    @SneakyThrows
    public static void cleanPostgreDataBase() {
        var connection = getPostgreConn();
        runner.update(connection, "DELETE FROM order_entity");
        runner.update(connection, "DELETE FROM payment_entity");
        runner.update(connection, "DELETE FROM credit_request_entity");
    }
}
