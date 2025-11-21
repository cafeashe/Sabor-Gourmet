package cl.ipss.sabor_gourmet;

import cl.ipss.sabor_gourmet.models.Cliente;
import cl.ipss.sabor_gourmet.models.Mesa;
import cl.ipss.sabor_gourmet.models.Reserva;
import cl.ipss.sabor_gourmet.repositories.ClienteRepository;
import cl.ipss.sabor_gourmet.repositories.MesaRepository;
import cl.ipss.sabor_gourmet.repositories.ReservaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class SaborGourmetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaborGourmetApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(MesaRepository mesaRepo,
                                      ClienteRepository clienteRepo,
                                      ReservaRepository reservaRepo) {

        return args -> {

            // Solo cargar datos si está todo vacío
            if (mesaRepo.count() == 0 && clienteRepo.count() == 0 && reservaRepo.count() == 0) {

                // --- MESAS ---
                Mesa m1 = new Mesa();
                m1.setNumero(1);
                m1.setCapacidad(2);
                m1.setDisponible(true);

                Mesa m2 = new Mesa();
                m2.setNumero(5);
                m2.setCapacidad(4);
                m2.setDisponible(true);

                Mesa m3 = new Mesa();
                m3.setNumero(10);
                m3.setCapacidad(6);
                m3.setDisponible(false);

                mesaRepo.save(m1);
                mesaRepo.save(m2);
                mesaRepo.save(m3);

                // --- CLIENTES ---
                Cliente c1 = new Cliente();
                c1.setNombre("Ana Pérez");
                c1.setTelefono("+56 9 1111 1111");
                c1.setEmail("ana@example.com");

                Cliente c2 = new Cliente();
                c2.setNombre("Carlos Gómez");
                c2.setTelefono("+56 9 2222 2222");
                c2.setEmail("carlos@example.com");

                clienteRepo.save(c1);
                clienteRepo.save(c2);

                // --- RESERVAS (usando fecha + hora + estado) ---
                Reserva r1 = new Reserva();
                r1.setMesa(m1);
                r1.setCliente(c1);
                r1.setFecha(LocalDate.now().plusDays(1));      // mañana
                r1.setHora(LocalTime.of(20, 0));               // 20:00
                r1.setEstado("ACTIVA");

                Reserva r2 = new Reserva();
                r2.setMesa(m2);
                r2.setCliente(c2);
                r2.setFecha(LocalDate.now().plusDays(2));      // pasado mañana
                r2.setHora(LocalTime.of(21, 30));              // 21:30
                r2.setEstado("ACTIVA");

                reservaRepo.save(r1);
                reservaRepo.save(r2);
            }
        };
    }
}
