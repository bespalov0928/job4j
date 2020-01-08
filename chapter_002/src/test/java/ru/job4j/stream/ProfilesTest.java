package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfilesTest {

    @Test
    public void testProfiles() {
        Address address1 = new Address();
        Address address2 = new Address();
        List<Address> result = Profiles.collect(Arrays.asList(new Profile(address1), new Profile(address2)));
        assertThat(result, is(Arrays.asList(address1, address2)));
    }
}
