package org.jwd.apartmenttracker.Controllers;

import org.jwd.apartmenttracker.entities.Apartment;
import org.jwd.apartmenttracker.repository.ApartmentRepository;
import org.jwd.apartmenttracker.scraper.ApartmentsComScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        try {
            Apartment apartment = scraperService.scrapeListing(url);
            apartmentRepository.save(apartment);
        }catch(Exception e){
            System.out.println("Apartment already tracked or invalid URL");
        }
        return "redirect:/";
    }

    @PatchMapping("/update")
    public String updateApartments() {
        List<Apartment> apartments = apartmentRepository.findAll();
        for (Apartment apartment : apartments) {
        boolean changed = false;
            try {
                Apartment updatedApartment = scraperService.scrapeListing((apartment.getUrl()));
                //if apartment name changes, update
                if(!updatedApartment.getName().equals(apartment.getName())) {
                    apartment.setName(updatedApartment.getName());
                    changed = true;
                }
                //if floorplans change, update
                if(!updatedApartment.getFloorplans().equals(apartment.getFloorplans())) {
                    apartment.getFloorplans().clear();
                    apartment.getFloorplans().addAll(updatedApartment.getFloorplans());
                    for(var floorplan : apartment.getFloorplans()) {
                        floorplan.setApartment(apartment);
                    }
                    changed = true;
                }

                if(changed){
                    apartmentRepository.save(apartment);
                }
            } catch (Exception e) {
                System.out.println("Failed to update apartment: " + apartment.getUrl());
            }
        }
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String DeleteApartment(@PathVariable Long id) {
        apartmentRepository.deleteById(id);
        return "redirect:/";
    }


}
