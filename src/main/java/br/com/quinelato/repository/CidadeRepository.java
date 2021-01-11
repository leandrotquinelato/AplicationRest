package br.com.quinelato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.quinelato.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> 
{ 
	List<Cidade> findByNome(String nome);
	
	List<Cidade> findByEstado(String estado);

}
