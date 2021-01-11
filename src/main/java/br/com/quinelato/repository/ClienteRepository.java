package br.com.quinelato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.quinelato.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNome(String nome);
}
