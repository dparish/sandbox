package dparish.webdriver;

import dparish.client.view.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author dparish
 */
public class MainNavigationIT {

    WebDriver driver;
    WebDriverHelper helper;

    public MainNavigationIT() {
        driver = new FirefoxDriver();
        helper = new WebDriverHelper(driver);
    }

    @Before
    public void setup() {
        driver.get(WebDriverHelper.URL);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testSuccess() {
        helper.presenceOfElementLocated(By.ById.id("gwt-debug-" + Page.WELCOME.name()));
    }
}
