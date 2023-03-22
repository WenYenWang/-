package main.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Cart;
import main.model.CartDetail;
import main.model.CartForm;
import main.model.Customer;
import main.model.Product;
import main.model.User;
import main.repository.CartDetailRepository;
import main.repository.CartRepository;
import main.repository.ProductRepository;
import main.repository.UserRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService{

	@Autowired
	private ProductService productService;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartDetailRepository cartDetailRepository;
	@Autowired
	private CartDetailService cartDetailService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private CustomerService customerService;
	
	@Override
	public List<Cart> getAll() {
		return cartRepository.findAll();
	}

	@Override
	public Cart getById(long cartId) {
		return cartRepository.findById(cartId).orElse(null);
	}

	@Override
	public void saveOrUpdate(Cart cart) {
		cartRepository.saveAndFlush(cart);
	}

	@Override
	public void delete(long cartId) {
		cartRepository.deleteById(cartId);
	}

	@Override
	public Cart getByCustomerId(long customerId) {
		return cartRepository.getByCustomerId(customerId);
	}

	@Override
	public void addCartDetail(CartForm cartForm, Principal principal) {
		User user = userRepository.findByLogin(principal.getName());
		Customer customer = customerService.getByUserId(user.getUserId());
		long customerId = customer.getCustomerId();
		
	    Cart cart = cartRepository.getByCustomerId(customerId);
	    Product product = productService.getById(cartForm.getProductId());
	    List<CartDetail> cartDetails = new ArrayList<>();
        CartDetail cartDetail = new CartDetail();

        cartDetail.setQuantity(cartForm.getQuantity());
        cartDetail.setUnitPrice(product.getProductPrice());
        cartDetail.setTotalPrice(product.getProductPrice().multiply(BigDecimal.valueOf(cartForm.getQuantity())));
        cartDetail.setDiscount(BigDecimal.valueOf(0.9));
        cartDetail.setProductId(cartForm.getProductId());
        
        if (cart == null) {
        	cartDetails.add(cartDetail);
            cart = new Cart();
            cart.setCustomerId(customerId);
            cart.setCartDetail(cartDetails);
            cartRepository.save(cart);
        	cartDetail.setCartId(cart.getCartId());
//            product.getCartDetail().clear();
            product.getCartDetail().addAll(cartDetails);
            productRepository.save(product);
        	cartDetail.setCart(cart);
        	cartDetail.setProduct(product);
	        cartDetailService.saveOrUpdate(cartDetail);
        } else {
        	List<CartDetail> userCarts = cartDetailService.getByCartId(cart.getCartId());
        	int cartFormQuantity = cartForm.getQuantity();
        	boolean update = false;
        	for (CartDetail userCart : userCarts) {
        		if (userCart.getProductId() == product.getProductId()) {
                	cartFormQuantity += userCart.getQuantity();
                	userCart.setQuantity(cartFormQuantity);
        	        cartDetailService.saveOrUpdate(userCart);
        	        update = true;
        		}
//        		break;
            }
        	if(update == false) {
	        	cartDetails.add(cartDetail);
	            cart.getCartDetail().addAll(cartDetails);
	        	cartDetail.setCartId(cart.getCartId());
	            product.getCartDetail().addAll(cartDetails);
	        	cartDetail.setCart(cart);
	        	cartDetail.setProduct(product);
		        cartDetailService.saveOrUpdate(cartDetail);
        	}
        }
	}
}