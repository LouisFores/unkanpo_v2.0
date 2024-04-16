package com.unkanpo.service;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IGameService {
    Iterable<GameForm> findAll();
    GameForm findGameById(Long id);
    void delete(Game game);
    Iterable<GameForm> findAllByName_game(String key_word);
    GameForm findById(Long id);

    GameForm save(GameForm gameForm) throws IOException;
    GameForm save(GameForm gameForm, List<MultipartFile> images) throws IOException, Exception;

}