package com.dimbo.helper.validator;

import org.apache.commons.lang.math.NumberUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainValidator {
    
    private static final String LOGIN_REGEX = "^(\\w)(?=\\S+$).{6,}$";
    private static final Pattern LOGIN_PATTERN = Pattern.compile(LOGIN_REGEX);
    
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    
    private static final String BIRTHDAY_REGEX = "^(\\d{4})-(\\d{2})-(\\d{2})$";
    private static final Pattern BIRTHDAY_PATTERN = Pattern.compile(BIRTHDAY_REGEX);
    
    private static final String SEARCH_REGEX = "^([\\w\\d\\sа-яА-Я])+$";
    private static final Pattern SEARCH_PATTERN = Pattern.compile(SEARCH_REGEX);
    
    public static boolean registrationValidator(String login, String password,
                                                String firstName, String lastName,
                                                String birthDay) {
        return login(login)
            && password(password)
            && simpleText(firstName)
            && simpleText(lastName)
            && birthday(birthDay);
    }
    
    public static boolean tariffValidator(String title, String description,
                                          String numberOfDays, String cost,
                                          String currency) {
        
        return simpleText(title)
            && simpleText(description)
            && positiveNumber(numberOfDays)
            && positiveNumber(cost)
            && simpleText(currency);
    }
    
    public static boolean login(String login) {
        Matcher matcher = LOGIN_PATTERN.matcher(login);
        
        return matcher.find();
    }
    
    public static boolean password(String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        
        return matcher.find();
    }
    
    public static boolean birthday(String birthdayString) {
        Matcher matcher = BIRTHDAY_PATTERN.matcher(birthdayString);
        
        return matcher.find();
    }
    
    public static boolean positiveNumber(String number) {
        return NumberUtils.isNumber(number)
            && (NumberUtils.toDouble(number) > 0);
    }
    
    public static boolean simpleText(String text) {
        return text.length() > 0;
    }
    
    public static boolean searchText(String text) {
        Matcher matcher = SEARCH_PATTERN.matcher(text);
        
        return matcher.find();
    }
}
