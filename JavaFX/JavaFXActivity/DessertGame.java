import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
// to be used through this activity
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import java.util.Random;
import javafx.event.ActionEvent;

public class DessertGame extends Application {
    private int gameScore;

    private void randomizeButtonPositions(Random rand, Button[] btn) {
	    for(int i = 0; i < btn.length; i++){
		    btn[i].setLayoutX(rand.nextInt(601));
		    btn[i].setLayoutY(rand.nextInt(401));
	    }
    }

    @Override
    public void start(final Stage stage) {
        // update this method definition to complete this activity
	BorderPane borderPane = new BorderPane();
	Label score = new Label("Score: 0");
	Button exit = new Button("Exit");
	exit.setOnAction(event -> { Platform.exit(); });
	borderPane.setTop(score);
	borderPane.setBottom(exit);
        borderPane.setAlignment(exit, Pos.BOTTOM_RIGHT);

	//creating the main game
	Pane gameWindow = new Pane();
	Button[] btn = new Button[8];
	btn[0] = new Button("Dessert");
	btn[1] = new Button("Desert");
	btn[2] = new Button("Desert");
	btn[3] = new Button("Desert");
	btn[4] = new Button("Desert");
	btn[5] = new Button("Desert");
	btn[6] = new Button("Desert");
	btn[7] = new Button("Desert");
	for(int i = 0; i < btn.length; i++){
		gameWindow.getChildren().addAll(btn[i]);
	}

	borderPane.setCenter(gameWindow);
	Random rand = new Random();
	randomizeButtonPositions(rand, btn);
	exit.requestFocus();
	// what to do when button is pressed
	gameScore = 0;
	btn[0].setOnAction(event -> { gameScore++;
		score.setText("Score: " + gameScore);
		randomizeButtonPositions(rand,btn);
		exit.requestFocus();
	});

	 btn[1].setOnAction(event -> { gameScore--;
                score.setText("Score: " + gameScore);
                randomizeButtonPositions(rand,btn);
		exit.requestFocus();
        });

	 btn[2].setOnAction(event -> { gameScore--;
                score.setText("Score: " + gameScore);
                randomizeButtonPositions(rand,btn);
		exit.requestFocus();
        });

	 btn[3].setOnAction(event -> { gameScore--;
                score.setText("Score: " + gameScore);
                randomizeButtonPositions(rand,btn);
		exit.requestFocus();
        });

	 btn[4].setOnAction(event -> { gameScore--;
                score.setText("Score: " + gameScore);
                randomizeButtonPositions(rand,btn);
		exit.requestFocus();
        });

	 btn[5].setOnAction(event -> { gameScore--;
                score.setText("Score: " + gameScore);
                randomizeButtonPositions(rand,btn);
		exit.requestFocus();
        });

	 btn[6].setOnAction(event -> { gameScore--;
                score.setText("Score: " + gameScore);
                randomizeButtonPositions(rand,btn);
		exit.requestFocus();
        });

	 btn[7].setOnAction(event -> { gameScore--;
                score.setText("Score: " + gameScore);
                randomizeButtonPositions(rand,btn);
		exit.requestFocus();
        });

	Scene scene = new Scene(borderPane,640,480);
        stage.setScene(scene);
	stage.setTitle("Dessert in the Desert JavaFX Game");
        stage.show();
    }
    
    public static void main(String[] args) {
        Application.launch();
    }
}
