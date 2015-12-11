package fishing.sunshine.security;

import fishing.sunshine.form.LoginForm;
import fishing.sunshine.model.Account;
import fishing.sunshine.service.AccountService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sunshine on 2015/8/14.
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    /**
     * 登录验证
     *
     * @param authenticationToken
     * @return
     * @throws org.apache.shiro.authc.AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        LoginForm form = new LoginForm();
        form.setEmail(token.getUsername());
        form.setPassword(new String(token.getPassword()));
        Account account = new Account(form);
        ResultData result = accountService.queryAccount(account);
        if (result.getResponseCode() != ResponseCode.RESPONSE_OK) {
            return null;
        }
        Account current = ((List<Account>) result.getData()).get(0);
        if (current != null) {
            Subject subject = SecurityUtils.getSubject();
            if (subject != null) {
                Session session = subject.getSession();
                session.setAttribute("current", current);
            }
            return new SimpleAuthenticationInfo(current, token.getPassword(), getName());
        }
        return null;
    }

    /**
     * 权限检查
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }
}
