package com.accounts;

public interface AccountService {
    UserProfile getUserByLogin(String login);

    void addNewUser(UserProfile userProfile);
}
