package br.edu.unoesc.buscar_aluno.model;

import java.math.BigDecimal;
import java.time.LocalDate;


import org.springframework.data.annotation.Id;

public record Aluno(
	@Id
	Integer id,
	String nome,
	BigDecimal salario,
	LocalDate nascimento
	) {	
}
