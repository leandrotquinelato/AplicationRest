package br.com.quinelato.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.quinelato.entity.Cidade;
import br.com.quinelato.repository.CidadeRepository;
import br.com.quinelato.util.Mensagem;

 
@Service
@Transactional
public class CidadeService {

	@Autowired
    private CidadeRepository _cidadeRepository;
	
	public ResponseEntity findAllCidades() {
		List<Cidade> cidades = _cidadeRepository.findAll();
		
		if(cidades != null && !cidades.isEmpty())
        	return new ResponseEntity(cidades, HttpStatus.FOUND);
        else {        	
        	return new ResponseEntity(new Mensagem("Não existe cidades Cadastadas."), HttpStatus.NOT_FOUND);
        }
		
    }
        
    public Cidade save(@Valid @RequestBody Cidade cidade)
    {
        return _cidadeRepository.save(cidade);
    }
        
    public ResponseEntity findByNome(@PathVariable(value = "nome") String nome)
    {
        List<Cidade> cidades = _cidadeRepository.findByNome(nome);
        if(cidades != null && !cidades.isEmpty())
        	return new ResponseEntity(cidades, HttpStatus.FOUND);
        else {        	
        	return new ResponseEntity(new Mensagem("Não foi encontrada nenhuma Cidade cadastada com esse nome"), HttpStatus.NOT_FOUND);
        }
    }
    
    public ResponseEntity findByEstado(@PathVariable(value = "estado") String estado)
    {
        List<Cidade> cidades = _cidadeRepository.findByEstado(estado);
        if(cidades != null && !cidades.isEmpty())
        	return new ResponseEntity(cidades, HttpStatus.FOUND);
        else {        	
        	return new ResponseEntity(new Mensagem("Não exite Cidade cadastada para esse Estado"), HttpStatus.NOT_FOUND);
        }
    }    
	
}
