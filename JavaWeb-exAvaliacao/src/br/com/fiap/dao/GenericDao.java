package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;


public class GenericDao<T> implements Dao<T> {

	private final Class<T> classe;
	protected EntityManager em = JpaUtil.getEntityManager();

	public GenericDao(Class<T> classe) {
		this.classe = classe;
	}

	@Override
	public void removeById(int id){
		System.out.println(em.isOpen());
		em.getTransaction().begin();
		T entity = em.find(classe,id);
		em.remove(entity);
		em.getTransaction().commit();
	}
	@Override
	public void remover(T entity) {
		em.getTransaction().begin();
		//para forçar a entidade ser gerenciada pelo em
		em.merge(entity);
		em.remove(entity);
		em.getTransaction().commit();

	}
	@Override
	public void adicionar(T entidade) {
		
		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();
		
		
	}
	@Override
	public void update(T entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listar() {
		em = JpaUtil.getEntityManager();
		return em.createQuery("From " + classe.getSimpleName()).getResultList();
	}

	@Override
	public T buscar(int id) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		T entidade = em.find(classe, id);
		em.getTransaction().commit();
		

		return entidade;
	}



}
