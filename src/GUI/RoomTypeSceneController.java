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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshRoomTypeTable();
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
                        RoomTypeNameTextField.setText(newValue.getTenLoaiPhong());
                        RoomTypeNameTextField.setUserData(newValue.getMaLoaiPhong());
                        DecimalFormat formatter = new DecimalFormat("#,###");
                        String newValueStr = formatter.format(newValue.getDonGia());
                        RoomTypePriceTextField.setText(newValueStr);
                        RoomTypeBedNumTextField.setText(newValue.getSoLuongGiuong()+"");
                    }
                }
            });
        }
    }
}
