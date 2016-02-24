package Testing;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class ClassB {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<ClassA> getListClassA() {
		return listClassA;
	}

	public void setListClassA(Set<ClassA> listClassA) {
		this.listClassA = listClassA;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id; 
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinTable(name="CLASSA_CLASSB", joinColumns =
	 {@JoinColumn(name="CLASSA_id", nullable=false, updatable=false)},
	 inverseJoinColumns = {@JoinColumn(name="CLASSB_ID", nullable=false,
	updatable=false)}) 
	private Set<ClassA> listClassA = new HashSet<ClassA>();
}
