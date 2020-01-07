package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfilesTest {

    @Test
    public void testProfiles() {
        Profiles.Address address1 = new Profiles.Address();
        Profiles.Address address2 = new Profiles.Address();
        List<Profiles.Profile> profiles = Arrays.asList(new Profiles.Profile(address1), new Profiles.Profile(address2));
        List<Profiles.Address> result = Profiles.collect(profiles);
        assertThat(result, is(Arrays.asList(address1, address2)));
    }
}
