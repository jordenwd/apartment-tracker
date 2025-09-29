package org.jwd.apartmenttracker;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jwd.apartmenttracker.entities.Apartment;
import org.jwd.apartmenttracker.scraper.ApartmentsComScraperService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ApartmentTrackerApplication.class, ApartmentsComScraperService.class})
public class ApartmentsComScraperTests {
    @Autowired
    ApartmentsComScraperService scraper;
    private Apartment apt;
    WebDriver driver;

    @BeforeEach
    public void setup(){
        //Initialize new WebDriver so that mockito doesn't interfere with selenium
        driver = new ChromeDriver();
        scraper.setDriver(driver);
        //if all tests start failing check url, if the listing is removed find a new one
        apt = scraper.scrapeListing("https://www.apartments.com/alta-stone-at-the-gateway-salt-lake-city-ut/lrm5yyd/");
    }

    @AfterEach
    public void teardown() {
        // Quit WebDriver after each test
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void ScraperGetsNameCorrectlyGetsName()
    {
        assertEquals("Alta Stone at The Gateway", apt.getName());
    }

    @Test
    public void ScraperGetsStreetAddressCorrectly(){
        assertEquals("565 W 100 S", apt.getStreetAddress());
    }

    @Test
    public void ScraperGetsCityCorrectly(){
        assertEquals("Salt Lake City", apt.getCity());
    }

    @Test
    public void ScraperGetsStateCorrectly(){
        assertEquals("UT", apt.getState());
    }

    @Test
    public void ScraperGetsZipCodeCorrectly(){
        assertEquals(84101, apt.getZipCode());
    }

    @Test
    public void ScraperGetsFloorplansCorrectly(){
        assertNotNull(apt.getFloorplans());
        assertTrue(apt.getFloorplans().size() > 0);
    }

}
