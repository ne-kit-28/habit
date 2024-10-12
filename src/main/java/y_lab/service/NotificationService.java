package y_lab.service;

public class NotificationService {
    public void sendNotification(String email, String habitName) {
        System.out.println("Reminder: " + email + ", it's time to complete your habit: " + habitName);
    }
}
