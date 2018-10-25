package commands.basic;

import commands.Command;
import commands.CommandModule;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.MysqlCore;
import sql.interfaces.SqlUserInterface;

import java.sql.Connection;

public class ShutdownCommand extends Command{


    public ShutdownCommand(CommandModule module) {
        super("shutdown", module);
    }

    @Override
    protected void doCommand(MessageReceivedEvent event) {


        /**
         * This class manages shutting down the bot. Currently, it is commented out because it uses an SQL Database
         * and verifies that the supplied user is an administrator before actually shutting down. I don't think you are
         * prepared to run this with a SQL database currently, so it's non-functioning. Talk to Conrad if you want
         * help getting this setup properly.
         */

        //Check if the user talking is an administrator before complying
        SqlUserInterface userInterface = new SqlUserInterface();

        if(!userInterface.isUserAdmin(event.getAuthor().getId())) {

            //Silent nope.jpg
            return;
        }



        System.exit(1);


    }
}
