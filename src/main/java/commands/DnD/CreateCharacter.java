package commands.DnD;

import commands.Command;
import commands.CommandModule;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CreateCharacter extends Command {


    public CreateCharacter(CommandModule module) {
        super("createcharacter", module);
    }

    @Override
    protected void doCommand(MessageReceivedEvent event) {

        event.getChannel().sendMessage("Character Created.").queue();



    }





}
