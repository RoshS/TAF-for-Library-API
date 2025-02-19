package ua.com.epam.utils.data;

import lombok.extern.log4j.Log4j;
import ua.com.epam.config.DataProp;
import ua.com.epam.core.client.mysql.MySQLClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j
public class BaseData {
    private Connection connection;
    private Statement statement;
    protected ResultSet resultSet;
    protected DataProp dp;

    protected BaseData() {
        this.connection = MySQLClient.getConnection();
        this.dp = new DataProp();
    }

    protected void execute(String query) {
        try {
            statement = connection.createStatement();
            log.debug("Execute query: " + query + "...");
            statement.execute(query);

            resultSet = statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void close() {
        if (resultSet != null) {
            log.debug("Try to close ResultSet...");
            try {
                resultSet.close();
                log.debug("ResultSet was closed successfully!");
            } catch (SQLException e) {
                log.error("Database access error occurs! Can't close ResultSet!");
                e.printStackTrace();
            }
        }

        if (statement != null) {
            log.debug("Try to close Statement...");
            try {
                statement.close();
                log.debug("Statement was closed successfully!");
            } catch (SQLException e) {
                log.error("Database access error occurs! Can't close Statement!");
                e.printStackTrace();
            }
        }
    }
}
