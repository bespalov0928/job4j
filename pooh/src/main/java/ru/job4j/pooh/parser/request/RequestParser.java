package ru.job4j.pooh.parser.request;

import ru.job4j.pooh.request.HttpRequest;

import java.io.InputStream;

public interface RequestParser {
    HttpRequest parse(InputStream inputStream);
}
