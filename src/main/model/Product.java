package main.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "product")
@Proxy(lazy=false)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;
	
	@NotNull(message = "{product.brand.notnull}")
	@Column(name = "brand")
	private String brand;
	
	@NotBlank(message = "{product.name.notblank}")
	@Column(name = "product_name")
	private String name;

	@Column(name = "product_image")
	private String productImage;
	
	@Column(name = "product_description")
	private String productDescription;

	@NotNull(message = "{product.price.notnull}")
	@DecimalMin(value="0", message = "{product.price.min}")
	@Column(name = "product_price")
	private BigDecimal productPrice;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	@NotFound(action=NotFoundAction.IGNORE)
	private List<CartDetail> cartDetail;
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public List<CartDetail> getCartDetail() {
		return cartDetail;
	}

	public void setCartDetail(List<CartDetail> cartDetail) {
		this.cartDetail = cartDetail;
	}
	
}
