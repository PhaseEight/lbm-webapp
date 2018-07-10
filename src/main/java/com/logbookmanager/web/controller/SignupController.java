package com.logbookmanager.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/user/signup")
public class SignupController {

    private static final Logger logger = LoggerFactory.getLogger(SignupController.class.getName());

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getSignupForm(Locale locale, Model model) {
        logger.info("getRegisterForm for {}.", locale);

        // return the name of the View to be loaded
        return "signup-form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doSignup(Locale locale, Model model) {
        logger.debug("doSignup {}.", locale);

        model.addAttribute("signup-success-message", "doSignup has loaded");

        // return the name of the View to be loaded
        return "signup-success";
    }

}
