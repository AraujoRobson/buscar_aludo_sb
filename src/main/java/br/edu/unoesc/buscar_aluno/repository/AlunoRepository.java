package br.edu.unoesc.buscar_aluno.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.unoesc.buscar_aluno.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer>{

}
