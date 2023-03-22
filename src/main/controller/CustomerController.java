package main.controller;

import java.security.Principal;
//import java.security.Principal;
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

import main.model.Customer;
import main.model.User;
import main.repository.UserRepository;
import main.service.CustomerService;
import main.service.UserService;





/**建構顧客維護功能
 * @author richard
 *
 */
@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@RequestMapping("/customer-home")
	public String showCustomerPage() {
		return "customer-home";
	}
	/**新增客戶
	 * @param model
	 * @return
	 */
	@GetMapping("/add-customer")
	public String showcForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-form";
	}
	
	
	/**填寫客戶表單
	 * @param customer
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/process-customer-form")
	public String showCustomerData(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "customer-form";
		}
		User user = userRepository.findByLogin(principal.getName());
		if(user != null) {
			userService.addCustomerId(customer,principal);
//			model.addAttribute("user", user);
			customerService.saveOrUpdate(customer);
			userService.saveOrUpdate(user);
		}
		return "redirect:/show-customer";
	}
	
	/**view客戶資料清單
	 * @param model
	 * @return
	 */
	@GetMapping("/show-customer")
	public String getCustomers(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "customers";
	}
	
	/**刪除客戶資料
	 * @param customerId
	 * @return
	 */
	@GetMapping("/delete-customer/{customerId}")
	public String deleteCustomer(@PathVariable("customerId") long customerId) {
		Customer customer =  customerService.getById(customerId);
		if(customer != null) {
			customerService.delete(customerId);
		}
		return "redirect:/show-customer";
	}
	
	/**修改客戶資料
	 * @param customerId
	 * @param model
	 * @return
	 */
	@GetMapping("/edit-customer/{customerId}")
	public String editCustomer(@PathVariable("customerId") long customerId, Model model) {
		Customer customer = customerService.getById(customerId);
		if(customer != null) {
			model.addAttribute("customer", customer);
			return "customer-form";
		}
		return "redirect:/show-customer";
	}
	
}

