package ru.stqa.pft.m2.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void loginAdmin(String username, String password) {
        type(By.name("login[username]"),username);
        type(By.name("login[password]"),password);
        click(By.cssSelector("button.action-login.action-primary"));
    }
}
