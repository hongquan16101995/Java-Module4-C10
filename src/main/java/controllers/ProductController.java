package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @GetMapping("/greeting")
    public ModelAndView getView(){
        ModelAndView modelAndView = new ModelAndView("view");
        return modelAndView;
    }
}
