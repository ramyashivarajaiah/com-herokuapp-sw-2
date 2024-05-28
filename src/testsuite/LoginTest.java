package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
/*
userShouldLoginSuccessfullyWithValidCredentials
* Enter “tomsmith” username
* Enter “SuperSecretPassword!” password
* Click on ‘LOGIN’ button
* Verify the text “Secure Area”
 */

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("radius")).click();
        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//div[@id=\"content\"]/div/h2")).getText();
        Assert.assertEquals(expectedText, actualText);
    }
/*
verifyTheUsernameErrorMessage
* Enter “tomsmith1” username
* Enter “SuperSecretPassword!” password
* Click on ‘LOGIN’ button
* Verify the error message “Your username
is invalid!”
 */

    @Test
    public void verifyTheUsernameErrorMessage() {
        driver.findElement(By.name("username")).sendKeys("tomsmith1");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("radius")).click();
        String expectedMessage = "Your username is invalid!\n×";
        String actualMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        Assert.assertEquals(expectedMessage, actualMessage);

    }

    /*
    verifyThePasswordErrorMessage
* Enter “tomsmith” username
* Enter “SuperSecretPassword” password
* Click on ‘LOGIN’ button
* Verify the error message “Your password
is invalid!”
 */

    @Test
    public void verifyThePasswordErrorMessage() {
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.className("radius")).click();
        String expectedMessage = "Your password is invalid!\n" + "×";
        String actualMessage = driver.findElement(By.xpath("//div[@id='flash-messages']//div[1]")).getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }


    @After
    public void tearDown() {
        //  closeBrowser();
    }
}

