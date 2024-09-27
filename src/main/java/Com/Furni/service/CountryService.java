package Com.Furni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Com.Furni.entity.Country;
import Com.Furni.repositry.CountryRepository;



@Service
public class CountryService {
	
	private final CountryRepository countryRepository;

	public CountryService(CountryRepository countryRepository) {
		super();
		this.countryRepository = countryRepository;
	}
	
	
	public void saveCountry(Country country) {
		countryRepository.save(country);
	}
	public Country findCountryById(Long id) {
		
		return countryRepository.findById(id).orElseThrow(()-> new RuntimeException("Country not found!"));
	}
	
	public List<Country> findAll(){
		return countryRepository.findAll();
	}

}
