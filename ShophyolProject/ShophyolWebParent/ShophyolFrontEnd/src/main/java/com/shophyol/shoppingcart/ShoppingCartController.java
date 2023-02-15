package com.shophyol.shoppingcart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shophyol.Utility;
import com.shophyol.common.entity.CartItem;
import com.shophyol.common.entity.Customer;
import com.shophyol.customer.CustomerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService cartService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/cart")
	public String viewCart(Model model, HttpServletRequest request) {

		Customer customer = getAuthenticatedCustomer(request);
		List<CartItem> cartItems = cartService.listCartItems(customer);

		float estimatedTotal = 0.0F;

		for (CartItem items : cartItems) {
			estimatedTotal += items.getSubtotal();
		}

		model.addAttribute("cartItems", cartItems);
		model.addAttribute("estimatedTotal", estimatedTotal);

		return "cart/shopping_cart";
	}

	public Customer getAuthenticatedCustomer(HttpServletRequest request) {

		String email = Utility.getEmailOfAuthenticatedCustomer(request);

		return customerService.getCustomerByEmail(email);
	}
}
