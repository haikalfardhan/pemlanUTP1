package UTPPP;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemGym engine = new SistemGym();

        System.out.println("Fardhan Haikal Taufik");
        System.out.println("255150707111008");
        System.out.println("TI-A");

        int jumlahInput = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < jumlahInput; i++) {

            String baris = sc.nextLine();
            String[] token = baris.split(" ");

            try {
                switch (token[0]) {

                    case "ADD":
                        engine.registrasi(token[1], token[2], token[3]);
                        break;

                    case "TOPUP":
                        engine.isiSaldo(token[1], Integer.parseInt(token[2]));
                        break;

                    case "BUY":
                        engine.transaksi(token[1], token[2], Integer.parseInt(token[3]));
                        break;

                    case "CHECK":
                        engine.detailMember(token[1]);
                        break;

                    case "COUNT":
                        engine.totalMember();
                        break;

                    default:
                        System.out.println("Perintah ga dikenal nih");
                }

            } catch (Exception e) {
                System.out.println("Input ora valid");
            }
        }
    }
}
