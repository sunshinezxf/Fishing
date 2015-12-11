package fishing.sunshine.model;

import fishing.sunshine.form.LoginForm;
import fishing.sunshine.util.Encryption;

/**
 * Created by sunshine on 12/11/15.
 */
public class Account extends SunshineEntity {
    private String accountId;
    private String username;
    private String password;

    public Account() {
        super();
    }

    public Account(LoginForm form) {
        this();
        this.username = form.getEmail();
        this.password = Encryption.desEncode(form.getPassword(), username);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
