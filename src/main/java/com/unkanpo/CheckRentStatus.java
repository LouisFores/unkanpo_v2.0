//package com.unkanpo;
//
//import com.unkanpo.service.imp.RentalHistoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CheckRentStatus {
//    @Autowired
//    private RentalHistoryService rentalService;
//    @Scheduled(fixedRate = 60*60*5)
//    public void performTask() {
//        rentalService.checkRentalOnline();
//    }
//}