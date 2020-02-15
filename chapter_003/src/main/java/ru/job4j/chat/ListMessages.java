package ru.job4j.chat;

import java.util.Iterator;
import java.util.List;

public class ListMessages implements Messages {
    List<String> messages;
    Iterator<String> it;

    public ListMessages(List<String> messages) {
        this.messages = messages;
        this.it = this.messages.iterator();
    }

    @Override
    public String getMessage() {
        return it.next();
    }
}
