package com.example.shopping.service;

import com.example.shopping.converter.DTOConverter;
import com.example.shopping.model.Shop;
import com.example.shopping.repository.ShopRepository;
import com.example.shoppingclient.dtoProduct.ProductDTO;
import com.example.shoppingclient.dtoProduct.ShopReportDTO;
import com.example.shoppingclient.dtoShop.ItemDTO;
import com.example.shoppingclient.dtoShop.ShopDTO;
import com.example.shoppingclient.dtoUser.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }
    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository
                .findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }
    public List<ShopDTO> getByDate(ShopDTO ShopDTO) {
        List<Shop> shops = shopRepository
                .findAllByDateGreaterThan(ShopDTO.getDate());
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }
    public ShopDTO findById(long ProductId) {
        Optional<Shop> shop = shopRepository.findById(ProductId);
        if (shop.isPresent()) {
            return DTOConverter.convert(shop.get());
        }
        return null;
    }
    public ShopDTO save(ShopDTO shopDTO, String key) {
        UserDTO userDTO = userService
                .getUserByCpf(shopDTO.getUserIdentifier(),key);
        validateProducts(shopDTO.getItems());
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));
        Shop shop = Shop.convert(shopDTO);
        shop.setDate(new Date());
        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    public boolean validateProducts(List<ItemDTO> items){
        for (ItemDTO item: items){
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
            if (productDTO == null){
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }

        return true;
    }

    public List<ShopDTO> getShopsByFilters(
            Date dataInicio,
            Date dataFim,
            Float valorMinimo
            ){
        List<Shop> shops = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(
            Date dataInicio,
            Date dataFim
    ){
        return shopRepository.getReportByDate(dataInicio, dataFim);
    }

}
