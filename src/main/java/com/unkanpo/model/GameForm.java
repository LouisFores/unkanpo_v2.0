package com.unkanpo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameForm {
    private Game game;

    private List<String> types;
    public GameForm() {
    }
// default constructor when post data form to controller
    public GameForm(Game game, List<String> types) {
        this.game = game;
        this.types = types;
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
}
