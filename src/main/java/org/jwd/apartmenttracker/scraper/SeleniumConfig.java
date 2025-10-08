package org.jwd.apartmenttracker.scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class SeleniumConfig {

    @Value("${selenium.grid.url}")
    private String seleniumGridUrl;

    @Bean
    public WebDriver driver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        return new RemoteWebDriver(new URL(seleniumGridUrl), options);
    }
}
