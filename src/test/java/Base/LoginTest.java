package Base;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.SecureAreaPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        SecureAreaPage secureAreaPage = loginPage.loginValidUser("tomsmith", "SuperSecretPassword!");

        String actualMessage = secureAreaPage.getSuccessMessage();
        Assert.assertTrue(actualMessage.contains("You logged into a secure area!"),
                "Expected success message not found!");
    }

    @Test(priority = 2)
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginInvalidUser("invalidUser", "wrongPass");

        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Your username is invalid!") ||
                          errorMsg.contains("Your password is invalid!"),
                "Expected error message not displayed!");
    }
}