package com.webapp.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.demo.model.Client;
import com.webapp.demo.model.Restaurant;
import com.webapp.demo.service.RestaurantService;

@Controller
public class WebController {
	
	private final String ENDPOINT_SUBMIT_INFO = "/";
	private final String ENDPOINT_RESTAURANT_DATA = "/data";
	private final String ENDPOINT_SHOW_RECOMMENDATION = "/recommend";
	private final String ENDPOINT_SHOW_WEIGHTS = "/weights";
	
	
	//To store clients' submitted request (only process request ONE AT A TIME)
	ArrayList<Client> clientPayload = new ArrayList<Client>();
	
	@Autowired
	RestaurantService restaurantService;
	
	
	@RequestMapping(value=ENDPOINT_SUBMIT_INFO, method=RequestMethod.POST)
	@ResponseBody
	public Client clientSubmitData(@RequestBody Client client) {
			clientPayload.add(client);
		return client;
	}
	
	
	@RequestMapping(value=ENDPOINT_RESTAURANT_DATA, method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<Restaurant> displaySelectedRestaurantData() {
		//Retrieve the most newly added client only
		Client client = clientPayload.get(clientPayload.size() - 1);
		ArrayList<Restaurant> restaurantList = restaurantService.instantiateRestaurantData(client.getPayloadCuisineType());
		
		return restaurantList;
	}
	
	
	@RequestMapping(value=ENDPOINT_SHOW_WEIGHTS, method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Double> displayPriorityWeights() {
		Client client = clientPayload.get(clientPayload.size() - 1);
		double[] clientPreferences = {
				client.getPayloadGeneralRating(), client.getPayloadTaste(), 
				client.getPayloadCleanliness(), client.getPayloadService(), 
				client.getPayloadVarietyMeals(), client.getPayloadPortionSize() 
				};
		double[] criteriaWeights = restaurantService.ahpCalculation(clientPreferences);
		
		//Mapping name of weights to its corresponding value
		Map<String, Double> priorityWeights = new HashMap<String, Double>();
		
		String[] attributesList = {
				"General Rating", "Taste", "Cleanliness",
				"Service", "Variety Meals", "Portion Size"
		};
		
		for (int i = 0; i < criteriaWeights.length; i++)
			priorityWeights.put(attributesList[i], criteriaWeights[i]);
		
		return priorityWeights;
	}
	
	
	@RequestMapping(value=ENDPOINT_SHOW_RECOMMENDATION, method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Double> displayRecommendationResults() {
		
		Client client = clientPayload.get(clientPayload.size() - 1);
		
		//Instantiate restaurant "database"
		ArrayList<Restaurant> restaurantList = restaurantService.instantiateRestaurantData(client.getPayloadCuisineType());
		
		double[] clientPreferences = {
				client.getPayloadGeneralRating(), client.getPayloadTaste(), 
				client.getPayloadCleanliness(), client.getPayloadService(), 
				client.getPayloadVarietyMeals(), client.getPayloadPortionSize() 
				};
		
		//Obtain priority weights via AHP (based on clients' rating)
		double[] criteriaWeights = restaurantService.ahpCalculation(clientPreferences);
		
		//Mapping restaurant names to corresponding recommendation scores
		Map<String, Double> recommendationsResult = new HashMap<String, Double>();
		
		for (Restaurant restaurant: restaurantList) {
			double f_score = restaurantService.neuralNetwork(restaurant, criteriaWeights);
			recommendationsResult.put(restaurant.getRestaurantName(), f_score);
		}
		
		return recommendationsResult;
	}
}
