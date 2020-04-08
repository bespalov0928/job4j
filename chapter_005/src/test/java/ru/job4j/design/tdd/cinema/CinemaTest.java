package ru.job4j.design.tdd.cinema;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Ignore
public class CinemaTest {

    private final static class IllegalSitException extends Exception {

    }

    private final static class IllegalDateException extends Exception {

    }

    private final static class SessionNotFoundException extends Exception {

    }

    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test(expected = IllegalSitException.class)
    public void whenWrongSitChosenThanBuyShouldThrowException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        cinema.buy(account, 0, 0, date);
    }

    @Test(expected = IllegalDateException.class)
    public void whenDateIsInThePastThanBuyShouldThrowException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(1970, Calendar.NOVEMBER, 10, 23, 0);
        cinema.buy(account, 1, 1, date);
    }

    @Test(expected = SessionNotFoundException.class)
    public void whenNoSessionForDateThanBuyShouldThrowException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        cinema.buy(account, 1, 1, date);
    }
}