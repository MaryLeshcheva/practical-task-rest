import coreDB.DatabaseConfig;
import coreDB.Queries;
import org.aeonbits.owner.ConfigFactory;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseTestDB {

    private static Connection connection;

    @BeforeAll
    public static void prepareConnection() throws SQLException { //предусловие для всех тестов BD
        DatabaseConfig databaseConfig = ConfigFactory.create(DatabaseConfig.class);
        String databaseUrl = databaseConfig.databaseUrl();
        String databaseUser = databaseConfig.databaseUser();
        String databasePass = databaseConfig.databasePass();

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(databaseUrl);
        dataSource.setUser(databaseUser);
        dataSource.setPassword(databasePass);

        connection = dataSource.getConnection();
    }

    //    @Step("Проверка добавления товара через БД")
    public static void checkProductAvailability(String name, String type, Boolean exotic) throws SQLException { //проверить добавление товара
        try (PreparedStatement selectStatement = connection.prepareStatement(Queries.selectProduct)) {
            selectStatement.setString(1, name);
            selectStatement.setString(2, type);
            selectStatement.setBoolean(3, exotic);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                boolean result = resultSet.next();
                Assertions.assertTrue(result, "Товар не найден в таблице");
            }
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException { //постусловие для всех тестов
        connection.close();
    }


}
