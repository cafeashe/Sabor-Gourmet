package cl.ipss.sabor_gourmet.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;

    private int capacidad;

    private boolean disponible;
}
