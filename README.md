# CarService
Simple Java based RESTful service using Jersey + Spring + JPA-Hibernate

Instructions to build the war file:
- Unzip the zip file.
- Go to car directory.
- Run mvn clean install.
- Put car.war file in tomcat and start the tomcat.
- Please also start redis on default 6379 port.

1. This RESTful service is created using Jersey + Spring + JPA-Hibernate + Maven.
2. It also makes use of Redis cache to cache the results.
      - This is on-access cache. First time get request will fetch data from DB and put in the cache.
      - Subsequent requests will fetch data from cache until ttl (5 minutes).
      - Updates will update the cache entry as well. Deletes will delete entry from cache.
3. Using in-memory hsql DB for this assignment.
      - It will create required tables on the fly.


# API details:

1. Create Car entry:
    - POST call
    - URL: http://localhost:8080/car
    - Headers:
        - Content-Type: application/json
        - Accept: application/json
    - make, model, year, engine are mandatory fields. It will throw error if any one of these fields is missing.
    - Any new field, other than mandatory fields and attributes, will be ignored.
    - Any error will have error message and status code: 10000
    Sample payload:
    {
        "make": "Ford",
        "model": "Fiesta",
        "year": 2012,
        "engine": 5000,
        "attributes": [
          {
            "key": "year_of_import", 
            "value": 2010
          }
        ]
    }

2. Get Car details:
    - GET call
    - URL: http://localhost:8080/car/{car_id}
    - Headers:
        - Content-Type: application/json
        - Accept: application/json
    - Will give status code 404 if entity is not found

3. Update Car details:
    - PUT call
    - URL: http://localhost:8080/car
    - Headers:
        - Content-Type: application/json
        - Accept: application/json
    - Will give status code 404 if entity is not found
    - Any other error will have error message and status code: 10001
    - Sample Payload:
    {
        "id": 1,
        "make": "ABC",
        "model": "PQR",
        "year": 2015,
        "engine": 500000,
        "attributes": [
            {
                "id": 2,
                "key": “year_of_import",
                "value": "123"
            },
            {
                "key": "rty",
                "value": "456"
            }
        ]
}

4. Delete Car details:
    - DELETE call
    - URL: http://localhost:8080/car/{car_id}
    - Headers:
        - Content-Type: application/json
        - Accept: application/json
    - Will give status code 404 if entity is not found
    - Any other error will have error message and status code: 10002


