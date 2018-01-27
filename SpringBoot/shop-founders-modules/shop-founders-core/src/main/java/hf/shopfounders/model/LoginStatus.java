package hf.shopfounders.model;

public class LoginStatus {

    private boolean status;
    private String message;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format(
                "LoginStatus[status='%s', message='%s']",
                status, message);
    }
}