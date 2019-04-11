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
public class Menu2 {
    private double totalBlok;
    private double blokIndex;

    public Menu2(int n, float bfr, float fanOut) {
        double jml = n/bfr;
        double x = n/fanOut;
        this.totalBlok = Math.ceil(jml);
        this.blokIndex = Math.ceil(x);
    }

    public double getTotalBlok() {
        return totalBlok;
    }

    public double getBlokIndex() {
        return blokIndex;
    }
    
    public void tampilkanData(String namaTabel){
        System.out.println("Tabel Data "+namaTabel+" = "+this.totalBlok);
        System.out.println("Index "+namaTabel+" = "+this.blokIndex);
    }
    
    
    
    
}
