package com.generation.games_store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "tb_produtos")
public class produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Esse atributo é de preenchimento obrigatório")
	@Size(min = 5, max = 100, message = "Este atributo tem que ter no mínimo 5 caracteres e no máximo 100 caracteres.")
	private String nomeProduto;

	@NotNull
	@Positive
	private Double preco;

	@NotBlank
	@Size
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	@JsonIgnoreProperties
	private categoria categoria;
	

	public produto(Long id,
	@NotBlank(message = "Esse atributo é de preenchimento obrigatório") @Size(min = 5, max = 100, message = "Este atributo tem que ter no mínimo 5 caracteres e no máximo 100 caracteres.") String nomeProduto,
	@NotNull @Positive Double preco, @NotBlank @Size String descricao,
	com.generation.games_store.model.categoria categoria) {
	super();
	this.id = id;
	this.nomeProduto = nomeProduto;
	this.preco = preco;
	this.descricao = descricao;
	this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(categoria categoria) {
		this.categoria = categoria;
	}

}
