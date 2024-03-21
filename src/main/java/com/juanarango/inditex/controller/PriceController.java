package com.juanarango.inditex.controller;

import com.juanarango.inditex.domain.model.Price;
import com.juanarango.inditex.domain.vm.ProductFinalPriceVM;
import com.juanarango.inditex.service.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/price")
public class PriceController {

    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final PriceService priceService;

    @GetMapping
    @ResponseBody
    public ProductFinalPriceVM getProductFinalPriceUsingRequestParams(@RequestParam @DateTimeFormat(pattern = DATETIME_PATTERN) final LocalDateTime date,
                                                                      @RequestParam final long productId,
                                                                      @RequestParam final long brandId) {
        return this.queryToDatabaseAndBuildModel(brandId, productId, date);
    }

    @ResponseBody
    @GetMapping("/{brandId}/{productId}/{date}")
    public ProductFinalPriceVM getProductFinalPriceUsingPathParams(@PathVariable("brandId") final long brandId,
                                                                   @PathVariable("productId") final long productId,
                                                                   @PathVariable("date") @DateTimeFormat(pattern = DATETIME_PATTERN) final LocalDateTime date) {
        return this.queryToDatabaseAndBuildModel(brandId, productId, date);
    }

    private ProductFinalPriceVM queryToDatabaseAndBuildModel(final long brandId, final long productId, final LocalDateTime date) {
        final Price price = this.priceService.getPriceByBrandIdProductIdAndDate(brandId, productId, date);
        return ProductFinalPriceVM.fromModel(price);
    }
}
