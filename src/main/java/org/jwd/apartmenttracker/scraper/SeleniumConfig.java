package org.jwd.apartmenttracker.scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfig {
    @Bean
    public WebDriver driver(){
        return new ChromeDriver();
    }
}
