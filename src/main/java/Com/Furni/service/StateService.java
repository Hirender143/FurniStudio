package Com.Furni.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Com.Furni.entity.Country;
import Com.Furni.entity.State;
import Com.Furni.repositry.StateRepository;

@Service
public class StateService {
	
	private final StateRepository stateRepository;
	
	public StateService(StateRepository stateRepository) {
		super();
		this.stateRepository = stateRepository;
	}
	
	
	public void saveState(State state) {
		stateRepository.save(state);
	}
	public State findStateById(Long id) {
		
		return stateRepository.findById(id).orElseThrow(()-> new RuntimeException("State not found!"));
	}
	
	public List<State> findAll(){
		return stateRepository.findAll();
	}

}
