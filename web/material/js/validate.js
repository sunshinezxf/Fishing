/**
 * Created by sunshine on 2015/8/17.
 */
function login_validate() {
    var username = $("#account_email").val();
    var password = $("#account_password").val();
    if (not_null(username) && not_null(password)) {
        if (email_validate(username)) {
            return true;
        }
    }
    return false;
}

function register_validate() {
    var email = $("#account_email").val();
    var name = $("#account_username").val();
    var password = $("#account_password").val();
    var verify_password = $("#confirm_account_password").val();
    if (not_null(email) && not_null(name) && not_null(password) && not_null(verify_password)) {
        if (email_validate(email) && password == verify_password) {
            return true;
        }
    }
    return false;
}

function not_null(item) {
    if (item == null || item == "" || item.length <= 0) {
        return false;
    }
    return true;
}

function email_validate(email) {
    var pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    return pattern.test(email);
}
