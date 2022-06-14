/*
 * HomeController.java
 *
 * Copyright by CRIF AG
 * Zürich
 * All rights reserved.
 */
package com.codehunter.demo;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;


/**
 * @author hvhai
 * @version $Revision: $
 */
@Controller
public class HomeController
{
    public static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/home")
    public String getHome(RedirectAttributes redirectAttributes, Model model)
    {
        String message = (String)redirectAttributes.getAttribute("message");
        log.info("message" + message);
        if (!StringUtils.isEmpty(message))
        {
            model.addAttribute("message", "Wrong captcha");
        }
        return "home";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("user")
    User user, BindingResult errors, Model model, HttpSession session, final RedirectAttributes redirectAttributes)
    {
        String captcha = (String)session.getAttribute("CAPTCHA");
        log.info("User: " + user);
        log.info("captcha: " + captcha);

        if (user.getCaptcha().equals(captcha))
        {
            return "redirect:user/" + user.getUsername();
        }
        else
        {
//            model.addAttribute("message", "Wrong captcha");
            redirectAttributes.addFlashAttribute("message", "Wrong captcha");
            return "redirect:home";
        }

    }


    @GetMapping("/user/{username}")
    public String getUser(@PathVariable
    String username, Model model)
    {
        model.addAttribute("username", username);
        return "user";
    }
}

/*
 * Changes:
 * $Log: $
 */