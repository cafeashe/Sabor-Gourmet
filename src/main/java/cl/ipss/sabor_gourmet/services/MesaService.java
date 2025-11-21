package cl.ipss.sabor_gourmet.services;

import cl.ipss.sabor_gourmet.models.Mesa;
import cl.ipss.sabor_gourmet.repositories.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> listarTodas() {
        return mesaRepository.findAll();
    }

    public Mesa obtenerPorId(Long id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada con id: " + id));
    }

    public Mesa guardar(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public void eliminar(Long id) {
        mesaRepository.deleteById(id);
    }
}
