package br.com.fiap.dao;

import java.util.List;



public interface Dao<T> {
	void adicionar(T entidade);
	List<T> listar();
	T buscar(Long id);
	void removeById(Long id);
	void remover(T entity);
	void update(T entity);
	void saveOrUpdate(T entity); 
}
