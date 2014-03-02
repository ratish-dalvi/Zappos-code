This project contains the source code for the programming challenge given by Zappos Inc.
The project takes "number of products" and " total price" as input from the user and displays a set of combinations of products which are closest to the total Price. The default term for search currently is "Boots", but it can be changed easily in the file Zappos.java

Details about the code:
The main function is present in the file Zappos.java. This is the entry point for the application. It prompts the user for input on console and prints the output on console.

Files:

Zappos.java: Entry point for the application. It prompts the user for input on console, uses the API to fetch results(currently the default term is "boots", but it can be set by changing the field "term" in this file).


ZappoProduct.java - This contains the business object to hold the properties of each product. Each object of this class identifies a product.

CombinedProducs - this contains an array holding the indices of products and their sum. Each object of this class identifies a combination which is checked for closeness to the total price

CalculateProductSet : The main algorithm for filtering the results lies here.
This Class contains code for picking up the top 5(default) results of combinations which are closest to the given total price. 
The number of results is 5 default. It can be configured by changing numOfResults field in this class.




