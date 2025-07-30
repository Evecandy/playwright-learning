package com.attachee.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AccessMaintainersPages {
    private final Page page;
    private final Locator adminSideButton;
    private final Locator leaveSideButton;
    private final Locator recruitmentSideButton;
    private final Locator performanceSideButton;

    public AccessMaintainersPages(Page page) {
        this.page = page;
        this.adminSideButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Admin"));
        this.leaveSideButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Leave"));
        this.recruitmentSideButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Recruitment"));
        this.performanceSideButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Performance"));
    }

    // Add this method to wait for side menu to load
    public void waitForSideMenu() {
        // Wait for the side panel to be visible
        page.waitForSelector(".oxd-sidepanel", new Page.WaitForSelectorOptions().setTimeout(15000));
        page.waitForTimeout(2000); // Small additional wait
    }

    public void goToLeavePage() {
        waitForSideMenu(); // Add this line
        leaveSideButton.click();
    }

    public void goToRecruitmentPage() {
        waitForSideMenu(); // Add this line
        recruitmentSideButton.click();
    }

    public void goToPerformancePage() {
        waitForSideMenu(); // Add this line
        performanceSideButton.click();
    }

    // Add admin method since you'll need it
    public void goToAdminPage() {
        waitForSideMenu();
        adminSideButton.click();
    }
}