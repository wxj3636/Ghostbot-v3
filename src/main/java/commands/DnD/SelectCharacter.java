package commands.DnD;

import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.interfaces.SqlUserInterface;

public class SelectCharacter extends Command {
    public SelectCharacter(DnDModule module) { super("selectCharacter", module); }


    @Override
    protected void doCommand(MessageReceivedEvent event) {


        //Get the discordUserId and Username of the user requesting to be registered
        String discordId = event.getAuthor().getId();

        //Check to see if the user already exists
        SqlUserInterface userInterface = new SqlUserInterface();

        //Retrieves characterId from the message
        String characterId = String.valueOf(event.getMessage().getContentRaw().substring(16));

        System.out.println(characterId);

        //If the user does not exist, add them to the database
        userInterface.selectCharacter(discordId, characterId);









    }
}
