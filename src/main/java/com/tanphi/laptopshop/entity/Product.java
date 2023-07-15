package com.tanphi.laptopshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="product")
public class Product {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) code dc them 1507
    private Integer productId;
    private String productName;
    private Integer quantity;
    private String image;
    private Integer unitprice;
    private Integer discount;
    private String description;
    private Integer isdeleted;
    private String cpu;
    private String cores;
    private String threads;
    private String cpuspeed;
    private String cache;
    private String storagecapacity;
    private String storagetype;
    private String ram;
    private String ramtype;
    private String rambusspeed;
    private String screensize;
    private String screenresolution;
    private String screenrefreshrate;
    private String graphicscard;
    private String audiotechnology;
    private String weight;
    private String casingmaterial;
    private String battery;
    private String operatingsystem;
    private Integer soldquantity;
    private Integer releasedate;
    private String producer;
    
    public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Integer unitprice) {
		this.unitprice = unitprice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Set<Cart> getCartSet() {
		return cartSet;
	}

	public void setCartSet(Set<Cart> cartSet) {
		this.cartSet = cartSet;
	}

	public Set<OrderDetail> getOrderDetailSet() {
		return orderDetailSet;
	}

	public void setOrderDetailSet(Set<OrderDetail> orderDetailSet) {
		this.orderDetailSet = orderDetailSet;
	}

	public Set<Reviews> getReviewsSet() {
		return reviewsSet;
	}

	public void setReviewsSet(Set<Reviews> reviewsSet) {
		this.reviewsSet = reviewsSet;
	}

	public String getCpu() {
		return cpu;
		}

		public void setCpu(String cpu) {
		this.cpu = cpu;
		}

		public String getCores() {
		return cores;
		}

		public void setCores(String cores) {
		this.cores = cores;
		}

		public String getThreads() {
		return threads;
		}

		public void setThreads(String threads) {
		this.threads = threads;
		}

		public String getCpuspeed() {
		return cpuspeed;
		}

		public void setCpuspeed(String cpuspeed) {
		this.cpuspeed = cpuspeed;
		}

		public String getCache() {
		return cache;
		}

		public void setCache(String cache) {
		this.cache = cache;
		}

		public String getStoragecapacity() {
		return storagecapacity;
		}

		public void setStoragecapacity(String storagecapacity) {
		this.storagecapacity = storagecapacity;
		}

		public String getStoragetype() {
		return storagetype;
		}

		public void setStoragetype(String storagetype) {
		this.storagetype = storagetype;
		}

		public String getRam() {
		return ram;
		}

		public void setRam(String ram) {
		this.ram = ram;
		}

		public String getRamtype() {
		return ramtype;
		}

		public void setRam_type(String ramtype) {
		this.ramtype = ramtype;
		}

		public String getRambusspeed() {
		return rambusspeed;
		}

		public void setRambusspeed(String rambusspeed) {
		this.rambusspeed = rambusspeed;
		}

		public String getScreensize() {
		return screensize;
		}

		public void setScreen_size(String screensize) {
		this.screensize = screensize;
		}

		public String getScreenresolution() {
		return screenresolution;
		}

		public void setScreen_resolution(String screenresolution) {
		this.screenresolution = screenresolution;
		}

		public String getScreenrefreshrate() {
		return screenrefreshrate;
		}

		public void setScreenrefreshrate(String screenrefreshrate) {
		this.screenrefreshrate = screenrefreshrate;
		}

		public String getGraphicscard() {
		return graphicscard;
		}

		public void setGraphicscard(String graphicscard) {
		this.graphicscard = graphicscard;
		}

		public String getAudiotechnology() {
		return audiotechnology;
		}

		public void setAudiotechnology(String audiotechnology) {
		this.audiotechnology = audiotechnology;
		}

		public String getWeight() {
		return weight;
		}

		public void setWeight(String weight) {
		this.weight = weight;
		}

		public String getCasingmaterial() {
		return casingmaterial;
		}

		public void setCasingmaterial(String casingmaterial) {
		this.casingmaterial = casingmaterial;
		}

		public String getBattery() {
		return battery;
		}

		public void setBattery(String battery) {
		this.battery = battery;
		}

		public String getOperatingsystem() {
		return operatingsystem;
		}

		public void setOperatingsystem(String operatingsystem) {
		this.operatingsystem = operatingsystem;
		}
		


	public Integer getSoldquantity() {
			return soldquantity;
		}

		public void setSoldquantity(Integer soldquantity) {
			this.soldquantity = soldquantity;
		}





	public Integer getReleasedate() {
			return releasedate;
		}

		public void setReleasedate(Integer releasedate) {
			this.releasedate = releasedate;
		}





	/**
		 * @return the producer
		 */
		public String getProducer() {
			return producer;
		}

		/**
		 * @param producer the producer to set
		 */
		public void setProducer(String producer) {
			this.producer = producer;
		}





	@ManyToOne
    @JoinColumn(name = "supplierId", nullable = false,referencedColumnName = "supplierId")
    @JsonBackReference
    private Supplier supplier;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Cart> cartSet;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<OrderDetail> orderDetailSet;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Reviews> reviewsSet;
    
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<WishList> wishListEntities;
}
