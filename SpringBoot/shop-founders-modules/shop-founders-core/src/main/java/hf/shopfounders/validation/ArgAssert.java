package hf.shopfounders.validation;

import org.apache.commons.lang3.StringUtils;

/**
 * This class asserts that certain conditions related to method arguments are
 * met. Each method throws an {@link IllegalArgumentException} if the condition
 * is not met.
 */
public final class ArgAssert {
    /**
     * Private constructor to avoid instantiation.
     */
    private ArgAssert() {
    }

    /**
     * Asserts that an argument is not null.
     *
     * @param <T>
     *            the type of the argument
     * @param arg
     *            the argument to assert.
     * @param argName
     *            the name of the argument.
     * @return the non-null argument that was validated.
     * @throws IllegalArgumentException
     *             if the argument is null.
     */
    public static <T> T assertNotNull(final T arg, final String argName) {
        if (null == arg) {
            throw new IllegalArgumentException(getNullErrorMessage(argName));
        }
        return arg;
    }

    /**
     * get error message for null.
     *
     * @param argName
     *            the name of argument
     * @return the message for null parameter
     */
    static String getNullErrorMessage(final String argName) {
        return argName + " is null.";
    }

    /**
     * Asserts that a specified string is neither null nor empty.
     *
     * <ul>
     * <li>{@code assertNotEmpty(null) throws an IllegalArgumentException}</li>
     * <li>{@code assertNotEmpty("") throws an IllegalArgumentException}</li>
     * <li>{@code assertNotEmpty(" ") returns " "}</li>
     * <li>{@code assertNotEmpty("a") returns "a"}</li>
     * <li>{@code assertNotEmpty("a b") returns "a b"}</li>
     * </ul>
     *
     * @param arg
     *            the string to check for emptiness
     * @param argName
     *            the name of the argument
     * @return the string that was validated
     */
    public static String assertNotEmpty(final String arg, final String argName) {
        ArgAssert.assertNotNull(arg, argName);

        if (StringUtils.isEmpty(arg)) {
            throw new IllegalArgumentException(getEmptyErrorMessage(argName));
        }

        return arg;
    }

    /**
     * get error message for empty.
     *
     * @param argName
     *            the name of argument
     * @return the error message for empty paramater
     */
    private static String getEmptyErrorMessage(final String argName) {
        return argName + " is empty.";
    }

    /**
     * Asserts that a specified string is neither null, nor empty, nor blank.
     *
     * <ul>
     * <li>{@code assertNotBlank(null) throws an IllegalArgumentException}</li>
     * <li>{@code assertNotBlank("") throws an IllegalArgumentException}</li>
     * <li>{@code assertNotBlank(" ") throws an IllegalArgumentException}</li>
     * <li>{@code assertNotBlank("a") returns "a"}</li>
     * <li>{@code assertNotBlank("a b") returns "a b"}</li>
     * </ul>
     *
     * @param arg
     *            the string argument to check for blankness
     * @param argName
     *            the name of the argument
     * @return the string that was validated
     */
    public static String assertNotBlank(final String arg, final String argName) {
        if (StringUtils.isBlank(arg)) {
            throw new IllegalArgumentException(argName + " is blank");
        }
        return arg;
    }
}
