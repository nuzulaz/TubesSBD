/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubessbd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author nuzul
 */
public class TubesSBD {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        String csvFile = "FlightQEP.txt"; //sesuai sama path filenya dimana
        BufferedReader br = null;
        
        try {     
                br = new BufferedReader(new FileReader(csvFile));
                br.readLine();
                String Customer = br.readLine();
                String Flight = br.readLine();
                String Booking = br.readLine();
                System.out.println(Customer);
                System.out.println(Flight);
                System.out.println(Booking);
                Scanner sql = new Scanner(System.in);// Persiapan Inpu Data

                CSv tab1 = new CSv(Customer); //Ambil data CSV
                CSv tab2 = new CSv(Flight); //Ambil data CSV
                CSv tab3 = new CSv(Booking); //Ambil data CSV
                CSv anonymus = new CSv();
                
//                System.out.println("");
                
                String x = sql.nextLine(); // Input Data
                String[] select = x.split(" ");//Split berdasarkan Split
                
                if(select[0].equals("Select") && select[2].equals("From")){
                            String[] colName = select[1].split(",");
                            //ngecek array dalam 1 tabel, ex : select id,nama from sekolah
                            System.out.println(select.length);
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
                            }else if(select.length >=7){
                                if(select[4].equals("Join") && select[6].equals("Using")){
                                    String keys = anonymus.getKeys(select[7]);
//                                    System.out.println(keys);
                                    boolean cek = anonymus.getComma(select[7]);   
                                            if(cek){
                                                String joinName = "";
                                                String[] tabJoin = null;
                                                String[] tabTmp = null;
                                                String namaTmp = "";
                                                String tableName = select[5];
                                                
                                                //ngecek data nya ada di tabel di csv atau engga
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
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
}
