import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by liuyang on 16-6-18.
 */

public class Main extends Application{
    private BayesClassifier classifier = new BayesClassifier();
    private TextArea textArea = new TextArea();
    private Text status = new Text();
    public static void main(String[] args){ launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(addHBox());
        borderPane.setCenter(addGridPane());

        Scene scene = new Scene(borderPane, 640, 480);
        primaryStage.setTitle("自然语言识别");
        primaryStage.setScene(scene);
        primaryStage.show();
        new Thread(new InitialTask()).start();
    }

    private HBox addHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: powderblue");

        return hBox;
    }

    private GridPane addGridPane(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        textArea.setPrefWidth(300);
        textArea.setWrapText(true);
        Button buttonConfirm = new Button("确定");
        buttonConfirm.setPrefWidth(80);
        buttonConfirm.setOnAction((event)->{ new Thread(new MyTask()).start(); });
        Label statusLabel = new Label("判断结果: ");
        statusLabel.setFont(new Font(statusLabel.getFont().getName(), 20));
        status.setFont(new Font(status.getFont().getName(), 30));

        gridPane.add(textArea, 0, 0, 4, 1);
        gridPane.add(buttonConfirm, 0, 1);
        gridPane.add(statusLabel, 0, 2);
        gridPane.add(status, 3, 2);

        return gridPane;
    }

    class MyTask extends Task{
        @Override
        protected Object call() throws Exception {
            status.setText("处理中...");
            String result = classifier.classify(textArea.getText());
            //String result = String.valueOf(textArea.getText().length());
            status.setText(result);
            return result;
        }
    }

    class InitialTask extends Task{
        @Override
        protected Object call() throws Exception{
            status.setText("初始化中...");
            classifier.classify("你好");
            status.setText("初始化完成");
            return 1;
        }
    }
}
