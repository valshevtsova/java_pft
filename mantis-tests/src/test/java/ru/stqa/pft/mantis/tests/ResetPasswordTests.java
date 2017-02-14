package ru.stqa.pft.mantis.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword() {
        app.account().login();
        app.account().gotoManageUserPage();

    }
}
