package core;

import commands.CommandListener;
import core.enums.ConfigurationVariable;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class BotCore {

    private JDA jda;
    private BotConfigurationManager configurationManager = null;

    /**
     * A boolean tracking if there is an active SQL connection. If set to false, functions requiring such
     * will not be processed.
     * This is automatically set to false by the SqlCore if a connection cannot be made.
     */
    public static boolean sqlConnectionEstablished = true;

    /**
     * Main Bot Constructor.
     * This creates the JDA instance, and attaches all of the available callbacks so that everything runs correctly.
     */
    public BotCore() {

        //Create the JDA instance
        this.configurationManager = BotConfigurationManager.getInstance();
        this.createBotInstance();
        this.attachCallbacks();

    }

    /**
     * Create the {@link JDA} instance thus interfacing functionality with the Discord API framework.
     */
    private void createBotInstance() {

        try {

            this.jda = new JDABuilder(AccountType.BOT)
                    .setToken(configurationManager.getPropertyValue(ConfigurationVariable.DISCORD_BOT_API_KEY))
                    .buildBlocking();

        } catch (InterruptedException e) {

            System.err.println("[Fatal] Something bad happened. See stacktrace");
            e.printStackTrace();

        } catch (LoginException e) {

            System.err.println("[Fatal] Unable to connect with the specified token!");
            System.exit(1);

        }
    }

    /**
     * Attach all of the Listener classes which need to interface with the Discord API
     */
    private void attachCallbacks() {

        this.jda.addEventListener(new CommandListener());

    }

    /**
     * This method is called every time the System.shutdown() command is called.
     */
    private void shutdown() {

        //Add things here
    }

    public static void main(String[] args) {

        //Create the new instance
        BotCore bot = new BotCore();

        //Add a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> bot.shutdown()));
    }
}
