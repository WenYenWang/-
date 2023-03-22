package main.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.Cart;
import main.model.CartDetail;
import main.model.CartForm;
import main.model.Customer;
import main.model.User;
import main.repository.CartRepository;
import main.repository.UserRepository;
import main.service.CartDetailService;
import main.service.CartService;
import main.service.CustomerService;
import main.service.ProductService;

@Controller
public class CartController {

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
	
	@GetMapping("/show-cartdetail")
	public String showCartDetail(@Valid @ModelAttribute CartForm cartForm, BindingResult bindingResult, Model model, Principal principal) {
		User user = userRepository.findByLogin(principal.getName());
		Customer customer = customerService.getByUserId(user.getUserId());
	    Cart cart = cartRepository.getByCustomerId(customer.getCustomerId());
    	List<CartDetail> cartDetail = cartDetailService.getByCartId(cart.getCartId());
		model.addAttribute("cartdetail",cartDetail);
		return "cart";
	}

    @PostMapping("/cart-form")
    public String CartForm(@Valid @ModelAttribute CartForm cartForm, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "cart-Form";
        }
        cartService.addCartDetail(cartForm,principal);
        return "redirect:/show-cartdetail/";
    }  
}