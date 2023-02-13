package com.tester.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tester.base.BaseSetup;
import com.tester.pages.DashboardPage;
import com.tester.pages.SignInPage;
import com.tester.pages.DashboardPage;

public class DashboardTest extends BaseSetup{
	private WebDriver driver;
	public SignInPage signInPage;
	public DashboardPage dashboardPage;

	@BeforeClass
	public void setUp() {
		// Đã khởi tạo browser hết rồi kể cả wait, phóng to màn hình,...
		driver = getDriver();
	}
	
	//SignIn mặc định
	@Test(priority = 1)
	public void signIn() throws Exception {
		System.out.println(driver);
		signInPage = new SignInPage(driver);

		Assert.assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
		Assert.assertTrue(signInPage.verifySignInPageText(), "Header page text not matching");
		
		signInPage.signin("thaian@mailinator.com", "Demo@123", "123456");

	}

	//Phần xử lý trang Dashboard
	@Test(priority = 2)
	public void openDocument() throws Exception {
		
		dashboardPage = new DashboardPage(driver);
		
		Assert.assertTrue(dashboardPage.verifySignInPageTitle(), "Dashboard page title doesn't match");
		dashboardPage.openDocumentManagement();

	}
}
