import java.util.List;

public class CustomerUtilities {
    static DatabaseReaderWriter databaseReaderWriter = DatabaseReaderWriter.getInstance();
    static List<Customer> privateCustomerList = databaseReaderWriter.readPrivateCustomerFile();
    static List<Customer> corporateCustomerList = databaseReaderWriter.readCorpCustomerFile();

    public static String loggingIn (String accountNumber, String password) {
        accountNumber = accountNumber.trim();
        password = password.trim();

        // Check private customers
        for (int i = 0; i < privateCustomerList.size(); i++) {
            if (accountNumber.equals(privateCustomerList.get(i).getAccountNumber())) {
                if (password.equals(privateCustomerList.get(i).getPassword())) {
                    return "Välkommen " + privateCustomerList.get(i).getName();
                } else {
                    return "Fel lösenord!";
                }
            }
        }

        // Check corporate customers
        for (int i = 0; i < corporateCustomerList.size(); i++) {
            if (accountNumber.equals(corporateCustomerList.get(i).getAccountNumber())) {
                if (password.equals(corporateCustomerList.get(i).getPassword())) {
                    return "Välkommen " + corporateCustomerList.get(i).getName();
                } else {
                    return "Fel lösenord!";
                }
            }
        }

        // If no account number matched
        return "Fel kontonummer!";
    }
}
