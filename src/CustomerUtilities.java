import java.io.FileWriter;
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

    public static String createNewCustomerAccount(String accountName, String accountAdress, String accountEmail, String accountPersonnummer, String accountPassword) {
        accountName = accountName.trim();
        accountAdress = accountAdress.trim();
        accountEmail = accountEmail.trim();
        accountPersonnummer = accountPersonnummer.trim();
        accountPassword = accountPassword.trim();
        String theNewCustomerAccountNumber = getNewCustomerAccountNumber();

        try (FileWriter fw = new FileWriter(DatabaseReaderWriter.customerFileName, true)) {
            fw.write("\n" + theNewCustomerAccountNumber + ";" + accountName + ";" + accountAdress + ";" + accountEmail + ";" + accountPersonnummer + ";" + accountPassword + ";" + "0");
            Customer newCustomer = new PrivateCustomer(theNewCustomerAccountNumber, accountName, accountAdress, accountEmail, accountPersonnummer, accountPassword, 0);
            privateCustomerList.add(newCustomer);
        } catch (Exception e) {
            System.out.println("Error writing file");
        }
        return theNewCustomerAccountNumber;
    }

    public static String createNewCompanyAccount(String accountName, String accountNumber, String accountAdress, String accountEmail, String accountPassword) {
        accountName = accountName.trim();
        accountNumber = accountNumber.trim();
        accountAdress = accountAdress.trim();
        accountEmail = accountEmail.trim();
        accountPassword = accountPassword.trim();
        String newCorporateAccountNumber = getNewCorporateAccountNumber();

        try (FileWriter fw = new FileWriter(DatabaseReaderWriter.companyFileName, true)) {
            fw.write("\n" + newCorporateAccountNumber + ";" + accountName + ";" + accountNumber + ";" + accountAdress + ";" + accountEmail + ";" + accountPassword + ";" + "0" + ";" + "0");
            CorporateCustomer newCompany = new CorporateCustomer(accountName, newCorporateAccountNumber, accountNumber, accountAdress, accountEmail, accountPassword, 0, 0);
            corporateCustomerList.add(newCompany);
        } catch (Exception e) {
            System.out.println("Error writing file");
        }
        return newCorporateAccountNumber;
    }

    public static String getNewCustomerAccountNumber() {
      Customer lastCustomer = privateCustomerList.get(privateCustomerList.size() - 1);
      String getLastCustomerNumber = lastCustomer.getAccountNumber();
      int lastCustomerNumber = Integer.parseInt(getLastCustomerNumber);
      lastCustomerNumber = lastCustomerNumber + 1;
      return String.valueOf(lastCustomerNumber);
    }

    public static String getNewCorporateAccountNumber() {
        Customer lastCompany = corporateCustomerList.get(corporateCustomerList.size() - 1);
        String getLastCompanyNumber = lastCompany.getAccountNumber();
        int lastCompanyNumber = Integer.parseInt(getLastCompanyNumber);
        lastCompanyNumber = lastCompanyNumber + 1;
        return String.valueOf(lastCompanyNumber);
    }
}

