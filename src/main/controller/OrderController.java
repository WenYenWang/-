package main.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.model.Cart;
import main.model.CartDetail;
import main.model.Order;
import main.model.OrderDetail;
import main.service.CartService;
import main.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService; 
	
	@RequestMapping("/order")
	public String getOrder() {
		return "orders";
	}
	
	@GetMapping("/show-order")
	public String getOrders(Model model) {
		List<Order> orders = orderService.getAll();
		model.addAttribute("orders", orders);
		return "orders";
	}
	
	@GetMapping("/add-order")
	public String showForm(Model model) {
		model.addAttribute("order", new Order());
		return "order-form";
	}
	
	@PostMapping("/process-order-form")
	public String showOrderData(@Valid @ModelAttribute Order order, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "order-form";
		}
		orderService.saveOrUpdate(order);
		return "redirect:show-order";
	}
	@GetMapping("/process-order-form/{cartId}")
	public String showOrderData(@PathVariable long cartId, Model model) {
		Cart cart = cartService.getById(cartId);
		List<CartDetail> cartDetails = cart.getCartDetail();
		BigDecimal total = BigDecimal.valueOf(0);
		for(CartDetail cartDetail: cartDetails) {
			total = total.add(cartDetail.getUnitPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
		}

		model.addAttribute("total", total);
		model.addAttribute("cartDetails", cartDetails);
//orderService.save(order)
//		cartservice.delete(cart) 
		return "show-order-result";
	}
	
	

	@GetMapping("/delete-order/{id}")
	public String deleteOrder(@PathVariable long id) {
		Order order = orderService.getById(id);
		if(order != null) {
			orderService.delete(id);
		}
		return "redirect:/show-order";
	}
	
	@GetMapping("/edit-order/{id}")
	public String editOrder(@PathVariable long id, Model model) {
		Order order = orderService.getById(id);
		if(order != null) {
			model.addAttribute("order", order);
			return "order-form";
		}
		return "redirect:/show-order";
	}
	
	@GetMapping("/checkout/{id}")
	public String checkout(@PathVariable long id) {
		Cart cart = cartService.getById(id);
		
		if(cart != null) {
			Order order = new Order();
			List<OrderDetail> orderDetails = new ArrayList<>();
			BigDecimal total = BigDecimal.valueOf(0);
			order.setCustomerId(cart.getCustomerId());
			order.setEmployeeId(0);
			order.setOrderDate(new Date());
			order.setOrderId(cart.getCartId());
			order.setShippingMethodId(0);
			for(CartDetail cartDetail: cart.getCartDetail()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setDiscount(cartDetail.getDiscount());
				orderDetail.setOrder(order);
				orderDetail.setOrderDetailId(cartDetail.getCartDetailId());
				orderDetail.setOrderId(cartDetail.getCartId());
				orderDetail.setProductId(cartDetail.getProductId());
				orderDetail.setQuantity(cartDetail.getQuantity());
				orderDetail.setUnitPrice(cartDetail.getUnitPrice());
				orderDetails.add(orderDetail);
				total = total.add(cartDetail.getUnitPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
			}
			
	
			order.setTotalPrice(total);
			order.setOrderDetail(orderDetails);
			orderService.saveOrUpdate(order);
			cartService.delete(id);
		}
		return "checkout";
	}
		
	
}
