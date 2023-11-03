import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ControlPanel {
    private static final String CORRECT_PASSWORD = "1234";
    private static final int MAX_ATTEMPTS = 3;
    private static final long LOCKOUT_TIME = TimeUnit.SECONDS.toMillis(120);

    private String enteredPassword = "";
    private int incorrectAttempts = 0;
    private long lockoutStartTime = 0;

    public void enterPassword(String input) {
        if (isLockedOut()) {
            long remainingLockoutTime = lockoutStartTime + LOCKOUT_TIME - System.currentTimeMillis();
            System.out.println("Control panel is locked. Remaining lockout time: " + TimeUnit.MILLISECONDS.toSeconds(remainingLockoutTime) + " seconds.");
            return;
        }

        enteredPassword += input;

        if (enteredPassword.length() == 4) {
            if (enteredPassword.equals(CORRECT_PASSWORD)) {
                System.out.println("Password is correct. Access granted.");
                // 进入系统功能选择状态
                enterSystemFunctionSelection();
            } else {
                System.out.println("Incorrect password.");
                incorrectAttempts++;
                enteredPassword = "";

                if (incorrectAttempts >= MAX_ATTEMPTS) {
                    lockoutStartTime = System.currentTimeMillis();
                    System.out.println("Control panel is locked for " + TimeUnit.MILLISECONDS.toSeconds(LOCKOUT_TIME) + " seconds.");
                }
            }
        }
    }

    private void enterSystemFunctionSelection() {
        System.out.println("Entering system function selection...");
        // 在这里添加进入系统功能选择状态的代码
    }

    private boolean isLockedOut() {
        if (lockoutStartTime == 0) {
            return false;
        }

        long elapsedTime = System.currentTimeMillis() - lockoutStartTime;
        return elapsedTime < LOCKOUT_TIME;
    }

    public static void main(String[] args) {
        ControlPanel controlPanel = new ControlPanel();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a digit (0-9) or 'q' to quit: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            if (input.matches("[0-9]")) {
                controlPanel.enterPassword(input);
            } else {
                System.out.println("Invalid input. Please enter a digit (0-9).");
            }
        }
    }
}
