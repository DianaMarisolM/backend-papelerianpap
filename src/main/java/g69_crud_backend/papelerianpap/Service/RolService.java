package g69_crud_backend.papelerianpap.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import g69_crud_backend.papelerianpap.Entity.ERol;
import g69_crud_backend.papelerianpap.Entity.Role;
import g69_crud_backend.papelerianpap.Repositoy.RolRepository;


@Service
public class RolService {
    
    @Autowired
    RolRepository rolRepository;

    public List<Role> findAll(){
        return (List<Role>) rolRepository.findAll();
    }

    public Optional<Role> findByNombre(ERol nombre){
        return rolRepository.findByNombre(nombre);
    }

    public Role save(Role role){
        return rolRepository.save(role);
    }
}
