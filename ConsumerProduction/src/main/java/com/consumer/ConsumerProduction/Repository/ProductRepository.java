package com.consumer.ConsumerProduction.Repository;

import com.consumer.ConsumerProduction.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/*
 *****Redis repository
 */
@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {

}
