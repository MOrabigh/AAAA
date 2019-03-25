    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import Connectvy.ConnectionClass;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;


/**
 *
 * @author algha
 */
public class printreport extends JFrame {
    ConnectionClass connectionClass = new ConnectionClass();
    // we call conneClass  that we make it up
    Connection connection = connectionClass.getConnection();
     PreparedStatement pst = null;
     ResultSet rs =null;
     
     public printreport() throws HeadlessException{
        
     } 
     public void showReportEN (String ss)throws JRException{
         
             
             JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\warrantyEN.jrxml");
             String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('paid') AND m.MO_NBER ='" + ss+ "'";
             
             JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
             
             
             
         
     }
     public void financialassessmentEN(String ff) throws JRException{
          JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\financialassessmentEN.jrxml");
             String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approve', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + ff + "'";
             JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
             
         
     } 
     public void showReport (String ss)throws JRException{
         
             
             JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\warranty.jrxml");
             String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('paid') AND m.MO_NBER ='" + ss+ "'";
             
             JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
             
             
             
         
     }
     public void financialassessment(String ff) throws JRException{
          JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\financialassessment.jrxml");
             String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approve', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + ff + "'";
             JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
             
         
     } 
     public void PreviousMO (LocalDate bb, LocalDate ss)  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\PreviousMO.jrxml");
           String query =  "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove') AND m.STARTING_DATE <= '" +bb+ "' AND m.STARTING_DATE >= '" + ss + "'";
            System.out.println(query);
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
            jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
         
     }
       public void CurrentMO(LocalDate bb, LocalDate ss)  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\CurrentMO.jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approve','under maintenance') AND m.STARTING_DATE <= '" + bb + "' AND m.STARTING_DATE >= '" + ss + "'";
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
            jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
      }
        public void FinshedMO(LocalDate bb, LocalDate ss)  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\FinshedMO.jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE <= '" + bb + "' AND m.STARTING_DATE >= '" + ss + "'";
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
           jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
        }
         public void CustomersList()  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\CustomersList.jrxml");
          //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
            // JRDesignQuery jrquery = new JRDesignQuery();
             //jrquery.setText(query);
            // jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
         }
          public void EmployeesList()  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\EmployeesList.jrxml");
          //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
            // JRDesignQuery jrquery = new JRDesignQuery();
             //jrquery.setText(query);
            // jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
          }
           public void SuppliersList()  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\SuppliersList.jrxml");
          //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
            // JRDesignQuery jrquery = new JRDesignQuery();
             //jrquery.setText(query);
            // jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
           }
            public void SPsOUTOS()  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\SPs OUTOS.jrxml");
          //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
            // JRDesignQuery jrquery = new JRDesignQuery();
             //jrquery.setText(query);
            // jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
            }
             public void SPstobeOUTOS()  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\SPs to be OUTOS.jrxml");
          //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
            // JRDesignQuery jrquery = new JRDesignQuery();
             //jrquery.setText(query);
            // jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             }
     public void ListofaemployeesMOs (String ss) throws JRException{
          JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\List of a employee's MOs .jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '"+ ss+"'" ;
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
         
         
     } 
      public void ListofcustomersMOs (String bb, String ss) throws JRException{
          JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\List of a customer's MOs .jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where m.MO_NBER = '" + bb + "'OR r.CUS_MOBILE_NBER = '" + ss + "'" ;
           JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
     

    
    
      }
         public void FinancialassessReport(String bb, String ss) throws JRException{
              JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\Financialassessmentreport.jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approve', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + bb+ "' OR r.CUS_MOBILE_NBER = '" + ss + "'";
             JRDesignQuery jrquery = new JRDesignQuery();
           jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
         
             jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
             
         }

    void PreviousMOPeriod(LocalDate AA, LocalDate MM) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\PreviousMO.jrxml");
           String query =  "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
            System.out.println(query);
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
            jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
        
    }
      void CurrentMOPeriod (LocalDate AA, LocalDate MM)  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\PreviousMO.jrxml");
           String query =  "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approve','under maintenance') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
            System.out.println(query);
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
            jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
         
     }
       public void FinshedMOPeriod(LocalDate AA, LocalDate MM)  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\FinshedMO.jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
           jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
        }
       public void PreviousMOEN (LocalDate bb, LocalDate ss)  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\PreviousMOEN.jrxml");
           String query =  "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove') AND m.STARTING_DATE <= '" +bb+ "' AND m.STARTING_DATE >= '" + ss + "'";
            System.out.println(query);
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
            jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
         
     }
       public void CurrentMOEN (LocalDate bb, LocalDate ss)  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\CurrentMOEN.jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approve','under maintenance') AND m.STARTING_DATE <= '" + bb + "' AND m.STARTING_DATE >= '" + ss + "'";
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
            jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
      }
        public void FinshedMOEN (LocalDate bb, LocalDate ss)  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\FinshedMOEN.jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE <= '" + bb + "' AND m.STARTING_DATE >= '" + ss + "'";
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
           jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
        }
         public void CustomersListEN()  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\CustomersListEN.jrxml");
          //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
            // JRDesignQuery jrquery = new JRDesignQuery();
             //jrquery.setText(query);
            // jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
         }
          public void EmployeesListEN()  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\EmployeesListEN.jrxml");
          //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
            // JRDesignQuery jrquery = new JRDesignQuery();
             //jrquery.setText(query);
            // jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
          }
           public void SuppliersListEN()  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\SuppliersListEN.jrxml");
          //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
            // JRDesignQuery jrquery = new JRDesignQuery();
             //jrquery.setText(query);
            // jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
           }
            public void SPsOUTOSEN()  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\SPs OUTOSEN.jrxml");
          //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
            // JRDesignQuery jrquery = new JRDesignQuery();
             //jrquery.setText(query);
            // jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
            }
             public void SPstobeOUTOSEN()  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\SPs to be OUTOSEN.jrxml");
          //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
            // JRDesignQuery jrquery = new JRDesignQuery();
             //jrquery.setText(query);
            // jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             }
     public void ListofaemployeesMOsEN (String ss) throws JRException{
          JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\List of a employee's MOsEN .jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '"+ ss+"'" ;
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
         
         
     } 
      public void ListofcustomersMOsEN (String bb, String ss) throws JRException{
          JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\List of a customer's MOsEN .jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where m.MO_NBER = '" + bb + "'OR r.CUS_MOBILE_NBER = '" + ss + "'" ;
           JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
     

    
    
      }
         public void FinancialassessReportEN(String bb, String ss) throws JRException{
              JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\FinancialassessmentreportEN.jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approve', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + bb+ "' OR r.CUS_MOBILE_NBER = '" + ss + "'";
             JRDesignQuery jrquery = new JRDesignQuery();
           jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
         
             jrquery.setText(query);
             jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
             
         }

    void PreviousMOPeriodEN(LocalDate AA, LocalDate MM) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\PreviousMOEN.jrxml");
           String query =  "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
            System.out.println(query);
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
            jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
        
    }
      void CurrentMOPeriodEN (LocalDate AA, LocalDate MM)  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\PreviousMOEN.jrxml");
           String query =  "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approve','under maintenance') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
            System.out.println(query);
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
            jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
             
         
     }
       public void FinshedMOPeriodEN(LocalDate AA, LocalDate MM)  throws JRException{
         JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\algha\\Documents\\NetBeansProjects\\AAA\\src\\sample\\FinshedMOEN.jrxml");
           String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
            JRDesignQuery jrquery = new JRDesignQuery();
             jrquery.setText(query);
           jasperDesign.setQuery(jrquery);
             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
             JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null,connection );
             JRViewer viewer = new JRViewer (Jasperprint);
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500);
              this.setVisible(true);
        }

   
}
