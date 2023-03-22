package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.OrderDetail;
import main.service.OrderDetailService;

@Controller
public class OrderDetailController {

	@Autowired
	private OrderDetailService orderDetailService;
	
	@GetMapping("/showOrderDetail/orderId}")
	public String showOrderDetail(@PathVariable long orderId, Model model) {
		OrderDetail order = orderDetailService.getById(orderId);
		if(order != null) {
			model.addAttribute("order", order);
			return "orderDetail";
		}
		return "redirect:/showOffer";
	}
	
	@GetMapping("/editOrderDetail/{orderId}")
	public String editOrderDetail(@PathVariable long orderId, Model model) {
		OrderDetail order = orderDetailService.getById(orderId);
		if(order != null) {
			model.addAttribute("orderDetails", order.getOrderDetailId());
			return "form-orderDetail";
		}
		return "redirect:/showOffer";
	}
	
	@PostMapping("/processFormOrderDetails")
	public String processOrderDetailData(@ModelAttribute OrderDetail orderDetail) {
		orderDetailService.saveOrUpdate(orderDetail);
		return "redirect:/showOffer";
	}
	
}
