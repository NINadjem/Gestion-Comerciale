
package gstioncomercialenadjem;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import gstioncomercialenadjem.DbConnexion.Client;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
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


public class SearchClientFXMLController implements Initializable {
    public DbConnexion.Login login;
    DbConnexion dbConnexion;
    int index=0;
    public ArrayList<DialogControllerInterface> dialogList;
    @FXML
    private JFXTreeTableView clientListTable;

    @FXML
    private JFXTextField searchClientFieled;
    
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
    private Label codePostalFieled;

    @FXML
    private Label telFieled;

    @FXML
    private Label faxFieled;

    @FXML
    private Label mobileFieled;

    @FXML
    private Label emailFieled;

    @FXML
    private Label typeFieled;

    @FXML
    private Label exmptTvaBtn;

    @FXML
    private Label livreMemAdrsBtn;

    @FXML
    private Label factureMemAdrsBtn;

    @FXML
    private Label lastEditDateLabel;

    @FXML
    private Label InsertDateLabel;

    @FXML
    private Label observationFieled;
    private JFXTreeTableColumn<Table, String> idCol,firstNameCol,nameCol, societeCol;

    ArrayList<DbConnexion.Client>clientList,visibleClientList;
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
      }else{if(box==fournisseurMenuItem){
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
    void erraceBtnClicked(MouseEvent event) {
        eraceAll();
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
        clientMenuItem.setStyle("-fx-background-color:  #ffd6ff ;");  

    }
    void defineUser(DbConnexion.Login login) throws SQLException{
      this.login=login;  
      username_label.setAlignment(Pos.TOP_RIGHT);
      username_label.setText("Salut "+login.getUsername()+" !");
      eraceAll();
      clientList=dbConnexion.getAllClient();
        visibleClientList=clientList;
        initTable();
        loadTableData();
        if(clientList.size()<=0)
        {//error_report.setText("La Liste des clients est vide");
                initTable();
        loadTableData();
        }
               else 
            setClientData(visibleClientList.get(0));
            
    }
    void goToClientGestPage(){
        
    }

    private boolean allDialogAreClosed() {
        
        return (dialogList.isEmpty());
    }
          /*  @FXML
   void addClientBtnClicked(MouseEvent event) throws SQLException {
        if(true){
        boolean id_=dbConnexion.deleteClient(id);
        if(id_){    
       clientList=dbConnexion.getAllClient();
        visibleClientList=clientList;
      /*  if(clientList.size()<=0)error_report.setText("La Liste des clients est vide");
                else  setClientData(visibleClientList.get(0));
        //chooseClientCombo.getItems().clear();        
        for(DbConnexion.Client a:clientList){
          //  chooseClientCombo.getItems().add(a.getId());
        }
        //chooseClientCombo.getSelectionModel().selectFirst();
        eraceAll();
             //error_report.setText("La Suppression a été effectué");
         } else{
             //error_report.setText("La Suppression immpossible! risque de violance des contraintes");
         } 
        }   
        }*/

   
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
        exmptTvaBtn.setText("");
        factureMemAdrsBtn.setText("");
        livreMemAdrsBtn.setText("");
        lastEditDateLabel.setText("");
        InsertDateLabel.setText("");
//        chooseClientCombo.getEditor().setText("");
        
    }
        @FXML
    void searchClient(KeyEvent event) {
      visibleClientList=new ArrayList<>() ;
      String t=searchClientFieled.getText();
       if(t.isEmpty()){
           t="";
       }
        for(DbConnexion.Client a:clientList){
            if(a.getId().contains(t)||a.getName().contains(t)||a.getFirstName().contains(t)
                    ||a.getAdrs().contains(t)||a.getTown().contains(t)
                    ||a.getEmail().contains(t)||a.getCountry().contains(t)||a.getSociete().contains(t)){
                visibleClientList.add(a);
               
            //chooseClientCombo.getItems().add(a.getId());
                setClientData(a);
            }
      
}
        loadTableData();


        
    }
    void setClientData(DbConnexion.Client client){
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
        typeFieled.setText(client.getType());
        observationFieled.setText(client.getObservation());
        exmptTvaBtn.setText(DbConnexion.Client.BooleanFrenchValue(client.getExmptTvaValue()));
        factureMemAdrsBtn.setText(DbConnexion.Client.BooleanFrenchValue(client.getFacture_meme_adrsValue()));
        livreMemAdrsBtn.setText(DbConnexion.Client.BooleanFrenchValue(client.getLivre_meme_adrsValue()));    
        lastEditDateLabel.setText("Dérniére modification le "+client.getModifiedAt()+" par : "+client.getModifiedBy());
        InsertDateLabel.setText("Ajouter le "+client.getAddedAt()+" par : "+client.getAddedBy());
        id=client.getId();
        //chooseClientCombo.getEditor().setText(id);
    }
       private void initTable() {
        idCol = new JFXTreeTableColumn<>("NumClient");
        idCol.setPrefWidth(100);
        idCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String>
                param) -> param.getValue().getValue().id);
        societeCol = new JFXTreeTableColumn<>("Sociéte");
        societeCol.setPrefWidth(200);
        societeCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String>
                param) -> param.getValue().getValue().societe);
        nameCol = new JFXTreeTableColumn<>("Nom");
        nameCol.setPrefWidth(200);
        nameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) ->
                param.getValue().getValue().name);
        firstNameCol = new JFXTreeTableColumn<>("Prénom");        
        firstNameCol.setPrefWidth(200);
        firstNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) ->
                param.getValue().getValue().firstName);

        clientListTable.getColumns().addAll(idCol,societeCol,nameCol,firstNameCol);
        clientListTable.setPrefWidth(700);
        clientListTable.setShowRoot(false);
        clientListTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        clientListTable.getSelectionModel().selectFirst();
        clientListTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem<Table>>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends TreeItem<Table>> c) {
              int index=clientListTable.getSelectionModel().getSelectedIndex();
                setClientData(visibleClientList.get(index));
               
            }
        });


                }

    private void loadTableData() {
        ObservableList<Table> listFoundIndex = FXCollections.observableArrayList();

        for(Client index : visibleClientList) {
            listFoundIndex.add(new Table(index.getId(), index.getName(),index.getFirstName(),index.getSociete()));
        }

        final TreeItem<Table> treeItem = new RecursiveTreeItem<>(listFoundIndex, RecursiveTreeObject::getChildren);
        try {
            clientListTable.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
        clientListTable.getSelectionModel().selectFirst();
    }
       class Table extends RecursiveTreeObject<Table> {
        StringProperty id,name ,firstName,societe;
        

        public Table(String id_, String nam,String FirstNam,String soc) {
            id = new SimpleStringProperty(id_);
            name = new SimpleStringProperty(nam);
            firstName=new SimpleStringProperty(FirstNam);
            societe=new SimpleStringProperty(soc);
            
        }
    }     
}

