package com.unkanpo.service.imp;

import com.unkanpo.dto.RentalTokenDTO;
import com.unkanpo.model.GameAccount;
import com.unkanpo.model.RentalHistory;
import com.unkanpo.model.User;
import com.unkanpo.repository.RentalHistoryRepository;
import com.unkanpo.service.IRentalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalHistoryService implements IRentalHistoryService {
    @Autowired
    private RentalHistoryRepository rentalHistoryRepository;
    @Autowired
    private TokenService tokenService;
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
        rental.startRenting();
        rentalHistoryRepository.save(rental);
        String[] infoLogin = gameAccount.getHideInfo().split("_");
        RentalTokenDTO tokenDTO = new RentalTokenDTO();
        tokenDTO.setRentId(rentalHistoryRepository.findTopByUserAndGameAccountOrderByIdRentalDesc(user,gameAccount).get().getIdRental());
        tokenDTO.setToken(tokenService.createToken(infoLogin[0],infoLogin[1]));
        return tokenDTO;
    }
}
