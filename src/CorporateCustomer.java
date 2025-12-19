public class CorporateCustomer extends Customer {
    private String organizationNumber;

    public CorporateCustomer(String name, String organizationNumber) {
        super(name);
        this.organizationNumber = organizationNumber;
    }
}
