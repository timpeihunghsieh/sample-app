#!/usr/bin/env bash

kubectl label namespace default metrics-monitoring=target
kubectl create namespace metrics-monitoring
kubectl label namespace metrics-monitoring metrics-monitoring=target
helm install prometheus prometheus-community/kube-prometheus-stack \
  --version "14.0.0" \
  -f deployments/prometheus-stack/values.yaml

kubectl apply -f deployments/prometheus-stack/virtual-service.yaml