package GUI;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button RoomGridPaneButton;
    public Button CustomerButton;
    public AnchorPane MainScene;
    public Button RoomTypeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RoomGridPaneButton.setGraphic(makeImageView("roomgrid", "", 0, 0, 0, 0));
        CustomerButton.setGraphic(makeImageView("customer", "", 0, 0, 0, 0));
        RoomTypeButton.setGraphic(makeImageView("roomprice", "",0,0,0,0));
        MainScene.setMaxHeight(Double.MAX_VALUE);
        MainScene.setMaxWidth(Double.MAX_VALUE);
        RoomGridPaneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resetDefaultColor();
                changeColorIfSelected(RoomGridPaneButton);
                refreshMainScene("RoomGridPane");
            }
        });
        CustomerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resetDefaultColor();
                changeColorIfSelected(CustomerButton);
            }
        });
        RoomTypeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resetDefaultColor();
                changeColorIfSelected(RoomTypeButton);
                refreshMainScene("RoomTypeScene");
            }
        });
    }

    private void refreshMainScene(String type){
        if(type.equals("RoomGridPane")){
            try {
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("RoomGridPane.fxml"));
                AnchorPane.setTopAnchor(anchorPane, 0.0);
                AnchorPane.setBottomAnchor(anchorPane, 0.0);
                AnchorPane.setLeftAnchor(anchorPane, 0.0);
                AnchorPane.setRightAnchor(anchorPane, 0.0);
                MainScene.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("RoomTypeScene")){
            try {
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("RoomTypeScene.fxml"));
                AnchorPane.setTopAnchor(anchorPane, 0.0);
                AnchorPane.setBottomAnchor(anchorPane, 0.0);
                AnchorPane.setLeftAnchor(anchorPane, 0.0);
                AnchorPane.setRightAnchor(anchorPane, 0.0);
                MainScene.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ImageView makeImageView(String fileName, String toolTip, double topPadding, double rightPadding, double bottomPadding, double leftPadding) {
        File imageFile = new File("resources/" + fileName + ".png");
        Image image = new Image(imageFile.toURI().toString());
        ImageView imageView = new ImageView();
        HBox.setMargin(imageView, new Insets(topPadding, rightPadding, bottomPadding, leftPadding));
        imageView.setImage(image);
        Tooltip.install(imageView, new Tooltip(toolTip));

        return imageView;
    }

    private void changeColorIfSelected(Node node) {
        if (node instanceof Button) {
            Button button = (Button) node;
            button.setBackground(new Background(new BackgroundFill(Color.rgb(148, 148, 148), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    private void resetDefaultColor() {
        RoomGridPaneButton.setBackground(new Background(new BackgroundFill(Color.rgb(226, 226, 226), CornerRadii.EMPTY, Insets.EMPTY)));
        CustomerButton.setBackground(new Background(new BackgroundFill(Color.rgb(226, 226, 226), CornerRadii.EMPTY, Insets.EMPTY)));
        RoomTypeButton.setBackground(new Background(new BackgroundFill(Color.rgb(226, 226, 226), CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
