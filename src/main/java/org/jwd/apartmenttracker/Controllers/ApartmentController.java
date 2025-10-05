package org.jwd.apartmenttracker.Controllers;

import org.jwd.apartmenttracker.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ApartmentController {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("apartments", apartmentRepository.findAll());
        return "index";
    }
}
