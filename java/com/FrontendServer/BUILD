package(default_visibility = ["//visibility:public"])

# url-shorten
java_library(
    name = "frontend-service",
    srcs = [
        "FrontendModule.java",
        "FrontendServer.java",
        "FrontendServlet.java",
        "FrontendLogic.java",
        "FrontendLogicImpl.java",
    ],
    deps = [
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