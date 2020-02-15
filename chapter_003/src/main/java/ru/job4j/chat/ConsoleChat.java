package ru.job4j.chat;

public class ConsoleChat {
    private Messages messages;
    private Answers answers;
    private Printer printer;
    private boolean silent = false;

    public ConsoleChat(Messages messages, Answers answers, Printer printer) {
        this.messages = messages;
        this.answers = answers;
        this.printer = printer;
    }

    public void start() {
        String phrase = messages.getMessage();
        while (!"exit".equals(phrase)) {
            if ("stop".equals(phrase)) {
                silent = true;
            } else if ("continue".equals(phrase)) {
                silent = false;
            }
            printer.print(phrase);
            if (!silent) {
                printer.print(answers.getAnswer());
            }
            phrase = messages.getMessage();
        }
        printer.print(phrase);
    }

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat(
                new ConsoleMessages(),
                new RandomAnswers(new FileAnswers("answers").getAnswers()),
                new FilePrinter("chat.log"));
        chat.start();
    }
}
