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
                Menu1 Customer = new Menu1(csv.getBlockSize(),tab1.getR(),csv.getPointer(),tab1.getV());  
                Menu1 Flight = new Menu1(csv.getBlockSize(),tab2.getR(),csv.getPointer(),tab2.getV());
                Menu1 Booking = new Menu1(csv.getBlockSize(),tab3.getR(),csv.getPointer(),tab3.getV());
                                          
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
                                        String[] tableName = select[3].split(";");
                                        String[] tab = null;
                                        double biaya1 = 0;
                                        double biaya2 = 0;
                                        double total = 0;
                                        String algo = null;
                                        if(tableName[0].equals(tab1.getNamaTabel())){
                                                if(anonymus.isKeys(select[5], "No_identitas")){
                                                  biaya1 = cost.costA1keys(tab1.getN());
                                                  biaya2 = cost.costA2(Math.round(Customer.getVanaoutRatio()), tab1.getN());
                                                  total = cost.compareKeys(biaya1, biaya2);
                                                }else{
                                                  biaya1 = cost.costA1non(tab1.getN());
                                                  biaya2 = cost.costA3(Math.round(Customer.getVanaoutRatio()), tab1.getN());
                                                  total = cost.compareNonKeys(biaya1, biaya2);
                                                }
                                              algo = cost.getAlgo();
                                              tab = tab1.getTab();
                                        }else if(tableName[0].equals(tab2.getNamaTabel())){
                                            if(anonymus.isKeys(select[5], "Kode_Booking")){
                                                    biaya1 = cost.costA1keys(tab2.getN());
                                                    biaya2 = cost.costA2(Math.round(Flight.getVanaoutRatio()), tab2.getN());
                                                    total = cost.compareKeys(biaya1, biaya2);
                                            }else{
                                                    biaya1 = cost.costA1non(tab2.getN());
                                                    biaya2 = cost.costA3(Math.round(Flight.getVanaoutRatio()), tab2.getN());
                                                    total = cost.compareNonKeys(biaya1, biaya2);
                                            } 
                                            algo = cost.getAlgo();
                                            tab = tab2.getTab();
                                        }else if(tableName[0].equals(tab3.getNamaTabel())){
                                            if(anonymus.isKeys(select[5], "Kode_Flight")){
                                                    biaya1 = cost.costA1keys(tab3.getN());
                                                    biaya2 = cost.costA2(Math.round(Booking.getVanaoutRatio()), tab3.getN());
                                                    total = cost.compareKeys(biaya1, biaya2);
                                            }else{
                                                    biaya1 = cost.costA1non(tab3.getN());
                                                    biaya2 = cost.costA3(Math.round(Booking.getVanaoutRatio()), tab3.getN());
                                                    total = cost.compareNonKeys(biaya1, biaya2);
                                            }
                                            algo = cost.getAlgo();
                                            tab = tab3.getTab();
                                        }
                                        //Aksi
                                        anonymus.tampilData(colName, tab, tableName[0]);
                                        anonymus.tampilQepBasic(tab, select[5], colName, tableName[0], algo, biaya1);
                                        anonymus.tampilQepBasic(tab, select[5], colName, tableName[0], algo, biaya2);
                                        System.out.println("QEP optimal : "+ algo);
                                }
                            }else if(select.length >=7){
                                if(select[4].equals("Join") && select[6].equals("Using")){
                                    String keys = anonymus.getKeys(select[7]);
                                    boolean cek = anonymus.getComma(select[7]);   
                                    int bs=0;
                                            if(cek){
                                                String joinName = "";
                                                String[] tabJoin = null;
                                                String[] tabTmp = null;
                                                String namaTmp = "";
                                                String tableName = select[5];
                                                
                                                if(tableName.equals(tab1.getNamaTabel())){
                                                    joinName = tab1.getNamaTabel();
                                                    tabJoin = tab1.getTab();
                                                    bs = tab1.getN();
                                                }else if(tableName.equals(tab2.getNamaTabel())){
                                                    joinName = tab2.getNamaTabel();
                                                    tabJoin = tab2.getTab();
                                                    bs = tab2.getN();
                                                }else if(tableName.equals(tab3.getNamaTabel())){
                                                    joinName = tab3.getNamaTabel();
                                                    tabJoin = tab3.getTab();
                                                    bs = tab3.getN();
                                                }
                                                //ngecek from nama tabel apa sama ga sama data di csv tabel
                                                int br=0;
                                                if(!tabJoin.equals(null)){
                                                    if(select[3].equals(tab1.getNamaTabel())){
                                                         tabTmp = tab1.getTab();
                                                         namaTmp = tab1.getNamaTabel();                                                   
                                                         br = tab1.getN();
                                                    }else if(select[3].equals(tab2.getNamaTabel())){
                                                         tabTmp = tab2.getTab();
                                                         namaTmp = tab2.getNamaTabel();
                                                         br = tab2.getN();
                                                    }else if(select[3].equals(tab3.getNamaTabel())){
                                                         tabTmp = tab3.getTab();
                                                         namaTmp = tab3.getNamaTabel();
                                                         br = tab3.getN();
                                                    }else{
                                                        System.out.println("Missing Table!");
                                                    }
                                                    //
                                                    boolean joinKeys = anonymus.cekKeys(keys, tabTmp, tabJoin);
                                                    if(joinKeys){
                                                        CSv joinTab = new CSv();
                                                        Cost cost = new Cost();
                                                        System.out.println(namaTmp);
                                                        System.out.println(joinName);
                                                        long qep1 = cost.BLNJblok(br, bs);
                                                        long qep2 = cost.BLNJblok(bs, br);
                                                        joinTab.tampilJoin(colName, tabTmp,tabJoin, namaTmp);
                                                        joinTab.tampilJoin(colName,tabJoin,tabTmp, joinName);
                                                        System.out.println("");
                                                        joinTab.tampilQepJoin(colName, tabJoin, tabTmp, namaTmp, joinName,cost.BLNJblok(br, bs), "b");
                                                        joinTab.tampilQepJoin(colName, tabJoin, tabTmp, joinName, namaTmp, cost.BLNJblok(bs, br), "b");
                                                        if(qep1 >= qep2){
                                                            System.out.println("Solusi Optimal QEP2");
                                                        }else{
                                                            System.out.println("Solusi Optimal QEP1");
                                                        }
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
