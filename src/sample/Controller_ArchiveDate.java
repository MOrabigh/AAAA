package sample;

import Connectvy.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller_ArchiveDate {

    ConnectionClass connectionClass = new ConnectionClass();
    // we call conneClass  that we make it up
    Connection connection = connectionClass.getConnection();

    @FXML
    private JFXDatePicker Date_Archive;
    @FXML
    private JFXButton Btn_Archive_ArchiceDate;
    private int count_Language;
    @FXML
    private AnchorPane Archive_Window;

    public void Set_count_Language(int c) {
        count_Language = c;

    }

    @FXML
    private void M_Btn_Archive_ArchiceDate(ActionEvent event) throws SQLException {

        if (Date_Archive.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);

            if (count_Language == 0) {
                alert.setContentText("Please enter the value");
            } else {
                alert.setContentText("الرجاء إدخال القيمة");

            }
            alert.showAndWait();

        } else {

            String query = "SELECT * FROM `maintenance_operation` Where `STARTING_DATE` < \"" + Date_Archive.getValue() + "\" ";
            ResultSet rs = connectionClass.execQuery(query);
            if (rs.first()) {
                while (rs.next()) {
                    System.out.println("HEREEEEE");
                    //System.out.println("rs.getString(\"MO_Number\")  ="+rs.getString("MO_Number"));

                    String query_requier = "SELECT * FROM `require` Where `MO_NBER` =" + rs.getString("MO_NBER");
                    ResultSet rs2 = connectionClass.execQuery(query_requier);

                    String insert_to_MO = "INSERT INTO `maintenance_operation_backup` VALUES(" + rs.getString("MO_NBER") + "," + "'" + rs.getString("STATE") + "'" + "," + "'" + rs.getString("MO_COST")
                            + "'" + "," + "'" + rs.getString("SP_COST") + "'" + "," + "'" + rs.getString("STARTING_DATE") + "'" + "," + "'" + rs.getString("ENDING_DATE") + "'" + "," + "'"
                            + rs.getString("WARRANTY") + "'" + "," + "'" + rs.getString("PROBLEM_DESC") + "'" + "," + "'" + rs.getString("DEVICE_SN") + "'" + "," + "'" + rs.getString("DEVICE_DESC")
                            + "'" + "," + "'" + rs.getString("EMPLOYEE_ID") + "'" + "," + "'" + rs.getString("CUS_MOBILE_NBER") + "','" + rs.getString("INVOICE_DATE") + "'," + rs.getString("INVOICE_NBER") + ")";
                    System.out.println(insert_to_MO);
                    java.sql.Statement statement1 = connection.createStatement();
                    statement1.executeUpdate(insert_to_MO);
                    while (rs2.next()) {

                        String insert_to_requir = "INSERT INTO `require_backup` VALUES(" + rs2.getString("MO_NBER") + ",'" + rs2.getString("SP_NBER") + "','"
                                + rs2.getString("Seq_Nber") + "','" + rs2.getString("SERIAL_NUMBER") + "'" + ",'" + rs2.getString("Effective_Price") + "')";
                        System.out.println(insert_to_requir);
                        statement1.executeUpdate(insert_to_requir);
                    }
                    String deletSP = "DELETE FROM  `require` " + " WHERE MO_NBER= " + rs.getString("MO_NBER");
                    String sql1 = "DELETE FROM  `maintenance_operation` " + " WHERE MO_NBER= " + rs.getString("MO_NBER");
                    System.out.println(deletSP);
                    System.out.println(sql1);
                    statement1.executeUpdate(deletSP);
                    statement1.executeUpdate(sql1);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);

                if (count_Language == 0) {
                    alert.setContentText("Successfully archived");
                } else {

                    alert.setContentText("تم الأرشفة بنجاح");

                }
                alert.showAndWait();
                Stage stage2 = (Stage) Archive_Window.getScene().getWindow();
                stage2.close();
// باقي اذا ما تأرشف اي شي نعلمه
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);

                if (count_Language == 0) {
                    alert.setContentText("مافيه بيانات بالتاريخ المحدد لكي نأرشفها ");
                } else {

                    alert.setContentText("مافيه بيانات بالتاريخ المحدد لكي نأرشفها ");

                }
                alert.showAndWait();

            }

        }

    }
}
