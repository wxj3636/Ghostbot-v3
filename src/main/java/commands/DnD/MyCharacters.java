package commands.DnD;

import commands.Command;
import commands.CommandModule;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sql.interfaces.SqlUserInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyCharacters extends Command {
    /**
     * Constructor that requires the name of the command
     *
     * @param module
     */
    public MyCharacters(CommandModule module) {
        super("myCharacters", module);
    }

    @Override
    protected void doCommand(MessageReceivedEvent event) {

        if(event.getChannelType() == ChannelType.PRIVATE) {
            //Get the discordUserId and Username of the user requesting to be registered
            String discordId = event.getAuthor().getId();

            //Check to see if the user already exists
            SqlUserInterface userInterface = new SqlUserInterface();


            //If the user does not exist, add them to the database
            ResultSet temp = userInterface.myCharacters(discordId);
            //event.getChannel().sendMessage("You've been registered with Ghostbot!\nCommands are now available for use.").queue();

            //event.getChannel().getMessageById(event.getChannel().getLatestMessageId());

            System.out.println(temp);
            try {
                while (temp.next()) {
                    String characterName = temp.getString("character_name");
                    int characterId = temp.getInt("characterId");
                    System.out.println(characterName + "\t\t\t" + characterId );
                    //event.getChannel().sendMessage(String.format("%s%-30s%d", characterName, characterId)).queue();
                    event.getChannel().sendMessage(characterName + "\t\t\t" + characterId).queue();
                }
            } catch (SQLException e ) {
                //e.printSQLException();
            }



        }
        else{
            event.getChannel().sendMessage("Try again in a Private Message.").queue();
        }



    }
}
