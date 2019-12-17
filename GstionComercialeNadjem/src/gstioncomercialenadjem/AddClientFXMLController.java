/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gstioncomercialenadjem;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import gstioncomercialenadjem.DbConnexion.Client;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Manno
 */
public class AddClientFXMLController implements Initializable {
    public DbConnexion.Login login;
    DbConnexion dbConnexion;
    int index=0;
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
    private VBox paramsMenuItem;

    @FXML
    private Label username_label;

    @FXML
    private VBox changePasswordOrLogOutPane;

    @FXML
    private JFXTextField nameFieled;

    @FXML
    private JFXTextField firstNameFieled;

    @FXML
    private JFXTextField adrsFieled;

    @FXML
    private JFXTextField townFieled;

    @FXML
    private JFXToggleButton livreMemAdrsBtn;

    @FXML
    private JFXToggleButton exmptTvaBtn;

    @FXML
    private JFXToggleButton factureMemAdrsBtn;

    @FXML
    private Label error_report;

    @FXML
    private JFXTextField civiliteFieled;

    @FXML
    private JFXTextField telFieled;

    @FXML
    private JFXTextField mobileFieled;

    @FXML
    private JFXTextField faxFieled;

    @FXML
    private JFXTextField emailFieled;

    @FXML
    private JFXTextField countryFieled;

    @FXML
    private JFXTextField codePostalFieled;

    @FXML
    private JFXTextField societeFieled;

    @FXML
    private JFXTextField typeFieled;

    @FXML
    private JFXTextArea observationFieled;
    String id,name,firstName,adrs,town,civilite,tel,mobile,fax,email,country,codePostal,societe,
               type,observation,livre_meme_adrs,facture_meme_adrs,exmt_tva;

    @FXML
    void clientAndFourniManuBtnListener(MouseEvent event) throws IOException, SQLException {
      VBox box= (VBox) event.getSource();
      box.setStyle("-fx-background-color:  #ffbaff ;");
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
               
           } else{
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
      if(box!=clientMenuItem) clientMenuItem.setStyle("-fx-background-color:  white ;");  
    }

    @FXML
    void menuItemMouseExited(MouseEvent event) {
      VBox box= (VBox) event.getSource();
      box.setStyle("-fx-background-color:  white ;");
      clientMenuItem.setStyle("-fx-background-color:  #ffd6ff ;"); 
    }
    @FXML
    void changePasswordBtnCliked(MouseEvent event) throws IOException{  
      AnchorPane box= (AnchorPane) event.getSource();
      box.setStyle("-fx-background-color:  #ffbaff ;"); 
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
      box.setStyle("-fx-background-color:  #ffbaff ;");  
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
        clientMenuItem.setStyle("-fx-background-color:  #ffd6ff ;");  
        error_report.setAlignment(Pos.CENTER);
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
    void prenomTextWatcher(KeyEvent event) {
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

    @FXML
    void telNumTextWatcher(KeyEvent event) {
        JFXTextField field=(JFXTextField)event.getSource();
        String string="";
        for(char ch:field.getText().toCharArray()){
            
            if ((ch<='9'&&ch>='0')||ch=='+'||ch=='('||ch==')'||ch=='-')if(string.length()<20)
            {string+=String.valueOf(ch);
            }else
            break;
        }
        field.setText(string);
        field.positionCaret(string.length());
    }
    @FXML
    void civiliteTextWatcher(KeyEvent event) {
        
        String string="";
        for(char ch:civiliteFieled.getText().toCharArray()){
            
            if (ch>'9'||ch<'0')if(string.length()<5){
                if(string.length()==0)string+=String.valueOf(ch).toUpperCase();
                else string+=String.valueOf(ch);
            }else
            break;
        }
        civiliteFieled.setText(string);
        civiliteFieled.positionCaret(string.length());
    }

    @FXML
    void typeTextWatcher(KeyEvent event) {
    String string="";
    for(char ch:typeFieled.getText().toCharArray()){
           if (ch<='9'&&ch>='0')string+=String.valueOf(ch);}
        typeFieled.setText(string);
        typeFieled.positionCaret(string.length());
    }
        @FXML
    void codePostalTextWatcher(KeyEvent event) {
        
        String string="";
        for(char ch:codePostalFieled.getText().toCharArray()){
            
            if (ch<='9'&&ch>='0')if(string.length()<5){string+=String.valueOf(ch);
            }else
            break;
        }
        codePostalFieled.setText(string);
        codePostalFieled.positionCaret(string.length());
        
        
    }

    @FXML
    void erraceBtnClicked(MouseEvent event) {
        eraceAll();
        error_report.setText("Tout est effacés! Les champs ci-desssus sont obligatiores");
    }

    @FXML
    void goToMenuBtnCliked(MouseEvent event) throws IOException {
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
    }

    @FXML
    void importantFieledVboxExited(MouseEvent event) {
        AllGood();
    }
        @FXML
    void addClientBtnClicked(MouseEvent event) throws SQLException {
        if(AllGood()){
        if(factureMemAdrsBtn.isSelected())facture_meme_adrs="1";else facture_meme_adrs="0";
        if(livreMemAdrsBtn.isSelected())livre_meme_adrs="1";else livre_meme_adrs="0";
        if(exmptTvaBtn.isSelected())exmt_tva="1";else exmt_tva="0";
        if(civiliteFieled.getText().isEmpty())civilite="";else civilite=civiliteFieled.getText();
        if(telFieled.getText().isEmpty())tel="";else tel=telFieled.getText();
        if(mobileFieled.getText().isEmpty())mobile="";else mobile=mobileFieled.getText();
        if(faxFieled.getText().isEmpty())fax="";else fax=faxFieled.getText();
        if(emailFieled.getText().isEmpty())email="";else email=emailFieled.getText();
        if(countryFieled.getText().isEmpty())country="";else country=countryFieled.getText();
        if(codePostalFieled.getText().isEmpty())codePostal="";else codePostal=codePostalFieled.getText();
        if(societeFieled.getText().isEmpty())societe="";else societe=societeFieled.getText();
        if(typeFieled.getText().isEmpty())type="0";else type=typeFieled.getText();
        if (observationFieled.getText().isEmpty())observation="";else observation=observationFieled.getText();
        Client client=new Client( name, firstName, adrs, town, civilite, tel, mobile, fax, email, country,
                codePostal, societe, type, observation, livre_meme_adrs, facture_meme_adrs, exmt_tva, 
                login.getUsername(), login.getUsername());
        int id_=dbConnexion.addClient(client);
        if(id_!=0){    
         if(id_!=-1) {
             eraceAll();
             error_report.setText("L'ajout du client a été effectué avec l'identifiant "+id_);
         } else{
             error_report.setText("échoué! cet utilisateur existe déja");
         } 
        }   
        }

    }
    boolean AllGood(){
        if(!nameFieled.getText().isEmpty()&&!firstNameFieled.getText().isEmpty()&&
                !adrsFieled.getText().isEmpty()&&!townFieled.getText().isEmpty()){
            name=nameFieled.getText();
            firstName=firstNameFieled.getText();
            adrs=adrsFieled.getText();
            town=townFieled.getText();
            return true;
            
        }else{
           error_report.setText("Vous devez d'abord remplir les champs obligatiores");  
        }
        return false;
    }
    void eraceAll(){
        nameFieled.setText("");
        firstNameFieled.setText("");
        adrsFieled.setText("");
        townFieled.setText("");
        civiliteFieled.setText("");
        telFieled.setText("");
        mobileFieled.setText("");
        faxFieled.setText("");
        emailFieled.setText("");
        countryFieled.setText("");
        codePostalFieled.setText("");
        societeFieled.setText("");
        typeFieled.setText("");
        observationFieled.setText("");
        exmptTvaBtn.setSelected(true);
        factureMemAdrsBtn.setSelected(true);
        livreMemAdrsBtn.setSelected(true);
        name="";
        firstName="";
        adrs="";
        town="";
        civilite="";
        tel="";
        mobile="";
        fax="";
        email="";
        country="";
        codePostal="";
        societe="";
        type="";
        observation="";
        exmt_tva="1";
        facture_meme_adrs="1";
        livre_meme_adrs="1";
    }
}
