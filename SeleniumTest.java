# JawwySubValidTest
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

public class SubscriptionTest {

    public static void main(String[] args) {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Navigate to URL
        driver.get("https://subscribe.jawwy.tv/ae-en");

        // Equivalence partitioning - define input values
        String[] types = {"Lite", "Classic", "Premium"};
        String[] countries = {"SA", "Kuwait", "Bahrain"};
        String[] currency = {"SAR", "KWD", "BHD"};

        // Loop through all combinations of input values
        for (String type : types) {
            for (String country : countries) {
                // Select subscription type
                WebElement typeDropdown = driver.findElement(By.xpath("//div[@class='dropdown__control--value' and contains(text(), 'Select Subscription Type')]"));
                typeDropdown.click();
                WebElement typeOption = driver.findElement(By.xpath("//div[@class='dropdown__option' and contains(text(), '" + type + "')]"));
                typeOption.click();

                // Select country
                WebElement countryDropdown = driver.findElement(By.xpath("//div[@class='dropdown__control--value' and contains(text(), 'Select Country')]"));
                countryDropdown.click();
                WebElement countryOption = driver.findElement(By.xpath("//div[@class='dropdown__option' and contains(text(), '" + country + "')]"));
                countryOption.click();

                // Verify subscription details
                WebElement priceElement = driver.findElement(By.xpath("//div[@class='subscription__price']"));
                String priceText = priceElement.getText();
                // Parse price and currency from text
                String[] priceParts = priceText.split(" ");
                String currency = priceParts[0];
                double price = Double.parseDouble(priceParts[1]);

                // Validate price and currency
                switch (type) {
                    case "Lite":
                        switch (country) {
                            case "SA":
                                assert currency.equals("SAR");
                                assert price == 25.0;
                                break;
                            case "Kuwait":
                                assert currency.equals("KWD");
                                assert price == 2.5;
                                break;
                            case "Bahrain":
                                assert currency.equals("BHD");
                                assert price == 2.5;
                                break;
                        }
                        break;
                    case "Classic":
                        switch (country) {
                            case "SA":
                                assert currency.equals("SAR");
                                assert price == 50.0;
                                break;
                            case "Kuwait":
                                assert currency.equals("KWD");
                                assert price == 5.0;
                                break;
                            case "Bahrain":
                                assert currency.equals("BHD");
                                assert price == 5.0;
                                break;
                        }
                        break;
                    case "Premium":
                        switch (country) {
                            case "SA":
                                assert currency.equals("SAR");
                                assert price == 100.0;
                                break;
                            case "Kuwait":
                                assert currency.equals("KWD");
                                assert price == 10.0;
                                break;
                            case "Bahrain":
                                assert currency.equals("BHD");
                                assert price == 10.0;
                                break;
                        }
                        break;
                }
            }
        }

        // Close WebDriver
        driver.quit();
    }
}
