package com.shophyol.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shophyol.Utility;
import com.shophyol.common.entity.Address;
import com.shophyol.common.entity.Customer;
import com.shophyol.customer.CustomerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("address_book")
	public String showAddressBook(Model model, HttpServletRequest request) {

		Customer customer = getAuthenticatedCustomer(request);
		List<Address> listAddresses = addressService.listAddressBook(customer);

		boolean usePrimaryAddressAsDefault = true;

		for (Address address : listAddresses) {
			if (address.isDefaultForShipping()) {
				usePrimaryAddressAsDefault = false;
				break;
			}
		}

		model.addAttribute("listAddresses", listAddresses);
		model.addAttribute("customer", customer);
		model.addAttribute("usePrimaryAddressAsDefault", usePrimaryAddressAsDefault);

		return "address_book/addresses";

	}

	private Customer getAuthenticatedCustomer(HttpServletRequest request) {

		String email = Utility.getEmailOfAuthenticatedCustomer(request);

		return customerService.getCustomerByEmail(email);
	}

}
