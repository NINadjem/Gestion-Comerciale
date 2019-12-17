
package gstioncomercialenadjem;

import gstioncomercialenadjem.DbConnexion.Login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXTextField;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ChangePasswordFXMLController extends DialogControllerInterface {
    
    Login login=null;
    DbConnexion dbConnexion;
    @FXML
    AnchorPane parent;
    @FXML
    private JFXTextField oldPassword;

    @FXML
    private JFXTextField newPassword;

    @FXML
    private JFXTextField cNewPassword;

    @FXML
    private Label login_error_report;

    @FXML
    void changeThepassword(MouseEvent event) throws SQLException {
        if(allGood()){
            oldPassword.setEditable(false);
            newPassword.setEditable(false);
            cNewPassword.setEditable(false);
            login.setPassword(newPassword.getText().toString());
            if(dbConnexion.setLogin(login)){
               Stage pStage = (Stage) parent.getScene().getWindow();
               pStage.close(); 
            }else{
                //problem de requette
                login_error_report.setVisible(true);
                login_error_report.setText("le changement n'a pas été effectué vuillez changer le nouveau mot de passe où resseyer");
                oldPassword.setEditable(true);
                newPassword.setEditable(true);
                cNewPassword.setEditable(true);
            }
        }
    }

    @FXML
    void exitChangeing(MouseEvent event) {
        Stage pStage = (Stage) parent.getScene().getWindow();
        pStage.close();
    }

    @FXML
    void testConfirmationPassword(MouseEvent event) {
        allGood();
    }

    @FXML
    void testOldPassword(MouseEvent event) {
        allGood();
    }

    @FXML
    void testnewPassword(MouseEvent event) {
        allGood();
    }  

    void init(DbConnexion connexion,DbConnexion.Login login,int id,ArrayList<DialogControllerInterface> list) {
        this.login=login;
        dbConnexion=connexion;
        setId(id);
        setList(list);
    }
    boolean allGood(){
       boolean allGood=false;
       if(!isEmptyFieled(oldPassword,true)){
           oldPassword.setUnFocusColor(Paint.valueOf("#a9b7c6"));
           if(hasTheRightPassword(false)){
            oldPassword.setUnFocusColor(Paint.valueOf("#a9b7c6"));   
           if(!isEmptyFieled(newPassword,true)){ 
            newPassword.setUnFocusColor(Paint.valueOf("#a9b7c6"));   
            if(!isEmptyFieled(cNewPassword,true)){
             cNewPassword.setUnFocusColor(Paint.valueOf("#a9b7c6"));   
                if(!newOldEquals(false)){
                  newPassword.setUnFocusColor(Paint.valueOf("#a9b7c6"));  
                    if(newConEquals(false)){
                        cNewPassword.setUnFocusColor(Paint.valueOf("#a9b7c6"));;
                        return true;
                    }
                }
            }
           }
           }
       }
       return allGood;
    }
    boolean isEmptyFieled(JFXTextField field,boolean reportError){
        if(field.getText().isEmpty()){
          String det;  
          if(field==oldPassword) {
              det="l'";
          }
          else if (field==newPassword) {
              det="le ";
          }else{
              det="la ";
          }
          if(reportError){
          login_error_report.setText("Vous devez remplir "+det+field.getPromptText()+ "d'abord !"); 
          field.setUnFocusColor(Paint.valueOf("#ec716f"));}
          return true;  
        }
        login_error_report.setText(""); 
        return false;
    }
    boolean newOldEquals(boolean testNull){
        if(testNull){
          if(isEmptyFieled(oldPassword,true)&&isEmptyFieled(newPassword,true)) return false;
        }
        if(newPassword.getText().toString().equals(oldPassword.getText().toString())){
            login_error_report.setText("Le nouveau mot de passe et l'Ancien mot de passe sont identique");
            newPassword.setUnFocusColor(Paint.valueOf("#ec716f"));
            return true;
        }
        login_error_report.setText("");
        return false;
    }
    boolean newConEquals (boolean testNull){
        if(testNull){
          if(isEmptyFieled(newPassword,true)&&isEmptyFieled(cNewPassword,true)) return false;
        }
        if(!newPassword.getText().toString().equals(cNewPassword.getText().toString())){
            login_error_report.setText("Le nouveau mot de passe est incompatible avec la confirmation");
            cNewPassword.setUnFocusColor(Paint.valueOf("#ec716f"));
            return false;
        }
        login_error_report.setText("");
        return true;
    }
    boolean hasTheRightPassword(boolean testNull){
        if(testNull){
            if(isEmptyFieled(oldPassword,true))return false;
        }
        if(!oldPassword.getText().toString().equals(login.getPassword())) {
            login_error_report.setText("L'ancien mot de Passe est icorrect");
            oldPassword.setUnFocusColor(Paint.valueOf("#ec716f"));
            return false;
        }
        login_error_report.setText("");
        return true;
    }

    @Override
    public boolean closeWidow() {
        if(!isEmptyFieled(oldPassword, false)&&!isEmptyFieled(newPassword, false)
                &&!isEmptyFieled(cNewPassword, false)) return false;   
        Stage pStage = (Stage) parent.getScene().getWindow();
        pStage.close();
        return true;
    }


    
    
}
