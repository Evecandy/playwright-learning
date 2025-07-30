//package com.attachee.tests;
//
//import com.microsoft.playwright.*;
//import org.testng.Assert;
//import org.testng.annotations.*;
//import com.attachee.pages.ForgotPasswordPage;
//
//public class ForgotPasswordTests {
//    private Playwright playwright;
//    private Browser browser;
//    private BrowserContext context;
//    private Page page;
//    private ForgotPasswordPage forgotPasswordPage;
//
//    @BeforeClass
//    public void setUpClass() {
//        playwright = Playwright.create();
//        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
//                .setHeadless(false)
//                .setSlowMo(1000)); // Slow down for better observation
//    }
//
//    @BeforeMethod
//    public void setUp() {
//        context = browser.newContext();
//        page = context.newPage();
//        forgotPasswordPage = new ForgotPasswordPage(page);
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        if (context != null) {
//            context.close();
//        }
//    }
//
//    @AfterClass
//    public void tearDownClass() {
//        if (browser != null) {
//            browser.close();
//        }
//        if (playwright != null) {
//            playwright.close();
//        }
//    }
//
//    @Test(priority = 1, description = "Verify Forgot Password link is visible on login page")
//    public void testForgotPasswordLinkVisibility() {
//        System.out.println("🧪 Testing Forgot Password link visibility...");
//
//        forgotPasswordPage.navigateToLoginPage();
//
//        Assert.assertTrue(forgotPasswordPage.isForgotPasswordLinkVisible(),
//                "Forgot Password link should be visible on login page");
//
//        System.out.println("✅ Forgot Password link is visible");
//    }
//
//    @Test(priority = 2, description = "Test navigation to Forgot Password page")
//    public void testNavigationToForgotPasswordPage() {
//        System.out.println("🧪 Testing navigation to Forgot Password page...");
//
//        forgotPasswordPage.navigateToLoginPage();
//        forgotPasswordPage.clickForgotPasswordLink();
//
//        Assert.assertTrue(forgotPasswordPage.isForgotPasswordPageDisplayed(),
//                "Should navigate to Forgot Password page");
//
//        String expectedTitle = "Reset Password";
//        String actualTitle = forgotPasswordPage.getForgotPasswordTitle();
//        Assert.assertTrue(actualTitle.contains("Reset") || actualTitle.contains("Forgot"),
//                "Page title should contain 'Reset' or 'Forgot'. Actual: " + actualTitle);
//
//        Assert.assertTrue(forgotPasswordPage.getCurrentUrl().contains("requestPasswordResetCode"),
//                "URL should contain forgot password path");
//
//        System.out.println("✅ Successfully navigated to Forgot Password page");
//        System.out.println("   Page Title: " + actualTitle);
//        System.out.println("   URL: " + forgotPasswordPage.getCurrentUrl());
//    }
//
//    @Test(priority = 3, description = "Verify Forgot Password page elements")
//    public void testForgotPasswordPageElements() {
//        System.out.println("🧪 Testing Forgot Password page elements...");
//
//        forgotPasswordPage.navigateToLoginPage();
//        forgotPasswordPage.clickForgotPasswordLink();
//
//        // Check all required elements are present
//        Assert.assertTrue(forgotPasswordPage.isUsernameFieldVisible(),
//                "Username field should be visible");
//        Assert.assertTrue(forgotPasswordPage.isResetButtonVisible(),
//                "Reset Password button should be visible");
//        Assert.assertTrue(forgotPasswordPage.isCancelButtonVisible(),
//                "Cancel button should be visible");
//
//        System.out.println("✅ All required elements are present on Forgot Password page");
//    }
//
//    @Test(priority = 4, description = "Test password reset with valid username")
//    public void testPasswordResetWithValidUsername() {
//        System.out.println("🧪 Testing password reset with valid username...");
//
//        forgotPasswordPage.resetPasswordFlow("Admin");
//
//        // Check for success message or confirmation
//        // Note: The actual behavior may vary - sometimes shows success, sometimes error
//        boolean hasSuccessMessage = forgotPasswordPage.isSuccessMessageDisplayed();
//        boolean hasErrorMessage = forgotPasswordPage.isErrorMessageDisplayed();
//
//        if (hasSuccessMessage) {
//            System.out.println("✅ Success message displayed: " + forgotPasswordPage.getSuccessMessage());
//        } else if (hasErrorMessage) {
//            System.out.println("ℹ️ System response: " + forgotPasswordPage.getErrorMessage());
//        } else {
//            System.out.println("ℹ️ No explicit message displayed - this may be expected behavior");
//        }
//
//        // At minimum, we should still be on the forgot password page or redirected appropriately
//        String currentUrl = forgotPasswordPage.getCurrentUrl();
//        Assert.assertTrue(currentUrl.contains("requestPasswordResetCode") ||
//                        currentUrl.contains("sendPasswordReset") ||
//                        currentUrl.contains("auth"),
//                "Should remain on forgot password flow or redirect appropriately");
//
//        System.out.println("✅ Password reset flow completed for valid username");
//    }
//
//    @Test(priority = 5, description = "Test password reset with invalid username")
//    public void testPasswordResetWithInvalidUsername() {
//        System.out.println("🧪 Testing password reset with invalid username...");
//
//        forgotPasswordPage.resetPasswordFlow("InvalidUserThatDoesNotExist");
//
//        // The system might show an error or handle it gracefully
//        // Let's check what actually happens
//        boolean hasErrorMessage = forgotPasswordPage.isErrorMessageDisplayed();
//        boolean hasSuccessMessage = forgotPasswordPage.isSuccessMessageDisplayed();
//
//        if (hasErrorMessage) {
//            System.out.println("✅ Error message displayed: " + forgotPasswordPage.getErrorMessage());
//        } else if (hasSuccessMessage) {
//            System.out.println("ℹ️ Success message shown (system may not reveal invalid usernames): " +
//                    forgotPasswordPage.getSuccessMessage());
//        } else {
//            System.out.println("ℹ️ No explicit message - system may handle this silently");
//        }
//
//        System.out.println("✅ Password reset flow completed for invalid username");
//    }
//
//    @Test(priority = 6, description = "Test password reset with empty username")
//    public void testPasswordResetWithEmptyUsername() {
//        System.out.println("🧪 Testing password reset with empty username...");
//
//        forgotPasswordPage.navigateToLoginPage();
//        forgotPasswordPage.clickForgotPasswordLink();
//
//        // Don't enter any username, just click reset
//        forgotPasswordPage.clickResetPasswordButton();
//
//        // Should show validation error or stay on same page
//        Assert.assertTrue(forgotPasswordPage.isForgotPasswordPageDisplayed(),
//                "Should remain on forgot password page when username is empty");
//
//        System.out.println("✅ Handled empty username appropriately");
//    }
//
//    @Test(priority = 7, description = "Test Cancel button functionality")
//    public void testCancelButtonFunctionality() {
//        System.out.println("🧪 Testing Cancel button functionality...");
//
//        forgotPasswordPage.navigateToLoginPage();
//        forgotPasswordPage.clickForgotPasswordLink();
//
//        Assert.assertTrue(forgotPasswordPage.isForgotPasswordPageDisplayed(),
//                "Should be on forgot password page");
//
//        forgotPasswordPage.clickCancelButton();
//
//        // Should return to login page
//        page.waitForTimeout(2000); // Wait for navigation
//
//        String currentUrl = forgotPasswordPage.getCurrentUrl();
//        Assert.assertTrue(currentUrl.contains("login") || currentUrl.contains("auth"),
//                "Should return to login page after clicking Cancel. Current URL: " + currentUrl);
//
//        System.out.println("✅ Cancel button successfully returns to login page");
//    }
//
//    @Test(priority = 8, description = "Test complete forgot password user journey")
//    public void testCompleteForgotPasswordJourney() {
//        System.out.println("🧪 Testing complete forgot password user journey...");
//
//        // Step 1: Start from login page
//        forgotPasswordPage.navigateToLoginPage();
//        System.out.println("   Step 1: Navigated to login page ✅");
//
//        // Step 2: Click forgot password link
//        forgotPasswordPage.clickForgotPasswordLink();
//        Assert.assertTrue(forgotPasswordPage.isForgotPasswordPageDisplayed(),
//                "Should navigate to forgot password page");
//        System.out.println("   Step 2: Navigated to forgot password page ✅");
//
//        // Step 3: Enter username and submit
//        forgotPasswordPage.enterUsername("Admin");
//        forgotPasswordPage.clickResetPasswordButton();
//        System.out.println("   Step 3: Submitted password reset request ✅");
//
//        // Step 4: Handle the response (varies by system)
//        page.waitForTimeout(3000);
//
//        if (forgotPasswordPage.isBackToLoginLinkVisible()) {
//            forgotPasswordPage.clickBackToLogin();
//            System.out.println("   Step 4: Clicked back to login ✅");
//        }
//
//        System.out.println("✅ Complete forgot password journey tested successfully");
//    }
//
//    @Test(priority = 9, description = "Debug test to explore forgot password functionality")
//    public void debugForgotPasswordFunctionality() {
//        System.out.println("🔍 Debug test - exploring forgot password functionality...");
//
//        forgotPasswordPage.navigateToLoginPage();
//        forgotPasswordPage.debugPageElements();
//
//        System.out.println("\n--- Clicking Forgot Password Link ---");
//        forgotPasswordPage.clickForgotPasswordLink();
//        forgotPasswordPage.debugPageElements();
//
//        // Take screenshot for visual verification
//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(java.nio.file.Paths.get("forgot-password-page.png")));
//
//        System.out.println("📸 Screenshot saved as: forgot-password-page.png");
//        System.out.println("✅ Debug test completed");
//    }
//}