package sample;

import Connectvy.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JRException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class Controller implements Initializable {
    
    

    ConnectionClass connectionClass = new ConnectionClass();
    // we call conneClass  that we make it up
    Connection connection = connectionClass.getConnection();
    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);

    int count = 0;

    //0 equal EN    And 1 == AR
    public ListView List_of_reports;
    ObservableList<String> items = FXCollections.observableArrayList();

    public ObservableList<MO> CurrnetList = FXCollections.observableArrayList();
    public ObservableList<MO> PendingList = FXCollections.observableArrayList();
    public ObservableList<MO> FinshedList = FXCollections.observableArrayList();
    public ObservableList<MO> PriveousList = FXCollections.observableArrayList();

    ObservableList<String> ListOfSuppliers = FXCollections.observableArrayList();
    ObservableList<AddSP> ListOFSelectedSP = FXCollections.observableArrayList();
    ObservableList<AddSP> ListOFSP = FXCollections.observableArrayList();

    @FXML
    public TableView<MO> Table_CurrentMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_Cost_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Current_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Current_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Current_MngMO;

    @FXML
    private TableView<MO> Table_FinshedMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_MOCost_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Finshed_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Finshed_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Finshed_MngMO;

    @FXML
    private TableView<MO> Table_PreviousMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_MOCost_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Previous_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Previous_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Previous_MngMO;

    @FXML
    private TableView<MO> Table_pendingMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_MOCost_Pending_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Pending_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Pending_MngMO;
    @FXML
    private JFXButton Btn_Edit_MangeCurrentMO;
    @FXML
    private JFXTextField Txfiled_Search_MangeCurrentMO;
    public AnchorPane rootPage;
    @FXML
    private JFXDatePicker Date_REQdate_ReqSP;
    @FXML
    private JFXTextField Txfiled_REQnum_ReqSP;
    @FXML
    private JFXButton Btn_Print_ReqSP;
    @FXML
    private JFXButton Btn_Cancle_ReqSP;
    @FXML
    private JFXButton Btn_Delete_ReqSP;
    @FXML
    private JFXButton Btn_Save_ReqSP;
    @FXML
    private JFXButton Btn_Search_ReqSP;

    @FXML
    private TableView<AddSP> Table_SelectedSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPQuantity_SelectedSP_ReqSP;
    @FXML
    private TableColumn<AddSP, String> Col_SPname_SelectedSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPnum_SelectedSP_ReqSP;
    @FXML
    private JFXTextField Txfiled_QuanitiySP_ReqSP;
    @FXML
    private JFXButton Btn_RemoveSP_ReqSP;

    @FXML
    private TableView<AddSP> Table_AddSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPQuantity_AddSP_ReqSP;
    @FXML
    private TableColumn<AddSP, String> Col_SPname_AddSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPnum_AddSP_ReqSP;
    @FXML
    private JFXTextField Txfiled_SearchSP_ReqSP;
    @FXML
    private JFXButton Btn_AddSP_ReqSP;
    @FXML
    private ComboBox<String> Selct_Supplier_ReqSP;
    @FXML
    private Label SP_aboutTObeOUT_MainWindow;
    @FXML
    private Label PendingMO_MainWindow;
    @FXML
    private Label FinhedMO_MainWindow;
    @FXML
    private Label CurrentMO_MainWindow;

    @FXML
    private ImageView icMaonMove;
    @FXML
    private Label MainLable1;
    @FXML
    private JFXTextField Txfiled_Name_Customer;
    @FXML
    private JFXTextField Txfiled_Address_Customer;
    @FXML
    private JFXTextField Txfiled_MNum_Customer;
    @FXML
    private JFXTextField Txfiled_Email_Customer;
    @FXML
    private JFXButton Btn_ChangeMN_Customer;
    @FXML
    private JFXButton Btn_Save_Customer;
    @FXML
    private JFXButton Btn_Cancle_Customer;

    @FXML
    private JFXButton Btn_Delete_Customer;
    @FXML
    private JFXButton Btn_Search_Customer;

    @FXML
    private JFXTextField Txfiled_Name_SP;
    @FXML
    private JFXTextField Txfiled_Quantity_SP;
    @FXML
    private JFXTextField Txfiled_SPNum_SP;
    @FXML
    private JFXTextField Txfiled_Price_SP;
    @FXML
    private JFXTextArea Txfiled_Discription_SP;
    @FXML
    private JFXButton Btn_Cancle_SP;
    @FXML
    private JFXButton Btn_Delete_SP;
    @FXML
    private JFXButton Btn_Save_SP;

    @FXML
    private ToggleGroup ReportsDate;
    @FXML
    private Label MainLable;
    @FXML
    private JFXButton Btn_Cancel_Employee;

    @FXML
    private JFXButton Btn_Delete_Employee;

    @FXML
    private JFXButton Btn_Save_Employee;

    @FXML
    private JFXButton Btn_Search_Employee;

    @FXML
    private JFXTextField Txfiled_Name_Supplier;

    @FXML
    private JFXTextField Txfiled_Address_Supplier;

    @FXML
    private JFXTextField Txfiled_Num_Supplier;

    @FXML
    private JFXTextField Txfiled_MNum_Supplier;

    @FXML
    private JFXTextField Txfiled_Email_Supplier;

    @FXML
    private JFXButton Btn_Cancle_Supplier;

    @FXML
    private JFXButton Btn_Delete_Supplier;

    @FXML
    private JFXButton Btn_Save_Supplier;

    @FXML
    private JFXButton Btn_Search_Supplier;
    @FXML
    private JFXTextField Txfiled_Name_Employee;

    @FXML
    private JFXTextField Txfiled_MNum_Employee;

    @FXML
    private ComboBox<String> Selct_Sex_Employee;
    @FXML
    private ComboBox<String> Selct_Name_Employee;
    ObservableList<String> ListOfselectName = FXCollections.observableArrayList();
    @FXML
    private JFXTextField Txfiled_MO_Nber;

    @FXML
    private JFXTextField Txfiled_CUS_MNBER;
    @FXML
    private JFXDatePicker Date_Unill;

    @FXML
    private JFXDatePicker Date_StartFrom;

    @FXML
    private ComboBox<String> Selct_JType_Employee;
    ObservableList<String> ListOfJobtype_EN = FXCollections.observableArrayList("Administrator", "ReceptionDesk", "Technician");
    ObservableList<String> ListOfSex_EN = FXCollections.observableArrayList("Male", "Female");
    ObservableList<String> ListOfJobtype_AR = FXCollections.observableArrayList("اداري", "استقبال", "فني");
    ObservableList<String> ListOfSex_AR = FXCollections.observableArrayList("ذكر", "انثى");

    @FXML
    private JFXTextField Txfiled_Num_Employee;

    @FXML
    private JFXTextField Txfiled_Email_Employee;

    @FXML
    private JFXTextField Txfiled_Address_Employee;

    @FXML
    private JFXPasswordField Txfiled_Password_Employee;
    @FXML
    private JFXTextField Txfiled_minimumQuantity_SP;
    @FXML
    private JFXRadioButton Rad_Last_month;
    @FXML
    private JFXRadioButton Rad_LastThree_months;

    @FXML
    private JFXRadioButton Rad_This_Year;

    @FXML
    private JFXRadioButton Rad_Last_Year;

    @FXML
    private JFXRadioButton Rad_Choose_Period;
    @FXML
    public Tab Tab_CustomerMangment;
    public JFXTabPane FatharTap;
    @FXML
    public Tab Tab_SPMangment;
    @FXML
    public Tab Tab_ReqSP;
    @FXML
    public Tab Tab_SupliersMangment;
    @FXML
    public Tab Tab_EmployeeMangment;
    @FXML
    public Tab Tab_Reports;
    @FXML
    public Tab Tab_Tools;
    @FXML
    public Tab Main_Tab;
    @FXML
    public Tab Mangment_MO_Tab;

    public int count_Language;
    @FXML
    private Label Label_UserID_Name;
    @FXML
    private Label Label_UserJob;
    @FXML
    private HBox Hbox_Date_Reports;
    @FXML
    private HBox Hbox_Emolyee_Reports;
    @FXML
    private HBox Hbox_MO_Reports;
    @FXML
    private HBox Hbox_Customer_Reports;
    @FXML
    public JFXButton Btn_SaveDB_Tools;
    @FXML
    public JFXButton Btn_ArchiveDB_Tools;
    @FXML
    private JFXTextField Txfiled_Search_MangeFinshedMO;
    @FXML
    private JFXTextField Txfiled_Search_MangePreviousMO;
    @FXML
    private JFXTextField Txfiled_Search_MangePendingMO;
    @FXML
    private JFXButton Btn_Search_SP;
    //private ListView<?> List_of_reports;

    //int count = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("count_Language =" + count_Language);
        connectionClass.connectDB();

        intilCol();
        //loadAllMO();
        //loadAllSP();
        loadSuppliers();
        List_of_reports.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Selct_Name_Employee.setItems(ListOfselectName);
        loadEmp();
    }

    public void EmJob_SEX_lang(int c) {
        count_Language = c;
        System.out.println("NOW count_Language =" + count_Language);
        if (count_Language == 0) {
            Selct_JType_Employee.setItems(ListOfJobtype_EN);
            Selct_Sex_Employee.setItems(ListOfSex_EN);

            List_of_reports.getItems().add("- current  maintenance operations");
            List_of_reports.getItems().add("- Finished maintenance operations");
            List_of_reports.getItems().add("- previous  maintenance operations");
            List_of_reports.getItems().add("- financial estimate of maintenance");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- list of customers");
            List_of_reports.getItems().add("- list of customer maintenance operations");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- list of employees");
            List_of_reports.getItems().add("- List of maintenance operations for an employee");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- list of Suppliers");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- Spare Parts about to be out of stock");
            List_of_reports.getItems().add("- Spare parts out of stock");

        } else if (count_Language == 1) {
            Selct_JType_Employee.setItems(ListOfJobtype_AR);
            Selct_Sex_Employee.setItems(ListOfSex_AR);

            List_of_reports.getItems().add("- عمليات الصيانة الحالية");
            List_of_reports.getItems().add("- عمليات الصيانة المنتهية");
            List_of_reports.getItems().add("- عمليات الصيانة السابقة");
            List_of_reports.getItems().add("- تقدير مالي عن عملية صيانة");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- قائمة بالعملاء");
            List_of_reports.getItems().add("- قائمة عمليات الصيانة لعميل");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- قائمة بالموظفين");
            List_of_reports.getItems().add("- قائمة عمليات الصيانة لموظف");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- قائمة بالمزودين");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- قطع على وشك النفاذ");
            List_of_reports.getItems().add("- قطع الغيار التي نفذت كميتها");

        }
        loadAllMO();
        System.out.println("AFTEER count_Language =" + count_Language);

    }

    private void intilCol() {

        Col_MOnum_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_Cost_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        Col_MOnum_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_MOCost_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        Col_MOnum_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_MOCost_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        Col_MOnum_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_MOCost_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        //______________________________________________________
        Col_SPnum_AddSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Number"));
        Col_SPname_AddSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Name"));
        Col_SPQuantity_AddSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Quantity"));

        Col_SPnum_SelectedSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Number"));
        Col_SPname_SelectedSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Name"));
        Col_SPQuantity_SelectedSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Quantity"));

    }

    public void loadAllMO() {

        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID";
        ResultSet rs = connectionClass.execQuery(query);

        try {
            while (rs.next()) {
                System.out.println("HEREEEEE");
                //System.out.println("rs.getString(\"MO_Number\")  ="+rs.getString("MO_Number"));

                String MONber = rs.getString("MO_NBER");
                String mobile = rs.getString("CUS_MOBILE_NBER");
                String priceSP = rs.getString("SP_COST");
                String priceMO = rs.getString("MO_COST");
                int MO_num = Integer.parseInt(MONber);
                int CusMobile = Integer.parseInt(mobile);

                double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                /*
                if (rs.getString("STATE").equalsIgnoreCase("cannot be done") || rs.getString("STATE").equalsIgnoreCase("other defects has been detected")
                || rs.getString("STATE").equalsIgnoreCase("created")
                || rs.getString("STATE").equalsIgnoreCase("لا يمكن القيام بعملية الصيانة") || rs.getString("STATE").equalsIgnoreCase("تم الكشف عن عيوب أخرى")
                || rs.getString("STATE").equalsIgnoreCase("تم الإنشاء")) {
                //Pending
                
                PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));
                
                } else if (rs.getString("STATE").equalsIgnoreCase("approved") || rs.getString("STATE").equalsIgnoreCase("under maintenance")
                || rs.getString("STATE").equalsIgnoreCase("تم الموافقة") || rs.getString("STATE").equalsIgnoreCase("تحت الصيانة")) {
                //Current
                CurrnetList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));
                
                } else if (rs.getString("STATE").equalsIgnoreCase("repaired") || rs.getString("STATE").equalsIgnoreCase("تم الاصلاح")) {
                //Finshed
                FinshedList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));
                
                } else if (rs.getString("STATE").equalsIgnoreCase("paid") || rs.getString("STATE").equalsIgnoreCase("disapproved")
                || rs.getString("STATE").equalsIgnoreCase("دفعت") || rs.getString("STATE").equalsIgnoreCase("مرفوضة")) {
                //Priveous
                PriveousList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));
                 */
                String State;

                if (rs.getString("STATE").equals("created") || rs.getString("STATE").equals("تم الإنشاء")) {
                    if (count_Language == 0) {
                        State = "created";
                    } else {
                        State = "تم الإنشاء";

                    }
                    PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("approved") || rs.getString("STATE").equals("تم الموافقة")) {
                    if (count_Language == 0) {
                        State = "approved";

                    } else {
                        State = "تم الموافقة";

                    }
                    CurrnetList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("مرفوضة") || rs.getString("STATE").equals("disapproved")) {
                    if (count_Language == 0) {
                        State = "disapproved";

                    } else {
                        State = "مرفوضة";

                    }
                    PriveousList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("لا يمكن القيام بعملية الصيانة") || rs.getString("STATE").equals("cannot be done")) {
                    if (count_Language == 0) {
                        State = "cannot be done";
                    } else {
                        State = "لا يمكن القيام بعملية الصيانة";

                    }
                    PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("تم الكشف عن عيوب أخرى") || rs.getString("STATE").equals("other defects has been detected")) {

                    if (count_Language == 0) {
                        State = "other defects has been detected";

                    } else {
                        State = "تم الكشف عن عيوب أخرى";

                    }
                    PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("تم الاصلاح") || rs.getString("STATE").equals("repaired")) {

                    if (count_Language == 0) {
                        State = "repaired";

                    } else {
                        State = "تم الاصلاح";

                    }
                    FinshedList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("تحت الصيانة") || rs.getString("STATE").equals("under maintenance")) {
                    System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP+  " + count_Language);

                    if (count_Language == 0) {
                        State = "under maintenance";

                    } else {
                        State = "تحت الصيانة";

                    }
                    CurrnetList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("دفعت") || rs.getString("STATE").equals("paid")) {
                    if (count_Language == 0) {
                        State = "paid";

                    } else {
                        State = "دفعت";

                    }
                    PriveousList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                }
            }
            rs.close();

            String SPqury = "SELECT SP_NBER FROM spare_parts WHERE `SP_QUANTITY` < `MINIMUM_QUANTITY_IN_STOCK`";
            ResultSet rs2 = connectionClass.execQuery(SPqury);
            int rowcount = 0;
            if (rs2.last()) {
                rowcount = rs2.getRow();
            }

            Table_CurrentMO_MngMO.getItems().setAll(CurrnetList);
            Table_FinshedMO_MngMO.getItems().setAll(FinshedList);
            Table_PreviousMO_MngMO.getItems().setAll(PriveousList);
            Table_pendingMO_MngMO.getItems().setAll(PendingList);
            PendingMO_MainWindow.setText(String.valueOf(PendingList.size()));
            CurrentMO_MainWindow.setText(String.valueOf(CurrnetList.size()));
            FinhedMO_MainWindow.setText(String.valueOf(FinshedList.size()));
            SP_aboutTObeOUT_MainWindow.setText(String.valueOf(rowcount));

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SP_Alert_Admin_EN() throws SQLException {

        String SPqury = "SELECT SP_NBER FROM spare_parts WHERE `SP_QUANTITY` < `MINIMUM_QUANTITY_IN_STOCK`";
        ResultSet rs2 = connectionClass.execQuery(SPqury);
        int rowcount = 0;
        if (rs2.last()) {
            rowcount = rs2.getRow();
        }
        if (rowcount > 0) {
            String alertMessage = "There are " + rowcount + "  Spear parts are about to be out of stock";

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText(alertMessage);
            alert.showAndWait();
        }
        // Table_AddSP_ReqSP.getItems().setAll(ListOFSP);
    }

    public void SP_Alert_Admin_AR() throws SQLException {

        //String alertMessage = "قطع الغيار التالية على وشك النفاذ";
        String SPqury = "SELECT SP_NBER FROM spare_parts WHERE `SP_QUANTITY` < `MINIMUM_QUANTITY_IN_STOCK`";
        ResultSet rs2 = connectionClass.execQuery(SPqury);
        int rowcount = 0;
        if (rs2.last()) {
            rowcount = rs2.getRow();
        }

        if (rowcount > 0) {
            String alertMessage = "هناك " + rowcount + " قطع غيار على وشك النفاذ";

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText(alertMessage);
            alert.showAndWait();
        }
    }

    private void loadAllSP() {
        ListOFSP.clear();
        String query = "SELECT * FROM spare_parts";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {
                String SPnumber = rs.getString("SP_NBER");

                String SPquantity = rs.getString("SP_QUANTITY");

                int SP_num = Integer.parseInt(SPnumber);
                int SP_quantity = Integer.parseInt(SPquantity);

                ListOFSP.add(new AddSP(SP_num, rs.getString("SP_NAME"), SP_quantity));

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        Table_AddSP_ReqSP.getItems().setAll(ListOFSP);

    }

    private void loadSpecifecSP() throws SQLException {
        ListOFSP.clear();
        String query = "SELECT * FROM `spare_parts`";
        ResultSet rs = connectionClass.execQuery(query);
        if (rs.isBeforeFirst() == false) {
            System.out.println("NULL RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRr");

            loadAllSP();
            System.out.println("NULL KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        } else {

            while (rs.next()) {
                System.out.println("NOOOOT NULL EEEEEEEEEEEEEE");
                String SPnumber = rs.getString("SP_NBER");

                String SPquantity = rs.getString("SP_QUANTITY");

                int SP_num = Integer.parseInt(SPnumber);
                int SP_quantity = Integer.parseInt(SPquantity);

                ListOFSP.add(new AddSP(SP_num, rs.getString("SP_NAME"), SP_quantity));

            }

            rs.close();
            for (int i = 0; i < ListOFSP.size(); i++) {
                for (int j = 0; j < ListOFSelectedSP.size(); j++) {
                    if (ListOFSP.get(i).getSP_Number().equals(ListOFSelectedSP.get(j).getSP_Number())) {
                        System.out.println(ListOFSP.get(i).getSP_Number() + "-----------" + ListOFSelectedSP.get(j).getSP_Number());
                        System.out.println("i==" + i + "j==" + j);
                        ListOFSP.remove(i);
                        System.out.println("Size==" + ListOFSP.size());
                        System.out.println("NOOOOT NULL OOOOOOOOOOOOOOOOOO");

                    }
                }

            }


            /*   try {
                while (rs.next()) {
                    System.out.println("?????????????????????????????????????????????");

                    String SPnumber = rs.getString("SP_NBER");

                    String SPquantity = rs.getString("SP_QUANTITY");

                    int SP_num = Integer.parseInt(SPnumber);
                    int SP_quantity = Integer.parseInt(SPquantity);
                    System.out.println("");
                    if (ListOFSP.isEmpty()) {
                        System.out.println("Equal 00000000000");
                        ListOFSP.add(new AddSP(SP_num, rs.getString("SP_NAME"), SP_quantity));

                    } else {
                        for (int i = 0; i < ListOFSP.size(); i++) {
                            if (ListOFSP.get(i).getSP_Number().equals(SP_num)) {
                                System.out.println("How are youuuu!!!" + ListOFSP.get(i).getSP_Number() + "====" + SP_num);
                                rs.next();
                                SPnumber = rs.getString("SP_NBER");
                                 SP_num = Integer.parseInt(SPnumber);
i=1000;
                                
                            } else if (!ListOFSP.get(i).getSP_Number().equals(SP_num)) {

                                ListOFSP.add(new AddSP(SP_num, rs.getString("SP_NAME"), SP_quantity));

                                System.out.println("i am fine!!!");
                              
                            }
                        }
                    }
                }
                rs.close();

            } catch (SQLException ex) {
                ex.printStackTrace();

            }*/
            // ListOFSelectedSP.forEach(ListOFSP::remove);
            Table_AddSP_ReqSP.getItems().setAll(ListOFSP);

        }
    }

    private void loadSpSelected() throws SQLException {
        ListOFSelectedSP.clear();
        System.out.println("i am in loadSpSelected ");
        String SQLqq = "SELECT * FROM `spare_parts` s JOIN `attach` r ON s.SP_NBER = r.SP_NBER WHERE r.REQUEST_NBER=" + Txfiled_REQnum_ReqSP.getText();

        System.out.println(SQLqq);
        ResultSet rs = connectionClass.execQuery(SQLqq);

        try {
            double a = 0.00;
            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    int SP_num = Integer.parseInt(rs.getString("SP_NBER"));
                    int SP_Quant = Integer.parseInt(rs.getString("Req_QUANTITY"));

                    //loadlist.add(new Controller_AddMO.SelectedSP(SP_num, rs.getString("SP_NAME"), rs.getString("DESCRIPTION"), SP_Pri2, rs.getString("SERIAL_NUMBER"),SP_Seq));
                    // if (rs.getString("REQUEST_NBER").equals(Txfiled_REQnum_ReqSP.getText())) {
                    //loadlist.add(new Controller_AddMO.SelectedSP(SP_num, mid, mobile, SP_Pri, rs.getString("SERIAL_NUMBER")));
                    ListOFSelectedSP.add(new AddSP(SP_num, rs.getString("SP_Name"), SP_Quant));                // }

                }
                //System.out.println(loadlist.get(0).getSP2_Name());
                Table_SelectedSP_ReqSP.getItems().setAll(ListOFSelectedSP);
            }

            rs.close();

        } catch (SQLException ex) {
        }
    }

    void loadWindow(String loc, String title) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle(title);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Btn_AddMO_MangeMO(ActionEvent event) {
        System.out.println("ok now ?" + count_Language);

        FXMLLoader loader = new FXMLLoader();

        //Controller controller = loader.getController();
        if (count_Language == 0) {
            loader.setLocation(getClass().getResource("/sample/AddMo_EN.fxml"));
            try {
                loader.load();

            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            Controller_AddMO controller_AddMO = loader.getController();
            controller_AddMO.SetMoStatus_language(0);
        } else if (count_Language == 1) {
            loader.setLocation(getClass().getResource("/sample/AddMo_AR.fxml"));

            try {

                loader.load();
                Controller_AddMO controller_AddMO = loader.getController();
                controller_AddMO.SetMoStatus_language(1);

            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.showAndWait();

        RefreshMOTables();

    }

    @FXML
    private void M_Btn_ChangeMN_Customer(ActionEvent event) {
        count = 3;
        Txfiled_MNum_Customer.setDisable(false);
        Txfiled_Name_Customer.setDisable(true);
        Txfiled_Email_Customer.setDisable(true);
        Txfiled_Address_Customer.setDisable(true);
    }

    @FXML
    private void M_Btn_Cancle_Customer(ActionEvent event) {
        Txfiled_MNum_Customer.clear();
        Txfiled_Name_Customer.clear();
        Txfiled_Email_Customer.clear();
        Txfiled_Address_Customer.clear();

        Txfiled_MNum_Customer.setDisable(false);
    }

    @FXML
    private void Btn_Edit_MangeFinshedMO(ActionEvent event) throws SQLException {
        openEdit(Table_FinshedMO_MngMO);

    }

    @FXML
    private void Btn_Edit_MangePreviousMO(ActionEvent event) throws SQLException {
        openEdit(Table_PreviousMO_MngMO);

    }

    public void openEdit(TableView TableName) throws SQLException {

        ObservableList<MO> SPSelected, AllSP;
        AllSP = TableName.getItems();
        SPSelected = TableName.getSelectionModel().getSelectedItems();
        if (TableName.getSelectionModel().getSelectedItems().isEmpty() == false) {
            System.out.println("Cus name   " + SPSelected.get(0).getCus_Name());
            System.out.println("MO_NUMBER=  " + SPSelected.get(0).getMO_Number());

            FXMLLoader loader = new FXMLLoader();

            if (count_Language == 0) {

                loader.setLocation(getClass().getResource("/sample/AddMo_EN.fxml"));
                try {
                    loader.load();

                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                Controller_AddMO controller_AddMO = loader.getController();
                controller_AddMO.SetMoStatus_language(0);

            } else if (count_Language == 1) {

                loader.setLocation(getClass().getResource("/sample/AddMo_AR.fxml"));
                try {
                    loader.load();

                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                Controller_AddMO controller_AddMO = loader.getController();
                controller_AddMO.SetMoStatus_language(1);

            }
            Controller_AddMO controller_AddMO = loader.getController();

            controller_AddMO.Search_MO(SPSelected.get(0).getMO_Number());
            /*

            Connection connection = connectionClass.getConnection();

            Statement st = connection.createStatement();
            st.executeQuery("SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE MO_NBER = " + SPSelected.get(0).getMO_Number());

            ResultSet rs = st.getResultSet();

            if (rs.first()) {

                Controller_AddMO controller_AddMO = loader.getController();

                controller_AddMO.loadInTO(rs.getString("MO_NBER"), rs.getString("CUS_NAME"), rs.getString("PROBLEM_DESC"), rs.getString("CUS_MOBILE_NBER"), rs.getString("SP_COST"), rs.getString("MO_COST"),
                        rs.getString("DEVICE_SN"), rs.getString("DEVICE_DESC"), rs.getString("WARRANTY"), rs.getString("STARTING_DATE"), rs.getString("ENDING_DATE"), rs.getString("STATE"), rs.getString("EMP_NAME"));
//controller_AddMO.Txfiled_CusName_AddMO.setText("GGGGGGGGGGGGGGGGGGGGG");
            }
             */
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.showAndWait();
            RefreshMOTables();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            if (count_Language == 0) {

                alert.setContentText("Please choose a maintenance process from the table");
                alert.showAndWait();
            } else if (count_Language == 1) {

                alert.setContentText("الرجاء اختيار عملية صيانة من الجدول");
                alert.showAndWait();

            }

        }

    }

    public void RefreshMOTables() {
        CurrnetList.clear();
        PendingList.clear();
        FinshedList.clear();
        PriveousList.clear();

        loadAllMO();
    }

    @FXML
    private void Btn_Edit_MangePendingMO(ActionEvent event) throws SQLException {
        openEdit(Table_pendingMO_MngMO);

    }

    @FXML
    private void M_Btn_Delete_Customer(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("");
        if (count_Language == 0) {
            alert.setContentText("This Cstomer will be deleted ");
        } else {
            alert.setContentText("  سوف يتم حذف هذا العميل ");

        }

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            String deletSP = "DELETE FROM  `maintenance_operation` " + " WHERE CUS_MOBILE_NBER= " + Txfiled_MNum_Customer.getText();
            String sql1 = "DELETE FROM  `customer` " + " WHERE CUS_MOBILE_NBER= " + Txfiled_MNum_Customer.getText();
            System.out.println(deletSP);
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(deletSP);
            statement1.executeUpdate(sql1);
            clear();
        }

    }

    @FXML
    private void M_Btn_Save_Customer(ActionEvent event) throws SQLException {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        if (Txfiled_MNum_Customer.getText().isEmpty() || Txfiled_Name_Customer.getText().isEmpty()) {
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

        if (count == 1) {
            System.out.println("Equal  insert");
            String sqll = ("INSERT INTO customer (CUS_MOBILE_NBER,CUS_NAME,CUS_EMAIL,CUS_ADDRESS) VALUES('" + Txfiled_MNum_Customer.getText() + "','" + Txfiled_Name_Customer.getText() + "','" + Txfiled_Email_Customer.getText() + "','" + Txfiled_Address_Customer.getText() + "')");
            //"INSERT INTO customer (CUS_MOBILE_NBER ,'CUS_NAME','CUS_EMAIL',' CUS_ADDRESS') VALUES ("+Txfiled_MNum_Customer.getText()+ ""+","+"" +   Txfiled_Name_Customer.getText() + "" + ","+"" +    Txfiled_Email_Customer.getText()+ "" + ","+"" +  Txfiled_Address_Customer.getText()+")"; 
            System.out.println(sqll);
            java.sql.Statement statement1 = connection.createStatement();

            statement1.executeUpdate(sqll);
            if (count_Language == 0) {

                alert2.setContentText(" A new Customer has been created");
            } else {
                alert2.setContentText("تم انشاء عميل جديد");

            }

            alert2.showAndWait();

        } else if (count == 2) {
            System.out.println("Equal  update");
            //System.out.println(Selct_MoStatus_AddMO.getValue());
            String sql1 = "UPDATE  `customer` SET CUS_NAME='" + Txfiled_Name_Customer.getText() + "',CUS_EMAIL='" + Txfiled_Email_Customer.getText() + "',CUS_ADDRESS='" + Txfiled_Address_Customer.getText()
                    + "' WHERE CUS_MOBILE_NBER= '" + Txfiled_MNum_Customer.getText() + "'";
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);
            if (count_Language == 0) {

                alert2.setContentText(" Changes saved successfully");
            } else {
                alert2.setContentText("تم حفظ التعديلات بنجاح");

            }

            alert2.showAndWait();
        } else if (count == 3) {
            System.out.println("Equal  update mobile number");
            String sqll = "UPDATE customer SET CUS_MOBILE_NBER='" + Txfiled_MNum_Customer.getText() + "' WHERE CUS_NAME= '" + Txfiled_Name_Customer.getText() + "'";
            System.out.println(sqll);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqll);
            if (count_Language == 0) {

                alert2.setContentText("Mobile Number has been changed");
            } else {
                alert2.setContentText("تم تغيير رقم الهاتف");

            }

            alert2.showAndWait();

        }
        //count = 2;

    }
    public int number = 0;

    @FXML
    private void M_Btn_Search_Customer(ActionEvent event) throws SQLException {

        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `customer`  WHERE CUS_MOBILE_NBER = " + Txfiled_MNum_Customer.getText());
        ResultSet rs = st.getResultSet();
        if (rs.first()) {

            System.out.println(Txfiled_MNum_Customer.getText());

            System.out.println("THIS MO NUMBER IN DB== " + rs.getString("CUS_MOBILE_NBER"));
            System.out.println("THIS MO NUMBER IN FILED== " + Txfiled_MNum_Customer.getText());

            if (rs.getString("CUS_MOBILE_NBER").equals(Txfiled_MNum_Customer.getText())) {

                count = 2;

                Txfiled_Name_Customer.setText(rs.getString("CUS_NAME"));
                Txfiled_Email_Customer.setText(rs.getString("CUS_EMAIL"));
                Txfiled_Address_Customer.setText(rs.getString("CUS_ADDRESS"));
                Txfiled_MNum_Customer.setDisable(true);
                Btn_Delete_Customer.setDisable(false);
                Btn_Cancle_Customer.setDisable(false);
                Btn_ChangeMN_Customer.setDisable(false);

            }
        } else {
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            count = 1;
            Txfiled_MNum_Customer.setDisable(true);
            Btn_Cancle_Customer.setDisable(false);
            Btn_Save_Customer.setDisable(false);
            Btn_Delete_Customer.setDisable(false);
            Btn_ChangeMN_Customer.setDisable(false);

        }

    }

    @FXML
    void M_Btn_Cancel_Employee(ActionEvent event) {
        Txfiled_Num_Employee.setDisable(false);
        Txfiled_Num_Employee.clear();
        Txfiled_Name_Employee.clear();
        Txfiled_Email_Employee.clear();
        Txfiled_Address_Employee.clear();
        Txfiled_MNum_Employee.clear();
        Selct_JType_Employee.getSelectionModel().clearSelection();
        Selct_Sex_Employee.getSelectionModel().clearSelection();
        Txfiled_Password_Employee.clear();

    }

    @FXML
    void M_Btn_Delete_Employee(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("");
        if (count_Language == 0) {
            alert.setContentText("This Employee will be deleted ");
        } else {
            alert.setContentText("  سوف يتم حذف هذا الموظف ");

        }

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            String deletSP = "DELETE FROM  `maintenance_operation` " + " WHERE EMPLOYEE_ID= " + Txfiled_Num_Employee.getText();
            String sql1 = "DELETE FROM  `employee` " + " WHERE EMPLOYEE_ID= " + Txfiled_Num_Employee.getText();
            System.out.println(deletSP);
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(deletSP);
            statement1.executeUpdate(sql1);
            Txfiled_Num_Employee.setDisable(false);
            Txfiled_Num_Employee.clear();
            Txfiled_Name_Employee.clear();
            Txfiled_Email_Employee.clear();
            Txfiled_Address_Employee.clear();
            Txfiled_MNum_Employee.clear();
            Selct_JType_Employee.getSelectionModel().clearSelection();
            Selct_Sex_Employee.getSelectionModel().clearSelection();
            Txfiled_Password_Employee.clear();

        }

    }

    @FXML
    private void M_Btn_Save_Employee(ActionEvent event) throws SQLException {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        if (Txfiled_Num_Employee.getText().isEmpty() || Txfiled_Name_Employee.getText().isEmpty() || Txfiled_Email_Employee.getText().isEmpty()
                || Txfiled_Address_Employee.getText().isEmpty() || Txfiled_MNum_Employee.getText().isEmpty() || Selct_JType_Employee.getValue().isEmpty() || Selct_Sex_Employee.getValue().isEmpty() || Txfiled_Password_Employee.getText().isEmpty()) {
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

        if (count == 1) {
            System.out.println("Equal  insert");
            String sqll = "INSERT INTO employee (EMPLOYEE_ID, EMP_NAME, EMP_EMAIL,EMP_ADDRESS,EMP_MOBILE_NBER,JOP_TYPE,SEX,PASSWORD) VALUES (" + number + "," + "'" + Txfiled_Name_Employee.getText() + "'" + "," + "'" + Txfiled_Email_Employee.getText()
                    + "'" + "," + "'" + Txfiled_Address_Employee.getText() + "'" + "," + "'" + Txfiled_MNum_Employee.getText() + "'" + "," + "'" + Selct_JType_Employee.getValue() + "'" + "," + "'" + Selct_Sex_Employee.getValue()
                    + "'" + "," + "'" + Txfiled_Password_Employee.getText() + "')";
            System.out.println(sqll);
            java.sql.Statement statement1 = connection.createStatement();

            statement1.executeUpdate(sqll);
            if (count_Language == 0) {

                alert2.setContentText(" A new Employee has been created");
            } else {
                alert2.setContentText("تم انشاء موظف جديد");

            }

            alert2.showAndWait();

        } else if (count == 2) {
            System.out.println("Equal  update");
            //System.out.println(Selct_MoStatus_AddMO.getValue());
            String sql1 = "UPDATE  `employee` SET   EMP_NAME='" + Txfiled_Name_Employee.getText() + "',EMP_EMAIL='" + Txfiled_Email_Employee.getText() + "',EMP_ADDRESS='" + Txfiled_Address_Employee.getText() + "',EMP_MOBILE_NBER='" + Txfiled_MNum_Employee.getText()
                    + "',JOP_TYPE='" + Selct_JType_Employee.getValue() + "',SEX='" + Selct_Sex_Employee.getValue() + "',PASSWORD='" + Txfiled_Password_Employee.getText()
                    + "'WHERE EMPLOYEE_ID=' " + Txfiled_Num_Employee.getText() + "'";

            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);
            if (count_Language == 0) {

                alert2.setContentText(" Changes saved successfully");
            } else {
                alert2.setContentText("تم حفظ التعديلات بنجاح");

            }

            alert2.showAndWait();
        }
        count = 2;

    }

    @FXML
    private void M_Btn_Search_Employee(ActionEvent event) throws SQLException {

        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `employee`  WHERE EMPLOYEE_ID = " + Txfiled_Num_Employee.getText());
        ResultSet rs = st.getResultSet();
        if (rs.first()) {

            System.out.println(Txfiled_Num_Employee.getText());

            System.out.println("THIS NU NUMBER IN DB== " + rs.getString("EMPLOYEE_ID"));
            System.out.println("THIS NU NUMBER IN FILED== " + Txfiled_Num_Employee.getText());

            if (rs.getString("EMPLOYEE_ID").equals(Txfiled_Num_Employee.getText())) {

                count = 2;

                Txfiled_Name_Employee.setText(rs.getString("EMP_NAME"));
                Txfiled_Email_Employee.setText(rs.getString("EMP_EMAIL"));
                Txfiled_Address_Employee.setText(rs.getString("EMP_ADDRESS"));
                Txfiled_MNum_Employee.setText(rs.getString("EMP_MOBILE_NBER"));

                Txfiled_Password_Employee.setText(rs.getString("PASSWORD"));

                if (rs.getString("JOP_TYPE").equals("فني") || rs.getString("JOP_TYPE").equals("Technician")) {
                    if (count_Language == 0) {
                        Selct_JType_Employee.getSelectionModel().select("Technician");
                    } else {
                        Selct_JType_Employee.getSelectionModel().select("فني");

                    }
                } else if (rs.getString("JOP_TYPE").equals("اداري") || rs.getString("JOP_TYPE").equals("Administrator")) {
                    if (count_Language == 0) {
                        Selct_JType_Employee.getSelectionModel().select("Administrator");
                    } else {
                        Selct_JType_Employee.getSelectionModel().select("اداري");

                    }

                } else if (rs.getString("JOP_TYPE").equals("استقبال") || rs.getString("JOP_TYPE").equals("ReceptionDesk")) {
                    if (count_Language == 0) {
                        Selct_JType_Employee.getSelectionModel().select("ReceptionDesk");
                    } else {
                        Selct_JType_Employee.getSelectionModel().select("استقبال");

                    }

                }
                if (rs.getString("SEX").equals("ذكر") || rs.getString("SEX").equals("Male")) {
                    if (count_Language == 0) {
                        Selct_Sex_Employee.getSelectionModel().select("Male");
                    } else {
                        Selct_Sex_Employee.getSelectionModel().select("ذكر");

                    }

                } else if (rs.getString("SEX").equals("انثى") || rs.getString("SEX").equals("Female")) {
                    if (count_Language == 0) {
                        Selct_Sex_Employee.getSelectionModel().select("Female");
                    } else {
                        Selct_Sex_Employee.getSelectionModel().select("انثى");

                    }

                }
                //Selct_JType_Employee.getSelectionModel().select(rs.getString("JOP_TYPE"));

                //Selct_Sex_Employee.getSelectionModel().select(rs.getString("SEX"));
                Txfiled_Num_Employee.setDisable(true);
                Btn_Save_Employee.setDisable(false);
                Btn_Delete_Employee.setDisable(false);
                Btn_Cancel_Employee.setDisable(false);

            }
        } else {
            System.out.println("??????????????? why");
            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM `employee` ORDER BY EMPLOYEE_ID DESC LIMIT 1");
            ResultSet rs2 = st2.getResultSet();
            //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
            if (rs2.first()) {

                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                //System.out.println();

                count = 1;
                number = Integer.parseInt(rs2.getString("EMPLOYEE_ID"));
                number++;
                System.out.println(number);
                Txfiled_Num_Employee.setText(String.valueOf(number));
                Txfiled_Num_Employee.setDisable(true);
                Btn_Save_Employee.setDisable(false);
                Btn_Delete_Employee.setDisable(false);
                Btn_Cancel_Employee.setDisable(false);

            }
        }
    }

    @FXML
    void M_Btn_Search_SP(ActionEvent event) throws SQLException {

        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `spare_parts`  WHERE SP_NBER = " + Txfiled_SPNum_SP.getText());
        ResultSet rs = st.getResultSet();
        if (rs.first()) {

            System.out.println(Txfiled_SPNum_SP.getText());

            System.out.println("THIS NU NUMBER IN DB== " + rs.getString("SP_NBER"));
            System.out.println("THIS NU NUMBER IN FILED== " + Txfiled_SPNum_SP.getText());

            if (rs.getString("SP_NBER").equals(Txfiled_SPNum_SP.getText())) {

                count = 2;

                Txfiled_Name_SP.setText(rs.getString("SP_NAME"));
                Txfiled_Price_SP.setText(rs.getString("PRICE"));
                Txfiled_Quantity_SP.setText(rs.getString("SP_QUANTITY"));
                Txfiled_Discription_SP.setText(rs.getString("DESCRIPTION"));
                Txfiled_minimumQuantity_SP.setText(rs.getString("MINIMUM_QUANTITY_IN_STOCK"));

                Txfiled_SPNum_SP.setDisable(true);
                Btn_Save_SP.setDisable(false);
                Btn_Delete_SP.setDisable(false);
                Btn_Cancle_SP.setDisable(false);

            }
        } else {

            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM spare_parts ORDER BY SP_NBER DESC LIMIT 1");
            ResultSet rs2 = st2.getResultSet();
            //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
            if (rs2.first()) {

                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                //System.out.println();

                count = 1;
                number = Integer.parseInt(rs2.getString("SP_NBER"));
                number++;
                System.out.println(number);
                Txfiled_SPNum_SP.setText(String.valueOf(number));
                Txfiled_SPNum_SP.setDisable(true);
                Btn_Save_SP.setDisable(false);
                Btn_Delete_SP.setDisable(false);
                Btn_Cancle_SP.setDisable(false);

            }
        }
    }

    @FXML
    private void M_Btn_Cancle_SP(ActionEvent event) {
        Txfiled_SPNum_SP.setDisable(false);
        Txfiled_Name_SP.clear();
        Txfiled_Price_SP.clear();
        Txfiled_Quantity_SP.clear();
        Txfiled_Discription_SP.clear();
        Txfiled_minimumQuantity_SP.clear();
    }

    @FXML
    private void M_Btn_Delete_SP(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("");
        if (count_Language == 0) {
            alert.setContentText("This Spare part will be deleted ");
        } else {
            alert.setContentText("  سوف يتم حذف قطعة الغيار ");

        }

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            String deleteSP = "DELETE FROM  `require` " + " WHERE SP_NBER= " + Txfiled_SPNum_SP.getText();
            String deletSP = "DELETE FROM  `attach` " + " WHERE SP_NBER= " + Txfiled_SPNum_SP.getText();
            String sql1 = "DELETE FROM  `spare_parts`" + " WHERE SP_NBER= " + Txfiled_SPNum_SP.getText();
            System.out.println(deleteSP);
            System.out.println(deletSP);
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(deleteSP);
            statement1.executeUpdate(deletSP);
            statement1.executeUpdate(sql1);
            Txfiled_SPNum_SP.setDisable(false);
            Txfiled_Name_SP.clear();
            Txfiled_Price_SP.clear();
            Txfiled_Quantity_SP.clear();
            Txfiled_Discription_SP.clear();
            Txfiled_minimumQuantity_SP.clear();

        }
    }

    @FXML
    private void M_Btn_Save_SP(ActionEvent event) throws SQLException {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        if (Txfiled_SPNum_SP.getText().isEmpty() || Txfiled_Name_SP.getText().isEmpty() || Txfiled_Price_SP.getText().isEmpty()
                || Txfiled_Quantity_SP.getText().isEmpty() || Txfiled_Discription_SP.getText().isEmpty()) {
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

        if (count == 1) {
            System.out.println("=====" + Txfiled_Name_SP.getText());
            System.out.println("Equal  insert");
            String A = "SET CHARACTER SET utf8 ";
            String sqll = "INSERT INTO spare_parts (SP_NBER, SP_NAME, PRICE,SP_QUANTITY ,DESCRIPTION, MINIMUM_QUANTITY_IN_STOCK) VALUES (" + number + "," + "'" + Txfiled_Name_SP.getText() + "'" + "," + "'" + Txfiled_Price_SP.getText()
                    + "'" + "," + "'" + Txfiled_Quantity_SP.getText() + "'" + "," + "'" + Txfiled_Discription_SP.getText() + "'" + "," + "'" + Txfiled_minimumQuantity_SP.getText() + "')";
            System.out.println(sqll);
            java.sql.Statement statement1 = connection.createStatement();
            //statement1.executeUpdate(A);

            statement1.executeUpdate(sqll);
            if (count_Language == 0) {

                alert2.setContentText(" A new Spare part has been created");
            } else {
                alert2.setContentText("تم انشاء قطعة غيار جديدة");

            }

            alert2.showAndWait();

        } else if (count == 2) {
            System.out.println("Equal  update");
            //System.out.println(Selct_MoStatus_AddMO.getValue());
            String sql1 = "UPDATE  `spare_parts` SET  SP_NAME='" + Txfiled_Name_SP.getText() + "',PRICE='" + Txfiled_Price_SP.getText() + "',SP_QUANTITY='" + Txfiled_Quantity_SP.getText() + "',DESCRIPTION='" + Txfiled_Discription_SP.getText() + "',MINIMUM_QUANTITY_IN_STOCK='" + Txfiled_minimumQuantity_SP.getText()
                    + " 'WHERE SP_NBER=' " + Txfiled_SPNum_SP.getText() + "'";

            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);
            if (count_Language == 0) {

                alert2.setContentText(" Changes saved successfully");
            } else {
                alert2.setContentText("تم حفظ التعديلات بنجاح");

            }

            alert2.showAndWait();
        }
        // count = 2;

    }

    @FXML
    private void M_Btn_Save_Supplier(ActionEvent event) throws SQLException {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        if (Txfiled_Num_Supplier.getText().isEmpty() || Txfiled_MNum_Supplier.getText().isEmpty() || Txfiled_Email_Supplier.getText().isEmpty()
                || Txfiled_Name_Supplier.getText().isEmpty() || Txfiled_Address_Supplier.getText().isEmpty()) {
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

        if (count == 1) {
            System.out.println("Equal  insert");
            String sqll = "INSERT INTO supplier (SUPPLIER_NBER, SUP_MOBILE_NBER, SUP_EMAIL,SUP_NAME ,SUP_ADDRESS) VALUES (" + number + "," + "'" + Txfiled_MNum_Supplier.getText() + "'" + "," + "'" + Txfiled_Email_Supplier.getText()
                    + "'" + "," + "'" + Txfiled_Name_Supplier.getText() + "'" + "," + "'" + Txfiled_Address_Supplier.getText() + "')";
            System.out.println(sqll);
            java.sql.Statement statement1 = connection.createStatement();

            statement1.executeUpdate(sqll);
            if (count_Language == 0) {

                alert2.setContentText(" A new Supplier has been created");
            } else {
                alert2.setContentText("تم انشاء مزود جديد");

            }

            alert2.showAndWait();

        } else if (count == 2) {
            System.out.println("Equal  update");
            //System.out.println(Selct_MoStatus_AddMO.getValue());
            String sql1 = "UPDATE  `supplier` SET  SUP_MOBILE_NBER='" + Txfiled_MNum_Supplier.getText() + "',SUP_EMAIL='" + Txfiled_Email_Supplier.getText() + "',SUP_NAME='" + Txfiled_Name_Supplier.getText() + "',SUP_ADDRESS='" + Txfiled_Address_Supplier.getText()
                    + "' WHERE SUPPLIER_NBER= '" + Txfiled_Num_Supplier.getText() + "'";

            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);
            if (count_Language == 0) {

                alert2.setContentText(" Changes saved successfully");
            } else {
                alert2.setContentText("تم حفظ التعديلات بنجاح");

            }

            alert2.showAndWait();
        }
        count = 2;

    }

    @FXML
    private void M_Btn_Search_Supplier(ActionEvent event) throws SQLException, ParseException {

        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `supplier`  WHERE SUPPLIER_NBER = " + Txfiled_Num_Supplier.getText());
        ResultSet rs = st.getResultSet();
        if (rs.first()) {

            System.out.println(Txfiled_Num_Supplier.getText());

            System.out.println("THIS NU NUMBER IN DB== " + rs.getString("SUPPLIER_NBER"));
            System.out.println("THIS NU NUMBER IN FILED== " + Txfiled_Num_Supplier.getText());

            if (rs.getString("SUPPLIER_NBER").equals(Txfiled_Num_Supplier.getText())) {

                count = 2;

                Txfiled_MNum_Supplier.setText(rs.getString("SUP_MOBILE_NBER"));
                Txfiled_Email_Supplier.setText(rs.getString("SUP_EMAIL"));
                Txfiled_Name_Supplier.setText(rs.getString("SUP_NAME"));
                Txfiled_Address_Supplier.setText(rs.getString("SUP_ADDRESS"));

                Txfiled_Num_Supplier.setDisable(true);
                Btn_Save_Supplier.setDisable(false);
                Btn_Delete_Supplier.setDisable(false);
                Btn_Cancle_Supplier.setDisable(false);

            }
        } else {
            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM supplier ORDER BY SUPPLIER_NBER DESC LIMIT 1");
            ResultSet rs2 = st2.getResultSet();
            //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
            if (rs2.first()) {

                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                //System.out.println();

                count = 1;
                number = Integer.parseInt(rs2.getString("SUPPLIER_NBER"));
                number++;
                System.out.println(number);
                Txfiled_Num_Supplier.setText(String.valueOf(number));
                Txfiled_Num_Supplier.setDisable(true);
                Btn_Save_Supplier.setDisable(false);
                Btn_Delete_Supplier.setDisable(false);
                Btn_Cancle_Supplier.setDisable(false);

            }
        }
    }

    @FXML
    private void M_Btn_Delete_Supplier(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("");
        if (count_Language == 0) {
            alert.setContentText("This Supplier will be deleted ");
        } else {
            alert.setContentText("  سوف يتم حذف هذا المزود ");

        }

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            String deletSP = "DELETE FROM `requested_spare_parts` " + " WHERE SUPPLIER_NBER= " + Txfiled_Num_Supplier.getText();
            String sql1 = "DELETE FROM  `supplier` " + " WHERE SUPPLIER_NBER= " + Txfiled_Num_Supplier.getText();
            System.out.println(deletSP);
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(deletSP);
            statement1.executeUpdate(sql1);
            clearSUP();
        }

    }

    @FXML
    private void M_Btn_Cancle_Supplier(ActionEvent event) {

        clearSUP();
    }

    public void clearSUP() {
        Txfiled_Num_Supplier.setDisable(false);
        Txfiled_Num_Supplier.clear();
        Txfiled_MNum_Supplier.clear();
        Txfiled_Email_Supplier.clear();
        Txfiled_Name_Supplier.clear();
        Txfiled_Address_Supplier.clear();

    }

    private void M_MousClicked_listv(ActionEvent Event) {
        List_of_reports.setItems(items);

        List_of_reports.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //ObservableList <String> names;
        //names= List_of_reports.getSelectionModel().getSelectedItems();

        //names= List_of_reports.setItems("عمليات الصيانة الحالية");
        // List_of_reports.getSelectionModel().getSelectedItems();
        // System.out.println( List_of_reports.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void prbuttonReports(ActionEvent event) throws SQLException, JRException {
        if (count_Language == 1) {
            ObservableList<String> names;
            names = List_of_reports.getSelectionModel().getSelectedItems();
            //names= List_of_reports.setItems("عمليات الصيانة الحالية");
            // for (String name : names){
            //   System.out.println("");
            // System.out.println(names);

            String SelectedItem = (String) List_of_reports.getSelectionModel().getSelectedItem();

            System.out.println("HERE  B:" + SelectedItem);

            LocalDate Date = LocalDate.now();
            if (SelectedItem.equalsIgnoreCase("- عمليات الصيانة السابقة")) {

                if (Rad_Last_month.isSelected()) {
                    System.out.println(Rad_Last_month.getText());
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(1);
                    print.PreviousMO(bb, ss);

                } else if (Rad_LastThree_months.isSelected()) {
                    System.out.println(Rad_LastThree_months.getText());
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(3);
                    print.PreviousMO(bb, ss);

                } else if (Rad_This_Year.isSelected()) {
                    System.out.println(Rad_This_Year.getText());
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.PreviousMO(bb, ss);
                } else if (Rad_Last_Year.isSelected()) {
                    System.out.println(Rad_Last_Year.getText());
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.PreviousMO(bb, ss);

                } else if (Rad_Choose_Period.isSelected()) {
                    System.out.println(Rad_Choose_Period.getText());

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate AA = Date_StartFrom.getValue();
                    LocalDate MM = Date_Unill.getValue();
                    print.PreviousMOPeriod(AA, MM);
                    System.out.println("hhhhhhhhhhhhhhh");
                }
            } else if (SelectedItem.equalsIgnoreCase("- عمليات الصيانة الحالية")) {
                if (Rad_Last_month.isSelected()) {

                    //String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND '" + LocalDate.now() + "'>='" + Date.minusMonths(3) + "'";
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(1);
                    print.CurrentMO(bb, ss);

                } else if (Rad_LastThree_months.isSelected()) {
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(3);
                    print.CurrentMO(bb, ss);

                } else if (Rad_This_Year.isSelected()) {
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.CurrentMO(bb, ss);

                } else if (Rad_Last_Year.isSelected()) {
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.CurrentMO(bb, ss);

                } else if (Rad_Choose_Period.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";

                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    //  List_of_reports.getSelectionModel().getSelectedItems().add("عمليات الصيانة الحالية");
                    //List_of_reports.setItems(s);
                    printreport print = new printreport();
                    LocalDate AA = Date_StartFrom.getValue();
                    LocalDate MM = Date_Unill.getValue();
                    print.CurrentMOPeriod(AA, MM);
                }

            } else if (SelectedItem.equalsIgnoreCase("- عمليات الصيانة المنتهية")) {
                if (Rad_Last_month.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(1);
                    print.FinshedMO(bb, ss);

                } else if (Rad_LastThree_months.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(3);
                    print.FinshedMO(bb, ss);

                } else if (Rad_This_Year.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.FinshedMO(bb, ss);

                } else if (Rad_Last_Year.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.FinshedMO(bb, ss);

                } else if (Rad_Choose_Period.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate AA = Date_StartFrom.getValue();
                    LocalDate MM = Date_Unill.getValue();
                    print.FinshedMOPeriod(AA, MM);
                }

            } else if (SelectedItem.equalsIgnoreCase("- تقدير مالي عن عملية صيانة")) {
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + Txfiled_MO_Nber.getText() + "' OR r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                System.out.println(q);
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + Txfiled_MO_Nber.getText() + "' OR r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    String bb = Txfiled_MO_Nber.getText();
                    String ss = Txfiled_CUS_MNBER.getText();
                    print.FinancialassessReport(bb, ss);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("This maintenance does not have financialassessment");
                    alert.showAndWait();
                    return;

                }
            } else if (SelectedItem.equalsIgnoreCase("- قائمة بالعملاء")) {
                String query = "SELECT * FROM `customer`";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.CustomersList();

            } else if (SelectedItem.equalsIgnoreCase("- قائمة عمليات الصيانة لعميل")) {
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.MO_NBER = '" + Txfiled_MO_Nber.getText() + "' OR r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                System.out.println(q);
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.MO_NBER = '" + Txfiled_MO_Nber.getText() + "' OR r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();
                    printreport print = new printreport();
                    String bb = Txfiled_MO_Nber.getText();
                    String ss = Txfiled_CUS_MNBER.getText();
                    print.ListofcustomersMOs(bb, ss);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("العميل ليست لديه عملية صيانة");
                    alert.showAndWait();
                    return;
                }

            } else if (SelectedItem.equalsIgnoreCase("- قائمة بالموظفين")) {
                String query = "SELECT * FROM `employee`";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.EmployeesList();

            } else if (SelectedItem.equalsIgnoreCase("- قائمة عمليات الصيانة لموظف")) {
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '" + Selct_Name_Employee.getValue() + "'";
                System.out.println(q);
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '" + Selct_Name_Employee.getValue() + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    System.out.println(Selct_Name_Employee.getValue());

                    printreport print = new printreport();
                    String ss = Selct_Name_Employee.getValue();
                    print.ListofaemployeesMOs(ss);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("الموظف ليست لديه عملية صيانة");
                    alert.showAndWait();
                    return;

                }

            } else if (SelectedItem.equalsIgnoreCase("- قائمة بالمزودين")) {
                String query = "SELECT * FROM `supplier`";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SuppliersList();

            } else if (SelectedItem.equalsIgnoreCase("- قطع على وشك النفاذ")) {
                String query = "SELECT * FROM spare_parts WHERE `SP_QUANTITY` < `MINIMUM_QUANTITY_IN_STOCK` AND `SP_QUANTITY` <>0";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SPstobeOUTOS();

            } else if (SelectedItem.equalsIgnoreCase("- قطع الغيار التي نفذت كميتها")) {
                String query = "SELECT * FROM spare_parts WHERE `SP_QUANTITY` = 0";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SPsOUTOS();

            }

        } else if (count_Language == 0) {
            ObservableList<String> names;
            names = List_of_reports.getSelectionModel().getSelectedItems();

            String SelectedItem = (String) List_of_reports.getSelectionModel().getSelectedItem();

            System.out.println("HERE  B:" + SelectedItem);

            LocalDate Date = LocalDate.now();
            if (SelectedItem.equalsIgnoreCase("- previous  maintenance operations")) {

                if (Rad_Last_month.isSelected()) {
                    System.out.println(Rad_Last_month.getText());
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(1);
                    print.PreviousMOEN(bb, ss);

                } else if (Rad_LastThree_months.isSelected()) {
                    System.out.println(Rad_LastThree_months.getText());
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(3);
                    print.PreviousMOEN(bb, ss);

                } else if (Rad_This_Year.isSelected()) {
                    System.out.println(Rad_This_Year.getText());
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.PreviousMOEN(bb, ss);
                } else if (Rad_Last_Year.isSelected()) {
                    System.out.println(Rad_Last_Year.getText());
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.PreviousMOEN(bb, ss);

                } else if (Rad_Choose_Period.isSelected()) {
                    System.out.println(Rad_Choose_Period.getText());

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate AA = Date_StartFrom.getValue();
                    LocalDate MM = Date_Unill.getValue();
                    print.PreviousMOPeriodEN(AA, MM);
                    System.out.println("hhhhhhhhhhhhhhh");
                }
            } else if (SelectedItem.equalsIgnoreCase("- current  maintenance operations")) {
                if (Rad_Last_month.isSelected()) {

                    //String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND '" + LocalDate.now() + "'>='" + Date.minusMonths(3) + "'";
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(1);
                    print.CurrentMOEN(bb, ss);

                } else if (Rad_LastThree_months.isSelected()) {
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(3);
                    print.CurrentMOEN(bb, ss);

                } else if (Rad_This_Year.isSelected()) {
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.CurrentMOEN(bb, ss);

                } else if (Rad_Last_Year.isSelected()) {
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.CurrentMOEN(bb, ss);

                } else if (Rad_Choose_Period.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";

                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    //  List_of_reports.getSelectionModel().getSelectedItems().add("عمليات الصيانة الحالية");
                    //List_of_reports.setItems(s);
                    printreport print = new printreport();
                    LocalDate AA = Date_StartFrom.getValue();
                    LocalDate MM = Date_Unill.getValue();
                    print.CurrentMOPeriodEN(AA, MM);
                }

            } else if (SelectedItem.equalsIgnoreCase("- Finished maintenance operations")) {
                if (Rad_Last_month.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(1);
                    print.FinshedMOEN(bb, ss);

                } else if (Rad_LastThree_months.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(3);
                    print.FinshedMOEN(bb, ss);

                } else if (Rad_This_Year.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.FinshedMOEN(bb, ss);

                } else if (Rad_Last_Year.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate bb = LocalDate.now();
                    LocalDate ss = Date.minusMonths(12);
                    print.FinshedMOEN(bb, ss);

                } else if (Rad_Choose_Period.isSelected()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    LocalDate AA = Date_StartFrom.getValue();
                    LocalDate MM = Date_Unill.getValue();
                    print.FinshedMOPeriodEN(AA, MM);
                }

            } else if (SelectedItem.equalsIgnoreCase("- financial estimate of maintenance")) {
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + Txfiled_MO_Nber.getText() + "' OR r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                System.out.println(q);
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + Txfiled_MO_Nber.getText() + "' OR r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    String bb = Txfiled_MO_Nber.getText();
                    String ss = Txfiled_CUS_MNBER.getText();
                    print.FinancialassessReportEN(bb, ss);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("This maintenance does not have financialassessment");
                    alert.showAndWait();
                    return;

                }
            } else if (SelectedItem.equalsIgnoreCase("- list of customers")) {
                String query = "SELECT * FROM `customer`";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.CustomersListEN();

            } else if (SelectedItem.equalsIgnoreCase("- list of customer maintenance operations")) {
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.MO_NBER = '" + Txfiled_MO_Nber.getText() + "' OR r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                System.out.println(q);
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.MO_NBER = '" + Txfiled_MO_Nber.getText() + "' OR r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();
                    printreport print = new printreport();
                    String bb = Txfiled_MO_Nber.getText();
                    String ss = Txfiled_CUS_MNBER.getText();
                    print.ListofcustomersMOsEN(bb, ss);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("This customer does not have maintenance operation");
                    alert.showAndWait();
                    return;
                }

            } else if (SelectedItem.equalsIgnoreCase("- list of employees")) {
                String query = "SELECT * FROM `employee`";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.EmployeesListEN();

            } else if (SelectedItem.equalsIgnoreCase("- List of maintenance operations for an employee")) {
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '" + Selct_Name_Employee.getValue() + "'";
                System.out.println(q);
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '" + Selct_Name_Employee.getValue() + "'";
                    System.out.println(query);
                    java.sql.Statement statement1 = connection.createStatement();

                    System.out.println(Selct_Name_Employee.getValue());

                    printreport print = new printreport();
                    String ss = Selct_Name_Employee.getValue();
                    print.ListofaemployeesMOsEN(ss);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("This employee does not have maintenance operation");
                    alert.showAndWait();
                    return;

                }

            } else if (SelectedItem.equalsIgnoreCase("- list of Suppliers")) {
                String query = "SELECT * FROM `supplier`";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SuppliersListEN();

            } else if (SelectedItem.equalsIgnoreCase("- Spare Parts about to be out of stock")) {
                String query = "SELECT * FROM spare_parts WHERE `SP_QUANTITY` < `MINIMUM_QUANTITY_IN_STOCK` AND `SP_QUANTITY` <>0";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SPstobeOUTOSEN();

            } else if (SelectedItem.equalsIgnoreCase("- Spare parts out of stock")) {
                String query = "SELECT * FROM spare_parts WHERE `SP_QUANTITY` = 0";
                System.out.println(query);
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SPsOUTOSEN();

            }

        }
    }

    public void loadEmp() {
        String query = "SELECT EMP_NAME FROM employee";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {

                ListOfselectName.add(rs.getString("EMP_NAME"));

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    @FXML
    public void Mangment_MO_tab_selected(Event event) {

        if (count_Language == 0) {
            MainLable.setText("Maintenance Operations Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة عمليات الصيانة");

        }

    }

    @FXML
    public void Mangment_Customer_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Customers Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة العملاء");

        }

    }

    @FXML
    public void Mangment_supliers_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Suppliers Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة المزودين");

        }
    }

    @FXML
    public void Mangment_Staff_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Employees Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة الموظفين");

        }
    }

    @FXML
    public void RequstSpearPart_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Request Spare Parts");

        } else if (count_Language == 1) {

            MainLable.setText("طلب قطع غيار");

        }
    }

    @FXML
    public void Mangment_SpearParts_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Spear Parts Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة قطع الغيار");

        }
    }

    @FXML
    public void Mangment_Reports_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Reports Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة التقارير");

        }
    }

    @FXML
    private void Main_Tab(Event event) {
        MainLable.setText(" ");
    }

    @FXML
    private void Mangment_Tools_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Tools");

        } else if (count_Language == 1) {

            MainLable.setText("الأدوات");

        }

    }
    ObservableList<AddSP> SPSelected2, AllSP2;

    @FXML
    private void M_Btn_RemoveSP_ReqSP(ActionEvent event) throws SQLException {

        AllSP2 = Table_SelectedSP_ReqSP.getItems();
        SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();

        //Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
        //_____________________
        //for (AddSP addSP : Table_AddSP_AddMO.getSelectionModel().getSelectedItems()) {
        for (int i = 0; i < SPSelected2.size(); i++) {
            //for(int i =1;i<=1; i++){
            //list.clear();
            // list.add(new Controller_AddMO.AddSP(SPSelected2.get(i).getSP2_Number(), SPSelected2.get(i).getSP2_Name(), SPSelected2.get(i).getSP2_Description(), 
            //SPSelected2.get(i).getSP_Price2()));
            // Table_AddSP_AddMO.setItems(list);
            String sqlDeletSP = "DELETE FROM `attach` " + " WHERE SP_NBER= " + SPSelected2.get(i).getSP_Number() + " AND REQUEST_NBER=" + Txfiled_REQnum_ReqSP.getText();

            System.out.println(sqlDeletSP);
            //SPSelected2.get(i).ge

            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlDeletSP);

        }
        //}
        SPSelected2.forEach(ListOFSelectedSP::remove);
        Table_SelectedSP_ReqSP.getItems().setAll(ListOFSelectedSP);
        loadSpecifecSP();

        //calculate();
        //DELETE FROM `require` WHERE `require`.`MO_NBER` = 7 AND `require`.`SP_NBER` = 3;
        // Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
    }

    @FXML
    private void M_Btn_AddSP_ReqSP(ActionEvent event) throws SQLException {
        ObservableList<AddSP> SPSelected, AllSP;
        AllSP = Table_AddSP_ReqSP.getItems();
        SPSelected = Table_AddSP_ReqSP.getSelectionModel().getSelectedItems();
        // ObservableList<Controller_AddMO.SelectedSP> SPSelected3, AllSP3;
        //AllSP3 = Table_SelectedSP_AddMO.getItems();
        ///SPSelected3 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        //_____________________
        //for (AddSP addSP : Table_AddSP_AddMO.getSelectionModel().getSelectedItems()) {
        for (int i = 0; i < SPSelected.size(); i++) {
            System.out.println(SPSelected.size());
            //for(int i =1;i<=1; i++){

            ListOFSelectedSP.add(new AddSP(SPSelected.get(i).getSP_Number(), SPSelected.get(i).getSP_Name(), 1));
            //AllSP3.add()

            String sql1 = "INSERT INTO `attach` VALUES(" + SPSelected.get(0).getSP_Number() + ",'" + Txfiled_REQnum_ReqSP.getText() + "','"
                    + 1 + "')";
            java.sql.Statement statement1 = connection.createStatement();
            System.out.println(sql1);
            statement1.executeUpdate(sql1);
        }
        //}
        Table_SelectedSP_ReqSP.getItems().setAll(ListOFSelectedSP);

        // Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
        //SPSelected.forEach(AllSP::remove);
        //System.out.println(SPSelected);
        //list.add(new Controller_AddMO.AddSP(aa, mid, mobile));
        //list2.add(SPSelected.get(1));
        //System.out.println(SPSelected.get(0).SP_Description);
        //Table_AddSP_AddMO.getItems().setAll(list);
        //Table_SelectedSP_AddMO.getItems().setAll(list2);
        SPSelected.forEach(AllSP::remove);
    }

    @FXML
    private void M_Btn_Print_ReqSP(ActionEvent event) {
    }

    @FXML
    private void M_Btn_Cancle_ReqSP(ActionEvent event) {
        clear();
    }

    @FXML
    private void M_Btn_Delete_ReqSP(ActionEvent event) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            String deletSP = "DELETE FROM  `attach` " + " WHERE REQUEST_NBER= " + Txfiled_REQnum_ReqSP.getText();
            String sql1 = "DELETE FROM  `requested_spare_parts` " + " WHERE REQUEST_NBER= " + Txfiled_REQnum_ReqSP.getText();
            System.out.println(deletSP);
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(deletSP);
            statement1.executeUpdate(sql1);
            clear();
        } else {

        }
    }

    @FXML
    private void M_Btn_Save_ReqSP(ActionEvent event) throws SQLException {

        if (Txfiled_REQnum_ReqSP.getText().isEmpty() || Date_REQdate_ReqSP.getValue() == null || Selct_Supplier_ReqSP.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pleas enter the value");
            alert.showAndWait();
            return;

        }
        Statement st2 = connection.createStatement();
        st2.executeQuery("SELECT * FROM `supplier`");
        ResultSet rs2 = st2.getResultSet();
        //SELECT * FROM `employee` 
        int IndexOFTech = 0;
        for (int i = 0; i < ListOfSuppliers.size(); i++) {

            while (rs2.next()) {

                //ListOfTechichan.add(rs2.getString("EMP_NAME"));
                //البحث ب رقم العميل من الداتابيس
                if (Selct_Supplier_ReqSP.getValue().equals(rs2.getString("SUP_NAME"))) {

                    IndexOFTech = Integer.parseInt(rs2.getString("SUPPLIER_NBER"));

                }
            }
        }//IndexOFTech++;

        System.out.println("INDEX== " + IndexOFTech);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            if (count == 1) {
                System.out.println("Equal  insert");

                String sql1 = "INSERT INTO `requested_spare_parts` VALUES(" + monumber + "," + "'" + Date_REQdate_ReqSP.getValue() + "'" + "," + "'" + IndexOFTech + "')";
                System.out.println(sql1);
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);

            } else if (count == 2) {
                System.out.println("Equal  update");
                //System.out.println(Selct_MoStatus_AddMO.getValue());
                String sql1 = "UPDATE  `requested_spare_parts` SET REQUEST_DATE='" + Date_REQdate_ReqSP.getValue() + "',SUPPLIER_NBER='" + IndexOFTech
                        + "' WHERE REQUEST_NBER= '" + Txfiled_REQnum_ReqSP.getText() + "'";
                System.out.println(sql1);
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);
                //System.out.println(Selct_MoStatus_AddMO.getValue().equalsIgnoreCase(sql1));

            }
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("I have a great message for you!");

            alert2.showAndWait();
            clear();
            count = 2;

        } else {

        }
    }

    public void clear() {

        Txfiled_REQnum_ReqSP.setDisable(false);
        Txfiled_REQnum_ReqSP.clear();
        Selct_Supplier_ReqSP.getSelectionModel().clearSelection();
        Date_REQdate_ReqSP.setValue(null);
        Table_SelectedSP_ReqSP.getItems().clear();
        Table_AddSP_ReqSP.getItems().clear();
        ListOFSelectedSP.clear();
        ListOFSP.clear();
        Txfiled_QuanitiySP_ReqSP.setText("الكمية");
        Txfiled_SearchSP_ReqSP.setText("بحث");

    }

    public void loadSuppliers() {
        String query = "SELECT SUP_NAME FROM supplier";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {

                ListOfSuppliers.add(rs.getString("SUP_NAME"));

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        Selct_Supplier_ReqSP.setItems(ListOfSuppliers);

    }

    public int monumber = 0;

    @FXML
    private void M_Btn_Search_ReqSP(ActionEvent event) throws SQLException {
        if (Txfiled_REQnum_ReqSP.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pleas enter the value");
            alert.showAndWait();
            return;

        }
        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `requested_spare_parts` r JOIN `supplier` s ON r.SUPPLIER_NBER  = s.SUPPLIER_NBER  WHERE REQUEST_NBER = " + Txfiled_REQnum_ReqSP.getText());
//REQUEST_NBER
        ResultSet rs = st.getResultSet();
        //st = connection.prepareCall(sql);

        if (rs.first()) {

            System.out.println(Txfiled_REQnum_ReqSP.getText());

            System.out.println("THIS MO NUMBER IN DB== " + rs.getString("REQUEST_NBER"));
            System.out.println("THIS MO NUMBER IN FILED== " + Txfiled_REQnum_ReqSP.getText());

            if (rs.getString("REQUEST_NBER").equals(Txfiled_REQnum_ReqSP.getText())) {

                count = 2;

                Txfiled_REQnum_ReqSP.setDisable(true);

                LocalDate REQUEST_DATE = LocalDate.parse(rs.getString("REQUEST_DATE"));

                Date_REQdate_ReqSP.setValue(REQUEST_DATE);

                System.out.println("PPPPPPPPPPPPPP " + rs.getString("SUP_NAME"));
                Selct_Supplier_ReqSP.getSelectionModel().select(rs.getString("SUP_NAME"));

                Btn_Delete_ReqSP.setDisable(false);
                Btn_Save_ReqSP.setDisable(false);
                Btn_Print_ReqSP.setDisable(false);
                Btn_Delete_ReqSP.setDisable(false);

                Btn_Cancle_ReqSP.setDisable(false);
                Btn_AddSP_ReqSP.setDisable(false);
                Btn_RemoveSP_ReqSP.setDisable(false);
                //loadlist.clear();
                loadSpSelected();
                loadSpecifecSP();

                //calculate();
            }

            //java.sql.Statement statement1 = connection.createStatement();
            //statement1.executeQuery(sql);
        } else {

            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM `requested_spare_parts` ORDER BY `REQUEST_NBER` DESC LIMIT 1");
            ResultSet rs2 = st2.getResultSet();
            //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
            if (rs2.first()) {
                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                //System.out.println();
                count = 1;
                monumber = Integer.parseInt(rs2.getString("REQUEST_NBER"));
                monumber++;
                System.out.println(monumber);
                Txfiled_REQnum_ReqSP.setText(String.valueOf(monumber));
                Txfiled_REQnum_ReqSP.setDisable(true);
                //Txfiled_MOnum_AddMO.clear();
                Btn_Delete_ReqSP.setDisable(true);
                Btn_Cancle_ReqSP.setDisable(false);
                Btn_Save_ReqSP.setDisable(false);
                Btn_Print_ReqSP.setDisable(false);
            }
        }
    }//}

    @FXML
    private void M_MousClicked_TabelSelecSP_ReqSP(MouseEvent event) {
        SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();

        Txfiled_QuanitiySP_ReqSP.setText(String.valueOf(SPSelected2.get(0).getSP_Quantity()));

    }

    @FXML
    private void M_Txfiled_QuanitiySP_ReqSP(ActionEvent event) throws SQLException {
        AllSP2 = Table_SelectedSP_ReqSP.getItems();
        SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();
        //  SPSelected2.get(0).SP_SNProperty("");
        //Txfiled_SpSerialN_AddMO.getText();
        int perSp_Quant = Integer.parseInt(Txfiled_QuanitiySP_ReqSP.getText());

        for (int i = 0; i < SPSelected2.size(); i++) {
            ListOFSelectedSP.add(new AddSP(SPSelected2.get(i).getSP_Number(), SPSelected2.get(i).getSP_Name(), perSp_Quant));                // }

            String sqlupdateAddSP = "UPDATE `attach` SET `Req_QUANTITY` = '" + Txfiled_QuanitiySP_ReqSP.getText() + "' WHERE REQUEST_NBER= " + Txfiled_REQnum_ReqSP.getText()
                    + " AND SP_NBER=" + SPSelected2.get(i).getSP_Number();
            System.out.println(sqlupdateAddSP);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlupdateAddSP);

            SPSelected2.forEach(ListOFSelectedSP::remove);
            Table_SelectedSP_ReqSP.getItems().setAll(ListOFSelectedSP);
            //calculate();
            Txfiled_QuanitiySP_ReqSP.setText("الكمية");
        }
    }
    String id2 = "";

    @FXML
    private void M_Txfiled_SearchSP_ReqSP(KeyEvent event) throws SQLException {
        Choose = 2;
        System.out.println(event.getEventType().toString());
        System.out.println(event.getText());
        ListOFSP.clear();
        id2 += event.getText();
        if (event.getText().equals("")) {
            id2 = id2.substring(0, id2.length() - 1);

        }

        String id1 = Txfiled_SearchSP_ReqSP.getText();
        System.out.println("__________  " + id2);
        System.out.println("__________  " + id1);
        System.out.println("__________  " + Txfiled_SearchSP_ReqSP.getText());

        if (id2.isEmpty()) {
            loadSpecifecSP();

        } else {
            String sql1 = "SELECT * FROM spare_parts WHERE SP_NAME = '" + id2 + "'";
            String trysql = "SELECT * FROM spare_parts WHERE SP_NAME LIKE '" + id2 + "%';";
            System.out.println(trysql);
            Search(trysql, Choose);

        }
    }

    @FXML
    private void M_Txfiled_Search_MangeCurrentMO(KeyEvent event) throws SQLException {
        Choose = 3;
        System.out.println(event.getEventType().toString());
        System.out.println(event.getText());
        System.out.println("GEEEEEEET TEXT   " + Txfiled_Search_MangeCurrentMO.getText());
        System.out.println("yyyyyyyyyyy TEXT   " + Txfiled_Search_MangeCurrentMO.getSelectedText());

        String sql1 = "SELECT * FROM `maintenance_operation` WHERE `MO_NBER` = '" + Txfiled_Search_MangeCurrentMO.getText() + "'";
        String trysql = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID "
                + "WHERE `MO_NBER` LIKE '" + Txfiled_Search_MangeCurrentMO.getText() + "%' AND STATE IN ('approved' ,'under maintenance','تم الموافقة' ,'تحت الصيانة'); ";
        // + "OR `CUS_NAME` LIKE '" + id3 + "%' AND STATE ='approved' OR STATE ='under maintenance';";
        System.out.println(trysql);
        Search(trysql, Choose);

    }

    @FXML
    private void Txfiled_Search_MangeFinshedMO(KeyEvent event) throws SQLException {
        Choose = 4;
        System.out.println(event.getEventType().toString());
        System.out.println(event.getText());

        String sql1 = "SELECT * FROM `maintenance_operation` WHERE `MO_NBER` = '" + Txfiled_Search_MangeFinshedMO.getText() + "'";
        String trysql = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID "
                + "WHERE `MO_NBER` LIKE '" + Txfiled_Search_MangeFinshedMO.getText() + "%' AND STATE IN ('repaired','تم الاصلاح'); ";
        //  + "OR `CUS_NAME` LIKE '" + id3 + "%' AND STATE ='repaired' ;";
        System.out.println(trysql);
        Search(trysql, Choose);
    }

    @FXML
    private void Txfiled_Search_MangePreviousMO(KeyEvent event) throws SQLException {
        Choose = 5;
        System.out.println(event.getEventType().toString());
        System.out.println(event.getText());

        String sql1 = "SELECT * FROM `maintenance_operation` WHERE `MO_NBER` = '" + Txfiled_Search_MangePreviousMO.getText() + "'";
        String trysql = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID "
                + "WHERE `MO_NBER` LIKE '" + Txfiled_Search_MangePreviousMO.getText() + "%' AND STATE IN ('paid' ,'disapproved','دفعت' ,'مرفوضة'); ";
        // + "OR `CUS_NAME` LIKE '" + id3 + "%' AND STATE ='paid' OR STATE ='disapproved';";
        System.out.println(trysql);
        Search(trysql, Choose);
    }

    @FXML
    private void Txfiled_Search_MangePendingMO(KeyEvent event) throws SQLException {
        Choose = 6;
        System.out.println(event.getEventType().toString());
        System.out.println(event.getText());

        String sql1 = "SELECT * FROM `maintenance_operation` WHERE `MO_NBER` = '" + Txfiled_Search_MangePendingMO.getText() + "'";
        String trysql = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID "
                + "WHERE `MO_NBER` LIKE '" + Txfiled_Search_MangePendingMO.getText() + "%' AND STATE IN( 'other defects has been detected' ,'cannot be done' ,'created'"
                + ",'تم الكشف عن عيوب أخرى' ,'لا يمكن القيام بعملية الصيانة' ,'تم الإنشاء'); ";
        //+ "OR `CUS_NAME` LIKE '" + id3 + "%' AND STATE ='other defects has been detected' OR STATE ='cannot be done' OR STATE ='created';";
        System.out.println(trysql);
        Search(trysql, Choose);
    }



 

    void CheckListReportVisabil_EN(String ListName) {
        if (ListName.equalsIgnoreCase("- current  maintenance operations") || ListName.equalsIgnoreCase("- Finished maintenance operations")
                || ListName.equalsIgnoreCase("- previous  maintenance operations")) {
            Hbox_Date_Reports.setVisible(true);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);

        } else if (ListName.equalsIgnoreCase("- financial estimate of maintenance")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(true);

        } else if (ListName.equalsIgnoreCase("- list of customers") || ListName.equalsIgnoreCase("- list of employees") || ListName.equalsIgnoreCase("- list of Suppliers")
                || ListName.equalsIgnoreCase("- Spare Parts about to be out of stock") || ListName.equalsIgnoreCase("- Spare parts out of stock")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);

        } else if (ListName.equalsIgnoreCase("- list of customer maintenance operations")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(true);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);
        } else if (ListName.equalsIgnoreCase("- List of maintenance operations for an employee")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(true);
            Hbox_MO_Reports.setVisible(false);
        }

    }

    void CheckListReportVisabil_AR(String ListName) {
        if (ListName.equalsIgnoreCase("- عمليات الصيانة الحالية") || ListName.equalsIgnoreCase("- عمليات الصيانة المنتهية")
                || ListName.equalsIgnoreCase("- عمليات الصيانة السابقة")) {
            Hbox_Date_Reports.setVisible(true);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);

        } else if (ListName.equalsIgnoreCase("- تقدير مالي عن عملية صيانة")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(true);

        } else if (ListName.equalsIgnoreCase("- قائمة بالعملاء") || ListName.equalsIgnoreCase("- قائمة بالموظفين") || ListName.equalsIgnoreCase("- قائمة بالمزودين")
                || ListName.equalsIgnoreCase("- قطع على وشك النفاذ") || ListName.equalsIgnoreCase("- قطع الغيار التي نفذت كميتها")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);

        } else if (ListName.equalsIgnoreCase("- قائمة عمليات الصيانة لعميل")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(true);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);
        } else if (ListName.equalsIgnoreCase("- قائمة عمليات الصيانة لموظف")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(true);
            Hbox_MO_Reports.setVisible(false);
        }

    }

    @FXML
    private void M_Btn_SaveDB_Tools(ActionEvent event) {
        System.out.println("Btn_SaveDB_Tools");
        // int a =3;
        //Path_aa path_aa =new Path_aa();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Window stage = null;
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory == null) {
            //No Directory selected
        } else {
            System.out.println(selectedDirectory.getAbsolutePath());
            
            String executeCmd = "C:\\xampp\\mysql\\bin\\mysqldump.exe -u root  mo_db -r ";
            String Path =selectedDirectory.getAbsolutePath();
             Path = Path + "\\SaveDB_" + LocalDate.now() + ".sql";
             System.out.println(executeCmd+Path);
            Process runtimeProcess;
            try {

                runtimeProcess = Runtime.getRuntime().exec(executeCmd+Path);

                int processComplete = runtimeProcess.waitFor();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
         
           
                if (processComplete == 0) {
                       alert.setContentText("Backup created successfully");
                    System.out.println("Backup created successfully");
                    //return true;
                } else {
                       alert.setContentText("Could not create the backup");
                    System.out.println("Could not create the backup");
                }
                 alert.showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    @FXML
    private void M_Btn_ArchiveDB_Tools(ActionEvent event) throws SQLException {
 String query = "SELECT * FROM `maintenance_operation` Where `STARTING_DATE` < \"2019-01-30\" ";
        ResultSet rs = connectionClass.execQuery(query);

  
            while (rs.next()) {
                System.out.println("HEREEEEE");
                //System.out.println("rs.getString(\"MO_Number\")  ="+rs.getString("MO_Number"));

                
         String query_requier = "SELECT * FROM `require` Where `MO_NBER` ="+ rs.getString("MO_NBER");
        ResultSet rs2 = connectionClass.execQuery(query_requier);
                
         String insert_to_MO = "INSERT INTO `maintenance_operation_backup` VALUES(" +  rs.getString("MO_NBER") + "," + "'" + rs.getString("STATE") + "'" + "," + "'" + rs.getString("MO_COST")
                        + "'" + "," + "'" + rs.getString("SP_COST") + "'" + "," + "'" + rs.getString("STARTING_DATE")  + "'" + "," + "'" + rs.getString("ENDING_DATE")  + "'" + "," + "'"
                        + rs.getString("WARRANTY")  + "'" + "," + "'" + rs.getString("PROBLEM_DESC") + "'" + "," + "'" + rs.getString("DEVICE_SN")  + "'" + "," + "'" + rs.getString("DEVICE_DESC") 
                        + "'" + "," + "'" + rs.getString("EMPLOYEE_ID")  + "'" + "," + "'" + rs.getString("CUS_MOBILE_NBER")  + "','"+rs.getString("INVOICE_DATE") + "',"+rs.getString("INVOICE_NBER")  + ")";
                System.out.println(insert_to_MO);
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(insert_to_MO);
                while (rs2.next()) {
                
                String insert_to_requir = "INSERT INTO `require_backup` VALUES(" + rs2.getString("MO_NBER") + ",'" + rs2.getString("SP_NBER")  + "','"
                        +  rs2.getString("Seq_Nber")  + "','" +rs2.getString("SERIAL_NUMBER") +"'" + ",'" + rs2.getString("Effective_Price")  + "')";
              System.out.println(insert_to_requir);
                statement1.executeUpdate(insert_to_requir);
                }
                  String deletSP = "DELETE FROM  `require` " + " WHERE MO_NBER= " +  rs.getString("MO_NBER");
            String sql1 = "DELETE FROM  `maintenance_operation` " + " WHERE MO_NBER= " +  rs.getString("MO_NBER");
            System.out.println(deletSP);
            System.out.println(sql1);
            statement1.executeUpdate(deletSP);
            statement1.executeUpdate(sql1);
                
    }
    }

    @FXML
    private void M_KeyReleased_TabelSelecSP_ReqSP(KeyEvent event) {
             SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();

        Txfiled_QuanitiySP_ReqSP.setText(String.valueOf(SPSelected2.get(0).getSP_Quantity()));
    }
            String GetSelectedItem;

    @FXML
    private void M_ReportList_MouseClicked(MouseEvent event) {

          GetSelectedItem = (String) List_of_reports.getSelectionModel().getSelectedItem();
        System.out.println("SS===MouseEvent" + GetSelectedItem);
        if (count_Language == 0) {
            CheckListReportVisabil_EN(GetSelectedItem);
        } else {

            CheckListReportVisabil_AR(GetSelectedItem);
        }
    }

    @FXML
    private void M_ReportList_KeyReleased(KeyEvent event) {
                //event.getCode()
        if (event.getCode().isNavigationKey() == true) {
            System.out.println("upppp");
            GetSelectedItem = (String) List_of_reports.getSelectionModel().getSelectedItem();
            System.out.println("SS===SwipeEvent" + GetSelectedItem);
            if (count_Language == 0) {
                CheckListReportVisabil_EN(GetSelectedItem);
            } else {

                CheckListReportVisabil_AR(GetSelectedItem);
            }

        }
    }
   

    public static class AddSP {

        private final SimpleIntegerProperty SP_Number;
        private final SimpleStringProperty SP_Name;
        private final SimpleIntegerProperty SP_Quantity;

        AddSP(int SP_Number, String SP_Name, int SP_Quantity) {
            this.SP_Number = new SimpleIntegerProperty(SP_Number);
            this.SP_Name = new SimpleStringProperty(SP_Name);
            this.SP_Quantity = new SimpleIntegerProperty(SP_Quantity);

        }

        public Integer getSP_Number() {
            return SP_Number.get();
        }

        public String getSP_Name() {
            return SP_Name.get();
        }

        public Integer getSP_Quantity() {
            return SP_Quantity.get();
        }

    }

    public static class MO {

        private final SimpleIntegerProperty MO_Number;
        private final SimpleStringProperty Cus_Name;
        private final SimpleIntegerProperty Cus_Mobile;
        private final SimpleStringProperty MO_technician;
        private final SimpleStringProperty MO_EndDate;
        private final SimpleDoubleProperty MO_TotalCost;
        private final SimpleStringProperty MO_Status;

        MO(Integer MO_Number, String Cus_Name, Integer Cus_Mobile, String MO_technician, String MO_EndDate, double MO_TotalCost, String MO_Status) {
            this.MO_Number = new SimpleIntegerProperty(MO_Number);
            this.Cus_Name = new SimpleStringProperty(Cus_Name);
            this.Cus_Mobile = new SimpleIntegerProperty(Cus_Mobile);
            this.MO_technician = new SimpleStringProperty(MO_technician);
            this.MO_EndDate = new SimpleStringProperty(MO_EndDate);
            this.MO_TotalCost = new SimpleDoubleProperty(MO_TotalCost);
            this.MO_Status = new SimpleStringProperty(MO_Status);

        }

        public Integer getMO_Number() {
            return MO_Number.get();
        }

        public String getCus_Name() {
            return Cus_Name.get();
        }

        public Integer getCus_Mobile() {
            return Cus_Mobile.get();
        }

        public String getMO_technician() {
            return MO_technician.get();
        }

        public String getMO_EndDate() {
            return MO_EndDate.get();
        }

        public Double getMO_TotalCost() {
            return MO_TotalCost.get();
        }

        public String getMO_Status() {
            return MO_Status.get();
        }
    }

    @FXML
    private void M_Btn_Edit_MangeCurrentMO(ActionEvent event) throws SQLException {

        openEdit(Table_CurrentMO_MngMO);
    }
    int Choose = 0;

    public void Search(String Search, int Choose) throws SQLException {
        if (Choose == 2) {
            ResultSet rs = connectionClass.execQuery(Search);
            try {
                while (rs.next()) {
                    String mname = rs.getString("SP_NBER");
                    String mid = rs.getString("SP_NAME");
                    String mobile = rs.getString("DESCRIPTION");
                    String price = rs.getString("PRICE");

                    int SP_num = Integer.parseInt(mname);
                    int SP_Quant = Integer.parseInt(rs.getString("SP_QUANTITY"));

                    ListOFSP.add(new AddSP(SP_num, rs.getString("SP_Name"), SP_Quant));
                }

                for (int i = 0; i < ListOFSP.size(); i++) {
                    for (int j = 0; j < ListOFSelectedSP.size(); j++) {
                        if (ListOFSP.get(i).getSP_Number().equals(ListOFSelectedSP.get(j).getSP_Number())) {
                            System.out.println(ListOFSP.get(i).getSP_Number() + "-----------" + ListOFSelectedSP.get(j).getSP_Number());
                            System.out.println("i==" + i + "j==" + j);
                            ListOFSP.remove(i);
                            System.out.println("Size==" + ListOFSP.size());
                            System.out.println("NOOOOT NULL OOOOOOOOOOOOOOOOOO");

                        }

                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            Table_AddSP_ReqSP.getItems().setAll(ListOFSP);

        } else if (Choose == 1) {

            ResultSet rs = connectionClass.execQuery(Search);
            try {
                while (rs.next()) {

                    //Txfiled_CusName_AddMO.setText(rs.getString("CUS_NAME"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }

        } else if (Choose == 3) {

            ResultSet rs = connectionClass.execQuery(Search);
            CurrnetList.clear();
            try {
                while (rs.next()) {

                    String MONber = rs.getString("MO_NBER");
                    String mobile = rs.getString("CUS_MOBILE_NBER");
                    String priceSP = rs.getString("SP_COST");
                    String priceMO = rs.getString("MO_COST");
                    int MO_num = Integer.parseInt(MONber);
                    int CusMobile = Integer.parseInt(mobile);
                    double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                    CurrnetList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));

                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            Table_CurrentMO_MngMO.getItems().setAll(CurrnetList);

        } else if (Choose == 4) {

            ResultSet rs = connectionClass.execQuery(Search);
            FinshedList.clear();
            try {
                while (rs.next()) {

                    String MONber = rs.getString("MO_NBER");
                    String mobile = rs.getString("CUS_MOBILE_NBER");
                    String priceSP = rs.getString("SP_COST");
                    String priceMO = rs.getString("MO_COST");
                    int MO_num = Integer.parseInt(MONber);
                    int CusMobile = Integer.parseInt(mobile);
                    double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                    FinshedList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));

                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            Table_FinshedMO_MngMO.getItems().setAll(FinshedList);

        } else if (Choose == 5) {

            ResultSet rs = connectionClass.execQuery(Search);
            PriveousList.clear();
            try {
                while (rs.next()) {

                    String MONber = rs.getString("MO_NBER");
                    String mobile = rs.getString("CUS_MOBILE_NBER");
                    String priceSP = rs.getString("SP_COST");
                    String priceMO = rs.getString("MO_COST");
                    int MO_num = Integer.parseInt(MONber);
                    int CusMobile = Integer.parseInt(mobile);
                    double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                    PriveousList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));

                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            Table_PreviousMO_MngMO.getItems().setAll(PriveousList);

        } else if (Choose == 6) {

            ResultSet rs = connectionClass.execQuery(Search);
            PendingList.clear();
            try {
                while (rs.next()) {

                    String MONber = rs.getString("MO_NBER");
                    String mobile = rs.getString("CUS_MOBILE_NBER");
                    String priceSP = rs.getString("SP_COST");
                    String priceMO = rs.getString("MO_COST");
                    int MO_num = Integer.parseInt(MONber);
                    int CusMobile = Integer.parseInt(mobile);
                    double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                    PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));

                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            Table_pendingMO_MngMO.getItems().setAll(PendingList);

        }

    }

    public int Em_Id;

    @FXML
    private void M_Btn_ChangePassword_Tools(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();
        //Controller controller = loader.getController();
        if (count_Language == 0) {
            loader.setLocation(getClass().getResource("/sample/ChangePassword_EN.fxml"));
            try {
                loader.load();

            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            Controller_ChangePassword changePassword = loader.getController();
            changePassword.Set_count_Language(0);

            //controller.count_Language=0;
        } else if (count_Language == 1) {
            loader.setLocation(getClass().getResource("/sample/ChangePassword_AR.fxml"));

            try {

                loader.load();
                Controller_ChangePassword changePassword = loader.getController();
                changePassword.Set_count_Language(1);

            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //loadWindow("/sample/ChangePassword.fxml" ,"" );
        Controller_ChangePassword changePassword = loader.getController();
        changePassword.Em_Id = Em_Id;

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.showAndWait();

    }

    public void SetUserinformation(String Name, String Job) {

        if (Job.equals("فني") || Job.equals("Technician")) {
            if (count_Language == 0) {

                Label_UserJob.setText("Technician");
                Label_UserID_Name.setText(Em_Id + "- " + Name);
            } else {
                Label_UserJob.setText("فني");
                Label_UserID_Name.setText(Name + " -" + Em_Id);
            }
        } else if (Job.equals("اداري") || Job.equals("Administrator")) {
            if (count_Language == 0) {

                Label_UserJob.setText("Administrator");
                Label_UserID_Name.setText(Em_Id + "- " + Name);
            } else {
                Label_UserJob.setText("اداري");
                Label_UserID_Name.setText(Name + " -" + Em_Id);

            }
        } else if (Job.equals("استقبال") || Job.equals("ReceptionDesk")) {
            if (count_Language == 0) {

                Label_UserJob.setText("ReceptionDesk");
                Label_UserID_Name.setText(Em_Id + "- " + Name);
            } else {
                Label_UserJob.setText("استقبال");
                Label_UserID_Name.setText(Name + " -" + Em_Id);

            }
        }

    }
}

/*
package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Tab Mangment_MO_Tab;
    public Label MainLable;

    public TreeTableView TableViewReports;
    public TreeTableColumn Table_ColomnViewReports;
   public Label RepLable;
    public Tab Main_Tab;
    public ImageView icMaonMove;
    public AnchorPane kk;
    public JFXButton prbutton;
    public ListView List_of_reports;
    public JFXButton ShowMoWindowbuttn;



    @FXML
    private Label MainLable1;
    @FXML
    private ToggleGroup ReportsDate;


    @FXML
    public void Mangment_MO_tab_selected(Event event) {

      //  MainLable.setText("ادارة عمليات الصيانة");

    }

    @FXML
    public void Mangment_Customer_tab_selected(Event event) {
//        MainLable.setText("ادارة العملاء");

    }

    @FXML
    public void Mangment_supliers_tab_selected(Event event) {
  //      MainLable.setText("ادارة المزودين");
    }

    @FXML
    public void Mangment_Staff_tab_selected(Event event) {
    //    MainLable.setText("ادارة الموظفين");
    }

    @FXML
    public void RequstSpearPart_tab_selected(Event event) {
    //    MainLable.setText("طلب قطع غيار");
    }

    @FXML
    public void Mangment_SpearParts_tab_selected(Event event) {
        //MainLable.setText("ادارة قطع الغيار");
    }

    @FXML
    public void Mangment_Reports_tab_selected(Event event) {
        //MainLable.setText("ادارة التقارير");
    }



    @FXML
    public void Main_Tab(Event event) {
       
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listv.getItems().add("- عمليات الصيانة الحالية");
        listv.getItems().add("- عمليات الصيانة المنتهية");
        listv.getItems().add("- عمليات الصيانة السابقة");
        listv.getItems().add("- تقدير مالي عن عملية صيانة");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قائمة بالعملاء");
        listv.getItems().add("- قائمة عمليات الصيانة لعميل");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قائمة بالموظفين");
        listv.getItems().add("- قائمة عمليات الصيانة لموظف");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قائمة بالمزودين");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قطع على وشك النفاذ");
        listv.getItems().add("- قطع الغيار التي نفذت كميتها");


       


    }

    @FXML
    public void prbuttonReports(ActionEvent actionEvent) {
        //ReportTable.getItems().setAll(studentsModels1);
        //ReportTable.setItems(studentsModels1);
    }

    @FXML
    public void ShowMoWindow(ActionEvent actionEvent) {
        loadWindow("/sample/AddMoNeww.fxml" ,"" );
    }


    void loadWindow(String loc , String title){
        try {

            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

    @FXML
    private void Btn_ChangeMN_Customer(ActionEvent event) {
    }

    @FXML
    private void Btn_Cancle_Customer(ActionEvent event) {
    }

    @FXML
    private void Btn_Delete_Customer(ActionEvent event) {
    }

    @FXML
    private void Btn_Save_Customer(ActionEvent event) {
    }

    @FXML
    private void Btn_Search_Customer(ActionEvent event) {
    }

    @FXML
    private void Btn_Edit_MangeCurrentMO(ActionEvent event) {
    }

    @FXML
    private void Txfiled_Search_MangeCurrentMO(ActionEvent event) {
    }

    @FXML
    private void Btn_Edit_MangeFinshedMO(ActionEvent event) {
    }

    @FXML
    private void Txfiled_Search_MangeFinshedMO(ActionEvent event) {
    }

    @FXML
    private void Btn_Edit_MangePreviousMO(ActionEvent event) {
    }

    @FXML
    private void Txfiled_Search_MangePreviousMO(ActionEvent event) {
    }

    @FXML
    private void Btn_Edit_MangePendingMO(ActionEvent event) {
    }

    @FXML
    private void Txfiled_Search_MangePendingMO(ActionEvent event) {
    }


    class StudentsModel {

//  private SimpleStringProperty Name;
  private  SimpleStringProperty Name;



        public StudentsModel( String reports) {
   this.Name = new SimpleStringProperty(reports);

  }

  public String getName() {
   return Name.get();
  }

  public void setName(String reportTableCol) {
   this.Name = new SimpleStringProperty(reportTableCol);
  }

 }
}
 */
