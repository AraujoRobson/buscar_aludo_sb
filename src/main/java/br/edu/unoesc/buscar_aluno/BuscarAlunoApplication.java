package br.edu.unoesc.buscar_aluno;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.edu.unoesc.buscar_aluno.model.Aluno;
import br.edu.unoesc.buscar_aluno.service.AlunoService;

@SpringBootApplication
public class BuscarAlunoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuscarAlunoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(AlunoService servico) {
		return args -> {
			Aluno a = new Aluno(null, "Pedro", new BigDecimal("5000"), LocalDate.of(2000, 05, 13));
			servico.salvar(a);
			
			servico.salvar(new Aluno(null, "Vinicius", new BigDecimal("20000"), LocalDate.of(2001, 07, 20)));
			
		};
	}
}
