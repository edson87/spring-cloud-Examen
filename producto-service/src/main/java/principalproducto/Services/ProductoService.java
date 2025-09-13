package principalproducto.Services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import principalproducto.Configs.KafkaInventarioEventProducer;
import principalproducto.Models.Producto;
import principalproducto.Repositories.ProductoRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductoService {
    private static final Logger logger = LoggerFactory.getLogger(ProductoService.class);
    private KafkaInventarioEventProducer kafaInventario;

    @Autowired
    private ProductoRepository repository;
    public Producto registrar(Producto prod){
        logger.info("Se procede a registrar un producto: ", prod);
        kafaInventario.sendKafkaInventarioEvent("Se procede a registrar un producto:");
        return repository.save(prod);
    }

    public Producto getById(Long id){
        logger.info("Se envían datos del producto con ID: ",id);
        kafaInventario.sendKafkaInventarioEvent("Se envían datos del producto con ID: ");
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
    public List<Producto> getAll(){
        logger.info("Se solicitó una lista de todos los productos.");
        kafaInventario.sendKafkaInventarioEvent("Se solicitó una lista de todos los productos.");
        return repository.findAll();
    }
}
