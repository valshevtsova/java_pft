package ru.stqa.pft.mantis.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException {
        String newpassword = "tester33";
        String username = app.getProperty("web.adminLogin");
        String password = app.getProperty("web.adminPassword");
        app.account().login(username, password);
        app.account().gotoManageUserPage();
        Users users = app.db().users();
        UserData user = users.iterator().next();
        app.account().initResetUserPassword(user.getId());
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String resetLink = findResetLink(mailMessages);
        app.registration().finish(user.getRealname(), resetLink, newpassword);
        assertTrue(app.newSession().login(user.getUsername(), newpassword));

    }

    private String findResetLink(List<MailMessage> mailMessages) {
        MailMessage mailMessage = mailMessages.stream().findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
