package com.unkanpo.model;

import org.springframework.web.multipart.MultipartFile;

import com.unkanpo.dto.GameFormDTO;

import java.util.ArrayList;
import java.util.List;


public class GameForm {
    private Game game;
    private List<String> types;
    private List<String> images;
    private MultipartFile background;
    public GameForm() {
    }
// default constructor when post data form to controller
    public GameForm(Game game, List<String> types) {
        this.game = game;
        this.types = types;
    }

    public GameForm(Game game) {
        this.game = game;
    }

    public GameForm(Game game, List<String> types, MultipartFile background) {
        this.game = game;
        this.types = types;
        this.background = background;
    }

    public GameForm(Game game, List<String> types, List<String> images) {
        this.game = game;
        this.types = types;
        this.images = images;
    }

    // constructor when get data to view form
    public GameForm(List<Type> typeList,Game game) {
        List<String> types = new ArrayList<>();

        for (Type type : typeList) {
            types.add(type.convert());
        }
        this.game = game;
        this.types = types;
    }
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
    public boolean checkType(String type) {
        boolean isExist = false;
        for (String check : this.types) {
            if (type.equals(check)) {
                isExist = true;
            }
        }
        return isExist;
    }

    public MultipartFile getBackground() {
        return background;
    }

    public void setBackground(MultipartFile background) {
        this.background = background;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public GameFormDTO gameFromDTO() {
        GameFormDTO gameFormDTO = new GameFormDTO();
        gameFormDTO.setIdGame(this.getGame().getIdGame())
                .setNameGame(this.game.getNameGame())
                .setDescriptionGame(this.game.getDescriptionGame())
                .setTypes(this.getTypes())
                .setImages(this.getImages());
        return gameFormDTO;
    }
}
