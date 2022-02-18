package ru.kpfu.servlets.service;

import javax.servlet.http.Cookie;

public class AuthorizationVerification {
    public static User rememberUser(User user, Cookie[] cookies, DBHelperInterface db) {
        if (user != null) {
            return user;
        }
        String userEmail = "";
        String userCookiePassword = "";
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ApplicationParameters.USER_EMAIL)) {
                userEmail = cookie.getValue();
            }
            if (cookie.getName().equals(ApplicationParameters.USER_COOKIE_PASSWORD)) {
                userCookiePassword = cookie.getValue();
            }
        }

        if (userEmail.length() * userCookiePassword.length() == 0) {
            return null;
        }

        user = db.getUserByEmail(ApplicationParameters.USERS, userEmail);
        if (user == null) {
            return null;
        }

        if (!user.getCookie().equals(userCookiePassword)) {
            return null;
        }

        return user;
    }
}
