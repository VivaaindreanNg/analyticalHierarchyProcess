package com.webapp.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.webapp.demo.model.Restaurant;


@Service
public class RestaurantServiceImplementation implements RestaurantService {

	@Override
	public ArrayList<Restaurant> instantiateRestaurantData(String payloadCuisineType) {
		
		ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
		
		if (payloadCuisineType.equalsIgnoreCase("Western")) {
			Restaurant spadesBurger = new Restaurant(
					"Spades Burger", "Western", "high", 4.0, 4.5, 3.6, 4.0, 2.0, 4.5);
			Restaurant kfc = new Restaurant(
					"KFC", "Western", "medium", 4.5, 3.4, 3.6, 3.0, 2.0, 4.0);
			Restaurant mcD = new Restaurant(
					"McDonalds", "Western", "medium", 3.9, 4.3, 3.2, 3.7, 2.0, 3.2);
			Restaurant eHub = new Restaurant(
					"E-Hub", "Western", "high", 4.9, 4.7, 4.8, 4.8, 4.2, 4.5);
			
			restaurantList.add(spadesBurger);
			restaurantList.add(kfc);
			restaurantList.add(mcD);
			restaurantList.add(eHub);
		}
		
		else if (payloadCuisineType.equalsIgnoreCase("Eastern")) {
			Restaurant sushiKing = new Restaurant(
					"Sushi King", "Eastern", "high", 3.6, 4.7, 4.0, 4.0, 4.0, 3.8);
			Restaurant fancyMee = new Restaurant(
					"Fancy Mee", "Eastern", "medium", 4.1, 3.6, 4.1, 3.8, 3.5, 3.5);
			Restaurant econRice = new Restaurant(
					"33 economy rice", "Eastern", "low", 3.3, 3.8, 3.5, 3.5, 4.2, 4.6);
			Restaurant bak = new Restaurant(
					"Golden Dragon Bak Kut Teh", "Eastern", "medium", 
					2.8, 4.2, 3.6, 3.7, 3.0, 3.9);
			
			restaurantList.add(sushiKing);
			restaurantList.add(fancyMee);
			restaurantList.add(econRice);
			restaurantList.add(bak);
		}
		
		else {
			Restaurant homeyEatery = new Restaurant(
					"Homey Eatery", "Fusion", "low", 4.1, 3.9, 3.3, 3.0, 3.2, 3.2);
			Restaurant squareBox = new Restaurant(
					"Squarebox", "Fusion", "medium", 3.0, 4.0, 3.6, 3.8, 4.9, 3.6);
			
			restaurantList.add(homeyEatery);
			restaurantList.add(squareBox);
		}
		
		return restaurantList;
	}
	
	
	
	@Override
	public double[] ahpCalculation(double[] clientPreferences) {
		//Receive data from client for AHP calculation
		//double[] clientPreferences = {
				//payloadGeneralRating, payloadTaste, payloadCleanliness,
				//payloadService, payloadVarietyMeals, payloadPortionSize };
		
		double[][] clientAHPMatrix = new double[clientPreferences.length][clientPreferences.length];
		
		//Comparison pair-wise matrix
		for (int row = 0; row < clientPreferences.length; row++) {
			for (int col = 0; col < clientPreferences.length; col++) {
				clientAHPMatrix[row][col] = clientPreferences[row]/clientPreferences[col];
			}
		}
		
		//Normalization of AHP matrix
		double[] sumByCol = new double[clientPreferences.length];
		for (int row = 0; row < clientPreferences.length; row++) {
			double sum = 0.0;
			for (int col = 0; col < clientPreferences.length; col++) {
				// Obtain the sum for every column
				sum += clientAHPMatrix[col][row];
			}
			sumByCol[row] = sum;
		}
		
		for (int row = 0; row < clientPreferences.length; row++) {
			for (int col = 0; col < clientPreferences.length; col++) {
				clientAHPMatrix[row][col] = clientAHPMatrix[row][col] / sumByCol[col];
			}
		}
		
		//Compute criteria weights (Criteria weights should summed up to approx 1.0 across all attributes)
		double[] criteriaWeights = new double[clientPreferences.length];
		for (int row = 0; row < clientPreferences.length; row++) {
			double weights = 0.0;
			for (int col = 0; col < clientPreferences.length; col++) {
				weights += clientAHPMatrix[row][col];
			}
			weights /= clientPreferences.length;
			criteriaWeights[row] = weights;
		}
		
		return criteriaWeights;
	}
	
	
	@Override
	public double neuralNetwork(Restaurant restaurant, double[] criteriaWeights) {
			
			//Scale back each rating for restaurant, from 1 to 5 and scale into 0 into 1. 
			restaurant.setGeneralRating(restaurant.getGeneralRating()/5);
			restaurant.setTaste(restaurant.getTaste()/5);
			restaurant.setCleanliness(restaurant.getCleanliness()/5);
			restaurant.setService(restaurant.getService()/5);
			restaurant.setVarietyMeals(restaurant.getVarietyMeals()/5);
			restaurant.setPortionSize(restaurant.getPortionSize()/5);
			
			double[] f_attributes = { 
					restaurant.getGeneralRating(), restaurant.getTaste(), 
					restaurant.getCleanliness(), restaurant.getService(),
					restaurant.getVarietyMeals(), restaurant.getPortionSize()
			};
			
			double f_score = 0.0;
			
			for (int i = 0; i < criteriaWeights.length; i++)
				f_score += f_attributes[i] * criteriaWeights[i];
			
			return f_score;
		}
}
