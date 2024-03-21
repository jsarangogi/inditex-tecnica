package com.juanarango.inditex.domain.vm;

import com.juanarango.inditex.domain.model.Price;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductFinalPriceVM(long productId, long brandId, long priceList, LocalDateTime startDate, LocalDateTime endTime, double price) {

    public static ProductFinalPriceVM fromModel(final Price model) {
        return ProductFinalPriceVM.builder()
                .productId(model.getProduct().getId())
                .brandId(model.getBrand().getId())
                .priceList(model.getPriceList())
                .startDate(model.getStartDate())
                .endTime(model.getEndDate())
                .price(model.getPrice())
                .build();
    }
}
