package hibernate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class two extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Two Column App Example");

        // Create the left column (Add/Register)
        VBox leftColumn = new VBox();
        leftColumn.setAlignment(Pos.CENTER);
        leftColumn.setSpacing(10);
        leftColumn.setPadding(new Insets(20));

        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button4 = new Button("4");

        leftColumn.getChildren().addAll(button1, button2, button3, button4);

        // Create the right column (Transactions)
        VBox rightColumn = new VBox();
        rightColumn.setAlignment(Pos.CENTER);
        rightColumn.setSpacing(10);
        rightColumn.setPadding(new Insets(20));

        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button7 = new Button("7");

        rightColumn.getChildren().addAll(button5, button6, button7);

        // Create the vertical line separator
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        // Create the grid pane to hold the columns and separator
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Set the column constraints for the grid pane
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1, col2);

        // Add the left column, separator, and right column to the grid pane
        gridPane.add(leftColumn, 0, 0);
        gridPane.add(separator, 1, 0);
        gridPane.add(rightColumn, 2, 0);

        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}