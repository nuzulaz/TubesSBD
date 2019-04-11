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
public class Menu3 {
    private double blokIndex;
    private double blokTanpaIndex;

    public Menu3(float bfr, int n, float fanout) {
        float tmp = (n/bfr);
        float tmp2 = (n/fanout)+1;
        this.blokTanpaIndex = Math.ceil(tmp);
        this.blokIndex = Math.ceil(tmp2);
    }

    public double getBlokIndex() {
        return blokIndex;
    }

    public double getBlokTanpaIndex() {
        return blokTanpaIndex;
    }
    
    public void display(String namaTabel){
        System.out.println("Menggunakan indeks, jumlah blok yang diakses : "+this.blokIndex);
        System.out.println("Tanpa indeks, jumlah blok yang diakses : "+this.blokTanpaIndex);
    }
    
    
    
    
}
