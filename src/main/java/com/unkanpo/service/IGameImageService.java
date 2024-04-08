package com.unkanpo.service;

import com.unkanpo.model.GameImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGameImageService {
    void saveAll(List<GameImage> images);

    void save(GameImage background);
}
