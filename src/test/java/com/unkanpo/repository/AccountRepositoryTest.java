package com.unkanpo.repository;
import com.unkanpo.service.imp.RentalHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountRepositoryTest {
    @Autowired
    private RentalHistoryService rentalHistoryService;

    @Test
    private void testRental() {
        rentalHistoryService.checkRentalOnline();
    }
}
