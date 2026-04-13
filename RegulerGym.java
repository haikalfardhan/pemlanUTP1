package UTPPP;

class RegularGym extends MemberGym {

    public RegularGym(String kodeMember, String namaMember) {
        super(kodeMember, namaMember);
    }

    public String tipe() {
        return "REGULAR";
    }
}
