package y_lab.usecases.utils;

public class HashFunction {
    public static String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }
}
