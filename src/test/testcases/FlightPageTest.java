package test.testcases;

import main.java.pageobjects.FlightPage;
import org.junit.Assert;
import org.junit.Test;
import test.utils.SetupDestroyWebDriver;

public class FlightPageTest extends SetupDestroyWebDriver {
    /**
     * Shows available hotels for next day
     */
    @Test
    public void nextDayHotel() {
        setupApplication();
        driver.get("http://www.phptravels.net");
        FlightPage flightPage = new FlightPage(driver);
        flightPage.openFlightPage();
        flightPage.selectTripType();
        flightPage.selectNumPeople(3, 1);
        flightPage.checkFlights();
        flightPage.submit();
        flightPage.validateSearchResult();
    }
}
