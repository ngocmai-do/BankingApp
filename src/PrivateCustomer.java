public class PrivateCustomer extends Customer {
    private String personNummer;

    public PrivateCustomer(String name, String personNummer) {
        super(name);
        this.personNummer = personNummer;
    }
}
