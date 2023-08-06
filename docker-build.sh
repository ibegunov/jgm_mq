# docker rmi credit-app:latest
eval $(minikube docker-env)
docker build --no-cache -t credit-app .