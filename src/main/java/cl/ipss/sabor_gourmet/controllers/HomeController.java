package cl.ipss.sabor_gourmet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/inicio"})
    public String inicio(Model model) {
        model.addAttribute("tituloPagina", "SaborGourmet - Reservas y mesas");
        return "index";
    }
}
