package Com.Furni.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Com.Furni.entity.User;

@Repository
public interface SignUpRepository extends JpaRepository<User, Long> {
	
	//FOR LOGIN
	User findByUsername(String username);

	@Query("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
	User findByphoneNumber(@Param("phoneNumber") String phoneNumber);

	@Query(value = "SELECT isadmin FROM user WHERE username = :username", nativeQuery = true)
	Boolean findAdminStatusByUsername(@Param("username") String username);

}
