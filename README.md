hello-spring-data-jpa
=====================

## Running the app

You can run the app with embedded DB using:

```bash
mvn spring-boot:run
```

You can also run the app with PostgreSQL:
```bash
docker compose up
```

You can run the app with embedded DB on minikube using:
```bash
docker build -t hello-spring-data-jpa .
minikube image load hello-spring-data-jpa:latest
kubectl apply -f k8s/app-deployment.yaml
kubectl port-forward deployment/hello-spring-data-jpa-app-deployment 8080:8080 &
```

## Testing the app

You can test the running app using curl:

```bash
curl -X POST -H "Content-Type:application/json" -d '{"title":"DB config", "text":"Switch to PostreSQL"}' http://localhost:8080/todos
curl -X POST -H "Content-Type:application/json" -d '{"title":"Improve REST API", "text":"Use Srping HATEOAS"}' http://localhost:8080/todos
curl -X GET http://localhost:8080/todos
curl -X GET http://localhost:8080/todos/<id>
curl -X PUT -H "Content-Type:application/json" -d '{"title":"Improve REST API", "text":"Use Spring HATEOAS"}' http://localhost:8080/todos/<id>
```

