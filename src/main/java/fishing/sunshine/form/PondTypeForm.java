package fishing.sunshine.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by sunshine on 12/3/15.
 */
public class PondTypeForm {
    @NotNull
    @Size(min = 2, max = 8, message = "长度为2-8个字符")
    private String pondTypeName;

    public String getPondTypeName() {
        return pondTypeName;
    }

    public void setPondTypeName(String pondTypeName) {
        this.pondTypeName = pondTypeName;
    }
}
