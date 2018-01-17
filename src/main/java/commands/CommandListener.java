package commands;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    private CommandManager manager = null;

    @Override public void onMessageReceived(MessageReceivedEvent event) {

        Message message = event.getMessage();
        String contents = message.getContentRaw();

        //This line is important. This sets the prefix which the system responds to.
        if (!contents.startsWith("!")) {

            return;
        }

        this.manager = CommandManager.getInstance();
        contents = contents.substring(1,  contents.length());

        try {

            Command command = this.manager.getCommand(contents);
            command.execute(event);

            //Handle if the command was not found
        } catch (CommandNotFoundException ex) {

            System.out.println(String.format("[Command] User %s entered invalid command %s", message.getAuthor().getName(), contents));
        }
    }
}
