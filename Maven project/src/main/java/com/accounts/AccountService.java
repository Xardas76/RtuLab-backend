package com.accounts;

public interface AccountService {
    User getUserByLogin(String login);

    void addNewUser(User userProfile);
}
