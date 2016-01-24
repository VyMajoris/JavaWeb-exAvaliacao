package br.com.fiap.dao;

import java.util.List;

import javax.ejb.Stateless;



public interface Dao<T> {
	void adicionar(T entidade);
	List<T> listar();
	T buscar(int id);
	void removeById(int id);
	void remover(T entity);
	void update(T entity); 
}
