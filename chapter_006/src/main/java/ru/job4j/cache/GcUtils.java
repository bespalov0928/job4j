package ru.job4j.cache;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GcUtils {
    private static final Logger LOG = LogManager.getLogger(GcUtils.class.getName());

    public static void tryToAllocateAllAvailableMemory() {
        try {
            final List<Object[]> allocations = new ArrayList<>();
            int size;
            while ((size = (int) Runtime.getRuntime().freeMemory()) > 0) {
                Object[] part = new Object[Math.min(size, Integer.MAX_VALUE)];
                allocations.add(part);
            }
        } catch (OutOfMemoryError e) {
            LOG.log(Level.ERROR, "catch expected exception: " + e.getMessage());
        }
    }
}
