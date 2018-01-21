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


        //Check if the user talking is an administrator before complying
        SqlUserInterface userInterface = new SqlUserInterface();

        if(!userInterface.isUserAdmin(event.getAuthor().getId())) {

            //Silent nope.jpg
            return;
        }

        System.exit(1);

    }
}
