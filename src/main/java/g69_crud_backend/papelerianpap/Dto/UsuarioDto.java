package g69_crud_backend.papelerianpap.Dto;


import java.util.List;

import g69_crud_backend.papelerianpap.Entity.Role;

import lombok.Data;

@Data
public class UsuarioDto {
    private String idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String userName;
    private List<Role> roles;
}
