package menus;

import services.MemberService;
import java.util.Scanner;

public class MemberMenu {
    private MemberService memberService;
    private Scanner scanner;

    public MemberMenu(MemberService memberService, Scanner scanner) {
        this.memberService = memberService;
        this.scanner = scanner;
    }

    public void manageMembers() {
        while (true) {
            clearConsole();
            System.out.println("============ Manage Members ============");
            System.out.println("1. Add Member");
            System.out.println("2. Update Member");
            System.out.println("3. Delete Member");
            System.out.println("4. Search Member");
            System.out.println("5. View All Members");
            System.out.println("6. Back to Home");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1": memberService.addMember(); break;
                case "2": memberService.updateMember(); break;
                case "3": memberService.deleteMember(); break;
                case "4": memberService.searchMember(); break;
                case "5": memberService.viewAllMembers(); break;
                case "6": return;
                default:
                    System.out.println("\nInvalid option. Please try again. Press Enter to continue...");
                    scanner.nextLine();
            }
        }
    }

    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}