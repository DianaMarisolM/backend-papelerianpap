package g69_crud_backend.papelerianpap.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import g69_crud_backend.papelerianpap.Entity.Message;
import g69_crud_backend.papelerianpap.Entity.Role;
import g69_crud_backend.papelerianpap.Entity.Usuario;
import g69_crud_backend.papelerianpap.Repositoy.UsuarioRepository;
import g69_crud_backend.papelerianpap.Security.Hash;



@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Message deleteById(String id) {
        try {
            usuarioRepository.deleteById(id);
            return new Message(200, "Registro con id " + id + " eliminado");
        } catch (Exception e) {
            // TODO: handle exception
            return new Message(400, "No existe usuario con id " + id);
        }

    }

    public Message update(Usuario usuario) {
        Usuario usuarioConsulta = usuarioRepository.findById(usuario.getIdUsuario()).get();
        if (!usuarioConsulta.getIdUsuario().equals("")) {
            usuarioRepository.save(usuario);
            return new Message(200, "Registro actualizado");
        } else {
            return new Message(404, "Usuario no encontrado");
        }

    }

    public Usuario login(String user, String pwd) {
        return usuarioRepository.login(user, pwd);
    }

    public Usuario findByUserName(String user) {
        return usuarioRepository.findByUserName(user);
    }

    public Usuario findById(String id) {
        return usuarioRepository.findById(id).get();
    }

    public Boolean validarCredenciales(String user, String key) {
        Usuario usuarioC = usuarioRepository.findByUserName(user);
        if (usuarioC == null) {
            return false;
        } else {
            if (Hash.sha1(usuarioC.getPassword() + Hash.sha1(user)).equals(key)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean validarUsuarioAdmin(String user, String key) {
        Usuario usuario = usuarioRepository.findByUserName(user);

        if (!validarCredenciales(user, key)) {
            return false;
        }
        try {
            if (usuario.getNombreUsuario() == null) {
                return false;
            }
            int cantidad = 0;

            for (Role role : usuario.getRoles()) {
                if (role.getNombre().toString().equals("ROLE_ADMIN")) {
                    cantidad++;
                }

            }
            if (cantidad == 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
