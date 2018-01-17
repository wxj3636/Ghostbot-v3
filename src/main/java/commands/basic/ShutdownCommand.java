package commands.basic;

import commands.Command;
import commands.CommandModule;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ShutdownCommand extends Command{


    public ShutdownCommand(CommandModule module) {
        super("shutdown", module);
    }

    @Override
    protected void doCommand(MessageReceivedEvent event) {

        //TODO: Implement user administration checking before shutting down. (Requires user management functionality)
        System.exit(1);

    }
}
