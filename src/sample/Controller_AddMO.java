package sample;

import Connectvy.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DoubleStringConverter;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

public class Controller_AddMO implements Initializable {

    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);

    ConnectionClass connectionClass = new ConnectionClass();
    // we call conneClass  that we make it up
    Connection connection = connectionClass.getConnection();

    @FXML
    private JFXDatePicker Date_Warranty_AddMO;
    @FXML
    private JFXDatePicker Date_StartMo_AddMO;
    @FXML
    private JFXDatePicker Date_EndMO_AddMO;
    @FXML
    private JFXTextField Txfiled_SPCost_AddMO;
    @FXML
    private JFXTextField Txfiled_MOCost_AddMO;
    @FXML
    private JFXTextField Txfiled_VAT_AddMO;
    @FXML
    private JFXTextField Txfiled_TotalCost_AddMO;
    @FXML
    private JFXTextField Txfiled_MOnum_AddMO;
    @FXML
    private JFXTextArea Txfiled_ProplemDisc_AddMO;
    @FXML
    private ComboBox<String> Selct_Techichan_AddMO;
    @FXML
    private ComboBox<String> Selct_MoStatus_AddMO;
    ObservableList<String> ListOfStatus_EN = FXCollections.observableArrayList("created", "approved", "disapproved",
            "cannot be done", "under maintenance", "other defects has been detected", "repaired", "paid");
    ObservableList<String> ListOfStatus_AR = FXCollections.observableArrayList("تم الإنشاء", "تم الموافقة", "مرفوضة",
            "لا يمكن القيام بعملية الصيانة", "تحت الصيانة", "تم الكشف عن عيوب أخرى", "تم الاصلاح", "دفعت");

    ObservableList<String> ListOfTechichan = FXCollections.observableArrayList();

    @FXML
    private JFXTextField Txfiled_SpSerialN_AddMO;
    @FXML
    private JFXTextField Txfiled_SearchSP_AddMO;
    @FXML
    public JFXTextField Txfiled_CusName_AddMO;
    @FXML
    private JFXTextField Txfiled_CusMnum_AddMO;
    @FXML
    private JFXTextField Txfiled_DevSerialN_AddMO;
    @FXML
    private JFXTextField Txfiled_DevDiscription_AddMO;
    @FXML
    private JFXButton Btn_Print_AddMo;
    @FXML
    private JFXButton Btn_Cancle_AddMo;
    @FXML
    private JFXButton Btn_Delete_AddMo;
    @FXML
    private JFXButton Btn_Save_AddMo;
    @FXML
    private JFXButton Btn_Search_AddMo;
    @FXML
    private TableView<Controller_AddMO.AddSP> Table_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, String> Col_SPdisc_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, String> Col_SPname_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, Integer> Col_SPnum_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, Double> Col_SPprice_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, Double> Col_SPQuantity_AddSP_AddMO;

    ObservableList<Controller_AddMO.AddSP> list = FXCollections.observableArrayList();
    ObservableList<Controller_AddMO.SelectedSP> list2 = FXCollections.observableArrayList();
    ObservableList<Controller_AddMO.SelectedSP> loadlist = FXCollections.observableArrayList();
    @FXML
    private TableView<Controller_AddMO.SelectedSP> Table_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, String> Col_SPdisc_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, String> Col_SPname_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, Integer> Col_SPnum_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, String> Col_SPSN_SelectedSP_AddMO;

    ObservableList<Controller_AddMO.SelectedSP> SPSelected2, AllSP2;
    int count = 0;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, Double> Col_SPprice_SelectedSP_AddMO;
    @FXML
    private JFXButton Btn_ReomveSP_AddMo;
    @FXML
    private JFXButton Btn_AddSP_AddMo;
    @FXML
    private JFXTextField Txfiled_SpPrice_AddMO;
    private int count_Language;

    @FXML
    private void M_Txfiled_SpSerialN_AddMO(ActionEvent event) throws SQLException {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        AllSP2 = Table_SelectedSP_AddMO.getItems();
        SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();
        //  SPSelected2.get(0).SP_SNProperty("");
        //Txfiled_SpSerialN_AddMO.getText();
        double perSp_price = Double.parseDouble(Txfiled_SpPrice_AddMO.getText());

        for (int i = 0; i < SPSelected2.size(); i++) {
            loadlist.add(new Controller_AddMO.SelectedSP(SPSelected2.get(i).getSP2_Number(), SPSelected2.get(i).getSP2_Name(),
                    SPSelected2.get(i).getSP2_Description(), perSp_price, Txfiled_SpSerialN_AddMO.getText(), SPSelected2.get(i).getSP_Seq_Nber()));

            String sqlupdateAddSP = "UPDATE `require` SET `SERIAL_NUMBER` = '" + Txfiled_SpSerialN_AddMO.getText() + "', `Effective_Price` ='" + Txfiled_SpPrice_AddMO.getText() + "' WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText()
                    + " AND SP_NBER=" + SPSelected2.get(i).getSP2_Number() + " AND Seq_Nber=" + SPSelected2.get(i).getSP_Seq_Nber();
            System.out.println(sqlupdateAddSP);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlupdateAddSP);

            SPSelected2.forEach(loadlist::remove);
            Table_SelectedSP_AddMO.getItems().setAll(loadlist);
            calculate();

            String sqlupdatePrice = "UPDATE `maintenance_operation` SET `MO_COST` = " + Txfiled_MOCost_AddMO.getText() + ", `SP_COST` = " + Txfiled_SPCost_AddMO.getText() + " WHERE `MO_NBER` = " + Txfiled_MOnum_AddMO.getText() + ";";

            statement1.executeUpdate(sqlupdatePrice);
            //JOptionPane.showMessageDialog(null, "تم تعديل سعر قطعة الغيار", "Alert", JOptionPane.INFORMATION_MESSAGE);

            if (count_Language == 0) {
                Txfiled_SpSerialN_AddMO.setText("Serial number");
                Txfiled_SpPrice_AddMO.setText("Cost");
                //alert2.setContentText("The price of the spare part has been adjusted");
     
                alert2.setContentText("Changes saved successfully");
            } else {
                Txfiled_SpSerialN_AddMO.setText("الرقم التسلسلي");
                Txfiled_SpPrice_AddMO.setText("السعر");
                alert2.setContentText("تم حفظ التعديلات بنجاح");

            }

            alert2.showAndWait();
        }
    }

    public void SetMoStatus_language(int c) {

        count_Language = c;

        if (c == 0) {

            Selct_MoStatus_AddMO.setItems(ListOfStatus_EN);

        } else if (c == 1) {

            Selct_MoStatus_AddMO.setItems(ListOfStatus_AR);

        }

    }

    @FXML
    private void M_MousClicked_TabelSelecSP_AddMO(MouseEvent event) {

        SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
        Txfiled_SpPrice_AddMO.setText(String.valueOf(SPSelected2.get(0).getSP_Price2()));

        //Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
    }

    @FXML
    private void M_Btn_ReomveSP_AddMo(ActionEvent event) throws SQLException {
        alert2.setTitle(null);
        alert2.setHeaderText(null);

        AllSP2 = Table_SelectedSP_AddMO.getItems();
        SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
        //_____________________
        //for (AddSP addSP : Table_AddSP_AddMO.getSelectionModel().getSelectedItems()) {
        for (int i = 0; i < SPSelected2.size(); i++) {
            //for(int i =1;i<=1; i++){
            //list.clear();
            // list.add(new Controller_AddMO.AddSP(SPSelected2.get(i).getSP2_Number(), SPSelected2.get(i).getSP2_Name(), SPSelected2.get(i).getSP2_Description(), 
            //SPSelected2.get(i).getSP_Price2()));
            // Table_AddSP_AddMO.setItems(list);
            String sqlDeletSP = "DELETE FROM `require` " + " WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText() + " AND SP_NBER=" + SPSelected2.get(i).getSP2_Number()
                    + " AND Seq_Nber=" + SPSelected2.get(i).getSP_Seq_Nber();
            System.out.println(sqlDeletSP);
            //SPSelected2.get(i).ge
            String sqlupdateSP = "UPDATE `spare_parts` SET `SP_Quantity` = SP_Quantity+1 WHERE `spare_parts`.`SP_NBER` =" + SPSelected2.get(i).getSP2_Number();

            System.out.println(sqlupdateSP);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlDeletSP);
            statement1.executeUpdate(sqlupdateSP);

            //JOptionPane.showMessageDialog(null, "تم ازالة  " + SPSelected2.get(0).getSP2_Name() + "من عملية الصيانة", "Alert", JOptionPane.INFORMATION_MESSAGE);
            if (count_Language == 0) {
                alert2.setContentText("The " + SPSelected2.get(0).getSP2_Name() + " has been removed from the MO");
            } else {
                alert2.setContentText("تم ازالة  " + SPSelected2.get(0).getSP2_Name() + "من عملية الصيانة");

            }

            alert2.showAndWait();
        }
        //loadData();
        //}

        SPSelected2.forEach(loadlist::remove);
        Table_SelectedSP_AddMO.getItems().setAll(loadlist);
        loadData();
        calculate();
        String sqlupdatePrice = "UPDATE `maintenance_operation` SET `MO_COST` = " + Txfiled_MOCost_AddMO.getText() + ", `SP_COST` = " + Txfiled_SPCost_AddMO.getText() + " WHERE `MO_NBER` = " + Txfiled_MOnum_AddMO.getText() + ";";
        java.sql.Statement statement1 = connection.createStatement();
        statement1.executeUpdate(sqlupdatePrice);
        //DELETE FROM `require` WHERE `require`.`MO_NBER` = 7 AND `require`.`SP_NBER` = 3;

        // Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
    }
    //DELETE FROM `require` WHERE `SERIAL_NUMBER`=HH-2fC

    /*private void M_Txfiled_SearchSP_AddMO(ActionEvent event) {
        System.out.println(event.getEventType().toString());
        list.clear();

        String id1 = Txfiled_SearchSP_AddMO.getText();
        if (Txfiled_SearchSP_AddMO.getText().isEmpty()){
            String sql1 ="SELECT * FROM spare_parts";
            System.out.println(sql1);
            Search(sql1);
            

        }else {
            String sql1 = "SELECT * FROM spare_parts WHERE SP_NBRE = '" + id1 + "'";
            String trysql = "SELECT * FROM spare_parts WHERE SP_NBRE LIKE '"+id1+"%';";
            System.out.println(trysql);
            Search(trysql);

        }}*/
    String INVOICE_NBER="DD";
   
          //String INVOICE_NBER3=null;

    public void Search_MO(int MO) throws SQLException {

        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE MO_NBER = " + MO);

        ResultSet rs = st.getResultSet();
        //st = connection.prepareCall(sql);

        if (rs.first()) {
            
 INVOICE_NBER=rs.getString("INVOICE_NBER");
            System.out.println("i am ="+INVOICE_NBER);
            System.out.println(Txfiled_MOnum_AddMO.getText());

            System.out.println("INVOICE_NBER======"+INVOICE_NBER+".");
            
           // System.out.println("INVOICE_NBER======333333333"+INVOICE_NBER3+".");
            System.out.println("THIS MO NUMBER IN DB== " + rs.getString("MO_NBER"));
            System.out.println("THIS MO NUMBER IN FILED== " + Txfiled_MOnum_AddMO.getText());

            count = 2;
            Txfiled_MOnum_AddMO.setText(String.valueOf(MO));
            Txfiled_MOnum_AddMO.setDisable(true);
            Txfiled_CusName_AddMO.setText(rs.getString("CUS_NAME"));
            Txfiled_ProplemDisc_AddMO.setText(rs.getString("PROBLEM_DESC"));
            Txfiled_CusMnum_AddMO.setText(rs.getString("CUS_MOBILE_NBER"));
            Txfiled_SPCost_AddMO.setText(rs.getString("SP_COST"));
            Txfiled_MOCost_AddMO.setText(rs.getString("MO_COST"));
            Txfiled_DevSerialN_AddMO.setText(rs.getString("DEVICE_SN"));
            Txfiled_DevDiscription_AddMO.setText(rs.getString("DEVICE_DESC"));

            LocalDate WARRANTYDate = LocalDate.parse(rs.getString("WARRANTY"));
            LocalDate STARTINGDate = LocalDate.parse(rs.getString("STARTING_DATE"));
            LocalDate ENDINGDate = LocalDate.parse(rs.getString("ENDING_DATE"));

            Date_Warranty_AddMO.setValue(WARRANTYDate);
            Date_StartMo_AddMO.setValue(STARTINGDate);
            Date_EndMO_AddMO.setValue(ENDINGDate);

            //List<String> State = new ArrayList<>();
            //State.add(rs.getString("STATE"));
            //Selct_MoStatus_AddMO.setItems(FXCollections.observableArrayList(State));
            Selct_MoStatus_AddMO.getSelectionModel().select(rs.getString("STATE"));

            //List<String> Tec = new ArrayList<>();
            //Tec.add(rs.getString("EMPLOYEE_ID"));
            System.out.println("PPPPPPPPPPPPPP " + rs.getString("EMP_NAME"));
            Selct_Techichan_AddMO.getSelectionModel().select(rs.getString("EMP_NAME"));

            if (rs.getString("STATE").equals("created") || rs.getString("STATE").equals("تم الإنشاء")) {
                if (count_Language == 0) {
                    Selct_MoStatus_AddMO.getSelectionModel().select("created");
                } else {
                    Selct_MoStatus_AddMO.getSelectionModel().select("تم الإنشاء");

                }
            } else if (rs.getString("STATE").equals("approved") || rs.getString("STATE").equals("تم الموافقة")) {
                if (count_Language == 0) {
                    Selct_MoStatus_AddMO.getSelectionModel().select("approved");
                } else {
                    Selct_MoStatus_AddMO.getSelectionModel().select("تم الموافقة");

                }

            } else if (rs.getString("STATE").equals("مرفوضة") || rs.getString("STATE").equals("disapproved")) {
                if (count_Language == 0) {
                    Selct_MoStatus_AddMO.getSelectionModel().select("disapproved");
                } else {
                    Selct_MoStatus_AddMO.getSelectionModel().select("مرفوضة");

                }
            } else if (rs.getString("STATE").equals("لا يمكن القيام بعملية الصيانة") || rs.getString("STATE").equals("cannot be done")) {
                if (count_Language == 0) {
                    Selct_MoStatus_AddMO.getSelectionModel().select("cannot be done");
                } else {
                    Selct_MoStatus_AddMO.getSelectionModel().select("لا يمكن القيام بعملية الصيانة");

                }
            } else if (rs.getString("STATE").equals("تم الكشف عن عيوب أخرى") || rs.getString("STATE").equals("other defects has been detected")) {
                if (count_Language == 0) {
                    Selct_MoStatus_AddMO.getSelectionModel().select("other defects has been detected");
                } else {
                    Selct_MoStatus_AddMO.getSelectionModel().select("تم الكشف عن عيوب أخرى");

                }
            } else if (rs.getString("STATE").equals("تم الاصلاح") || rs.getString("STATE").equals("repaired")) {
                if (count_Language == 0) {
                    Selct_MoStatus_AddMO.getSelectionModel().select("repaired");
                } else {
                    Selct_MoStatus_AddMO.getSelectionModel().select("تم الاصلاح");

                }
            } else if (rs.getString("STATE").equals("تحت الصيانة") || rs.getString("STATE").equals("under maintenance")) {
                if (count_Language == 0) {
                    Selct_MoStatus_AddMO.getSelectionModel().select("under maintenance");
                } else {
                    Selct_MoStatus_AddMO.getSelectionModel().select("تحت الصيانة");

                }
            } else if (rs.getString("STATE").equals("دفعت") || rs.getString("STATE").equals("paid")) {
                if (count_Language == 0) {
                    Selct_MoStatus_AddMO.getSelectionModel().select("paid");
                } else {
                    Selct_MoStatus_AddMO.getSelectionModel().select("دفعت");

                }
            }

            Btn_Delete_AddMo.setDisable(false);
            Btn_Save_AddMo.setDisable(false);
            Btn_Print_AddMo.setDisable(false);
            Btn_Delete_AddMo.setDisable(false);
            Txfiled_CusName_AddMO.setDisable(true);
            Btn_Cancle_AddMo.setDisable(false);
            Btn_AddSP_AddMo.setDisable(false);
            Btn_ReomveSP_AddMo.setDisable(false);
            //loadlist.clear();
            loadSpSelected(MO);

            calculate();

            //java.sql.Statement statement1 = connection.createStatement();
            //statement1.executeQuery(sql);
        } else {

            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM `maintenance_operation` ORDER BY `MO_NBER` DESC LIMIT 1");
            ResultSet rs2 = st2.getResultSet();
            //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
            if (rs2.first()) {

                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                //System.out.println();
                count = 1;
                monumber = Integer.parseInt(rs2.getString("MO_NBER"));
                monumber++;
                System.out.println(monumber);
                Txfiled_MOnum_AddMO.setText(String.valueOf(monumber));
                Txfiled_MOnum_AddMO.setDisable(true);
                //Txfiled_MOnum_AddMO.clear();
                Btn_Delete_AddMo.setDisable(true);
                Btn_Cancle_AddMo.setDisable(false);
                Btn_Save_AddMo.setDisable(false);
                Btn_Print_AddMo.setDisable(false);
                Txfiled_CusName_AddMO.setDisable(false);
            }

        }
    }

    public void loadInTO(String MO_Nber, String CUS_NAME, String PROBLEM_DESC, String CUS_MOBILE_NBER, String SP_COST, String MO_COST, String DEVICE_SN, String DEVICE_DESC, String WARRANTY, String STARTING_DATE, String ENDING_DATE,
            String STATE, String EMP_NAME) throws SQLException {
        count = 2;

        Txfiled_MOnum_AddMO.setDisable(true);
        Txfiled_MOnum_AddMO.setText(MO_Nber);
        Txfiled_CusName_AddMO.setText(CUS_NAME);
        Txfiled_ProplemDisc_AddMO.setText(PROBLEM_DESC);
        Txfiled_CusMnum_AddMO.setText(CUS_MOBILE_NBER);
        Txfiled_SPCost_AddMO.setText(SP_COST);
        Txfiled_MOCost_AddMO.setText(MO_COST);
        Txfiled_DevSerialN_AddMO.setText(DEVICE_SN);
        Txfiled_DevDiscription_AddMO.setText(DEVICE_DESC);

        LocalDate WARRANTYDate = LocalDate.parse(WARRANTY);
        LocalDate STARTINGDate = LocalDate.parse(STARTING_DATE);
        LocalDate ENDINGDate = LocalDate.parse(ENDING_DATE);

        Date_Warranty_AddMO.setValue(WARRANTYDate);
        Date_StartMo_AddMO.setValue(STARTINGDate);
        Date_EndMO_AddMO.setValue(ENDINGDate);

        //List<String> State = new ArrayList<>();
        //State.add(rs.getString("STATE"));
        //Selct_MoStatus_AddMO.setItems(FXCollections.observableArrayList(State));
        Selct_MoStatus_AddMO.getSelectionModel().select(STATE);

        //List<String> Tec = new ArrayList<>();
        //Tec.add(rs.getString("EMPLOYEE_ID"));
        System.out.println("PPPPPPPPPPPPPP " + EMP_NAME);
        Selct_Techichan_AddMO.getSelectionModel().select(EMP_NAME);

        Btn_Delete_AddMo.setDisable(false);
        Btn_Save_AddMo.setDisable(false);
        Btn_Print_AddMo.setDisable(false);
        Btn_Delete_AddMo.setDisable(false);
        Txfiled_CusName_AddMO.setDisable(true);
        Btn_Cancle_AddMo.setDisable(false);
        Btn_AddSP_AddMo.setDisable(false);
        Btn_ReomveSP_AddMo.setDisable(false);
        //loadlist.clear();
        //loadSpSelected();

        calculate();

    }

    public void Search(String Search, int Choose) {
        if (Choose == 2) {
            ResultSet rs = connectionClass.execQuery(Search);
            try {
                while (rs.next()) {
                    String mname = rs.getString("SP_NBER");
                    String mid = rs.getString("SP_NAME");
                    String mobile = rs.getString("DESCRIPTION");
                    String price = rs.getString("PRICE");

                    int SP_num = Integer.parseInt(mname);
                    int SP_quan = Integer.parseInt(rs.getString("SP_QUANTITY"));

                    double SP_Pri = Double.parseDouble(price);

                    list.add(new Controller_AddMO.AddSP(SP_num, mid, mobile, SP_Pri, SP_quan));

                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            Table_AddSP_AddMO.getItems().setAll(list);

        } else if (Choose == 1) {

            ResultSet rs = connectionClass.execQuery(Search);

            try {
                if (rs.first()) {

                    Txfiled_CusName_AddMO.setText(rs.getString("CUS_NAME"));

                } else {
                    Txfiled_CusName_AddMO.clear();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controller_AddMO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void M_Btn_AddSP_AddMo(ActionEvent event) throws SQLException {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        ObservableList<Controller_AddMO.AddSP> SPSelected, AllSP;
        AllSP = Table_AddSP_AddMO.getItems();
        SPSelected = Table_AddSP_AddMO.getSelectionModel().getSelectedItems();
        // ObservableList<Controller_AddMO.SelectedSP> SPSelected3, AllSP3;
        //AllSP3 = Table_SelectedSP_AddMO.getItems();
        ///SPSelected3 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        //_____________________
        //for (AddSP addSP : Table_AddSP_AddMO.getSelectionModel().getSelectedItems()) {
        for (int i = 0; i < SPSelected.size(); i++) {
            System.out.println(SPSelected.size());
            //for(int i =1;i<=1; i++){

            int a = 0;
            int seqNumber = 1;
            for (SelectedSP loadlist1 : loadlist) {

                System.out.println("i==== " + a);
                System.out.println(SPSelected.get(0).getSP_Name());
                System.out.println(loadlist.get(a).getSP2_Name());
                System.out.println("SPSelected size === " + SPSelected.size());
                System.out.println("load size === " + loadlist.size());
                if (loadlist.get(a).getSP2_Name().equals(SPSelected.get(0).getSP_Name())) {
                    loadlist.get(a).getSP_Seq_Nber();
                    System.out.println("seq for a load ==  " + loadlist.get(a).getSP_Seq_Nber());
                    seqNumber = loadlist.get(a).getSP_Seq_Nber() + 1;
                    System.out.println("seqNumber== " + seqNumber);
                }
                a++;

            }
            if (SPSelected.get(0).getSP_Quantity() > 0) {

                loadlist.add(new Controller_AddMO.SelectedSP(SPSelected.get(0).getSP_Number(), SPSelected.get(0).getSP_Name(),
                        SPSelected.get(0).getSP_Description(), SPSelected.get(0).getSP_Price(), "null", seqNumber));
                //AllSP3.add()

                String sql1 = "INSERT INTO `require` VALUES(" + Txfiled_MOnum_AddMO.getText() + ",'" + SPSelected.get(0).getSP_Number() + "','"
                        + seqNumber + "','" + "Null'" + ",'" + SPSelected.get(0).getSP_Price() + "')";
                System.out.println(sql1);

                String sqlupdateSP = "UPDATE `spare_parts` SET `SP_QUANTITY` = SP_Quantity-1 WHERE `spare_parts`.`SP_NBER` =" + SPSelected.get(0).getSP_Number();
                System.out.println(sqlupdateSP);

                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);
                statement1.executeUpdate(sqlupdateSP);
                // JOptionPane.showMessageDialog(null, "تم اضافة  " + SPSelected.get(0).getSP_Name() + "لعملية الصيانة", "Alert", JOptionPane.INFORMATION_MESSAGE);

                if (count_Language == 0) {
                    alert2.setContentText("A " + SPSelected.get(0).getSP_Name() + " has been added to the MO");
                } else {
                    alert2.setContentText("تم اضافة  " + SPSelected.get(0).getSP_Name() + "لعملية الصيانة");

                }
                alert2.showAndWait();

                loadData();
            } else {
                Alert alert3 = new Alert(Alert.AlertType.WARNING);
                alert3.setTitle(null);
                alert3.setHeaderText(null);
                //JOptionPane.showMessageDialog(null, "Wrong !!! .", "Alert", JOptionPane.ERROR_MESSAGE);
                if (count_Language == 0) {
                    alert3.setContentText("The spare part can not be added because it is out of stock");
                } else {
                    alert3.setContentText("لا يمكنك اضافة قطعة الغيار , لقد نفذت كميتها");

                }

                alert3.showAndWait();
            }

        }
        //}
        Table_SelectedSP_AddMO.getItems().setAll(loadlist);

        calculate();
        String sqlupdatePrice = "UPDATE `maintenance_operation` SET `MO_COST` = " + Txfiled_MOCost_AddMO.getText() + ", `SP_COST` = " + Txfiled_SPCost_AddMO.getText() + " WHERE `MO_NBER` = " + Txfiled_MOnum_AddMO.getText() + ";";
        java.sql.Statement statement1 = connection.createStatement();
        statement1.executeUpdate(sqlupdatePrice);

        // Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
        //SPSelected.forEach(AllSP::remove);
        //System.out.println(SPSelected);
        //list.add(new Controller_AddMO.AddSP(aa, mid, mobile));
        //list2.add(SPSelected.get(1));
        //System.out.println(SPSelected.get(0).SP_Description);
        //Table_AddSP_AddMO.getItems().setAll(list);
        //Table_SelectedSP_AddMO.getItems().setAll(list2);
        //SPSelected.forEach(AllSP::remove);
    }

    void loadWindow(String loc, String title) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle(title);
            stage.show();
        } catch (IOException s) {
            s.printStackTrace();
        }
    }

    @FXML
    private void M_Btn_Print_AddMo(ActionEvent event) throws JRException, SQLException {
        if (count_Language == 0) {
            if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("paid")) {
                String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('paid') AND m.MO_NBER ='" + Txfiled_MOnum_AddMO.getText() + "'";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                String ss = Txfiled_MOnum_AddMO.getText();

                print.showReportEN(ss);
            } else if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("created") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("approved") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("under maintenance") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("other defects has been detected")) {
                String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + Txfiled_MOnum_AddMO.getText() + "'";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                String ff = Txfiled_MOnum_AddMO.getText();
                print.financialassessmentEN(ff);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Can not print this MO");
                alert.showAndWait();
                return;

            }
        } else if (count_Language == 1) {
            if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("paid")) {
                String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('paid') AND m.MO_NBER ='" + Txfiled_MOnum_AddMO.getText() + "'";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                String ss = Txfiled_MOnum_AddMO.getText();

                print.showReport(ss);
            } else if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("created") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("approved") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("under maintenance") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("other defects has been detected")) {
                String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + Txfiled_MOnum_AddMO.getText() + "'";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                String ff = Txfiled_MOnum_AddMO.getText();
                print.financialassessment(ff);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("لايمكن الطباعة");
                alert.showAndWait();
                return;

            }

        }
    }

    @FXML
    private void M_Btn_Cancle_AddMo(ActionEvent event) {

        clear();

    }

    @FXML
    private void M_Btn_Delete_AddMo(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("");
        if (count_Language == 0) {
            alert.setContentText("This MO will be deleted ");
        } else {
            alert.setContentText("  سوف يتم حذف عملية الصيانة ");

        }

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            String deletSP = "DELETE FROM  `require` " + " WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText();
            String sql1 = "DELETE FROM  `maintenance_operation` " + " WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText();
            System.out.println(deletSP);
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(deletSP);
            statement1.executeUpdate(sql1);
            clear();
        }

    }

    public void clear() {
        Txfiled_MOnum_AddMO.setDisable(false);

        Txfiled_ProplemDisc_AddMO.clear();
        Txfiled_CusMnum_AddMO.clear();
        Txfiled_SPCost_AddMO.setText("0.00");
        Txfiled_MOCost_AddMO.setText("0.00");
        Txfiled_DevSerialN_AddMO.clear();
        Txfiled_DevDiscription_AddMO.clear();
        Txfiled_SpSerialN_AddMO.clear();
        Txfiled_TotalCost_AddMO.setText("0.00");
        Txfiled_VAT_AddMO.setText("0.00");
        Txfiled_MOnum_AddMO.clear();
        Txfiled_SearchSP_AddMO.clear();
        Txfiled_CusName_AddMO.clear();

        Selct_Techichan_AddMO.getSelectionModel().clearSelection();
        Selct_MoStatus_AddMO.getSelectionModel().clearSelection();
        Date_Warranty_AddMO.setValue(null);
        Date_StartMo_AddMO.setValue(null);
        Date_EndMO_AddMO.setValue(null);
        Table_SelectedSP_AddMO.getItems().clear();
        //loadlist.clear();

    }

    @FXML
    private void M_Btn_Save_AddMo(ActionEvent event) throws SQLException, AddressException, MessagingException {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        if (Txfiled_MOnum_AddMO.getText().isEmpty() || Txfiled_ProplemDisc_AddMO.getText().isEmpty() || Txfiled_CusMnum_AddMO.getText().isEmpty()
                || Txfiled_DevSerialN_AddMO.getText().isEmpty() || Txfiled_DevDiscription_AddMO.getText().isEmpty() || Date_EndMO_AddMO.getValue() == null
                || Date_StartMo_AddMO.getValue() == null || Date_Warranty_AddMO.getValue() == null || Selct_MoStatus_AddMO.getValue().isEmpty()
                || Selct_Techichan_AddMO.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);

            if (count_Language == 0) {
                alert.setContentText("Please enter the value");
            } else {
                alert.setContentText("الرجاء إدخال القيمة");

            }
            alert.showAndWait();

        }
        Statement st2 = connection.createStatement();
        st2.executeQuery("SELECT * FROM `employee`");
        ResultSet rs2 = st2.getResultSet();
        //SELECT * FROM `employee` 
        int IndexOFTech = 0;
        for (int i = 0; i < ListOfTechichan.size(); i++) {

            while (rs2.next()) {

                //ListOfTechichan.add(rs2.getString("EMP_NAME"));
                //البحث ب رقم العميل من الداتابيس
                if (Selct_Techichan_AddMO.getValue().equals(rs2.getString("EMP_NAME"))) {

                    IndexOFTech = Integer.parseInt(rs2.getString("EMPLOYEE_ID"));

                }
            }
        }
        if (Txfiled_CusName_AddMO.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);

            if (count_Language == 0) {
                alert.setContentText("Please enter valid customer");
            } else {
                alert.setContentText("الرجاء التاكد من اضافة عميل");

            }
            alert.showAndWait();

        } else {

            
            
//IndexOFTech++;
            System.out.println("INDEX== " + IndexOFTech);
            if (count == 1) {
                System.out.println("Equal  insert");

                String sql1 = "INSERT INTO `maintenance_operation` VALUES(" + monumber + "," + "'" + Selct_MoStatus_AddMO.getValue() + "'" + "," + "'" + Txfiled_MOCost_AddMO.getText()
                        + "'" + "," + "'" + Txfiled_SPCost_AddMO.getText() + "'" + "," + "'" + Date_StartMo_AddMO.getValue() + "'" + "," + "'" + Date_EndMO_AddMO.getValue() + "'" + "," + "'"
                        + Date_Warranty_AddMO.getValue() + "'" + "," + "'" + Txfiled_ProplemDisc_AddMO.getText() + "'" + "," + "'" + Txfiled_DevSerialN_AddMO.getText() + "'" + "," + "'" + Txfiled_DevDiscription_AddMO.getText()
                        + "'" + "," + "'" + IndexOFTech + "'" + "," + "'" + Txfiled_CusMnum_AddMO.getText() + "', NULL ,NULL" + ")";
                System.out.println(sql1);
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);
                if (count_Language == 0) {

                    alert2.setContentText(" A new MO has been created");
                } else {
                    alert2.setContentText("تم انشاء عملية صيانة جديدة");

                }

                alert2.showAndWait();
            } else if (count == 2) {
                System.out.println("Equal  update");
                System.out.println(Selct_MoStatus_AddMO.getValue());
                String sql1 = "UPDATE  `maintenance_operation` SET STATE='" + Selct_MoStatus_AddMO.getValue() + "',MO_COST='" + Txfiled_MOCost_AddMO.getText() + "',SP_COST='" + Txfiled_SPCost_AddMO.getText()
                        + "',STARTING_DATE='" + Date_StartMo_AddMO.getValue() + "',ENDING_DATE='" + Date_EndMO_AddMO.getValue() + "',WARRANTY='" + Date_Warranty_AddMO.getValue() + "',PROBLEM_DESC='" + Txfiled_ProplemDisc_AddMO.getText()
                        + "',DEVICE_SN='" + Txfiled_DevSerialN_AddMO.getText() + "',DEVICE_DESC='" + Txfiled_DevDiscription_AddMO.getText() + "',EMPLOYEE_ID='" + IndexOFTech + "',CUS_MOBILE_NBER='" + Txfiled_CusMnum_AddMO.getText()
                        + "' WHERE MO_NBER= '" + Txfiled_MOnum_AddMO.getText() + "'";
                System.out.println(sql1);
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);
                System.out.println(Selct_MoStatus_AddMO.getValue().equalsIgnoreCase(sql1));

                if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("paid") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("دفعت")) {
                    //ystem.out.println("????pls"+INVOICE_NBER.isEmpty());
                    System.out.println("INVOICE_NBER?>?>"+INVOICE_NBER);
                    
                    if(INVOICE_NBER !=null){
                            alert2.setContentText("لدى العملية الحالية فاتورة بالفعل برقم "+INVOICE_NBER);
                alert2.showAndWait();
                        

                    //System.out.println(Txfiled_MOnum_AddMO.getText());
                }else{
                        
                        Statement st3 = connection.createStatement();
                    st3.executeQuery("SELECT * FROM `maintenance_operation` ORDER BY `INVOICE_NBER` DESC LIMIT 1");
                    ResultSet rs3 = st3.getResultSet();
                    //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
                    if (rs3.first()) {
                        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                        //System.out.println();

                        monumber = Integer.parseInt(rs3.getString("INVOICE_NBER"));
                        monumber++;
                        System.out.println(monumber);
                    }
                    System.out.println("PAAAAAAAAAID");
                    String inv_num_date = "UPDATE  `maintenance_operation` SET INVOICE_DATE='" +/*المفروض  يكون التاريخ الحالي للنظام*/ LocalDate.now() + "',INVOICE_NBER='" + monumber + "' WHERE MO_NBER= '" + Txfiled_MOnum_AddMO.getText() + "'";
                    java.sql.Statement statement2 = connection.createStatement();
                    statement2.executeUpdate(inv_num_date);
                     alert2.setContentText("تم انشاء فاتورة لعملية الصيانة برقم "+monumber);
                alert2.showAndWait();
                              //       alert2.setTitle(null);
        //alert2.setHeaderText(null);
        
                    }}
                if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("approved") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تم الموافقة")) {

                    Statement st = connection.createStatement();
                    String CusEmail_query = "SELECT CUS_EMAIL FROM `customer` Where CUS_MOBILE_NBER =" + Txfiled_CusMnum_AddMO.getText();
                    System.out.println(CusEmail_query);
                    st.executeQuery(CusEmail_query);
                    ResultSet rs = st.getResultSet();
                    String to = "";
                    System.out.println("?>?>?> " + rs.isFirst());
                    if (rs.first()) {
                        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                        //System.out.println();

                        to = rs.getString("CUS_EMAIL");
                    } else {
                    }

                    String host = "smtp.gmail.com";
                    String user = "abdualziz.alhazmi1997@gmail.com";
                    String pass = "Azoz789!@#";

                    String from = "abdualziz.alhazmi1997@gmail.com";
                    String subject = "العنوان تم استلام الجهاز";
                    String messageText = "تم استلام الجهاز";
                    boolean sessionDebug = false;

                    Properties props = System.getProperties();

                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", host);
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.required", "true");

                    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                    Session mailSession = Session.getDefaultInstance(props, null);
                    mailSession.setDebug(sessionDebug);
                    Message msg = new MimeMessage(mailSession);
                    msg.setFrom(new InternetAddress(from));
                    InternetAddress[] address = {new InternetAddress(to)};
                    msg.setRecipients(Message.RecipientType.TO, address);
                    msg.setSubject(subject);
                    msg.setSentDate(new Date());
                    msg.setText(messageText);
                    Transport transport = mailSession.getTransport("smtp");
                    transport.connect(host, user, pass);
                    transport.sendMessage(msg, msg.getAllRecipients());
                    transport.close();

                    System.out.println("message send successfully");

                    //System.out.println(Txfiled_MOnum_AddMO.getText());
                } else if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("repaired") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تم الاصلاح")) {

                    Statement st = connection.createStatement();
                    String CusEmail_query = "SELECT CUS_EMAIL FROM `customer` Where CUS_MOBILE_NBER =" + Txfiled_CusMnum_AddMO.getText();
                    System.out.println(CusEmail_query);
                    st.executeQuery(CusEmail_query);
                    ResultSet rs = st.getResultSet();
                    String to = "";
                    System.out.println("?>?>?> " + rs.isFirst());
                    if (rs.first()) {
                        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                        //System.out.println();

                        to = rs.getString("CUS_EMAIL");
                    } else {
                    }

                    String host = "smtp.gmail.com";
                    String user = "abdualziz.alhazmi1997@gmail.com";
                    String pass = "Azoz789!@#";

                    String from = "abdualziz.alhazmi1997@gmail.com";
                    String subject = "العنوان تعال استلم جهازك";
                    String messageText = "تم تعال استلم جهازك";
                    boolean sessionDebug = false;

                    Properties props = System.getProperties();

                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", host);
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.required", "true");

                    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                    Session mailSession = Session.getDefaultInstance(props, null);
                    mailSession.setDebug(sessionDebug);
                    Message msg = new MimeMessage(mailSession);
                    msg.setFrom(new InternetAddress(from));
                    InternetAddress[] address = {new InternetAddress(to)};
                    msg.setRecipients(Message.RecipientType.TO, address);
                    msg.setSubject(subject);
                    msg.setSentDate(new Date());
                    msg.setText(messageText);
                    Transport transport = mailSession.getTransport("smtp");
                    transport.connect(host, user, pass);
                    transport.sendMessage(msg, msg.getAllRecipients());
                    transport.close();

                    System.out.println("message send successfully");

                    //System.out.println(Txfiled_MOnum_AddMO.getText());
                } else if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("other defects has been detected") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تم الكشف عن عيوب أخرى")) {

                    Statement st = connection.createStatement();
                    String CusEmail_query = "SELECT CUS_EMAIL FROM `customer` Where CUS_MOBILE_NBER =" + Txfiled_CusMnum_AddMO.getText();
                    System.out.println(CusEmail_query);
                    st.executeQuery(CusEmail_query);
                    ResultSet rs = st.getResultSet();
                    String to = "";
                    System.out.println("?>?>?> " + rs.isFirst());
                    if (rs.first()) {
                        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                        //System.out.println();

                        to = rs.getString("CUS_EMAIL");
                    } else {
                    }

                    String host = "smtp.gmail.com";
                    String user = "abdualziz.alhazmi1997@gmail.com";
                    String pass = "Azoz789!@#";

                    String from = "abdualziz.alhazmi1997@gmail.com";
                    String subject = "العنوان تم استلام الجهاز";
                    String messageText = "تم استلام الجهاز";
                    boolean sessionDebug = false;

                    Properties props = System.getProperties();

                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", host);
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.required", "true");

                    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                    Session mailSession = Session.getDefaultInstance(props, null);
                    mailSession.setDebug(sessionDebug);
                    Message msg = new MimeMessage(mailSession);
                    msg.setFrom(new InternetAddress(from));
                    InternetAddress[] address = {new InternetAddress(to)};
                    msg.setRecipients(Message.RecipientType.TO, address);
                    msg.setSubject(subject);
                    msg.setSentDate(new Date());
                    msg.setText(messageText);
                    Transport transport = mailSession.getTransport("smtp");
                    transport.connect(host, user, pass);
                    transport.sendMessage(msg, msg.getAllRecipients());
                    transport.close();

                    System.out.println("message send successfully");

                    //System.out.println(Txfiled_MOnum_AddMO.getText());
                }
                //else if (mo state == problem){send mail}

                if (count_Language == 0) {

                    alert2.setContentText("Changes saved successfully");
                } else {
                    alert2.setContentText("تم حفظ التعديلات بنجاح");
                }
                alert2.showAndWait();
            }
            count = 2;

        }
    }
    public int monumber = 0;

    @FXML
    private void M_Btn_Search_AddMo(ActionEvent event) throws SQLException, ParseException {
        if (Txfiled_MOnum_AddMO.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);

            if (count_Language == 0) {
                alert.setContentText("Please enter the value");
            } else {
                alert.setContentText("الرجاء إدخال القيمة");

            }
            alert.showAndWait();
            return;
        }
        int MO_number = Integer.parseInt(Txfiled_MOnum_AddMO.getText());
        Search_MO(MO_number);
        /*Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE MO_NBER = " + Txfiled_MOnum_AddMO.getText());

        ResultSet rs = st.getResultSet();
        //st = connection.prepareCall(sql);

        if (rs.first()) {

            System.out.println(Txfiled_MOnum_AddMO.getText());

            System.out.println("THIS MO NUMBER IN DB== " + rs.getString("MO_NBER"));
            System.out.println("THIS MO NUMBER IN FILED== " + Txfiled_MOnum_AddMO.getText());

            if (rs.getString("MO_NBER").equals(Txfiled_MOnum_AddMO.getText())) {

                count = 2;

                Txfiled_MOnum_AddMO.setDisable(true);
                Txfiled_CusName_AddMO.setText(rs.getString("CUS_NAME"));
                Txfiled_ProplemDisc_AddMO.setText(rs.getString("PROBLEM_DESC"));
                Txfiled_CusMnum_AddMO.setText(rs.getString("CUS_MOBILE_NBER"));
                Txfiled_SPCost_AddMO.setText(rs.getString("SP_COST"));
                Txfiled_MOCost_AddMO.setText(rs.getString("MO_COST"));
                Txfiled_DevSerialN_AddMO.setText(rs.getString("DEVICE_SN"));
                Txfiled_DevDiscription_AddMO.setText(rs.getString("DEVICE_DESC"));

                LocalDate WARRANTYDate = LocalDate.parse(rs.getString("WARRANTY"));
                LocalDate STARTINGDate = LocalDate.parse(rs.getString("STARTING_DATE"));
                LocalDate ENDINGDate = LocalDate.parse(rs.getString("ENDING_DATE"));

                Date_Warranty_AddMO.setValue(WARRANTYDate);
                Date_StartMo_AddMO.setValue(STARTINGDate);
                Date_EndMO_AddMO.setValue(ENDINGDate);

                //List<String> State = new ArrayList<>();
                //State.add(rs.getString("STATE"));
                //Selct_MoStatus_AddMO.setItems(FXCollections.observableArrayList(State));
                Selct_MoStatus_AddMO.getSelectionModel().select(rs.getString("STATE"));

                //List<String> Tec = new ArrayList<>();
                //Tec.add(rs.getString("EMPLOYEE_ID"));
                System.out.println("PPPPPPPPPPPPPP " + rs.getString("EMP_NAME"));
                Selct_Techichan_AddMO.getSelectionModel().select(rs.getString("EMP_NAME"));

                  
    
                if (rs.getString("STATE").equals("created") || rs.getString("STATE").equals("تم الإنشاء")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("created");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("تم الإنشاء");

                    }
                } else if (rs.getString("STATE").equals("approved") || rs.getString("STATE").equals("تم الموافقة")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("approved");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("تم الموافقة");

                    }

                } else if (rs.getString("STATE").equals("مرفوضة") || rs.getString("STATE").equals("disapproved")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("disapproved");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("مرفوضة");

                    }
                } else if (rs.getString("STATE").equals("لا يمكن القيام بعملية الصيانة") || rs.getString("STATE").equals("cannot be done")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("cannot be done");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("لا يمكن القيام بعملية الصيانة");

                    }
                } else if (rs.getString("STATE").equals("تم الكشف عن عيوب أخرى") || rs.getString("STATE").equals("other defects has been detected")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("other defects has been detected");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("تم الكشف عن عيوب أخرى");

                    }
                } else if (rs.getString("STATE").equals("تم الاصلاح") || rs.getString("STATE").equals("repaired")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("repaired");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("تم الاصلاح");

                    }
                } else if (rs.getString("STATE").equals("استقبال") || rs.getString("STATE").equals("ReceptionDesk")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("ReceptionDesk");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("استقبال");

                    }
                }else if (rs.getString("STATE").equals("دفعت") || rs.getString("STATE").equals("paid")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("paid");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("دفعت");

                    }}

                Btn_Delete_AddMo.setDisable(false);
                Btn_Save_AddMo.setDisable(false);
                Btn_Print_AddMo.setDisable(false);
                Btn_Delete_AddMo.setDisable(false);
                Txfiled_CusName_AddMO.setDisable(true);
                Btn_Cancle_AddMo.setDisable(false);
                Btn_AddSP_AddMo.setDisable(false);
                Btn_ReomveSP_AddMo.setDisable(false);
                //loadlist.clear();
                loadSpSelected();

                calculate();

                //java.sql.Statement statement1 = connection.createStatement();
                //statement1.executeQuery(sql);
            } else {

                Statement st2 = connection.createStatement();
                st2.executeQuery("SELECT * FROM `maintenance_operation` ORDER BY `MO_NBER` DESC LIMIT 1");
                ResultSet rs2 = st2.getResultSet();
                //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
                if (rs2.first()) {

                    System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                    //System.out.println();
                    count = 1;
                    monumber = Integer.parseInt(rs2.getString("MO_NBER"));
                    monumber++;
                    System.out.println(monumber);
                    Txfiled_MOnum_AddMO.setText(String.valueOf(monumber));
                    Txfiled_MOnum_AddMO.setDisable(true);
                    //Txfiled_MOnum_AddMO.clear();
                    Btn_Delete_AddMo.setDisable(true);
                    Btn_Cancle_AddMo.setDisable(false);
                    Btn_Save_AddMo.setDisable(false);
                    Btn_Print_AddMo.setDisable(false);
                    Txfiled_CusName_AddMO.setDisable(false);
                }
            }
        }
    }//}*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources
    ) {

        //ConnectionClass connectionClass =new ConnectionClass();
        // we call conneClass  that we make it up
        connectionClass.connectDB();
        //Connection connection= connectionClass.getConnection();
        intilCol();
        loadData();
        loadTech();
        System.out.println("Byee");
        Selct_Techichan_AddMO.setItems(ListOfTechichan);

    }

    public void loadTech() {
        String query = "SELECT EMP_NAME FROM employee where JOP_TYPE= 'Technician' or JOP_TYPE='فني' ";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {

                ListOfTechichan.add(rs.getString("EMP_NAME"));

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    private void intilCol() {

        Col_SPnum_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Number"));
        Col_SPname_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Name"));
        Col_SPdisc_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Description"));
        Col_SPprice_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Price"));
        Col_SPQuantity_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Quantity"));

        Col_SPnum_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Number2"));
        Col_SPname_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Name2"));
        Col_SPdisc_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Description2"));
        Col_SPSN_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_SN"));
        Col_SPprice_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Price2"));
    }

    private void loadData() {
        list.clear();
        Table_AddSP_AddMO.getItems().clear();
        String query = "SELECT * FROM spare_parts";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {

                String mname = rs.getString("SP_NBER");
                String mid = rs.getString("SP_NAME");
                String mobile = rs.getString("DESCRIPTION");
                String price = rs.getString("PRICE");

                int SP_num = Integer.parseInt(mname);
                int SP_quan = Integer.parseInt(rs.getString("SP_QUANTITY"));

                double SP_Pri = Double.parseDouble(price);

                list.add(new Controller_AddMO.AddSP(SP_num, mid, mobile, SP_Pri, SP_quan));

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        Table_AddSP_AddMO.getItems().setAll(list);
        for (int i = 0; i < Table_AddSP_AddMO.getItems().size(); i++) {
            if (Table_AddSP_AddMO.getItems().get(i).getSP_Quantity() <= 0) {
                System.out.println("i am = 0");

                // Table_AddSP_AddMO.getRowFactory().call(Table_AddSP_AddMO.getItems().get(i).getSP_Quantity()).setStyle("-fx-background-color: blue");
                // Table_AddSP_AddMO.columnResizePolicyProperty().
                // setTextFill(Color.BLACK);
                //Table_AddSP_AddMO.setStyle(      "-fx-control-inner-background: red;"
                // + "-fx-control-inner-background-alt: blue;");
                //setStyle("-fx-background-color: yellow");
                //Table_AddSP_AddMO.getStyleClass().clear();'Table_AddSP_AddMO,
                //  Table_AddSP_AddMO.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
                //Table_AddSP_AddMO.setStyle("-fx-background-color: yellow");
            } else {
                Table_AddSP_AddMO.setStyle("-fx-my-cell-background: green");
                //  Table_AddSP_AddMO.setStyle(      "-fx-control-inner-background: white ;"
                // + "-fx-control-inner-background-alt: green;");

            }
        }
        //-fx-cell-size: 50px;
//row-selection
        //-fx-background-color

        //Table_AddSP_AddMO.getItems().get(0).getSP_Quantity()
    }

    private void loadSpSelected(int MM) throws SQLException {
        int MO_num = MM;
        loadlist.clear();
        System.out.println("i am in loadSpSelected ");

        String SQLqq = "SELECT *\n"
                + "FROM   spare_parts s\n"
                + "JOIN   `require` r ON s.SP_NBER = r.SP_NBER WHERE MO_NBER= " + MO_num;
        System.out.println(SQLqq);
        ResultSet rs = connectionClass.execQuery(SQLqq);

        try {
            double a = 0.00;
            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    int SP_num = Integer.parseInt(rs.getString("SP_NBER"));
                    int SP_Seq = Integer.parseInt(rs.getString("Seq_Nber"));
                    int Mo_n = Integer.parseInt(rs.getString("MO_NBER"));

                    double SP_Pri = Double.parseDouble(rs.getString("PRICE"));
                    System.out.println(rs.getString("SP_NBER"));

                    double SP_Pri2 = Double.parseDouble(rs.getString("Effective_Price"));

                    //Txfiled_SPCost_AddMO.setText(String.valueOf(a));
                    //loadlist.add(new Controller_AddMO.SelectedSP(SP_num, rs.getString("SP_NAME"), rs.getString("DESCRIPTION"), SP_Pri2, rs.getString("SERIAL_NUMBER"),SP_Seq));
                    if (Mo_n == MO_num) {
                        a += SP_Pri2;

                        System.out.println("Total AA price =  " + a);
                        System.out.println("EQUALLLLLLL");
                        Txfiled_SPCost_AddMO.setText(String.valueOf(a));
                        //loadlist.add(new Controller_AddMO.SelectedSP(SP_num, mid, mobile, SP_Pri, rs.getString("SERIAL_NUMBER")));
                        loadlist.add(new Controller_AddMO.SelectedSP(SP_num, rs.getString("SP_NAME"), rs.getString("DESCRIPTION"), SP_Pri2, rs.getString("SERIAL_NUMBER"), SP_Seq));
                    }

                }
                //System.out.println(loadlist.get(0).getSP2_Name());
                Table_SelectedSP_AddMO.getItems().setAll(loadlist);
            }

            rs.close();

        } catch (SQLException ex) {
        }

        /*String query = "SELECT * FROM `require` WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText();
        System.out.println(query);
        ResultSet rs = connectionClass.execQuery(query);

        String query2 = "SELECT * FROM spare_parts";
        ResultSet rs2 = connectionClass.execQuery(query2);
        try {
            while (rs2.next()) {
                String mname = rs2.getString("SP_NBRE");
                String mid = rs2.getString("SP_NAME");
                String mobile = rs2.getString("DESCRIPTION");
                String price = rs2.getString("PRICE");

                int SP_num = Integer.parseInt(mname);
                double SP_Pri = Double.parseDouble(price);
                System.out.println(rs2.getString("SP_NBRE"));
        while (rs.next()) {
    //System.out.println(rs.getString("SP_NBRE"));
            System.out.println(rs.getString("MO_NBER"));
            System.out.println(rs.getString("SERIAL_NUMBER"));

                
                System.out.println(rs.getString("MO_NBER"));
                System.out.println(rs.getString("SERIAL_NUMBER"));
                System.out.println("dddddddddddddd");
                                double SP_Pri2 = Double.parseDouble(rs.getString("Effective_Price"));

                loadlist.add(new Controller_AddMO.SelectedSP(SP_num, mid, mobile, SP_Pri2, rs.getString("SERIAL_NUMBER")));
                if (rs.getString("MO_NBER") == Txfiled_MOnum_AddMO.getText()) {

                    loadlist.add(new Controller_AddMO.SelectedSP(SP_num, mid, mobile, SP_Pri, rs.getString("SERIAL_NUMBER")));
                }
                }
            }
            rs2.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        Table_SelectedSP_AddMO.getItems().setAll(loadlist);
         */
    }

    public void calculate() {
        double spcost = 0;
        //System.out.println("size=  " + list2.size());

        if (!loadlist.isEmpty()) {

            for (int i = 0; i < loadlist.size(); i++) {
                double[] mypriceArray = new double[loadlist.size()];
                mypriceArray[i] = loadlist.get(i).getSP_Price2();
                System.out.println(mypriceArray[i]);
                spcost += mypriceArray[i];
                System.out.println("pr= " + i + "  " + mypriceArray[i]);
                Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
            }
        } else {

            Txfiled_SPCost_AddMO.setText(String.valueOf(0.00));
        }

        double costofmaint = Double.parseDouble(Txfiled_MOCost_AddMO.getText());
        double costofSP = spcost;
        double total = costofmaint + costofSP;
        double vat = total * 0.05;

        System.out.println("VAT== " + vat);
        System.out.printf("%.2f", vat);
        String saa = String.format("%.2f", vat);

        double Total = total + vat;
        Txfiled_VAT_AddMO.setText(String.valueOf(saa));
        Txfiled_TotalCost_AddMO.setText(String.valueOf(Total));

    }

    public int Choose = 0;

    @FXML
    private void M_Txfiled_CusMnum_AddMO(KeyEvent event) {
        Choose = 1;
        System.out.println(event.getEventType().toString());
        System.out.println(event.getText());

        String sql1 = "SELECT * FROM `customer` WHERE `CUS_MOBILE_NBER` = '" + Txfiled_CusMnum_AddMO.getText() + "'";
        String trysql = "SELECT * FROM `customer` WHERE `CUS_MOBILE_NBER` LIKE '" + Txfiled_CusMnum_AddMO.getText() + "%';";
        System.out.println(sql1);
        Search(sql1, Choose);

    }

    @FXML
    private void M_Txfiled_SearchSP_AddMO(KeyEvent event) {
        Choose = 2;
        /*
        System.out.println(event.getEventType().toString());
        System.out.println("Get text ==" + event.getText());
        // System.out.println("getCharacter=== "+event.
        //System.out.println("getOnKeyPressed       " + Txfiled_SearchSP_AddMO.getOnKeyPressed().toString());
        System.out.println("Textfiled===" + Txfiled_SearchSP_AddMO.getText());
        list.clear();
        id2 += event.getText();
        //event.consume();  كانت تمرر الحذف بدون ماتحذف 
        //var1 = event.getText() ;
        System.out.println("Textfiled===" + Txfiled_SearchSP_AddMO.getText());

        System.out.println("Var1===" + var1);

        /* if (event.getText().equals("")) {
            id2 = id2.substring(0, id2.length() - 1);

        }
        if (event.getCode().equals(KeyCode.BACK_SPACE) || event.getCode().equals(KeyCode.DELETE)) {
            id2 = id2.substring(0, id2.length() - 1);

        }
           
        String id1 = Txfiled_SearchSP_AddMO.getText();
        System.out.println("__________  " + id2);
        System.out.println("__________  " + id1);
        System.out.println("__________  " + Txfiled_SearchSP_AddMO.getText());
         */
        list.clear();
        if (Txfiled_SearchSP_AddMO.getText().isEmpty()) {
            String sql1 = "SELECT * FROM spare_parts";
            System.out.println(sql1);
            Search(sql1, Choose);

        } else {
            String sql1 = "SELECT * FROM spare_parts WHERE SP_NAME = '" + Txfiled_SearchSP_AddMO.getText() + "'";
            String trysql = "SELECT * FROM spare_parts WHERE SP_NAME LIKE '" + Txfiled_SearchSP_AddMO.getText() + "%';";
            System.out.println(trysql);
            Search(trysql, Choose);

        }
    }

    @FXML
    private void M_Txfiled_SPCost_AddMO(KeyEvent event) {
        calculate();
    }

    @FXML
    private void M_Txfiled_SPCost_AddMO(ActionEvent event) {
    }

    @FXML
    private void M_KeyReleased_TabelSelecSP_AddMO(KeyEvent event) {
        
         if (event.getCode().isNavigationKey() == true) {  
             SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
        Txfiled_SpPrice_AddMO.setText(String.valueOf(SPSelected2.get(0).getSP_Price2()));
         
    }}

    public static class AddSP {

        private final SimpleIntegerProperty SP_Number;
        private final SimpleStringProperty SP_Name;
        private final SimpleStringProperty SP_Description;
        private final SimpleDoubleProperty SP_Price;
        private final SimpleIntegerProperty SP_Quantity;

        AddSP(int SP_Number, String SP_Name, String SP_Description, double SP_Price, int SP_Quantity) {
            this.SP_Number = new SimpleIntegerProperty(SP_Number);
            this.SP_Name = new SimpleStringProperty(SP_Name);
            this.SP_Description = new SimpleStringProperty(SP_Description);
            this.SP_Price = new SimpleDoubleProperty(SP_Price);
            this.SP_Quantity = new SimpleIntegerProperty(SP_Quantity);

        }

        public Integer getSP_Number() {
            return SP_Number.get();
        }

        public SimpleIntegerProperty SP_NumberProperty() {
            return SP_Number;
        }

        public String getSP_Name() {
            return SP_Name.get();
        }

        public SimpleStringProperty SP_NameProperty() {
            return SP_Name;
        }

        public String getSP_Description() {
            return SP_Description.get();
        }

        public SimpleStringProperty SP_DescriptionProperty() {
            return SP_Description;
        }

        public double getSP_Price() {
            return SP_Price.get();
        }

        public SimpleDoubleProperty SP_PriceProperty() {
            return SP_Price;
        }

        public Integer getSP_Quantity() {
            return SP_Quantity.get();
        }

        public SimpleIntegerProperty SP_QuantityProperty() {
            return SP_Quantity;
        }

    }

    public static class SelectedSP {

        private final SimpleIntegerProperty SP_Number2;
        private final SimpleStringProperty SP_Name2;
        private final SimpleStringProperty SP_Description2;
        private final SimpleDoubleProperty SP_Price2;
        private final SimpleStringProperty SP_SN;
        private final SimpleIntegerProperty SP_Seq_Nber;

        SelectedSP(Integer SP_Number2, String SP_Name2, String SP_Description2, double SP_Price2, String SP_SN, Integer SP_Seq_Nber) {
            this.SP_Number2 = new SimpleIntegerProperty(SP_Number2);
            this.SP_Name2 = new SimpleStringProperty(SP_Name2);
            this.SP_Description2 = new SimpleStringProperty(SP_Description2);
            this.SP_Price2 = new SimpleDoubleProperty(SP_Price2);
            this.SP_SN = new SimpleStringProperty(SP_SN);
            this.SP_Seq_Nber = new SimpleIntegerProperty(SP_Seq_Nber);

        }

        public Integer getSP2_Number() {
            return SP_Number2.get();
        }

        public SimpleIntegerProperty SP_Number2Property() {
            return SP_Number2;
        }

        public String getSP2_Name() {
            return SP_Name2.get();
        }

        public SimpleStringProperty SP_Name2Property() {
            return SP_Name2;
        }

        public String getSP2_Description() {
            return SP_Description2.get();
        }

        public SimpleStringProperty SP_Description2Property() {
            return SP_Description2;
        }

        public double getSP_Price2() {
            return SP_Price2.get();
        }

        public SimpleDoubleProperty SP_Price2Property() {
            return SP_Price2;
        }

        public String getSP_SN() {
            return SP_SN.get();
        }

        public SimpleStringProperty SP_SNProperty() {
            return SP_SN;
        }

        public Integer getSP_Seq_Nber() {
            return SP_Seq_Nber.get();
        }

        public SimpleIntegerProperty SP_Seq_NberProperty() {
            return SP_Seq_Nber;
        }

    }
}
