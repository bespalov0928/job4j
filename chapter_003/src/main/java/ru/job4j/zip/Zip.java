package ru.job4j.zip;

import java.io.File;
import java.util.List;

public class Zip {
    private static final class Args {
        private String[] args;

        public Args(String[] args) {
            this.args = args;
        }

        public String directory() {
            return "";
        }

        public String exclude() {
            return "";
        }

        public String output() {
            return "";
        }
    }

    private static List<File> seekBy(String root, String ext) {
        return null;
    }

    private static void pack(List<File> sources, File target) {

    }

    public static void main(String[] args) {
        Args arguments = new Args(args);

        List<File> files = seekBy(arguments.directory(), arguments.exclude());
        pack(files, new File(arguments.output()));
    }
}
