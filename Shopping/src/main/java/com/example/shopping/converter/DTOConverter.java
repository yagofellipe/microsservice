package com.example.shopping.converter;

import com.example.shopping.model.Item;
import com.example.shopping.model.Shop;

import com.example.shoppingclient.dtoShop.ItemDTO;
import com.example.shoppingclient.dtoShop.ShopDTO;

import java.util.stream.Collectors;

public class DTOConverter {
    public static ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(
                item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }
    public static ShopDTO convert(Shop shop) {
        ShopDTO ShopDTO = new ShopDTO();
        ShopDTO.setUserIdentifier(shop.getUserIdentifier());
        ShopDTO.setTotal(shop.getTotal());
        ShopDTO.setDate(shop.getDate());
        ShopDTO.setItems(shop
                .getItems()
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList()));
        return ShopDTO;
    }
}
