package g69_crud_backend.papelerianpap.Dto;

import java.util.Set;



import lombok.Data;

@Data
public class CrearUsuarioDto {
    private String idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String userName;
    private String password;
    private Set<String> roles;
}
