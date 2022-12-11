package g69_crud_backend.papelerianpap;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PapelerianpapApplication {

	public static void main(String[] args) {
		SpringApplication.run(PapelerianpapApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
	return new ModelMapper();
	}
	

}
