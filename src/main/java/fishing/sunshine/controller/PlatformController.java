package fishing.sunshine.controller;

import fishing.sunshine.form.LoginForm;
import fishing.sunshine.model.Account;
import fishing.sunshine.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by sunshine on 11/9/15.
 */
@RestController
public class PlatformController {
    private Logger logger = LoggerFactory.getLogger(PlatformController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Valid LoginForm form, BindingResult result, HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.setViewName("redirect:/login");
            return view;
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                view.setViewName("redirect:/dashboard");
                return view;
            }
            Account param = new Account(form);
            Account current = ((List<Account>) accountService.queryAccount(param).getData()).get(0);
            subject.login(new UsernamePasswordToken(form.getEmail(), form.getPassword()));
            request.getSession().setAttribute("current", current);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            view.setViewName("redirect:/login");
            return view;
        }
        view.setViewName("redirect:/dashboard");
        return view;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView view = new ModelAndView();
        view.setViewName("dashboard");
        return view;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        ModelAndView view = new ModelAndView();
        session.removeAttribute("current");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        view.setViewName("redirect:/index");
        return view;
    }
}
