package(default_visibility = ["//visibility:public"])

load("@io_bazel_rules_k8s//k8s:objects.bzl", "k8s_objects")
load("@simulation_k8//:defaults.bzl", "simulation_k8")

# greeter-service

simulation_k8(
    name = "greeter-service-service",
    template = "//deployments/sample-app/greeter-service/greeter-service:service.yaml",
)

simulation_k8(
    name = "greeter-service-deployment",
    images = {
        "greeter-service:latest": "//java/com/sample-app/greeter-service/server:greeter_server_img",
    },
    template = "//deployments/sample-app/greeter-service/greeter-service:deployment.yaml",
)

k8s_objects(
    name = "greeter-service",
    objects = [
        ":greeter-service-service",
        ":greeter-service-deployment",
    ],
)

# Main k8s objects

k8s_objects(
    name = "k8-all",
    objects = [
        ":greeter-service",
    ],
)
