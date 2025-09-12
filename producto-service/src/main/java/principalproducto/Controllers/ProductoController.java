package principalproducto.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import principalproducto.Models.Producto;
import principalproducto.Services.InventarioClienteService;
import principalproducto.Services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private InventarioClienteService inventarioClienteService;
    @GetMapping
    public List<Producto> getAll(){
        return productoService.getAll();
    }
    @GetMapping("/{identificador}")
    public Producto getProducto(@PathVariable("identificador") Long id){
        return productoService.getById(id);
    }
    @PostMapping
    public Producto crear(@RequestBody Producto producto){
        return productoService.registrar(producto);
    }
    @GetMapping("/stock/{id}") //http://localhost:8093/producto/stock/1 [GET]
    public int getProductoStock(@PathVariable Long id){
        return inventarioClienteService.obtenerStockPorGrpc(id);
    }
}
