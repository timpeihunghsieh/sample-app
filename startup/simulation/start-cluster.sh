#!/usr/bin/env bash

# Istio Gateway
./deployments/istio/startup.sh

# Prometheus stack
./deployments/prometheus-stack/startup.sh

# # ELK
./deployments/elk-stack/startup.sh

bazel run deployments/sample-app/greeter-service/simulation:k8-all.apply
bazel run deployments/sample-app/frontend-service/simulation:k8-all.apply
