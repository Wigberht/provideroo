package com.d_cherkashyn.epam.helper.validator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainValidatorTest {
    
    @Test
    public void testPasswordWithSpace() {
        String password = "hello moto";
        assertEquals(false, MainValidator.password(password));
    }
    
    @Test
    public void testPasswordWithNoBigLetter() {
        String password = "hello_moto";
        assertEquals(false, MainValidator.password(password));
    }
    
    @Test
    public void testPasswordWithNoNumber() {
        String password = "Hello_moto";
        assertEquals(false, MainValidator.password(password));
    }
    
    @Test
    public void testPasswordValid() {
        String password = "Hello_moto1";
        assertEquals(true, MainValidator.password(password));
    }
    
    @Test
    public void testLoginExactLength() {
        String login = "hamakue";
        assertEquals(true, MainValidator.login(login));
    }
    
    @Test
    public void testLoginValid() {
        String login = "hello_java";
        assertEquals(true, MainValidator.login(login));
    }
    
    @Test
    public void testLoginShort() {
        String login = "short";
        assertEquals(false, MainValidator.login(login));
    }
    
    @Test
    public void testLoginWithSpace() {
        String login = "shortage of beans";
        assertEquals(false, MainValidator.login(login));
    }
    
    @Test
    public void testBirthdayValid() {
        String birthday = "1995-04-22";
        assertEquals(true, MainValidator.birthday(birthday));
    }
    
    @Test
    public void testBirthdayNoHyphen() {
        String birthday = "19950422";
        assertEquals(false, MainValidator.birthday(birthday));
    }
    
    @Test
    public void testBirthdayShort() {
        String birthday = "1995-4-2";
        assertEquals(false, MainValidator.birthday(birthday));
    }
    
    @Test
    public void testPositiveNumberValid() {
        String number = "195.02";
        assertEquals(true, MainValidator.positiveNumber(number));
    }
    
    @Test
    public void testPositiveNumberZero() {
        String number = "0";
        assertEquals(false, MainValidator.positiveNumber(number));
    }
    
    @Test
    public void testPositiveLessThanZero() {
        String number = "-5";
        assertEquals(false, MainValidator.positiveNumber(number));
    }
    
    @Test
    public void testPositiveNumberWithText() {
        String number = "195.02hello";
        assertEquals(false, MainValidator.positiveNumber(number));
    }
    
    @Test
    public void testPositiveNumberWithSpecialChars() {
        String number = "195,.02";
        assertEquals(false, MainValidator.positiveNumber(number));
    }
    
    @Test
    public void testSimpleTextValid() {
        String text = "Hello there lads";
        assertEquals(true, MainValidator.simpleText(text));
    }
    
    @Test
    public void testSimpleTextBlank() {
        String text = "";
        assertEquals(false, MainValidator.simpleText(text));
    }
    
    @Test
    public void testSearchTextValid() {
        String text = "русский текст вместе с english text";
        
        assertEquals(true, MainValidator.searchText(text));
    }
    
}
