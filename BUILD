package(default_visibility = ["//visibility:public"])

load("@rules_proto_grpc//java:defs.bzl", "java_proto_library")
load("@rules_proto_grpc//java:defs.bzl", "java_grpc_library")

proto_library(
    name = "greeter_grpc",
    srcs = ["greeter.proto"],
    deps = [
        "@com_google_protobuf//:any_proto",  # Well-known
    ],
)

java_grpc_library(
    name = "greeter_java_grpc",
    protos = [":greeter_grpc"],
)

java_library(
    name = "server",
    srcs = ["GreeterServer.java"],
    deps = [
        ":greeter_java_grpc",
        "@main_mavin//:org_eclipse_jetty_jetty_server",
        "@main_mavin//:org_eclipse_jetty_jetty_servlet",
        "@main_mavin//:org_eclipse_jetty_jetty_util",
        "@main_mavin//:io_prometheus_simpleclient",
        "@main_mavin//:io_prometheus_simpleclient_servlet",
    ],
)

java_binary(
    name = "server-bin",
    main_class = "greeter.GreeterServer",
    runtime_deps = [
        ":server",
    ],
)

java_library(
    name = "client",
    srcs = ["GreeterClient.java"],
    deps = [
        ":greeter_java_grpc",
    ],
)

java_binary(
    name = "client-bin",
    main_class = "greeter.GreeterClient",
    runtime_deps = [
        ":client",
    ],
)