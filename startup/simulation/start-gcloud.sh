#!/usr/bin/env bash

PROJECT_ID=elevated-dynamo-310317
CLUSTER_NAME=simulation

gcloud services enable container.googleapis.com --project $PROJECT_ID
gcloud container clusters create $CLUSTER_NAME  \
   --machine-type n1-standard-2  \
   --num-nodes 3  \
   --scopes "https://www.googleapis.com/auth/source.read_write,cloud-platform"  \
   --zone us-east1-b \
   --project $PROJECT_ID \
   --enable-ip-alias

# Set up proxy only subnet
# https://cloud.google.com/load-balancing/docs/l7-internal/proxy-only-subnets#example
gcloud compute networks subnets create new-l7ibackend-subnet-us-east1-b \
     --purpose INTERNAL_HTTPS_LOAD_BALANCER \
     --role ACTIVE \
     --region us-east1 \
     --range 10.18.8.0/23 \
     --network default \
     --project $PROJECT_ID