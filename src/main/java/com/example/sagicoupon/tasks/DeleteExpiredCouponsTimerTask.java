package com.example.sagicoupon.tasks;

import com.example.sagicoupon.services.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Component
@AllArgsConstructor
public class DeleteExpiredCouponsTimerTask extends TimerTask {

    private final CouponService couponService;

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
        couponService.getExpiredCoupons()
                .forEach(coupon -> couponService.deleteCouponById(coupon.getId()));
    }
}
