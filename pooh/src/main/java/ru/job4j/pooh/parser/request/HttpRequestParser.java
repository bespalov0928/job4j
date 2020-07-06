package ru.job4j.pooh.parser.request;

import ru.job4j.pooh.request.HttpRequest;
import ru.job4j.pooh.request.HttpRequestMethod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HttpRequestParser implements RequestParser {

    @Override
    public HttpRequest parse(InputStream inputStream) {
        HttpRequestMethod method = HttpRequestMethod.POST;
        var requestUrl = "";
        var body = "";
        try {
            var in = new BufferedReader(new InputStreamReader(inputStream));
            var tokenizer = new StringTokenizer(in.readLine());
            method = HttpRequestMethod.valueOf(tokenizer.nextToken().toUpperCase());
            requestUrl = tokenizer.nextToken();
            body = tokenizer.hasMoreTokens() ? tokenizer.nextToken("") : "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HttpRequest(method, requestUrl, body);
    }
}
