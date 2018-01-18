package sql;

import java.sql.DriverManager;
import java.sql.SQLException;

import core.BotConfigurationManager;
import core.ConfigurationVariable;

import java.sql.Connection;

public class MysqlCore {


    protected Connection connection;
    protected static MysqlCore instance = null;

    /**
     * Constructor which creates the SQL connection if an instance of this class doesn't already exist.
     */
    protected MysqlCore() {

        //Singleton implementation if the instance isn't already populated. Only create the connection once.
        if(sql.MysqlCore.instance == null) {

            sql.MysqlCore.instance = new MysqlCore();
            createSqlInstanceConnection();
        }

    }

    /**
     * Method which creates an SQL connection, and stores the instance
     */
    private void createSqlInstanceConnection() {

        //Attempt to load the MYSQL driver
        try {

            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

            System.out.println("[Error] Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("Mysql Driver registered!");

        //Attempt to connect to the database.
        try {

            BotConfigurationManager configManager = core.BotConfigurationManager.getInstance();

            this.connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"
                                    + configManager.getPropertyValue(ConfigurationVariable.MYSQL_DATABASE_NAME)
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




