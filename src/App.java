import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    public static void main(String[] args) throws Exception {
        launch();
    }

    @Override
    public void start(Stage arg0) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/MainView.fxml"));
        Scene scene = new Scene(root, 1650, 700);

        arg0.setScene(scene);
        arg0.show();
        
    }
}
