package ru.job4j.chat;

import ru.job4j.disjointgroups.DisjointGroups;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileAnswers implements Answers {
    private List<String> answers;
    private Iterator<String> it;

    public FileAnswers(String fileName) {
        URL resource = DisjointGroups.class.getResource("/" + fileName);
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            answers = lines.collect(Collectors.toList());
            it = answers.iterator();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAnswers() {
        return answers;
    }

    @Override
    public String getAnswer() {
        if (!it.hasNext()) {
            it = answers.iterator();
        }
        return it.next();
    }
}
