public abstract class Customer {
    private String name;
    private String accountNumber;
    private int accountBalance;

    public Customer(String name) {
        this.name = name;
        this.accountBalance = 0;
        this.accountNumber = String.valueOf(generateNewAccountNum());
    }

    public StringBuilder generateNewAccountNum () {  //generate new account number when a customer create a bank account
        StringBuilder accountNum = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int number = (int) (Math.random() * 10);
            accountNum.append(number);
        }
        return accountNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }


}
