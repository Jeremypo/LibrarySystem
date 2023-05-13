package hibernate;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.stage.Modality;



public class Main extends Application {
	Scene mainMenu;
	Scene studentRegisterView;
	Scene authorRegisterView;
	Scene AddBookScreen;
	Scene AddDocScreen;
	Scene loanMenu;
	
	Receipt receipt;
	
	public static final int WINDOW = 500;
	public static final int BUTT_WIDTH = 200;
	public static final int BUTT_HEIGHT = 40; 
	public static final String pattern = "yyyy-MM-dd";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		//Main menu----------------------------------------------
		Label leftCol = new Label("Add/Register");
		Label rightCol = new Label("Transactions");
		Button student = new Button("Student");
		Button authDir = new Button("Author/Director");
		Button book = new Button("Book");
		Button doc = new Button("Documentary");
		Button loanBook = new Button("Loan Book/Doc");
		Button generateReport = new Button("Generate Report");
		
		Separator vert = new Separator();
		Separator horiLeft = new Separator();
		Separator horiRight = new Separator();
		
		vert.setOrientation(Orientation.VERTICAL);
		horiLeft.setOrientation(Orientation.HORIZONTAL);
		horiRight.setOrientation(Orientation.HORIZONTAL);
		
		setDime(student);
		setDime(authDir);
		setDime(book);
		setDime(doc);
		setDime(loanBook);
		setDime(generateReport);
		
		VBox leftBox = new VBox(20, leftCol, horiLeft, student, authDir, book, doc);
		VBox rightBox = new VBox(20, rightCol, horiRight, loanBook, generateReport);
		HBox rootMain = new HBox(20, leftBox, vert, rightBox);
		
		leftCol.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
		rightCol.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
		
		rootMain.setAlignment(Pos.CENTER);
		rootMain.setPadding(new Insets(25));
		
		student.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(studentRegisterView);
				stage.show();
			}
		});
		authDir.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(authorRegisterView);
				stage.show();
			}
		});
		book.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(AddBookScreen);
				stage.show();
			}
		});
		doc.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(AddDocScreen);
				stage.show();
			}
		});
		loanBook.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(loanMenu);
				stage.show();
			}
		});


		
		mainMenu = new Scene(rootMain);
		//Main menu----------------------------------------------
		
		
		//Student Register View----------------------------------------------
			//Top
			TextField name_SR = new TextField();
			TextField email_SR = new TextField();
			TextField studentID_SR = new TextField();
			TextField courseID_SR = new TextField();
			//Bottom
			Button back_SR = new Button("Back");
			Button register_SR = new Button("Register");
			
			back_SR.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					stage.setScene(mainMenu);
					stage.show();
				}
			});
			
			register_SR.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					int stuID = Integer.parseInt(studentID_SR.getText());
					String name = name_SR.getText();
					String couID = courseID_SR.getText();
					String email = email_SR.getText();
					
					Student student = new Student(stuID, name, email, couID);
					try {
						student.createStudent();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			name_SR.setPromptText("Insert Student Name");
			email_SR.setPromptText("Insert Student Email");
			studentID_SR.setPromptText("Insert Student ID");
			courseID_SR.setPromptText("Insert Course ID");
			
			name_SR.setPrefSize(200.0, 30.0);
			email_SR.setPrefSize(200.0, 30.0);
			studentID_SR.setPrefSize(200.0, 30.0);
			courseID_SR.setPrefSize(200.0, 30.0);
			back_SR.setPrefSize(200.0, 30.0);
			register_SR.setPrefSize(200.0, 30.0);
			
			VBox vbox_SR = new VBox(20, name_SR, email_SR, studentID_SR, courseID_SR);
			HBox hbox_SR = new HBox(20, back_SR, register_SR);
			
			VBox root_SR = new VBox(20, vbox_SR, hbox_SR);
			root_SR.setPadding(new Insets(25));
			root_SR.setAlignment(Pos.CENTER);
			
			studentRegisterView = new Scene(root_SR);

		//Student Register View----------------------------------------------

		//Author Register View----------------------------------------------
			//Top
			ComboBox<String> select_AR = new ComboBox<>();
			TextField id_AR = new TextField();
			TextField name_AR = new TextField();
			TextField nation_AR = new TextField();
			TextField subject_AR = new TextField();
			//Bottom
			Button back_AR = new Button("Back");
			Button register_AR = new Button("Register");
			
			select_AR.getItems().addAll("Author", "Director");
			
			back_AR.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					stage.setScene(mainMenu);
					stage.show();
				}
			});
			
			register_AR.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if(select_AR.getValue() == null){ 
						select_AR.setValue("Author");
					}
					
					String type = select_AR.getValue();
					int id = Integer.parseInt(id_AR.getText());
					String name = name_AR.getText();
					String nation = nation_AR.getText();
					String subject = subject_AR.getText();
					
					if(type.equals("Author")) {
						Author author = new Author(id, name, nation, subject);
						try {
							author.createAuthor();
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						Director director= new Director(id, name, nation, subject);
						try {
							director.createDirector();
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			});
			
			select_AR.setPromptText("Select Author or Director");
			id_AR.setPromptText("Insert Author ID");
			name_AR.setPromptText("Insert Author Name");
			nation_AR.setPromptText("Insert Author Nationality");
			subject_AR.setPromptText("Insert Author Subject");
			
			select_AR.setPrefSize(200.0, 30.0);
			id_AR.setPrefSize(200.0, 30.0);
			name_AR.setPrefSize(200.0, 30.0);
			nation_AR.setPrefSize(200.0, 30.0);
			subject_AR.setPrefSize(200.0, 30.0);
			back_AR.setPrefSize(200.0, 30.0);
			register_AR.setPrefSize(200.0, 30.0);
			
			VBox vbox_AR = new VBox(20, select_AR, id_AR, name_AR, nation_AR, subject_AR);
			HBox hbox_AR = new HBox(20, back_AR, register_AR);
			
			VBox root_AR = new VBox(20, vbox_AR, hbox_AR);
			root_AR.setPadding(new Insets(25));
			root_AR.setAlignment(Pos.CENTER);
			
			authorRegisterView = new Scene(root_AR);

		//Author Register View----------------------------------------------		
			
		//------------------------------------AddBookScreen----------------------
			TextField bookCodeField = new TextField();
			bookCodeField.setPromptText("Insert Book Code");
			TextField bookTitleField = new TextField();
			bookTitleField.setPromptText("Insert Book Title");
			TextField bookDescField = new TextField();
			bookDescField.setPromptText("Insert Book Description");
			TextField authorField = new TextField();
			authorField.setPromptText("Insert Author Code");
			TextField publisherField = new TextField();
			publisherField.setPromptText("Insert Publisher Name");
			TextField publishDateField = new TextField();
			publishDateField.setPromptText("Insert Publish Date");
			TextField pageCountField = new TextField();
			pageCountField.setPromptText("Insert the Number of Pages");
			TextField bookDailyPriceField = new TextField();
			bookDailyPriceField.setPromptText("Insert the Daily Price");
			TextField bookLocationField = new TextField();
			bookLocationField.setPromptText("Insert the Book's Location");
			
			
			
			Button cbBackButton = new Button("Back");
			Button cbCreateButton = new Button("Create");
			HBox createBookButtons = new HBox(20, cbBackButton, cbCreateButton);
			createBookButtons.setAlignment(Pos.CENTER);
			cbBackButton.setPrefSize(200.0, 30.0);
			cbCreateButton.setPrefSize(200.0, 30.0);
			VBox bookInfo = new VBox(20, bookCodeField, bookTitleField, bookDescField, authorField, publisherField, 
					publishDateField, pageCountField, bookDailyPriceField, createBookButtons);
			bookInfo.setPadding(new Insets(30, 30, 30, 30));
			bookInfo.setAlignment(Pos.CENTER);
			AddBookScreen = new Scene(bookInfo, 500, 500);
			
			
			
			// cbBackButton event handlers
			cbBackButton.setOnAction(event -> {
				stage.setScene(mainMenu);
			});
			
			// creatBookButton event handler
			cbCreateButton.setOnAction(event -> {
				int bookCode = Integer.parseInt(bookCodeField.getText());
				String bookTitle = bookTitleField.getText();
				int authCode_cb = Integer.parseInt(authorField.getText());
				String bookDesc = bookDescField.getText();
				String bookPublisher = publisherField.getText();
				String bookPublishDate = publishDateField.getText();
				int bookPageCount = Integer.parseInt(pageCountField.getText());
				double bookDailyPrice = Double.parseDouble(bookDailyPriceField.getText());
				String bookLocation = bookLocationField.getText();
				boolean bookStatus = true;
				Book book1 = new Book(bookCode, bookTitle, bookDesc, bookPublisher, 
						bookPublishDate, bookPageCount, bookLocation, bookDailyPrice, bookStatus);
				Author author = new Author();
				try {
					book1.createBook(author.searchAuthor(authCode_cb));
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
			});
		//AddBookScreen----------------------------------------------------
			
		//------------------AddDocScreen-------------------------------------
			TextField docCodeField = new TextField();
			docCodeField.setPromptText("Insert Documentary Code");
			TextField docTitleField = new TextField();
			docTitleField.setPromptText("Insert Documentary Title");
			TextField docDescField = new TextField();
			docDescField.setPromptText("Insert Documentary Description");
			TextField directorField = new TextField();
			directorField.setPromptText("Insert Director Code");
			TextField releaseDateField = new TextField();
			releaseDateField.setPromptText("Insert Release Date");
			TextField docLengthField = new TextField();
			docLengthField.setPromptText("Insert the Runtime");
			TextField docDailyPriceField = new TextField();
			docDailyPriceField.setPromptText("Insert the Daily Price");
			TextField docLocationField = new TextField();
			docLocationField.setPromptText("Insert the Documentary's Location");
			
			Button dBackButton = new Button("Back");
			Button dCreateButton = new Button("Create");
			HBox createDocButtons = new HBox(20, dBackButton, dCreateButton);
			createDocButtons.setAlignment(Pos.CENTER);
			
			VBox docInfo = new VBox(20, docCodeField, docTitleField, docDescField, directorField, releaseDateField, docLengthField, docDailyPriceField, createDocButtons);
			docInfo.setPadding(new Insets(30, 30, 30, 30));
			docInfo.setAlignment(Pos.CENTER);
			
			dBackButton.setPrefSize(200.0, 30.0);
			dCreateButton.setPrefSize(200.0, 30.0);
			AddDocScreen = new Scene(docInfo, 500, 500);
			
			
			// dBackButton event handlers
			dBackButton.setOnAction(event -> {
				stage.setScene(mainMenu);
			});
			
			// creatDocButton event handler
			dCreateButton.setOnAction(event -> {
				int docCode = Integer.parseInt(docCodeField.getText());
				String docTitle = docTitleField.getText();
				int dirCode_dc = Integer.parseInt(directorField.getText());
				String docDesc = docDescField.getText();
				String docReleaseDate = releaseDateField.getText();
				int docLength = Integer.parseInt(docLengthField.getText());
				double docDailyPrice = Double.parseDouble(docDailyPriceField.getText());
				String docLocation = docLocationField.getText();
				boolean docStatus = true;
				
				Documentary documentary = new Documentary(docCode, docTitle, docDesc, docReleaseDate, docLength, docLocation, docDailyPrice, docStatus);
				Director director = new Director();
				try {
					documentary.createDocumentary(director.searchDirector(dirCode_dc));
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		//------------------AddDocScreen-------------------------------------	
		
	//Loan Menu----------------------------------------------
		// Set Title
				stage.setTitle("Loan");
				
				// Create the buttons
				
				// Top
				ComboBox<String> select_loan = new ComboBox<>();
				TextField loanID = new TextField();
				TextField itemID = new TextField();
				TextField studentID = new TextField();
				TextField beginDate = new TextField();
				TextField endDate = new TextField();
				TextField estPrice = new TextField("Estimated Price (7-day)");
				
				// Bottom
				Button loanBack = new Button("Back");
				Button calc = new Button("Calculate");
				Button ver = new Button("Verify/Create");
				
				select_loan.getItems().addAll("Book", "Documentary");
				
				
				loanBack.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent arg0) {
						stage.setScene(mainMenu);
						stage.show();
					}
				});
				calc.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent arg0) {
						Double estimated = 0.00;
						if(select_loan.getValue().equals("Book")) {
							Book temp = new Book();
							temp = temp.searchBook(Integer.parseInt(itemID.getText()));
							estimated = temp.getDailyPrice() * 7;
						}
						else {
							Documentary temp = new Documentary();
							temp = temp.searchDocumentary(Integer.parseInt(itemID.getText()));
							estimated = temp.getDailyPrice() * 7;
						}

						estPrice.setText("$" + Double.toString(estimated));
					}
				});
				
				//Receipt Menu----------------------------------------------
				Label loan_r = new Label("Loan #");
				Label item_r = new Label("Item #");
				Label title_r = new Label("Title: "); 
				Label type_r = new Label("Item Type: ");
				Label outDate_r = new Label("Check-Out Date: ");
				Label dueDate_r = new Label("Due Date: ");
				Label estPrice_r = new Label("Estimated Price: $");
				
				Label loanNum_r = new Label();
				Label itemNum_r = new Label();
				Label titleVal_r = new Label();
				Label typeVal_r = new Label();
				Label outVal_r = new Label();
				Label dueVal_r = new Label();
				Label estVal_r = new Label();
				
				VBox left_r = new VBox(20, loan_r, item_r, title_r, type_r, outDate_r, dueDate_r, estPrice_r);
				VBox right_r = new VBox(20, loanNum_r, itemNum_r, titleVal_r, typeVal_r, outVal_r, dueVal_r, estVal_r); 
				HBox box_r = new HBox(20, left_r, right_r);
				
				box_r.setAlignment(Pos.CENTER);
				box_r.setPadding(new Insets(25));
				
				Stage receiptWindow = new Stage();
				receiptWindow.initModality(Modality.APPLICATION_MODAL);
				receiptWindow.initOwner(stage);
				receiptWindow.setTitle("Print Receipt");
				
				Scene popUp = new Scene(box_r);
				receiptWindow.setScene(popUp);
				
				//Scene scene = new Scene();
				
				//Receipt Menu----------------------------------------------
				
				ver.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent arg0) {
						String loanType;
						int loan_id;
						int item_id;
						int stuID_loan;
						String begin_loan;
						String due_loan;
						
						Student tempStu = new Student();
						
						//check which item type
						if(select_loan.getValue() == null){ 
							select_loan.setValue("Book");
						}
						
						loanType = select_loan.getValue();
						loan_id = Integer.parseInt(loanID.getText());
						item_id = Integer.parseInt(itemID.getText());
						stuID_loan = Integer.parseInt(studentID.getText());
						begin_loan = beginDate.getText();
						due_loan = endDate.getText();
						
						if(loanType.equals("Book")) {
							Book tempBook = new Book();
							
							tempBook = tempBook.searchBook(item_id);
							tempStu = tempStu.searchStudent(stuID_loan);
							
							BookLoan bl = new BookLoan(loan_id, begin_loan, due_loan);
							
							try {
								bl.createBookLoan(tempBook, tempStu);
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							receipt = new Receipt(bl);
						}
						else {
							Documentary tempDoc = new Documentary();
							
							tempDoc = tempDoc.searchDocumentary(item_id);
							tempStu = tempStu.searchStudent(stuID_loan);
							
							DocLoan dl = new DocLoan(loan_id, begin_loan, due_loan);
							
							try {
								dl.createDocLoan(tempDoc, tempStu);
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							receipt = new Receipt(dl);
						}
						
						loanNum_r.setText(Integer.toString(receipt.getLoanNumber()));
						itemNum_r.setText(Integer.toString(receipt.getItemID()));
						titleVal_r.setText(receipt.getItemName());
						typeVal_r.setText(select_loan.getValue());
						outVal_r.setText(beginDate.getText());
						dueVal_r.setText(receipt.getDueDate());
						estVal_r.setText(Double.toString(receipt.getEstPrice()));
					
						receiptWindow.showAndWait();
					}
				});			
				
				estPrice.setEditable(false);

				loanID.setPromptText("Insert Loan ID");
				itemID.setPromptText("Insert Item ID");
				studentID.setPromptText("Insert student ID");
				beginDate.setPromptText("Insert Begin Date (i.e. 2023-05-11)");
				endDate.setPromptText("Insert End Date (i.e. 2023-05-11)");

				loanID.setPrefSize(200.0, 30.0);
		        itemID.setPrefSize(200.0, 30.0);
		        studentID.setPrefSize(200.0, 30.0);
		        beginDate.setPrefSize(200.0, 30.0);
		        endDate.setPrefSize(200.0, 30.0);
		        estPrice.setPrefSize(200.0, 30.0);
		        loanBack.setPrefSize(200.0, 30.0);
		        calc.setPrefSize(200.0, 30.0);
		        ver.setPrefSize(200.0, 30.0);
				
				// Put all the buttons into a VBox
				VBox inputs = new VBox(20, select_loan, loanID, itemID, studentID, beginDate, endDate, estPrice);
				
				// Put all buttons in HBox
				HBox butt = new HBox(20, loanBack, calc, ver);
				
				VBox root = new VBox(20, inputs, butt);
				root.setPadding(new Insets(25));
				root.setAlignment(Pos.CENTER);
				
				loanMenu = new Scene(root, WINDOW, WINDOW);
		//Loan Menu----------------------------------------------
				
		

		stage.setResizable(true);
		stage.setTitle("CPP Library");
		stage.setWidth(WINDOW);
		stage.setHeight(WINDOW);
		stage.setScene(mainMenu);
		stage.show();
	}
	
	public static void setDime(Button button) {
		button.setPrefSize(BUTT_WIDTH, BUTT_HEIGHT);
	}
	
	public static void setDime(TextField text) {
		text.setPrefSize(BUTT_WIDTH, BUTT_HEIGHT);
	}
	
}

