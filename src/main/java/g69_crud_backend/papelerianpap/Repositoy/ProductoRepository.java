package g69_crud_backend.papelerianpap.Repositoy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import g69_crud_backend.papelerianpap.Entity.Producto;





@Repository
public interface ProductoRepository extends CrudRepository<Producto,String> {
    
}
