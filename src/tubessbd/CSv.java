/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubessbd;

/**
 *
 * @author nuzul
 */
public class CSv {

    private String[] tab;
    private String[] namaTabel= new String[1];
    private String QEP;
    private String QEPjoin;
    private int R;
    private int n;
    private int V;
    public CSv(String csvFile){

            String[] isiTabel = csvFile.split(" ");// Misahin data tabel sama keterangan R dll
            String[] tampung = isiTabel[0].split(";");
            this.namaTabel[0] = tampung[0];
            this.tab = tampung[1].split(",");
            
            String[] splitR = isiTabel[2].split(";");// nilai R 
            String[] splitn = isiTabel[4].split(";");// nilai n 
            String[] splitV = isiTabel[6].split("#");// nilai v 
            this.R = Integer.parseInt(splitR[0]);
            this.n = Integer.parseInt(splitn[0]);
            this.V = Integer.parseInt(splitV[0]);
    }

    public CSv() {
    }
    
    
    
    public boolean getComma(String input){
        boolean cek=true;
        //ngecek nama tabel sebelum ;
        char tmp = input.charAt(input.length()-1); 
        if(tmp == ';'){
            cek = true;
        }else{
            cek = false;
        }
        return cek;
    }
    
    //ilangin tanda kurung (id)
    public String getKeys(String input){
   
        String[] tmp = input.split("\\(");
        String[] sameKey = tmp[1].split("\\)");   
        return sameKey[0];
    }
    
    public boolean isKeys(String input,String keys){
        String[] tmp = input.split("=");
        String cekKeys = tmp[0];
        System.out.println(cekKeys+"----"+keys);
        if(cekKeys.equals(keys)){
            return true;
        }else{
            return false;
        }
    }
    
    
    //
    public boolean searchColumnJoin(String[] input, String[] csv1, String[] csv2){
        boolean cek = true;
        boolean tmp1 = true;
        boolean tmp2 = true;
        int countTrue=0;
        if(input.length <= csv1.length){
//            System.out.println("cek");
                for (int i = 0; i < input.length; i++) {
                    for (int j = 0; j < csv1.length; j++){
                        if(input[i].equals(csv1[j])){
                            tmp1 = true;
                        }
                    }
                    
                    for (int k = 0; k < csv2.length; k++){
                        if(input[i].equals(csv2[k])){
                            tmp2 = true;
                        }
                    }
                  }
//                System.out.println(countTrue);
                if(tmp1 && tmp2){
                    cek=true;
                }else{
                    cek=false;
                }
        }
        return cek; 
    }
    
    //ngecek foreign key
    public boolean cekKeys(String data,String[] tab1, String[] tab2){ 
        
        boolean cek = false;
        int count = 0;
        
        //ngecek dua tabel
        for (int i = 0; i < tab1.length; i++) {
            if(data.equals(tab1[i])){
//                System.out.println(tab1[i]);
                    //ngecek dua tabel yang dijoinin
                    count++;
            }
        }
//        System.out.println("-------------");
        for (int i = 0; i < tab2.length; i++) {
            if(data.equals(tab2[i])){
//                System.out.println(tab2[i]);
                count++;
            }
        }
        //negeck 2 tabel yang dijoin
//        System.out.println(count);
        if(count>=2){
            cek = true;
        }else{
            cek = false;
        }
//        System.out.println(cek);
        return cek;
    }
    
    //ketika array 0 = * dan cuma ada 1 char
    public boolean searchColumn(String[] input, String[] csv){
        boolean cek = true;
        int countTrue=0;
        if(input[0].equals("*") && input.length == 1){
            cek = true;
        }else if(input.length <= csv.length){
                for (int i = 0; i < input.length; i++) {
                    for (int j = 0; j < csv.length; j++){
                        if(input[i].equals(csv[j])){
                            countTrue++;
                        }
                    }
                  }
                if(countTrue == input.length){
                    cek=true;
                }else{
                    cek=false;
                }
        }
        return cek;   
    }
    
    //menampilkan data sesuai input dan csv nya
    public void tampilData(String[] input, String[] csv){
                boolean cek=searchColumn(input,csv);               
                if(cek){
                    if(input[0].equals("*")){
                        System.out.println(">> Tabel : "+this.namaTabel[0]);
                        System.out.print("List Kolom  :");    
                        for (int j = 0; j < csv.length; j++) {
                                System.out.print(csv[j]+",");
                            }
                    }else{
                        System.out.println("Tabel : "+this.namaTabel[0]);
                        System.out.print("List Kolom  :");
                        for (int i = 0; i < input.length; i++) {
                            for (int j = 0; j < csv.length; j++) {
                                if(input[i].equals(csv[j])){
                                    System.out.print(input[i]+",");
                                }
                            }
                        }
                    }
                        
                }else{
                    System.out.println("Column Not Found !");
                }
    }
    public void tampilData(String[] input, String[] csv, String namaTabel){
                boolean cek=searchColumn(input,csv);               
                if(cek){
                    if(input[0].equals("*")){
                        System.out.println(">> Tabel : "+namaTabel);
                        System.out.print("List Kolom  :");    
                        for (int j = 0; j < csv.length; j++) {
                                System.out.print(csv[j]+",");
                            }
                    }else{
                        System.out.println("Tabel : "+namaTabel);
                        System.out.print("List Kolom  :");
                        for (int i = 0; i < input.length; i++) {
                            for (int j = 0; j < csv.length; j++) {
                                if(input[i].equals(csv[j])){
                                    System.out.print(input[i]+",");
                                }
                            }
                        }
                    }
                        
                }else{
                    System.out.println("Column Not Found !");
                }
    }
    
        public void tampilQepJoin(String[] input, String[] kolom1,String[] kolom2, String namaTabel1, String namaTabel2, double cost, String keys){
        boolean cek=searchColumn(kolom1,input);
        boolean col = searchColumnJoin(input,kolom1,kolom2);
            if(col){
                System.out.print("PROJECTION ");
                for (int i = 0; i < input.length; i++){
                    System.out.print(input[i]+",");
                }
                System.out.println("-- on the fly");
                System.out.println("Join "+ namaTabel1+"."+keys+" = "+ namaTabel2 +"."+ keys + "-- BNLJ");
                System.out.println(namaTabel1+"         "+namaTabel2);
                System.out.println(">> Cost : "+cost);

            }
        }
        
        public void tampilQepJoinNew(String[] input, String[] kolom1,String[] kolom2, String namaTabel1, String namaTabel2, int br, int bs, String keys){
//      boolean cek=searchColumn(kolom1,input);
        String kolom ="";
        boolean col = searchColumnJoin(input,kolom1,kolom2);
        Cost cost = new Cost();
        if(col){
            long qep1 = cost.BLNJblok(br, bs);// Nama Tabel From dulu baru join
            long qep2 = cost.BLNJblok(bs, br);// Nama Tabel Join dulu baru From 
            
            System.out.print("PROJECTION ");
            for (int i = 0; i < input.length; i++){
                System.out.print(input[i]+",");
                kolom += input[i]+",";
            }
            System.out.println("-- on the fly");
            System.out.println("Join "+ namaTabel1+"."+keys+" = "+ namaTabel2 +"."+ keys + "-- BNLJ");
            System.out.println(namaTabel1+"         "+namaTabel2);
            System.out.println(">> Cost : "+qep1);    
            
            System.out.print("PROJECTION ");
            for (int i = 0; i < input.length; i++){
                System.out.print(input[i]+",");
            }
            System.out.println("-- on the fly");
            System.out.println("Join "+ namaTabel2+"."+keys+" = "+ namaTabel1 +"."+ keys + "-- BNLJ");
            System.out.println(namaTabel2+"         "+namaTabel1);
            System.out.println(">> Cost : "+qep2);    
        
            if(qep1 > qep2){
               this.QEPjoin = "PROJECTION "+kolom+" -- on the fly%"+"Join "+namaTabel2+"."+keys+" = "+ namaTabel1 +"."+ keys + "-- BNLJ%"+namaTabel2+"   "+namaTabel1+"%Cost :"+qep2;
            }else{
               this.QEPjoin = "PROJECTION "+kolom+" -- on the fly%"+"Join "+namaTabel1+"."+keys+" = "+ namaTabel2 +"."+ keys + "-- BNLJ%"+namaTabel1+"   "+namaTabel2+"%Cost :"+qep1;
            }
            
        }
    }

    public String getQEPjoin() {
        return QEPjoin;
    }
        
    public void tampilQepNew(String kondisi, String[] input, String namaTabel, String algo1, String algo2, double biaya1, double biaya2){
            String kolom= "";
            System.out.println("PROJECTION ");
            for (int i = 0; i < input.length; i++){
                System.out.print(input[i]+",");
                kolom += input[i]+",";
            }
            System.out.println("-- on the fly");
            System.out.println("SELECTION " + kondisi +" -- "+algo1);
            System.out.println(namaTabel);
            System.out.println(">> Cost : "+biaya1);
            
            
            System.out.println("PROJECTION ");
            for (int i = 0; i < input.length; i++){
                System.out.print(input[i]+",");
            }
            System.out.println("-- on the fly");
            System.out.println("SELECTION " + kondisi +" -- "+algo2);
            System.out.println(namaTabel);
            System.out.println(">> Cost : "+biaya2);
   
            if(biaya1 <= biaya2){
                System.out.println("QEP Optimal = "+algo1);
                this.QEP ="PROJECTION "+kolom+" -- on the fly%"+"SELECTION "+kondisi+" --"+algo1+"%"+namaTabel+"%>>Cost :"+biaya1;
            }else{
                System.out.println("QEP Optimal = "+algo2);
                this.QEP ="PROJECTION "+kolom+" -- on the fly%"+"SELECTION "+kondisi+" --"+algo2+"%"+namaTabel+"%>>Cost :"+biaya2;
            }
            
//            System.out.println(this.QEP);
    }
    
    public String qepBanding(String qep){
        String[] data = qep.split("%");
        String isi = data[0]+data[1]+data[2]+data[3];
        return isi;
    
    }

    public String getQEP() {
        return QEP;
    }
    
    
    
    
    public void tampilQepBasic(String[] kolom, String kondisi, String[] input, String namaTabel, String algo, double cost){
        boolean cek=searchColumn(input,kolom);
        if(cek){
            System.out.println("PROJECTION ");
            for (int i = 0; i < input.length; i++){
                System.out.print(input[i]+",");
            }
            System.out.println("-- on the fly");
            System.out.println("SELECTION " + kondisi +" -- "+algo);
            System.out.println(namaTabel);
            System.out.println(">> Cost : "+cost);
        }
        
    }
    
    
    
    //kalo udah ketemu joinnya trus sesuai sama dengan data yang ada di csv dan input
    public void tampilJoin(String[] input, String[] csv,String[] tabJoin,String namaTabel){
                boolean cek = searchColumnJoin(input,csv,tabJoin);
                if(cek){
                    if(input.length <= csv.length){
                        System.out.println(">> Tabel : "+namaTabel);
                        System.out.print("List Kolom  :");
                        for (int i = 0; i < input.length; i++) {
                            for (int j = 0; j < csv.length; j++) {
                                if(input[i].equals(csv[j])){
                                   System.out.print(input[i]+",");
                                }
                            }
                        }
                    }else{
                        System.out.println("SQL ERROR");
                    }
                }else{
                    System.out.println("Column Not FOund!");
                }
    }
    public String[] getTab() {
        return tab;
    }

    public void setTab(String[] tab) {
        this.tab = tab;
    }

    public String getNamaTabel() {
        return namaTabel[0];
    }

    public void setNamaTabel(String[] namaTabel) {
        this.namaTabel = namaTabel;
    }

    public int getR() {
        return R;
    }

    public int getN() {
        return n;
    }

    public int getV() {
        return V;
    }
    
    
    
    
    
    
    

}
