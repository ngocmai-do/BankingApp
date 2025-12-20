public class PrivateCustomer extends Customer {
    private String personNummer;

    public String getPersonNummer() {
        return personNummer;
    }

    public void setPersonNummer(String personNummer) {
        this.personNummer = personNummer;
    }

    public PrivateCustomer (String accountNumber, String name, String address, String email, String personNummer, String password, int balance) {
        setAccountNumber(accountNumber);
        setName(name);
        setAddress(address);
        setEmail(email);
        this.personNummer = personNummer;
        setPassword(password);
        setAccountBalance(balance);
    }

    @Override
    public String getAllInfo() {
        return getAccountNumber() + "; " + getName() + "; " + getAddress() + "; " + getEmail() + "; " + getPersonNummer() + "; " + getPassword() + "; " + getAccountBalance();
    }
}
