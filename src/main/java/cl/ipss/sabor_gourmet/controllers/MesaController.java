package cl.ipss.sabor_gourmet.controllers;

import cl.ipss.sabor_gourmet.models.Mesa;
import cl.ipss.sabor_gourmet.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    // LISTAR MESAS
    @GetMapping
    public String listarMesas(Model model) {
        model.addAttribute("tituloPagina", "Mesas registradas");
        model.addAttribute("mesas", mesaService.listarTodas());
        return "mesas";
    }

    // MOSTRAR FORMULARIO NUEVA MESA
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaMesa(Model model) {
        model.addAttribute("tituloFormulario", "Registrar nueva mesa");
        model.addAttribute("mesa", new Mesa());
        return "mesas-form";
    }

    // EDITAR MESA
    @GetMapping("/editar/{id}")
    public String editarMesa(@PathVariable Long id, Model model) {
        Mesa mesa = mesaService.obtenerPorId(id);
        model.addAttribute("tituloFormulario", "Editar mesa");
        model.addAttribute("mesa", mesa);
        return "mesas-form";
    }

    // GUARDAR (CREAR / EDITAR)
    @PostMapping("/guardar")
    public String guardarMesa(@ModelAttribute("mesa") Mesa mesa) {
        mesaService.guardar(mesa);
        return "redirect:/mesas";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarMesa(@PathVariable Long id) {
        mesaService.eliminar(id);
        return "redirect:/mesas";
    }
}
