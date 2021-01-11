package br.com.quinelato.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "cliente") 
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	@Column(name="nome", nullable = false, length = 100)
    private String nome;
    
	@Column(name="sexo", nullable = false, length = 20)
    private String sexo;
    	
    @Column(name="dt_nascimento", nullable = false)
    private Date dataNascimento;
    
	@Column(name="idade", nullable = false)
    private Integer idade;
    
	@OneToOne    
	private Cidade cidade;    

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	  
    
}
