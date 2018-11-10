package commands.DnD;

import commands.CommandModule;

public class DnDModule extends CommandModule {

    public DnDModule() {
        super(null, "");

        this.addCommand(new CreateCharacter(this));
        this.addCommand(new MyCharacters(this));
        this.addCommand(new SelectCharacter(this));


    }

    @Override
    protected String getCompleteName() {

        return "";
    }


}
