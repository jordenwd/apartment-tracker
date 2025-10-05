package org.jwd.apartmenttracker.Controllers;

import org.jwd.apartmenttracker.entities.Apartment;
import org.jwd.apartmenttracker.repository.ApartmentRepository;
import org.jwd.apartmenttracker.scraper.ApartmentsComScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class ApartmentController {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ApartmentsComScraperService scraperService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("apartments", apartmentRepository.findAll());
        return "index";
    }

    @PostMapping("/track")
    public String trackApartment(String url) {
        Apartment apartment = scraperService.scrapeListing(url);
        apartmentRepository.save(apartment);
        return "redirect:/";
    }

}
