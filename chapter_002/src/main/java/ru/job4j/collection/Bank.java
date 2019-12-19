package ru.job4j.collection;

import java.math.BigDecimal;
import java.util.*;

public class Bank {
    private Map<User, List<Account>> users = new TreeMap<>();

    public void addUser(User user) {
        this.users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccountToUser(String passport, Account account) {
        List<Account> userAccounts = getUserAccountsByUserPassport(passport);
        userAccounts.add(account);
    }

    public void deleteUser(User user) {
        this.users.remove(user);
    }

    public void deleteAccountFromUser(String passport, Account account) {
        this.getUserAccountsByUserPassport(passport).remove(account);
    }

    public List<Account> getUserAccountsByUserPassport(String passport) {
        User user = findUserByPassport(passport);
        return getUserAccountsByUser(user);
    }

    public boolean transferMoney(
            String srcPassport,
            String srcRequisite,
            String destPassport,
            String destRequisite,
            double amount) {
        boolean result = false;
        User srcUser = findUserByPassport(srcPassport);
        User destUser = findUserByPassport(destPassport);
        Account srcAccount = findUserAccountByRequisites(srcUser, srcRequisite);
        Account destAccount = findUserAccountByRequisites(destUser, destRequisite);
        if (!srcAccount.getRequisites().isEmpty()
                && !destAccount.getRequisites().isEmpty()
                && srcAccount.getAmount() > 0
                && srcAccount.getAmount() >= amount) {
            srcAccount.setAmount(srcAccount.getAmount() - amount);
            destAccount.setAmount(destAccount.getAmount() + amount);
            result = true;
        }
        return result;
    }

    protected int getUserCount() {
        return this.users.size();
    }

    protected int getUserAccountsCount(User user) {
        List<Account> accounts = this.users.get(user);
        return accounts.size();
    }

    public List<Account> getUserAccountsByUser(User user) {
        List<Account> result = this.users.get(user);
        return result != null ? result : Collections.emptyList();
    }

    private User findUserByPassport(String passport) {
        User result = new User("", 0, "");
        for (User user : this.users.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
                break;
            }
        }
        return result;
    }

    private Account findUserAccountByRequisites(User user, String requisites) {
        Account result = new Account(0, "");
        for (Account account : getUserAccountsByUser(user)) {
            if (account.getRequisites().equals(requisites)) {
                result = account;
                break;
            }
        }
        return result;
    }
}
