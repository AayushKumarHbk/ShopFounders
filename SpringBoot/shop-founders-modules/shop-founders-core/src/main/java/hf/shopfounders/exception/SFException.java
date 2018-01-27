package hf.shopfounders.exception;

public class SFException extends RuntimeException {

    /**
     * error code
     */
    private Enum code;
    /**
     * BRSError information.
     */
    private SFErrorInfo sfErrorInfo ;
    /**
     * the instance of object array.
     */
    private Object[] messageParameters = null;

    /**
     *
     * @param errorInfo
     */
    public SFException(SFErrorInfo errorInfo) {
        this.sfErrorInfo=errorInfo;
    }
    /**
     * Class constructor
     *
     * @param code the error code ,it can not be empty
     * @param format the format
     * @param args the array of objects
     */
    protected SFException(Enum code, String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
    }

    /**
     * Class constructor
     *
     * @param cause the error cause
     * @param code the error code and it can not be empty
     * @param format the format
     * @param args the array of objects
     */
    protected SFException(Throwable cause, Enum code, String format,
                           Object... args) {
        super(String.format(format, args), cause);
        this.code = code;
    }

    /**
     * Class constructor
     *
     * @param code  error code and it can not be empty
     * @param message the error message
     */
    protected SFException(Enum code, String message) {
        super(message);
        this.code = code;
    }
    /**
     * Class constructor
     *
     * @param errorInfo the instance of {@link SFErrorInfo}
     * @param cause the isntance of {@link Throwable}
     */
    public SFException(SFErrorInfo errorInfo, Throwable cause) {
        super(cause);
        this.sfErrorInfo=errorInfo;
    }

    /**
     * Class constructor
     *
     * @param errorInfo the instance of {@link SFErrorInfo}
     * @param arguments the array of {@link Object}
     */
    public SFException(SFErrorInfo errorInfo, Object... arguments) {
        this.messageParameters =arguments;
        this.sfErrorInfo=errorInfo;
    }

    /**
     * Class constructor
     *
     * @param errorInfo the instance of {@link SFErrorInfo}
     * @param cause the instance of {@link Throwable}
     * @param arguments the array of {@link Object}
     */
    public SFException(SFErrorInfo errorInfo, Throwable cause, Object... arguments) {
        super(cause);
        this.messageParameters =arguments;
        this.sfErrorInfo=errorInfo;
    }

    /**
     * Gets error code.
     * @return the error code'
     */
    public Enum getCode() {
        return this.code;
    }

    /**
     * Gets array of objects.
     * @return the array of instance of {@link Object}.
     */
    public Object[] getMessageParameters() {
        return messageParameters;
    }

    /**
     * Gets SFErrorInfo
     * @return the instance of {@link SFErrorInfo}
     */
    public SFErrorInfo getSFErrorInfo() {
        return this.sfErrorInfo;
    }

}
