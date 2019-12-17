/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gstioncomercialenadjem;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.sun.javafx.collections.ElementObservableListDecorator;
import gstioncomercialenadjem.DbConnexion.Client;
import gstioncomercialenadjem.DbConnexion.Famille;
import gstioncomercialenadjem.DbConnexion.Fournisseur;
import gstioncomercialenadjem.DbConnexion.FraisPort;
import gstioncomercialenadjem.DbConnexion.Produit;
import gstioncomercialenadjem.DbConnexion.Stock;
import gstioncomercialenadjem.DbConnexion.TVA;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
public class AddProductFXMLController implements Initializable {
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
    private JFXTextField reffFieled;

    @FXML
    private JFXTextField codeGenFieled;

    @FXML
    private JFXTextField codeBarFieled;

    @FXML
    private JFXTextField libelleFieled;

    @FXML
    private JFXToggleButton catalogueBtn;

    @FXML
    private Label error_report;

    @FXML
    private JFXComboBox<String> familleCombo;

    @FXML
    private JFXTextField prixHTFieled;

    @FXML
    private JFXComboBox<String> fournisseurCombo;

    @FXML
    private JFXTextField minQteFieled;

    @FXML
    private JFXTextField reapoQteFieled;

    @FXML
    private JFXComboBox<String> tvaCombo;

    @FXML
    private JFXTextField qteStockFieled;

    @FXML
    private JFXTextField vQteStockFieled;

    @FXML
    private JFXComboBox<String> codePortCombo;

    @FXML
    private JFXTextArea descriptionFieled;
    String catalogue,description,reff,codeG,codeB,lib,famille,prixHT,fournisseur,qteMin,reapoQte,vQte,tva,stckQte,codePort;
    ArrayList<DbConnexion.Famille> familleList;
    ArrayList<DbConnexion.TVA> TvaList;
    ArrayList<DbConnexion.FraisPort> fraisPortList;
ArrayList<DbConnexion.Fournisseur> fournisseurList;
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
      if(box!=produitAndStockMenuItem) produitAndStockMenuItem.setStyle("-fx-background-color:  white ;");  
    }

    @FXML
    void menuItemMouseExited(MouseEvent event) {
      VBox box= (VBox) event.getSource();
      box.setStyle("-fx-background-color:  white ;");
     produitAndStockMenuItem.setStyle("-fx-background-color:  #ffd6ff ;"); 
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

    void inisialize(DbConnexion dbConnexion, DbConnexion.Login login) throws SQLException {
        this.dbConnexion=dbConnexion;
        dialogList=new ArrayList();
        defineUser(login);
        produitAndStockMenuItem.setStyle("-fx-background-color:  #ffd6ff ;");  
        error_report.setAlignment(Pos.CENTER);
        familleList=dbConnexion.getAllFamille();
        if(familleList.size()<=0)
        error_report.setText("La Liste des famille est vide");
               // else  setClientData(visibleClientList.get(0));
                
        for(Famille a:familleList){
            familleCombo.getItems().add(a.getCodeFamille());
        }
                fraisPortList=dbConnexion.getAllFraisPorts();
        if(fraisPortList.size()<=0)
        error_report.setText("La Liste des FraisPort est vide");
               // else  setClientData(visibleClientList.get(0));
                
        for(FraisPort a:fraisPortList){
            codePortCombo.getItems().add(a.getCodePort());
        }
        TvaList=dbConnexion.getAllTVA();
        if(TvaList.size()<=0)
        error_report.setText("La Liste des TVA est vide");
               // else  setClientData(visibleClientList.get(0));
                
        for(TVA a:TvaList){
            tvaCombo.getItems().add(a.getTva());
        }
                fournisseurList=dbConnexion.getAllFournisseur();
        if(fournisseurList.size()<=0)
        error_report.setText("La Liste des fournisseurs est vide");
               // else  setClientData(visibleClientList.get(0));
                
        for(Fournisseur a:fournisseurList){
            fournisseurCombo.getItems().add(a.getId());
        }
        fournisseurCombo.getSelectionModel().selectFirst();
       codePortCombo.getSelectionModel().selectFirst();
       familleCombo.getSelectionModel().selectFirst();
       tvaCombo.getSelectionModel().selectFirst();
       fournisseurCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
               fournisseur=newValue.toString(); 
            }else{
             fournisseurCombo.getSelectionModel().selectFirst();  
       }
            }
        });
       codePortCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
               codePort=newValue.toString(); 
            }else{
             codePortCombo.getSelectionModel().selectFirst();  
       }
            }
        });
       familleCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
               famille=newValue.toString(); 
            }else{
             familleCombo.getSelectionModel().selectFirst();  
       }
            }
        });
       tvaCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
               tva=newValue.toString(); 
            }else{
             tvaCombo.getSelectionModel().selectFirst();  
       }
            }
        });
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
    void prixTextWatcher(KeyEvent event) {
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
    void typeTextWatcher(KeyEvent event) {
        JFXTextField typeFieled=(JFXTextField)event.getSource();
    String string="";
    for(char ch:typeFieled.getText().toCharArray()){
           if (ch<='9'&&ch>='0')string+=String.valueOf(ch);}
        typeFieled.setText(string);
        typeFieled.positionCaret(string.length());
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
        if(catalogueBtn.isSelected())catalogue="1";else catalogue="0";
        if(prixHTFieled.getText().isEmpty())prixHT="";else prixHT=prixHTFieled.getText();
        if(minQteFieled.getText().isEmpty())qteMin="";else qteMin=minQteFieled.getText();
        if(reapoQteFieled.getText().isEmpty())reapoQte="";else reapoQte=reapoQteFieled.getText();
        if(vQteStockFieled.getText().isEmpty())vQte="";else vQte=vQteStockFieled.getText();
        if(qteStockFieled.getText().isEmpty())stckQte="";else stckQte=qteStockFieled.getText();
        if(descriptionFieled.getText().isEmpty())description="";else description=descriptionFieled.getText();
        Stock s=new Stock(reff, vQte, stckQte, login.getUsername(), null);
        Produit p=new Produit(tva, catalogue, description, reff, codeG, codeB, lib, famille, prixHT, fournisseur,qteMin, reapoQte, codePort, login.getUsername(), null);
        String id_=dbConnexion.addProduitAndStock(p,s);
        if(id_!=null){    
         if(id_!="-1") {
             eraceAll();
             error_report.setText("L'ajout du produit et du stock a été effectué avec l'identifiant "+id_);
         } else{
             error_report.setText("échoué! ce stock existe déja");
         } 
        }else{
           error_report.setText("échoué! ce produit existe déja"); 
        }   
        }

    }
    boolean AllGood(){
        if(!reffFieled.getText().isEmpty()&&!codeGenFieled.getText().isEmpty()&&
                !codeBarFieled.getText().isEmpty()&&!libelleFieled.getText().isEmpty()){
            reff=reffFieled.getText();
            codeG=codeGenFieled.getText();
            codeB=codeBarFieled.getText();
            lib=libelleFieled.getText();
            return true;
            
        }else{
           error_report.setText("Vous devez d'abord remplir les champs obligatiores");  
        }
        return false;
    }
    void eraceAll(){
        reffFieled.setText("");
        libelleFieled.setText("");
        codeGenFieled.setText("");
        codeBarFieled.setText("");
        prixHTFieled.setText("");
        minQteFieled.setText("");
        reapoQteFieled.setText("");
        vQteStockFieled.setText("");
        qteStockFieled.setText("");
        descriptionFieled.setText("");
        catalogueBtn.setSelected(true);
        familleCombo.getSelectionModel().selectFirst();
        tvaCombo.getSelectionModel().selectFirst();
        fournisseurCombo.getSelectionModel().selectFirst();
        codePortCombo.getSelectionModel().selectFirst();
       
    }
    
}
