package com.example.shopping.controller;

import com.example.shopping.service.ShopService;
import com.example.shoppingclient.dtoProduct.ShopReportDTO;
import com.example.shoppingclient.dtoShop.ShopDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;
    @GetMapping("/shopping")
    public List<ShopDTO> getShops() {
        List<ShopDTO> produtos = shopService.getAll();
        return produtos;
    }
    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(
            @PathVariable String userIdentifier) {
        List<ShopDTO> produtos =
                shopService.getByUser(userIdentifier);
        return produtos;
    }
    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO ShopDTO) {
        List<ShopDTO> produtos = shopService.getByDate(ShopDTO);
        return produtos;
    }
    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }
    @PostMapping("/shopping")
    public ShopDTO newShop(@RequestHeader(name = "key", required = true) String key,
                           @RequestBody ShopDTO ShopDTO) {
        return shopService.save(ShopDTO, key);
    }


    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(
            @RequestParam(name = "dataInicio", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @RequestParam(name = "dataFim", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
            @RequestParam(name = "valorMinimo", required = false)
            Float valorMinimo
    ) {
        return shopService.getShopsByFilters(dataInicio, dataFim, valorMinimo);
    }


    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(
            @RequestParam(value = "dataInicio", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @RequestParam(value = "dataFim", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim
    ){
        return shopService.getReportByDate(dataInicio, dataFim);
    }
}
