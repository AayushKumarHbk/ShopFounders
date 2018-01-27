package hf.shopfounders.exception;

import java.util.Arrays;
import java.util.ResourceBundle;

public interface BaseErrorCode {

    public String CODE1000 = "1000";  // Invalid Request
    public String CODE1001 = "1001";  // Json processing error
    public String CODE1002 = "1002";  // A field has improper format
    public String CODE1003 = "1003";  // Required field(s) missing
    public String CODE1101 = "1004";  // Invalid email/password pair

    public String CODE2000 = "2000";  // Duplicate found
    public String CODE2001 = "2001";  // unexpected error with MongoDB

    ResourceBundle BASE_LABELS = ResourceBundle.getBundle("BaseErrorCode");

    static String getString(String code, ResourceBundle... bundles) {
        return Arrays.stream(bundles)
                .filter(b -> b.containsKey(code))
                .findFirst()
                .map(b -> b.getString(code))
                .orElseThrow(() -> new IllegalArgumentException("Cannot find the message for error key " + code + ". Check your resources files."));
    }

    static String getString(String code) {
        return getString(code,BASE_LABELS);
    }
}
