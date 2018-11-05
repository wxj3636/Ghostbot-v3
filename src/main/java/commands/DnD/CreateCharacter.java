package commands.DnD;

import commands.Command;
        import commands.CommandModule;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.interfaces.SqlUserInterface;

public class CreateCharacter extends Command {


    public CreateCharacter(CommandModule module) {
        super("createCharacter", module);
    }

    @Override
    protected void doCommand(MessageReceivedEvent event) {

        if(event.getChannelType() == ChannelType.PRIVATE) {
            //Get the discordUserId and Username of the user requesting to be registered
            String discordId = event.getAuthor().getId();

            //Check to see if the user already exists
            SqlUserInterface userInterface = new SqlUserInterface();

            String characterName = String.valueOf(event.getMessage().getContentRaw().substring(17));

            System.out.println(characterName);

            //If the user does not exist, add them to the database
            userInterface.createCharacter(discordId, characterName);
            //event.getChannel().sendMessage("You've been registered with Ghostbot!\nCommands are now available for use.").queue();

            event.getChannel().sendMessage("Character Created.").queue();

            event.getChannel().sendMessage("Your new character's ID is: " + userInterface.fetchCharacterID(discordId)).queue();

            //event.getChannel().getMessageById(event.getChannel().getLatestMessageId());




        }
        else{
            event.getChannel().sendMessage("Try again in a Private Message.").queue();
        }

    }





}
