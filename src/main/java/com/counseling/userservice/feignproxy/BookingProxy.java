package com.counseling.userservice.feignproxy;


import com.counseling.userservice.dto.BookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("booking-service")
@RequestMapping(value = "/booking-service/")
public interface OrderProxy {

    @GetMapping(value = "welcome")
    String welcome();

    @PostMapping("booking/v1/appointment/{customerName}")
    public ResponseEntity<String> makeAppointment
            (@RequestBody BookingDto bookingDto, @PathVariable String customerName);



}
