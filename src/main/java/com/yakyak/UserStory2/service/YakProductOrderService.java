package com.yakyak.UserStory2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yakyak.UserStory2.data.Herd;
import com.yakyak.UserStory2.dto.Order;
import com.yakyak.UserStory2.dto.StockDto;

@Service
public class YakProductOrderService {

	@Autowired
	YakService yakService;
	
	public StockDto servOnlyAvailableProducts(Order request, Herd myHerd, int day){
		StockDto herdStock = sellYakMilk(yakService.getTotalStock(myHerd, day), request.getOrder().getMilk());
		return sellYakSkin(herdStock, request.getOrder().getSkins());
		
	}
	
	private StockDto sellYakMilk(StockDto stock, double amount){
		if(stock.getMilk() >= amount){
			stock.setMilk(amount);
		}else
			stock.setMilk(null);
		
		return stock;
	}
	
	private StockDto sellYakSkin(StockDto stock, int amount){
		if(stock.getSkins() >= amount){
			stock.setSkins(amount);
		}else
			stock.setSkins(null);
		
		return stock;
	}
	
}
