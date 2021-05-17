#!/usr/bin/env bash

kubectl create namespace logs-monitoring
kubectl apply -f https://download.elastic.co/downloads/eck/1.5.0/all-in-one.yaml
sleep 5  # TODO(timhsieh): hack to wait for CRD to be deployed. 
kubectl apply -f deployments/elk-stack/filebeat_autodiscover.yaml --namespace logs-monitoring
