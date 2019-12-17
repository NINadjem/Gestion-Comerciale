/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gstioncomercialenadjem;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

/**
 * FXML Controller class
 *
 * @author Manno
 */
public class DeletefournisseurFXMLController implements Initializable {

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
    private Label error_report;

    @FXML
    private JFXComboBox<String> chooseClientCombo;

    @FXML
    private Label nameFieled;

    @FXML
    private Label firstNameFieled;

    @FXML
    private Label countryFieled;

    @FXML
    private Label townFieled;

    @FXML
    private Label adrsFieled;

    @FXML
    private Label societeFieled;

    @FXML
    private Label civiliteFieled;

    @FXML
    private Label telFieled;

    @FXML
    private Label faxFieled;

    @FXML
    private Label mobileFieled;

    @FXML
    private Label emailFieled;

   /*  @FXML
    private Label typeFieled;

   @FXML
    private Label exmptTvaBtn;

    @FXML
    private Label livreMemAdrsBtn;

    @FXML
    private Label factureMemAdrsBtn;

    @FXML
    private Label lastEditDateLabel;*/

    @FXML
    private Label codePostalFieled,observationFieled;
    ArrayList<DbConnexion.Fournisseur>clientList,visibleClientList;
    String id="";

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
          } 
       else{
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
      if(box!=fournisseurMenuItem) fournisseurMenuItem.setStyle("-fx-background-color:  white ;");  
    }

    @FXML
    void menuItemMouseExited(MouseEvent event) {
      VBox box= (VBox) event.getSource();
      box.setStyle("-fx-background-color:  white ;");
      fournisseurMenuItem.setStyle("-fx-background-color:  #ffd6ff ;"); 
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
    void erraceBtnClicked(MouseEvent event) {
        eraceAll();
        error_report.setText("Tout est effacés! Les champs ci-desssus est"
                + " obligatiores");
    }
    @FXML
    void goToMenuBtnCliked(MouseEvent event) throws IOException {
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

    void inisialize(DbConnexion dbConnexion, DbConnexion.Login login) throws SQLException {
        this.dbConnexion=dbConnexion;
        dialogList=new ArrayList();
        defineUser(login);
        fournisseurMenuItem.setStyle("-fx-background-color:  #ffd6ff ;");  
    }
    void defineUser(DbConnexion.Login login) throws SQLException{
      this.login=login;  
      username_label.setAlignment(Pos.TOP_RIGHT);
      username_label.setText("Salut "+login.getUsername()+" !");
      eraceAll();
      clientList=dbConnexion.getAllFournisseur();
        visibleClientList=clientList;
        if(clientList.size()<=0)
        error_report.setText("La Liste des clients est vide");
               // else  setClientData(visibleClientList.get(0));
                
        for(DbConnexion.Fournisseur a:clientList){
            chooseClientCombo.getItems().add(a.getId());
        }
        //chooseClientCombo.getSelectionModel().selectFirst();
       chooseClientCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
               id=newValue.toString(); 
               for(DbConnexion.Fournisseur a:visibleClientList){
                   if(a.getId().equals(id)){
                       setClientData(a);
                       break;
                   }
                       
               }
            }else{
           eraceAll();
           id="";
       }
            }
        });
    }
    void goToClientGestPage(){
        
    }

    private boolean allDialogAreClosed() {
        
        return (dialogList.isEmpty());
    }
           @FXML
    void addClientBtnClicked(MouseEvent event) throws SQLException {
        if(AllGood()){
        boolean id_=dbConnexion.deleteFournisseur(id);
        if(id_){    
       clientList=dbConnexion.getAllFournisseur();
        visibleClientList=clientList;
        if(clientList.size()<=0)error_report.setText("La Liste des clients est vide");
                else  setClientData(visibleClientList.get(0));
        chooseClientCombo.getItems().clear();        
        for(DbConnexion.Fournisseur a:clientList){
            chooseClientCombo.getItems().add(a.getId());
        }
        //chooseClientCombo.getSelectionModel().selectFirst();
        eraceAll();
             error_report.setText("La Suppression a été effectué");
         } else{
             error_report.setText("La Suppression immpossible! risque de violance des contraintes");
         } 
        }   
        }

    
    boolean AllGood(){
        if(id!=""){
            error_report.setText("");
            return true;
            
        }else{
           error_report.setText("Vous devez d'abord selectionner un Client");  
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
        //typeFieled.setText("");
        observationFieled.setText("");
       /* exmptTvaBtn.setText("");
        factureMemAdrsBtn.setText("");
        livreMemAdrsBtn.setText("");*/
   /*     name="";
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
        livre_meme_adrs="1";*/
        /*lastEditDateLabel.setText("");
        InsertDateLabel.setText("");*/
        chooseClientCombo.getEditor().setText("");
        
    }
        @FXML
    void searchClient(KeyEvent event) {
      visibleClientList=new ArrayList<>() ;
      chooseClientCombo.getItems().clear();
      String t=chooseClientCombo.getEditor().getText();
       if(t.isEmpty()){
           t="";
       }
        for(DbConnexion.Fournisseur a:clientList){
            if(a.getId().contains(t)||a.getName().contains(t)||a.getFirstName().contains(t)
                    ||a.getAdrs().contains(t)||a.getTown().contains(t)
                    ||a.getEmail().contains(t)||a.getCountry().contains(t)||a.getSociete().contains(t)){
                visibleClientList.add(a);
               
            chooseClientCombo.getItems().add(a.getId());
                setClientData(a);
            }
}
        


        
    }
    void setClientData(DbConnexion.Fournisseur client){
        nameFieled.setText(client.getName());
        firstNameFieled.setText(client.getFirstName());
        adrsFieled.setText(client.getAdrs());
        townFieled.setText(client.getTown());
        civiliteFieled.setText(client.getCivilite());
        telFieled.setText(client.getTel());
        mobileFieled.setText(client.getMobile());
        faxFieled.setText(client.getFax());
        emailFieled.setText(client.getEmail());
        countryFieled.setText(client.getCountry());
        codePostalFieled.setText(client.getCodePostal());
        societeFieled.setText(client.getSociete());
       // typeFieled.setText(client.getType());
        observationFieled.setText(client.getObservation());
        /*exmptTvaBtn.setText(DbConnexion.Client.BooleanFrenchValue(client.getExmptTvaValue()));
        factureMemAdrsBtn.setText(DbConnexion.Client.BooleanFrenchValue(client.getFacture_meme_adrsValue()));
        livreMemAdrsBtn.setText(DbConnexion.Client.BooleanFrenchValue(client.getLivre_meme_adrsValue()));    
        lastEditDateLabel.setText("Dérniére modification le "+client.getModifiedAt()+" par : "+client.getModifiedBy());
        InsertDateLabel.setText("Ajouter le "+client.getAddedAt()+" par : "+client.getAddedBy());*/
        id=client.getId();
        //chooseClientCombo.getEditor().setText(id);
    }
}
