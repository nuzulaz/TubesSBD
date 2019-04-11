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
public class Menu1 {
    private float bfr;
    private float vanaoutRatio;

    public Menu1(int B, int R, int P, int V) {
        this.bfr = B/R;
        this.vanaoutRatio = B/(P+V);
    }

    public float getBfr() {
        return bfr;
    }

    public float getVanaoutRatio() {
        return vanaoutRatio;
    }
    
    public void tampilkanBFR(String namaTabel){
        System.out.println("BFR "+namaTabel+" = "+this.bfr);
        System.out.println("Fan Out Ratio "+namaTabel+" = "+this.vanaoutRatio);
    }
    

}
