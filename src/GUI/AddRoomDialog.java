package GUI;

import DAO.LauDAO;
import DAO.LoaiPhongDAO;
import DAO.PhongDAO;
import DTO.LauDTO;
import DTO.LoaiPhongDTO;
import DTO.PhongDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

import java.util.List;

public class AddRoomDialog {
    private boolean isNewRoorAdded = false;

    private TextField RoomNameTextField;
    private ComboBox<LauDTO> lauDTOComboBox;
    private ComboBox<LoaiPhongDTO> loaiPhongDTOComboBox;
    private ComboBox<String> RoomStatusComboBox;
    private TextArea RoomDescriptionTextArea;

    public boolean display(LauDTO lauDTO) {
        Stage window = new Stage();

        window.setMinWidth(610);
        window.setMinHeight(550);
        window.setTitle("Thêm phòng");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(true);
        window.initStyle(StageStyle.UTILITY);

        HBox RoomNameHBox = makeRoomNameHBox();
        HBox FloorHBox = makeFloorHBox(lauDTO);
        HBox RoomTypeHBox = makeRoomTypeHBox();
        HBox RoomStatusHBox = makeRoomStatusHBox();
        HBox RoomDescriptionHBox = makeRoomDescriptionHBox();

        HBox confirmHBox = new HBox();
        confirmHBox.setPadding(new Insets(20, 0, 0, 0));
        confirmHBox.setAlignment(Pos.CENTER);
        confirmHBox.setSpacing(10);

        Button acceptButton = new Button("OK");
        acceptButton.setTextFill(Color.WHITE);
        acceptButton.setAlignment(Pos.CENTER);
        acceptButton.setMinSize(70, 70);
        acceptButton.setFont(new Font("System", 18));
        acceptButton.setBackground(new Background(new BackgroundFill(Color.rgb(31, 107, 65), CornerRadii.EMPTY, Insets.EMPTY)));
        acceptButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(RoomNameTextField.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Tên loại phòng không được để trống! ", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }

                PhongDTO phongDTO = new PhongDTO();
                phongDTO.setTenPhong(RoomNameTextField.getText());
                phongDTO.setMaLoaiPhong(loaiPhongDTOComboBox.getSelectionModel().getSelectedItem().getMaLoaiPhong());
                phongDTO.setMaLau(lauDTOComboBox.getSelectionModel().getSelectedItem().getMaLau());
                phongDTO.setTinhTrangPhong(RoomStatusComboBox.getSelectionModel().getSelectedItem().toString());
                if(RoomDescriptionTextArea.getText().isEmpty()){
                    phongDTO.setGhiChu("");
                }

                PhongDAO phongDAO = new PhongDAO();
                if(phongDAO.insert(phongDTO)){
                    isNewRoorAdded = true;
                    lauDTO.setSoPhong(lauDTO.getSoPhong()+1);
                    LauDAO lauDAO = new LauDAO();
                    lauDAO.update(lauDTO);
                }else {
                    isNewRoorAdded = false;
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Thêm không thành công! Vui lòng thử lại. ", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
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
                isNewRoorAdded = false;
                window.close();
            }
        });

        confirmHBox.getChildren().addAll(acceptButton, cancelButton);

        VBox layout = new VBox(0);
        layout.setPadding(new Insets(0, 0, 20, 0));
        layout.setMaxWidth(600);
        layout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.getChildren().addAll(RoomNameHBox, FloorHBox, RoomTypeHBox, RoomStatusHBox, RoomDescriptionHBox, confirmHBox);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return isNewRoorAdded;
    }

    private HBox makeRoomNameHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 20, 0, 20));

        Label label = new Label("Tên phòng:");
        label.setFont(new Font("System", 18));
        label.setMinWidth(110);
        label.setTextFill(Color.BLACK);
        label.setPadding(new Insets(5, 10, 0, 0));

        RoomNameTextField = new TextField();
        RoomNameTextField.setFont(new Font("System", 18));

        hBox.getChildren().addAll(label, RoomNameTextField);

        return hBox;
    }

    private HBox makeFloorHBox(LauDTO lauDTO) {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 20, 0, 20));

        Label label = new Label("Tầng:");
        label.setMinWidth(110);
        label.setFont(new Font("System", 18));
        label.setTextFill(Color.BLACK);
        label.setPadding(new Insets(5, 10, 0, 0));

        hBox.getChildren().add(label);

        LauDAO lauDAO = new LauDAO();
        List<LauDTO> lauDTOList = lauDAO.getList();
        if (lauDTOList != null) {
            lauDTOComboBox = new ComboBox<LauDTO>(FXCollections.observableList(lauDTOList));
            lauDTOComboBox.setConverter(new StringConverter<LauDTO>() {
                @Override
                public String toString(LauDTO object) {
                    return object.getTenLau();
                }

                @Override
                public LauDTO fromString(String string) {
                    return null;
                }
            });
            lauDTOComboBox.setStyle("-fx-font: 20px \"System\";");
            lauDTOComboBox.getSelectionModel().select(lauDTO);
            hBox.getChildren().add(lauDTOComboBox);
        }

        return hBox;
    }

    private HBox makeRoomTypeHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 20, 0, 20));

        Label label = new Label("Loại phòng:");
        label.setMinWidth(110);
        label.setFont(new Font("System", 18));
        label.setTextFill(Color.BLACK);
        label.setPadding(new Insets(5, 10, 0, 0));

        hBox.getChildren().add(label);

        LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
        List<LoaiPhongDTO> loaiPhongDTOList = loaiPhongDAO.getList();
        if (loaiPhongDTOList != null) {
            loaiPhongDTOComboBox = new ComboBox<LoaiPhongDTO>(FXCollections.observableList(loaiPhongDTOList));
            loaiPhongDTOComboBox.setConverter(new StringConverter<LoaiPhongDTO>() {
                @Override
                public String toString(LoaiPhongDTO object) {
                    return object.getTenLoaiPhong();
                }

                @Override
                public LoaiPhongDTO fromString(String string) {
                    return null;
                }
            });
            loaiPhongDTOComboBox.setStyle("-fx-font: 20px \"System\";");
            loaiPhongDTOComboBox.getSelectionModel().selectFirst();
            hBox.getChildren().add(loaiPhongDTOComboBox);
        }

        return hBox;
    }

    private HBox makeRoomStatusHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 20, 0, 20));

        Label label = new Label("Trạng thái:");
        label.setMinWidth(110);
        label.setFont(new Font("System", 18));
        label.setTextFill(Color.BLACK);
        label.setPadding(new Insets(5, 10, 0, 0));

        ObservableList<String> data = FXCollections.observableArrayList("Trống", "Được thuê", "Chưa dọn", "Đang sửa");
        RoomStatusComboBox = new ComboBox<>(data);
        RoomStatusComboBox.setStyle("-fx-font: 20px \"System\";");
        RoomStatusComboBox.getSelectionModel().selectFirst();

        hBox.getChildren().addAll(label, RoomStatusComboBox);

        return hBox;
    }

    private HBox makeRoomDescriptionHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 20, 0, 20));

        Label label = new Label("Ghi chú:");
        label.setMinWidth(110);
        label.setFont(new Font("System", 18));
        label.setTextFill(Color.BLACK);
        label.setPadding(new Insets(5, 10, 0, 0));

        RoomDescriptionTextArea = new TextArea();
        RoomDescriptionTextArea.setWrapText(true);
        RoomDescriptionTextArea.setFont(new Font("System", 20));
        RoomDescriptionTextArea.setPrefRowCount(5);


        hBox.getChildren().addAll(label, RoomDescriptionTextArea);

        return hBox;
    }


}
