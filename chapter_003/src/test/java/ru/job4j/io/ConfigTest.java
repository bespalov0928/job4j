package ru.job4j.io;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenToStringPrintsFileContent() {
        Config config = new Config("./data/app.properties");
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add("hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect");
        result.add("hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio");
        result.add("hibernate.connection.driver_class=org.postgresql.Driver");
        result.add("hibernate.connection.username=postgres");
        result.add("hibernate.connection.password=password");
        assertThat(result.toString(), is(config.toString()));
    }

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Dmitry Pratsun")
        );
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Dmitry Pratsun")
        );
    }

    @Test
    public void whenNoPairs() {
        String path = "./data/no_pair.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("name"));
    }
}
