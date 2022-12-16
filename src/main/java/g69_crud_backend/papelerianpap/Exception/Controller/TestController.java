package g69_crud_backend.papelerianpap.Exception.Controller;


//import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g69_crud_backend.papelerianpap.Dto.UsuarioDto;
import g69_crud_backend.papelerianpap.Entity.Usuario;
import g69_crud_backend.papelerianpap.Exception.Exception.DivisionZeroErrorException;
import g69_crud_backend.papelerianpap.Exception.Exception.NoFoundException;
import g69_crud_backend.papelerianpap.Service.UsuarioService;
import g69_crud_backend.papelerianpap.Utility.ConvertEntity;



@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TestController {

    @Autowired
    UsuarioService usuarioService;

    ConvertEntity convertEntity = new ConvertEntity();

    @GetMapping("/operacion/{num1}/{num2}")
    public double operarcion(@PathVariable double num1, @PathVariable double num2, @RequestBody String operacion) {

        if (num2 == 0) {
            throw new DivisionZeroErrorException("Numerador en 0");
        }
        return num1 / num2;
    }

    @GetMapping("/usuario/{username}")
    public UsuarioDto operarcion(@PathVariable String username) {
        Usuario usuario = usuarioService.findByUserName(username);

        if(usuario==null){
            throw new NoFoundException("El usuario con UserName "+username+" no existe");
        }

        return (UsuarioDto) convertEntity.convert(usuario, new UsuarioDto());

    }
}

