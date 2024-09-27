package Com.Furni.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
 
@Entity
public class Country { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	 
	private String countryName; // countr-01 (id =1, name = "india") -> s1, s2
	
//	@OneToMany
//	@JoinTable(name = "country_state")
//	private List<State> states = new ArrayList<>(); /*
//													 * table -> col1 (country_id) | col2 (state_id) 01 | s1 01 | s2
//													 */
	
	@OneToMany(mappedBy = "country")
	private List<State> states = new ArrayList<>();
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	

}
