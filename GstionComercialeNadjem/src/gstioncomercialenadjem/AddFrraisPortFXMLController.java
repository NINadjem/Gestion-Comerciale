/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gstioncomercialenadjem;


import gstioncomercialenadjem.DbConnexion.FraisPort;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class AddFrraisPortFXMLController implements Initializable {

   public DbConnexion.Login login;
    DbConnexion dbConnexion;
    int index=0;
    String codePort,libFraitPort,montant;
    public ArrayList<DialogControllerInterface> dialogList;
    
    @FXML
    private AnchorPane parent;

    @FXML
    private VBox clientMenuItem;

    @FXML
    private VBox fournisseurMenuItem;

    @FXML
    private VBox FactMenuItem;

    @FXML
    private VBox commandeMenuItem;

    @FXML
    private VBox produitAndStockMenuItem;

    @FXML
    private VBox societeMenuItem;

    @FXML
    private VBox paramsMenuItem,
                 changePasswordOrLogOutPane;

    @FXML
    private Label username_label;
        @FXML
    private JFXTextField codePortFieled;

    @FXML
    private JFXTextField libFraitPortFieled;

    @FXML
    private JFXTextField montantFieled;
    
    
    @FXML
    private Label error_report;



    @FXML
    void clientAndFourniManuBtnListener(MouseEvent event) throws IOException, SQLException {
      VBox box= (VBox) event.getSource();
      box.setStyle("-fx-background-color:  #22EFAB ;");
      if(box==clientMenuItem){
       Stage pStage = (Stage) parent.getScene().getWindow();
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(getClass().getResource("welcomePageFXML.fxml"));
       fxmlLoader.load();
       WelcomePageFXMLController welcomeControler = fxmlLoader.getController();
       welcomeControler.inisialize(dbConnexion,login);
       Stage stage = new Stage();
       stage.setScene(new Scene(fxmlLoader.getRoot()/*, width, heigh*/));
       stage.setResizable(false);
       stage.setTitle("");
       stage.show();
       pStage.close();  
      }else{
                   if(box==fournisseurMenuItem){
      Stage pStage = (Stage) parent.getScene().getWindow();
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(getClass().getResource("fournisseurMenuPageFXML.fxml"));
       fxmlLoader.load();
       FournisseurMenuPageFXMLController welcomeControler = fxmlLoader.getController();
       welcomeControler.inisialize(dbConnexion,login);
       Stage stage = new Stage();
       stage.setScene(new Scene(fxmlLoader.getRoot()/*, width, heigh*/));
       stage.setResizable(false);
       stage.setTitle("");
       stage.show();
       pStage.close();
          } else{
           if(box==produitAndStockMenuItem){
      Stage pStage = (Stage) parent.getScene().getWindow();
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(getClass().getResource("produitEtStockMenuFXML.fxml"));
       fxmlLoader.load();
       ProduitEtStockMenuFXMLController welcomeControler = fxmlLoader.getController();
       welcomeControler.inisialize(dbConnexion,login);
       Stage stage = new Stage();
       stage.setScene(new Scene(fxmlLoader.getRoot()/*, width, heigh*/));
       stage.setResizable(false);
       stage.setTitle("");
       stage.show();
       pStage.close();               
               
           }else{
                       if(box==FactMenuItem){
      Stage pStage = (Stage) parent.getScene().getWindow();
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(getClass().getResource("factureFournisseurFXML.fxml"));
       fxmlLoader.load();
       FactureFournisseurFXMLController welcomeControler = fxmlLoader.getController();
       welcomeControler.inisialize(dbConnexion,login);
       Stage stage = new Stage();
       stage.setScene(new Scene(fxmlLoader.getRoot()/*, width, heigh*/));
       stage.setResizable(false);
       stage.setTitle("");
       stage.show();
       pStage.close();               
               
           }else{
                                                                if(box==paramsMenuItem){
      Stage pStage = (Stage) parent.getScene().getWindow();
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(getClass().getResource("toolsFXML.fxml"));
       fxmlLoader.load();
       ToolsFXMLController welcomeControler = fxmlLoader.getController();
       welcomeControler.inisialize(dbConnexion,login);
       Stage stage = new Stage();
       stage.setScene(new Scene(fxmlLoader.getRoot()/*, width, heigh*/));
       stage.setResizable(false);
       stage.setTitle("");
       stage.show();
       pStage.close();                             
               
                       
    
                               
      }  
           }   
           }             
                   }
      }
    }

    @FXML
    void menuItemMouseEntereed(MouseEvent event) {
      VBox box= (VBox) event.getSource();
      box.setStyle("-fx-background-color:  #ffd6ff ;");
      if(box!=paramsMenuItem) paramsMenuItem.setStyle("-fx-background-color:  white ;");  
    }

    @FXML
    void menuItemMouseExited(MouseEvent event) {
      VBox box= (VBox) event.getSource();
      box.setStyle("-fx-background-color:  white ;");
      paramsMenuItem.setStyle("-fx-background-color:  #ffd6ff ;");
    }
    @FXML
    void changePasswordBtnCliked(MouseEvent event) throws IOException{  
      AnchorPane box= (AnchorPane) event.getSource();
      box.setStyle("-fx-background-color:  #ffd6ff ;");
      FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(getClass().getResource("changePasswordFXML.fxml"));
       fxmlLoader.load();
       ChangePasswordFXMLController controler = fxmlLoader.getController();
       dialogList.add(controler);
       controler.init(dbConnexion,this.login, index++,dialogList);
       Stage stage = new Stage();
       stage.setScene(new Scene(fxmlLoader.getRoot()/*, width, heigh*/));
       stage.setResizable(false);
       stage.setTitle("");
       stage.show();
    }
    @FXML
    void changePasswordOrLogOutMouseEntereed(MouseEvent event){
      AnchorPane box= (AnchorPane) event.getSource();
      box.setStyle("-fx-background-color:  #ffd6ff ;");       
    }
    @FXML
    void changePasswordOrLogOutMouseExited(MouseEvent event) throws IOException{
      AnchorPane box= (AnchorPane) event.getSource();
      box.setStyle("-fx-background-color:  white ;");
       
    }
    @FXML
    void logOutBtnCliked(MouseEvent event) throws IOException{
       if(true) {
      AnchorPane box= (AnchorPane) event.getSource();
      box.setStyle("-fx-background-color:  #ffd6ff ;");
      Stage pStage = (Stage) parent.getScene().getWindow();
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(getClass().getResource("loginFXML.fxml"));
       fxmlLoader.load();
       Stage stage = new Stage();
       stage.setScene(new Scene(fxmlLoader.getRoot()));
       stage.setResizable(false);
       stage.setTitle("");
       stage.show();
       pStage.close();
       }else{
         //toast  
       }
    }
    @FXML
    void userAvatarBtnEntered(MouseEvent event){
        changePasswordOrLogOutPane.setVisible(true);
    }
    @FXML
    void userAvatarBtnExited(MouseEvent event){
       changePasswordOrLogOutPane.setVisible(false);   
    }
   @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    void inisialize(DbConnexion dbConnexion, DbConnexion.Login login) {
        this.dbConnexion=dbConnexion;
        dialogList=new ArrayList();
        defineUser(login);
        paramsMenuItem.setStyle("-fx-background-color:  #ffd6ff ;"); 
    }
    void defineUser(DbConnexion.Login login){
      this.login=login;  
      username_label.setAlignment(Pos.TOP_RIGHT);
      username_label.setText("Salut "+login.getUsername()+" !"); 
    }
    void goToClientGestPage(){
        
    }

    private boolean allDialogAreClosed() {
        
        return (dialogList.isEmpty());
    }
            @FXML
    void nomTextWatcher(KeyEvent event) {
       JFXTextField field=(JFXTextField)event.getSource();
       String string="";
        for(char ch:field.getText().toCharArray()){
               if (ch>'9'||ch<'0')
                if(string.length()<40)
                {
                    string+=String.valueOf(ch); 
                }else break;
        }
        field.setText(string.toUpperCase());
        field.positionCaret(string.length());
    }
    
    @FXML
    void realTyped(KeyEvent event) {
    JFXTextField typeFieled=(JFXTextField)event.getSource();
    String string="";
    for(char ch:typeFieled.getText().toCharArray()){
           if ((ch<='9'&&ch>='0')||(ch=='.'))
           {string+=String.valueOf(ch);}
        typeFieled.setText(string);
        typeFieled.positionCaret(string.length());
    }
    }
     @FXML
    void villeTextWatcher(KeyEvent event) {
   JFXTextField field=(JFXTextField)event.getSource();
       String string="";
        for(char ch:field.getText().toCharArray()){
            
            if (ch>'9'||ch<'0')
                if(string.length()<40)
                {
                int i=string.length();
                if(i>=1){if(string.charAt(i-1)!=' ')   
                string+=String.valueOf(ch); 
                else string+=String.valueOf(ch).toUpperCase(); 
                
            }else{
                string+=String.valueOf(ch).toUpperCase();     
                }
                }else
            break;
        }
        field.setText(string);
        field.positionCaret(string.length());
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------//
      @FXML
    void erraceBtnClicked(MouseEvent event) {
          eraceAll();

    }
    
    @FXML
    void addFraitPortBtnClicked(MouseEvent event) throws SQLException {
                if(AllGood()){
            DbConnexion.FraisPort fraisPort = new DbConnexion.FraisPort(codePort,libFraitPort,montant);
            int id_=dbConnexion.addFraitPort(fraisPort);
            if(id_!=0){    
         if(id_!=-1) {
             eraceAll();
             error_report.setText("L'ajout de la famille a été effectué");
         } else{
             System.err.println("************id="+id_);
             error_report.setText("échoué! cette famille existe déja");
         } 
        }
                    
        }

    }
    void eraceAll(){
        codePortFieled.setText("");
        libFraitPortFieled.setText("");
        montantFieled.setText("");
    }
    boolean AllGood(){
        if(!codePortFieled.getText().isEmpty()&&!libFraitPortFieled.getText().isEmpty()
                && !montantFieled.getText().isEmpty()){
            codePort=codePortFieled.getText();
            libFraitPort=libFraitPortFieled.getText();
            montant=montantFieled.getText();
            return true;
            
        }else{
           error_report.setText("Vous devez remlir tous les champs");  
        }
        return false;
    }   
}
