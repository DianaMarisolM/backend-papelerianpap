package g69_crud_backend.papelerianpap.Utility;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import g69_crud_backend.papelerianpap.Entity.ERol;
import g69_crud_backend.papelerianpap.Entity.Role;
import g69_crud_backend.papelerianpap.Entity.Usuario;
import g69_crud_backend.papelerianpap.Repositoy.RolRepository;
import g69_crud_backend.papelerianpap.Security.Hash;
import g69_crud_backend.papelerianpap.Service.RolService;
import g69_crud_backend.papelerianpap.Service.UsuarioService;


@Component
public class DatosIniciales implements CommandLineRunner {
  

    @Autowired
    RolService rolService;

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        if (rolService.findAll().size() == 0) {
            Role rolAdmin = new Role(ERol.ROLE_ADMIN);
            Role rolUser = new Role(ERol.ROLE_USER);
            Role rolLogist = new Role(ERol.ROLE_LOGIST);
            rolService.save(rolAdmin);
            rolService.save(rolUser);
            rolService.save(rolLogist);
        }
        if (usuarioService.findAll().size() == 0) {
            Role userRole = roleRepository.findByNombre(ERol.ROLE_ADMIN).get();
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario("admin");
            usuario.setApellidoUsuario("admin");
            usuario.setUserName("admin");
            usuario.setPassword(Hash.sha1("123456"));
            usuario.setRoles(roles);
            usuarioService.save(usuario);
        }

    }
    
}
