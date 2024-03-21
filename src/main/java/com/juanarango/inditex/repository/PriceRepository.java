package com.juanarango.inditex.repository;

import com.juanarango.inditex.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "from Price where brand.id = :brandId and product.id = :productId and startDate <= :date and endDate >= :date")
    List<Price> findAllByBrandIdAndProductIdAndDate(@Param("brandId") final long brandId,
                                                    @Param("productId") final long productId,
                                                    @Param("date") final LocalDateTime date);
}
