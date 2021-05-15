package(default_visibility = ["//visibility:public"])

load("@rules_proto_grpc//java:defs.bzl", "java_grpc_library")

proto_library(
    name = "common_grpc",
    srcs = ["common-rpc.proto"],
    deps = [],
)

java_grpc_library(
    name = "common_java_grpc",
    protos = [":common_grpc"],
)

java_library(
    name = "common_rpc_server",
    srcs = ["CommonRpcServer.java"],
    deps = [
        ":common_java_grpc",
    ]
)

java_library(
    name = "common_jetty_server",
    srcs = ["CommonJettyServer.java"],
    deps = [
        ":common_java_grpc",
        "@main_mavin//:org_eclipse_jetty_jetty_server",
        "@main_mavin//:org_eclipse_jetty_jetty_servlet",
        "@main_mavin//:org_eclipse_jetty_jetty_util",
        "@main_mavin//:io_prometheus_simpleclient_servlet",
    ]
)

java_library(
    name = "common_server",
    srcs = ["CommonServer.java"],
    deps = [
        ":common_rpc_server",
        ":common_jetty_server",
        ":common_java_grpc",
    ],
)