package principalorden.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import principalorden.DTOs.Producto;
import principalorden.Models.Orden;
import principalorden.Repositories.OrdenRepository;

import java.util.List;

@Service
public class OrdenService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> getTodasLasOrdenes(){
        return ordenRepository.findAll();
    }

    public Orden registrarOrden(Orden orden){
        // Llamar al microservico de productos
        String productoUrl = "http://localhost:8091/producto/"+orden.getProductoid();
        Producto producto = restTemplate.getForObject(productoUrl,Producto.class);
        if(producto == null) throw new RuntimeException("Producto no encontrado");
        orden.setTotal(producto.getPrecio() * orden.getCantidad());
        return ordenRepository.save(orden);
    }
}
