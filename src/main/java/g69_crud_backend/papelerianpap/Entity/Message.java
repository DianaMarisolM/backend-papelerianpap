package g69_crud_backend.papelerianpap.Entity;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    public static final HttpStatus UNAUTHORIZED = null;
    private int status;
    private String message;
}
