package UTPPP;

import java.util.*;

/**
 * Kelas utama yang mengatur seluruh aktivitas gym:
 * - Registrasi member
 * - Top up saldo
 * - Transaksi layanan
 * - Cek data member
 */
class SistemGym {

    private Map<String, MemberGym> databaseMember = new HashMap<>();

    private final Map<String, Integer> katalogLayanan = Map.of(
        "cardio", 20000,
        "yoga", 25000,
        "personal_training",40000
    );

    //method buat mendaftarkan member baru ke sistem
    public void registrasi(String tipe, String id, String nama) {

        // validasi ID gaboleh duplikat
        if (databaseMember.containsKey(id)) {
            System.out.println("Member sudah terdaftar");
            return;
        }

        MemberGym entitas;

        // Penentuan tipe member (basic polymorphism)
        if (tipe.equals("REGULAR")) {
            entitas = new RegularGym(id, nama);
        } else if (tipe.equals("VIP")) {
            entitas = new VIPGym(id, nama);
        } else {
            // buat kalo ada yang aneh
            System.out.println("Tipe member tidak dikenal");
            return;
        }

        //simpan ke database
        databaseMember.put(id, entitas);

        System.out.println(tipe + " " + id + " berhasil ditambahkan");
    }

    // Method untuk menambahkan saldo ke akun member
    public void isiSaldo(String id, int nominal) {

        MemberGym memberGym = databaseMember.get(id);

        // validasi member
        if (memberGym == null) {
            System.out.println("Member tidak ditemukan");
            return;
        }

        // validasi nominal gaboleh nol/negatif
        if (nominal <= 0) {
            System.out.println("Nominal tidak valid");
            return;
        }

        // tambahkan saldo
        memberGym.saldoDompet += nominal;

        System.out.println("Saldo " + id + ": " + memberGym.saldoDompet);
    }

    // method buat transaksi layanan gym

    public void transaksi(String id, String layanan, int sesi) {

        // ini cek member
        MemberGym memberGym = databaseMember.get(id);
        if (memberGym == null) {
            System.out.println("Member tidak ditemukan");
            return;
        }

        // ini cek layanan di katalog
        Integer harga = katalogLayanan.get(layanan);
        if (harga == null) {
            System.out.println("Layanan tidak valid");
            return;
        }

        // Validasi tambahan(biar kaya fithub)
        if (sesi <= 0) {
            System.out.println("Jumlah sesi tidak valid");
            return;
        }

        // ini buat hitung total (logic ada di class MemberGym)
        int total = memberGym.prosesTagihan(harga, sesi);

        // ini buat cek saldo cukup atau tidak
        if (memberGym.saldoDompet < total) {
            System.out.println("Saldo " + id + " tidak cukup");
            return;
        }

        // ini buat potong saldo
        memberGym.saldoDompet -= total;

        // Output sesuai requirement soal
        System.out.println("Total bayar " + id + ": " + total);
        System.out.println("Saldo " + id + ": " + memberGym.saldoDompet);
    }

    //  Menampilkan detail member berdasarkan ID
    public void detailMember(String id) {

        MemberGym memberGym = databaseMember.get(id);

        if (memberGym == null) {
            System.out.println("Member tidak ditemukan");
        } else {
            System.out.println(
                memberGym.kodeMember + " | " +
                memberGym.namaMember + " | " +
                memberGym.tipe() + " | saldo: " +
                memberGym.saldoDompet
            );
        }
    }

    //  Menghitung total member aktif dalam sistem
    public void totalMember() {
        System.out.println("Total member: " + databaseMember.size());
    }
}