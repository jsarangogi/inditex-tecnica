package com.juanarango.inditex.service;

import com.juanarango.inditex.domain.model.Price;
import com.juanarango.inditex.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PriceService {

    private final PriceRepository priceRepository;

    public Price getPriceByBrandIdProductIdAndDate(final long brandId, final long productId, final LocalDateTime date) {
        return this.priceRepository.findAllByBrandIdAndProductIdAndDate(brandId, productId, date)
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElse(Price.builder().build());
    }
}
