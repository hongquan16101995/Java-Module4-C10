package controllers;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping
    public ModelAndView getAll(){
        return new ModelAndView("create");
    }

//    @PostMapping("/greeting")
//    public ModelAndView getView(@RequestParam("name") String name){
//        ModelAndView modelAndView = new ModelAndView("index");
//        modelAndView.addObject("ten", name);
//        return modelAndView;
//    }

    @PostMapping("/greeting")
    public ModelAndView getView(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("ten", product.getName());
        modelAndView.addObject("gia", product.getPrice());
        return modelAndView;
    }

    @PostMapping("/greeting1")
    public ModelAndView getView1(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/view")
    public String getName(@RequestParam("name") String name,@RequestParam("mess") String mess, Model model){
        model.addAttribute("ten", name);
        model.addAttribute("mess", mess);
        return "index";
    }

    @GetMapping("/view1")
    public String getName1(@RequestParam("name") String name, @RequestParam("mess") String mess, ModelMap modelMap){
        modelMap.addAttribute("ten", name);
        modelMap.addAttribute("mess", mess);
        return "index";
    }
}
