package cl.ipss.sabor_gourmet.services;

import cl.ipss.sabor_gourmet.models.Reserva;
import cl.ipss.sabor_gourmet.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Reserva obtenerPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con id: " + id));
    }

    public Reserva guardar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }

    // Cambiar estado a CANCELADA en lugar de borrarla
    public void cancelar(Long id) {
        Reserva reserva = obtenerPorId(id);
        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);
    }
}
