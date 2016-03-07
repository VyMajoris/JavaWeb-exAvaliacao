package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class GenericDao<T> implements Dao<T> {

	private final Class<T> classe;
	protected EntityManager em = JpaUtil.getEntityManager();
	static SessionFactory sessionFactory = JpaUtil.getHibSession().getSessionFactory();
	
	
	public static Session getSession() throws HibernateException {         
		   Session sess = null;       
		   try {         
		       sess = sessionFactory.getCurrentSession();  
		   } catch (org.hibernate.HibernateException he) {  
		       sess = sessionFactory.openSession();     
		   }             
		   return sess;
		} 

	public GenericDao(Class<T> classe) {
		this.classe = classe;
	}


	@Override
	public void saveOrUpdate(T entity){
		
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(entity);
		transaction.commit();
		session.flush();
		
	}
	public void hibernateDelete(T entity){
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(em.contains(entity) ? entity : em.merge(entity));
		transaction.commit();
		session.flush();
		
	}

	@Override
	public void removeById(Long id){
		em.getTransaction().begin();
		T entity = em.find(classe,id);
		em.remove(entity);
		em.getTransaction().commit();
	}
	@Override
	public void remover(T entity) {
		em.getTransaction().begin();
		//para for�ar a entidade ser gerenciada pelo em
		em.merge(entity);
		em.remove(em.contains(entity) ? entity : em.merge(entity));
		em.getTransaction().commit();

	}
	@Override
	public T adicionar(T entidade) {
		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();
		return entidade;

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
	public T buscar(Long id) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		T entidade = em.find(classe, id);
		em.getTransaction().commit();
		return entidade;
	}
	



}
