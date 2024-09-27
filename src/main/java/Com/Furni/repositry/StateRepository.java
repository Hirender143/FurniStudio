package Com.Furni.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.Furni.entity.State;

@Repository
public interface StateRepository  extends JpaRepository<State, Long>{

}
