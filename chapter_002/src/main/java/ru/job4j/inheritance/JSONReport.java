package ru.job4j.inheritance;

public class JSONReport extends TextReport {
    @Override
    public String generate(String name, String body) {
        StringBuilder builder = new StringBuilder();
        builder.append("{").append(System.lineSeparator());
        builder.append("   name : ").append(name).append(",");
        builder.append(System.lineSeparator());
        builder.append("   body : ").append(body).append(",");
        builder.append(System.lineSeparator()).append("}");
        return builder.toString();
    }
}
