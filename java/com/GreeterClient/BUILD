package(default_visibility = ["//visibility:public"])

java_library(
    name = "greeter_client",
    srcs = ["GreeterClient.java"],
    deps = [
        "//java/com/GreeterService:greeter_java_grpc",
    ],
)

java_binary(
    name = "greeter_client_bin",
    main_class = "com.greeterclient.GreeterClient",
    runtime_deps = [
        ":greeter_client",
    ],
)