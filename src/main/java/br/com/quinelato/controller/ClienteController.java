package br.com.quinelato.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.quinelato.entity.Cliente;
import br.com.quinelato.service.ClienteService;


@RestController
public class ClienteController {

	@Autowired
    private ClienteService _clienteService;

    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public ResponseEntity findAllClientes() {
        return _clienteService.findAllClientes();
    }

    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findById(@PathVariable(value = "id") long id)
    {
       return _clienteService.findById(id);        
    }
    
    @RequestMapping(value = "/cliente/nome/{nome}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findByNome(@PathVariable(value = "nome") String nome)
    {
       return _clienteService.findByNome(nome);        
    }

    @RequestMapping(value = "/cliente", method =  RequestMethod.POST)
    public ResponseEntity save(@Valid @RequestBody Cliente cliente)
    {
        return _clienteService.save(cliente);
    }

    @RequestMapping(value = "/cliente/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Cliente> alterarRegistro(@PathVariable(value = "id") long id, @Valid @RequestBody Cliente newcliente)
    {
         return _clienteService.alterarRegistro(id, newcliente);
        
    }

    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletarCliente(@PathVariable(value = "id") long id)
    {
        return _clienteService.deletarCliente(id);
    }
}
