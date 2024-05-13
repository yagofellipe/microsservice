package com.example.shopping.model;

import com.example.shoppingclient.dtoShop.ShopDTO;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userIdentifier;
    private float total;
    private Date date;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> items;

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static Shop convert(ShopDTO ShopDTO){
        Shop shop = new Shop();
        shop.setUserIdentifier(ShopDTO.getUserIdentifier());
        shop.setTotal(ShopDTO.getTotal());
        shop.setDate(ShopDTO.getDate());
        shop.setItems(ShopDTO
                .getItems()
                .stream()
                .map(Item::convert)
                .collect(Collectors.toList()));

        return shop;
    }
}
