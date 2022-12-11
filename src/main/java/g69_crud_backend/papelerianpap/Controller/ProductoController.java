package g69_crud_backend.papelerianpap.Controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g69_crud_backend.papelerianpap.Dto.ProductoDto;
import g69_crud_backend.papelerianpap.Entity.Message;
import g69_crud_backend.papelerianpap.Entity.Producto;
import g69_crud_backend.papelerianpap.Service.ProductoService;
import g69_crud_backend.papelerianpap.Utility.ConvertEntity;


@RestController
@RequestMapping("/api/v1/producto")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    ConvertEntity convertEntity = new ConvertEntity();
    
    @PostMapping("/create")
    public Producto save(@RequestBody Producto producto){
        
        return productoService.save(producto);
    }

    @PutMapping("/update")
    public Producto update(@RequestBody Producto producto){        
        return productoService.save(producto);
    }

    @GetMapping("/list")
    public List<ProductoDto> findAll(){

        List<Producto> Productos = productoService.findAll();
        List<ProductoDto> ProductosDto = new ArrayList<>();
        for (Producto Producto : Productos) {
            ProductoDto ProductoDto = new ProductoDto();
           ProductosDto.add((ProductoDto) convertEntity.convert(Producto, ProductoDto));
        }  
        return ProductosDto;
    }

    @GetMapping("/list/{id}")
    public ProductoDto findAllId(@PathVariable("id") String id){
        ProductoDto ProductoDto = new ProductoDto();
        Producto producto = productoService.findAllId(id);
        return (ProductoDto)convertEntity.convert(producto, ProductoDto);        
    }

    @DeleteMapping("/delete/{id}")
    public Message deleteById(@PathVariable("id") String id){
        return productoService.deleteById(id);
    }
}
