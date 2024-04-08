package com.unkanpo.repository;

import com.unkanpo.model.GameImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameImageRepository extends JpaRepository<GameImage,Long> {
}
