package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import main.model.CartDetail;
import main.model.Product;
import main.repository.CartRepository;
import main.repository.UserRepository;
import main.service.CartDetailService;
import main.service.CartService;
import main.service.CustomerService;
import main.service.ProductService;

@Controller
public class CarDetailController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;	
	@Autowired
	private CartDetailService cartDetailService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CartRepository cartRepository;
    
	@GetMapping("/delete-cartdetail/{cartDetailId}")
	public String deleteProducts(@PathVariable long cartDetailId) {
		CartDetail cartDetail = cartDetailService.getById(cartDetailId);
		if(cartDetail != null) {
			cartDetailService.delete(cartDetailId);
		}
		return "redirect:/show-cartdetail";
	}
	
	@GetMapping("/edit-cartdetail/{cartDetailId}")
	public String editProducts(@PathVariable long cartDetailId) {
		CartDetail cartDetail = cartDetailService.getById(cartDetailId);
		if(cartDetail != null) {
			String url = "/show-cartform/" + cartDetail.getProductId();
			cartDetailService.delete(cartDetailId);
//			model.addAttribute("cartDetail", cartDetail);
			return "redirect:" + url;
		}
		return "redirect:/show-cartdetail";
	}
}