
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
import gstioncomercialenadjem.DbConnexion.Reglement;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static sun.security.jgss.GSSUtil.login;

/**
 * FXML Controller class
 *
 * @author Manno
 */
public class ReglementListFXMLController implements Initializable {
        public DbConnexion.Login login;
    DbConnexion dbConnexion;
    int index=0;
    public ArrayList<DialogControllerInterface> dialogList;
    static ArrayList<DbConnexion.Client>clientList,visibleClientList;
    static ArrayList<DbConnexion.Facture>factList,visibleFactList;
    static ArrayList<DbConnexion.Reglement>reglementList,visibleReglementList;
    static int currentFactindex=0;
    static ArrayList<DbConnexion.ModeDeReglement>modeDeRList,vmodeDeRList;
    static ArrayList<Table> tabList;
    static String id="",dateR="",idM="",remis="0";
    int k=0;
    static Facture facture;

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
    private JFXTreeTableView clientListTable;

    @FXML
    private JFXDatePicker dateFieled,dateFieledDebut;

    private JFXTreeTableColumn<Table, String> idCol,dateCol,numFactCol,clientCol,reglementCol,modeDeRCol,saisieParCol,saisieLeCol,observationCol;
    int tabIndex=0;
    String modP="1",factId,reg=null;
    String begin,end;
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

    void inisialize(DbConnexion dbConnexion, DbConnexion.Login login) throws SQLException {
        this.dbConnexion=dbConnexion;
        snackBar = new JFXSnackbar(parent);
        dialogList=new ArrayList();
        defineUser(login);
        clientMenuItem.setStyle("-fx-background-color:  #ffd6ff ;");  
    }
    void defineUser(DbConnexion.Login login) throws SQLException{
      this.login=login;  
      username_label.setAlignment(Pos.TOP_RIGHT);
      username_label.setText("Salut "+login.getUsername()+" !"); 
      //eraceAll();
      /*reglementList=dbConnexion.getAllReglement();
      clientList=dbConnexion.getAllClient();
      visibleClientList=clientList;      
        for(Client a:clientList){
            chooseClientCombo.getItems().add(a.getId());
        }
       chooseClientCombo.getSelectionModel().selectFirst();
       chooseClientCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
                id=newValue.toString(); 
               for(Client a:visibleClientList){
                   if(a.getId().equals(id)){
                       try {
                           setClientData(a);
                       } catch (SQLException ex) {
                           Logger.getLogger(FicheClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
        modePCombo.getSelectionModel().selectFirst();
        modePCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
                modP=newValue.toString(); 
              /* for(ModeDeReglement a:modeDeRList){
                   if((a.getId()+" "+a.lib).equals(modP)){
                   modP=a.id;   
                       break;
                   }
                       
               }
            }else{
          // eraceAll();
           //id="";
       }
            }
        });
        factList=dbConnexion.getAllFactures();
        visibleFactList=new ArrayList<>();
        ProdCombo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
                factId=newValue.toString();
               visibleReglementList=new ArrayList();
                    try {
                        if(ProdCombo.getSelectionModel().getSelectedIndex()>=0)
                        setFData(clientList.get(chooseClientCombo.getSelectionModel().getSelectedIndex()),
                                visibleFactList.get(ProdCombo.getSelectionModel().getSelectedIndex()));
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(FicheClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                           initTable();
           loadTableData();
            }else{
                    factId=null;
           //eraceAll();
         
       }
            }
        });
        ProdCombo.getSelectionModel().selectFirst();
        setClientData(clientList.get(0));*/
      modeDeRList=dbConnexion.getAllModeDeRlist();
      eraceAll();
       
    }
    void goToClientGestPage(){
        
    }

    private boolean allDialogAreClosed() {
        
        return (dialogList.isEmpty());
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------//
            @FXML
    void addClientBtnClicked(MouseEvent event) throws SQLException, IOException {

      
    }
    int AllGood(){
      /*  if(factId!=null){
        if (Double.parseDouble(visibleFactList.get(ProdCombo.getSelectionModel().getSelectedIndex()).remise)<0){
       if(!deposeAgent.getText().isEmpty()){
            dateR=dateFieled.getValue().toString()+" 00:00:00";
            double i=Double.parseDouble(deposeAgent.getText())+Double.parseDouble(visibleFactList.get(ProdCombo.getSelectionModel().getSelectedIndex()).remise);
            if(i>0) {remiseFieled.setText(String.valueOf(i));reg=String.valueOf
        (Double.parseDouble(visibleFactList.get(ProdCombo.getSelectionModel().getSelectedIndex()).remise)*-1);}
            else {remiseFieled.setText("0"); reg=deposeAgent.getText();}
            remis=String.valueOf(i);
            return 1;  
        }
        }else return -1;
        }else{
           dateR=dateFieled.getValue().toString()+" 00:00:00";
          if(clientList.get(chooseClientCombo.getSelectionModel().getSelectedIndex()).dete>=Double.parseDouble
        (deposeAgent.getText())){
                 double i=Double.parseDouble(deposeAgent.getText())-clientList.get(chooseClientCombo.getSelectionModel().getSelectedIndex()).dete;           
                remis=String.valueOf(i); remiseFieled.setText("0");reg=deposeAgent.getText();
             
             
          }else{ 
              double i=Double.parseDouble(deposeAgent.getText())-clientList.get(chooseClientCombo.getSelectionModel().getSelectedIndex()).dete;
            remis=String.valueOf(i); 
            remiseFieled.setText(String.valueOf(i));
            reg=String.valueOf(clientList.get(chooseClientCombo.getSelectionModel().getSelectedIndex()).dete);
            

            
        } 
          return -2;
        }*/
        return 0;
    }
    void eraceAll() throws SQLException{
        //nameFieled.setText("");
        //adrsFieled.setText("");
/*        deposeAgent.setText("");
        remiseFieled.setText("");
        observation.setText("");
        dateFieled.setValue(LocalDate.now());
        chooseClientCombo.getEditor().setText("");
        modePCombo.getEditor().setText("");
       // adrsCombo.getEditor().setText("");
        if(clientList.size()!=0)
        id=clientList.get(0).getId();
                if(modeDeRList.size()!=0)
        modP=modeDeRList.get(0).getId();*/
                        //if(adrsFacturactionList.size()!=0)
        //drsF=adrsFacturactionList.get(0).getId();
        visibleReglementList=dbConnexion.getAllReglement();
                dateFieled.setValue(LocalDate.now());
                if(visibleReglementList.size()==0)
                    dateFieledDebut.setValue(LocalDate.now().minusYears(1));
                else{
                    dateFieledDebut.setValue(LocalDate.parse(visibleReglementList.get(visibleReglementList.size()-1).date.substring(0,10)));
                }
        initTable();
        loadTableData();
        
    }
     /*   @FXML
    void searchClient(KeyEvent event) throws SQLException {
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

    void setClientData(Client client) throws SQLException{
        nameFieled.setText(client.getName()+" "+client.getFirstName());
        adrsFieled.setText(client.getAdrs());
        id=client.getId();
        factId=null;
        dateFieled.setValue(LocalDate.now());
        emailFieled1.setText(client.getEmail());
        mobileFieled.setText(client.getMobile());
        ProdCombo.getItems().clear();
       visibleFactList=dbConnexion.getAllFactures(id);
              imgBoxHappy.setVisible(false); 
              imgBox.setVisible(false); 
       clientPayed.setVisible(true);
          clientPayed.setVisible(true);
          clientdidntPay.setVisible(false);
          double dete=0;
       for(Facture f:visibleFactList) {ProdCombo.getItems().add(f.id);
       f.getDeposed();
      
       if(!f.payed){
          clientPayed.setVisible(false);
          clientdidntPay.setVisible(true);
          dete+=-1*Double.parseDouble(f.remise);
       }
       }
       if(dete>0)
       clientdidntPay.setText("Ce client  a des dêtes de "+dete+" DA");
       client.dete=dete;
       
        
    }  */
    
    @FXML
    void erraceBtnClicked(MouseEvent event) throws SQLException {
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
    void precedantClicked(MouseEvent event) throws SQLException {
        if(currentFactindex>0){
//        setFData(factList.get(--currentFactindex));
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



   /* @FXML
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
    }*/

    @FXML
    void suivantClicked(MouseEvent event) throws SQLException {
        if(currentFactindex<factList.size()-1){
//        setFData(factList.get(++currentFactindex));
        }
    }

 private void initTable() {
       if(k++==0){
        numFactCol = new JFXTreeTableColumn<>("Num Facture");
        //numFactCol.setPrefWidth(50);
        numFactCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String>
                param) -> param.getValue().getValue().numFact);
        
        clientCol = new JFXTreeTableColumn<>("NumClient");
        //clientCol.setPrefWidth(50);
        clientCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String>
                param) -> param.getValue().getValue().client);
        idCol = new JFXTreeTableColumn<>("Num");
        //idCol.setPrefWidth(50);
        idCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String>
                param) -> param.getValue().getValue().id);
        
        dateCol = new JFXTreeTableColumn<>("Date");
        //dateCol.setPrefWidth(100);
        dateCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String>
                param) -> param.getValue().getValue().date);
        modeDeRCol = new JFXTreeTableColumn<>("Mode De Reglement ");
        //modeDeRCol.setPrefWidth(150);
        modeDeRCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) ->
                param.getValue().getValue().modeDeR);
        saisieLeCol = new JFXTreeTableColumn<>("Saisie Le");        
        //saisieLeCol.setPrefWidth(100);
        saisieLeCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) ->
                param.getValue().getValue().saisieLe);
        saisieParCol = new JFXTreeTableColumn<>("Saisie Par");        
        //saisieParCol.setPrefWidth(120);
        saisieParCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) ->
          param.getValue().getValue().saisiePar);
                observationCol = new JFXTreeTableColumn<>("Observation");        
        //observationCol.setPrefWidth(200);
        observationCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) ->
                param.getValue().getValue().observation);
                reglementCol = new JFXTreeTableColumn<>("reglement");        
        //observationCol.setPrefWidth(200);
        reglementCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) ->
                param.getValue().getValue().reglement);        
        clientListTable.getColumns().addAll(idCol,clientCol,numFactCol,dateCol,reglementCol,modeDeRCol,saisieParCol,saisieLeCol,observationCol);
        clientListTable.setPrefWidth(956);
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
         tabList=new ArrayList<>();
         for(Reglement r:visibleReglementList){
             tabList.add(new Table(r));
         }
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
    
       static class Table extends RecursiveTreeObject<Table> {
        StringProperty id,date,modeDeR,saisiePar,saisieLe,observation,reglement,numFact,client;
        public Table(Reglement r) {
            id = new SimpleStringProperty(r.getId());
            date = new SimpleStringProperty(r.getDate().substring(0, 11));
            modeDeR=new SimpleStringProperty(modeDeRList.get(Integer.parseInt(r.getModeDeR())-1).getLib());
            saisiePar=new SimpleStringProperty(r.getSaisiePar());
            saisieLe=new SimpleStringProperty(r.getSaisieLe());
            observation=new SimpleStringProperty(r.getObservation());
            reglement=new SimpleStringProperty(r.reglement);
            numFact=new SimpleStringProperty(r.numFacture);
            client=new SimpleStringProperty(r.numClient);
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
  /*  void setFData(Client c,Facture f) throws SQLException{
        visibleReglementList=new ArrayList();
        f.getDeposed();

            if(f.numClient.equals(c.getId())){
                                   if(f.payed) {
              imgBoxHappy.setVisible(true); 
              imgBox.setVisible(false);
           }else{
               imgBox.setVisible(true);
               imgBoxHappy.setVisible(false);
           
           }
            for(Reglement a: reglementList){
                if(a.numFacture.equals(f.id)){
                    visibleReglementList.add(a);
                }
            }

}

       }*/
        @FXML
    void addProBtnClicked(MouseEvent event) throws SQLException, IOException {
     /*   int w=AllGood();
        if(w==-2){
                    String  obs="";
//        if (!observation.getText().isEmpty()) obs=observation.getText();
        Reglement reglement=new Reglement( null,dateR, modP, null, login.username, null, obs,reg);
        if(dbConnexion.addReglementInAll(reglement,remis,id)){
            Stage pStage = (Stage) parent.getScene().getWindow();  
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ficheClientFXML.fxml"));
            fxmlLoader.load();
            FicheClientFXMLController welcomeControler = fxmlLoader.getController();
            welcomeControler.inisialize(dbConnexion,login);
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.getRoot()/*, width, heigh));
            stage.setResizable(false);
            stage.setTitle("");
            stage.show();
            pStage.close();  
        }else{
            snackBar.show("Ce reglement ne peut pas etre ajouté!", 2000);
        }
        }else{
               if(w!=0){
         if(w!=-1){        
          String  obs="";
//        if (!observation.getText().isEmpty()) obs=observation.getText();
        Reglement reglement=new Reglement( null,dateR, modP, factId, login.username, null, obs,reg);
        if(dbConnexion.addReglement(reglement,remis)){
            Stage pStage = (Stage) parent.getScene().getWindow();  
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ficheClientFXML.fxml"));
            fxmlLoader.load();
            FicheClientFXMLController welcomeControler = fxmlLoader.getController();
            welcomeControler.inisialize(dbConnexion,login);
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.getRoot(), width, heigh));
            stage.setResizable(false);
            stage.setTitle("");
            stage.show();
            pStage.close();
        
        }else{
            snackBar.show("Ce reglement ne peut pas etre ajouté!", 2000);
        }
        }else{
            snackBar.show("Cette Facture est déja réglée", 2000);
        }
      }else{
          snackBar.show("Vous devez remlir ou selectioner Tous les champs svp !", 2000); 
      }
    }
*/
     if(dateFieled.getValue().toString().isEmpty()||dateFieledDebut.getValue().toString().isEmpty())
     {
         snackBar.show("Vous devez remlir ou selectioner Tous les champs svp !", 2000);
         return;
     }
     begin=dateFieledDebut.getValue().toString()+" 00:00:00";
     end=dateFieled.getValue().toString()+" 00:00:00";
     visibleReglementList=dbConnexion.gatAllReglementByDate(begin, end);
     initTable();
     loadTableData();
              
    }
        @FXML
    void goToReglement(MouseEvent event) {
        

    }


      
           
    


      
}


