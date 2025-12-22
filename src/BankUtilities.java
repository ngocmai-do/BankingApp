import java.util.List;

public class BankUtilities {
    DatabaseReaderWriter databaseReaderWriter = DatabaseReaderWriter.getInstance();
    List<Customer> privateCustomerList = databaseReaderWriter.readPrivateCustomerFile();
    List<Customer> corporateCustomerList = databaseReaderWriter.readCorpCustomerFile();

    public static String bankLogIn (String password) {
        if (password.trim().equals("mai&farah")) {
            return "Välkommen!";
        } else {
            return "Fel lösenord!";
        }
    }

    public void seeAllCustomer() {
        databaseReaderWriter.printCustomerList(privateCustomerList);
        databaseReaderWriter.printCustomerList(corporateCustomerList);
    }

    public String lookUpCustomer (String name) {
        name = name.trim();

        // Check private customers
        for (int i = 0; i < privateCustomerList.size(); i++) {
            if (name.equals(privateCustomerList.get(i).getName())) {
                privateCustomerList.get(i).getAllInfo();
            }
        }

        // Check corporate customers
        for (int i = 0; i < corporateCustomerList.size(); i++) {
            if (name.equals(corporateCustomerList.get(i).getName())) {
                corporateCustomerList.get(i).getAllInfo();
            }
        }

        // If no account number matched
        return "Det finns ingen kund med namnet: " + name;
    }
}
