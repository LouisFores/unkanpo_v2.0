package com.unkanpo.dto;

import java.util.List;

public class GameFormDTO {
    private Long idGame;
    private String nameGame;
    private String descriptionGame;
    private List<String> types;
    private List<String> images;

    public GameFormDTO(Long idGame, String nameGame, String descriptionGame, List<String> types, List<String> images) {
        this.idGame = idGame;
        this.nameGame = nameGame;
        this.descriptionGame = descriptionGame;
        this.types = types;
        this.images = images;
    }

    public GameFormDTO() {
    }

    public Long getIdGame() {
        return idGame;
    }

    public GameFormDTO setIdGame(Long idGame) {
        this.idGame = idGame;
        return this;
    }

    public String getNameGame() {
        return nameGame;
    }

    public GameFormDTO setNameGame(String nameGame) {
        this.nameGame = nameGame;
        return this;
    }

    public String getDescriptionGame() {
        return descriptionGame;
    }

    public GameFormDTO setDescriptionGame(String descriptionGame) {
        this.descriptionGame = descriptionGame;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public GameFormDTO setTypes(List<String> types) {
        this.types = types;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public GameFormDTO setImages(List<String> images) {
        this.images = images;
        return this;
    }
}
