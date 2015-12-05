package fishing.sunshine.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by sunshine on 12/5/15.
 */
public class ContracterForm {
    @NotNull
    @Size(min = 2, max = 8, message = "长度为2-8个字符")
    private String contracterName;

    @NotNull
    @Size(min = 2, max = 8, message = "长度为2-8个字符")
    private String contracterPhone;

    public String getContracterName() {
        return contracterName;
    }

    public void setContracterName(String contracterName) {
        this.contracterName = contracterName;
    }

    public String getContracterPhone() {
        return contracterPhone;
    }

    public void setContracterPhone(String contracterPhone) {
        this.contracterPhone = contracterPhone;
    }
}
