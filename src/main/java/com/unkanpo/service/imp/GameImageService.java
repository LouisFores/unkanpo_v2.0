package com.unkanpo.service.imp;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameImage;
import com.unkanpo.repository.GameImageRepository;
import com.unkanpo.service.IGameImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameImageService implements IGameImageService {
    @Autowired
    private GameImageRepository gameImageRepository;
    @Override
    public void saveAll(List<GameImage> images) {
        gameImageRepository.saveAll(images);
    }

    @Override
    public void save(GameImage background) {
        gameImageRepository.save(background);
    }

    @Override
    @Transactional
    public void deleteByGame(Game game) {
        gameImageRepository.deleteByGame(game);
    }

    @Override
    public int getQuantityImageByGame(Game game) {
        return gameImageRepository.countByGame(game);
    }
}
