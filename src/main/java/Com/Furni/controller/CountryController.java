package Com.Furni.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Com.Furni.entity.Country;
import Com.Furni.entity.State;
import Com.Furni.service.CountryService;
import Com.Furni.service.StateService;

@Controller
public class CountryController {
	
	private final CountryService countryService;
	private final StateService stateService;

	
	
	
	
	public CountryController(CountryService countryService, StateService stateService) {
		super();
		this.countryService = countryService;
		this.stateService = stateService;
	}

	@GetMapping("/countries")
	@ResponseBody
	public List<Country> getAllCountry() {
		
		return countryService.findAll();
		
	}
	
	@GetMapping("/create-country")
	@ResponseBody
	public String saveCountry() {
		
		Country country = new Country();
		country.setCountryName("India");
		countryService.saveCountry(country);
		
		State punjab = new State();
		punjab.setStateName("Punjab");
		punjab.setCountry(country);
		stateService.saveState(punjab);
		
		
		State haryana = new State();
		haryana.setStateName("Haryana");
		haryana.setCountry(country);
		stateService.saveState(haryana);
		
		return "saved succesfully!";
		
	}
}
