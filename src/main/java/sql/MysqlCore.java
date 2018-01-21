package sql;

import java.sql.DriverManager;
import java.sql.SQLException;

import core.BotConfigurationManager;
import core.ConfigurationVariable;

import java.sql.Connection;

public class MysqlCore {


    protected Connection connection;

    /**
     * Constructor which creates the SQL connection if an instance of this class doesn't already exist.
     */
    protected MysqlCore() {

       createSqlInstanceConnection();

    }

    /**
     * Method which creates an SQL connection, and stores the instance
     */
    private void createSqlInstanceConnection() {

        //Attempt to load the MYSQL driver
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {

            System.out.println("[Error] Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }


        //Attempt to connect to the database.
        try {

            BotConfigurationManager configManager = core.BotConfigurationManager.getInstance();

            this.connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"
                                    + configManager.getPropertyValue(ConfigurationVariable.MYSQL_DATABASE_NAME)
                                    + "?useSSL=false"
                            , configManager.getPropertyValue(ConfigurationVariable.MYSQL_USERNAME)
                            , configManager.getPropertyValue(ConfigurationVariable.MYSQL_PASSWORD));
        }

        catch (SQLException e) {

            System.out.println("Connection Failed! Check console output for debugging");
            e.printStackTrace();
            return;
        }

        //If the connection is null for whatever reason, warn the user
        if(connection == null) {

            System.err.println("[Warning] Connection to SQL database failed for an unknown reason");
        }


    }
}




