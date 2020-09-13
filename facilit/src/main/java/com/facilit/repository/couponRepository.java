package com.facilit.repository;

import com.facilit.model.Coupon;
import org.springframework.data.repository.CrudRepository;

public interface couponRepository extends CrudRepository<Coupon, Long> {
    Coupon findByCouponCode(long couponCode);
}
