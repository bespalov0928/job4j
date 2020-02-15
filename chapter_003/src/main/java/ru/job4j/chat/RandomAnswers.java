package ru.job4j.chat;

import java.util.List;
import java.util.Random;

public class RandomAnswers extends ListAnswers {
    private Random random;

    public RandomAnswers(List<String> answers) {
        super(answers);
        this.random = new Random();
    }

    @Override
    public String getAnswer() {
        return answers.get(random.nextInt(answers.size()));
    }
}
