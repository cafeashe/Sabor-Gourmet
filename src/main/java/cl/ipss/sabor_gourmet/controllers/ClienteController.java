package cl.ipss.sabor_gourmet.controllers;

import cl.ipss.sabor_gourmet.models.Cliente;
import cl.ipss.sabor_gourmet.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // LISTAR CLIENTES
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("tituloPagina", "Clientes registrados");
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes";
    }

    // MOSTRAR FORMULARIO NUEVO CLIENTE
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("tituloFormulario", "Registrar nuevo cliente");
        model.addAttribute("cliente", new Cliente());
        return "clientes-form";
    }

    // EDITAR CLIENTE
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerPorId(id);
        model.addAttribute("tituloFormulario", "Editar cliente");
        model.addAttribute("cliente", cliente);
        return "clientes-form";
    }

    // GUARDAR (CREAR / EDITAR)
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }
}
