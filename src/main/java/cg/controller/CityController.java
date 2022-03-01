package cg.controller;

import cg.model.City;
import cg.model.Country;
import cg.service.ICityService;
import cg.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/city")
public class CityController {
    @Autowired
    private ICityService iCityService;

    @Autowired
    private ICountryService iCountryService;

    @ModelAttribute(value = "countries")
    private Iterable<Country> findAll() {
        return iCountryService.findAll();
    }

    @GetMapping
    public ModelAndView showCity(@PageableDefault(value = 5) Pageable pageable,
                                 @RequestParam Optional<String> search) {
        ModelAndView modelAndView = new ModelAndView("city/list");
        Page<City> cities;
        if (search.isPresent()) {
            cities = iCityService.findAllByName(pageable, search.get());
            modelAndView.addObject("search", search.get());
        } else {
            cities = iCityService.findAll(pageable);
        }
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("city/detail");
        Optional<City> city = iCityService.findOne(id);
        city.ifPresent(value -> modelAndView.addObject("city", value));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PageableDefault(value = 5) Pageable pageable, @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("city/list");
        iCityService.delete(id);
        Page<City> cities = iCityService.findAll(pageable);
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("city") City city,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("city", city);
            return "city/create";
        }
        iCityService.save(city);
        return "redirect:/city";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("city/edit");
        Optional<City> city = iCityService.findOne(id);
        city.ifPresent(value -> modelAndView.addObject("city", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@Valid @ModelAttribute("city") City city,
                              BindingResult bindingResult, Model model,
                              @PathVariable("id") Long id) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("city", city);
            return "city/edit";
        }
        city.setId(id);
        iCityService.save(city);
        return "redirect:/city";
    }
}
