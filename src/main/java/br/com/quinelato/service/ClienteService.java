package br.com.quinelato.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.quinelato.entity.Cidade;
import br.com.quinelato.entity.Cliente;
import br.com.quinelato.repository.CidadeRepository;
import br.com.quinelato.repository.ClienteRepository;
import br.com.quinelato.util.Mensagem;

@Service
@Transactional
public class ClienteService {

	@Autowired
    private ClienteRepository _clienteRepository;
	
	@Autowired
    private CidadeRepository _cidadeRepository;
    
    public ResponseEntity findAllClientes() {
		List<Cliente> clientes = _clienteRepository.findAll();
		
		if(clientes != null && !clientes.isEmpty())
        	return new ResponseEntity(clientes, HttpStatus.FOUND);
        else {        	
        	return new ResponseEntity(new Mensagem("Não existe clientes cadastados."), HttpStatus.NOT_FOUND);
        }		
    }

    public ResponseEntity findById(@PathVariable(value = "id") long id)
    {
        Optional<Cliente> cliente = _clienteRepository.findById(id);
        if(cliente.isPresent())
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(new Mensagem("Não existe cliente cadastado com esse ID."), HttpStatus.NOT_FOUND);
    }
    
    public ResponseEntity findByNome(@PathVariable(value = "nome") String nome)
    {
        List<Cliente> cliente = _clienteRepository.findByNome(nome);
        if(cliente != null && !cliente.isEmpty())
        	return new ResponseEntity(cliente, HttpStatus.FOUND);
        else {        	
        	return new ResponseEntity(new Mensagem("Não foi encontrado nenhum Cliente cadastado com esse nome"), HttpStatus.NOT_FOUND);
        }
    }
   
    public ResponseEntity save(@Valid @RequestBody Cliente cliente)
    {
    	Optional<Cidade> cidade = null;
    	
    	if(cliente.getCidade() != null && cliente.getCidade().getId() > 0) {    		
    		cidade = _cidadeRepository.findById(cliente.getCidade().getId());
    	}
    	else {
    		return new ResponseEntity<>(new Mensagem("É obrigatório o preenchimento de uma cidade para o Cliente."), HttpStatus.NOT_FOUND);
    	}
    	if(cidade.isPresent()) {    	
    		Cliente clienteSavo = _clienteRepository.save(cliente);
    		return new ResponseEntity<Cliente>(clienteSavo, HttpStatus.OK);
    	}
    	else {
    		return new ResponseEntity<>(new Mensagem("Não existe cidade cadastada com o ID passado."), HttpStatus.NOT_FOUND);
    	}
    }
    
    public ResponseEntity<Cliente> alterarRegistro(@PathVariable(value = "id") long id, @Valid @RequestBody Cliente newcliente)
    {
        Optional<Cliente> oldcliente = _clienteRepository.findById(id);
        if(oldcliente.isPresent()){
        	Cliente cliente = oldcliente.get();
        	newcliente.setId(cliente.getId());
            _clienteRepository.save(newcliente);
            return new ResponseEntity<Cliente>(newcliente, HttpStatus.OK);
        }
        else
        	return new ResponseEntity(new Mensagem("Não existe cliente cadastado com esse ID."), HttpStatus.NOT_FOUND);            
    }
    
    public ResponseEntity<Object> deletarCliente(@PathVariable(value = "id") long id)
    {
        Optional<Cliente> cliente = _clienteRepository.findById(id);
        if(cliente.isPresent()){
        	_clienteRepository.delete(cliente.get());
            return new ResponseEntity<>(new Mensagem("Cliente excluido com sucesso."), HttpStatus.OK);
        }
        else
        	return new ResponseEntity<>(new Mensagem("Não existe cliente cadastado com esse ID."), HttpStatus.NOT_FOUND);
    }
}
