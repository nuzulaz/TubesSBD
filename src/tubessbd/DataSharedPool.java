/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubessbd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nuzul
 */
public class DataSharedPool {
    private String query;
    private String cost;
    private String qep;
    private BufferedReader br = null;
    
    public DataSharedPool() throws IOException {
        br = new BufferedReader(new FileReader("SharedPool.txt"));
    }
      public DataSharedPool(String query){
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public String getCost() {
        return cost;
    }

    public String getQep() {
        return qep;
    }
    
    public void saveToSharedPool(String query, String qep) throws IOException{
        
        File file = new File("SharedPool.txt");
        FileWriter fw = new FileWriter(file,true);
        PrintWriter pw = new PrintWriter(fw);
//        System.out.println(qep);
        String[] data = qep.split("%");
        pw.println(query);
        pw.println(data[0]);
        pw.println(data[1]);
        pw.println(data[2]);
        pw.println(data[3]);
        pw.println();
        pw.close();
    }
    
    public void ReadSharedPool() throws IOException{
        BufferedReader x = new BufferedReader(new FileReader("SharedPool.txt"));
        String line="";
        while ((line = x.readLine()) != null) {
                // use comma as separator
                System.out.println(line);
        }
    }
    
    public boolean isDuplicate(String qep) throws IOException{
            List<String> data = new ArrayList<>();
            String line ="";
            String cek = "";
            String[] tmp = null;
            boolean hasil = false;
            int i =0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                if(!line.equals("") && i!=5){
                    if(i!=0){
                        cek+=line;
                        i++;
                    }else{
                        i++;
                        cek = "";
                    }
                }else{
                    i=0;
                    data.add(cek);
                }
            }
            for(String dataSp: data) {
               
                if(dataSp.equals(qep)){
                    hasil = true;
                }
                
            }
            
            return hasil;
}
}
