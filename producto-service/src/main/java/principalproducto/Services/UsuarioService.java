package principalproducto.Services;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    public boolean validarCredenciales(String usuario, String pass){
        // conexión a DDBB o fuente de datos
        return usuario.equals("Pepe") && pass.equals("123abc");
    }
}
