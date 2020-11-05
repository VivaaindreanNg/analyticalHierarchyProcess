# analyticalHierarchyProcess
Implementing AHP (Analytical Hierarchy Process) in recommending restaurants to client based on restaurant's attributes (cleanliness, price, variety of menus, etc.). 

Here's how it goes: 

* Client choose preferences based on restaurant's features or attributes (prioritize cleanliness? Or prioritize cheaper price?)

* Based on (dummy) database storing restaurant's data along with users' input preferences, calculate the most suited restaurants to be recommended to users.

* Return list of restaurants that matches users' preferences. 


# todos
- [x] Add in endpoint to display the criteria weights (compute based on clients' preferences along with restaurant's attributes).

- [ ] Incorporate persistent db (Mongo/MySQL).

- [ ] Add in front-end instead of solely using Postman to submit HTTP requests. 
