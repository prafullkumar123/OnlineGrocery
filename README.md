# OnlineGrocery
Online Grocery spring boot application

## Create Costumer 
curl --location 'http://localhost:8080/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "PK",
"email": "pk@gmail.com",
"address": "HYD",
"phone": "0293405058"
}'

## Get Costumer
curl http://localhost:8080/customers

## Create Grocery
curl --location 'http://localhost:8080/grocery-items' \
--header 'Content-Type: application/json' \
--data '{
"name": "Apple",
"category": "Fruits",
"price": 100.50,
"quantity": 5
}'

## Get Grocery
curl --location 'http://localhost:8080/grocery-items'

## Create an Order
curl --location 'http://localhost:8080/orders' \
--header 'Content-Type: application/json' \
--data '{
"customer": {
"id": 1
},
"groceryItems": [
{
"id": 1
},
{
"id": 2
}
]
}'