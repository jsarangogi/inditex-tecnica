package com.juanarango.inditex.controller;

import com.juanarango.inditex.domain.model.Brand;
import com.juanarango.inditex.domain.model.Price;
import com.juanarango.inditex.domain.model.Product;
import com.juanarango.inditex.service.PriceService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Test
    void getRequestParamsEndpointTest() throws Exception {
        when(priceService.getPriceByBrandIdProductIdAndDate(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(this.getMockPrice());
        this.mockMvc.perform(get("/price?date=2020-06-14 10:00:00&productId=35455&brandId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.price", is(25.50D)))
                .andExpect(jsonPath("$.brandId", is(1)));
    }

    @Test
    void getURLParamsEndpointTest() throws Exception {
        when(priceService.getPriceByBrandIdProductIdAndDate(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(this.getMockPrice());
        this.mockMvc.perform(get("/price/1/35455/2020-06-14 10:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.price", is(25.50D)))
                .andExpect(jsonPath("$.brandId", is(1)));
    }

    private Price getMockPrice() {
        final Product expectedProduct = Product.builder().id(35455).description("Producto 1").build();
        final Brand expectedBrand = Brand.builder().id(1).description("Zara").build();
        return Price.builder()
                .brand(expectedBrand)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .priceList(1)
                .product(expectedProduct)
                .priority(0)
                .price(25.50D)
                .currency("EUR")
                .build();
    }
}
