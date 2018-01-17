package sql;

import sql.exceptions.SqlStatementExecutionError;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInterface extends MysqlCore{

    /**
     * Singleton instance of the SqlInterface, only allowing one connection to the database at a time.
     */
    protected static SqlInterface instance;

    public static SqlInterface getInstance() {

        if(sql.SqlInterface.instance == null)
            sql.SqlInterface.instance = new SqlInterface();

        return sql.SqlInterface.instance;

    }

    protected SqlInterface() {

        //Init the SqlCore parent class
        super();

    }

    /**
     * Method creating a statement which returns a response from the SQL database from a string
     */
    public ResultSet ExecuteSelectStatement(String query) throws SqlStatementExecutionError {

        try {

            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            return rs;

        }
        catch (SQLException e) {

            throw new SqlStatementExecutionError(e.getMessage());

        }
    }

}
