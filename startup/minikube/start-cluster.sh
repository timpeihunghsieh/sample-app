#!/usr/bin/env bash

# Istio Gateway
# ./deployments/istio/startup.sh

# Note! As of April 2021, ELK and Prometheus stack can't be started together
# in Minikube due to lack of resources.
# TODO(timhsieh): configure this so that they can be configured together.

# Prometheus stack
# ./deployments/prometheus-stack/startup.sh

# # ELK
# ./deployments/elk-stack/startup.sh

# Frontend
# bazel run deployments/url-shorten/minikube:k8-all.apply