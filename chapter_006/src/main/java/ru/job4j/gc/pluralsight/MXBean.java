package ru.job4j.gc.pluralsight;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class MXBean {
    public static class User {
        long a;
        long aa;
        long aaa;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Object finalizing...");
        }
    }

    public static void main(String[] args) {
        List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();
        printMXBeans(beans);

        System.out.println(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().toString());
        for (int i = 0; i < 600; i++) {
            new User();
        }
        System.out.println(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().toString());
        printMXBeans(beans);
    }

    private static void printMXBeans(List<GarbageCollectorMXBean> beans) {
        for (var bean: beans) {
            System.out.println("Name: " + bean.getName());
            System.out.println("Number of collections: " + bean.getCollectionCount());
            System.out.println("Collection time: " + bean.getCollectionTime() + "ms");
            System.out.println("Pool names:");
            for (var name: bean.getMemoryPoolNames()) {
                System.out.println("\t" + name);
            }
        }
    }
}
