docker build -t smart-medical-backend:test .

docker stop smart-medical-backend

docker rm smart-medical-backend

docker run --name smart-medical-backend -d -p 8080:8080 smart-medical-backend:test