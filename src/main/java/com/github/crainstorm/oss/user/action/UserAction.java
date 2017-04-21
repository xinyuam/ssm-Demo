package com.github.crainstorm.oss.user.action;

import com.github.crainstorm.oss.user.entity.User;
import com.github.crainstorm.oss.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chen on 4/17/17.
 */
@Controller
@RequestMapping("/user")
public class UserAction {

    private static Logger logger = LogManager.getLogger(UserAction.class);
    @Autowired
    private UserService service;

    @RequestMapping("/checkLogin")
    public String checkLogin(User user) {
        logger.trace("enter checkLogin...");
        if (logger.isDebugEnabled()) {
            logger.debug(user);
        }
        if (service.loginSuccess(user)) {
            logger.info("login success...");
            return "redirect:/index.jsp";
        } else {
            logger.info("login failed...");
            return "login/user";
        }
    }

    @RequestMapping("testTransaction")
    public String testTransaction() {
        if (AopUtils.isAopProxy(service)) {
            logger.debug("service is aop proxy..");
        } else {
            logger.debug("service isn't aop proxy...");
        }
        service.testTransaction();

        return "redirect:/index.jsp";
    }
}
