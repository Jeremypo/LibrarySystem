package hibernate;

public class Receipt {
	private int loanNumber;
	private int itemID;
	private String itemName;
	private int broncoID;
	private String studentName;
	private String dueDate;
	private double estPrice;
	
	// constructors
	public Receipt() {
		super();
	}
	
	// constructor if loan is bookLoan
	public Receipt(BookLoan bookLoan) {
		this.loanNumber = bookLoan.getLoanNumber();
		this.itemID = bookLoan.getBook().getCode();
		this.itemName = bookLoan.getBook().getTitle();
		this.broncoID = bookLoan.getStudent().getBroncoID();
		this.studentName = bookLoan.getStudent().getStudentName();
		this.dueDate = bookLoan.getDueDate();
		this.estPrice = bookLoan.getEstimatedPrice();
	}
	
	
	// constructor if loan is docLoan
	public Receipt(DocLoan docLoan) {
		this.loanNumber = docLoan.getLoanNumber();
		this.itemID = docLoan.getDoc().getCode();
		this.itemName = docLoan.getDoc().getTitle();
		this.broncoID = docLoan.getStudent().getBroncoID();
		this.studentName = docLoan.getStudent().getStudentName();
		this.dueDate = docLoan.getDueDate();
		this.estPrice = docLoan.getEstimatedPrice();
	}

	public int getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(int loanNumber) {
		this.loanNumber = loanNumber;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getBroncoID() {
		return broncoID;
	}

	public void setBroncoID(int broncoID) {
		this.broncoID = broncoID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public double getEstPrice() {
		return estPrice;
	}

	public void setEstPrice(double estPrice) {
		this.estPrice = estPrice;
	}
	
	
}
