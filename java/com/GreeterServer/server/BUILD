package(default_visibility = ["//visibility:public"])

load("@io_bazel_rules_docker//java:image.bzl", "java_image")

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

java_image(
    name = "greeter_server_img",
    # base = "@ubuntu//image",  # This is optional. It makes it easier to debug,
    main_class = "com.greeterservice.GreeterServer",
    runtime_deps = [
        ":greeter_server",
    ],
)