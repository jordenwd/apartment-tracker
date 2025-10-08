package org.jwd.apartmenttracker.Controllers;

import org.jwd.apartmenttracker.repository.FloorplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FloorplanController {
    @Autowired
    private FloorplanRepository floorplanRepository;

    @GetMapping("/floorplans")
    public String floorplans(@RequestParam(required = false) String sort, Model model) {
        if(sort == null) sort = "";
        if (sort.equals("minPrice")) {
            model.addAttribute("floorplans", floorplanRepository.findAll().stream()
                    .sorted((f1, f2) -> Integer.compare(f1.getMinPrice(), f2.getMinPrice()))
                    .toList());
        } else if (sort.equals("maxPrice")) {
            model.addAttribute("floorplans", floorplanRepository.findAll().stream()
                    .sorted((f1, f2) -> Integer.compare(f2.getMaxPrice(), f1.getMaxPrice()))
                    .toList());
        } else {
            model.addAttribute("floorplans", floorplanRepository.findAll());
        }
        return "floorplans";
    }
}
