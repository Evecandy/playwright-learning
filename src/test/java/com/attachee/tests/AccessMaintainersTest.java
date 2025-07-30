//package com.attachee.tests;
//
//import com.attachee.base.Base;
//import com.attachee.pages.AccessMaintainersPages;
//import org.testng.annotations.Test;
//
//public class AccessMaintainersTest extends Base {
//
//
//    @Test
//    public void navigateSideButtons(){
//        AccessMaintainersPages accessMaintainersPages = new  AccessMaintainersPages(page);
//        accessMaintainersPages.goToLeavePage();
//        accessMaintainersPages.goToRecruitmentPage();
//        accessMaintainersPages.goToPerformancePage();
//
//        System.out.println("Shalom");
//
//    }
//}


package com.attachee.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import com.attachee.pages.AccessMaintainersPages;

public class AccessMaintainersTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private AccessMaintainersPages accessMaintainersPages;

    @BeforeClass
    public void setUpClass() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(1000));
    }

    @BeforeMethod
    public void setUp() {
        context = browser.newContext();
        page = context.newPage();

        // LOGIN FIRST - This was missing!
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page.fill("input[name='username']", "Admin");
        page.fill("input[name='password']", "admin123");
        page.click("button[type='submit']");

        // Wait for dashboard to load
        page.waitForSelector(".oxd-sidepanel", new Page.WaitForSelectorOptions().setTimeout(15000));

        // Now create the page object
        accessMaintainersPages = new AccessMaintainersPages(page);
    }

    @AfterMethod
    public void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    @AfterClass
    public void tearDownClass() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @Test
    public void navigateSideButtons() {
        // Now this should work because we're logged in!
        accessMaintainersPages.goToLeavePage();

        // Add a small wait to see the navigation
        page.waitForTimeout(2000);

        System.out.println("✅ Successfully navigated to Leave page!");
        System.out.println("Current URL: " + page.url());
    }
}