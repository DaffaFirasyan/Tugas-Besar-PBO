import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MainApp {

    private static final ArrayList<Book> availableBooks = new ArrayList<>();
    private static final ArrayList<Member> members = new ArrayList<>();
    private static final ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("Library Management System");
                System.out.println("1. Tambah Member");
                System.out.println("2. Tambah Buku");
                System.out.println("3. Meminjam Buku");
                System.out.println("4. Buku yang Tersedia");
                System.out.println("5. Exit");

                System.out.print("Pilih nomor yang ingin dilakukan: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        addMember(scanner);
                        break;
                    case 2:
                        addBook(scanner);
                        break;
                    case 3:
                        performTransaction(scanner);
                        break;
                    case 4:
                        viewAvailableBooks();
                        break;
                    case 5:
                        System.out.println("Exiting the program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error: Tolong untuk memasukkan angka, bukan simbol atau huruf!");
        } finally {
            scanner.close();
        }
    }

    private static void addMember(Scanner scanner) {
        System.out.print("Enter Member Name: ");
        String memberName = scanner.nextLine();

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); 

        Member member = new Member(memberName, memberId);
        members.add(member);

        System.out.println("Member added successfully.");
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter Book Title: ");
        String bookTitle = scanner.nextLine();

        System.out.print("Enter Book Author: ");
        String bookAuthor = scanner.nextLine();

        System.out.print("Enter Book ISBN: ");
        int bookISBN = scanner.nextInt();
        scanner.nextLine(); 

        Book book = new Book(bookTitle, bookAuthor, bookISBN);
        availableBooks.add(book);

        System.out.println("Book added successfully.");
    }

    private static void performTransaction(Scanner scanner) throws ParseException {
        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        Member member = findMember(memberId);

        if (member == null) {
            System.out.println("Member not found. Please add the member first.");
            return;
        }

        System.out.print("Enter Book ISBN to borrow: ");
        int bookISBN = scanner.nextInt();
        scanner.nextLine();

        Book book = findBook(bookISBN);

        if (book == null) {
            System.out.println("Book not found. Please add the book first.");
            return;
        }

        System.out.print("Enter Due Date (dd-MM-yyyy): ");
        String dueDateString = scanner.nextLine();
        Date dueDate = parseDate(dueDateString);

        Transaction transaction = new Transaction(book, member, dueDate);
        transactions.add(transaction);

        System.out.println("Transaction completed successfully.");
    }

    private static void viewAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : availableBooks) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getISBN() + ")");
        }
    }

    private static Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    private static Book findBook(int bookISBN) {
        for (Book book : availableBooks) {
            if (book.getISBN() == bookISBN) {
                return book;
            }
        }
        return null;
    }

    private static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.parse(dateString);
    }
}
