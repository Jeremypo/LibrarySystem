package hibernate;

import java.sql.SQLException;
//import hibernate.ReturnLoan;
import hibernate.Book;

public class trial {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Student st = new Student(345234, "ciaran", "cc@gmail.com", "music");
		//st.createStudent();
		
		Author au = new Author(5050, "guy fieri", "american", "food");
		//au.createAuthor();
		Book bk = new Book(5678, "flavortown", "recipebook", "foodnetwork", "2010-10-10", 80, "3rd floor", 2.00, true);
		//bk.createBook(au);
		
		
		BookLoan loan = new BookLoan(35, "2023-05-11", "2023-05-20");
		loan.createBookLoan(bk, st);
		
		Book search = new Book();
		search = search.searchBook(5678);
		System.out.println(search);
		
		BookLoan searchLoan = new BookLoan();
		searchLoan = searchLoan.searchBookLoan(35);
		System.out.println(searchLoan);
	}
}
