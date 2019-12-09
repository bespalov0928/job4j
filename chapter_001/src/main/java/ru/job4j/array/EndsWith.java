package ru.job4j.array;

public class EndsWith {
    public static boolean endsWith(char[] word, char[] post) {
        boolean result = true;
        for (int postIndex = post.length - 1, wordIndex = word.length - 1; postIndex >= 0; postIndex--, wordIndex--) {
            if (word[wordIndex] != post[postIndex]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
