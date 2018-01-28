package com.d_cherkashyn.epam.helper;


import org.mindrot.jbcrypt.BCrypt;

/**
 * Class used to encrypt passwords of user
 */
public class Passwords {
    
    /**
     * Get password hash
     *
     * @param pass
     * @return
     */
    public static String getPasswordHash(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }
    
    /**
     * Check if hash corresponds to password
     *
     * @param pass
     * @param hash
     * @return
     */
    public static boolean checkPassword(String pass, String hash) {
        return BCrypt.checkpw(pass, hash);
    }
}
