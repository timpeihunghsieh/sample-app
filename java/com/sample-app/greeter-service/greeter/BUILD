package(default_visibility = ["//visibility:public"])

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
    name = "greeter_rpc_impl",
    srcs = [
        "GreeterRpcImpl.java",
        "Greeter.java",
        "GreeterImpl.java",
        "GreeterModule.java",
    ],
    deps = [
        ":greeter_java_grpc",
        "@main_mavin//:com_google_inject_guice",
        "@main_mavin//:io_prometheus_simpleclient",
    ]
)