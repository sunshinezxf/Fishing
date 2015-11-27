package fishing.sunshine.util;

/**
 * Created by sunshine on 11/27/15.
 */
public class ResultData {
    private ResponseCode responseCode;
    private Object data;
    private String description;

    public ResultData() {
        responseCode = ResponseCode.RESPONSE_OK;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
