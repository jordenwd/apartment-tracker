package org.jwd.apartmenttracker.Controllers;

import org.jwd.apartmenttracker.repository.FloorplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FloorplanController {
    @Autowired
    private FloorplanRepository floorplanRepository;

    @GetMapping("/floorplans")
    public String floorplansPage(Model model) {
        model.addAttribute("floorplans", floorplanRepository.findAll());
        return "floorplans";
    }
}
