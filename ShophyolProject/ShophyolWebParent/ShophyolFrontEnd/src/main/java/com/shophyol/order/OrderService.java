package com.shophyol.order;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shophyol.checkout.CheckoutInfo;
import com.shophyol.common.entity.Address;
import com.shophyol.common.entity.CartItem;
import com.shophyol.common.entity.Customer;
import com.shophyol.common.entity.order.Order;
import com.shophyol.common.entity.order.OrderDetail;
import com.shophyol.common.entity.order.OrderStatus;
import com.shophyol.common.entity.order.PaymentMethod;
import com.shophyol.common.entity.product.Product;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	public Order createOrder(Customer customer, Address address, List<CartItem> cartItems, PaymentMethod paymentMethod,
			CheckoutInfo checkoutInfo) {

		Order newOrder = new Order();

		newOrder.setOrderTime(new Date());
		newOrder.setStatus(OrderStatus.NEW);
		newOrder.setCustomer(customer);
		newOrder.setProductCost(checkoutInfo.getProductCost());
		newOrder.setSubtotal(checkoutInfo.getProductTotal());
		newOrder.setShippingCost(checkoutInfo.getShippingCostTotal());
		newOrder.setTax(0.0f);
		newOrder.setTotal(checkoutInfo.getPaymentTotal());
		newOrder.setPaymentMethod(paymentMethod);
		newOrder.setDeliverDays(checkoutInfo.getDeliverDays());
		newOrder.setDeliverDate(checkoutInfo.getDeliverDate());

		if (address == null) {
			newOrder.copyAddressFromCustomer();
		} else {
			newOrder.copyShippingAddress(address);
		}

		Set<OrderDetail> orderDetails = newOrder.getOrderDetails();

		for (CartItem item : cartItems) {

			Product product = item.getProduct();

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(newOrder);
			orderDetail.setProduct(product);
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setUnitPrice(product.getDiscountPrice());
			orderDetail.setProductCost(product.getCost() * item.getQuantity());
			orderDetail.setSubtotal(item.getSubtotal());
			orderDetail.setShippingCost(item.getShippingCost());

			orderDetails.add(orderDetail);
		}

		return repo.save(newOrder);
	}

}
