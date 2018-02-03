package moderation;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ModerationCore extends ListenerAdapter {


    /**
     * This is the main function which is triggered every time there is a new message in a channel which this Bot
     * is located in. Use the 'event' parameter to pull all of the required information.
     * @param event The Discord event containing all of the information about an individual message as it's seen by the bot
     */
    @Override public void onMessageReceived(MessageReceivedEvent event) {


    }
}
