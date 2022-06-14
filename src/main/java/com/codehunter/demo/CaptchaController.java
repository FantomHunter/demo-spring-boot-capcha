/*
 * CaptchaController.java
 *
 * Copyright by CRIF AG
 * Zürich
 * All rights reserved.
 */
package com.codehunter.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author hvhai
 * @version $Revision: $
 */
@Controller
public class CaptchaController
{
    public static final String FILE_TYPE = "jpeg";

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);
        response.setContentType("image/jpeg");

        String captchaStr = "";

        System.out.println("------------------In captcha---------------");

        captchaStr = CaptchaUtil.generateCaptchaTextMethod2(6);

        try
        {
            int width = 100;
            int height = 40;

            Color bg = new Color(0, 255, 255);
            Color fg = new Color(0, 100, 0);

            Font font = new Font("Arial", Font.BOLD, 20);
            BufferedImage cpimg = new BufferedImage(width, height, BufferedImage.OPAQUE);
            Graphics g = cpimg.createGraphics();

            g.setFont(font);
            g.setColor(bg);
            g.fillRect(0, 0, width, height);
            g.setColor(fg);
            g.drawString(captchaStr, 10, 25);

            HttpSession session = request.getSession(true);
            session.setAttribute("CAPTCHA", captchaStr);

            OutputStream outputStream = response.getOutputStream();

            ImageIO.write(cpimg, FILE_TYPE, outputStream);
            outputStream.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

/*
 * Changes:
 * $Log: $
 */