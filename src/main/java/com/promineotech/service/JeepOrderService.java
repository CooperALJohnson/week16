/**
 * 
 */
package com.promineotech.service;

import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;

public interface JeepOrderService {


  Order createOrder(OrderRequest orderRequest);

}
