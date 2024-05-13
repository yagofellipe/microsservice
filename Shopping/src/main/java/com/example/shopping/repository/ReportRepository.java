package com.example.shopping.repository;

import com.example.shopping.model.Shop;
import com.example.shoppingclient.dtoProduct.ShopReportDTO;

import java.util.Date;
import java.util.List;

public interface ReportRepository {

    public List<Shop> getShopByFilters(
            Date dataInicio,
            Date dataFim,
            Float valorMinimo);

    public ShopReportDTO getReportByDate(
            Date dataInicio,
            Date dataFim);

}
