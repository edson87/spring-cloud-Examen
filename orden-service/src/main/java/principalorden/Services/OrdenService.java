package principalorden.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import principalorden.Configs.ProductoClient;
import principalorden.DTOs.Producto;
import principalorden.Models.Orden;
import principalorden.Repositories.OrdenRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrdenRepository ordenRepository;

    private final ProductoClient productoClient;

    public List<Orden> getTodasLasOrdenes(){
        return ordenRepository.findAll();
    }

    public Orden registrarOrden(Orden orden){
        // Llamar al microservico de productos por HTTP
        //String productoUrl = "http://localhost:8091/producto/"+orden.getProductoid();
        //Producto producto = restTemplate.getForObject(productoUrl,Producto.class);

        // Llamar al microservicio de productos por Eureka
        Producto producto = productoClient.obtenerProducto(orden.getProductoid());

        if(producto == null) throw new RuntimeException("Producto no encontrado");
        orden.setTotal(producto.getPrecio() * orden.getCantidad());
        return ordenRepository.save(orden);
    }
}
