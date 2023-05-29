package com.generation.games_store.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private static Long id;

	@OneToMany(mappedBy = "categoria")
	@JsonIgnoreProperties
	private ArrayList<produto> produtos = new ArrayList<>();

	@NotBlank
	@Size
	private String descricao;

	public categoria(Long id, ArrayList<produto> produtos, @NotBlank @Size String descricao) {
		super();
		this.id = id;
		this.produtos = produtos;
		this.descricao = descricao;
	}

	public static Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<produto> produtos) {
		this.produtos = produtos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
