package sql.interfaces;

import sql.exceptions.SqlStatementExecutionError;
import sql.exceptions.UserNotExistsException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUserInterface extends SqlGenericInterface {

    /**
     * Constructor which creates the SQL connections, and prepares the class for use
     */
    public SqlUserInterface() {
        super();
    }

    

    /**
     * Method which checks if a registered user is an Administrator or not, given their unique discord ID
     * @param userId The unique snowflake ID referencing a discord user account
     */
    public boolean isUserAdmin(String userId) throws UserNotExistsException {

        //Build the SQL query
        String query = "SELECT is_admin FROM discord_users WHERE snowflake_id = " + userId;

        try {

            ResultSet result = this.executeSelectStatement(query);

            //Check if the ResultSet is empty. If so, throw a unique error stating such.
            if(!result.next()) {
                throw new UserNotExistsException();
            }

            //Set the ResultSet back to it's beginning and extrapolate it's data
            return result.getBoolean(1);



        } catch (SqlStatementExecutionError ex) {

            //There was a failure with the SQL syntax somewhere. You should hopefully never see this.
            //TODO: Change this to register with the Discord Bot Logger
            System.err.println("[Warning] SQL Syntax error in isUserAdmin function.");

        } catch (SQLException e) {

            //Something went wrong with the ResultSet. You should never see this.
            //TODO: Change this to register with the Discord Bot Logger
            System.err.println("[Warning] SQL ResultSet object error in isUserAdmin function.");
        }

        //If for some reason we make it down here, we claim the user isn't an admin.
        return false;
    }


}
