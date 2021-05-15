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
    srcs = ["GreeterRpcImpl.java"],
    deps = [
        ":greeter_java_grpc",
        "@main_mavin//:io_prometheus_simpleclient",
    ]
)

java_library(
    name = "greeter_rpc_server",
    srcs = ["GreeterRpcServer.java"],
    deps = [
        ":greeter_java_grpc",
        ":greeter_rpc_impl",
    ]
)

java_library(
    name = "greeter_jetty_server",
    srcs = ["GreeterJettyServer.java"],
    deps = [
        ":greeter_java_grpc",
        "@main_mavin//:org_eclipse_jetty_jetty_server",
        "@main_mavin//:org_eclipse_jetty_jetty_servlet",
        "@main_mavin//:org_eclipse_jetty_jetty_util",
        "@main_mavin//:io_prometheus_simpleclient_servlet",
    ]
)

java_library(
    name = "greeter_server",
    srcs = ["GreeterServer.java"],
    deps = [
        ":greeter_java_grpc",
        ":greeter_rpc_server",
        ":greeter_jetty_server",
    ],
)

java_binary(
    name = "greeter_server_bin",
    main_class = "com.greeterservice.GreeterServer",
    runtime_deps = [
        ":greeter_server",
    ],
)