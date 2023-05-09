import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Receipt {

	private Loan loan;
	private int loanNumber;
	private String studentName;
	private int studentID;
	private Date loanDate;
	private Date dueDate;
	private double dailyPrice;
	private double totalPrice;
	
	public Receipt(Loan loan) {
		this.loan = loan;
		this.loanNumber = loan.getLoanNumber();
		this.studentName = loan.getStudentName();
		this.studentID = loan.getStudentID();
		this.loanDate = loan.getLoanDate();
		this.dueDate = loan.getLoanDueDate();
		this.dailyPrice = loan.getDailyPrice();
		this.totalPrice = loan.getFinalPrice();
	}

	//ignore this for now , int loanNumber, String studentName, int studentID, Date loanDate, Date dueDate, double dailyPrice, double totalPrice
}