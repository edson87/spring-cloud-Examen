package principalorden.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import principalorden.Models.Orden;
import principalorden.Services.OrdenService;

import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {
    @Autowired
    private OrdenService ordenService;
    @GetMapping
    public List<Orden> getAll(){
        return ordenService.getTodasLasOrdenes();
    }
    @PostMapping
    public Orden crearOrden(@RequestBody Orden o){
        return ordenService.registrarOrden(o);
    }

}
