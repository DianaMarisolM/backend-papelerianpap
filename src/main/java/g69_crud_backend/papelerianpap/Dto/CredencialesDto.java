package g69_crud_backend.papelerianpap.Dto;

import java.util.List;

import g69_crud_backend.papelerianpap.Entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredencialesDto {
    private String user;
    private String key;
    private List<Role> roles;
}
