package ru.job4j.pooh.request;

public class HttpRequest {
    private final HttpRequestMethod method;
    private final String requestUrl;
    private final String body;

    public HttpRequest(HttpRequestMethod method, String requestUrl, String body) {
        this.method = method;
        this.requestUrl = requestUrl;
        this.body = body;
    }

    public HttpRequestMethod getMethod() {
        return method;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpRequest{"
                + "method=" + method
                + ", requestUrl='" + requestUrl + '\''
                + ", body='" + body + '\'' + '}';
    }
}
