package Testing;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ClassA {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<ClassB> getListClassB() {
		return listClassB;
	}

	public void setListClassB(Set<ClassB> listClassB) {
		this.listClassB = listClassB;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id; 
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="listClassA")
	private Set<ClassB> listClassB = new HashSet<ClassB>();
}
