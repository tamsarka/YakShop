package com.yakyak.UserStory2.controller;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yakyak.UserStory2.component.YakComponent;
import com.yakyak.UserStory2.data.Herd;
import com.yakyak.UserStory2.dto.HerdResponse;
import com.yakyak.UserStory2.dto.Order;
import com.yakyak.UserStory2.dto.StockDto;
import com.yakyak.UserStory2.service.YakProductOrderService;
import com.yakyak.UserStory2.service.YakService;

@RestController
public class LabYakController {

	@Autowired
	YakService yakService;
	@Autowired
	YakProductOrderService orderService;
	@Autowired
	YakComponent resourceLoader;
	Herd herd = null;
	
	// WEB API for  User STORY 1
	@RequestMapping(value="/herd/{day}", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_XML_VALUE)
	public StockDto loadData(@RequestBody Herd requestBody, @PathVariable int day){
		StockDto stock = yakService.getTotalStock(requestBody, day);
		return stock ;
	}
	
	// WEB API for  User STORY 2
	@GetMapping(value="/yak-shop/stock/{t}")
	public StockDto getStockData(@PathVariable int t) throws Exception{
		Herd herd = loadHerData(); 
		StockDto stock = yakService.getTotalStock(herd, t +1);
		return stock ;
	}
	
	@GetMapping(value="/yak-shop/herd/{t}")
	public HerdResponse getHerdData(@PathVariable int t) throws Exception{
		Herd herd = loadHerData(); 
		HerdResponse stock = yakService.getHerdData(herd, t);
		return stock ;
	}
	
	// WEB API for  User STORY 3
	@PostMapping(value="/yak-shop/order/{day}")
	public ResponseEntity<StockDto> loadData(@RequestBody Order orderRequest, @PathVariable int day, HttpServletResponse response) throws JAXBException{
		Herd herd = loadHerData(); 
		StockDto stock = orderService.servOnlyAvailableProducts(orderRequest, herd, day);
		if(stock.getMilk() == null || stock.getSkins() == null){
			return new ResponseEntity<StockDto>(stock, HttpStatus.PARTIAL_CONTENT);
		}else
			return new ResponseEntity<StockDto>(stock, HttpStatus.CREATED);
	}
	
	private Herd loadHerData() throws JAXBException{
		if(herd == null){
			herd = resourceLoader.getBeanAfterConfiguringFromXml();
		}
		
		return herd;
		
	}
}
