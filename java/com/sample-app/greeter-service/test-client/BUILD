package(default_visibility = ["//visibility:public"])

java_library(
    name = "greeter_client",
    srcs = ["GreeterClient.java"],
    deps = [
        "//java/com/sample-app/greeter-service/greeter:greeter_java_grpc",
    ],
)

java_binary(
    name = "greeter_client_bin",
    main_class = "com.sampleapp.greeterservice.GreeterClient",
    runtime_deps = [
        ":greeter_client",
    ],
)