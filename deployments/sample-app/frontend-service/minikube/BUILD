package(default_visibility = ["//visibility:public"])

load("@io_bazel_rules_k8s//k8s:objects.bzl", "k8s_objects")
load("@local_k8//:defaults.bzl", "local_k8")

# frontend service

local_k8(
    name = "frontend-service-service",
    template = "//deployments/sample-app/frontend-service/frontend-service:service.yaml",
)

local_k8(
    name = "frontend-service-deployment",
    images = {
        "frontend-service:latest": "//java/com/sample-app/frontend-service:frontend-service-img",
    },
    template = "//deployments/sample-app/frontend-service/frontend-service:deployment.yaml",
)

local_k8(
    name = "frontend-service-virtual-service",
    template = "//deployments/sample-app/frontend-service/frontend-service:virtual-service.yaml",
)

k8s_objects(
    name = "frontend-service",
    objects = [
        ":frontend-service-service",
        # TODO(timhsieh): re-enable when prometheus is loaded
        #":url-shorten-service-monitor",
        ":frontend-service-deployment",
        ":frontend-service-virtual-service",
    ],
)

# Main k8s objects

k8s_objects(
    name = "k8-all",
    objects = [
        ":frontend-service",
    ],
)
