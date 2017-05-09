package ru.stqa.pft.m2.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

    private SessionHelper sessionHelper;
    private CustomerHelper customerHelper;
    private NavigationHelper nav;
    private ILNHelper ilnHelper;
    private String browser;

    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
        if (browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)){
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)){
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        customerHelper = new CustomerHelper(wd);
        ilnHelper = new ILNHelper(wd);
        nav = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.loginAdmin(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        wd.quit();
    }

    public NavigationHelper getNav() {
        return nav;
    }

    public CustomerHelper getCustomerHelper() {
        return customerHelper;
    }

    public ILNHelper getIlnHelper() { return ilnHelper; }
}
