package principalproducto.Services;

import com.wintux.inventario.grpc.InventarioServiceGrpc;
import com.wintux.inventario.grpc.StockRequest;
import com.wintux.inventario.grpc.StockResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import principalproducto.Configs.KafkaInventarioEventProducer;

@Service
public class InventarioClienteService {
    private static final Logger logger = LoggerFactory.getLogger(InventarioClienteService.class);
    private KafkaInventarioEventProducer kafaInventario;
    @GrpcClient("inventario-service")
    private InventarioServiceGrpc.InventarioServiceBlockingStub inventarioStub;
    public int obtenerStockPorGrpc(Long productoId){
        StockRequest request = StockRequest.newBuilder().setProductoid(productoId).build();
        StockResponse response = inventarioStub.getStock(request);
        logger.info("Enviando petición al servidor gRPC con productoid: {}",productoId);
        kafaInventario.sendKafkaInventarioEvent("Enviando petición al servidor gRPC con productoid: {}"+productoId);
        return response.getCantidad();
    }
}

