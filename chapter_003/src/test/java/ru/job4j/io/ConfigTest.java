package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    private static final String PATH = "." + File.separator + "data" + File.separator;

    @Test
    public void whenToStringPrintsFileContent() {
        Config config = new Config(PATH + "app.properties");
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
        String path = PATH + "pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Dmitry Pratsun")
        );
    }

    @Test
    public void whenPairWithComment() {
        String path = PATH + "pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Dmitry Pratsun")
        );
    }

    @Test
    public void whenNoPairs() {
        String path = PATH + "no_pair.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("name"));
    }
}
