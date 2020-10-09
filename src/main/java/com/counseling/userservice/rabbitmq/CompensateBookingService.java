package com.counseling.userservice.rabbitmq;

import com.counseling.userservice.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.transaction.annotation.Transactional;

@EnableBinding(CompensateBookingTransaction.class)
public class CompensateBookingService {

    @Autowired
    UserRepository userRepository;

    @StreamListener(target = CompensateBookingTransaction.BOOKING_USER_COMPENSATION)
    @Transactional
    public void compensateBookingUser(Integer userId){
        System.out.println("The  user is is "+userId);
        userRepository.deleteById(userId);
    }
}
