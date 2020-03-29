package ru.job4j.design.srp.collector;

import java.util.HashMap;
import java.util.stream.Collector;

public final class CollectorMap extends HashMap<String, Collector<CharSequence, ?, String>> {
    public final static String HEADER_COLLECTOR = "header";
    public final static String BODY_COLLECTOR = "body";
    public final static String FIELDS_COLLECTOR = "fields";
}
