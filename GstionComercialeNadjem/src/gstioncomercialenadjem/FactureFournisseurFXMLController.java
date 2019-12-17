/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gstioncomercialenadjem;

import java.net.URL;
import java.util.ResourceBundle;   
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.deploy.cache.InMemoryLocalApplicationProperties;
import com.sun.javafx.collections.ElementObservableListDecorator;
import gstioncomercialenadjem.DbConnexion.AdresseDeFacturation;
import gstioncomercialenadjem.DbConnexion.Client;
import gstioncomercialenadjem.DbConnexion.Facture;
import gstioncomercialenadjem.DbConnexion.ModeDeReglement;
import gstioncomercialenadjem.DbConnexion.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import static sun.security.jgss.GSSUtil.login;

/**
 * FXML Controller class
 *
 * @author Manno
 */
public class FactureFournisseurFXMLController implements Initializable {
        public DbConnexion.Login login;
    DbConnexion dbConnexion;
    int index=0,q=0;
    public ArrayList<DialogControllerInterface> dialogList;
    static ArrayList<DbConnexion.Client>clientList,visibleClientList;
    static ArrayList<DbConnexion.Facture>factList,visibleFactList;
    static int currentFactindex=0;
    static ArrayList<DbConnexion.ModeDeReglement>modeDeRList,vmodeDeRList;
    static ArrayList<DbConnexion.AdresseDeFacturation> adrsFacturactionList,vAdrsFacturactionList;
    static ArrayList<DbConnexion.Produit> produitList,vProduitsList;
    static ArrayList<Table> tabList;
    static String id="",idA="",idM="";
    static Facture facture;
    
    @FXML
    private AnchorPane imgBox,imgBoxHappy;
       @FXML
    private ImageView notPayedImg1;

    @FXML
    private ImageView notPayedImg2;
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
    private Label factNumLabel;

    @FXML
    private JFXTreeTableView clientListTable;

    @FXML
    private Label nameFieled;

    @FXML
    private Label emailFieled;

    @FXML
    private Label adrsFieled;

    @FXML
    private JFXComboBox<String> adrsCombo,ProdCombo;

    @FXML
    private JFXTextArea observation;

    @FXML
    private JFXTextField deposeAgent,quantiteFieled;

    @FXML
    private JFXComboBox<String> modePCombo;

    @FXML
    private JFXTextField remiseFieled;

    @FXML
    private JFXComboBox<String> chooseClientCombo;

    @FXML
    private JFXDatePicker dateFieled;

    @FXML
    private JFXTextField htFieled;

    @FXML
    private JFXTextField tvaFieled;

    @FXML
    private JFXTextField ttcFieled;
    private JFXTreeTableColumn<Table, String> idCol,firstNameCol,nameCol, societeCol,tvaCol;
    int tabIndex=0;
    String modP,adrsF;
    private JFXSnackbar snackBar;

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
      if(box!=FactMenuItem) FactMenuItem.setStyle("-fx-background-color:  white ;");  
    }

    @FXML
    void menuItemMouseExited(MouseEvent event) {
      VBox box= (VBox) event.getSource();
      box.setStyle("-fx-background-color:  white ;");
      FactMenuItem.setStyle("-fx-background-color:  #ffd6ff ;"); 
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

    void inisialize(DbConnexion dbConnexion, DbConnexion.Login login) throws SQLException, IOException {
        this.dbConnexion=dbConnexion;
        snackBar = new JFXSnackbar(parent);
        dialogList=new ArrayList();
        defineUser(login);
        FactMenuItem.setStyle("-fx-background-color:  #ffd6ff ;");  
    }
    void defineUser(DbConnexion.Login login) throws SQLException, IOException{
      this.login=login;  
      username_label.setAlignment(Pos.TOP_RIGHT);
      username_label.setText("Salut "+login.getUsername()+" !"); 
      //eraceAll();
                 produitList=dbConnexion.getAllProduitsDispo();
           for(Produit a:produitList){
            ProdCombo.getItems().add(a.getReff());
        }
      clientList=dbConnexion.getAllClient();
      visibleClientList=clientList;      
        for(Client a:clientList){
            chooseClientCombo.getItems().add(a.getId());
        }
        //chooseClientCombo.getSelectionModel().selectFirst();
       chooseClientCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
                id=newValue.toString(); 
               for(Client a:visibleClientList){
                   if(a.getId().equals(id)){
                       try{
                       setClientData(a);
                       }catch( SQLException sqe){
                           
                       }catch(IOException io){
                           
                       }
                       break;
                   }
                       
               }
            }else{
           eraceAll();
           //id="";
       }
            }
        });
             modeDeRList=dbConnexion.getAllModeDeRlist();
             vmodeDeRList=modeDeRList;   
        for(ModeDeReglement a:modeDeRList){
            modePCombo.getItems().add(a.getId()+" "+a.lib);
        }
        //chooseClientCombo.getSelectionModel().selectFirst();
       modePCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
                modP=newValue.toString(); 
               for(ModeDeReglement a:modeDeRList){
                   if((a.getId()+" "+a.lib).equals(modP)){
                   modP=a.id;   
                       break;
                   }
                       
               }
            }else{
           eraceAll();
           //id="";
       }
            }
        });
             adrsFacturactionList=dbConnexion.getAllAdressFact();
      vAdrsFacturactionList=adrsFacturactionList;      
        for(AdresseDeFacturation a:adrsFacturactionList){
            adrsCombo.getItems().add(a.getId()+" "+a.lib);
        }
        //chooseClientCombo.getSelectionModel().selectFirst();
       adrsCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
                adrsF=newValue.toString(); 
               for(AdresseDeFacturation a:adrsFacturactionList){
                   if((a.getId()+" "+a.lib).equals(adrsF)){
                       adrsF=a.id;
                       break;
                   }
                       
               }
            }else{
           //eraceAll();
           //id="";
       }
            }
        });
       factList=dbConnexion.getAllFactures();
       if(factList.size()!=0)
       factList.add(new Facture(String.valueOf(Integer.parseInt(factList.get(factList.size()-1).id)+1),
               "", clientList.get(0).id, adrsFacturactionList.get(0).id, 
               modeDeRList.get(0).id, "0", "0", "0", "", "0", "", this.login.getUsername(), "", ""));
       else    factList.add(new Facture("1",
               LocalDate.now().toString(), clientList.get(0).id, adrsFacturactionList.get(0).id, 
               modeDeRList.get(0).id, "0", "0", "0", "", "0", "", this.login.getUsername(), "", ""));
       factList.get(factList.size()-1).insert=true;
       setFData(factList.get(factList.size()-1));
       currentFactindex=factList.size()-1;
      
       
    }
    void goToClientGestPage(){
        
    }

    private boolean allDialogAreClosed() {
        
        return (dialogList.isEmpty());
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------//
            @FXML
    void addClientBtnClicked(MouseEvent event) throws SQLException, IOException {
      if(AllGood()){
        if (observation.getText().isEmpty())facture.setObservation("");else facture.setObservation(observation.getText());
        if(facture.insert){

          if(dbConnexion.insertFact(facture))  {
          for(Table t:tabList){
              t.idF_=facture.id;
              if(t.opType==0){
                  //insert
                  dbConnexion.insertligneFact(t);
              }else{
                if(t.opType==1)  {
                  //up  
                  dbConnexion.insertligneFact(t);
                }else{
                    //del
                    //dbConnexion.delLigneFact(t);
                }
              }
                
            }   
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
              
          }
        }else{
          if(dbConnexion.updateFact(facture))  {
                        for(Table t:tabList){
              t.idF_=facture.id;
              if(t.opType==0){
                  //insert
                  dbConnexion.insertligneFact(t);
              }else{
                if(t.opType==1)  {
                  //up  
                  dbConnexion.updateligneFact(t);
                }else{
                    //del
                    dbConnexion.delLigneFact(t);
                }
              }
                
            }
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
          }
        }
      }else{
          snackBar.show("Vous devez remlir ou selectioner Tous les champs svp !", 2000); 
      }
      
    }
    boolean AllGood(){
       if(!deposeAgent.getText().isEmpty()&&!remiseFieled.getText().isEmpty()&&
                !ttcFieled.getText().isEmpty()&&!tvaFieled.getText().isEmpty()&&!htFieled.getText().isEmpty()){
           facture= factList.get(currentFactindex);
            //facture.setId(id);
            facture.setDate(dateFieled.getValue().toString()+" 00:00:00");
            facture.setNumClient(id);
            facture.setTva(tvaFieled.getText());
            facture.setTtc(ttcFieled.getText());
            facture.setHt(htFieled.getText());
            facture.setModeR(modP);
            facture.setAdrsFact(adrsF);
            return true;
            
        }
        return false;
    }
    void eraceAll(){
        nameFieled.setText("");
        adrsFieled.setText("");
        deposeAgent.setText("");
        remiseFieled.setText("");
        ttcFieled.setText("");
        tvaFieled.setText("");
        htFieled.setText("");
        observation.setText("");
        dateFieled.setValue(LocalDate.now());
        chooseClientCombo.getEditor().setText("");
        modePCombo.getEditor().setText("");
        adrsCombo.getEditor().setText("");
        if(clientList.size()!=0)
        id=clientList.get(0).getId();
                if(modeDeRList.size()!=0)
        modP=modeDeRList.get(0).getId();
                        if(adrsFacturactionList.size()!=0)
        adrsF=adrsFacturactionList.get(0).getId();
                
        
        
    }
        @FXML
    void searchClient(KeyEvent event) throws SQLException, IOException{
      visibleClientList=new ArrayList<>() ;
      chooseClientCombo.getItems().clear();
      String t=chooseClientCombo.getEditor().getText();
       if(t.isEmpty()){
           t="";
       }
        for(Client a:clientList){
            if(a.getId().contains(t)||a.getName().contains(t)||a.getFirstName().contains(t)
                    ||a.getAdrs().contains(t)||a.getTown().contains(t)
                    ||a.getEmail().contains(t)||a.getCountry().contains(t)||a.getSociete().contains(t)){
                visibleClientList.add(a);
               
            chooseClientCombo.getItems().add(a.getId());
                setClientData(a);
            }
}
        


        
    }

    void setClientData(Client client) throws SQLException, IOException{
        nameFieled.setText(client.getName()+" "+client.getFirstName());
        adrsFieled.setText(client.getAdrs());
        id=client.getId();
        adrsFacturactionList=dbConnexion.getAllAdressFact( id);
        adrsCombo.getItems().clear();
        for(AdresseDeFacturation a:adrsFacturactionList) adrsCombo.getItems().add(a.getId()+" "+a.lib);
        adrsCombo.getSelectionModel().selectFirst();
       //chooseClientCombo.getEditor().setText(id);*/
    }  
    
    @FXML
    void erraceBtnClicked(MouseEvent event) {
        eraceAll();
        //error_report.setText("Tout est effacés! Les champs ci-desssus sont obligatiores");
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
    void imprimeBtnClicked(MouseEvent event) {

    }

    @FXML
    void precedantClicked(MouseEvent event) throws SQLException, IOException {
        if(currentFactindex>0){
        setFData(factList.get(--currentFactindex));
        }
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
    void searchAdrs(KeyEvent event) {
      vAdrsFacturactionList=new ArrayList<>() ;
      adrsCombo.getItems().clear();
      String t=adrsCombo.getEditor().getText();
       if(t.isEmpty()){
           t="";
       }
        for(DbConnexion.AdresseDeFacturation a:adrsFacturactionList){
            if(a.getId().contains(t)||a.getLib().contains(t)){
                vAdrsFacturactionList.add(a);
                adrsCombo.getItems().add(a.getId()+" "+a.lib);
            }
}
    }


    @FXML
    void searchModeP(KeyEvent event) {
      vmodeDeRList=new ArrayList<>() ;
      modePCombo.getItems().clear();
      String t=modePCombo.getEditor().getText();
       if(t.isEmpty()){
           t="";
       }
        for(DbConnexion.ModeDeReglement a:modeDeRList){
            if(a.getId().contains(t)||a.getLib().contains(t)){
                vmodeDeRList.add(a);
                modePCombo.getItems().add(a.getId()+" "+a.getLib());
            }
}
    }

    @FXML
    void suivantClicked(MouseEvent event) throws SQLException, IOException {
        if(currentFactindex<factList.size()-1){
        setFData(factList.get(++currentFactindex));
        }
    }

        @FXML
    void delProBtnClicked(MouseEvent event) throws SQLException, IOException {
        if(currentFactindex==factList.size()-1){
        tabList.remove(tabIndex);
        initTable();
        loadTableData();
        facture=factList.get(currentFactindex);
        Table  t=tabList.get(tabIndex);
        facture.ht=String.valueOf(Double.parseDouble(facture.ht)-Double.parseDouble(t.pv_));
        facture.tva=String.valueOf(Double.parseDouble(facture.tva)-Double.parseDouble(t.tva_));
        facture.ttc=String.valueOf(Double.parseDouble(facture.ht)+Double.parseDouble(facture.tva));
        remiseFieled.setText(String.valueOf(Double.parseDouble(deposeAgent.getText())-Double.parseDouble(facture.ttc)));
        }
        else{
            tabList.get(tabIndex).opType=3;
            Table t_=tabList.get(tabIndex);
            facture.ht=String.valueOf(Double.parseDouble(t_.qte_)*(Double.parseDouble(facture.ht)-Double.parseDouble(t_.pv_)));
            facture.tva=String.valueOf(Double.parseDouble(t_.qte_)*(Double.parseDouble(facture.tva)-Double.parseDouble(t_.tva_)));
            facture.ttc=String.valueOf(Double.parseDouble(facture.ht)+Double.parseDouble(facture.tva));
            remiseFieled.setText(String.valueOf(Double.parseDouble(deposeAgent.getText())-Double.parseDouble(facture.ttc)));
             if(AllGood()){
        if (observation.getText().isEmpty())facture.setObservation("");else facture.setObservation(observation.getText());
        if(facture.insert){
            
          if(dbConnexion.insertFact(facture))  {
          for(Table t:tabList){
              t.idF_=facture.id;
              if(t.opType==0){
                  //insert
                  dbConnexion.insertligneFact(t);
              }else{
                if(t.opType==1)  {
                  //up  
                  dbConnexion.insertligneFact(t);
                }else{
                    //del
                    //dbConnexion.delLigneFact(t);
                }
              }
                
            }   
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
              
          }
        }else{
          if(dbConnexion.updateFact(facture))  {
                        for(Table t:tabList){
              t.idF_=facture.id;
              if(t.opType==0){
                  //insert
                  dbConnexion.insertligneFact(t);
              }else{
                if(t.opType==1)  {
                  //up  
                  dbConnexion.updateligneFact(t);
                }else{
                    //del
                    dbConnexion.delLigneFact(t);
                }
              }
                
            }
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
          }
        }
      }
            
        }

    }
     private void initTable() {
         if(q++==0){
idCol = new JFXTreeTableColumn<>("Reff");
        idCol.setPrefWidth(130);
        idCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String>
                param) -> param.getValue().getValue().id);
        societeCol = new JFXTreeTableColumn<>("Libellé");
        societeCol.setPrefWidth(220);
        societeCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String>
                param) -> param.getValue().getValue().name);
        nameCol = new JFXTreeTableColumn<>("Qte");
        nameCol.setPrefWidth(70);
        nameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) ->
                param.getValue().getValue().qte);
        firstNameCol = new JFXTreeTableColumn<>("Prix vente");        
        firstNameCol.setPrefWidth(70);
        firstNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) ->
                param.getValue().getValue().pv);
        tvaCol = new JFXTreeTableColumn<>("TVA");        
        tvaCol.setPrefWidth(70);
        tvaCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) ->
                param.getValue().getValue().tva);
        clientListTable.getColumns().addAll(idCol,societeCol,nameCol,firstNameCol,tvaCol);
        clientListTable.setPrefWidth(560);
        clientListTable.setShowRoot(false);
        clientListTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        clientListTable.getSelectionModel().selectFirst();
        clientListTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem<FactureFournisseurFXMLController.Table>>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends TreeItem<FactureFournisseurFXMLController.Table>> c) {
              tabIndex=clientListTable.getSelectionModel().getSelectedIndex();
                //setClientData(visibleClientList.get(index));
                
               
            }
        });
         }

                }

     private void loadTableData() {
         if(tabList!=null) {  
        ObservableList<Table> listFoundIndex = FXCollections.observableArrayList();
        
        for(Table index : tabList) {
            listFoundIndex.add(index);
        }

        final TreeItem<Table> treeItem = new RecursiveTreeItem<>(listFoundIndex, RecursiveTreeObject::getChildren);
        try {
            clientListTable.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
        clientListTable.getSelectionModel().selectFirst();
        tabIndex=0;
         }
    }
       static class Table extends RecursiveTreeObject<Table> {
        StringProperty id,name ,qte,pv,tva,idF;
        String id_,name_ ,qte_,pv_,tva_,idF_,id_l,oldQte;
        
        int opType;
        

        public Table(String id_, String nam,String FirstNam,String soc,String tva1) {
            id = new SimpleStringProperty(id_);
            name = new SimpleStringProperty(nam);
            qte=new SimpleStringProperty(FirstNam);
            pv=new SimpleStringProperty(soc);
            tva=new SimpleStringProperty(tva1);
            this.id_ = id_;
            this.name_ = nam;
            this.qte_ = FirstNam;
            this.pv_ = soc;
            this.tva_ =tva1;
            this.idF_ = factList.get(currentFactindex).id;
        }

        public void setIdF(StringProperty idF) {
            this.idF = idF;
        }
        
    }
                 @FXML
       void searchProd(KeyEvent event) {
           
    }
            @FXML
    void intTyped(KeyEvent event) {
            JFXTextField typeFieled=(JFXTextField)event.getSource();
    String string="";
    for(char ch:typeFieled.getText().toCharArray()){
           if ((ch<='9'&&ch>='0'))
           {string+=String.valueOf(ch);}
        typeFieled.setText(string);
        typeFieled.positionCaret(string.length());
    }
    }   
       void setFData(Facture f) throws SQLException,IOException{
           factNumLabel.setText("Facture Recp N° 0000"+f.id);
           String t=f.numClient;
           int i=0;
            for(Client a:clientList){
            if(a.getId().equals(t)){   
                id=t;
                chooseClientCombo.getSelectionModel().select(i);
                setClientData(a);
                break;
            }
            i++;
           
       }
            t=f.modeR;
            i=0;
            for(DbConnexion.ModeDeReglement a:modeDeRList){
            if(a.getId().equals(t)){   
                modP=t;
                modePCombo.getSelectionModel().select(i);
                break;
            }
            i++;
           
       }
            t=f.adrsFact;
            i=0;
           for(DbConnexion.AdresseDeFacturation a:adrsFacturactionList){
            if(a.getId().equals(t)){   
                adrsF=t;
                adrsCombo.getSelectionModel().select(i);
                break;
            }
            i++;
           
       }
           if(Double.parseDouble(f.remise)>0)
            remiseFieled.setText(f.remise);
           else  remiseFieled.setText("0");

            if(f.date.equals("")) dateFieled.setValue(LocalDate.now()); 
            else dateFieled.setValue(LocalDate.parse(f.date.substring(0,10)));
            observation.setText(f.observation);
           // if(Double.parseDouble(f.remise)<0) deposeAgent.setText(f.getDeposed());
           if(Double.parseDouble(f.getDeposed())<=0) deposeAgent.setText("0");
           else deposeAgent.setText(f.getDeposed());
           if(deposeAgent.getText().equals(remiseFieled.getText())&&!remiseFieled.getText().equals("0"))
           {
               deposeAgent.setText("0");
               remiseFieled.setText("0");
           }
            tabList=dbConnexion.getLigneFactForFact(f.id);
            htFieled.setText(String.valueOf(DbConnexion.ht));
            ttcFieled.setText(String.valueOf(DbConnexion.ttc));
            tvaFieled.setText(String.valueOf(DbConnexion.tva));
                       if(f!=factList.get(factList.size()-1)){
              
           if(f.payed) {
              imgBoxHappy.setVisible(true); 
              imgBox.setVisible(false);
           }else{
               imgBox.setVisible(true);
               imgBoxHappy.setVisible(false);
           }
           }else{
               imgBox.setVisible(false);
               imgBoxHappy.setVisible(false);
           }
            f.ht=String.valueOf(DbConnexion.tva);
            f.ttc=String.valueOf(DbConnexion.ttc);
            f.tva=String.valueOf(DbConnexion.ht);
            dbConnexion.updateFact(f);
            initTable();
            loadTableData();
       }
        @FXML
       void addProBtnClicked(MouseEvent event) throws SQLException, IOException {
           if(quantiteFieled.getText().isEmpty()|| ProdCombo.getSelectionModel().getSelectedItem()==null)
           {snackBar.show("Vous devez remlir la qantité et secltioner un produit !", 3000); return;}
           Produit X= produitList.get(ProdCombo.getSelectionModel().getSelectedIndex());
           Table T=new Table(X.reff,X.getLib(),quantiteFieled.getText() , X.prixHT, X.tva);
           facture=factList.get(currentFactindex);
           T.idF_=facture.id;
           T.opType=0;
        facture.ht=String.valueOf(Double.parseDouble(T.qte_)*Double.parseDouble(T.pv_)+Double.parseDouble(facture.ht));
        facture.tva=String.valueOf(Double.parseDouble(T.qte_)*Double.parseDouble(T.tva_)+Double.parseDouble(facture.tva));
        facture.ttc=String.valueOf(Double.parseDouble(facture.ht)+Double.parseDouble(facture.tva));
        facture.remise=String.valueOf(Double.parseDouble(deposeAgent.getText())-Double.parseDouble(facture.ttc));
        
        tabList.add(T);
        System.out.println(tabList.size());
            //setFData(facture);
if(AllGood()){
    double ttc=0,tva=0,ht=0;
        if (observation.getText().isEmpty())facture.setObservation("");else facture.setObservation(observation.getText());
        if(facture.insert){
 System.out.println(tabList.size());
          if(dbConnexion.insertFact(facture))  {
             
          for(Table t:tabList){
              t.idF_=facture.id;
              if(t.opType==0){
                  //insert
                  dbConnexion.insertligneFact(t);
                  ht+=Double.parseDouble(t.pv_)*Double.parseDouble(t.qte_);
                  tva+=Double.parseDouble(t.tva_)*Double.parseDouble(t.qte_);
                  ttc=ht+tva;
              }else{
                if(t.opType==1)  {
                  //up  
                  ht+=Double.parseDouble(t.pv_)*Double.parseDouble(t.qte_);
                  tva+=Double.parseDouble(t.tva_)*Double.parseDouble(t.qte_);
                  ttc=ht+tva;
                  dbConnexion.insertligneFact(t);
                }else{
                    //del
                    //dbConnexion.delLigneFact(t);
                }
              }
                
            }   
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
              
          }
        }else{
          if(dbConnexion.updateFact(facture))  {
                        for(Table t:tabList){
              t.idF_=facture.id;
              if(t.opType==0){
                  //insert
                  ht+=Double.parseDouble(t.pv_)*Double.parseDouble(t.qte_);
                  tva+=Double.parseDouble(t.tva_)*Double.parseDouble(t.qte_);
                  ttc=ht+tva;
                  dbConnexion.updateligneFact(t);
                  dbConnexion.insertligneFact(t);
              }else{
                if(t.opType==1)  {
                  //up  
                  ht+=Double.parseDouble(t.pv_)*Double.parseDouble(t.qte_);
                  tva+=Double.parseDouble(t.tva_)*Double.parseDouble(t.qte_);
                  ttc=ht+tva;
                  dbConnexion.updateligneFact(t);
                  
                }else{
                    //del
                  ht-=Double.parseDouble(t.pv_)*Double.parseDouble(t.qte_);
                  tva-=Double.parseDouble(t.tva_)*Double.parseDouble(t.qte_);
                  ttc=ht+tva;
                  dbConnexion.updateligneFact(t);
                    dbConnexion.delLigneFact(t);
                }
              }
                
            }
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
          }
        }
        facture.ttc=String.valueOf(ttc);
        facture.ht=String.valueOf(ht);
        facture.tva=String.valueOf(tva);
        facture.remise=String.valueOf(Double.parseDouble(deposeAgent.getText())-ttc);
        dbConnexion.updateFact(facture);
      }
           
    }
            @FXML
    void editProBtnClicked(MouseEvent event) throws SQLException, IOException {
           /*    Produit X= produitList.get(ProdCombo.getSelectionModel().getSelectedIndex());
           Table T=new Table(ProdCombo.getValue(),X.getLib(),quantiteFieled.getText() , X.prixHT, X.tva);
           
           facture=factList.get(currentFactindex);
           T.idF_=facture.id;
           T.opType=1;
           Table t_=tabList.get(tabIndex);
           T.oldQte=String.valueOf(Integer.parseInt(T.qte_)-Integer.parseInt(t_.qte_));
           
          /*  facture.ht=String.valueOf(Double.parseDouble(t_.qte_)*(Double.parseDouble(facture.ht)-Double.parseDouble(t_.pv_)));
            facture.tva=String.valueOf(Double.parseDouble(t_.qte_)*(Double.parseDouble(facture.tva)-Double.parseDouble(t_.tva_)));
            facture.ttc=String.valueOf(Double.parseDouble(facture.ht)+Double.parseDouble(facture.tva));
            remiseFieled.setText(String.valueOf(Double.parseDouble(deposeAgent.getText())-Double.parseDouble(facture.ttc)));
           tabList.set(tabIndex, T);
           facture.ht=String.valueOf(Double.parseDouble(facture.ht)+Double.parseDouble(T.pv_));
        facture.tva=String.valueOf(Double.parseDouble(facture.tva)+Double.parseDouble(T.tva_));
        facture.ttc=String.valueOf(Double.parseDouble(facture.ht)+Double.parseDouble(facture.tva));
        remiseFieled.setText(String.valueOf(Double.parseDouble(deposeAgent.getText())-Double.parseDouble(facture.ttc)));
        tabList.add(T); 
        setFData(facture);
          dbConnexion.updateligneFact(T);
                              Stage pStage = (Stage) parent.getScene().getWindow();
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(getClass().getResource("factureFournisseurFXML.fxml"));
       fxmlLoader.load();
       FactureFournisseurFXMLController welcomeControler = fxmlLoader.getController();
       welcomeControler.inisialize(dbConnexion,login);
       Stage stage = new Stage();
       stage.setScene(new Scene(fxmlLoader.getRoot()/*, width, heigh));
       stage.setResizable(false);
       stage.setTitle("");
       stage.show();
       pStage.close(); 
        */  
    } 

      
}
