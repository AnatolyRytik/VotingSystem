##CURL examples



## Admin:

#### Actions with restaurants 

#####GET all restaurants:

`curl -s http://localhost:8080/rest/admin/restaurants --user admin@gmail.com:adminpass`
   
#####GET restaurant with id 0:

`curl -s http://localhost:8080/rest/admin/restaurants/0 --user admin@gmail.com:adminpass` 
 
#####DELETE restaurant with id 1:
 
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/1 --user admin@gmail.com:adminpass`
 
#####CREATE restaurant:

`curl -s -X POST -d '{"name":"Subway"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@gmail.com:adminpass`
 
#####UPDATE restaurant:
 
`curl -s -X PUT -d '{"id": 2, "name": "Big Fernand Burgers"}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/restaurants/2 --user admin@gmail.com:adminpass`


#### Actions with Dishes

#####GET all dishes:

`curl -s http://localhost:8080/rest/admin/dishes --user admin@gmail.com:adminpass`

#####GET dish with id 1:

`curl -s http://localhost:8080/rest/admin/dishes/1 --user admin@gmail.com:adminpass`

#####DELETE dish with id 1:

 `curl -s -X DELETE http://localhost:8080/rest/admin/dishes/1 --user admin@gmail.com:adminpass`
 
#####CREATE dish:

`curl -s -X POST -d '{"date":"2020-11-20","name": "Pepsi","price": 150,"restaurantId": 1}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/dishes --user admin@gmail.com:adminpass`

#####UPDATE dish:

`curl -s -X PUT -d '{"id": 2, "date":"2020-11-10", "name":"Chicken burger", "price": 180, "restaurantId" : 2}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/dishes/3 --user admin@gmail.com:adminpass`


##User:

#####GET user vote for date:
`curl -s http://localhost:8080/rest/votes?date=2020-11-24 --user userone@yandex.ru:useronepass`

#####GET votes for restaurant with id 2:

`curl -s http://localhost:8080/rest/votes/votes-by-date/?date=2020-11-24&restaurant_id=2 --user userone@yandex.ru:useronepass`

#####CREATE(UPDATE) vote for restaurant with id 2:

`curl -s -X POST 'http://localhost:8080/rest/votes?restaurant_id=2' -H'Content-Type:application/json;charset=UTF-8' --user userone@yandex.ru:useronepass`

#####GET restaurant by name:

`curl -s http://localhost:8080/rest/restaurants/name?name=MacDonalds --user userone@yandex.ru:useronepass`

#####GET list of restaurants with dishes for today:

`curl -s http://localhost:8080/rest/restaurants/with-dishes --user userone@yandex.ru:useronepass`
