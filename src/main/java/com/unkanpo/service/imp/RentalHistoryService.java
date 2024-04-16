package com.unkanpo.service.imp;

import com.unkanpo.dto.RentalTokenDTO;
import com.unkanpo.model.GameAccount;
import com.unkanpo.model.RentalHistory;
import com.unkanpo.model.User;
import com.unkanpo.repository.RentalHistoryRepository;
import com.unkanpo.service.IRentalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RentalHistoryService implements IRentalHistoryService {
    @Autowired
    private RentalHistoryRepository rentalHistoryRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AccountService accountService;
    // stack is time to deduct coin in one hour, is five minutes
    @Override
    public RentalTokenDTO startRent(User user, GameAccount gameAccount) throws Exception {
        int coin = user.getCoin();
        int price = gameAccount.getPrice();
        int stack = 60/5;
        if (coin < price*stack) {
            throw new Exception("Tài khoản phải có ít nhất " + (price*stack) + "coin để bắt đầu thuê (thời gian chơi trong 1h)");
        }
        RentalHistory rental = new RentalHistory(user, gameAccount);
        String[] infoLogin = gameAccount.getHideInfo().split("_");
        RentalTokenDTO tokenDTO = new RentalTokenDTO();
        GameAccount account = gameAccount;

        rental.startRenting();
        rentalHistoryRepository.save(rental);

        tokenDTO.setRentId(rentalHistoryRepository.findTopByUserAndGameAccountOrderByIdRentalDesc(user,gameAccount).get().getIdRental());
        tokenDTO.setToken(tokenService.createToken(infoLogin[0],infoLogin[1]));
        account.setIsRent(true);
        accountService.save(account);
        return tokenDTO;
    }

    public void updateRentStatus(Long idRental) {
        RentalHistory rental = rentalHistoryRepository.findById(idRental).get();

        rental.updateStatus();
        int price = rental.getGameAccount().getPrice();
        rental.totalCoin(price);
        rentalHistoryRepository.save(rental);
    }

    public void stopRent(Long idRental) {
        RentalHistory rental = rentalHistoryRepository.findById(idRental).get();
        GameAccount account = accountService.findById(rental.getGameAccount().getIdAccount()).get();

        rental.updateStatus();
        rental.endRent();
        int price = rental.getGameAccount().getPrice();
        rental.totalCoin(price);
        rentalHistoryRepository.save(rental);

        account.setIsRent(false);
        accountService.save(account);
    }

    public void checkRentalOnline() {
        Iterable<RentalHistory> rental = rentalHistoryRepository.findAllActiveRentalHistories();
        LocalDateTime now = LocalDateTime.now();

        for (RentalHistory rentalHistory : rental) {
            LocalDateTime lastOnline = LocalDateTime.parse(rentalHistory.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Duration duration = Duration.between(lastOnline, now);

            int minutes = (int) duration.toMinutes();
            if (minutes > 7) {
                RentalHistory rentAfk = rentalHistory;
                GameAccount account = accountService.findById(rentAfk.getGameAccount().getIdAccount()).get();

                int price = rentalHistory.getGameAccount().getPrice();
                rentAfk.endRent();
                rentAfk.totalCoin(price);
                rentalHistoryRepository.save(rentAfk);

                account.setIsRent(false);
                accountService.save(account);
            }
        }
    }
}
