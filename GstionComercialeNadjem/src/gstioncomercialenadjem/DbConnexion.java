
package gstioncomercialenadjem;

import gstioncomercialenadjem.FactureFournisseurFXMLController.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp; 
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnexion {
    
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    static double tva=0,ttc=0,ht=0;
    static String smalDate="2019-02-04 00:00:00";
    
    
    public static final String DATABASE_SERVER_PATH="jdbc:mysql://localhost:3306/gestion_comerciale",
                                                     DATABASE_USER="root",DATABASE_PASSWORD="";
    public DbConnexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection(DATABASE_SERVER_PATH,DATABASE_USER,DATABASE_PASSWORD);
        statement=connection.createStatement();
        System.out.println("I connected to data base succesufully : ) ");
    }
    public Login SignIn(String username)throws SQLException{
     Login login=null;
     String query="SELECT * FROM `login` WHERE (`login`.username='"+username+"' );";
     resultSet=statement.executeQuery(query);
     if(resultSet.next()){
       login=new Login(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getString("password"));
     }
     return login;
    }
    public boolean setLogin(Login login) throws SQLException{
        if(login==null){
            return false;
        }
        String query="update login set username=? , password=? where id=? ";
        preparedStatement=connection.prepareCall(query);
        preparedStatement.setString(1,login.getUsername());
        preparedStatement.setString(2,login.getPassword());
        preparedStatement.setString(3,String.valueOf(login.getId()));
        if(preparedStatement.executeUpdate()<0)return false;
        return true
               ; 
    }
    public int addClient(Client client) throws SQLException {
        if(client==null)return 0;
        String query="INSERT INTO `client`(`societe`, `civilite`, `nomClient`, `prenom`,"
                + " `adresse`, `codePostal`, `ville`,"
                + " `pays`, `tel`, `mobile`, `fax`, `email`, `type`, `livreMemeAdresse`,"
                + " `factureMemeAdresse`, `exemptTVA`, `saisiPar`, `auteurModif`,"
                + "`observation`) "
          + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? );";
        String query2;
        String query3;
        
        preparedStatement=connection.prepareCall(query);

        
        int i=0;
        preparedStatement.setString(++i,client.getSociete());
        preparedStatement.setString(++i,client.getCivilite());
        preparedStatement.setString(++i,client.getName());
        preparedStatement.setString(++i,client.getFirstName());
        preparedStatement.setString(++i,client.getAdrs());
        preparedStatement.setString(++i,client.getCodePostal());
        preparedStatement.setString(++i,client.getTown());
        preparedStatement.setString(++i,client.getCountry());
        preparedStatement.setString(++i,client.getTel());
        preparedStatement.setString(++i,client.getMobile());
        preparedStatement.setString(++i,client.getFax());
        preparedStatement.setString(++i,client.getEmail());
        preparedStatement.setString(++i,client.getType());
        preparedStatement.setString(++i,client.getLivre_meme_adrs());
        preparedStatement.setString(++i,client.getFacture_meme_adrs());
        preparedStatement.setString(++i,client.getExmt_tva());
        preparedStatement.setString(++i,client.getAddedBy());
        preparedStatement.setString(++i,client.getAddedBy());
        preparedStatement.setString(++i,client.getObservation());
        
        
        
        try {
            if(preparedStatement.executeUpdate()==0){
                return -1;
            }else{
                query="select `id` from client where ( `societe` = ? and `civilite` = ? and `nomClient` = ? and"
                        + " `prenom` = ? and `adresse` = ? and `codePostal` = ? and `ville`= ? and `pays` = ? and `tel` = ? and"
                        +" `mobile` = ? and `fax`= ? and `email` = ? and `type` = ? and `livreMemeAdresse` = ? and `factureMemeAdresse` = ? "
                        + "and `exemptTVA` = ? and `observation` = ? );";
                 query2="INSERT INTO `adr_livraison`(`NumClient`, `Civilite`,"
                         + " `Contact`, `Adresse`, `CodePostal`, `Ville`, `Pays`, `Telephone`, "
                         + "`Mobile`, `Fax`, `Email`, `Observation`)"
                          + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
                query3="INSERT INTO `adr_facturation`(`NumClient`, `Civilite`, "
                         + "`Contact`, `Adresse`, `CodePostal`, `Ville`, `Pays`, `Telephone`,"
                         + " `Mobile`, `Fax`, `Email`, `Observation`) "
                         + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                
                preparedStatement=connection.prepareCall(query);
                
                i=0;
                preparedStatement.setString(++i,client.getSociete());
                preparedStatement.setString(++i,client.getCivilite());
                preparedStatement.setString(++i,client.getName());
                preparedStatement.setString(++i,client.getFirstName());
                preparedStatement.setString(++i,client.getAdrs());
                preparedStatement.setString(++i,client.getCodePostal());
                preparedStatement.setString(++i,client.getTown());
                preparedStatement.setString(++i,client.getCountry());
                preparedStatement.setString(++i,client.getTel());
                preparedStatement.setString(++i,client.getMobile());
                preparedStatement.setString(++i,client.getFax());
                preparedStatement.setString(++i,client.getEmail());
                preparedStatement.setString(++i,client.getType());
                preparedStatement.setString(++i,client.getLivre_meme_adrs());
                preparedStatement.setString(++i,client.getFacture_meme_adrs());
                preparedStatement.setString(++i,client.getExmt_tva());
                preparedStatement.setString(++i,client.getObservation());
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    int id=resultSet.getInt("id");
                    i=0;
                    preparedStatement=connection.prepareCall(query2);
                    preparedStatement.setString(++i,resultSet.getString("id"));
                    preparedStatement.setString(++i,client.getCivilite());
                    preparedStatement.setString(++i,"");
                    preparedStatement.setString(++i,client.getAdrs());
                    preparedStatement.setString(++i,client.getCodePostal());
                    preparedStatement.setString(++i,client.getTown());
                    preparedStatement.setString(++i,client.getCountry());
                    preparedStatement.setString(++i,client.getTel());
                    preparedStatement.setString(++i,client.getMobile());
                    preparedStatement.setString(++i,client.getFax());
                    preparedStatement.setString(++i,client.getEmail());
                    preparedStatement.setString(++i,client.getObservation());
                    preparedStatement.executeUpdate();
                    /*********************************************/
                    i=0;
                    preparedStatement=connection.prepareCall(query3);
                    preparedStatement.setString(++i,resultSet.getString("id"));
                    preparedStatement.setString(++i,client.getCivilite());
                    preparedStatement.setString(++i,"");
                    preparedStatement.setString(++i,client.getAdrs());
                    preparedStatement.setString(++i,client.getCodePostal());
                    preparedStatement.setString(++i,client.getTown());
                    preparedStatement.setString(++i,client.getCountry());
                    preparedStatement.setString(++i,client.getTel());
                    preparedStatement.setString(++i,client.getMobile());
                    preparedStatement.setString(++i,client.getFax());
                    preparedStatement.setString(++i,client.getEmail());
                    preparedStatement.setString(++i,client.getObservation());
                    preparedStatement.executeUpdate();
                    return id;
                }else return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
      
    }
     public int addFamille(Famille famille) throws SQLException{
        if(famille==null) return 0;
        
        String query= "INSERT INTO `famille`(`CodeFamille`, `Libelle`) "
          + "VALUES (?,?);";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,famille.getCodeFamille());
        preparedStatement.setString(++i,famille.getLib());
        try {
        if(preparedStatement.executeUpdate()==0){
                return -1;
            }else{
            query="select `CodeFamille` from famille where (`CodeFamille`= ? and `Libelle` = ?);";
            preparedStatement=connection.prepareCall(query);
             i=0;
             preparedStatement.setString(++i,famille.getCodeFamille());
             preparedStatement.setString(++i,famille.getLib());
             resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    return 1;
                }else return -1;
        }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
        
    }
    public int addFraitPort(FraisPort fraisPort) throws SQLException{
        if(fraisPort==null) return 0;
              
        String query= "INSERT INTO `fraisport`(`CodePort`, `LibFraisPort`, `Montant`) "
          + "VALUES (?,?,?);";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,fraisPort.getCodePort());
        preparedStatement.setString(++i,fraisPort.getLib());
        preparedStatement.setString(++i,fraisPort.getMontant());
        try {
        if(preparedStatement.executeUpdate()==0){
                return -1;
            }else{
            query="select `CodePort` from fraisport where"
                    + " (`CodePort`= ? and `LibFraisPort` = ? and `Montant` = ?);";
            preparedStatement=connection.prepareCall(query);
             i=0;
             preparedStatement.setString(++i,fraisPort.getCodePort());
             preparedStatement.setString(++i,fraisPort.getLib());
             preparedStatement.setString(++i,fraisPort.getMontant());
             resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    return 1;
                }else return -1;
        }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
    }
    public int editClient(Client client) throws SQLException{
        if(client==null)return 0;
        String query="UPDATE `client` SET `societe` = ? , `civilite` = ?, `nomClient` = ? , `prenom`= ? , "
                + "`adresse`= ? , `codePostal` = ? , `ville`= ? , `pays` = ? , `tel` = ? , `mobile` = ? "
                + " , `fax`= ? , `email` = ? , `type` = ? , `livreMemeAdresse` = ? , "
                + "`factureMemeAdresse` = ? ,`exemptTVA` = ?,`auteurModif` = ? ,"
                + " `observation` = ? WHERE ( `id` = ? );";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,client.getSociete());
        preparedStatement.setString(++i,client.getCivilite());
        preparedStatement.setString(++i,client.getName());
        preparedStatement.setString(++i,client.getFirstName());
        preparedStatement.setString(++i,client.getAdrs());
        preparedStatement.setString(++i,client.getCodePostal());
        preparedStatement.setString(++i,client.getTown());
        preparedStatement.setString(++i,client.getCountry());
        preparedStatement.setString(++i,client.getTel());
        preparedStatement.setString(++i,client.getMobile());
        preparedStatement.setString(++i,client.getFax());
        preparedStatement.setString(++i,client.getEmail());
        preparedStatement.setString(++i,client.getType());
        preparedStatement.setString(++i,client.getLivre_meme_adrs());
        preparedStatement.setString(++i,client.getFacture_meme_adrs());
        preparedStatement.setString(++i,client.getExmt_tva());
        preparedStatement.setString(++i,client.getModifiedBy());
        preparedStatement.setString(++i,client.getObservation());
        preparedStatement.setString(++i,client.getId());
        if(preparedStatement.executeUpdate()==0){
            return -1;
        }else{
            return 1;
        }
        
      
    }
    public ArrayList<Client> getAllClient() throws SQLException{
        ArrayList<Client> list=new ArrayList();
        Client client;
        String query="select * from client";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         client=new Client(String.valueOf(resultSet.getInt(1)), resultSet.getString(4), resultSet.getString(5),
                 resultSet.getString(6), resultSet.getString(8), resultSet.getString(3), resultSet.getString(10), 
                 resultSet.getString(11), resultSet.getString(12), resultSet.getString(13), resultSet.getString(9),
                 resultSet.getString(7), resultSet.getString(2), resultSet.getString(14), resultSet.getString(22),
                 Client.booleanToString(resultSet.getBoolean(15)), Client.booleanToString(resultSet.getBoolean(16)),
                         Client.booleanToString(resultSet.getBoolean(17)),
                 resultSet.getString(18),Client.timeSpamToString(resultSet.getTimestamp(19)),resultSet.getString(20),
                 Client.timeSpamToString(resultSet.getTimestamp(21))
                         );
         list.add(client);
            //System.out.println("imen");
        }
        return list;
    }
    public boolean deleteClient(String id) throws SQLException{
        if(id == null || id=="") return false;
        String query="delete from `client` where `id` = ?";
        preparedStatement=connection.prepareCall(query);
        preparedStatement.setString(1,id);
        if(preparedStatement.executeUpdate()==0){
            return false;
        }
        
        return true;
    }
    public int addFournisseur(Fournisseur client) throws SQLException {
        if(client==null)return 0;
        String query="INSERT INTO `fournisseur`(`societe`, `civilite`, `nom`, `prenom`,"
                + " `adresse`, `codePostal`, `ville`,"
                + " `pays`, `tel`, `mobile`, `fax`, `email`, "
                + "`observation`) "
          + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,client.getSociete());
        preparedStatement.setString(++i,client.getCivilite());
        preparedStatement.setString(++i,client.getName());
        preparedStatement.setString(++i,client.getFirstName());
        preparedStatement.setString(++i,client.getAdrs());
        preparedStatement.setString(++i,client.getCodePostal());
        preparedStatement.setString(++i,client.getTown());
        preparedStatement.setString(++i,client.getCountry());
        preparedStatement.setString(++i,client.getTel());
        preparedStatement.setString(++i,client.getMobile());
        preparedStatement.setString(++i,client.getFax());
        preparedStatement.setString(++i,client.getEmail());
        preparedStatement.setString(++i,client.getObservation());
        
        try {
            if(preparedStatement.executeUpdate()==0){
                return -1;
            }else{
                query="select `id` from `fournisseur` where ( `societe` = ? and `civilite` = ? and `nom` = ? and"
                        + " `prenom` = ? and `adresse` = ? and `codePostal` = ? and `ville`= ? and `pays` = ? and `tel` = ? and"
                        +" `mobile` = ? and `fax`= ? and `email` = ? and  `observation` = ? );";
                preparedStatement=connection.prepareCall(query);
                i=0;
                preparedStatement.setString(++i,client.getSociete());
                preparedStatement.setString(++i,client.getCivilite());
                preparedStatement.setString(++i,client.getName());
                preparedStatement.setString(++i,client.getFirstName());
                preparedStatement.setString(++i,client.getAdrs());
                preparedStatement.setString(++i,client.getCodePostal());
                preparedStatement.setString(++i,client.getTown());
                preparedStatement.setString(++i,client.getCountry());
                preparedStatement.setString(++i,client.getTel());
                preparedStatement.setString(++i,client.getMobile());
                preparedStatement.setString(++i,client.getFax());
                preparedStatement.setString(++i,client.getEmail());
                preparedStatement.setString(++i,client.getObservation());
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    return resultSet.getInt("id");
                }else return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
      
    }
    public int editFournisseur(Fournisseur client) throws SQLException{
        if(client==null)return 0;
        String query="UPDATE `fournisseur` SET `societe` = ? , `civilite` = ?, `nom` = ? , `prenom`= ? , "
                + "`adresse`= ? , `codePostal` = ? , `ville`= ? , `pays` = ? , `tel` = ? , `mobile` = ? "
                + " , `fax`= ? , `email` = ? ,"
                + " `observation` = ? WHERE ( `id` = ? );";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,client.getSociete());
        preparedStatement.setString(++i,client.getCivilite());
        preparedStatement.setString(++i,client.getName());
        preparedStatement.setString(++i,client.getFirstName());
        preparedStatement.setString(++i,client.getAdrs());
        preparedStatement.setString(++i,client.getCodePostal());
        preparedStatement.setString(++i,client.getTown());
        preparedStatement.setString(++i,client.getCountry());
        preparedStatement.setString(++i,client.getTel());
        preparedStatement.setString(++i,client.getMobile());
        preparedStatement.setString(++i,client.getFax());
        preparedStatement.setString(++i,client.getEmail());
        preparedStatement.setString(++i,client.getObservation());
        preparedStatement.setString(++i,client.getId());
        if(preparedStatement.executeUpdate()==0){
            return -1;
        }else{
            return 1;
        }
        
      
    }
    public boolean deleteFournisseur(String id) throws SQLException{
        if(id == null || id=="") return false;
        String query="delete from `fournisseur` where `id` = ?";
        preparedStatement=connection.prepareCall(query);
        preparedStatement.setString(1,id);
        if(preparedStatement.executeUpdate()==0){
            return false;
        }
        
        return true;
    }
    public ArrayList<Fournisseur> getAllFournisseur() throws SQLException{
        ArrayList<Fournisseur> list=new ArrayList();
        Fournisseur client;
        String query="select * from fournisseur";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         client=new Fournisseur(String.valueOf(resultSet.getInt(1)), resultSet.getString(4), resultSet.getString(5),
                 resultSet.getString(6), resultSet.getString(8), resultSet.getString(3), resultSet.getString(10), 
                 resultSet.getString(11), resultSet.getString(12), resultSet.getString(13), resultSet.getString(9),
                 resultSet.getString(7), resultSet.getString(2),resultSet.getString(14) );
         list.add(client);
            //System.out.println("imen");
        }
        return list;
    }
    public String addProduitAndStock(Produit p,Stock s) throws SQLException{
          if(p==null||s==null)return null;
        String query="INSERT INTO `produit`( `Reference`, `GenCode`, `CodeBar`, `LibProd`, `Description`, `PrixHT`, "
                + "`QteReappro`, `QteMini`, `TauxTVA`, "/*`Photo`,*/ +"`NumFournisseur`, `PlusAuCatalogue`, `SaisiPar`, "
                + " `CodeFamille`, `CodePort` )"
          + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?"+/*?*/");";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,p.getReff());
        preparedStatement.setString(++i,p.getCodeG());
        preparedStatement.setString(++i,p.getCodeB());
        preparedStatement.setString(++i,p.getLib());
        preparedStatement.setString(++i,p.getDescription());
        preparedStatement.setString(++i,p.getPrixHT());
        preparedStatement.setString(++i,p.getReapoQte());
        preparedStatement.setString(++i,p.getQteMin());
        preparedStatement.setString(++i,p.getTva());
        preparedStatement.setString(++i,p.getFournisseur());
        preparedStatement.setString(++i,p.getCatalogue());
        preparedStatement.setString(++i,p.getAddedBy());
        preparedStatement.setString(++i,p.getFamille());
        preparedStatement.setString(++i,p.getCodePort());
        try {
            if(preparedStatement.executeUpdate()==0){
                return null;
            }else{
        query="INSERT INTO `stock`(`Reference`, `QteEnStock`, `QteStockVirtuel`, `AuteurModif`)"
          + "VALUES (?,?,?,?);";
        preparedStatement=connection.prepareCall(query);
        i=0;  
        preparedStatement.setString(++i,s.getReff());
        preparedStatement.setString(++i,s.getStckQte());
        preparedStatement.setString(++i,s.getvQte());
        preparedStatement.setString(++i,s.getModifiedBy());
        try {
            if(preparedStatement.executeUpdate()==0){
                return "-1";
        }else{
             return p.getReff();   
            }        
        } catch (SQLException ex) {
            Logger.getLogger(DbConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return "-1";
        }
              
            }       } catch (SQLException ex) {
            Logger.getLogger(DbConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        
      
    
        
    }
    public ArrayList<Famille> getAllFamille() throws SQLException{
        ArrayList<Famille> list=new ArrayList();
        Famille f;
        String query="select * from famille";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         f=new Famille( resultSet.getString(1), resultSet.getString(2) );
         list.add(f);
        }
        return list;
            }
    public ArrayList<FraisPort> getAllFraisPorts() throws SQLException{
        ArrayList<FraisPort> list=new ArrayList();
        FraisPort f;
        String query="select * from fraisport";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         f=new FraisPort(resultSet.getString(1), resultSet.getString(2),String.valueOf(resultSet.getDouble(3)));
         list.add(f);
        }
        return list;
            }
    public ArrayList<TVA> getAllTVA() throws SQLException{
        ArrayList<TVA> list=new ArrayList();
        TVA f;
        String query="select * from tva";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         f=new TVA( resultSet.getString(1) );
         list.add(f);
        }
        return list;
            }   
    public boolean updateFact(Facture client) throws SQLException{
       if(client==null)return false;
       String query="UPDATE `facture` SET `DateFacture`=?,`NumClient`=?,`IDAdresseFacturation`=?,`IDModeReglement`=?,"
               + "`TotalHT`=?,`TotalTVA`=?,`TotalTTC`=?,`Remise`=?,`Observation`=? WHERE (`NumFacture`=?);";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,client.date);
        preparedStatement.setString(++i,client.numClient);
        preparedStatement.setString(++i,client.adrsFact);
        preparedStatement.setString(++i,client.modeR);
        preparedStatement.setString(++i,client.ht);
        preparedStatement.setString(++i,client.tva);
        preparedStatement.setString(++i,client.ttc);
        preparedStatement.setString(++i,client.remise);
        preparedStatement.setString(++i,client.getObservation());
        preparedStatement.setString(++i,client.getId());
        if(preparedStatement.executeUpdate()==0){
            return false;
        }
       return true;
   }
    public boolean insertFact(Facture client) throws SQLException{
       if(client==null)return false;
       if(false){
       String query="INSERT INTO `facture`( `DateFacture` , `NumClient`, `IDAdresseFacturation`, `IDModeReglement`, "
               + "`TotalHT`, `TotalTVA`, `TotalTTC`, `Remise` ,  `SaisiPar`, `Observation`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,client.date);
        preparedStatement.setString(++i,client.numClient);
        preparedStatement.setString(++i,client.adrsFact);
        preparedStatement.setString(++i,client.modeR);
        preparedStatement.setString(++i,client.ht);
        preparedStatement.setString(++i,client.tva);
        preparedStatement.setString(++i,client.ttc);
        preparedStatement.setString(++i,client.remise);
        preparedStatement.setString(++i,client.sPar);
        preparedStatement.setString(++i,client.getObservation());
        if(preparedStatement.executeUpdate()==0){
            return false;
        }
        String reg="0";
        if(Double.parseDouble(client.remise)>=0)reg=client.ttc;
        else reg=String.valueOf(Double.parseDouble(client.remise)+Double.parseDouble(client.ttc));
        addReglement(new Reglement(null, client.date,client.modeR , client.id, client.sPar, "", "", reg), "");
       return true;
       }else{
               String query="INSERT INTO `facture`( `NumFacture`,`DateFacture` , `NumClient`, `IDAdresseFacturation`, `IDModeReglement`, "
               + "`TotalHT`, `TotalTVA`, `TotalTTC`, `Remise` ,  `SaisiPar`, `Observation`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,client.id);
        preparedStatement.setString(++i,client.date);
        preparedStatement.setString(++i,client.numClient);
        preparedStatement.setString(++i,client.adrsFact);
        preparedStatement.setString(++i,client.modeR);
        preparedStatement.setString(++i,client.ht);
        preparedStatement.setString(++i,client.tva);
        preparedStatement.setString(++i,client.ttc);
        preparedStatement.setString(++i,client.remise);
        preparedStatement.setString(++i,client.sPar);
        preparedStatement.setString(++i,client.getObservation());
        if(preparedStatement.executeUpdate()==0){
            return false;
        }
        String reg="0";
        if(Double.parseDouble(client.remise)>=0)reg=client.ttc;
        else reg=String.valueOf(Double.parseDouble(client.remise)+Double.parseDouble(client.ttc));
        addReglement(new Reglement(null, client.date,client.modeR , client.id, client.sPar, "", "", reg), "");
       return true;   
       }
   } 
    public boolean updateligneFact(Table client) throws SQLException {
       if(client==null)return false;
       String query="UPDATE `lignefac` SET Reference = ?,`LibProd`= ?,`Quantite`= ?,`PrixVente`= ?,`TauxTVA`= ?"
               + " WHERE IDLigneFac = ? ";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,client.id_);
        preparedStatement.setString(++i,client.name_);
        preparedStatement.setString(++i,client.qte_);
        preparedStatement.setString(++i,client.pv_);
        preparedStatement.setString(++i,client.tva_);
        preparedStatement.setString(++i,client.idF_);
        if(preparedStatement.executeUpdate()==0){
            return false;
        }
                query="UPDATE `stock` SET `QteEnStock`=`QteEnStock`- ? WHERE `Reference`=?";
        preparedStatement=connection.prepareCall(query);
        i=0;
        preparedStatement.setString(++i,client.oldQte);
        preparedStatement.setString(++i,client.id_);
                if(preparedStatement.executeUpdate()==0){
            return false;
        }
       return true;
   }
    public boolean insertligneFact(Table client) throws SQLException{
       if(client==null)return false;
       String query="INSERT INTO `lignefac`(`NumFacture`, `Reference`, `LibProd`, `Quantite`, `PrixVente`, `TauxTVA`) "
               + "VALUES (?,?,?,?,?,?)";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,client.idF_);
        preparedStatement.setString(++i,client.id_);
        preparedStatement.setString(++i,client.name_);
        preparedStatement.setString(++i,client.qte_);
        preparedStatement.setString(++i,client.pv_);
        preparedStatement.setString(++i,client.tva_);
        if(preparedStatement.executeUpdate()==0){
            return false;
        }
        query="UPDATE `stock` SET `QteEnStock`=`QteEnStock`- ? WHERE `Reference`=?";
        preparedStatement=connection.prepareCall(query);
        i=0;
        preparedStatement.setString(++i,client.qte_);
        preparedStatement.setString(++i,client.id_);
                if(preparedStatement.executeUpdate()==0){
            return false;
        }
       return true;
   }
    public boolean delLigneFact( Table client) throws SQLException{
       if(client==null)return false;
       String query="DELETE FROM `lignefac` WHERE  (`IDLigneFac`=?);";
        preparedStatement=connection.prepareCall(query);
        int i=0;
         preparedStatement.setString(++i,client.id_l);
        if(preparedStatement.executeUpdate()==0){
            return false;
        }
                query="UPDATE `stock` SET `QteEnStock`=`QteEnStock`+ ? WHERE `Reference`=?";
        preparedStatement=connection.prepareCall(query);
        i=0;
        preparedStatement.setString(++i,client.qte_);
        preparedStatement.setString(++i,client.id_);
                if(preparedStatement.executeUpdate()==0){
                    
                    
            return false;
        }
       return true;
   }
    public ArrayList<Table> getLigneFactForFact(String id) throws SQLException{
       ArrayList<Table> list=new ArrayList();
        Table f;
        tva=0;ttc=0;ht=0;
        String query="SELECT * FROM `lignefac` WHERE `NumFacture` = "+id;
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         f=new Table(resultSet.getString(3),
                 resultSet.getString(4),String.valueOf(resultSet.getInt(5)),
                 TVA.getTheStringValurOfTVA(resultSet.getDouble(6)),TVA.getTheStringValurOfTVA(resultSet.getDouble(8)));
         f.idF_=String.valueOf(resultSet.getInt(2));
         f.id_l=String.valueOf(resultSet.getInt(1));
         f.opType=1;
         list.add(f);
         ht+=Double.parseDouble(f.pv_)*Integer.parseInt(f.qte_);
         tva+=Double.parseDouble(f.tva_)*Integer.parseInt(f.qte_);
         ttc=ht+tva;
        }
        return list;
      }
    public ArrayList<ModeDeReglement> getAllModeDeRlist() throws SQLException{
        ArrayList<ModeDeReglement> list=new ArrayList();
        ModeDeReglement f;
        String query="select * from modereglement";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         f=new ModeDeReglement(String.valueOf(resultSet.getInt(1)), resultSet.getString(2));
         list.add(f);
        }
        return list;
      }
    public ArrayList<AdresseDeFacturation> getAllAdressFact() throws SQLException{
        ArrayList<AdresseDeFacturation> list=new ArrayList();
        AdresseDeFacturation f;
        String query="select * from adr_facturation";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         f=new AdresseDeFacturation(String.valueOf(resultSet.getInt(1)), String.valueOf(resultSet.getInt(2))
                 +" " +resultSet.getString(5)+" "+resultSet.getString(7)+", "+resultSet.getString(8));
         list.add(f);
        }
        return list;
      }
    public ArrayList<AdresseDeFacturation> getAllAdressFact(String clientId) throws SQLException{
        ArrayList<AdresseDeFacturation> list=new ArrayList();
        AdresseDeFacturation f;
        String query="select * from adr_facturation where `NumClient`= "+clientId;
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         f=new AdresseDeFacturation(String.valueOf(resultSet.getInt(1)), String.valueOf(resultSet.getInt(2))
                 +" " +resultSet.getString(5)+" "+resultSet.getString(7)+", "+resultSet.getString(8));
         list.add(f);
        }
        return list;
      }
    public ArrayList<Facture> getAllFactures() throws SQLException{
          
       ArrayList<Facture> list=new ArrayList();
        Facture f;
        String query="select * from facture";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         f=new Facture(String.valueOf(resultSet.getInt(1)), Client.timeSpamToString(resultSet.getTimestamp(2)),
                 String.valueOf(resultSet.getInt(3)),String.valueOf(resultSet.getInt(4)),String.valueOf(resultSet.getInt(5)),
                 String.valueOf(resultSet.getDouble(6)),String.valueOf(resultSet.getDouble(7)),String.valueOf(resultSet.getDouble(8)),
                 String.valueOf(resultSet.getDouble(9)),String.valueOf(resultSet.getDouble(10)),Client.booleanToString(resultSet.getBoolean(11)),
                 resultSet.getString(12),Client.timeSpamToString(resultSet.getTimestamp(13)),resultSet.getString(14)
         );
         list.add(f);
        }
        return list;
      }
    public ArrayList<Facture> getAllFactures(String clientId) throws SQLException{
          
       ArrayList<Facture> list=new ArrayList();
        Facture f;
        String query="select * from facture where `NumClient` = "+clientId;
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
         f=new Facture(String.valueOf(resultSet.getInt(1)), Client.timeSpamToString(resultSet.getTimestamp(2)),
                 String.valueOf(resultSet.getInt(3)),String.valueOf(resultSet.getInt(4)),String.valueOf(resultSet.getInt(5)),
                 String.valueOf(resultSet.getDouble(6)),String.valueOf(resultSet.getDouble(7)),String.valueOf(resultSet.getDouble(8)),
                 String.valueOf(resultSet.getDouble(9)),String.valueOf(resultSet.getDouble(10)),Client.booleanToString(resultSet.getBoolean(11)),
                 resultSet.getString(12),Client.timeSpamToString(resultSet.getTimestamp(13)),resultSet.getString(14)
         );
         list.add(f);
        }
        return list;
      }
    public ArrayList<Produit> getAllProduitsDispo() throws SQLException{
          
       ArrayList<Produit> list=new ArrayList();
        Produit f;
        String query="SELECT * FROM `produit` WHERE (Reference in (select Reference from stock where stock.QteEnStock>0))";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
            /* String tva,String catalogue, String description, String reff, String codeG, String codeB, String lib, 
                     String famille, String prixHT, String fournisseur, String qteMin, String reapoQte, String codePort,
                             String addedBy,String addedAt*/
         f=new Produit(String.valueOf(resultSet.getDouble(9)), Client.booleanToString(resultSet.getBoolean(12)),
                 String.valueOf(resultSet.getString(5)),String.valueOf(resultSet.getString(1)),
                 String.valueOf(resultSet.getString(2)),
                 String.valueOf(resultSet.getString(1)),
                 String.valueOf(resultSet.getString(4)),
                 String.valueOf(resultSet.getString(15)),
                 String.valueOf(resultSet.getDouble(6)),
                 "","","","","","");
         list.add(f);
        }
        return list;
      }
    public ArrayList<Reglement> getAllReglement() throws SQLException{
    
       ArrayList<Reglement> list=new ArrayList();
        Reglement f;
        String query="SELECT * FROM `reglement`";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
            /* String tva,String catalogue, String description, String reff, String codeG, String codeB, String lib, 
                     String famille, String prixHT, String fournisseur, String qteMin, String reapoQte, String codePort,
                             String addedBy,String addedAt*/
         f=new Reglement(
                 String.valueOf(resultSet.getInt(1)), 
                 Client.timeSpamToString(resultSet.getTimestamp(2)),
                 String.valueOf(resultSet.getInt(3)),
                 String.valueOf(resultSet.getInt(4)),
                 String.valueOf(resultSet.getString(6)),
                 Client.timeSpamToString(resultSet.getTimestamp(7)),
                 String.valueOf(resultSet.getString(8)),
                 String.valueOf(resultSet.getInt(5)));
         list.add(f);
        }
        return list;
      }
    public boolean addReglement(Reglement r,String remise)throws SQLException{
       if(r==null)return false;
       String query="INSERT INTO `reglement`( `DateReglement`, `IDModeReglement`, `NumFacture`, `reglement`,"
               + " `SaisiPar`, `Observation`) VALUES (?,?,?,?,?,?)";
        preparedStatement=connection.prepareCall(query);
        int i=0;
        if(Double.parseDouble(r.reglement)<=0) r.reglement="0";
        preparedStatement.setString(++i,r.date);
        preparedStatement.setString(++i,r.modeDeR);
        preparedStatement.setString(++i,r.numFacture);
        preparedStatement.setString(++i,r.reglement);
        preparedStatement.setString(++i,r.saisiePar);
        preparedStatement.setString(++i,r.observation);
        if(preparedStatement.executeUpdate()==0){
            return false;
        }
        if(!remise.equals("")){
        query="UPDATE `facture` SET `Remise`=? WHERE (`NumFacture`=?);";
        preparedStatement=connection.prepareCall(query);
        i=0;
       
        preparedStatement.setString(++i,remise);
        preparedStatement.setString(++i,r.numFacture);
        if(preparedStatement.executeUpdate()==0){
            return false;
        }}
       return true;
      }
    public Facture getFacture(String id) throws SQLException{
                 String query="select * from facture where `NumFacture`= '"+id+"' ;";
        ResultSet resultSet2 =statement.executeQuery(query);
        Facture f1;
        resultSet2.next();
         f1=new Facture(String.valueOf(resultSet2.getInt(1)), Client.timeSpamToString(resultSet2.getTimestamp(2)),
                 String.valueOf(resultSet2.getInt(3)),String.valueOf(resultSet2.getInt(4)),String.valueOf(resultSet2.getInt(5)),
                 String.valueOf(resultSet2.getDouble(6)),String.valueOf(resultSet2.getDouble(7)),String.valueOf(resultSet2.getDouble(8)),
                 String.valueOf(resultSet2.getDouble(9)),String.valueOf(resultSet2.getDouble(10)),Client.booleanToString(resultSet2.getBoolean(11)),
                 resultSet2.getString(12),Client.timeSpamToString(resultSet2.getTimestamp(13)),resultSet2.getString(14)
         );
         return f1;
    }
    public boolean addReglementInAll(Reglement r,String remise,String idClient)throws SQLException{
       if(r==null)return false;
               double reglmnt=Double.parseDouble(r.reglement);
               double deteFact=0,nvDeteFact=0, payd=0;
               ArrayList<Facture> list=getAllFactures(idClient);
       for(Facture f:list){
           f.getDeposed();
           if(!f.payed){
                deteFact=Double.parseDouble(f.remise);
                nvDeteFact=reglmnt+deteFact;
                if(nvDeteFact>0) {payd=-1*deteFact;reglmnt=nvDeteFact; }else {payd=reglmnt;
                reglmnt=0;   }     
              String query="INSERT INTO `reglement`( `DateReglement`, `IDModeReglement`, `NumFacture`, `reglement`,"
               + " `SaisiPar`, `Observation`) VALUES (?,?,?,?,?,?)";
              
    preparedStatement=connection.prepareCall(query);
        int i=0;
        preparedStatement.setString(++i,r.date);
        preparedStatement.setString(++i,r.modeDeR);
        preparedStatement.setString(++i,f.id);
        preparedStatement.setString(++i,String.valueOf(payd));
        preparedStatement.setString(++i,r.saisiePar);
        preparedStatement.setString(++i,r.observation);
        if(preparedStatement.executeUpdate()==0){
            return false;
        } 
                   if(reglmnt==0) {
               //remise
                       if(Double.parseDouble(remise)<0){
         if(Double.parseDouble(remise)<Double.parseDouble(f.remise)) remise=String.valueOf(nvDeteFact);
         else{
            if(Double.parseDouble(remise)==Double.parseDouble(f.remise)) remise="0";
            
         }}
        query="UPDATE `facture` SET `Remise`=? WHERE (`NumFacture`=?);";
        preparedStatement=connection.prepareCall(query);
        i=0;
        preparedStatement.setString(++i,remise);
        preparedStatement.setString(++i,f.id);
        if(preparedStatement.executeUpdate()==0){
            return false;
        }
            System.out.println("--------------------"+reglmnt+"___"+remise);
                       break;  
              
           }

           }
       
       }
     return true;
    }
    public ArrayList<Reglement> gatAllReglementByDate(String beginDate,String endDate) throws SQLException{
        ArrayList<Reglement> list=new ArrayList();
        Reglement f;
       // ArrayList<Facture> factures=getAllFactures();
        smalDate="2019-02-01 00:00:00";
        String query="SELECT * FROM `reglement`,`facture` WHERE `facture`.`NumFacture` =`reglement`.`NumFacture` and `DateReglement`<= '" +endDate+"' and `DateReglement`>= '"+beginDate+"' order by `DateReglement` ASC";
        resultSet =statement.executeQuery(query);
        while(resultSet.next()){
            /* String tva,String catalogue, String description, String reff, String codeG, String codeB, String lib, 
                     String famille, String prixHT, String fournisseur, String qteMin, String reapoQte, String codePort,
                             String addedBy,String addedAt*/
         f=new Reglement(
                 String.valueOf(resultSet.getInt(1)), 
                 Client.timeSpamToString(resultSet.getTimestamp(2)),
                 String.valueOf(resultSet.getInt(3)),
                 String.valueOf(resultSet.getInt(4)),
                 String.valueOf(resultSet.getString(6)),
                 Client.timeSpamToString(resultSet.getTimestamp(7)),
                 String.valueOf(resultSet.getString(8)),
                 String.valueOf(resultSet.getInt(5)));

         f.numClient=String.valueOf(resultSet.getInt(11));
         list.add(f);
        
         
        }
        return list;
    }

   /*-------------------------------------------------------------------------------------------------------------------*/
    public static class Login{
        int id;
        String username,password;

        public Login(int id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
        }
        
        public Login(String id, String username, String password) {
            this.id = Integer.parseInt(id);
            this.username = username;
            this.password = password;
        }
        
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
        
        public String getTheStringValueOfId(){
            return String.valueOf(id);
        }
        
        
    }
    public static class Client{

        static String BooleanFrenchValue(boolean value) {
           if(value) return "oui";
           else return "non";
        }
       String id="",name="",firstName="",adrs="",town="",civilite="",tel="",mobile="",fax="",email="",country="",
               codePostal="",societe="",type="",observation="",livre_meme_adrs="",facture_meme_adrs="",exmt_tva="",
               addedBy="",addedAt="",modifiedBy="",modifiedAt="";
       double dete;

        public Client( String name, String firstName, String adrs, String town, String civilite,
                String tel, String mobile, String fax, String email, String country, String codePostal, 
                String societe, String type, String observation, String livre_meme_adrs, String facture_meme_adrs,
                String exmt_tva, String addedBy, String modifiedBy) {
            //this.id = id;
            this.name = name;
            this.firstName = firstName;
            this.adrs = adrs;
            this.town = town;
            if(civilite!=null)
            this.civilite = civilite;
            if(tel!=null)
            this.tel = tel;
            if(mobile!=null)
            this.mobile = mobile;
            if(fax!=null)
            this.fax = fax;
            if(email!=null)
            this.email = email;
            if(country!=null)
            this.country = country;
            if(codePostal!=null)
            this.codePostal = codePostal;
            if(societe!=null)
            this.societe = societe;
            if(type!=null)
            this.type = type;
            if(observation!=null)
            this.observation = observation;
            this.livre_meme_adrs = livre_meme_adrs;
            this.facture_meme_adrs = facture_meme_adrs;
            this.exmt_tva = exmt_tva;
            if(addedBy!=null)
            this.addedBy = addedBy;
            if(modifiedBy!=null)
            this.modifiedBy = modifiedBy;
        }

        public Client(String id, String name, String firstName, String adrs, String town, String civilite, 
                String tel, String mobile, String fax, String email, String country, String codePostal, 
                String societe, String type, String observation, String livre_meme_adrs, 
                String facture_meme_adrs, String exmt_tva, String addedBy, String addedAt,
                String modifiedBy, String modifiedAt) {
            this.id = id;
            this.name = name;
            this.firstName = firstName;
            this.adrs = adrs;
            this.town = town;
            if(civilite!=null)
            this.civilite = civilite;
            if(tel!=null)
            this.tel = tel;
            if(mobile!=null)
            this.mobile = mobile;
            if(fax!=null)
            this.fax = fax;
            if(email!=null)
            this.email = email;
            if(country!=null)
            this.country = country;
            if(codePostal!=null)
            this.codePostal = codePostal;
            if(societe!=null)
            this.societe = societe;
            if(type!=null)
            this.type = type;
            if(observation!=null)
            this.observation = observation;
            this.livre_meme_adrs = livre_meme_adrs;
            this.facture_meme_adrs = facture_meme_adrs;
            this.exmt_tva = exmt_tva;
            if(addedBy!=null)
            this.addedBy = addedBy;
            if(modifiedBy!=null)
            this.modifiedBy = modifiedBy;
            this.addedAt = addedAt;
            this.modifiedAt = modifiedAt;
        }

        
        public int getIdNum(){
            return Integer.parseInt(id);
        }
        public int getTypeNum(){
            return Integer.parseInt(type);
        }
        public boolean getLivre_meme_adrsValue(){
          if(livre_meme_adrs.equals("1")) return  true;
         else return false;
        } 
        public boolean getFacture_meme_adrsValue(){
          if(facture_meme_adrs.equals("1")) return  true;
         else return false;
        }
        public boolean getExmptTvaValue(){
          if(exmt_tva.equals("1")) return  true;
         else return false;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getAdrs() {
            return adrs;
        }

        public void setAdrs(String adrs) {
            this.adrs = adrs;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getCivilite() {
            return civilite;
        }

        public void setCivilite(String civilite) {
            this.civilite = civilite;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCodePostal() {
            return codePostal;
        }

        public void setCodePostal(String codePostal) {
            this.codePostal = codePostal;
        }

        public String getSociete() {
            return societe;
        }

        public void setSociete(String societe) {
            this.societe = societe;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getObservation() {
            return observation;
        }

        public void setObservation(String observation) {
            this.observation = observation;
        }

        public String getLivre_meme_adrs() {
            return livre_meme_adrs;
        }

        public void setLivre_meme_adrs(String livre_meme_adrs) {
            this.livre_meme_adrs = livre_meme_adrs;
        }

        public String getFacture_meme_adrs() {
            return facture_meme_adrs;
        }

        public void setFacture_meme_adrs(String facture_meme_adrs) {
            this.facture_meme_adrs = facture_meme_adrs;
        }

        public String getExmt_tva() {
            return exmt_tva;
        }

        public void setExmt_tva(String exmt_tva) {
            this.exmt_tva = exmt_tva;
        }

        public String getAddedBy() {
            return addedBy;
        }

        public void setAddedBy(String addedBy) {
            this.addedBy = addedBy;
        }

        public String getAddedAt() {
            return addedAt.substring(0,addedAt.length()-2);
        }

        public void setAddedAt(String addedAt) {
            this.addedAt = addedAt;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public String getModifiedAt() {
            return modifiedAt.substring(0, modifiedAt.length()-2);
        }

        public void setModifiedAt(String modifiedAt) {
            this.modifiedAt = modifiedAt;
        }
        public static  String booleanToString(boolean b){
            if(b)return "1";else  return "0";
        }
        public static String timeSpamToString(Timestamp timestamp){
            return timestamp.toString();
        }
        
       
        
    }
    public static class Fournisseur{
               String id="",name="",firstName="",adrs="",town="",civilite="",tel="",mobile="",fax="",email="",country="",
               codePostal="",societe="",observation="";


        
        public int getIdNum(){
            return Integer.parseInt(id);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getAdrs() {
            return adrs;
        }

        public void setAdrs(String adrs) {
            this.adrs = adrs;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getCivilite() {
            return civilite;
        }

        public void setCivilite(String civilite) {
            this.civilite = civilite;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCodePostal() {
            return codePostal;
        }

        public void setCodePostal(String codePostal) {
            this.codePostal = codePostal;
        }

        public String getSociete() {
            return societe;
        }

        public void setSociete(String societe) {
            this.societe = societe;
        }


        public String getObservation() {
            return observation;
        }

        public void setObservation(String observation) {
            this.observation = observation;
        }
  public Fournisseur(String id, String name, String firstName, String adrs, String town, String civilite, 
                String tel, String mobile, String fax, String email, String country, String codePostal, 
                String societe,  String observation) {
            this.id = id;
            this.name = name;
            this.firstName = firstName;
            this.adrs = adrs;
            this.town = town;
            if(civilite!=null)
            this.civilite = civilite;
            if(tel!=null)
            this.tel = tel;
            if(mobile!=null)
            this.mobile = mobile;
            if(fax!=null)
            this.fax = fax;
            if(email!=null)
            this.email = email;
            if(country!=null)
            this.country = country;
            if(codePostal!=null)
            this.codePostal = codePostal;
            if(societe!=null)
            this.societe = societe;
            if(observation!=null)
            this.observation = observation;

        }

               
    }
    public static class Reglement{
        String id,date,modeDeR,numFacture,saisiePar,saisieLe,observation,reglement,numClient;

        public Reglement(String id, String date, String modeDeR, String numFacture, String saisiePar, String saisieLe, String observation,String reglement) {
            this.id = id;
            this.reglement=reglement;
            this.date = date;
            this.modeDeR = modeDeR;
            this.numFacture = numFacture;
            this.saisiePar = saisiePar;
            this.saisieLe = saisieLe;
            this.observation = observation;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getModeDeR() {
            return modeDeR;
        }

        public void setModeDeR(String modeDeR) {
            this.modeDeR = modeDeR;
        }

        public String getNumFacture() {
            return numFacture;
        }

        public void setNumFacture(String numFacture) {
            this.numFacture = numFacture;
        }

        public String getSaisiePar() {
            return saisiePar;
        }

        public void setSaisiePar(String saisiePar) {
            this.saisiePar = saisiePar;
        }

        public String getSaisieLe() {
            return saisieLe;
        }

        public void setSaisieLe(String saisieLe) {
            this.saisieLe = saisieLe;
        }

        public String getObservation() {
            return observation;
        }

        public void setObservation(String observation) {
            this.observation = observation;
        }
        
    }
    public static class Produit {
        String catalogue,description,reff,codeG,codeB,lib,famille,prixHT,fournisseur,qteMin,reapoQte,codePort,addedBy,addedAt,tva;

        public Produit(String tva,String catalogue, String description, String reff, String codeG, String codeB, String lib, String famille, String prixHT, String fournisseur, String qteMin, String reapoQte, String codePort,String addedBy,String addedAt) {
            this.catalogue = catalogue;
            this.description = description;
            this.reff = reff;
            this.codeG = codeG;
            this.codeB = codeB;
            this.lib = lib;
            this.famille = famille;
            this.prixHT = prixHT;
            this.fournisseur = fournisseur;
            this.qteMin = qteMin;
            this.reapoQte = reapoQte;
            this.codePort = codePort;
            this.addedAt = addedAt;
            this.addedBy = addedBy;  
            this.tva=tva;
        }

        public String getAddedBy() {
            return addedBy;
        }

        public void setAddedBy(String addedBy) {
            this.addedBy = addedBy;
        }

        public String getAddedAt() {
            return addedAt;
        }

        public void setAddedAt(String addedAt) {
            this.addedAt = addedAt;
        }

        public String getTva() {
            return tva;
        }

        public void setTva(String tva) {
            this.tva = tva;
        }

        public String getCatalogue() {
            return catalogue;
        }

        public void setCatalogue(String catalogue) {
            this.catalogue = catalogue;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getReff() {
            return reff;
        }

        public void setReff(String reff) {
            this.reff = reff;
        }

        public String getCodeG() {
            return codeG;
        }

        public void setCodeG(String codeG) {
            this.codeG = codeG;
        }

        public String getCodeB() {
            return codeB;
        }

        public void setCodeB(String codeB) {
            this.codeB = codeB;
        }

        public String getLib() {
            return lib;
        }

        public void setLib(String lib) {
            this.lib = lib;
        }

        public String getFamille() {
            return famille;
        }

        public void setFamille(String famille) {
            this.famille = famille;
        }

        public String getPrixHT() {
            return prixHT;
        }

        public void setPrixHT(String prixHT) {
            this.prixHT = prixHT;
        }

        public String getFournisseur() {
            return fournisseur;
        }

        public void setFournisseur(String fournisseur) {
            this.fournisseur = fournisseur;
        }

        public String getQteMin() {
            return qteMin;
        }

        public void setQteMin(String qteMin) {
            this.qteMin = qteMin;
        }

        public String getReapoQte() {
            return reapoQte;
        }

        public void setReapoQte(String reapoQte) {
            this.reapoQte = reapoQte;
        }

        public String getCodePort() {
            return codePort;
        }

        public void setCodePort(String codePort) {
            this.codePort = codePort;
        }
        public String getTheStringValurOfAReal(double num){
            return String.valueOf(num);
        }
        public String getTheStringValueOfInt(int num){
            return String.valueOf(num);
        }
        
    }
    public static class Stock{
        String reff,tvavQte,stckQte,modifiedBy,modifiedAt;

        public Stock(String reff,String tvavQte, String stckQte, String modifiedBy, String modifiedAt) {
            this.tvavQte = tvavQte;
            this.stckQte = stckQte;
            this.modifiedBy = modifiedBy;
            this.modifiedAt = modifiedAt;
            this.reff=reff;
        }

        public String getReff() {
            return reff;
        }

        public void setReff(String reff) {
            this.reff = reff;
        }

        public String getvQte() {
            return tvavQte;
        }

        public void setvQte(String tvavQte) {
            this.tvavQte = tvavQte;
        }

        public String getStckQte() {
            return stckQte;
        }

        public void setStckQte(String stckQte) {
            this.stckQte = stckQte;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public String getModifiedAt() {
            return modifiedAt;
        }

        public void setModifiedAt(String modifiedAt) {
            this.modifiedAt = modifiedAt;
        }

        
    }
    public static class Famille{
         String codeFamille,lib;

        public Famille(String codeFamille, String lib) {
            this.codeFamille = codeFamille;
            this.lib = lib;
        }

        public String getCodeFamille() {
            return codeFamille;
        }

        public void setCodeFamille(String codeFamille) {
            this.codeFamille = codeFamille;
        }

        public String getLib() {
            return lib;
        }

        public void setLib(String lib) {
            this.lib = lib;
        }
    }
    public static class FraisPort{
      String codePort,lib,montant;

        public FraisPort(String codePort, String lib, String montant) {
            this.codePort = codePort;
            this.lib = lib;
            this.montant = montant;
        }

        public String getCodePort() {
            return codePort;
        }

        public void setCodePort(String codePort) {
            this.codePort = codePort;
        }

        public String getLib() {
            return lib;
        }

        public void setLib(String lib) {
            this.lib = lib;
        }

        public String getMontant() {
            return montant;
        }

        public void setMontant(String montant) {
            this.montant = montant;
        }
      
       
    }
    public static class TVA{
        String tva;

        public TVA(String tva) {
            this.tva = tva;
        }

        public String getTva() {
            return tva;
        }

        public void setTva(String tva) {
            this.tva = tva;
        }
        public static String getTheStringValurOfTVA(double TVA){
            return String.valueOf(TVA);
        }
    }
    public static class ModeDeReglement{
        String id,lib;

        public ModeDeReglement(String id, String lib) {
            this.id = id;
            this.lib = lib;
        }
        
         public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLib() {
            return lib;
        }

        public void setLib(String lib) {
            this.lib = lib;
        }
        
    }
    public static class AdresseDeFacturation{
        String id,lib;
        

        public AdresseDeFacturation(String id, String lib) {
            this.id = id;
            this.lib = lib;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLib() {
            return lib;
        }

        public void setLib(String lib) {
            this.lib = lib;
        }
        
    }
    public static class Facture{
        String id,date,numClient,adrsFact,modeR,ht,tva,ttc,tFraisPort,remise,aquite,sPar,sLe,observation;
        boolean insert=false,payed;
        
        public String getDeposed(){
            String result;
            double r=Double.parseDouble(remise);
            double t=Double.parseDouble(ttc);
            if(r>0) {result=String.valueOf(t+r);payed=true;}
            else if(r==0) {result= ttc; payed=true;}
            else {result= String.valueOf(t+r);payed=false;};
            return result;
        }
        public Facture(String id, String date, String numClient, String adrsFact, String modeR, String ht, String tva, String ttc, String tFraisPort, String remise, String aquite, String sPar, String sLe, String observation) {
            this.id = id;
            this.date = date;
            this.numClient = numClient;
            this.adrsFact = adrsFact;
            this.modeR = modeR;
            this.ht = ht;
            this.tva = tva;
            this.ttc = ttc;
            this.tFraisPort = tFraisPort;
            this.remise = remise;
            this.aquite = aquite;
            this.sPar = sPar;
            this.sLe = sLe;
            this.observation = observation;
        }
        
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getNumClient() {
            return numClient;
        }

        public void setNumClient(String numClient) {
            this.numClient = numClient;
        }

        public String getAdrsFact() {
            return adrsFact;
        }

        public void setAdrsFact(String adrsFact) {
            this.adrsFact = adrsFact;
        }

        public String getModeR() {
            return modeR;
        }

        public void setModeR(String modeR) {
            this.modeR = modeR;
        }

        public String getHt() {
            return ht;
        }

        public void setHt(String ht) {
            this.ht = ht;
        }

        public String getTva() {
            return tva;
        }

        public void setTva(String tva) {
            this.tva = tva;
        }

        public String getTtc() {
            return ttc;
        }

        public void setTtc(String ttc) {
            this.ttc = ttc;
        }

        public String gettFraisPort() {
            return tFraisPort;
        }

        public void settFraisPort(String tFraisPort) {
            this.tFraisPort = tFraisPort;
        }

        public String getRemise() {
            return remise;
        }

        public void setRemise(String remise) {
            this.remise = remise;
        }

        public String getAquite() {
            return aquite;
        }

        public void setAquite(String aquite) {
            this.aquite = aquite;
        }

        public String getsPar() {
            return sPar;
        }

        public void setsPar(String sPar) {
            this.sPar = sPar;
        }

        public String getsLe() {
            return sLe;
        }

        public void setsLe(String sLe) {
            this.sLe = sLe;
        }

        public String getObservation() {
            return observation;
        }

        public void setObservation(String observation) {
            this.observation = observation;
        }

        public boolean isInsert() {
            return insert;
        }

        public void setInsert(boolean insert) {
            this.insert = insert;
        }
        
        
    }
    
}
