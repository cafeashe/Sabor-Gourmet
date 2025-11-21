package cl.ipss.sabor_gourmet.repositories;

import cl.ipss.sabor_gourmet.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
