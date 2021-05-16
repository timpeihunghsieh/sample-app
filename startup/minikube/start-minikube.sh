#!/usr/bin/env bash

if  ! which kubectl &>/dev/null ; then
    echo "Please install kubectl (>v1.18.0)"
    exit 1
fi

if  ! which minikube &>/dev/null ; then
    echo "Please install minikube (>v1.18.1)"
    exit 1
fi

if  ! which helm &>/dev/null ; then
    echo "Please install helm (>3.0.0)"
    exit 1
fi

if  ! which istioctl &>/dev/null ; then
    echo "Please install istioctl (>1.9.1)"
    exit 1
fi


echo "Starting Minikube..."
minikube start \
    --kubernetes-version=v1.18.0 \
    --vm-driver=virtualbox \
    --cpus=6 \
    --memory=8192 \
    --disk-size=10g

minikube_ip=$(minikube ip)
if [[ -z "${minikube_ip}" ]]; then
    echo "Cannot find minikube. Exiting.."
    exit 1
fi

echo "Starting minikube's ingress addon."
if ! minikube addons enable ingress ; then
    echo "Failed to start minikube's ingress addon."
    exit 1
fi

echo "Setting up /etc/hosts. May ask for password..."
sudo -- sh -c "cp /etc/hosts /tmp/hosts.backup"
sudo -- sh -c "cat /tmp/hosts.backup | grep -v tim.hsieh > /etc/hosts"
sudo -- sh -c "echo '# Bazel entries for tim.hsieh' >> /etc/hosts"
sudo -- sh -c "echo \"${minikube_ip}     prometheus.tim.hsieh\" >> /etc/hosts"
sudo -- sh -c "echo \"${minikube_ip}     grafana.tim.hsieh\" >> /etc/hosts"
sudo -- sh -c "echo \"${minikube_ip}     alertmanager.tim.hsieh\" >> /etc/hosts"
sudo -- sh -c "echo \"${minikube_ip}     kibana.tim.hsieh\" >> /etc/hosts"

echo "Finished Setting up!"