package(default_visibility = ["//visibility:public"])

java_test(
    name = "greeter_impl_test",
    size = "small",
    srcs = ["GreeterImplTest.java"],
    test_class = "com.sampleapp.greeterservice.GreeterImplTest",
    deps = [
        "//java/com/sample-app/greeter-service/greeter:greeter_rpc_impl",
        "//java/com/sample-app/greeter-service/greeter:greeter_java_grpc",
        "@main_mavin//:junit_junit",
    ],
)
