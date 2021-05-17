package(default_visibility = ["//visibility:public"])

load("@io_bazel_rules_k8s//k8s:objects.bzl", "k8s_objects")
load("@simulation_k8//:defaults.bzl", "simulation_k8")

# greeter-server

simulation_k8(
    name = "greeter-server-service",
    template = "//deployments/greeter-server/greeter-server:service.yaml",
)

simulation_k8(
    name = "greeter-server-deployment",
    images = {
        "greeter-server:latest": "//java/com/GreeterServer/server:greeter_server_img",
    },
    template = "//deployments/greeter-server/greeter-server:deployment.yaml",
)

k8s_objects(
    name = "greeter-server",
    objects = [
        ":greeter-server-service",
        ":greeter-server-deployment",
    ],
)

# Main k8s objects

k8s_objects(
    name = "k8-all",
    objects = [
        ":greeter-server",
    ],
)
