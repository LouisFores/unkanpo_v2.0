package com.unkanpo.controller;

import com.unkanpo.model.Type;
import com.unkanpo.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/types")
public class TypeController {
    @Autowired
    private TypeRepository typeRepository;
    @GetMapping("")
    public ModelAndView showListType() {
        ModelAndView modelAndView = new ModelAndView("/type/list");
        modelAndView.addObject("types", typeRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateType() {
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createType(@ModelAttribute("type") Type type) {
        typeRepository.save(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateType(@PathVariable Long id) {
        Optional<Type> type = typeRepository.findById(id);
        if (type.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/update");
            modelAndView.addObject("type", type.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update")
    public String updateType(@ModelAttribute("type") Type type) {
        typeRepository.save(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/remove/{id}")
    public String removeType(@PathVariable Long id) {
        Optional<Type> typeOptional = typeRepository.findById(id);
        if (!typeOptional.isPresent()) {
            return "/error_404";
        }
        typeRepository.delete(typeOptional.get());
        return "redirect:/admin/types";
    }



}
