package ru.job4j.chat;

import java.util.Iterator;
import java.util.List;

public class ListAnswers implements Answers {
    List<String> answers;
    Iterator<String> it;

    public ListAnswers(List<String> answers) {
        this.answers = answers;
        this.it = this.answers.iterator();
    }

    @Override
    public String getAnswer() {
        if (!it.hasNext()) {
            it = answers.iterator();
        }
        return it.next();
    }
}
