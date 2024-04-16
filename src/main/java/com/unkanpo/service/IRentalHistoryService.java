package com.unkanpo.service;


import com.unkanpo.dto.RentalTokenDTO;
import com.unkanpo.model.GameAccount;
import com.unkanpo.model.User;

public interface IRentalHistoryService {
    RentalTokenDTO startRent(User user, GameAccount gameAccount) throws Exception;
}
