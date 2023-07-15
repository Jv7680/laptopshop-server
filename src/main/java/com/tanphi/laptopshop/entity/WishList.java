package com.tanphi.laptopshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name="wishlist")
@Getter
@Setter

public class WishList {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Integer wishlistId;
    private String isDelete;

    
    @ManyToOne
    @JoinColumn (name = "accountId", nullable = false,referencedColumnName = "accountId")
    @JsonBackReference
    private Accounts accounts;
    

    @ManyToOne
    @JoinColumn (name = "productId", nullable = false,referencedColumnName = "productId")
    @JsonBackReference
    private Product product;
    
}
