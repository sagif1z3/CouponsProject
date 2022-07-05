package com.example.sagicoupon.tasks;

import com.example.sagicoupon.model.Coupon;
import com.example.sagicoupon.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class DeleteExpiredCouponsTimerTask extends TimerTask {
    private final CouponService couponService;
    private final Date currentDate;

    @Autowired
    public DeleteExpiredCouponsTimerTask(CouponService couponService) {
        this.couponService = couponService;
        this.currentDate = new Date();
    }

    @PostConstruct
    private void initTimerTask() {
        Timer timer = new Timer();
        timer.schedule(this, getExecutionDate(), 86400000);
//        timer.schedule(this, 0, 5000);
    }

    private static Date getExecutionDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @Override
    public void run() {
        List<Coupon> set = couponService.getAllCoupons();
        set
                .stream()
                .filter(coupon -> coupon.getEndDate().before(currentDate))
                .forEach(coupon -> couponService.deleteCouponById(coupon.getId()));
    }
}
