import java.util.Date;

public class Transaction {

    private Book book;
    private Member member;
    private Date dueDate;

    public Transaction(Book book, Member member, Date dueDate) {
        this.book = book;
        this.member = member;
        this.dueDate = dueDate;
    }

    // Getter and Setter for book
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    // Getter and Setter for member
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    // Getter and Setter for dueDate
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}