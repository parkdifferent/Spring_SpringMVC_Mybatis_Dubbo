package com.szkingdom.ssm.repository;

import com.szkingdom.ssm.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by phoenix on 2017/3/30.
 */
public interface OrderRepository extends MongoRepository<Order,String> {

    List<Order> findByCustomer(String c);

    List<Order> findByCustomerLike(String c);

    List<Order> findByCustomerAndType(String c,String t);

    List<Order> findByCustomerLikeAndType(String c,String t);

    @Query("{'customer': ?0, 'type': ?1}")
    List<Order> findUserOrder(String username,String type);
}
