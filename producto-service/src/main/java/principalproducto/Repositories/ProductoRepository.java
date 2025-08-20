package principalproducto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import principalproducto.Models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
