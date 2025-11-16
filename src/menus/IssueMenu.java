package menus;

import services.IssueService;
import java.util.Scanner;

public class IssueMenu {
    private IssueService issueService;
    private Scanner scanner;

    public IssueMenu(IssueService issueService, Scanner scanner) {
        this.issueService = issueService;
        this.scanner = scanner;
    }

    public void issueBook() {
        issueService.issueBook();
    }

    public void returnBook() {
        issueService.returnBook();
    }
}