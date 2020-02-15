package ru.job4j.chat;

import org.junit.Test;
import ru.job4j.chat.*;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleChatTest {

    @Test
    public void whenFirstInputIsExitShouldPrintExit() {
        ListPrinter listPrinter = new ListPrinter();
        Messages messages = new ListMessages(List.of("exit"));
        Answers answers = new ListAnswers(Collections.emptyList());
        ConsoleChat chat = new ConsoleChat(messages, answers, listPrinter);
        chat.start();
        assertThat(listPrinter.getList(), is(List.of("exit")));
    }

    @Test
    public void whenAlwaysPrintInputAndAnswer() {
        ListPrinter listPrinter = new ListPrinter();
        Messages messages = new ListMessages(List.of("input", "input", "exit"));
        Answers answers = new ListAnswers(List.of("answer"));
        ConsoleChat chat = new ConsoleChat(messages, answers, listPrinter);
        chat.start();
        assertThat(listPrinter.getList(), is(List.of("input", "answer", "input", "answer", "exit")));
    }

    @Test
    public void whenInputIsStopShouldNotPrintAnswers() {
        ListPrinter listPrinter = new ListPrinter();
        Messages messages = new ListMessages(List.of("stop", "input", "input", "exit"));
        Answers answers = new ListAnswers(List.of());
        ConsoleChat chat = new ConsoleChat(messages, answers, listPrinter);
        chat.start();
        assertThat(listPrinter.getList(), is(List.of("stop", "input", "input", "exit")));
    }

    @Test
    public void whenInputIsStopAfterContinueShouldPrintAnswers() {
        ListPrinter listPrinter = new ListPrinter();
        Messages messages = new ListMessages(List.of("stop", "input", "continue", "input", "exit"));
        Answers answers = new ListAnswers(List.of("answer after continue", "answer after input"));
        ConsoleChat chat = new ConsoleChat(messages, answers, listPrinter);
        chat.start();
        assertThat(
                listPrinter.getList(),
                is(List.of("stop", "input", "continue", "answer after continue", "input", "answer after input", "exit"))
        );
    }
}
