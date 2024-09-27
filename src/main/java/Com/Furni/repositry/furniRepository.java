package Com.Furni.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import Com.Furni.entity.contactus;

@Repository
public interface furniRepository extends JpaRepository<contactus, Long> {

}
