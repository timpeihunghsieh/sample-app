#!/usr/bin/env bash

# Istio Gateway
./deployments/istio/startup.sh

# Note! As of April 2021, ELK and Prometheus stack can't be started together
# in Minikube due to lack of resources.
# TODO(timhsieh): configure this so that they can be configured together.

# Prometheus stack
# ./deployments/prometheus-stack/startup.sh

# # ELK
# ./deployments/elk-stack/startup.sh

# Greeter Server
bazel run deployments/greeter-server/minikube:k8-all.apply

# Frontend
bazel run deployments/FrontendService/minikube:k8-all.apply