
package gstioncomercialenadjem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loginFXMLController implements Initializable {
    
    @FXML
    AnchorPane parent_login;
    @FXML
    private JFXTextField login_nom_uti;
    @FXML
    private JFXPasswordField login_password;
    @FXML
    private JFXButton login_btn,frgot_password_button;
    @FXML
    private Label login_error_report;
    @FXML
    JFXSpinner prograss_bar;
    DbConnexion dbConnexion=null;
    
    public loginFXMLController() throws ClassNotFoundException,SQLException {

                dbConnexion = new DbConnexion();
          }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        login_error_report.setAlignment(Pos.CENTER);
        login_nom_uti.setText("Nadjem Nour El Imane");
        login_password.setText("123");
        login_btn.setOnMouseClicked(e->{ 
        try {
        loginButtonListener();
          } catch (IOException | SQLException ex) {
                Logger.getLogger(loginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }  

    private void loginButtonListener() throws IOException, SQLException {
        if(!login_password.getText().isEmpty()&&!login_nom_uti.getText().isEmpty()){
           String userName=login_nom_uti.getText().toString();
           String password=login_password.getText().toString(); 
           blockLoginPage(true,null);
           DbConnexion.Login login = dbConnexion.SignIn(userName);
           if(login!=null){
             System.out.println("login:got the user "+login.getId()+" successfully");
             if(login.getPassword().equals(password)){
               GoToWelcomePage("welcomePageFXML.fxml",""/*,1350, 685*/,login);    
               }else{
                 blockLoginPage(false,"Mot de Passe incorrect!");     
                 }
           }else{
             blockLoginPage(false,"Cette utilisateur n'existe pas");                                        
              }
        }else{
            login_error_report.setText("Vous devez remplir tous les champs d'abord");
        }
    }

    private void blockLoginPage(boolean blockFlag,String report) {
      if(blockFlag)  login_error_report.setText("");
      else if(report!=null) login_error_report.setText(report);
      login_btn.setVisible(!blockFlag);
      frgot_password_button.setVisible(!blockFlag);
      login_nom_uti.setEditable(!blockFlag);
      login_password.setEditable(!blockFlag);
      prograss_bar.setVisible(blockFlag);  
    }

    private void GoToWelcomePage(String fxmlDoc, String pageTitle/*, int width, int heigh*/, DbConnexion.Login login)
                                throws IOException {
        
       Stage pStage = (Stage) parent_login.getScene().getWindow();
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(getClass().getResource(fxmlDoc));
       fxmlLoader.load();
       WelcomePageFXMLController welcomeControler = fxmlLoader.getController();
       welcomeControler.inisialize(dbConnexion,login);
       Stage stage = new Stage();
       stage.setScene(new Scene(fxmlLoader.getRoot()/*, width, heigh*/));
       stage.setResizable(false);
       stage.setTitle(pageTitle);
       stage.show();
       pStage.close();
        
    }

}