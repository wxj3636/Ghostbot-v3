package commands;

@SuppressWarnings("serial")
public class CommandNotFoundException extends Exception {

    @Override public String getMessage() {
        return "The specified command was not found";
    }

}
