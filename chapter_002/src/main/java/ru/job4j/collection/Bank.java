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
        Account srcAccount = findUserAccountByPassportAndRequisites(srcPassport, srcRequisite);
        Account destAccount = findUserAccountByPassportAndRequisites(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getAmount() > 0 && srcAccount.getAmount() >= amount) {
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
        return accounts != null ? accounts.size() : 0;
    }

    public List<Account> getUserAccountsByUser(User user) {
        List<Account> result = this.users.get(user);
        return result != null ? result : Collections.emptyList();
    }

    private User findUserByPassport(String passport) {
        return this.users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst()
                .orElse(new User("", 0, ""));
    }

    private Account findUserAccountByPassportAndRequisites(String passport, String requisites) {
        User user = findUserByPassport(passport);
        return getUserAccountsByUser(user).stream()
                .filter(account -> account.getRequisites().equals(requisites))
                .findFirst()
                .orElse(null);
    }
}
