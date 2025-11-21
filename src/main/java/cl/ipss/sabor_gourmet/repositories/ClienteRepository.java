package cl.ipss.sabor_gourmet.repositories;

import cl.ipss.sabor_gourmet.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
