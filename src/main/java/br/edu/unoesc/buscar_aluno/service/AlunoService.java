package br.edu.unoesc.buscar_aluno.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.edu.unoesc.buscar_aluno.model.Aluno;

public interface AlunoService {
	Aluno salvar(Aluno aluno);
	void excluir(Integer id);
	
	Aluno buscar(Integer id);
	Aluno buscarPorCodigo(Integer id);
	Optional<Aluno> porCodigo(Integer id);
	
	List<Aluno> buscarPorNome(String nome);
	List<Aluno> buscarPorSalario(BigDecimal salario);
	
	Iterable<Aluno> listar();
}
