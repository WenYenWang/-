package main.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.service.ProductService;

@Controller
public class HomeController {
	
    @Autowired
    private ProductService productService;
    
    @RequestMapping("/")
	public String getHome(Model model,Principal principal) {
//    	List<Product> products = productService.getAll();
//    	model.addAttribute("products", products);
    	if (principal != null) {
    		System.out.println("username:" + principal.getName());
    	}
	    return "index";
  }
}