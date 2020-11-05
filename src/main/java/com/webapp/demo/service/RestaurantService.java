package com.webapp.demo.service;

import java.util.ArrayList;

import com.webapp.demo.model.Restaurant;

public interface RestaurantService {
	
	//Create objects/restaurant of data based on cuisine types selected by clients
	public abstract ArrayList<Restaurant> instantiateRestaurantData(String payloadCuisineType);
	
	//Receive data from client for AHP calculation
	public abstract double[] ahpCalculation(double[] clientPreferences);
	
	//Compute f_score
	public abstract double neuralNetwork(Restaurant restaurant, double[] criteriaWeights);
}
