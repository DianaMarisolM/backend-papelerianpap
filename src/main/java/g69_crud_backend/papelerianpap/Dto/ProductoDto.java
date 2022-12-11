package g69_crud_backend.papelerianpap.Dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductoDto {
    private String id;
    private String descripcion;
    private Date fechaCreacion;
    private double existenciaProducto;
    private UsuarioDto usuario;
}