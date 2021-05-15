package(default_visibility = ["//visibility:public"])

java_library(
    name = "greeter_rpc_server",
    srcs = ["GreeterRpcServer.java"],
    deps = [
        "//java/com/GreeterService/greeter:greeter_rpc_impl",
        "//java/com/GreeterService/greeter:greeter_java_grpc",
    ]
)

java_library(
    name = "greeter_jetty_server",
    srcs = ["GreeterJettyServer.java"],
    deps = [
        "//java/com/GreeterService/greeter:greeter_java_grpc",
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
        ":greeter_rpc_server",
        ":greeter_jetty_server",
        "//java/com/GreeterService/greeter:greeter_java_grpc",
    ],
)

java_binary(
    name = "greeter_server_bin",
    main_class = "com.greeterservice.GreeterServer",
    runtime_deps = [
        ":greeter_server",
    ],
)