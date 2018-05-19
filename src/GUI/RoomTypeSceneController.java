package GUI;

import DAO.LoaiPhongDAO;
import DTO.LoaiPhongDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

public class RoomTypeSceneController implements Initializable {
    public TableColumn RoomTypeNameColumn;
    public TableColumn RoomTypePriceColumn;
    public TableColumn RoomTypeBedNumColumn;
    public TableView<LoaiPhongDTO> RoomTypeTable;
    public TextField RoomTypeNameTextField;
    public TextField RoomTypePriceTextField;
    public TextField RoomTypeBedNumTextField;
    public Button AddRoomTypeButton;
    public Button DeleteRoomTypeButton;
    public Button EditRoomTypeButton;
    public Button ClearFieldsButton;
    private double price;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        price = 0;
        refreshRoomTypeTable();
        ClearFieldsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearSelection();
            }
        });

        RoomTypePriceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("^[0-9,]")) {
                    RoomTypePriceTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        RoomTypeBedNumTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("^[0-9]")) {
                    RoomTypeBedNumTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        AddRoomTypeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addRoomType();
            }
        });

        EditRoomTypeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (EditRoomTypeButton.getText().equals("Chỉnh sửa loại phòng")) {
                    enableAllTextField();
                    EditRoomTypeButton.setText("Cập nhật thay đổi");
                } else if (EditRoomTypeButton.getText().equals("Cập nhật thay đổi")) {
                    if (editRoomType()) {
                        EditRoomTypeButton.setText("Chỉnh sửa loại phòng");
                        disableAllTextFields();
                    }
                }
            }
        });

        DeleteRoomTypeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (deleteRoomType()) {
                    clearSelection();
                }
            }
        });
    }

    private void refreshRoomTypeTable() {
        LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
        List<LoaiPhongDTO> loaiPhongDTOList = loaiPhongDAO.getList();

        if (loaiPhongDTOList != null) {
            RoomTypeNameColumn.setCellValueFactory(
                    new PropertyValueFactory<LoaiPhongDTO, String>("TenLoaiPhong"));

            RoomTypePriceColumn.setCellValueFactory(
                    new PropertyValueFactory<LoaiPhongDTO, Double>("DonGia"));

            RoomTypeBedNumColumn.setCellValueFactory(
                    new PropertyValueFactory<LoaiPhongDTO, String>("SoLuongGiuong"));

            RoomTypeTable.setItems(FXCollections.observableList(loaiPhongDTOList));

            RoomTypeTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LoaiPhongDTO>() {
                @Override
                public void changed(ObservableValue<? extends LoaiPhongDTO> observable, LoaiPhongDTO oldValue, LoaiPhongDTO newValue) {
                    if (newValue != null) {
                        DeleteRoomTypeButton.setDisable(false);
                        EditRoomTypeButton.setDisable(false);
                        AddRoomTypeButton.setDisable(true);
                        disableAllTextFields();
                        RoomTypeNameTextField.setText(newValue.getTenLoaiPhong());
                        RoomTypeNameTextField.setUserData(newValue.getMaLoaiPhong());
                        DecimalFormat formatter = new DecimalFormat("#,###");
                        String newValueStr = formatter.format(newValue.getDonGia());
                        RoomTypePriceTextField.setText(newValueStr);
                        RoomTypeBedNumTextField.setText(newValue.getSoLuongGiuong() + "");
                    }
                }
            });
        }
    }

    private void clearSelection() {
        RoomTypeTable.getSelectionModel().clearSelection();
        DeleteRoomTypeButton.setDisable(true);
        EditRoomTypeButton.setDisable(true);
        AddRoomTypeButton.setDisable(false);
        enableAllTextField();
        RoomTypeNameTextField.setText("");
        RoomTypePriceTextField.setText("");
        RoomTypeBedNumTextField.setText("");
    }

    private void disableAllTextFields() {
        RoomTypeNameTextField.setDisable(true);
        RoomTypePriceTextField.setDisable(true);
        RoomTypeBedNumTextField.setDisable(true);
    }

    private void enableAllTextField() {
        RoomTypeNameTextField.setDisable(false);
        RoomTypePriceTextField.setDisable(false);
        RoomTypeBedNumTextField.setDisable(false);
    }

    private void addRoomType() {
        if (RoomTypeNameTextField.getText().isEmpty() || RoomTypePriceTextField.getText().isEmpty() || RoomTypeBedNumTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        LoaiPhongDTO loaiPhongDTO = new LoaiPhongDTO();
        loaiPhongDTO.setTenLoaiPhong(RoomTypeNameTextField.getText());
        loaiPhongDTO.setDonGia(Double.parseDouble(RoomTypePriceTextField.getText()));
        loaiPhongDTO.setSoLuongGiuong(Integer.parseInt(RoomTypeBedNumTextField.getText()));
        LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
        if (loaiPhongDAO.insert(loaiPhongDTO)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thêm thành công!.", ButtonType.OK);
            alert.showAndWait();
            refreshRoomTypeTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Thêm thông tin không thành công! Vui lòng thử lại.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private boolean deleteRoomType() {
        int MaLoaiPhong = (Integer) RoomTypeNameTextField.getUserData();
        LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
        if (loaiPhongDAO.delete(MaLoaiPhong)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Xóa thành công!.", ButtonType.OK);
            alert.showAndWait();
            refreshRoomTypeTable();
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Xóa thông tin không thành công! Vui lòng thử lại.", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
    }

    private boolean editRoomType() {
        if (RoomTypeNameTextField.getText().isEmpty() || RoomTypePriceTextField.getText().isEmpty() || RoomTypeBedNumTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin!", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        LoaiPhongDTO loaiPhongDTO = new LoaiPhongDTO();
        loaiPhongDTO.setMaLoaiPhong((Integer) RoomTypeNameTextField.getUserData());
        loaiPhongDTO.setTenLoaiPhong(RoomTypeNameTextField.getText());
        loaiPhongDTO.setDonGia(Double.parseDouble(RoomTypePriceTextField.getText()));
        loaiPhongDTO.setSoLuongGiuong(Integer.parseInt(RoomTypeBedNumTextField.getText()));
        LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
        if (loaiPhongDAO.update(loaiPhongDTO)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Chỉnh sửa thành công!.", ButtonType.OK);
            alert.showAndWait();
            refreshRoomTypeTable();
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Thêm thông tin không thành công! Vui lòng thử lại.", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
    }
}
