package hf.shopfounders.exception;

/**
 * Error information class, that define the error objects to categorize
 * different objects of {@link SFException}
 */
public class SFErrorInfo {

    /**
     * error code
     */
    private String errorCode;
    /**
     * exception message pattern
     */
    private String messagePattern;
    /**
     * error severity
     */
    private SFExceptionSeverity severity;
    /**
     * error origin
     */
    private SFExceptionOrigin origin;

    /**
     * Class constructor
     *
     * @param errorCode      <code>String</code> the error code.
     * @param messagePattern
     * @param severity       {@link SFExceptionSeverity} the error severity
     * @param origin         {@link SFExceptionOrigin} the error origin
     */
    public SFErrorInfo(String errorCode, String messagePattern, SFExceptionSeverity severity,
                        SFExceptionOrigin origin) {
        this.errorCode = errorCode;
        this.messagePattern = messagePattern;
        this.severity = severity;
        this.origin = origin;
    }

    /**
     * Class constructor
     *
     * @param errorCode <code>String</code> the error code.
     * @param severity  {@link SFExceptionSeverity} the error severity
     * @param origin    {@link SFExceptionOrigin} the error origin
     */
    public SFErrorInfo(String errorCode, SFExceptionSeverity severity, SFExceptionOrigin origin) {
        this.errorCode = errorCode;
        this.severity = severity;
        this.origin = origin;
    }

    /**
     * Gets the error code
     *
     * @return errorCode Unique Error code defined for the error
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code
     *
     * @param errorCode Unique Error code defined for the error
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the Message Pattern
     *
     * @return messagePattern Message Pattern
     */
    public String getMessagePattern() {
        return messagePattern;
    }

    /**
     * Sets the message pattern
     *
     * @param messagePattern Message Pattern
     */
    public void setMessagePattern(String messagePattern) {
        this.messagePattern = messagePattern;

    }

    /**
     * Gets error severity
     *
     * @return severity severity Severity level
     */
    public SFExceptionSeverity getSeverity() {
        return severity;
    }

    /**
     * Sets the severity
     *
     * @param severity severity Security level
     */
    public void setSeverity(SFExceptionSeverity severity) {
        this.severity = severity;
    }

    /**
     * Gets error origin
     *
     * @return Origin of the error
     */
    public SFExceptionOrigin getOrigin() {
        return origin;
    }


    /**
     * Sets error origin
     *
     * @param origin Origin of the error
     */
    public void setOrigin(SFExceptionOrigin origin) {
        this.origin = origin;
    }
}
