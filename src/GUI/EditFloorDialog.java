package GUI;

import DTO.LauDTO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditFloorDialog {
    private boolean isFloorChanged = false;

    public boolean display(LauDTO lauDTO) {
        Stage window = new Stage();

        window.setMinWidth(400);
        window.setMinHeight(250);
        window.setTitle("Sửa lầu");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(true);
        window.initStyle(StageStyle.UTILITY);

        HBox FloorNameHBox = makeFloorNameHBox(lauDTO);

        HBox confirmHBox = new HBox();
        confirmHBox.setPadding(new Insets(20, 0, 0, 0));
        confirmHBox.setAlignment(Pos.CENTER);
        confirmHBox.setSpacing(10);

        Button acceptButton = new Button("Sửa");
        //acceptButton.setPadding(new Insets(0, 15, 0, 0));
        acceptButton.setTextFill(Color.WHITE);
        acceptButton.setAlignment(Pos.CENTER);
        acceptButton.setMinSize(70, 70);
        acceptButton.setFont(new Font("System", 18));
        acceptButton.setBackground(new Background(new BackgroundFill(Color.rgb(31, 107, 65), CornerRadii.EMPTY, Insets.EMPTY)));
        acceptButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isFloorChanged = true;
                window.close();
            }
        });

        Button cancelButton = new Button("Hủy");
        cancelButton.setTextFill(Color.WHITE);
        cancelButton.setAlignment(Pos.CENTER);
        cancelButton.setMinSize(70, 70);
        cancelButton.setFont(new Font("System", 18));
        cancelButton.setBackground(new Background(new BackgroundFill(Color.rgb(196, 68, 35), CornerRadii.EMPTY, Insets.EMPTY)));
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isFloorChanged = false;
                window.close();
            }
        });

        confirmHBox.getChildren().addAll(acceptButton, cancelButton);

        VBox layout = new VBox(0);
        layout.setPadding(new Insets(0, 0, 20, 0));
        layout.setMaxWidth(600);
        layout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.getChildren().addAll(FloorNameHBox, confirmHBox);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return isFloorChanged;
    }

    private HBox makeFloorNameHBox(LauDTO lauDTO) {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 20, 0, 20));

        Label label = new Label("Tên lầu:");
        label.setFont(new Font("System", 18));
        label.setMinWidth(110);
        label.setTextFill(Color.BLACK);
        label.setPadding(new Insets(0, 10, 0, 0));

        TextField textField = new TextField(lauDTO.getTenLau());
        textField.setFont(new Font("System", 18));

        hBox.getChildren().addAll(label, textField);

        return hBox;
    }
}
