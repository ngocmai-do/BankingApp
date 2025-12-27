import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseReaderWriter {

    public static String customerFileName = "src/privateCustomer.txt";
    public static String companyFileName = "src/corporateCustomer.txt";
    private static DatabaseReaderWriter databaseReaderWriter = new DatabaseReaderWriter();  //Singleton designmönster

    private DatabaseReaderWriter() {}

    public static DatabaseReaderWriter getInstance(){
        return databaseReaderWriter;
    }

    public List<Customer> readPrivateCustomerFile() {
        List<Customer> privateCustomerList = new ArrayList<>();
        String[] personDataArray;
        String personData;

        try (Scanner sc = new Scanner(new File(customerFileName))) {
            if (sc.hasNextLine()) {
                sc.nextLine();      // Skip first line because it has the name of the fields instead of the actual data
            }
            while (sc.hasNextLine()) {
                personData = sc.nextLine();
                personDataArray = personData.split(";");

                PrivateCustomer p = new PrivateCustomer(personDataArray[0], personDataArray[1], personDataArray[2], personDataArray[3],
                        personDataArray[4], personDataArray[5], Integer.parseInt(personDataArray[6]));

                privateCustomerList.add(p);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            //System.exit(0);
        }
        return privateCustomerList;
    }

    public List<Customer> readCorpCustomerFile() {
        List<Customer> corpCustomerList = new ArrayList<>();
        String[] personDataArray;
        String personData;

        try (Scanner sc = new Scanner(new File("src/corporateCustomer.txt"))) {
            if (sc.hasNextLine()) {
                sc.nextLine();      // Skip first line because it has the name of the fields instead of the actual data
            }
            while (sc.hasNextLine()) {
                personData = sc.nextLine();
                personDataArray = personData.split(";");

                CorporateCustomer p = new CorporateCustomer(personDataArray[0], personDataArray[1], personDataArray[2], personDataArray[3],
                        personDataArray[4], personDataArray[5], Integer.parseInt(personDataArray[6]), Integer.parseInt(personDataArray[7]));

                corpCustomerList.add(p);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            //System.exit(0);
        }
        return corpCustomerList;
    }

    public String printCustomerList (List<Customer> customerList) {
        StringBuilder printResult = new StringBuilder();
        for (int i = 0; i < customerList.size(); i++) {
            printResult.append(customerList.get(i).getAllInfo()).append("\n");
        }
        return printResult.toString();
    }

    public String getBankInfo () {
        StringBuilder bankInfo = new StringBuilder();
        String temp;

        try (Scanner sc = new Scanner(new File("src/MarahBankInfo.txt"))) {
            while (sc.hasNextLine()) {
                temp = sc.nextLine();
                bankInfo.append(temp);
                bankInfo.append("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            //System.exit(0);
        }
        return bankInfo.toString();
    }

//TO DO: this method will need to fix (thinking this will be used to add new customer of the bank)
    public void writeDataToFile(String outFileName, String newCustomerInfo){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outFileName, true))) {
            bw.write(newCustomerInfo);
            bw.newLine();
        }
        catch (NullPointerException e) {
            //e.printStackTrace();
            System.exit(0);
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find file!");
            //e.printStackTrace();
            System.exit(0);
        }
        catch (IOException e){
            System.out.println("Could not write to file!");
            //e.printStackTrace();
            System.exit(0);
        }
        catch (Exception e){
            System.out.println("Unknown error!");
            //e.printStackTrace();
            System.exit(0);
        }
    }
}
