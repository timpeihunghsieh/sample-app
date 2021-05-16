package(default_visibility = ["//visibility:public"])

load("@io_bazel_rules_k8s//k8s:objects.bzl", "k8s_objects")
load("@local_k8//:defaults.bzl", "local_k8")

# url-shorten

local_k8(
    name = "frontend-server-service",
    template = "//deployments/FrontendService/FrontendService:service.yaml",
)

local_k8(
    name = "frontend-server-deployment",
    images = {
        "frontend-server:latest": "//java/com/FrontendServer:frontend-service-img",
    },
    template = "//deployments/FrontendService/FrontendService:deployment.yaml",
)

k8s_objects(
    name = "frontend-server",
    objects = [
        ":frontend-server-service",
        # TODO(timhsieh): re-enable when prometheus is loaded
        #":url-shorten-service-monitor",
        ":frontend-server-deployment",
    ],
)

# Main k8s objects

k8s_objects(
    name = "k8-all",
    objects = [
        ":frontend-server",
    ],
)
