
package com.unkanpo.controller;

import com.unkanpo.model.Type;
import com.unkanpo.service.imp.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
@RequestMapping("/admin/types")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("")
    public ModelAndView showListType() {
        ModelAndView modelAndView = new ModelAndView("/type/list");
        modelAndView.addObject("types", typeService.findAll());
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
        typeService.save(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateType(@PathVariable Long id) {
        Type type = typeService.findById(id);
        if (type != null) {
            ModelAndView modelAndView = new ModelAndView("/type/update");
            modelAndView.addObject("type", type);
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update")
    public String updateType(@ModelAttribute("type") Type type) {
        typeService.save(type);
        return "redirect:/admin/types";
    }
}