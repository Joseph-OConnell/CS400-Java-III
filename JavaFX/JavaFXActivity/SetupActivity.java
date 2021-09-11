import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
// to be used through this activity
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;


public class SetupActivity extends Application {
    @Override
    public void start(final Stage stage) {
        // update this method definition to complete this activity
	Label label = new Label("    The key to making programs fast\n" +
			"    is to make them do practically nothing.\n" +
			"    -- Mike Haertel");	
	Circle circle = new Circle(160, 120, 30);
	Polygon polygon = new Polygon(160, 120, 200, 220, 120, 220);
	Group group = new Group(label, circle, polygon);
        Scene scene = new Scene(group,320,240);
        stage.setScene(scene);
	stage.setTitle("CS 400: The Key");
        stage.show();
    }
    
    public static void main(String[] args) {
        Application.launch();
    }
}
