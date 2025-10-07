package org.jwd.apartmenttracker.scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfig {
    @Bean
    public WebDriver driver(){
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        return new ChromeDriver(options);
    }
}
