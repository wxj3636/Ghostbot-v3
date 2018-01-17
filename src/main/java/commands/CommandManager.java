package commands;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class CommandManager {

    //SQL connection core for running queries with commands
    private Connection databaseConnection = null;

    private List<CommandModule> modules;

    private CommandManager() {


        this.modules = new ArrayList<CommandModule>();

        //Insert the modules here
        //this.modules.add(new BasicModule(databaseConnection));

    }

    private static CommandManager instance = null;


    public static CommandManager getInstance() {
        if (CommandManager.instance == null) {
            CommandManager.instance = new CommandManager();
        }

        return CommandManager.instance;
    }

    public Command getCommand(String message) throws CommandNotFoundException {

        for(CommandModule module : this.modules) {

            try {

                Command command = module.getCommand(message);
                return command;
            } catch (CommandNotFoundException ex) {
                //Go to next
            }
        }

        throw new CommandNotFoundException();
    }

}
