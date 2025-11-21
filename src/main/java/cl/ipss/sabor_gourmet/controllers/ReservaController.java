package cl.ipss.sabor_gourmet.controllers;

import cl.ipss.sabor_gourmet.models.Cliente;
import cl.ipss.sabor_gourmet.models.Mesa;
import cl.ipss.sabor_gourmet.models.Reserva;
import cl.ipss.sabor_gourmet.services.ClienteService;
import cl.ipss.sabor_gourmet.services.MesaService;
import cl.ipss.sabor_gourmet.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MesaService mesaService;

    // LISTAR RESERVAS
    @GetMapping
    public String listarReservas(Model model) {
        List<Reserva> reservas = reservaService.listarTodas();

        model.addAttribute("tituloPagina", "Reservas actuales");
        model.addAttribute("reservas", reservas);

        return "reservas";
    }

    // MOSTRAR FORMULARIO NUEVA / EDITAR
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaReserva(Model model) {
        Reserva reserva = new Reserva();

        model.addAttribute("tituloFormulario", "Registrar nueva reserva");
        model.addAttribute("reserva", reserva);

        // listas para los <select>
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("mesas", mesaService.listarTodas());

        return "reservas-form";
    }

    @GetMapping("/editar/{id}")
    public String editarReserva(@PathVariable Long id, Model model) {
        Reserva reserva = reservaService.obtenerPorId(id);

        model.addAttribute("tituloFormulario", "Editar reserva");
        model.addAttribute("reserva", reserva);

        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("mesas", mesaService.listarTodas());

        return "reservas-form";
    }

    // GUARDAR (CREAR / EDITAR)
    @PostMapping("/guardar")
    public String guardarReserva(
            @ModelAttribute("reserva") Reserva reserva,
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("mesaId") Long mesaId) {

        // Buscar cliente y mesa en la BD
        Cliente cliente = clienteService.obtenerPorId(clienteId);
        Mesa mesa = mesaService.obtenerPorId(mesaId);

        reserva.setCliente(cliente);
        reserva.setMesa(mesa);

        // Si no viene estado, la dejamos ACTIVA
        if (reserva.getEstado() == null || reserva.getEstado().isBlank()) {
            reserva.setEstado("ACTIVA");
        }

        reservaService.guardar(reserva);
        return "redirect:/reservas";
    }

    // CANCELAR (no borrar físicamente)
    @GetMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id) {
        reservaService.cancelar(id);
        return "redirect:/reservas";
    }

    // ELIMINAR DEFINITIVAMENTE (opcional, por si te lo piden)
    @GetMapping("/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        reservaService.eliminar(id);
        return "redirect:/reservas";
    }
}
