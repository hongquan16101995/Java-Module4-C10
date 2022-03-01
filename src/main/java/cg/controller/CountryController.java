package cg.controller;

import cg.model.City;
import cg.model.Country;
import cg.service.ICityService;
import cg.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping(value = "/country")
public class CountryController {
    @Autowired
    private ICityService iCityService;

    @Autowired
    private ICountryService iCountryService;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping
    public ModelAndView showCountry() {
        ModelAndView modelAndView = new ModelAndView("country/list");
        Iterable<Country> countries = iCountryService.findAll();
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView showCityByCountry(@PageableDefault(value = 5) Pageable pageable, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("country/list-city");
        Page<City> cities;
        Optional<Country> country = iCountryService.findOne(id);
        if (country.isPresent()) {
            cities = iCityService.findAllByCountry(pageable, country.get());
            modelAndView.addObject("country", country.get());
        } else {
            cities = iCityService.findAll(pageable);
        }
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PageableDefault(value = 5) Pageable pageable, @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("country/list");
        iCountryService.delete(id);
        Iterable<Country> countries = iCountryService.findAll();
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("country/create");
        modelAndView.addObject("country", new Country());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("country") Country country,
                                BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("country", country);
            return "country/create";
        }

        MultipartFile multipartFile = country.getFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(country.getFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        country.setEnsignUrl("image/" + fileName);

        iCountryService.save(country);
        return "redirect:/country";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("country/edit");
        Optional<Country> country = iCountryService.findOne(id);
        country.ifPresent(value -> modelAndView.addObject("country", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@Valid @ModelAttribute("country") Country country,
                              BindingResult bindingResult, Model model,
                              @PathVariable("id") Long id) throws IOException {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("country", country);
            return "country/edit";
        }

        MultipartFile multipartFile = country.getFile();
        String fileName = multipartFile.getOriginalFilename();
        FileCopyUtils.copy(country.getFile().getBytes(), new File(fileUpload + fileName));

        country.setId(id);
        iCountryService.save(country);
        return "redirect:/country";
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView getIOException() {
        return new ModelAndView("error");
    }
}
