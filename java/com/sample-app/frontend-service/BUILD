package(default_visibility = ["//visibility:public"])

load("@io_bazel_rules_docker//java:image.bzl", "java_image")

# url-shorten
java_library(
    name = "frontend-service",
    srcs = [
        "FrontendModule.java",
        "FrontendServer.java",
        "FrontendServlet.java",
        "FrontendLogic.java",
        "FrontendLogicImpl.java",
        "GreeterCaller.java",
        "GreeterCallerImpl.java",
    ],
    deps = [
        "//java/com/sample-app/greeter-service/greeter:greeter_java_grpc",
        "@main_mavin//:org_eclipse_jetty_jetty_server",
        "@main_mavin//:org_eclipse_jetty_jetty_servlet",
        "@main_mavin//:org_eclipse_jetty_jetty_util",
        "@main_mavin//:javax_servlet_javax_servlet_api",
        "@main_mavin//:com_google_inject_guice",
        "@main_mavin//:io_prometheus_simpleclient",
        "@main_mavin//:io_prometheus_simpleclient_servlet",
        "@main_mavin//:org_slf4j_slf4j_api",
        "@main_mavin//:org_slf4j_slf4j_simple",
    ],
)

java_image(
    name = "frontend-service-img",
    # base = "@ubuntu//image",  # This is optional. It makes it easier to debug,
    main_class = "com.sampleapp.frontendservice.FrontendServer",
    runtime_deps = [
        ":frontend-service",
    ],
)