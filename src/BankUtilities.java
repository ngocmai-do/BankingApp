import java.util.List;

public class BankUtilities {
    static DatabaseReaderWriter databaseReaderWriter = DatabaseReaderWriter.getInstance();
    static List<Customer> privateCustomerList = databaseReaderWriter.readPrivateCustomerFile();
    static List<Customer> corporateCustomerList = databaseReaderWriter.readCorpCustomerFile();

    public static String bankLogIn (String password) {
        if (password.trim().equals("mai&farah")) {
            return "Välkommen!";
        } else {
            return "Fel lösenord!";
        }
    }

    public static String seeAllCustomer() {
        return "Privata kunder:\n" + databaseReaderWriter.printCustomerList(privateCustomerList) +
                "\nFöretag kunder:\n" + databaseReaderWriter.printCustomerList(corporateCustomerList);
    }

    public static String lookUpCustomer (String name) {
        name = name.trim();

        // Check private customers
        for (int i = 0; i < privateCustomerList.size(); i++) {
            if (name.equalsIgnoreCase(privateCustomerList.get(i).getName())) {
                return privateCustomerList.get(i).getAllInfo();
            }
        }

        // Check corporate customers
        for (int i = 0; i < corporateCustomerList.size(); i++) {
            if (name.equalsIgnoreCase(corporateCustomerList.get(i).getName())) {
                return corporateCustomerList.get(i).getAllInfo();
            }
        }

        // If no account number matched
        return "Det finns ingen kund med namnet: " + name;
    }
}
