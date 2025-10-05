package org.jwd.apartmenttracker.scraper;

import org.jwd.apartmenttracker.entities.Apartment;
import org.jwd.apartmenttracker.entities.Floorplan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>A service class for scraping apartment data from Apartments.com utilizing the
 * Selenium WebDriver.</p>
 *
 * <p>Currently, the following div tags contain the necessary information: </p>
 * <ul>
 *     <li>Property Name: id="propertyName"</li>
 *     <li>All address elements: class="propertyAddressContainer"
     *     <ol>
     *         <li>Street address: class="delivery-address</li>
     *         <li>City: The span tag next to Street address, no identifiers</li>
     *         <li>State and Zip: class="stateZipContainer"</li>
     *     </ol>
 *     </li>
 *     <li>Floorplans: class="pricingGridItem"
 *          <ol>
 *              <li>Plan name: class="modelName"</li>
 *              <li>Rent: class="rentLabel"</li>
 *              <li>Bed, Bath, Square Feet: class="detailsTextWrapper"</li>
 *          </ol>
 *     </li>
 *     <li>Contact: class="propertyPhone"</li>
 * </ul>
 */
@Service
public class ApartmentsComScraperService {
    @Autowired
    private WebDriver driver;

    private String url;




    /**
     * Scrapes the apartment listing from the given URL and returns an Apartment object
     * @param url
     * @return An Apartment object containing the scraped data
     */
    public Apartment scrapeListing(String url){
        driver.get(url);
        //data for apartment
        String propertyName = driver.findElement(By.id("propertyName")).getText();

        //find the different address elements
        WebElement addressContainer = driver.findElement(By.className("propertyAddressContainer"));
        String streetAddress = addressContainer.findElement(By.className("delivery-address")).getText().replace(",", "");
        WebElement cityElement = addressContainer.findElement(By.xpath(".//h2/span[not(@class)]"));
        String city = cityElement.getText();
        WebElement stateAndZip = addressContainer.findElement(By.className("stateZipContainer"));
        String state = stateAndZip.findElements(By.tagName("span")).get(0).getText();
        String zipCode = stateAndZip.findElements(By.tagName("span")).get(1).getText();


        Apartment apartment = new Apartment(propertyName, streetAddress, city, state, Integer.parseInt(zipCode), url);

        List<WebElement> plans = driver.findElements(By.className("pricingGridItem"));
        List<Floorplan> floorplans = new ArrayList<>();

        for(WebElement plan : plans){
            Floorplan fp = new Floorplan();
            //name and rent
            if(plan.findElement(By.className("modelName")).getText().equals(""))
                continue; //skip empty floorplans
            fp.setName(plan.findElement(By.className("modelName")).getText());
            fp.setPriceRange(plan.findElement(By.className("rentLabel")).getText());

            //bed, bath, sqft
            List<WebElement> bbs = plan.findElement(By.className("detailsTextWrapper")).findElements(By.tagName("span"));
            fp.setBed(bbs.get(0).getText());
            fp.setBath(bbs.get(1).getText());
            fp.setSquareFeet(bbs.get(2).getText());
            fp.setApartment(apartment);
            floorplans.add(fp);
        }
        apartment.setFloorplans(floorplans);
        return apartment;
    }

    // Setter for WebDriver to allow injection of mock or alternative drivers for testing
    public void setDriver(WebDriver newDriver) {
        this.driver = newDriver;
    }

}