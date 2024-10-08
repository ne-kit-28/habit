package y_lab.usecases;

public class HashFunction {
    public static String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }
}
