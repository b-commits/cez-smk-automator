package tools;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static tools.ConstantProvider.*;
import static tools.XPathProvider.*;

/**
 * Automizes SMK webpage navigation and authentication and handles data entry
 * with parsed data dump.
 */

//todo Refactor XPaths to WebElements.
public final class SMKAutomator {

    private final String login;
    private final String password;
    private static final WebDriver driver = new FirefoxDriver();
    private static final WebDriverWait waiter = new WebDriverWait(driver, TIMEOUT);

    public SMKAutomator(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void run() {
        loginSMK();
        navigateNonRTGForm();
        fillForm();
    }

    private void loginSMK() {
        System.setProperty("webdriver.gecko.driver", "/geckodriver.exe");
        driver.manage().window().maximize();
        driver.get(SMK_LOGIN_URL);
        driver.findElement(By.name(USERNAME_HTML_NAME)).sendKeys(login);
        driver.findElement(By.name(PASSWORD_HTML_NAME)).sendKeys(password);
        driver.findElement(By.name(LOGIN_HTML_NAME)).sendKeys(Keys.ENTER);
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("23")));
    }

    private void navigateNonRTGForm() {
        driver.findElement(By.xpath("(//button[@type='button'][contains(.,'Wybierz')])[3]")).sendKeys(Keys.ENTER);
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='M0 B2'][contains(.,'\uE60E')]")));
        driver.findElement(By.id("1001")).sendKeys(Keys.ENTER);
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("gwt-uid-154")));
        driver.findElement(By.id("gwt-uid-154")).click();
        driver.findElement(By.id("1501")).click();
        hold(1000);
        driver.findElement(By.id("509")).click();
        hold(5000);
        driver.findElement(By.xpath("(//button[contains(@type,'button')])[24]")).click();
        hold(250);
        driver.findElement(By.xpath("(//button[contains(@type,'button')])[25]")).click();
        hold(250);
        driver.findElement(By.xpath("(//button[contains(@type,'button')])[36]")).click();
        hold(250);
        driver.findElement(By.xpath(ADD_XPATH)).click();
        hold(250);
    }

    private void fillForm() {
        XMLReader.tokenizeDataDump();
        XMLReader.serializeValidData();
        XMLReader.getNonRTG().forEach(procedure -> {
            driver.findElement(By.xpath(DATE_XPATH)).sendKeys(procedure.getDate().toString());
            driver.findElement(By.xpath(YEAR_XPATH)).sendKeys(DEFAULT_YEAR);
            driver.findElement(By.xpath(OPERATOR_XPATH)).sendKeys(DEFAULT_OPERATOR);
            driver.findElement(By.xpath(DOC_XPATH)).sendKeys(DEFAULT_DOC);
            driver.findElement(By.xpath(LOC_XPATH)).sendKeys(DEFAULT_LOC);
            driver.findElement(By.xpath(INTERN_XPATH)).sendKeys(Keys.PAGE_DOWN);
            driver.findElement(By.xpath(INITIALS_XPATH)).sendKeys(procedure.getPatientInitials());
            driver.findElement(By.xpath(SEX_XPATH)).sendKeys(procedure.getSex());
            driver.findElement(By.xpath(PROCEDURE_XPATH)).sendKeys(procedure.getProcedureName());
            driver.findElement(By.xpath(ADD_XPATH)).click();
        });
    }

    private static void hold(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
