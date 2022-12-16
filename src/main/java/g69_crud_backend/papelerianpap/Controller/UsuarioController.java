package g69_crud_backend.papelerianpap.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g69_crud_backend.papelerianpap.Dto.CrearUsuarioDto;
import g69_crud_backend.papelerianpap.Dto.CredencialesDto;
import g69_crud_backend.papelerianpap.Dto.UsuarioDto;
import g69_crud_backend.papelerianpap.Entity.ERol;
import g69_crud_backend.papelerianpap.Entity.Message;
import g69_crud_backend.papelerianpap.Entity.Role;
import g69_crud_backend.papelerianpap.Entity.Usuario;
import g69_crud_backend.papelerianpap.Exception.Exception.InvalidDataException;
import g69_crud_backend.papelerianpap.Security.Hash;
import g69_crud_backend.papelerianpap.Service.RolService;
import g69_crud_backend.papelerianpap.Service.UsuarioService;
import g69_crud_backend.papelerianpap.Utility.ConvertEntity;


@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    ConvertEntity convertEntity = new ConvertEntity();

    @Autowired
    RolService rolService;

    @PostMapping("/create")
    public ResponseEntity<Message> save(@Valid @RequestBody CrearUsuarioDto usuario, BindingResult result,
            @RequestHeader String user,
            @RequestHeader String key) {

        if (result.hasErrors()) {
            throw new InvalidDataException("Error de datos", result);
        }

        if (usuarioService.validarUsuarioAdmin(user, key) == false) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Set<String> strRoles = usuario.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role rol = rolService.findByNombre(ERol.ROLE_USER).get();
            roles.add(rol);
        } else {

            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role rolAdmin = rolService.findByNombre(ERol.ROLE_ADMIN).get();
                        roles.add(rolAdmin);
                        break;
                    case "user":
                        Role rolUser = rolService.findByNombre(ERol.ROLE_USER).get();
                        roles.add(rolUser);
                        break;
                    case "logist":
                        Role rolLogist = rolService.findByNombre(ERol.ROLE_LOGIST).get();
                        roles.add(rolLogist);
                        break;
                }
            });

        }

        
        Usuario usuarioSave = (Usuario) convertEntity.convert(usuario, new Usuario());
       
        usuarioSave.setPassword(Hash.sha1(usuario.getPassword()));
       
        usuarioSave.setRoles(roles);
          if (!usuarioService.validarCredenciales(user, key)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            if (usuario.getNombreUsuario() == null) {
                return new ResponseEntity<Message>(new Message(400, "El campo nombre es obligatorio"),
                        HttpStatus.BAD_REQUEST);
            }
            
            usuarioService.save(usuarioSave);
            return new ResponseEntity<Message>(new Message(201, "Registro Creado de forma  satisfactoria"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message(400, "Los datos ingresados no    son correctos"),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody CrearUsuarioDto usuario, @RequestHeader String user,
    @RequestHeader String key) {
        if (usuarioService.validarCredenciales(user, key) == false) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Set<String> strRoles = usuario.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role rol = rolService.findByNombre(ERol.ROLE_USER).get();
            roles.add(rol);
        } else {

            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role rolAdmin = rolService.findByNombre(ERol.ROLE_ADMIN).get();
                        roles.add(rolAdmin);
                        break;
                    case "user":
                        Role rolUser = rolService.findByNombre(ERol.ROLE_USER).get();
                        roles.add(rolUser);
                        break;
                    case "logist":
                        Role rolLogist = rolService.findByNombre(ERol.ROLE_LOGIST).get();
                        roles.add(rolLogist);
                        break;
                }
            });

        }

        Usuario usuarioSave = (Usuario)convertEntity.convert(usuario, new Usuario());
        usuarioSave.setPassword(Hash.sha1(usuario.getPassword()));
        usuarioSave.setRoles(roles);
        try {
            if (usuario.getNombreUsuario() == null) {
                return new ResponseEntity<Message>(new Message(400, "El campo nombre es obligatorio"),
                        HttpStatus.BAD_REQUEST);
            }
            Message message = usuarioService.update(usuarioSave);
            return new ResponseEntity<Message>(new Message(message.getStatus(), message.getMessage()),
                    HttpStatus.valueOf(message.getStatus()));
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message(400, "Los datos ingresados no son correctos"),
                    HttpStatus.BAD_REQUEST);
        }
      

    }

    @GetMapping("/encriptar/{dato}")
    public String encriptar(@PathVariable String dato) {
        return Hash.sha1(dato);
    }

    @GetMapping
    public ResponseEntity<String> saludar() {
        return new ResponseEntity<>("Hola mundo", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteById(@PathVariable String id) {
        Message message = usuarioService.deleteById(id);
        return new ResponseEntity<>(message, HttpStatus.resolve(message.getStatus()));
    }

    @GetMapping("/list/{id}")
    public UsuarioDto findById(@PathVariable String id,@RequestHeader String user, @RequestHeader String key){
            
        UsuarioDto usuarioDto = new UsuarioDto();
        return (UsuarioDto) convertEntity.convert(usuarioService.findById(id), usuarioDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsuarioDto>> findAll(@RequestHeader String user, @RequestHeader String key) {

        if (!usuarioService.validarUsuarioAdmin(user, key)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDto> usuariosDto = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuariosDto.add((UsuarioDto) convertEntity.convert(usuario, usuarioDto));
        }

        return new ResponseEntity<>(usuariosDto, HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader String user, @RequestHeader String pwd) {
        System.out.println(Hash.sha1(pwd)+"--"+user);
        Usuario usuario = usuarioService.login(user, Hash.sha1(pwd));
        if (usuario != null) {
            System.out.println(usuario.getRoles());
            UsuarioDto usuarioDto = ((UsuarioDto) convertEntity.convert(usuario,new UsuarioDto()));
            return new ResponseEntity<>(new CredencialesDto(usuario.getUserName(),
                    Hash.sha1(Hash.sha1(pwd) + Hash.sha1(usuario.getUserName())),usuarioDto.getRoles()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message(401, "Error de credenciales"), HttpStatus.UNAUTHORIZED);
        }

    }

}
