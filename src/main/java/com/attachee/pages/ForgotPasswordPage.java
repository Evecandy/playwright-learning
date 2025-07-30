//package com.attachee.pages;
//
//import com.microsoft.playwright.Locator;
//import com.microsoft.playwright.Page;
//import com.microsoft.playwright.options.WaitForSelectorState;
//
//public class ForgotPasswordPage {
//    private final Page page;
//
//    // Locators for Login Page
//    private final Locator forgotPasswordLink;
//
//    // Locators for Forgot Password Page
//    private final Locator forgotPasswordTitle;
//    private final Locator usernameInput;
//    private final Locator resetPasswordButton;
//    private final Locator cancelButton;
//    private final Locator successMessage;
//    private final Locator errorMessage;
//    private final Locator backToLoginLink;
//
//    public ForgotPasswordPage(Page page) {
//        this.page = page;
//
//        // Login page elements
//        this.forgotPasswordLink = page.locator(".orangehrm-login-forgot-header");
//
//        // Forgot password page elements
//        this.forgotPasswordTitle = page.locator("h6.orangehrm-forgot-password-title");
//        this.usernameInput = page.locator("input[name='username']");
//        this.resetPasswordButton = page.locator("button[type='submit']");
//        this.cancelButton = page.locator(".oxd-button--ghost");
//        this.successMessage = page.locator(".orangehrm-forgot-password-wrapper p");
//        this.errorMessage = page.locator(".oxd-alert-content-text");
//        this.backToLoginLink = page.locator("text=Back To Login");
//    }
//
//    // Navigation Methods
//    public void navigateToLoginPage() {
//        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        page.waitForSelector("input[name='username']", new Page.WaitForSelectorOptions().setTimeout(10000));
//    }
//
//    public void clickForgotPasswordLink() {
//        forgotPasswordLink.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        forgotPasswordLink.click();
//
//        // Wait for forgot password page to load
//        forgotPasswordTitle.waitFor(new Locator.WaitForOptions().setTimeout(10000));
//    }
//
//    public void enterUsername(String username) {
//        usernameInput.waitFor();
//        usernameInput.fill(username);
//    }
//
//    public void clickResetPasswordButton() {
//        resetPasswordButton.click();
//        // Wait a moment for response
//        page.waitForTimeout(3000);
//    }
//
//    public void clickCancelButton() {
//        cancelButton.click();
//    }
//
//    public void clickBackToLogin() {
//        if (backToLoginLink.isVisible()) {
//            backToLoginLink.click();
//        }
//    }
//
//    // Verification Methods
//    public boolean isForgotPasswordLinkVisible() {
//        return forgotPasswordLink.isVisible();
//    }
//
//    public boolean isForgotPasswordPageDisplayed() {
//        try {
//            return forgotPasswordTitle.isVisible();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String getForgotPasswordTitle() {
//        if (isForgotPasswordPageDisplayed()) {
//            return forgotPasswordTitle.textContent();
//        }
//        return "";
//    }
//
//    public boolean isUsernameFieldVisible() {
//        return usernameInput.isVisible();
//    }
//
//    public boolean isResetButtonVisible() {
//        return resetPasswordButton.isVisible();
//    }
//
//    public boolean isCancelButtonVisible() {
//        return cancelButton.isVisible();
//    }
//
//    public boolean isSuccessMessageDisplayed() {
//        try {
//            return successMessage.isVisible();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String getSuccessMessage() {
//        if (isSuccessMessageDisplayed()) {
//            return successMessage.textContent();
//        }
//        return "";
//    }
//
//    public boolean isErrorMessageDisplayed() {
//        try {
//            return errorMessage.isVisible();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String getErrorMessage() {
//        if (isErrorMessageDisplayed()) {
//            return errorMessage.textContent();
//        }
//        return "";
//    }
//
//    public boolean isBackToLoginLinkVisible() {
//        return backToLoginLink.isVisible();
//    }
//
//    public String getCurrentUrl() {
//        return page.url();
//    }
//
//    // Complete flow methods
//    public void resetPasswordFlow(String username) {
//        navigateToLoginPage();
//        clickForgotPasswordLink();
//        enterUsername(username);
//        clickResetPasswordButton();
//    }
//
//    // Debug method
//    public void debugPageElements() {
//        System.out.println("🔍 Debugging Forgot Password Page Elements:");
//        System.out.println("  - Forgot Password Link visible: " + isForgotPasswordLinkVisible());
//        System.out.println("  - Forgot Password Page displayed: " + isForgotPasswordPageDisplayed());
//        System.out.println("  - Username field visible: " + isUsernameFieldVisible());
//        System.out.println("  - Reset button visible: " + isResetButtonVisible());
//        System.out.println("  - Cancel button visible: " + isCancelButtonVisible());
//        System.out.println("  - Current URL: " + getCurrentUrl());
//    }
//}