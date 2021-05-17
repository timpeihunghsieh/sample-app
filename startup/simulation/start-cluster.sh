#!/usr/bin/env bash

# Istio Gateway
./deployments/istio/startup.sh

# Prometheus stack
./deployments/prometheus-stack/startup.sh

# # ELK
# ./deployments/elk-stack/startup.sh

bazel run deployments/greeter-server/simulation:k8-all.apply
bazel run deployments/FrontendService/simulation:k8-all.apply
