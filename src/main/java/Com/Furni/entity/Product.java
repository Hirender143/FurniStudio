package Com.Furni.entity;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String itemName;

	private String itemPrice;

	private String itemDescription;
	
	
	//Temporary for file storation
	@Transient        // THIS IS USE FOR TO GET MULTIPART WORK IN REQUEST , THIS PROPERTY DON'T INTERACT OR SAVE IN DATABASE
	private MultipartFile imageFile;
	

	
	

	@Column(name = "item_image", columnDefinition = "LONGBLOB")
	@Lob
	private byte[] itemImage;
	
	private String itemImageType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public byte[] getItemImage() {
		return itemImage;
	}

	public void setItemImage(byte[] itemImage) {
		this.itemImage = itemImage;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getItemImageType() {
		return itemImageType;
	}

	public void setItemImageType(String itemImageType) {
		this.itemImageType = itemImageType;
	}
	
	
	

}
