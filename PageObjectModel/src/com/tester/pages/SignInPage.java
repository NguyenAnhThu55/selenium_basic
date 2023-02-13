package com.tester.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignInPage {

	private WebDriver driver;

	private By headerPageText = By.xpath("//a[normalize-space()='Forgot Username/Password?']");
	private By emailInput = By.id("EmailInputEmail");
	private By passwordInput = By.id("PasswordInputPassword");
	private By signinBtn = By.id("SignInButton");
	private By errorMsgText = By.id("signInError");

	private By pinInput = By.id("Pin");
	private By submitBtn = By.id("RequestPinForm_SubmitButton");
	private By backBtn = By.id("RequestPinForm_Back");
	private By resetPintBtn = By.id("RequestPinForm_ResetPin");

	// Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong
	// class này đọc
	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getSignInPageTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}

	public boolean verifySignInPageTitle() {
		String expectedTitle = "Sign In";
		return getSignInPageTitle().equals(expectedTitle);
	}

	public boolean verifySignInPageText() {
		WebElement element = driver.findElement(headerPageText);
		String pageText = element.getText();
		String expectedPageText = "Forgot Username/Password?";
		return pageText.contains(expectedPageText);
	}

	// Sau khi thực hiện click Submit thì khởi tạo trang DashboardPage
	public void signin(String username, String password, String Pin) throws Exception {
		enterEmail(username);
		enterPassword(password);
		clickSignIn();
		Thread.sleep(1000);
		enterPin(Pin);
		clickSubmit();
	}

	public boolean verifySignIn() {
		enterEmail("test");
		enterPassword("pass");
		clickSignIn();
		return getErrorMessage().contains("incorrect");
	}

	public void enterEmail(String email) {
		WebElement emailTxtBox = driver.findElement(emailInput);
		if (emailTxtBox.isDisplayed())
			emailTxtBox.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebElement passwordTxtBox = driver.findElement(passwordInput);
		if (passwordTxtBox.isDisplayed())
			passwordTxtBox.sendKeys(password);
	}

	public void clickSignIn() {
		WebElement signin = driver.findElement(signinBtn);
		if (signin.isDisplayed()) {
			signin.click();
		}
	}

	public void clickSubmit() {
		WebElement submit = driver.findElement(submitBtn);
		if (submit.isDisplayed()) {
			submit.click();
		}
	}

	public void enterPin(String PIN) {
		driver.findElement(pinInput).sendKeys(PIN);
	}

	public void clickBack() {
		driver.findElement(backBtn).click();
	}

	public void clickResetPin() {
		driver.findElement(resetPintBtn).click();
	}

	public String getErrorMessage() {
		String strErrorMsg = null;
		WebElement errorMsg = driver.findElement(errorMsgText);
		if (errorMsg.isDisplayed() && errorMsg.isEnabled())
			strErrorMsg = errorMsg.getText();
		return strErrorMsg;
	}

	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}
}
