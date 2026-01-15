import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

public class CustomerUtilities {
    static DatabaseReaderWriter databaseReaderWriter = DatabaseReaderWriter.getInstance();
    static List<Customer> privateCustomerList = databaseReaderWriter.readPrivateCustomerFile();
    static List<Customer> corporateCustomerList = databaseReaderWriter.readCorpCustomerFile();

    public static String loggingIn(String accountNumber, String password) {
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

    public static String createNewCompanyAccount(String accountName, String orgNumber, String accountAdress, String accountEmail, String accountPassword) {
        accountName = accountName.trim();
        orgNumber = orgNumber.trim();
        accountAdress = accountAdress.trim();
        accountEmail = accountEmail.trim();
        accountPassword = accountPassword.trim();
        String newCorporateAccountNumber = getNewCorporateAccountNumber();

        try (FileWriter fw = new FileWriter(DatabaseReaderWriter.companyFileName, true)) {
            fw.write("\n" + newCorporateAccountNumber + ";" + accountName + ";" + orgNumber + ";" + accountAdress + ";" + accountEmail + ";" + accountPassword + ";" + "0" + ";" + "0");
            CorporateCustomer newCompany = new CorporateCustomer(newCorporateAccountNumber, accountName, orgNumber, accountAdress, accountEmail, accountPassword, 0, 0);
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

    public static String updatePrivateCustomerMoney(String accountNumber, int amountToAdd) {
        try {
            File file = new File(DatabaseReaderWriter.customerFileName);
            Scanner in = new Scanner(file);

            StringBuilder sb = new StringBuilder();
            boolean found = false;

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] split = line.split(";");

                String customerAccountNumber = split[0];

                if (customerAccountNumber.equalsIgnoreCase(String.valueOf(accountNumber))) {
                    String currentMoney = (split[6]);
                    int theCurrentMoney = Integer.parseInt(currentMoney);

                    theCurrentMoney += amountToAdd;
                    String theTotalAmount = String.valueOf(theCurrentMoney);
                    found = true;
                    sb.append(customerAccountNumber).append(";").append(split[1]).append(";").append(split[2]).append(";").append(split[3]).append(";").append(split[4]).append(";").append(split[5]).append(";").append(theTotalAmount).append("\n");
                } else {
                    sb.append(line).append("\n");
                }
            }

            in.close();

            if (found) {
                FileWriter fw = new FileWriter(file, false);
                String newFileLines = sb.toString();
                fw.write(newFileLines);
                fw.close();
            }

        } catch (Exception e) {
            System.out.println("Error updating file: " + e.getMessage());
        }

        return String.valueOf(amountToAdd);
    }

    public static String updateCorporateBalance(String accountNumber, int amountToAdd) {
        try {
            File file = new File(DatabaseReaderWriter.companyFileName);
            Scanner in = new Scanner(file);

            StringBuilder sb = new StringBuilder();
            boolean found = false;

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] split = line.split(";");
                boolean isNotLastLine = in.hasNextLine();

                String companyAccountNumber = split[0];

                if (companyAccountNumber.equalsIgnoreCase(String.valueOf(accountNumber))) {
                    String currentBalance = (split[6]);
                    int theCurrentBalance = Integer.parseInt(currentBalance);

                    theCurrentBalance += amountToAdd;
                    String theTotalAmount = String.valueOf(theCurrentBalance);
                    found = true;
                    String lineToAdd = companyAccountNumber + ";" + split[1] + ";" + split[2] + ";" + split[3] + ";" + split[4] + ";" + split[5] + ";" + theTotalAmount + ";" + split[6];
                    if (isNotLastLine) {
                        lineToAdd += "\n";
                    }
                    sb.append(lineToAdd);
                } else {
                    if (isNotLastLine) {
                        sb.append(line).append("\n");
                    } else {
                        sb.append(line);
                    }
                }
            }

            in.close();

            if (found) {
                FileWriter fw = new FileWriter(file, false);
                String newFileLines = sb.toString();
                fw.write(newFileLines);
                fw.close();
            }

        } catch (Exception e) {
            System.out.println("Error updating file: " + e.getMessage());
        }

        return String.valueOf(amountToAdd);
    }

    public static String updateCorporateShareBalance(String accountNumber, int amountToAdd) {
        try {
            File file = new File(DatabaseReaderWriter.companyFileName);
            Scanner in = new Scanner(file);

            StringBuilder sb = new StringBuilder();
            boolean found = false;

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] split = line.split(";");
                boolean isNotLastLine = in.hasNextLine();

                String companyAccountNumber = split[0];

                if (companyAccountNumber.equalsIgnoreCase(String.valueOf(accountNumber))) {
                    String currentBalance = (split[7]);
                    int theCurrentBalance = Integer.parseInt(currentBalance);

                    theCurrentBalance += amountToAdd;
                    String theTotalAmount = String.valueOf(theCurrentBalance);
                    found = true;
                    String lineToAdd = companyAccountNumber + ";" + split[1] + ";" + split[2] + ";" + split[3] + ";" + split[4] + ";" + split[5] + ";" + split[6] + ";" + theTotalAmount;
                    if (isNotLastLine) {
                        lineToAdd += "\n";
                    }
                    sb.append(lineToAdd);
                } else {
                    if (isNotLastLine) {
                        sb.append(line).append("\n");
                    } else {
                        sb.append(line);
                    }
                }
            }
            in.close();

            if (found) {
                FileWriter fw = new FileWriter(file, false);
                String newFileLines = sb.toString();
                fw.write(newFileLines);
                fw.close();
            }

        } catch (Exception e) {
            System.out.println("Error updating file: " + e.getMessage());
        }

        return String.valueOf(amountToAdd);

    }

    public static int getAccountBalanceCustomer(String accountNumber) {
        accountNumber = accountNumber.trim();

        try (Scanner in = new Scanner(new File(DatabaseReaderWriter.customerFileName))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] split = line.split(";");

                if (split[0].equalsIgnoreCase(accountNumber)) {
                    return Integer.parseInt(split[6]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
        return -1;
    }

    public static int getAccountBalanceCorporate(String accountNumber) {
        accountNumber = accountNumber.trim();

        try (Scanner in = new Scanner(new File(DatabaseReaderWriter.companyFileName))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] split = line.split(";");

                if (split[0].equalsIgnoreCase(accountNumber)) {
                    return Integer.parseInt(split[6]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
        return -1;
    }

    public static int getAccountShareBalanceCorporate(String accountNumber) {
        accountNumber = accountNumber.trim();

        try (Scanner in = new Scanner(new File(DatabaseReaderWriter.companyFileName))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] split = line.split(";");

                if (split[0].equalsIgnoreCase(accountNumber)) {
                    return Integer.parseInt(split[7]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
        return -1;
    }

}

