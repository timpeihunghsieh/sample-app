package(default_visibility = ["//visibility:public"])

java_library(
    name = "greeter_server",
    srcs = ["GreeterServer.java"],
    deps = [
        "//java/com/common/CommonRpcServer:common_server",
        "//java/com/GreeterServer/greeter:greeter_rpc_impl",
    ],
)

java_binary(
    name = "greeter_server_bin",
    main_class = "com.greeterservice.GreeterServer",
    runtime_deps = [
        ":greeter_server",
    ],
)