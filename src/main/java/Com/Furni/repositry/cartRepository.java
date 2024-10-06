package Com.Furni.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import Com.Furni.entity.Cart;

public interface cartRepository extends JpaRepository<Cart, Long> {

	public Cart findByUserId(Long id);

}

