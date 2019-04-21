/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubessbd;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author nuzul
 */
public class MainClass {
    public static void main(String[] args) throws IOException {
        System.out.println(">> Menu Utama :");
        System.out.println("1. Tampilkan BFR dan Fanaout Ratio Setiap Tabel");
        System.out.println("2. Tampilkan Blok Data + Blok Index Setiap Tabel");
        System.out.println("3. Tampilkan Blok Yang Diakses Untuk Pencarian Record");
        System.out.println("4. Tamopilkan QEP dan Costs");
        System.out.println("5. Tampilkan Isi File Shared Pool");
        System.out.println(">> Masukan Pilihan Anda :");
        Scanner scn = new Scanner(System.in);
        String pil = scn.nextLine();
        Dictionary csv = new Dictionary();
        CSv customerTabel = new CSv(csv.getCustomerData());
        CSv bookingTabel = new CSv(csv.getBookingData());
        CSv flightTabel = new CSv(csv.getFlightData());
        Menu1 Customer = new Menu1(csv.getBlockSize(),customerTabel.getR(),csv.getPointer(),customerTabel.getV());
        Menu1 Booking = new Menu1(csv.getBlockSize(),bookingTabel.getR(),csv.getPointer(),bookingTabel.getV());
        Menu1 Flight = new Menu1(csv.getBlockSize(),flightTabel.getR(),csv.getPointer(),flightTabel.getV());
        switch(pil){
            case "1" :
                Customer.tampilkanBFR(customerTabel.getNamaTabel());
                Booking.tampilkanBFR(bookingTabel.getNamaTabel());
                Flight.tampilkanBFR(flightTabel.getNamaTabel());
                break;
            case "2" : 
                Menu2 jumlahCust = new Menu2(customerTabel.getN(),Customer.getBfr(),Customer.getVanaoutRatio());
                Menu2 jumlahFlight = new Menu2(flightTabel.getN(),Flight.getBfr(),Flight.getVanaoutRatio());
                Menu2 jumlahBook = new Menu2(bookingTabel.getN(),Booking.getBfr(),Booking.getVanaoutRatio());
                jumlahCust.tampilkanData(customerTabel.getNamaTabel());
                jumlahFlight.tampilkanData(flightTabel.getNamaTabel());
                jumlahBook.tampilkanData(bookingTabel.getNamaTabel());
                break;
            case "3" :
                Scanner scan1 = new Scanner(System.in);
                Scanner scan2 = new Scanner(System.in);
                System.out.println("Cari rekord ke- :" );
                int jmlRecord = scan1.nextInt();
                System.out.println("Nama Tabel :");
                String tabel = scan2.nextLine();
                if(tabel.equals("Customer")){
                    if(jmlRecord <= customerTabel.getN()){
                        Menu3 customerBlok = new Menu3(Customer.getBfr(),jmlRecord,Customer.getVanaoutRatio());
                        customerBlok.display("Customer");
                    }
                }else if(tabel.equals("Booking")){
                    if(jmlRecord <= bookingTabel.getN()){;
                    }
                }else if(tabel.equals("Flight")){
                    if(jmlRecord <= flightTabel.getN()){
                    
                    }
                }else{
                    System.out.println("Tabel Tidak Ditemukan ");
                }
                break;
            case "4" :
                Scanner scanner = new Scanner(System.in);
                System.out.println("Input Query :");
//                String query = scanner.nextLine();
                Menu4 coba = new Menu4("Select Nama,Username From Customer Where No_identitas=azmi");
                coba.display();
                break;
            case "5" :
                DataSharedPool sp = new DataSharedPool();
                sp.ReadSharedPool();
//                CSv x = new CSv();
//                System.out.println(Math.ceil(x.logOfBase(10, 1000)));
                break;
        }
        
    }
}
