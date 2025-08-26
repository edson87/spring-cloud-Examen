package principalorden.Services;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import principalorden.Configs.ProductoClient;
import principalorden.DTOs.Producto;
import principalorden.Models.Orden;
import principalorden.Repositories.OrdenRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenRecoveryService {
    @Autowired
    private OrdenRepository ordenRepository;
    private final ProductoClient productoClient;
    public void recuperacionOrdenesPendientes(){
        System.out.println("Recuperando órdenes pendientes desde la base de datos...");
        List<Orden> ordenesPendientes = ordenRepository.findByTotal(-1.0); // select * from orden where total = -1.0
        for(Orden orden : ordenesPendientes){
            try{
                Producto producto = productoClient.obtenerProducto(orden.getProductoid());
                orden.setTotal(producto.getPrecio() * orden.getCantidad());
                ordenRepository.save(orden);
            }catch(FeignException fe){
                ordenRepository.delete(orden);
                System.out.printf("Producto no exitente %d, se descarta.",orden.getProductoid());
            }
        }
        System.out.println("Intento de recuperación de órdenes pendientes: TERMINADO...");
    }
}

// openzipkin/zipkin

// grafana/grafana

// prom/prometheus