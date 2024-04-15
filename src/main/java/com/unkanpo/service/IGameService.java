package com.unkanpo.service;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGameService {
    Iterable<GameForm> findAll();
    GameForm findGameById(Long id);
    void delete(Game game);
    public GameForm save(GameForm gameForm, List<MultipartFile> images);
}