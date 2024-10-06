package Com.Furni.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.Furni.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
