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
public class Cost {

    private int cost;
    private String algoA1="A1";
    private String algoA2="A2";
    private String algoA3="A3";

    public Cost() {
    }
    
    public double logOfBase(int base, int num) {
        return Math.log(num) / Math.log(base);
    }
    
    public void rumusA1keys(int b){
       int tr = b/2;
       System.out.println("A1 Keys = ts + "+tr+" tr");
       System.out.println("Cost = "+tr+" blok");
    }
    public void rumusA1non(int b){
        int tr = b;
        System.out.println("A1 non= ts + "+tr+" tr");
        System.out.println("Cost = "+tr+" blok");
    }
    public void rumusA2(int y, int n){
        
        double h = Math.ceil(this.logOfBase(y,n));
        double tmp = h+1;
        
        System.out.println("A2 Keys ="+tmp+"tr + "+tmp+"ts");
        System.out.println("Cost = "+tmp+" blok");
    }
    
    public void rumusA3(int y,int n){
       double h = Math.ceil(this.logOfBase(y,n));
       double ts = h+1;
       double tr = ts + n;
       System.out.println("A3 ="+tr+"tr + "+ts+"ts");
       System.out.println("Cost = "+tr+" blok");
       
    }
    
    public long BLNJblok(int br, int bs){
        long worst = br * bs + bs;
        return worst;
    }
    
    public int BLNJseek(int br){
        return 2 * br;
    }
    
    public int compareBLNJ(int x, int y){
        if(x>=y){
            return y;
        }else{
            return x;
        }
    }


    public int getCost() {
        return this.cost;
    }
    
    
    public int costA1keys(int b){
        return b/2;
    }
    
    public int costA1non(int b){
        return b;
    }
    
    public double costA2(int y, int n){
        double h = Math.ceil(this.logOfBase(y,n));
        double tmp = h+1;
        return tmp;
    }
    
    public double costA3(int y, int n){
       double h = Math.ceil(this.logOfBase(y,n));
       double ts = h+1;
       double tr = ts + n;
        System.out.println(tr);
       return tr;
    }

    
    
    public double compareKeys(double costA1keys, double costA2){
        double tmp = 0;
        
        if(costA1keys > costA2){
            tmp = costA2;
        }else{
            tmp = costA1keys;
        }
        return tmp;
    }
    
    public double compareNonKeys(double costA1non, double costA3){
        double tmp = 0;
        
        if(costA1non > costA3){
            tmp = costA3;
        }else{
            tmp = costA1non;
        }
        return tmp;
    }

    public String getAlgoA1() {
        return algoA1;
    }

    public String getAlgoA2() {
        return algoA2;
    }

    public String getAlgoA3() {
        return algoA3;
    }
    
    
    
    
}