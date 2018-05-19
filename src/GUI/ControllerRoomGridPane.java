package GUI;

import DAO.*;
import DTO.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerRoomGridPane implements Initializable {

    public AnchorPane RoomGridPaneRoot;
    public HBox StatusBarHBox;
    private PhongDAO phongDAO;
    private LoaiPhongDAO loaiPhongDAO;
    private int RentRoomNum = 0;
    private int FreeRoomNum = 0;
    private int NeedCleanRoom = 0;
    private int NeedMaintenanceRoom = 0;
    public GridPane RoomGridPane;

    private ContextMenu FloorContextMenu;
    private ContextMenu RoomContextMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phongDAO = new PhongDAO();
        loaiPhongDAO = new LoaiPhongDAO();

        RoomGridPaneRoot.setMaxWidth(Double.MAX_VALUE);
        RoomGridPaneRoot.setMaxHeight(Double.MAX_VALUE);

        initFloorContextMenu();

        refreshRoomGridPane();

    }

    private void refreshStatusBarHBox() {
        HBox RentRoomStatus = makeStatusHBox("Được thuê", RentRoomNum, 196, 68, 35);
        HBox FreeRoomStatus = makeStatusHBox("Trống", FreeRoomNum, 57, 130, 90);
        HBox NeedCleanStatus = makeStatusHBox("Chưa dọn", NeedCleanRoom, 148, 148, 148);
        HBox NeedMaintenanceStatus = makeStatusHBox("Đang sửa", NeedMaintenanceRoom, 44, 44, 44);

        StatusBarHBox.getChildren().addAll(RentRoomStatus, FreeRoomStatus, NeedCleanStatus, NeedMaintenanceStatus);
    }

    private void initFloorContextMenu() {
        FloorContextMenu = new ContextMenu();
        MenuItem addRoom = new MenuItem("Thêm phòng");
        addRoom.setStyle("-fx-font: 18 System;");
        MenuItem updateRoomNum = new MenuItem("Cập nhật lại số phòng");
        updateRoomNum.setStyle("-fx-font: 18 System;");
        MenuItem editFloor = new MenuItem("Đổi tên lầu");
        editFloor.setStyle("-fx-font: 18 System;");
        FloorContextMenu.getItems().addAll(addRoom, editFloor);
    }

    private void initRoomContextMenu() {
        RoomContextMenu = new ContextMenu();
        MenuItem rentRoom = new MenuItem("Nhận phòng");
        rentRoom.setStyle("-fx-font: 18 System;");
        MenuItem editRoom = new MenuItem("Chỉnh sửa");
        editRoom.setStyle("-fx-font: 18 System;");
        MenuItem deleteRoom = new MenuItem("Xóa phòng");
        deleteRoom.setStyle("-fx-font: 18 System;");
        RoomContextMenu.getItems().addAll(rentRoom, editRoom, deleteRoom);
    }

    private HBox makeStatusHBox(String statusName, int statusNum, int red, int green, int blue) {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 0, 10));

        Label numberLabel = new Label(statusNum + "");
        numberLabel.setFont(new Font("System", 18));
        numberLabel.setTextFill(Color.WHITE);
        numberLabel.setAlignment(Pos.CENTER);
        numberLabel.setMinWidth(50);
        numberLabel.setMinHeight(50);
        numberLabel.setBackground(new Background(new BackgroundFill(Color.rgb(red, green, blue), CornerRadii.EMPTY, Insets.EMPTY)));
        numberLabel.setPadding(new Insets(0, 5, 0, 0));

        Label nameLabel = new Label(statusName);
        nameLabel.setFont(new Font("System", 18));
        nameLabel.setPadding(new Insets(10, 0, 0, 5));
        nameLabel.setTextFill(Color.BLACK);
        nameLabel.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(numberLabel, nameLabel);

        return hBox;
    }


    private void refreshRoomGridPane() {
        RentRoomNum = 0;
        FreeRoomNum = 0;
        NeedCleanRoom = 0;
        NeedMaintenanceRoom = 0;

        while (RoomGridPane.getRowConstraints().size() > 0) {
            RoomGridPane.getRowConstraints().remove(0);
        }

        while (RoomGridPane.getColumnConstraints().size() > 0) {
            RoomGridPane.getColumnConstraints().remove(0);
        }

        LauDAO lauDAO = new LauDAO();
        List<LauDTO> laus = lauDAO.getList();
        if (laus != null) {
            for (int i = 0; i < laus.size(); i++) {
                RowConstraints rowConst = new RowConstraints();
                rowConst.setPercentHeight(100.0 / laus.size());
                RoomGridPane.getRowConstraints().add(rowConst);
            }

            int numCols = 0;
            for (LauDTO k : laus) {
                if (k.getSoPhong() > numCols) {
                    numCols = k.getSoPhong();
                }
            }

            ColumnConstraints colConst1 = new ColumnConstraints();
            colConst1.setPercentWidth(10.0);
            RoomGridPane.getColumnConstraints().add(colConst1);

            for (int i = 0; i < numCols; i++) {
                ColumnConstraints colConst = new ColumnConstraints();
                colConst.setPercentWidth(90.0 / numCols);
                RoomGridPane.getColumnConstraints().add(colConst);
            }

            for (int i = 0; i < laus.size(); i++) {
                LauDTO lauDTO = laus.get(i);

                Label label = new Label(lauDTO.getTenLau());
                label.setFont(new Font("System", 22));
                label.setTextFill(Color.WHITE);
                label.setMaxHeight(Double.MAX_VALUE);
                label.setMaxWidth(Double.MAX_VALUE);
                label.setBorder(new Border(new BorderStroke(Color.WHITE,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                label.setBackground(new Background(new BackgroundFill(Color.rgb(54, 54, 54), CornerRadii.EMPTY, Insets.EMPTY)));
                label.setPadding(new Insets(5, 5, 5, 5));
                label.setAlignment(Pos.CENTER);
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        FloorContextMenu.show(label, event.getScreenX(), event.getScreenY());
                        FloorContextMenu.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                AddRoomDialog addRoomDialog = new AddRoomDialog();
                                boolean result = addRoomDialog.display(lauDTO);
                                if (result) {
                                    refreshRoomGridPane();
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thêm thành công! ", ButtonType.OK);
                                    alert.showAndWait();
                                }
                                System.out.println(result);
                            }
                        });
                        FloorContextMenu.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                lauDAO.refresh(lauDTO);
                            }
                        });
                        FloorContextMenu.getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                EditFloorDialog editFloorDialog = new EditFloorDialog();
                                boolean result = editFloorDialog.display(lauDTO);
                                System.out.println(result);
                            }
                        });
                    }
                });
                RoomGridPane.addRow(i, label);

                List<PhongDTO> phongDTOList = phongDAO.get(lauDTO.getMaLau());
                if (phongDTOList != null) {
                    for (PhongDTO k : phongDTOList) {
                        VBox vBox = makeRoomVBox(k);
                        RoomGridPane.addRow(i, vBox);
                    }
                }
            }

            refreshStatusBarHBox();
        }
    }

    private VBox makeRoomVBox(PhongDTO phongDTO) {
        VBox vBox = new VBox();

        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(57, 130, 90), CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setBorder(new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Label labelTenPhong = new Label(phongDTO.getTenPhong());
        labelTenPhong.setFont(new Font("System", 22));
        labelTenPhong.setTextFill(Color.WHITE);
        labelTenPhong.setMaxWidth(Double.MAX_VALUE);
        labelTenPhong.setAlignment(Pos.CENTER);
        labelTenPhong.setPadding(new Insets(40, 0, 5, 0));
        labelTenPhong.setBackground(new Background(new BackgroundFill(Color.rgb(57, 130, 90), CornerRadii.EMPTY, Insets.EMPTY)));

        vBox.getChildren().add(labelTenPhong);

        LoaiPhongDTO loaiPhongDTO = loaiPhongDAO.getByID(phongDTO.getMaLoaiPhong());
        Label labelLoaiPhong = new Label(loaiPhongDTO.getTenLoaiPhong());
        labelLoaiPhong.setFont(new Font("System", 16));
        labelLoaiPhong.setTextFill(Color.WHITE);
        labelLoaiPhong.setMaxWidth(Double.MAX_VALUE);
        labelLoaiPhong.setAlignment(Pos.CENTER);
        labelLoaiPhong.setPadding(new Insets(10, 0, 5, 0));
        labelLoaiPhong.setBackground(new Background(new BackgroundFill(Color.rgb(57, 130, 90), CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(labelLoaiPhong);

        if (phongDTO.getTinhTrangPhong().equals("Được thuê")) {
            RentRoomNum++;

            labelTenPhong.setBackground(new Background(new BackgroundFill(Color.rgb(196, 68, 35), CornerRadii.EMPTY, Insets.EMPTY)));
            labelLoaiPhong.setBackground(new Background(new BackgroundFill(Color.rgb(196, 68, 35), CornerRadii.EMPTY, Insets.EMPTY)));
            vBox.setBackground(new Background(new BackgroundFill(Color.rgb(196, 68, 35), CornerRadii.EMPTY, Insets.EMPTY)));

            PhieuThuePhongDAO phieuThuePhongDAO = new PhieuThuePhongDAO();
            PhieuThuePhongDTO phieuThuePhongDTO = phieuThuePhongDAO.get(phongDTO.getMaPhong());

            CTPhieuThuePhongDAO ctPhieuThuePhongDAO = new CTPhieuThuePhongDAO();
            List<CTPhieuThuePhongDTO> ctPhieuThuePhongDTOList = ctPhieuThuePhongDAO.get(phieuThuePhongDTO.getMaPhieuThuePhong());

            List<KhachHangDTO> khachHangDTOList = null;

            if (ctPhieuThuePhongDTOList != null) {
                Label KhachHangLabel = new Label("(" + ctPhieuThuePhongDTOList.size() + ") ");
                KhachHangLabel.setFont(new Font("System", 18));
                KhachHangLabel.setTextFill(Color.WHITE);
                KhachHangLabel.setAlignment(Pos.CENTER);
                KhachHangLabel.setMaxWidth(Double.MAX_VALUE);
                KhachHangLabel.setBackground(new Background(new BackgroundFill(Color.rgb(196, 68, 35), CornerRadii.EMPTY, Insets.EMPTY)));

                khachHangDTOList = new ArrayList<KhachHangDTO>();
                KhachHangDAO khachHangDAO = new KhachHangDAO();
                int i = 0;
                for (CTPhieuThuePhongDTO k : ctPhieuThuePhongDTOList) {
                    KhachHangDTO khachHangDTO = khachHangDAO.get(k.getMaKhachHang());
                    KhachHangLabel.setText(KhachHangLabel.getText() + khachHangDTO.getTenKhachHang());
                    khachHangDTOList.add(khachHangDTO);
                    i++;
                    if (i != ctPhieuThuePhongDTOList.size()) {
                        KhachHangLabel.setText(KhachHangLabel.getText() + ", ");
                    }
                }

                VBox.setVgrow(KhachHangLabel, Priority.ALWAYS);
                vBox.getChildren().add(KhachHangLabel);
            }
        } else if (phongDTO.getTinhTrangPhong().equals("Chưa dọn")) {
            NeedCleanRoom++;

            ImageView imageView = makeImageView("cleanroom", "", 0, 0, 0, 0);
            vBox.getChildren().add(0, imageView);
            vBox.setBackground(new Background(new BackgroundFill(Color.rgb(148, 148, 148), CornerRadii.EMPTY, Insets.EMPTY)));
            labelTenPhong.setPadding(new Insets(0, 0, 5, 0));
            labelTenPhong.setBackground(new Background(new BackgroundFill(Color.rgb(148, 148, 148), CornerRadii.EMPTY, Insets.EMPTY)));
            labelLoaiPhong.setBackground(new Background(new BackgroundFill(Color.rgb(148, 148, 148), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (phongDTO.getTinhTrangPhong().equals("Trống")) {
            FreeRoomNum++;
        } else if (phongDTO.getTinhTrangPhong().equals("Đang sửa")) {
            NeedMaintenanceRoom++;

            ImageView imageView = makeImageView("needmaintenance", "", 0, 0, 0, 0);
            vBox.getChildren().add(0, imageView);
            vBox.setBackground(new Background(new BackgroundFill(Color.rgb(44, 44, 44), CornerRadii.EMPTY, Insets.EMPTY)));
            labelTenPhong.setPadding(new Insets(0, 0, 5, 0));
            labelTenPhong.setBackground(new Background(new BackgroundFill(Color.rgb(44, 44, 44), CornerRadii.EMPTY, Insets.EMPTY)));
            labelLoaiPhong.setBackground(new Background(new BackgroundFill(Color.rgb(44, 44, 44), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        return vBox;
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
}
