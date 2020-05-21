package ru.job4j.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.apache.logging.log4j.Level.INFO;

public class SoftReferenceFileCache implements FileCache {
    private static final Logger LOG = LogManager.getLogger(SoftReferenceFileCache.class.getName());

    private final FileDataProvider provider;
    private final Map<String, SoftReference<String>> cache = new HashMap<>();

    public SoftReferenceFileCache(FileDataProvider provider) {
        this.provider = provider;
    }

    @Override
    public String getData(String fileName) {
        String result = cache.containsKey(fileName) ? cache.get(fileName).get() : null;

        if (result == null) {
            LOG.log(INFO, format("Getting data from file %s...", fileName));
            result = provider.getData(fileName);
            cache.put(fileName, new SoftReference<>(result));
        } else {
            LOG.log(INFO, format("Getting data from cache for %s...", fileName));
        }

        return result;
    }
}
