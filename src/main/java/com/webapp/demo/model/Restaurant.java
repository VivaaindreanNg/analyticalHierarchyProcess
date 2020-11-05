package com.webapp.demo.model;

public class Restaurant {
	protected String restaurantName;
	protected String cuisineType;
	protected String pricing;
	protected double generalRating;
	protected double taste;
	protected double cleanliness;
	protected double service;
	protected double varietyMeals;
	protected double portionSize;
	

	//Define the "data" of single restaurant here
	public Restaurant(
			String restaurantName, 
			String cuisineType, 
			String pricing,
			double generalRating,
			double taste,
			double cleanliness,
			double service,
			double varietyMeals,
			double portionSize
			) {
		
		this.restaurantName = restaurantName;
		this.cuisineType = cuisineType;
		this.pricing = pricing;
		this.generalRating = generalRating;
		this.taste = taste;
		this.cleanliness = cleanliness;
		this.service = service;
		this.varietyMeals = varietyMeals;
		this.portionSize = portionSize;
	}
	
	
	
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getCuisineType() {
		return cuisineType;
	}
	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}
	public String getPricing() {
		return pricing;
	}
	public void setPricing(String pricing) {
		this.pricing = pricing;
	}
	public double getGeneralRating() {
		return generalRating;
	}
	public void setGeneralRating(double generalRating) {
		this.generalRating = generalRating;
	}
	public double getTaste() {
		return taste;
	}
	public void setTaste(double taste) {
		this.taste = taste;
	}
	public double getCleanliness() {
		return cleanliness;
	}
	public void setCleanliness(double cleanliness) {
		this.cleanliness = cleanliness;
	}
	public double getService() {
		return service;
	}
	public void setService(double service) {
		this.service = service;
	}
	public double getVarietyMeals() {
		return varietyMeals;
	}
	public void setVarietyMeals(double varietyMeals) {
		this.varietyMeals = varietyMeals;
	}
	public double getPortionSize() {
		return portionSize;
	}
	public void setPortionSize(double portionSize) {
		this.portionSize = portionSize;
	}
	
	
	@Override
	public String toString() {
		return "Restaurant [restaurantName=" + restaurantName + ", cuisineType=" + cuisineType + ", pricing=" + pricing
				+ ", generalRating=" + generalRating + ", taste=" + taste + ", cleanliness=" + cleanliness
				+ ", service=" + service + ", varietyMeals=" + varietyMeals + ", portionSize=" + portionSize + "]";
	}
}
