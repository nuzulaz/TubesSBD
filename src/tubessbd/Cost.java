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
    private String A1key;
    private String A1non;
    private String A2;
    private String A3;
    private int cost;

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
}
