docker build -t doc-platform-backend:test .

docker stop doc-platform-backend

docker rm doc-platform-backend

docker run --name doc-platform-backend -d -p 8080:8080 doc-platform-backend:test