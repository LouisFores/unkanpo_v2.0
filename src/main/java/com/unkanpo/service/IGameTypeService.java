package com.unkanpo.service;

import com.unkanpo.model.Game;
import com.unkanpo.model.Type;

import java.util.List;

public interface IGameTypeService {
    void saveAll(Game game, List<Type> type);
    default void deleteByGame(Game game) {}
}
