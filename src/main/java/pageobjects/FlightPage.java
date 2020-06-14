package main.java.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FlightPage extends PageObject{

    public FlightPage(WebDriver driver) {
        super(driver);
    }
    JavascriptExecutor js = (JavascriptExecutor)driver;
    WebDriverWait wait = new WebDriverWait(driver, 10, 500);

    public void openFlightPage()
    {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@data-title='DOHOP']")));

        // Find flights tab
        WebElement flightsTab = driver.findElement(By.xpath(".//*[@data-title='DOHOP']"));

        // click flights tab
        flightsTab.click();
    }

    public void selectTripType()
    {

        WebElement trip = driver.findElement(By.xpath("//select[@id='trip']"));

        js.executeScript("arguments[0].click();", trip);

        // Find element of trip menu
        WebElement roundTrip = driver.findElement(By.xpath("//select[@id='trip']/option[contains(., 'Round Trip')]"));

        // Click roundTrip
        js.executeScript("arguments[0].click();", roundTrip);

    }
    /**
     * Used to select the check in date for hotel. Will go to next month and click on second week on Friday
     */
    public void checkFlights() {

        // Find "fly from" text field
        WebElement flyFrom = driver.findElement(By.id("a1"));

        // Send the name of the city of departure
        flyFrom.sendKeys("Bergen");

        // Wait and click element with "Bergen (BGO)"
        try{
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//li[contains(.,'Bergen (BGO)')]"))));
        }
        catch(Exception e){
        }


        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[contains(.,'Bergen (BGO)')]")));

        // Find "Arrive at" text field
        WebElement arriveAt = driver.findElement(By.id("a2"));

        // Send the name of the city of arrival
        arriveAt.sendKeys("Rabat");

        // Wait and click element with "Rabat (RBA)"
        try{
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//li[contains(.,'Rabat (RBA)')]"))));
        }
        catch(Exception e){
        }
        // Find depart field
        WebElement departF = driver.findElement(By.xpath("//input[@name='d1']"));

        // Send date
        departF.sendKeys("10/01/2020");

    }

    /**
     * Selects the number of adults and children going on trip
     * @param adults the number of adults going on trip
     * @param children the number of children going on trip
     */
    public void selectNumPeople(int adults, int children) {
        Select selectAdults = new Select(driver.findElement(By.id("adults")));
        selectAdults.selectByIndex(adults-1); // -1 because first value in list is 1

        Select selectChildren = new Select(driver.findElement(By.id("child")));
        selectChildren.selectByIndex(children);
    }

    public void submit() {
        WebElement searchB = driver.findElement(By.xpath(".//*[@id='DOHOP']//button"));
        // Click search button
        js.executeScript("arguments[0].click();", searchB);
    }
    public void validateSearchResult()
    {
        // Find search button
        WebElement searchB = driver.findElement(By.xpath(".//*[@id='DOHOP']//button"));

        // Click search button
        js.executeScript("arguments[0].click();", searchB);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        // Wait until, element with search results appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='destination']")));

        // Verify if search result correct
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-reactid='.0.1.0.0.0.0']")).getText().contains("Bergen") && driver.findElement(By.xpath("//div[@data-reactid='.0.1.0.0.0.2']")).getText().contains("Rabat"));

    }
}
