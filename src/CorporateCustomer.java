public class CorporateCustomer extends Customer {

    private String organizationNumber;
    private int shareOwned;

    public CorporateCustomer (String accountNumber, String name, String organizationNumber, String address, String email, String password, int balance, int shareOwned) {
        setAccountNumber(accountNumber);
        setName(name);
        setAddress(address);
        setEmail(email);
        this.organizationNumber = organizationNumber;
        setPassword(password);
        setAccountBalance(balance);
        this.shareOwned = shareOwned;
    }


    public String getOrganizationNumber() {
        return organizationNumber;
    }

    public void setOrganizationNumber(String organizationNumber) {
        this.organizationNumber = organizationNumber;
    }

    public int getShareOwned() {
        return shareOwned;
    }

    public void setShareOwned(int shareOwned) {
        this.shareOwned = shareOwned;
    }

    @Override
    public String getAllInfo() {
        return getAccountNumber() + "; " + getName() + "; " + getAddress() + "; " + getEmail() + "; " + getOrganizationNumber() + "; " + getPassword() + "; " + getAccountBalance() + "; " + getShareOwned();
    }
}
