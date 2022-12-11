package g69_crud_backend.papelerianpap.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g69_crud_backend.papelerianpap.Entity.Menssage;


@RestController
@RequestMapping("/prueba")

public class Prueba {
    
    @GetMapping("/{dia}")
    public String saludar(@RequestBody Menssage mensaje, @PathVariable String dia){
        return "Hola "+ mensaje.getNombre() + " " + mensaje.getSaludo() + " " + dia;
    }
}











