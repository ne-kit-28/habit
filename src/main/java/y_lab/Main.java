package y_lab;

import y_lab.out.repositories.UserRepositoryImpl;
import y_lab.usecases.EditUserUseCase;
import y_lab.usecases.LoginUseCase;
import y_lab.usecases.PasswordResetUseCase;
import y_lab.usecases.RegistrationUseCase;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        UserRepositoryImpl rep = new UserRepositoryImpl();
        LoginUseCase log = new LoginUseCase(rep);
        RegistrationUseCase reg = new RegistrationUseCase(rep);
        PasswordResetUseCase resPass = new PasswordResetUseCase(rep);
        EditUserUseCase edUs = new EditUserUseCase(rep);

        Scanner scanner = new Scanner(System.in);
        String em = scanner.nextLine();
        String pas = scanner.nextLine();
        String name = scanner.nextLine();

        reg.register(name, em, pas);
        log.login(em, pas);
        resPass.requestPasswordReset(em);

        String tok = scanner.nextLine();
        String newP = scanner.nextLine();

        resPass.resetPassword(em, tok, newP);

        edUs.editUser(0L, "nam@a", "", "");
        System.out.println(rep.findById(0L).get().getEmail());
    }
}