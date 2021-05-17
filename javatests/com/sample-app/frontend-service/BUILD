package(default_visibility = ["//visibility:public"])

java_test(
    name = "FrontendServletTest",
    size = "small",
    srcs = ["FrontendServletTest.java"],
    test_class = "com.sampleapp.frontendservice.FrontendServletTest",
    deps = [
        "//java/com/sample-app/frontend-service:frontend-service",
        "@main_mavin//:junit_junit",
        "@main_mavin//:org_mockito_mockito_all",
        "@main_mavin//:org_slf4j_slf4j_api",
        "@main_mavin//:org_springframework_spring_test",
        "@main_mavin//:javax_servlet_javax_servlet_api",
    ],
)
