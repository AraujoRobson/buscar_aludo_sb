package br.edu.unoesc.buscar_aluno.service_impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.buscar_aluno.model.Aluno;
import br.edu.unoesc.buscar_aluno.repository.AlunoRepository;
import br.edu.unoesc.buscar_aluno.service.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService{
	@Autowired
	private AlunoRepository repositorio;

	@Override
	public Aluno salvar(Aluno aluno) {
		return repositorio.save(aluno);
	}

	@Override
	public void excluir(Integer id) {
		if(repositorio.existsById(id)) {
			repositorio.deleteById(id);
		} else {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Aluno.class.getName(), null);
		}
	}

	@Override
	public Aluno buscar(Integer id) {
		Optional<Aluno> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Aluno.class.getName(), null)
		);
	}

	@Override
	public Aluno buscarPorCodigo(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Optional<Aluno> porCodigo(Integer id) {
		// TODO Auto-generated method stub
		return repositorio.findById(id);
	}

	@Override
	public List<Aluno> buscarPorNome(String nome) {
		return repositorio.findByNomeContainingIgnoreCase(nome);
	}

	@Override
	public List<Aluno> buscarPorSalario(BigDecimal salario) {
		return repositorio.findBySalario(salario);
	}

	@Override
	public Iterable<Aluno> listar() {
		return repositorio.findAll();
	}

}
