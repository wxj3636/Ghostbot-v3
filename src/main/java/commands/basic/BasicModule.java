package commands.basic;

import commands.CommandModule;

public class BasicModule extends CommandModule {

    public BasicModule() {

        super(null, "");

        //Add all of the available commands here
        this.addCommand(new ShutdownCommand(this));

    }

    @Override
    protected String getCompleteName() {

        return "";
    }

}
