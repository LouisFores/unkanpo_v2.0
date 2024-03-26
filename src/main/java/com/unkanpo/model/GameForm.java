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
    public List<Type> convertToTypes() {
        String value;
        String nameType;
        Long id;
        List<Type> typeList = new ArrayList<>();
        for (int i = 0; i < types.size(); i+=2) {
            value = types.get(i).replace("Type(idType=","").trim();
            id = Long.parseLong(value);

            nameType = types.get(i+1).replace("nameType=","").replace(")","").trim();
            typeList.add(new Type(id,nameType));
        }
        return typeList;

//        String regexName = "nameType=(.*? )";
//        String regexId = "idType=(.*? ,)";
//        Pattern pattern ;
//
//        List<Type> typeList = new ArrayList<>();
//        String name = null;
//        Long id = Long.parseLong("0");
//        Matcher matcher;
//
//        for (int i = 0; i < types.size(); i+=2) {
//            pattern = Pattern.compile(regexId);
//            matcher = pattern.matcher(types.get(i));
//            if (matcher.find()) {
//                id = Long.valueOf(matcher.group(1).trim());
//            }
//
//            pattern = Pattern.compile(regexName);
//            matcher = pattern.matcher(types.get(i+1));
//            if (matcher.find()) {
//                name = matcher.group(1).trim();
//            }
//
//            typeList.add(new Type(id,name));
//        }
//        return typeList;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
