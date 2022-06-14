/*
 * User.java
 *
 * Copyright by CRIF AG
 * Zürich
 * All rights reserved.
 */
package com.codehunter.demo;

/**
 * 
 *
 * @author hvhai
 * @version $Revision:  $
 */
public class User
{
    private String username;
    private String captcha;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getCaptcha()
    {
        return captcha;
    }

    public void setCaptcha(String captcha)
    {
        this.captcha = captcha;
    }

    @Override
    public String toString()
    {
        return "User [username=" + username + ", captcha=" + captcha + "]";
    }
    

}


/*
 * Changes:
 * $Log: $
 */