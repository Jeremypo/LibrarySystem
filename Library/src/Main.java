package hibernate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	Scene mainMenu;
	Scene studentMenu;
	Scene studentRegisterView;
	Scene itemMenu;
	Scene AddBookScreen;
	Scene AddDocScreen;
	Scene loanMenu;
	
	String name;
	String email;
	int stuID;
	int couID;
	
	public static final int WINDOW = 500;
	public static final int BUTT_WIDTH = 175;
	public static final int BUTT_HEIGHT = 75; 
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//Main menu----------------------------------------------
		Button student = new Button("Student");
		Button item = new Button("Item");
		Button document = new Button("Document");
		setDime(student);
		setDime(item);
		setDime(document);
		
		student.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(studentMenu);
				stage.show();
			}
		});
		item.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(itemMenu);
				stage.show();
			}
		});
		document.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(loanMenu);
				stage.show();
			}
		});
		
		VBox vboxMain = new VBox(20, student, item, document);
		vboxMain.setAlignment(Pos.CENTER);
		vboxMain.setPadding(new Insets(25));
		
		mainMenu = new Scene(vboxMain);
		//Main menu----------------------------------------------
		
		
		//Student Menu----------------------------------------------
		Button studentRegister = new Button("Student Register");
		Button studentSearch = new Button("Student Search");
		Button back = new Button("Back");
		setDime(studentRegister);
		setDime(studentSearch);
		setDime(back);
		
		studentRegister.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(studentRegisterView);
				stage.show();
			}
		});
		studentSearch.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				//stage.setScene(itemMenu);
				stage.show();
			}
		});
		back.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(mainMenu);
				stage.show();
			}
		});
		
		VBox top = new VBox(20, studentRegister, studentSearch, back);
		//HBox bot = new HBox(20, back);
		top.setAlignment(Pos.CENTER);
		//bot.setAlignment(Pos.BASELINE_LEFT);
		
		VBox vboxStudent = new VBox(20, top);
		vboxStudent.setAlignment(Pos.CENTER);
		vboxStudent.setPadding(new Insets(25));
		
		studentMenu = new Scene(vboxStudent);
		//Student Menu----------------------------------------------
		
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
					stuID = Integer.parseInt(studentID_SR.getText());
					name = name_SR.getText();
					couID = Integer.parseInt(studentID_SR.getText());
					email = email_SR.getText();
					Student student = new Student(stuID, name, couID, email);
					Staff.createStudent(student);
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
			
		//Item Menu--------------------------------000000---------------------
			Button addBookButton = new Button("Add Book");
			VBox bookButtons = new VBox(20, addBookButton);
			bookButtons.setAlignment(Pos.CENTER);
			Button addDocButton = new Button("Add Documentary");
			VBox docButtons = new VBox(20, addDocButton);
			docButtons.setAlignment(Pos.CENTER);
			HBox bookDocButtons = new HBox(50, bookButtons, docButtons);
			bookDocButtons.setAlignment(Pos.CENTER);
			bookDocButtons.setPadding(new Insets(30, 15, 30, 30));
			
			Button itemBackButton = new Button("Back");
			
			// event handler for add book button
			addBookButton.setOnAction(event -> {
				stage.setScene(AddBookScreen);
			});
			
			// event handler for add doc button
			addDocButton.setOnAction(event -> {
				stage.setScene(AddDocScreen);
			});
			
			// event handler for add doc button
			itemBackButton.setOnAction(event -> {
				stage.setScene(mainMenu);
			});
			
			VBox itemRoot = new VBox(20, bookDocButtons, itemBackButton);
			itemRoot.setAlignment(Pos.CENTER);
			
			itemMenu = new Scene(itemRoot);
		//Item Menu--------------------------------------------------------------	
			
			
		//------------------------------------AddBookScreen----------------------
			TextField bookCodeField = new TextField();
			bookCodeField.setPromptText("Insert Book Code");
			TextField bookTitleField = new TextField();
			bookTitleField.setPromptText("Insert Book Title");
			TextField bookDescField = new TextField();
			bookDescField.setPromptText("Insert Book Description");
			TextField authorField = new TextField();
			authorField.setPromptText("Insert Author Name");
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
			
			VBox bookInfo = new VBox(20, bookCodeField, bookTitleField, bookDescField, authorField, publisherField, 
					publishDateField, pageCountField, bookDailyPriceField, createBookButtons);
			bookInfo.setPadding(new Insets(30, 30, 30, 30));
			
			AddBookScreen = new Scene(bookInfo, 500, 500);
			
			
			
			// cbBackButton event handlers
			cbBackButton.setOnAction(event -> {
				stage.setScene(itemMenu);
			});
			
			// creatBookButton event handler
			cbCreateButton.setOnAction(event -> {
				int bookCode = Integer.parseInt(bookCodeField.getText());
				String bookTitle = bookTitleField.getText();
				String bookAuthor = authorField.getText();
				String bookDesc = bookDescField.getText();
				String bookPublisher = publisherField.getText();
				String bookPublishDate = publishDateField.getText();
				int bookPageCount = Integer.parseInt(pageCountField.getText());
				double bookDailyPrice = Double.parseDouble(bookDailyPriceField.getText());
				String bookLocation = bookLocationField.getText();
				boolean bookStatus = true;
				
				Staff.createBook(bookCode, bookTitle, bookDesc, bookAuthor, bookPublisher, 
						bookPublishDate, bookPageCount, bookLocation, bookDailyPrice, bookStatus);
				
				stage.setScene(itemMenu);
				
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
			directorField.setPromptText("Insert Director Name");
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
			
			
			AddDocScreen = new Scene(docInfo, 500, 500);
			
			
			// dBackButton event handlers
			dBackButton.setOnAction(event -> {
				stage.setScene(itemMenu);
			});
			
			// creatDocButton event handler
			dCreateButton.setOnAction(event -> {
				int docCode = Integer.parseInt(docCodeField.getText());
				String docTitle = docTitleField.getText();
				String docDirector = directorField.getText();
				String docDesc = docDescField.getText();
				String docReleaseDate = releaseDateField.getText();
				int docLength = Integer.parseInt(docLengthField.getText());
				double docDailyPrice = Double.parseDouble(docDailyPriceField.getText());
				String docLocation = docLocationField.getText();
				boolean docStatus = true;
				
				Documentary doc = new Documentary(docCode, docTitle, docDesc, docDirector, docLength, docReleaseDate, docLocation, docDailyPrice, docStatus);
				
				stage.setScene(itemMenu);
			});
		//------------------AddDocScreen-------------------------------------	
		
		//Loan Menu----------------------------------------------
		// Set Title
				stage.setTitle("Loan");
				
				// Create the buttons
				
				// Top
				TextField itemID = new TextField();
				TextField studentID = new TextField();
				TextField beginDate = new TextField();
				TextField endDate = new TextField();
				TextField estPrice = new TextField("Estimated Price");
				
				// Bottom
				Button loanBack = new Button("Back");
				Button calc = new Button("Calculate");
				Button ver = new Button("Verify/Create");
				
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
						estPrice.setText("$20.00");
					}
				});
				ver.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent arg0) {
						int one, two; 
						String three, four;
						one = Integer.parseInt(itemID.getText());
						two = Integer.parseInt(itemID.getText());
						three = beginDate.getText();
						four = endDate.getText();
						
						Loan.createLoan(one, two, three, four);
									
					}
				});
				
				estPrice.setEditable(false);

				itemID.setPromptText("Insert Item ID");
				studentID.setPromptText("Insert student ID");
				beginDate.setPromptText("Insert Begin Date (i.e. 2023-05-11)");
				endDate.setPromptText("Insert End Date (i.e. 2023-05-11)");

		        itemID.setPrefSize(200.0, 30.0);
		        studentID.setPrefSize(200.0, 30.0);
		        beginDate.setPrefSize(200.0, 30.0);
		        endDate.setPrefSize(200.0, 30.0);
		        estPrice.setPrefSize(200.0, 30.0);
		        loanBack.setPrefSize(200.0, 30.0);
		        calc.setPrefSize(200.0, 30.0);
		        ver.setPrefSize(200.0, 30.0);
				
				// Put all the buttons into a VBox
				VBox inputs = new VBox(20, itemID, studentID, beginDate, endDate, estPrice);
				
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
		button.setPrefWidth(BUTT_WIDTH);
		button.setPrefHeight(BUTT_HEIGHT);
	}
}

