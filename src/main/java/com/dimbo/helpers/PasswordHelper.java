package com.dimbo.helpers;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHelper {
    public static String getPasswordHash(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }
    
    public static boolean checkPassword(String pass, String hash) {
        return BCrypt.checkpw(pass, hash);
    }
}
