package sql.interfaces;

import sql.MysqlCore;
import sql.exceptions.SqlStatementExecutionError;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlGenericInterface extends MysqlCore {

    protected SqlGenericInterface() {

        //Init the SqlCore parent class
        super();

    }

    /**
     * Method creating a statement which returns a response from the SQL database from a string
     */
    public ResultSet executeSelectStatement(String query) throws SqlStatementExecutionError {

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
