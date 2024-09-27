package Com.Furni.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.Furni.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
