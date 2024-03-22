package com.unkanpo.service.imp;

import com.unkanpo.model.Type;
import com.unkanpo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Set<Type> getTypesByGameId(Long idGame) {
        return gameRepository.findTypesByGameId(idGame);
    }
}
