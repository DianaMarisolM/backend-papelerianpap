package g69_crud_backend.papelerianpap.Repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import g69_crud_backend.papelerianpap.Entity.Usuario;



@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,String> {
    public Optional<Usuario> findById(String id);

    public Usuario findByUserName(String user);

    @Transactional(readOnly = false)
    @Query(value = "Select * from usuarios where user_name=:user and password=:pwd ",nativeQuery = true)
    public Usuario login(@Param("user") String user,@Param("pwd") String pwd);

  
}
