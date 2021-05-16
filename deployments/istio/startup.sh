#!/usr/bin/env bash

echo "Install istio"
istioctl install --set profile=demo -y
kubectl label namespace default istio-injection=enabled

# Addons such as Kiali
kubectl apply -f deployments/istio/addons

# Istio Gateway
kubectl apply -f deployments/istio/gateway.yaml
kubectl apply -f deployments/istio/virtual-service.yaml

INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
SECURE_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}')
INGRESS_HOST=$(minikube ip)
GATEWAY_URL=$INGRESS_HOST:$INGRESS_PORT
echo "Example test call: "
echo "$ curl http://$GATEWAY_URL"
echo "$ curl \"http://$GATEWAY_URL/?short_url=abc&long_url=http://google.com\""
echo "$ watch 'curl \"http://$GATEWAY_URL/?short_url=abc&long_url=http://google.com\"'"