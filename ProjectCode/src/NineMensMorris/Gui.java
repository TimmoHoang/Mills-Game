package NineMensMorris;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/// All Structure extended from Application are static. That was why we could not use the non-static methods.
public class Gui extends Application{
    private Stage window;
    private Scene gamePage;
    private Scene menuPage;
    private Scene instructionsPage;
	private static Game.GamePlay myGame = new Game.GamePlay();
    private static String currentPlayer = "R";
    private Cell[][] cell = new Cell[7][7];
    public static VBox player1, player2;
    public static String getCurrentPlayer() { return currentPlayer; }
    public static Game.GamePlay getMyGame() { return myGame; }

    public static void setCurrentPlayer(String p) { currentPlayer = p; }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("9 Men Morris");

        initializeMenu();
        initializeInstructionsPage();
        initializeGamePage();
        goToMenu();
    }

    // sets the window scene to menuPage
    private void goToMenu(){
        window.setScene(menuPage);
        window.show();
    }

    private void initializeMenu(){
        VBox menuLayout = new VBox(20);
        menuLayout.setSpacing(10);
        menuLayout.setPadding(new Insets(0, 20, 10, 20));
        menuLayout.setAlignment(Pos.CENTER);

        Label menuDesc = new Label("Welcome to 9 Men Morris");
        Button startGame = new Button("Start Game");
        startGame.setOnMouseClicked(e -> window.setScene(gamePage));
        Button instructions = new Button("How to play");
        instructions.setOnMouseClicked(e -> window.setScene(instructionsPage));
        menuLayout.getChildren().addAll(menuDesc, startGame, instructions);
        menuPage = new Scene(menuLayout, 500, 500);
    }

    private void initializeInstructionsPage(){
        String instructions = "How to play 9 Men Morris:\n" +
                "Place pieces\n" +
                "Make mills\n" +
                "Remove the other player's pieces\n" +
                "Move your pieces\n";
        Label instructionsDesc = new Label(instructions);
        VBox pageLayout = new VBox(20);
        pageLayout.setAlignment(Pos.CENTER);
        pageLayout.setSpacing(10);
        Button menuReturn = new Button("Return to Menu");
        menuReturn.setOnMouseClicked(e -> window.setScene(menuPage));
        Button startGame = new Button("Start Game");
        startGame.setOnMouseClicked(e -> window.setScene(gamePage));
        pageLayout.getChildren().addAll(instructionsDesc, menuReturn, startGame);
        instructionsPage = new Scene(pageLayout, 500, 500);
    }

    private void initializeGamePage(){
        //Layout initialization for game
        BorderPane border = new BorderPane();
        player1 = addVBox("Player1", 9);
        player2 = addVBox("Player2", 9);
        GridPane gridpane = addGridPane();

        border.setLeft(player1);
        border.setCenter(gridpane);
        border.setRight(player2);
        gamePage = new Scene(border, 850, 650);
    }

    // Add to draw/visual class
    public VBox addVBox(String name, int num) {
    	//makes and returns vertical boxes for the players
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(8);

        Text title = new Text(name);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        vbox.getChildren().add(title);

        for (int i = 1; i <= num; i++){
           if(name.equals("Player1")){
               Ellipse ellipse = new Ellipse(20,20,20,20);
               ellipse.setStroke(Color.BLACK);
               ellipse.setFill(Style.darkRed);
               vbox.getChildren().add(ellipse);
           }else{
               Ellipse ellipse = new Ellipse(20,20,20,20);
               ellipse.setStroke(Color.BLACK);
               ellipse.setFill(Style.darkBlue);
               vbox.getChildren().add(ellipse);
           }
        }
        return vbox;
    }

    //Method to remove displayed pieces on each player's size one the piece is placed.
    public static void removeVBoxElement(String id) {

        if (id == "R") {
            ///Some how index zero is the actualy player's name "Player1/Player2" Please solve this.
            //player1.getChildren().remove(1);
            Node theNode = null;
            for (Node node : player1.getChildren()) {
                if (node instanceof Ellipse) {
                    theNode = node;
                    break;
                }
            }
            if (theNode != null) {player1.getChildren().remove(theNode); }
        }
        if (id == "B") {
            //player2.getChildren().remove(1);
            Node theNode = null;
            for (Node node : player2.getChildren()) {
                if (node instanceof Ellipse) {
                    theNode = node;
                    break;
                }
            }
            if (theNode != null) {player2.getChildren().remove(theNode); }
        }
    }

    // Creates 7x7 grid and returns it
    public GridPane addGridPane() {
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(10,10,10,10));

        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                cell[i][j] =  new Cell(i, j);
                gridpane.add(cell[i][j], i, j);
            }
        }
        return gridpane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
