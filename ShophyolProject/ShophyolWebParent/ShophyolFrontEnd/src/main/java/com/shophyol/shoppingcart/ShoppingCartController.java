package com.shophyol.shoppingcart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shophyol.Utility;
import com.shophyol.address.AddressService;
import com.shophyol.common.entity.Address;
import com.shophyol.common.entity.CartItem;
import com.shophyol.common.entity.Customer;
import com.shophyol.common.entity.ShippingRate;
import com.shophyol.customer.CustomerService;
import com.shophyol.shipping.ShippingRateService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService cartService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ShippingRateService shipService;

	@GetMapping("/cart")
	public String viewCart(Model model, HttpServletRequest request) {

		Customer customer = getAuthenticatedCustomer(request);
		List<CartItem> cartItems = cartService.listCartItems(customer);

		float estimatedTotal = 0.0F;

		for (CartItem items : cartItems) {
			estimatedTotal += items.getSubtotal();
		}

		Address defaultAddress = addressService.getDefaultAddress(customer);
		ShippingRate shippingRate = null;
		boolean usePrimaryAddressAsDefault = false;

		if (defaultAddress != null) {
			shippingRate = shipService.getShippingRateForAddress(defaultAddress);
		} else {
			usePrimaryAddressAsDefault = true;
			shippingRate = shipService.getShippingRateForCustomer(customer);
		}

		model.addAttribute("usePrimaryAddressAsDefault", usePrimaryAddressAsDefault);
		model.addAttribute("shippingSupported", shippingRate != null);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("estimatedTotal", estimatedTotal);

		return "cart/shopping_cart";
	}

	public Customer getAuthenticatedCustomer(HttpServletRequest request) {

		String email = Utility.getEmailOfAuthenticatedCustomer(request);

		return customerService.getCustomerByEmail(email);
	}
}
