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

/**
 *
 * @author nuzul
 */
public class Dictionary {

    private int pointer;
    private int blockSize;
    private String customerData;
    private String flightData;
    private String bookingData;
    private String rowBR;
    

    public Dictionary() {
        String csvFile = "FlightQEP.txt"; //sesuai sama path filenya dimana
        BufferedReader br = null; 
        
    try {     
                br = new BufferedReader(new FileReader(csvFile));
                this.rowBR= br.readLine();
                this.customerData = br.readLine();
                this.flightData= br.readLine();
                this.bookingData = br.readLine();
                
                String[] dataRow = this.rowBR.split(";");
                String[] dataPointer = dataRow[0].split(" ");
                String[] dataBlock = dataRow[1].split(" ");
                String[] splitBlock = dataBlock[2].split("#");
                this.pointer = Integer.parseInt(dataPointer[1]);
                this.blockSize = Integer.parseInt(splitBlock[0]);

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

    public int getPointer() {
        return pointer;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public String getCustomerData() {
        return customerData;
    }

    public String getFlightData() {
        return flightData;
    }

    public String getBookingData() {
        return bookingData;
    }
    
    
    
    
}

