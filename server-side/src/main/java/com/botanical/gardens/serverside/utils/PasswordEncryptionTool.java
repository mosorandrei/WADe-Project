package com.botanical.gardens.serverside.utils;
import org.mindrot.jbcrypt.BCrypt;
public class PasswordEncryptionTool {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String candidatePassword, String hashedPassword) {
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }
}
