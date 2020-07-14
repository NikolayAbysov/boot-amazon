package com.boot.amazon.repository;

import com.boot.amazon.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductIdFromSource(String productIdFromSource);

    @Query(value = "select product_id_from_source "
            + "from (select product_id_from_source, count(product_id_from_source) as s "
            + "      from public.review "
            + "               left join product p on review.product_id = p.id "
            + "      group by product_id_from_source "
            + "      order by s desc "
            + "      limit 1000) as pifss", nativeQuery = true)
    List<String> getTopThousandMostCommentedProducts();
}
