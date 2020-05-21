package ru.job4j.cache;

public class Application {
    public static void main(String[] args) {
        SoftReferenceFileCache cache = new SoftReferenceFileCache(new FileDataProviderImpl());
        cache.getData("names.txt");
        cache.getData("names.txt");
        GcUtils.tryToAllocateAllAvailableMemory();
        cache.getData("names.txt");
    }
}
