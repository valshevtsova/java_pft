package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.By;

public class AccountHelper extends HelperBase {

    public AccountHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[type = 'submit']"));
    }

    public void gotoManageUserPage() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }

    public void initResetUserPassword(int id) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_edit_page.php?user_id=" + id);
        click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
    }
}
