package com.unkanpo.service;


import com.unkanpo.model.GameAccount;
import com.unkanpo.model.User;

public interface IRentalHistoryService {
    void startRent(User user, GameAccount gameAccount) throws Exception;
}
