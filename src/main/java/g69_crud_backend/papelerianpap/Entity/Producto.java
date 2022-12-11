package g69_crud_backend.papelerianpap.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
public class Producto  {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idProducto")
    private String id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fechaCreación")
    private Date fechaCreacion;
    @Column(name = "existenciaProducto")
    private double existenciaProducto;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    @Override
    public String toString() {
        return "Cuenta [id_producto=" + id + "descripcion" + descripcion +", fecha_creación=" + fechaCreacion + ", existenciaProducto="
                + existenciaProducto + ", usuario=" + usuario + "]";
    }
    public Producto(String id) {
        this.id = id;
    }

    

}
