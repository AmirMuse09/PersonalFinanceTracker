import java.util.Scanner;
import java.util.ArrayList;

public class PersonalFinanceTracker {

    private static double totalIncome = 0.0;
    private static double totalExpenses = 0.0;
    private static double savingsGoal = 0.0;
    private static ArrayList<String> expenseList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Personal Finance Tracker!");

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Financial Summary");
            System.out.println("4. Set Savings Goal");
            System.out.println("5. Exit");

            int choice = getValidChoice(scanner);

            switch (choice) {
                case 1:
                    addIncome(scanner);
                    break;
                case 2:
                    addExpense(scanner);
                    break;
                case 3:
                    viewSummary();
                    break;
                case 4:
                    setSavingsGoal(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using Personal Finance Tracker!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static int getValidChoice(Scanner scanner) {
        int choice = -1;
        while (choice < 1 || choice > 5) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
        return choice;
    }

    private static void addIncome(Scanner scanner) {
        try {
            System.out.print("Enter income amount: $");
            double income = Double.parseDouble(scanner.nextLine());
            if (income < 0) throw new IllegalArgumentException();
            totalIncome += income;
            System.out.println("Income added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Income cannot be negative.");
        }
    }

    private static void addExpense(Scanner scanner) {
        try {
            System.out.print("Enter expense description: ");
            String description = scanner.nextLine();
            System.out.print("Enter expense amount: $");
            double expense = Double.parseDouble(scanner.nextLine());
            if (expense < 0) throw new IllegalArgumentException();
            totalExpenses += expense;
            expenseList.add(description + " - $" + expense);
            System.out.println("Expense added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Expense cannot be negative.");
        }
    }

    private static void viewSummary() {
        System.out.println("\n--- Financial Summary ---");
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expenses: $" + totalExpenses);
        System.out.println("Remaining Balance: $" + (totalIncome - totalExpenses));

        if (savingsGoal > 0) {
            System.out.println("Savings Goal: $" + savingsGoal);
            if ((totalIncome - totalExpenses) >= savingsGoal) {
                System.out.println("Congratulations! You have reached your savings goal.");
            } else {
                System.out.println("You are $" + (savingsGoal - (totalIncome - totalExpenses)) + " away from your savings goal.");
            }
        }

        if (!expenseList.isEmpty()) {
            System.out.println("\nExpenses List:");
            for (String expense : expenseList) {
                System.out.println(expense);
            }
        }
    }

    private static void setSavingsGoal(Scanner scanner) {
        try {
            System.out.print("Enter your savings goal: $");
            double goal = Double.parseDouble(scanner.nextLine());
            if (goal < 0) throw new IllegalArgumentException();
            savingsGoal = goal;
            System.out.println("Savings goal set to $" + goal);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Savings goal cannot be negative.");
        }
    }
}
