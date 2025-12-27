import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Program extends JFrame implements ActionListener {

    JPanel jp = new JPanel();
    JTextArea bankInfoField = new JTextArea();
    JButton kund = new JButton("Kund");
    JButton bank = new JButton("Bank");

    JLabel accountLabel = new JLabel("Kontonummer: ");
    JTextField accountNumberField = new JTextField(50);
    JLabel passwordLabel = new JLabel("Lösenord: ");
    JTextField passwordField = new JTextField(50);
    JButton logInButton = new JButton("Log In");
    JTextField logInResult = new JTextField(100);

    JLabel newCustomerName = new JLabel("För och efternamn: ");
    JTextField newCustomerNameField = new JTextField(50);
    JLabel newCustomerAdress = new JLabel("Adress: ");
    JTextField newCustomerAdressField = new JTextField(50);
    JLabel newCustomerEmail = new JLabel("Email: ");
    JTextField newCustomerEmailField = new JTextField(50);
    JLabel newCustomerPersonnummer = new JLabel("Personnummer: ");
    JTextField newCustomerPersonnummerField = new JTextField(50);
    JLabel newCustomerPassword = new JLabel("Skapa lösenord: ");
    JTextField newCustomerPasswordField = new JTextField(50);
    JButton createNewCustomerAccount = new JButton("Skapa");
    JButton createNewCompanyAccount = new JButton("Skapa");

    JLabel newCompanyName = new JLabel("Företags namn: ");
    JTextField newCompanyNameField = new JTextField(50);
    JLabel newCompanyNumber = new JLabel("Organisation nummer: ");
    JTextField newCompanyNumberField = new JTextField(50);
    JLabel newCompanyAdress = new JLabel("Adress: ");
    JTextField newCompanyAdressField = new JTextField(50);
    JLabel newCompanyEmail = new JLabel("Email: ");
    JTextField newCompanyEmailField = new JTextField(50);
    JLabel newCompanyPassword = new JLabel("Skapa lösenord: ");
    JTextField newCompanyPasswordField = new JTextField(50);

    JButton tryAgain = new JButton("Försök igen");
    JButton seeBankingOptions = new JButton("Se bankalternativ");

    JButton seeAllCustomer = new JButton("Se alla kunders information");
    JTextArea allCustomerInfo = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(allCustomerInfo);
    JButton lookUpCustomer = new JButton("Sök kund");
    JLabel nameLabel = new JLabel("Kundens fullständiga namn:");
    JTextField nameField = new JTextField(50);
    JButton lookUpButton = new JButton("Sök kund");

    JButton goBack = new JButton("Tillbaka");

    JButton nyKund = new JButton("Ny kund");
    JButton newCompany = new JButton("Nytt företag");
    JButton addMoney = new JButton("Sätt in pengar");
    JButton taUtPengar = new JButton("Ta ut pengar");

    DatabaseReaderWriter databaseReaderWriter = DatabaseReaderWriter.getInstance(); //Singleton designmönster

    String programState;

    public Program() {

        bankInfoField.setText(databaseReaderWriter.getBankInfo());

        this.setTitle("Marah Bank AB");
        this.add(jp, BorderLayout.NORTH);
        jp.setLayout(new GridLayout(3, 2));
        this.add(bankInfoField, BorderLayout.SOUTH);
        jp.add(kund);
        kund.addActionListener(this);
        jp.add(bank);
        bank.addActionListener(this);
        jp.add(nyKund);
        nyKund.addActionListener(this);
        jp.add(newCompany);
        newCompany.addActionListener(this);

        accountNumberField.addActionListener(this);
        passwordField.addActionListener(this);
        logInButton.addActionListener(this);
        tryAgain.addActionListener(this);
        seeBankingOptions.addActionListener(this);
        seeAllCustomer.addActionListener(this);
        lookUpCustomer.addActionListener(this);
        nameField.addActionListener(this);
        lookUpButton.addActionListener(this);
        goBack.addActionListener(this);
        createNewCustomerAccount.addActionListener(this);
        createNewCompanyAccount.addActionListener(this);

        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void afterLoggingIn (String logInResult) {
        if (logInResult.startsWith("Välkommen")) {
            jp.add(seeBankingOptions);
        } else {
            jp.add(tryAgain);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bank)) {
            jp.removeAll();
            passwordField.setText("");
            jp.add(passwordLabel);
            jp.add(passwordField);
            jp.add(logInButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
            programState = "bank";
        }

        if (e.getSource().equals(kund)) {
            jp.removeAll();
            accountNumberField.setText("");
            passwordField.setText("");
            jp.add(accountLabel);
            jp.add(accountNumberField);
            jp.add(passwordLabel);
            jp.add(passwordField);
            jp.add(logInButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
            programState = "kund";
        }

        if (e.getSource().equals(logInButton)) {
            String result;
            if (programState.equals("kund")) {
                result = CustomerUtilities.loggingIn(accountNumberField.getText(), passwordField.getText());
                jp.removeAll();
                logInResult.setText(result);
                jp.add(logInResult);
                afterLoggingIn(result);
                jp.add(goBack);
                jp.repaint();
                jp.revalidate();
            }
            if (programState.equals("bank")) {
                result = BankUtilities.bankLogIn(passwordField.getText());
                jp.removeAll();
                logInResult.setText(result);
                jp.add(logInResult);
                afterLoggingIn(result);
                jp.add(goBack);
                jp.repaint();
                jp.revalidate();
            }
        }

        if (e.getSource().equals(tryAgain) && programState.equals("kund")) {
            jp.removeAll();
            accountNumberField.setText("");
            passwordField.setText("");
            jp.add(accountLabel);
            jp.add(accountNumberField);
            jp.add(passwordLabel);
            jp.add(passwordField);
            jp.add(logInButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(tryAgain) && programState.equals("bank")) {
            jp.removeAll();
            passwordField.setText("");
            jp.add(passwordLabel);
            jp.add(passwordField);
            jp.add(logInButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(seeBankingOptions)) {
            if (programState.equals("bank")) {
                jp.removeAll();
                jp.add(seeAllCustomer);
                jp.add(lookUpCustomer);
                jp.add(goBack);
                jp.repaint();
                jp.revalidate();
            }
            if (programState.equals("kund")) {

            }
        }

        if (e.getSource().equals(seeAllCustomer)) {
            jp.removeAll();
            allCustomerInfo.setText(BankUtilities.seeAllCustomer());
            jp.setLayout(new BorderLayout());
            jp.add(scrollPane, BorderLayout.CENTER);
            setSize(1000, 700);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(lookUpCustomer)) {
            jp.removeAll();
            jp.add(nameLabel);
            jp.add(nameField);
            jp.add(lookUpButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(lookUpButton)) {
            jp.removeAll();
            allCustomerInfo.setText(BankUtilities.lookUpCustomer(nameField.getText()));
            jp.add(allCustomerInfo);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(goBack)) {
            jp.removeAll();
            jp.add(kund);
            jp.add(bank);
            jp.add(nyKund);
            jp.add(newCompany);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(nyKund)) {
            jp.removeAll();
            jp.add(newCustomerName);
            newCustomerNameField.setText("");
            jp.add(newCustomerNameField);
            jp.add(newCustomerAdress);
            newCustomerAdressField.setText("");
            jp.add(newCustomerAdressField);
            jp.add(newCustomerEmail);
            newCustomerEmailField.setText("");
            jp.add(newCustomerEmailField);
            jp.add(newCustomerPersonnummer);
            newCustomerPersonnummerField.setText("");
            jp.add(newCustomerPersonnummerField);
            jp.add(newCustomerPassword);
            newCustomerPasswordField.setText("");
            jp.add(newCustomerPasswordField);
            jp.add(createNewCustomerAccount);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(createNewCustomerAccount)) {
            String newAccountNumber = CustomerUtilities.createNewCustomerAccount(newCustomerNameField.getText(), newCustomerAdressField.getText(), newCustomerEmailField.getText(), newCustomerPersonnummerField.getText(), newCustomerPasswordField.getText());
            jp.removeAll();
            logInResult.setText("Välkommen!\n Ditt kontonummer är " + newAccountNumber);
            jp.add(logInResult);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();

        }

        if (e.getSource().equals(newCompany)) {
            jp.removeAll();
            jp.add(newCompanyName);
            newCompanyNameField.setText("");
            jp.add(newCompanyNameField);
            jp.add(newCompanyNumber);
            newCompanyNumberField.setText("");
            jp.add(newCompanyNumberField);
            jp.add(newCompanyAdress);
            newCompanyAdressField.setText("");
            jp.add(newCompanyAdressField);
            jp.add(newCompanyEmail);
            newCompanyEmailField.setText("");
            jp.add(newCompanyEmailField);
            jp.add(newCompanyPassword);
            newCompanyPasswordField.setText("");
            jp.add(newCompanyPasswordField);
            jp.add(createNewCompanyAccount);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(createNewCompanyAccount)) {
            String newAccountNumber = CustomerUtilities.createNewCompanyAccount(newCompanyNameField.getText(), newCompanyNumberField.getText(), newCompanyAdressField.getText(), newCompanyEmailField.getText(), newCompanyPasswordField.getText());
            jp.removeAll();
            logInResult.setText("Välkommen!\n Ditt kontonummer är " + newAccountNumber);
            jp.add(logInResult);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();

        }

    }

}
