/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubessbd;

import java.util.Scanner;

/**
 *
 * @author nuzul
 */
public class Menu4 {
    private String query;

    public Menu4(String x){
        this.query = x;
    }
    
    public void qep(){
        String[] select = this.query.split(" ");
        CSv anonymus = new CSv();
        if(select[0].equals("Select") && select[2].equals("From")){
                    String[] colName = select[1].split(",");
                    System.out.println(select.length);
                    if(select.length <= 4){
                        boolean cek = anonymus.getComma(select[3]);
                        if(cek==true){
                             
                        }else{
                            System.out.println("Missing ';' ");
                        }
                    }else if(select.length >=7){
                        if(select[4].equals("Join") && select[6].equals("Using")){
//                            String keys = anonymus.getKeys(select[7]);
//                            boolean cek = anonymus.getComma(select[7]);   
//                                    if(cek){
//        //                                                
//                                    }else{
//                                        System.out.println("Missing ';'");
//                                    }
                        }
                    }
        }else{  
            System.out.println("Missing Statement");
        }
        
    }
    
    
    public void display(){
                Dictionary csv = new Dictionary();
                CSv tab1 = new CSv(csv.getCustomerData());
                CSv tab2 = new CSv(csv.getBookingData());
                CSv tab3 = new CSv(csv.getFlightData());
                CSv anonymus = new CSv();
//                System.out.println(csv.getCustomerData());
//                System.out.println(csv.getBookingData());
//                System.out.println(csv.getFlightData());
                
                String[] select = this.query.split(" ");//Split berdasarkan Split
                
                if(select[0].equals("Select") && select[2].equals("From")){
                            String[] colName = select[1].split(",");
                            //ngecek array dalam 1 tabel, ex : select id,nama from sekolah
                            System.out.println(select.length);
//                            System.out.println(select[4]);
                            if(select.length <= 4){
                                boolean cek = anonymus.getComma(select[3]);
                                if(cek==true){
                                    String[] tableName = select[3].split(";");
                                    if(tableName[0].equals(tab1.getNamaTabel())){
                                        tab1.tampilData(colName, tab1.getTab());
                                    }else if(tableName[0].equals(tab2.getNamaTabel())){
                                        tab2.tampilData(colName, tab2.getTab());
                                    }else if(tableName[0].equals(tab3.getNamaTabel())){
                                        tab3.tampilData(colName, tab3.getTab());
                                    }
                                }else{
                                System.out.println("Missing ';' ");
                                }
                            }else if(select.length == 6){
                                if(select[4].equals("Where")){
                                        Cost cost  = new Cost();
//                                        System.out.println(select[5]);
                                        String[] tableName = select[3].split(";");
                                        String[] tab = null;
                                        if(tableName[0].equals(tab1.getNamaTabel())){
//                                            System.out.println(tab1.isKeys(select[5], "No_identitas"));
                                              if(anonymus.isKeys(select[5], "No_identitas")){
                                                  int n = tab1.getN();
                                                  cost.rumusA1keys(n);
                                              } 
                                              tab = tab1.getTab();
//                                            tab1.tampilData(colName, tab1.getTab());
//                                            tab1.tampilQepBasic(tab1.getTab(), select[5], colName);
                                        }else if(tableName[0].equals(tab2.getNamaTabel())){
                                            tab = tab2.getTab();
//                                            tab2.tampilData(colName, tab2.getTab());
                                        }else if(tableName[0].equals(tab3.getNamaTabel())){
                                            tab = tab3.getTab();
//                                            tab3.tampilData(colName, tab3.getTab());
                                        }
                                        //Aksi
                                        anonymus.tampilData(colName, tab, tableName[0]);
                                        anonymus.tampilQepBasic(tab, select[5], colName, tableName[0]);
                                }
                            }else if(select.length >=7){
                                if(select[4].equals("Join") && select[6].equals("Using")){
                                    String keys = anonymus.getKeys(select[7]);
                                    boolean cek = anonymus.getComma(select[7]);   
                                            if(cek){
                                                String joinName = "";
                                                String[] tabJoin = null;
                                                String[] tabTmp = null;
                                                String namaTmp = "";
                                                String tableName = select[5];
                                                
                                                if(tableName.equals(tab1.getNamaTabel())){
                                                    joinName = tab1.getNamaTabel();
                                                    tabJoin = tab1.getTab();
                                                }else if(tableName.equals(tab2.getNamaTabel())){
                                                    joinName = tab2.getNamaTabel();
                                                    tabJoin = tab2.getTab();
                                                }else if(tableName.equals(tab3.getNamaTabel())){
                                                    joinName = tab3.getNamaTabel();
                                                    tabJoin = tab3.getTab();
                                                }

                                                //ngecek from nama tabel apa sama ga sama data di csv tabel
                                                if(!tabJoin.equals(null)){
                                                    if(select[3].equals(tab1.getNamaTabel())){
//                                                         tab1.tampilJoin(colName, tab1.getTab(),tabJoin, tab1.getNamaTabel());
                                                         tabTmp = tab1.getTab();
                                                         namaTmp = tab1.getNamaTabel();                                                   
                                                    }else if(select[3].equals(tab2.getNamaTabel())){
//                                                         tab2.tampilJoin(colName, tab2.getTab(),tabJoin,tab2.getNamaTabel());
                                                         tabTmp = tab2.getTab();
                                                         namaTmp = tab2.getNamaTabel();
                                                    }else if(select[3].equals(tab3.getNamaTabel())){
//                                                         tab3.tampilJoin(colName, tab3.getTab(),tabJoin,tab3.getNamaTabel());
                                                         tabTmp = tab3.getTab();
                                                         namaTmp = tab3.getNamaTabel();
                                                    }else{
                                                        System.out.println("Missing Table!");
                                                    }
                                                    //
                                                    boolean joinKeys = anonymus.cekKeys(keys, tabTmp, tabJoin);
                                                    if(joinKeys){
                                                        CSv joinTab = new CSv();
                                                        joinTab.tampilJoin(colName, tabTmp,tabJoin, namaTmp);
                                                        joinTab.tampilJoin(colName,tabJoin,tabTmp, joinName);
                                                    }
                                                  
                                                }
                                            }else{
                                                System.out.println("Missing ';'");
                                            }
                                }
                            }
                }else{  
                    System.out.println("Missing Statement");
                }
    }
}
