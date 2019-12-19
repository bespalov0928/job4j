package ru.job4j.collection;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void testWhenEmpty() {
        Bank bank = new Bank();
        List<Account> result = bank.getUserAccountsByUserPassport("not exist");
        assertThat(result, is(Collections.emptyList()));
    }

    @Test(expected = NullPointerException.class)
    public void testAddUserWithNullParameters() {
        Bank bank = new Bank();
        User user1 = new User(null, 0, null);
        bank.addUser(user1);
    }

    @Test
    public void testAddUser() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        bank.addUser(user1);
        assertEquals(bank.getUserCount(), 1);
    }

    @Test
    public void testDeleteUserWhenNoSuchUser() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        bank.deleteUser(user1);
    }

    @Test
    public void testDeleteUserWhenPresent() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        bank.addUser(user1);
        bank.deleteUser(user1);
        assertEquals(bank.getUserCount(), 0);
    }

    @Test
    public void testAddUserAccount() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        bank.addUser(user1);
        Account account1 = new Account(2, "1");
        bank.addAccountToUser("1", account1);
        assertEquals(bank.getUserAccountsCount(user1), 1);
    }

    @Test
    public void testGetUserAccounts() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        User user2 = new User("3", 0, "3");
        User user3 = new User("2", 0, "2");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addUser(user3);
        Account account1 = new Account(2, "2");
        bank.addAccountToUser("2", account1);
        List<Account> result = bank.getUserAccountsByUserPassport("2");
        assertThat(result, is(Arrays.asList(account1)));
    }

    @Test
    public void testGetUserAccountsWhenUserNotPresent() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        bank.addUser(user1);
        Account account1 = new Account(2, "1");
        bank.addAccountToUser("1", account1);
        List<Account> result = bank.getUserAccountsByUserPassport("2");
        assertThat(result, is(Collections.emptyList()));
    }

    @Test
    public void testRemoveUserAccount() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        bank.addUser(user1);
        Account account1 = new Account(2, "1");
        bank.addAccountToUser("1", account1);
        bank.deleteAccountFromUser("1", account1);
        assertEquals(bank.getUserAccountsCount(user1), 0);
    }

    @Test
    public void testFailTransferWhenEmpty() {
        Bank bank = new Bank();
        boolean result = bank.transferMoney(
                "1",
                "1",
                "2",
                "2",
                3);
        assertFalse(result);
    }

    @Test
    public void testSuccessTransfer() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        User user2 = new User("2", 0, "2");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account(2, "1");
        bank.addAccountToUser("1", account1);
        Account account2 = new Account(2, "2");
        bank.addAccountToUser("2", account2);
        boolean result = bank.transferMoney(
                "1",
                "1",
                "2",
                "2",
                1);
        assertTrue(result);
    }

    @Test
    public void testFailTransferWhenAmountHigher() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        User user2 = new User("2", 0, "2");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account(2, "1");
        bank.addAccountToUser("1", account1);
        Account account2 = new Account(2, "2");
        bank.addAccountToUser("2", account2);
        boolean result = bank.transferMoney(
                "1",
                "1",
                "2",
                "2",
                3);
        assertFalse(result);
    }

    @Test
    public void testFailTransferWhenSrcUserNotFound() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        User user2 = new User("2", 0, "2");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account(2, "1");
        bank.addAccountToUser("1", account1);
        Account account2 = new Account(2, "2");
        bank.addAccountToUser("2", account2);
        boolean result = bank.transferMoney(
                "3",
                "1",
                "2",
                "2",
                3);
        assertFalse(result);
    }

    @Test
    public void testFailTransferWhenDestUserNotFound() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        User user2 = new User("2", 0, "2");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account(3, "1");
        bank.addAccountToUser("1", account1);
        Account account2 = new Account(2, "2");
        bank.addAccountToUser("2", account2);
        boolean result = bank.transferMoney(
                "1",
                "1",
                "3",
                "2",
                3);
        assertFalse(result);
    }

    @Test
    public void testFailTransferWhenSrcAccountNotFound() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        User user2 = new User("2", 0, "2");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account(2, "1");
        bank.addAccountToUser("1", account1);
        Account account2 = new Account(2, "2");
        bank.addAccountToUser("2", account2);
        boolean result = bank.transferMoney(
                "1",
                "3",
                "2",
                "2",
                3);
        assertFalse(result);
    }

    @Test
    public void testFailTransferWhenDestAccountNotFound() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        User user2 = new User("2", 0, "2");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account(2, "1");
        bank.addAccountToUser("1", account1);
        Account account2 = new Account(2, "2");
        bank.addAccountToUser("2", account2);
        boolean result = bank.transferMoney(
                "1",
                "1",
                "2",
                "3",
                3);
        assertFalse(result);
    }

    @Test
    public void successTransferAmountTest() {
        Bank bank = new Bank();
        User user1 = new User("1", 0, "1");
        User user2 = new User("2", 0, "2");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account(2, "1");
        bank.addAccountToUser("1", account1);
        Account account2 = new Account(2, "2");
        bank.addAccountToUser("2", account2);
        boolean result = bank.transferMoney(
                "1",
                "1",
                "2",
                "2",
                2);

        assertThat(4.0, is(account2.getAmount()));
        assertThat(0.0, is(account1.getAmount()));
    }
}
