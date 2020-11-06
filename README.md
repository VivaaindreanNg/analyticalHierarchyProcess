# analyticalHierarchyProcess
Implementing AHP (Analytical Hierarchy Process) in recommending restaurants to client based on restaurant's attributes (cleanliness, price, variety of menus, etc.). 

Here's how it goes: 

* Client choose preferences based on restaurant's features or attributes (prioritize cleanliness? Or prioritize restaurant's service/portion size?) Do so by 
  heading over to Postman and submit a POST request body (with application/json) that specify which restaurant's attribute to prioritize 
  (9 - highest priority, 1 - lowest priority):

```
{
    "payloadCuisineType": "Western",
    "payloadPricing": "medium",
    "payloadGeneralRating": 9,
    "payloadTaste": 8,
    "payloadCleanliness": 5,
    "payloadService": 4,
    "payloadVarietyMeals": 3,
    "payloadPortionSize": 6
}

```



* Based on (dummy) database storing restaurant's data along with users' input preferences, calculate the most suited restaurants to be recommended to users using AHP.

* Return list of restaurants that matches users' preferences as response body. Sample response:


```
{
    "McDonalds": 0.7171428571428571,
    "Spades Burger": 0.7942857142857143,
    "KFC": 0.7297142857142858,
    "E-Hub": 0.9400000000000002
}

```


In above response, AHP recommends E-hub (94%) as the most suitable restaurant based on client's preferences, followed by Spades Burger in 2nd place (79%).


# todos
- [x] Add in endpoint to display the criteria weights (compute based on clients' preferences along with restaurant's attributes).

- [ ] Incorporate persistent db (Mongo/MySQL).

- [ ] Add in front-end instead of solely using Postman to submit HTTP requests. 
