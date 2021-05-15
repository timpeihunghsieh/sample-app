package(default_visibility = ["//visibility:public"])

java_test(
    name = "greeter_impl_test",
    size = "small",
    srcs = ["GreeterImplTest.java"],
    test_class = "com.greeterservice.GreeterImplTest",
    deps = [
        "//java/com/GreeterServer/greeter:greeter_rpc_impl",
        "//java/com/GreeterServer/greeter:greeter_java_grpc",
        "@main_mavin//:junit_junit",
    ],
)
