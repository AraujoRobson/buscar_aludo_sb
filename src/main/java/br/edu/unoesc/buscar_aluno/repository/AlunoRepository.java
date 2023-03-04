package br.edu.unoesc.buscar_aluno.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.unoesc.buscar_aluno.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer>{
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
	
	@Query("Select a from Aluno a where a.salario >= :salario")
	public List<Aluno> findBySalario(@Param("salario")BigDecimal salario);
	
	@Query("Select a from Aluno a where upper(a.nome) like upper(concat('%', :filtro, '%')) order by nome")
	public List<Aluno> findByFiltro(@Param("filtro") String filtro);
}
