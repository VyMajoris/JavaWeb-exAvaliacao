package Testing;

import br.com.fiap.dao.GenericDao;

public class Teste {
	public static void main(String[] args) {
		GenericDao<ClassA> classADao = new GenericDao<ClassA>(ClassA.class);
		GenericDao<ClassB> classBDao = new GenericDao<ClassB>(ClassB.class);
		
		ClassA classA = new ClassA();
		ClassB b1 = new ClassB();
		ClassB b2 = new ClassB();
		ClassB b3 = new ClassB();
		
		classA.getListClassB().add(b1);
		classA.getListClassB().add(b2);
		classA.getListClassB().add(b3);
		classADao.adicionar(classA);
	}

}
