package UTPPP;

class VIPGym extends MemberGym {

    public VIPGym(String kodeMember, String namaMember) {
        super(kodeMember, namaMember);
    }

    public String tipe() {
        return "VIP";
    }
}