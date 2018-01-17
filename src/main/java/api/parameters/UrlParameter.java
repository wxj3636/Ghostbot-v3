package api.parameters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlParameter {
    private final String key;
    private final String value;

    /**
     * Parameters must be encoded as strings. This is the constructor
     * which is run to store such values.
     *
     * Each of the overloads merely converts the data types into String and invokes
     * this method.
     *
     * @param key
     * @param value
     */
    public UrlParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public UrlParameter(String key, int value) {
        this(key, String.valueOf(value));
    }

    public UrlParameter(String key, long value) {
        this(key, String.valueOf(value));
    }

    public UrlParameter(String key, boolean value) {
        this(key, value ? "true" : "false");
    }

    public UrlParameter(String key, Object value) {
        this(key, value.toString());
    }

    /**
     * Method which returns the key for this object
     * @return The objects key
     */
    protected String getKey() {

        return this.key;
    }

    /**
     * Method which returns the value for this object
     * @return The objects value
     */
    protected String getValue() {
        return this.value;
    }

    /**
     * ToString needs to convert this parameter into the proper format which can be put into a URL.
     * Example: Key = Money, Value = Dollar --> "Money=Dollar" (In UTF-8 Encoding)
     */
    @Override
    public String toString() {
        String parameter = null;
        try {
            parameter = URLEncoder.encode(getKey(), "UTF-8") + "=" + URLEncoder.encode(getValue(), "UTF-8");

        } catch (UnsupportedEncodingException ex) {

            //TODO: You should never get this, but interface this with the bot logger.

        }
        return parameter;
    }

}