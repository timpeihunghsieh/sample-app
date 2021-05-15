package(default_visibility = ["//visibility:public"])

java_test(
    name = "greeter_service_test",
    size = "small",
    srcs = ["GreeterServiceTest.java"],
    test_class = "com.greeterservice.GreeterServiceTest",
    deps = [
        "//java/com/GreeterClient:greeter_client",
        "//java/com/GreeterService:greeter_server",
        "@main_mavin//:junit_junit",
        "@main_mavin//:org_slf4j_slf4j_api",
        "@main_mavin//:javax_servlet_javax_servlet_api",
    ],
)
