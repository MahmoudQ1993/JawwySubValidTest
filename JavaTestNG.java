import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SubscriptionPackagesTest {
    
    private WebDriver driver;
    
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://subscribe.jawwy.tv/ae-en");
    }
    
    @Test
    public void testSubscriptionPackages() {
        // Verify that the page loads and the subscription packages are displayed
        WebElement subscriptionPackages = driver.findElement(By.className("subscription-packages"));
        Assert.assertTrue(subscriptionPackages.isDisplayed());
        
        // Define the list of countries to test
        String[] countries = {"Saudi Arabia (SA)", "Kuwait", "Bahrain"};
        
        // Define the list of package types to test
        String[] packageTypes = {"LITE", "CLASSIC", "PREMIUM"};
        
        // Loop through each country and package type, and verify the package details
        for (String country : countries) {
            // Select the country from the dropdown
            WebElement countryDropdown = driver.findElement(By.className("country-dropdown"));
            countryDropdown.click();
            WebElement countryOption = driver.findElement(By.xpath("//li[contains(text(), '"+country+"')]"));
            countryOption.click();
            
            // Loop through each package type and verify the details
            for (String packageType : packageTypes) {
                // Click on the package tab
                WebElement packageTab = driver.findElement(By.xpath("//a[contains(text(), '"+packageType+"')]"));
                packageTab.click();
                
                // Verify the package details for the selected country and package type
                WebElement packageName = driver.findElement(By.xpath("//div[@class='subscription-packages']//h3[contains(text(), '"+packageType+"')]"));
                WebElement packagePrice = driver.findElement(By.xpath("//div[@class='subscription-packages']//h3[contains(text(), '"+packageType+"')]//following-sibling::div[@class='price']"));
                Assert.assertTrue(packageName.getText().contains(country));
                Assert.assertTrue(packagePrice.getText().contains(getExpectedPriceForCountryAndPackageType(country, packageType)));
            }
        }
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
    private String getExpectedPriceForCountryAndPackageType(String country, String packageType)
