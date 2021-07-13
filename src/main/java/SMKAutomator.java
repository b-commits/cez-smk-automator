import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Automizes SMK webpage navigation and authentication and handles data entry
 * with parsed data dump.
 */
public class SMKAutomator {

    private static final String LOGIN = "luq1792@gmail.com";
    private static final String PASSWORD = "Lespaul9242/";
    private static WebDriver driver = new FirefoxDriver();
    private static WebDriverWait waiter = new WebDriverWait(driver, 20);


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "/geckodriver.exe");


        driver.manage().window().maximize();
        driver.get("https://eploz.ezdrowie.gov.pl/auth/realms/EPLOZ/protocol/saml/clients/smk?SAMLRequest=PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48c2FtbDJwOkF1dGhuUmVxdWVzdCB4bWxuczpzYW1sMnA9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpwcm90b2NvbCIgQXNzZXJ0aW9uQ29uc3VtZXJTZXJ2aWNlVVJMPSJodHRwczovL3Ntay5lemRyb3dpZS5nb3YucGwvc2FtbC9Bc3NlcnRpb25Db25zdW1lclNlcnZpY2UiIERlc3RpbmF0aW9uPSJodHRwczovL2VwbG96LmV6ZHJvd2llLmdvdi5wbC9hdXRoL3JlYWxtcy9FUExPWi9wcm90b2NvbC9zYW1sL2NsaWVudHMvc21rIiBJRD0iXzcyNmQzZWFkOTc4ZGRiMjIyNjNkOTRiZWMxMjQ5MDU5IiBJc3N1ZUluc3RhbnQ9IjIwMjEtMDctMTFUMTM6MzM6MjkuMTczWiIgUHJvdG9jb2xCaW5kaW5nPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YmluZGluZ3M6SFRUUC1QT1NUIiBWZXJzaW9uPSIyLjAiPjxzYW1sMjpJc3N1ZXIgeG1sbnM6c2FtbDI9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iPlNNSzwvc2FtbDI6SXNzdWVyPjxkczpTaWduYXR1cmUgeG1sbnM6ZHM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyMiPjxkczpTaWduZWRJbmZvPjxkczpDYW5vbmljYWxpemF0aW9uTWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIi8%2BPGRzOlNpZ25hdHVyZU1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNyc2Etc2hhMSIvPjxkczpSZWZlcmVuY2UgVVJJPSIjXzcyNmQzZWFkOTc4ZGRiMjIyNjNkOTRiZWMxMjQ5MDU5Ij48ZHM6VHJhbnNmb3Jtcz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI2VudmVsb3BlZC1zaWduYXR1cmUiLz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIi8%2BPC9kczpUcmFuc2Zvcm1zPjxkczpEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGVuYyNzaGEyNTYiLz48ZHM6RGlnZXN0VmFsdWU%2BNW9MWjluTFJNMHYzZ3JCMHpOTFZ2NGZIelg3WXlKZ05LL1ZpYStQZ1d1WT08L2RzOkRpZ2VzdFZhbHVlPjwvZHM6UmVmZXJlbmNlPjwvZHM6U2lnbmVkSW5mbz48ZHM6U2lnbmF0dXJlVmFsdWU%2Baks4UCtmSWVRV1JEYi8vaGlVaHFvRUN3bERzb0tTczZ0cktVNmlDMUszQVhBdFR1RjRNNUJkSW5WT1RWUnZkQ09odnd4OFlPRWVteUk0bEhNclUzN1pqSlBmQnpRQTdFNGIrcklXWmJPbjhseEtTMTdaMGx3SXZXQWNjN2tWNnBZbHRlaHF1ZlVaUXFHd3ZlSnkycG92bUFSK0Nqd1ZRQ0xEUloyRnMyUHZ1K2w2QVhmTU5ZZWY4b0pZbGFHZkpXbzR2L3RHL2cvVUZYUzJyT0lxVHZGbGpJQTF1VU84N0l1SzExQU1mZlFrZ21Zd09jWkxhL0dOcmh5eGVWYmdIL2JKZ0ZLaDMvNzA3bUQxN2JtbEFtVEY0M2FYZkNpeW1aYXozN3VxM1ZJbnU4OVpRSWNnc3M1TmNnT3Y1dGpET2VNMFNDZDJjNjF4Z3NYYzVHQjdoK0pRPT08L2RzOlNpZ25hdHVyZVZhbHVlPjxkczpLZXlJbmZvPjxkczpYNTA5RGF0YT48ZHM6WDUwOUNlcnRpZmljYXRlPk1JSUNsVENDQVgwQ0JnRnhxS2NhcmpBTkJna3Foa2lHOXcwQkFRc0ZBREFPTVF3d0NnWURWUVFEREFOVFRVc3dIaGNOTWpBd05ESXoKTWpBd05UVTFXaGNOTXpBd05ESXpNakF3TnpNMVdqQU9NUXd3Q2dZRFZRUUREQU5UVFVzd2dnRWlNQTBHQ1NxR1NJYjNEUUVCQVFVQQpBNElCRHdBd2dnRUtBb0lCQVFDTlVLdmFRYlJhMHNrRkU4Mm1tcmRXMEd5dmlyUmFjV2xhaWQzRnl6N21BYjFXc3RPMFRtcGp0Q2JrClNEZUw3Z2VmaE9KYUJQMkppeERpNjBkOStHYVVlbW1EWFJVcndJeHlQQ2EvdTNSRnFtUGxqV1ZHaGl4M3ZEeDlNQmR6dGdxS1VwdXUKTFNReC9IM0lvd3RYRFJoY3BZTS9jK1o2MS8raFlyc0VadUlmaUpWSGtMUmlETWJaSHE4OTEwRnJ0MWQ2bGtoemdxNXBYQXNRakVrWgpGeWNKaEMzMVo1Rmx4bVJUWEN3NWJUOUlxck1vc3VndFNsQjdOUHNrQlZIeE43SUltM1VlNXEvSGdTWFVTQmFhYzRRaDNLc1dtd2Z4CkVGMDZwaTFGVnpYZmY4Q0RrUGJYQmhnYURReU5RWkR0MDAwaU91S1BYd1ZxMG5Sam1xVnRlZGJUQWdNQkFBRXdEUVlKS29aSWh2Y04KQVFFTEJRQURnZ0VCQUgvY3JRRzlqUWxCUFh2WUVpQ00yNUpuekF3b1VjQzRjcG5URXorTW1EWjBHRG1xMklFd3ZKN1JiMDhoM0M5LwpCNktJNWxOdWVzZVl6dm1TSDRIZXgva2NPOEczL2Q4S2V4MGhFVFdtUGpoUFdlbmVGQjNPWlkxMGJUZ3pmRVovR0lWNmhON01zUHdhCkZVREovSllHVWFuaklzZzRJVjIyRGNyNmRnREd5NDUybVcrYkpsazZpRHc4U1lGaUxIV2RTZXhQMWxCUElkdm1mOS91RGRyV1Y4NUUKRUltdkJYVjhzYllIVHhpTmdCM1NqR2MzbFlNclM2Y1hKbXlCK1JWV2xzTTlEV3U3MnY4RDlRUVUrVVZJYVhMem95YlJ0TDZ2ejNlagptZ09qemxTQVBNWmxqWkEzQTh2enFzV2h2bTltejZyRXRxeVl2OCtFaWdHQldjdjE1cGc9PC9kczpYNTA5Q2VydGlmaWNhdGU%2BPC9kczpYNTA5RGF0YT48L2RzOktleUluZm8%2BPC9kczpTaWduYXR1cmU%2BPHNhbWwycDpOYW1lSURQb2xpY3kgQWxsb3dDcmVhdGU9InRydWUiIEZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6MS4xOm5hbWVpZC1mb3JtYXQ6dW5zcGVjaWZpZWQiLz48L3NhbWwycDpBdXRoblJlcXVlc3Q%2B");
        driver.findElement(By.name("username")).sendKeys(LOGIN);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("23")));
        driver.findElement(By.xpath("(//button[@type='button'][contains(.,'Wybierz')])[3]")).sendKeys(Keys.ENTER);
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='M0 B2'][contains(.,'\uE60E')]")));
        driver.findElement(By.id("1001")).sendKeys(Keys.ENTER);

        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("gwt-uid-154")));
        driver.findElement(By.id("gwt-uid-154")).click();
        driver.findElement(By.id("1501")).click();
        System.out.println("jestem");
        System.out.println("wdiczoyn");

        Thread.sleep(1500);
        driver.findElement(By.id("509")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//button[contains(@type,'button')])[24]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("(//button[contains(@type,'button')])[25]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("(//button[contains(@type,'button')])[36]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("(//button[contains(@type,'button')])[37]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("(//select[contains(@tabindex,'-1')])[1]")).sendKeys("2");
        driver.findElement(By.xpath("(//select[contains(@tabindex,'-1')])[2]")).sendKeys("A");

        driver.findElement(By.xpath("(//select[contains(@tabindex,'-1')])[3]")).sendKeys("Z");
        driver.findElement(By.xpath("(//select[contains(@tabindex,'-1')])[4]")).sendKeys("S");
        driver.findElement(By.xpath("(//input[contains(@type,'textArea')])[1]")).sendKeys("2021-05-07");
        driver.findElement(By.xpath("//input[contains(@size,'50')]")).sendKeys("?ukasz go?cicki");

        driver.findElement(By.xpath("//input[@size='5']")).sendKeys("KA");
        driver.findElement(By.xpath("//input[contains(@size,'2048')]")).sendKeys("GOPP");
        driver.findElement(By.xpath("//td[@class='GGB IGB JHB N3 PGB'][contains(.,'KM')]")).sendKeys("K");
        driver.findElement(By.xpath("//input[contains(@size,'50')]")).sendKeys("?ukasz go?cicki");




    }
}
