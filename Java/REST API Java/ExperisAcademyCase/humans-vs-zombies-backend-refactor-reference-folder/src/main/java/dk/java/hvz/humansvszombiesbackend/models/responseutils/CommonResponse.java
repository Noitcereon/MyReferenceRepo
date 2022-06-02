package dk.java.hvz.humansvszombiesbackend.models.responseutils;

import com.fasterxml.jackson.annotation.JsonInclude;

// borrowed this class from Greg
public class CommonResponse<T> {
    public static class ErrorDetails {
        private long status;
        private String message;

        public ErrorDetails(long status, String message) {
            this.status = status;
            this.message = message;
        }

        public long getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

    private Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDetails error;

    public CommonResponse(T payload) {
        this.payload = payload;
        this.success = true;
        this.error = null;
    }

    public CommonResponse(long status, String message) {
        this.payload = null;
        this.success = false;
        this.error = new ErrorDetails(status, message);
    }

    public T getPayload() {
        return payload;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ErrorDetails getError() {
        return error;
    }
}
