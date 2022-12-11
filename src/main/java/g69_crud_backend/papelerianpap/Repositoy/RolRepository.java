package g69_crud_backend.papelerianpap.Repositoy;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import g69_crud_backend.papelerianpap.Entity.ERol;
import g69_crud_backend.papelerianpap.Entity.Role;

@Repository
public interface RolRepository extends CrudRepository<Role,String> {
    public Optional<Role> findByNombre(ERol nombre);
}
