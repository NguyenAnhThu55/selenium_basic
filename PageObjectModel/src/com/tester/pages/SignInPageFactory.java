package com.tester.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignInPageFactory {

	private WebDriver driver;

	// Explicit Wait (chá»� Ä‘á»£i rÃµ rÃ ng cho tá»«ng láº§n tÃ¬m kiáº¿m Element)
	private WebDriverWait wait;

	@FindBy(xpath = "//a[normalize-space()='Forgot Username/Password?']")
	private WebElement headerPageText;
	@FindBy(id = "EmailInputEmail")
	private WebElement emailInput;
	@FindBy(id = "PasswordInputPassword")
	private WebElement passwordInput;
	@FindBy(id = "SignInButton")
	private WebElement signinBtn;
	@FindBy(id = "signInError")
	private WebElement errorMsgText;
	@FindBy(id = "Pin")
	private WebElement pinInput;
	@FindBy(id = "RequestPinForm_SubmitButton")
	private WebElement submitBtn;
	@FindBy(id = "RequestPinForm_Back")
	private WebElement backBtn;
	@FindBy(id = "RequestPinForm_ResetPin")
	private WebElement resetPintBtn;

	// Khá»Ÿi táº¡o class khi Ä‘Æ°á»£c gá»�i vá»›i driver
	// VÃ  khá»Ÿi táº¡o initElements tá»« Page Factory
	public SignInPageFactory(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(this.driver, this);
	}

	// ChÃºng ta viáº¿t hÃ m signin khÃ´ng cáº§n dÃ¹ng cÃ¡c hÃ m bá»• trá»£ enter
	// hay click ná»¯a
	public DashboardPage signin(String username, String password, String Pin) throws Exception {
		setText(emailInput, username); // emailInput.sendKeys(username);
		setText(passwordInput, password); // passwordInput.sendKeys(password);
		clickElement(signinBtn); // signinBtn.click();
		setText(pinInput, Pin); // pinInput.sendKeys(Pin);
		clickElement(submitBtn); // submitBtn.click();
		waitForPageLoaded();
		return new DashboardPage(driver);
	}

	public boolean verifySignInPageTitle() {
		String expectedTitle = "Sign In";
		return driver.getTitle().equals(expectedTitle);
	}

	public boolean verifySignInPageText() {
		String pageText = headerPageText.getText();
		String expectedPageText = "Forgot Username/Password?";
		return pageText.contains(expectedPageText);
	}

	// HÃ´m nay ráº£nh quÃ¡ viáº¿t dc 2 hÃ m chung: sendKeys chung vÃ  click chung
	public void setText(WebElement element, String valueText) {
		// wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.sendKeys(valueText);
	}

	public void clickElement(WebElement element) {
		// wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
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
