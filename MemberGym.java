package UTPPP;

abstract class MemberGym {
    String kodeMember;
    String namaMember;
    int saldoDompet;

    public MemberGym(String kodeMember, String namaMember) {
        this.kodeMember = kodeMember;
        this.namaMember = namaMember;
        this.saldoDompet = 0;
    }

    abstract String tipe();

    public int prosesTagihan(int hargaSatuan, int jumlahSesi) {
        int nilaiDasar = hargaSatuan * jumlahSesi;

        if (nilaiDasar <= 0) return 0;

        if (jumlahSesi > 5) {
            int potonganVolume = (int)(0.1 * nilaiDasar);
            nilaiDasar -= potonganVolume;
        }

        if (tipe().equals("VIP")) {
            int potonganVip = (int)(0.15 * nilaiDasar);
            nilaiDasar -= potonganVip;
        }

        int pajak = (int)(0.05 * nilaiDasar);
        int totalAkhir = nilaiDasar + pajak;

        return Math.max(totalAkhir, 0);
    }
}