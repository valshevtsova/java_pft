package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.By;

public class AccountHelper extends HelperBase {

    public AccountHelper(ApplicationManager app) {
        super(app);
    }

    public void login() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[type = 'submit']"));
    }

    public void gotoManageUserPage() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }
}
