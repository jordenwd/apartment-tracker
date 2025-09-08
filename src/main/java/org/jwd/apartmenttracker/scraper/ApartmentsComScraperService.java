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

    //data for apartment
    private String propertyName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private List<Floorplan> floorplans;
    private String contactPhone;

    //regex
    private String regex = "^(.*?),\\s*(.*?),\\s*([A-Z]{2})\\s*(\\d{5})$";

    Pattern pattern = Pattern.compile(regex);



    public Apartment scrapeListing(String url){
        driver.get(url);
        propertyName = driver.findElement(By.id("propertyName")).getText();

        String address = driver.findElement(By.className("propertyAddressContainer")).getText();

        Matcher matcher = pattern.matcher(address);

        if(matcher.matches()){
            streetAddress = matcher.group(1);
            city = matcher.group(2);
            state = matcher.group(3);
            zipCode = matcher.group(4);
        } else {
          throw new RuntimeException("Error parsing address");
        }

        List<WebElement> plans = driver.findElements(By.className("pricingGridItem"));
        floorplans = new ArrayList<>();

        for(WebElement plan : plans){
            Floorplan fp = new Floorplan();
            //name and rent
            fp.setName(plan.findElement(By.className("modelName")).getText());
            fp.setPriceRange(plan.findElement(By.className("rentLabel")).getText());

            //bed, bath, sqft
            List<WebElement> bbs = plan.findElement(By.className("detailsTextWrapper")).findElements(By.tagName("span"));
            fp.setBed(bbs.get(0).getText());
            fp.setBath(bbs.get(1).getText());
            fp.setSquareFeet(bbs.get(2).getText());
            floorplans.add(fp);
        }

        contactPhone = driver.findElement(By.className("propertyPhone")).getText();
        return new Apartment(propertyName, streetAddress, city, state, Integer.parseInt(zipCode), floorplans);
    }

}
