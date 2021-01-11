package br.com.quinelato.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.quinelato.entity.Cidade;
import br.com.quinelato.service.CidadeService;

@RestController
public class CidadeController {

	@Autowired
    private CidadeService _cidadeService;

    @RequestMapping(value = "/cidade", method = RequestMethod.GET)
    public ResponseEntity findAllCidades() {
        return _cidadeService.findAllCidades();
    }
    
    @RequestMapping(value = "/cidade", method =  RequestMethod.POST)
    public Cidade save(@Valid @RequestBody Cidade cidade)
    {
        return _cidadeService.save(cidade);
    }
        
    @RequestMapping(value = "/cidade/nome/{nome}", method = RequestMethod.GET)
    public ResponseEntity findByNome(@PathVariable(value = "nome") String nome)
    {
        return _cidadeService.findByNome(nome);
    }

    @RequestMapping(value = "/cidade/estado/{estado}", method = RequestMethod.GET)
    public ResponseEntity findByEstado(@PathVariable(value = "estado") String estado)
    {
        return _cidadeService.findByEstado(estado);        
    }    

}
