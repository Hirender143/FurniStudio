package Com.Furni.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.Furni.entity.Country;

@Repository
public interface CountryRepository  extends JpaRepository<Country, Long>{

}
