package commands;

import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {

    /**
     * Name of the command
     */
    private String name;

    /**
     * Reference to the module of the command
     */
    private CommandModule module;

    /**
     * Constructor that requires the name of the command
     *
     * @param name  the name of the command
     */
    public Command(String name, CommandModule module) {
        this.name = name;
        this.module = module;
    }

    /**
     * Method to get the arguments of the current command as a list
     * containing multiple strings, split by a comma character
     *
     * @param message  the message that called the command
     * @return a list of the comma-split arguments for the command
     */
    protected List<String> getArguments(Message message) {

        // get the content past the initial command
        String contents = message.getContentRaw().substring(
                this.getPath().length() + 1);

        // split the string on any comma characters
        List<String> list = Arrays.asList(contents.split(";"));

        // check each element for a leading whitespace character
        for (int i = 0; i < list.size(); i ++) {

            // remove the first character if it is a whitespace character
            String temp = list.get(i);
            while (temp.startsWith(" ")) {
                temp = temp.substring(1);
            }

            // re-set the element in the list
            list.set(i, temp);

        }

        // return the list
        return list;

    }


    public String getName() {
        return this.name;
    }


    private String getPath() {
        return this.module.getCompleteName() + " " + this.getName();
    }


    protected abstract void doCommand(MessageReceivedEvent event);


    void execute(MessageReceivedEvent event) {

        // log the message
        String str = String.format("[Command] User %s called command %s",
                event.getAuthor().getName(), event.getMessage().getContentRaw().substring(1));
        System.out.println(str);

        // execute the current command
        this.doCommand(event);

    }

}