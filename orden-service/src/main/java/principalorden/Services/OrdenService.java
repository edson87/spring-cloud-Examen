package principalorden.Services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import principalcommons.OrdenEvento;
import principalorden.Configs.OrdenEventoPublisher;
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
    @Autowired
    private OrdenEventoPublisher eventoPublisher;

    public List<Orden> getTodasLasOrdenes(){
        return ordenRepository.findAll();
    }
    @CircuitBreaker(name="productoServiceCB", fallbackMethod = "fallbackObtenerProducto")
    public Orden registrarOrden(Orden orden){
        // Llamar al microservico de productos por HTTP
        //String productoUrl = "http://localhost:8091/producto/"+orden.getProductoid();
        //Producto producto = restTemplate.getForObject(productoUrl,Producto.class);

        // Llamar al microservicio de productos por Eureka
        Producto producto = productoClient.obtenerProducto(orden.getProductoid());

        if(producto == null) throw new RuntimeException("Producto no encontrado");
        orden.setTotal(producto.getPrecio() * orden.getCantidad());
        Orden ordenGuardar = ordenRepository.save(orden);
        eventoPublisher.publicarOrdenEvento(
                new OrdenEvento(
                        ordenGuardar.getId(),
                        ordenGuardar.getProductoid(),
                        ordenGuardar.getCantidad()
                )
        );
        return ordenGuardar;
    }
    public Orden fallbackObtenerProducto(Orden orden, Throwable throwable){
        System.out.println("Fallback originado por: "+ throwable.getMessage());
        Orden fallbackOrden = new Orden();
        fallbackOrden.setProductoid(orden.getProductoid());
        fallbackOrden.setCantidad(orden.getCantidad());
        fallbackOrden.setTotal(-1.0);
        return ordenRepository.save(orden);
    }
}
