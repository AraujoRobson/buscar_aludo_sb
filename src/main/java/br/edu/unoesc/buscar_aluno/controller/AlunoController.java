package br.edu.unoesc.buscar_aluno.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.buscar_aluno.model.Aluno;
import br.edu.unoesc.buscar_aluno.service.AlunoService;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	@Autowired
	private AlunoService servico;

	@GetMapping
	public Iterable<Aluno> findAll(){
		return servico.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscarPorCodigo(@PathVariable("id") Integer Id){
		Optional<Aluno> aluno = servico.porCodigo(Id);
		if (aluno.isPresent()) {
			return ResponseEntity.ok(aluno.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/find")
	List<Aluno> findByFiltro(@RequestParam("filtro") String filtro){
		return servico.buscarPorNome(filtro);
	}
	
	@GetMapping("/nome")
	public List<Aluno> buscarPorNome(@RequestParam("filtro") String filtro){
		return servico.buscarPorNome(filtro);
	}
	
	@GetMapping("/salario")
	public List<Aluno> buscarPorSalario(@RequestParam("filtro") BigDecimal filtro){
		return servico.buscarPorSalario(filtro);
	}
	
	@GetMapping(value={"/alunoporsalario", "/alunoporsalario/{salario}"})
	public List<Aluno> listarPorSalario(@PathVariable Optional<BigDecimal> salario){
		return servico.buscarPorSalario(salario.orElse(null));
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Aluno incluir(@RequestBody Aluno aluno) {
		return servico.salvar(aluno);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Aluno> atualizar(@PathVariable("id") Integer id, @RequestBody Aluno aluno){
		
		if (servico.porCodigo(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		aluno.setId(id);
		aluno = servico.salvar(aluno);
		
		return ResponseEntity.ok(aluno);
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<Void> exlcuir(@PathVariable("id") Integer id){
		try {
			servico.excluir(id);
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}	
}
