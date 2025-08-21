package principalorden.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import principalorden.Models.Orden;

public interface OrdenRepository extends JpaRepository<Orden,Long> {
}
