package g69_crud_backend.papelerianpap.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import g69_crud_backend.papelerianpap.Entity.Message;
import g69_crud_backend.papelerianpap.Entity.Producto;
import g69_crud_backend.papelerianpap.Repositoy.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public Producto save(Producto Producto) {
        return productoRepository.save(Producto);
    }

    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    public Producto findAllId(String id) {
        return productoRepository.findById(id).get();
    }

    public Message deleteById(String id) {
        try {
            productoRepository.deleteById(id);
            return new Message(200, "Registro con id " + id + " eliminado");
        } catch (Exception e) {
        
            return new Message(400, "No existe Producto con id " + id);
        }

    }

}
