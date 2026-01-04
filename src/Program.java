import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Program extends JFrame implements ActionListener {

    JPanel jp = new JPanel();
    JTextArea bankInfoField = new JTextArea();
    JButton kund = new JButton("Kund");
    JButton companyCustomer = new JButton("Företag Kund");
    JButton privateCustomer = new JButton("Privat kund");
    JButton bank = new JButton("Bank");

    JLabel accountLabel = new JLabel("Kontonummer: ");
    JTextField accountNumberField = new JTextField(50);
    JLabel passwordLabel = new JLabel("Lösenord: ");
    JTextField passwordField = new JTextField(50);
    JButton logInButton = new JButton("Log In");
    JTextField logInResult = new JTextField(100);
    JTextField moneyAddedToAccountField = new JTextField(100);

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
    JButton addMoneyToAccountButton = new JButton("Lägg till");
    JButton addBalanceToAccountButton = new JButton("Lägg till");
    JButton addShareBalanceToAccountButton = new JButton("Lägg till");
    JButton takeOutMoneyFromAccountButton = new JButton("Ta ut");
    JButton takeOutShareMoneyFromAccountButton = new JButton("Ta ut");
    JButton takeOutMoneyFromCustomerAccountButton = new JButton("Ta ut");

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

    JButton seeAllCustomerButton = new JButton("Se alla kunders information");
    JTextArea allCustomerInfo = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(allCustomerInfo);
    JButton lookUpCustomer = new JButton("Sök kund");
    JLabel nameLabel = new JLabel("Kundens fullständiga namn:");
    JTextField nameField = new JTextField(50);
    JButton lookUpButton = new JButton("Sök kund");
    JTextField addMoneyField = new JTextField(50);

    JButton goBack = new JButton("Tillbaka");

    JButton nyKund = new JButton("Ny kund");
    JButton newCompany = new JButton("Nytt företag");
    JButton addMoney = new JButton("Sätt in pengar");
    JButton addBalance = new JButton("Sätt in pengar");
    JButton addShareBalance = new JButton("Sätt in pengar(Aktier)");
    JButton takeOutMoneyCompany = new JButton("Ta ut pengar");
    JButton takeOutShareMoneyCompany = new JButton("Ta ut pengar(Aktier)");
    JButton takeOutMoney = new JButton("Ta ut pengar");

    DatabaseReaderWriter databaseReaderWriter = DatabaseReaderWriter.getInstance(); //Singleton designmönster

    String programState;

    public Program() {

        bankInfoField.setText(databaseReaderWriter.getBankInfo());

        this.setTitle("Marah Bank AB");
        this.add(jp, BorderLayout.NORTH);
        jp.setLayout(new GridLayout(3, 2));
        this.add(bankInfoField, BorderLayout.SOUTH);
        jp.add(kund);
        jp.add(bank);

        kund.addActionListener(this);
        companyCustomer.addActionListener(this);
        privateCustomer.addActionListener(this);
        bank.addActionListener(this);
        nyKund.addActionListener(this);
        newCompany.addActionListener(this);
        addMoney.addActionListener(this);
        addBalance.addActionListener(this);
        addShareBalance.addActionListener(this);
        takeOutMoneyCompany.addActionListener(this);
        takeOutMoney.addActionListener(this);
        takeOutShareMoneyCompany.addActionListener(this);

        accountNumberField.addActionListener(this);
        passwordField.addActionListener(this);
        logInButton.addActionListener(this);
        tryAgain.addActionListener(this);
        seeBankingOptions.addActionListener(this);
        seeAllCustomerButton.addActionListener(this);
        lookUpCustomer.addActionListener(this);
        nameField.addActionListener(this);
        lookUpButton.addActionListener(this);
        goBack.addActionListener(this);
        createNewCustomerAccount.addActionListener(this);
        createNewCompanyAccount.addActionListener(this);
        addMoneyField.addActionListener(this);
        addMoneyToAccountButton.addActionListener(this);
        addBalanceToAccountButton.addActionListener(this);
        addShareBalanceToAccountButton.addActionListener(this);
        takeOutMoneyFromAccountButton.addActionListener(this);
        takeOutShareMoneyFromAccountButton.addActionListener(this);
        takeOutMoneyFromCustomerAccountButton.addActionListener(this);

        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void afterLoggingIn(String logInResult) {
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
            jp.add(companyCustomer);
            jp.add(privateCustomer);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
            programState = "kund";
        }

        if (e.getSource().equals(companyCustomer) || e.getSource().equals(privateCustomer)) {
            jp.removeAll();
            jp.setLayout(new GridLayout(3, 2));
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
            if (e.getSource().equals(companyCustomer)) {
                programState = "Företag";
            }
            else {
                programState = "Privat";
            }
        }

        if (e.getSource().equals(logInButton)) {
            String result;
            if (programState.equals("Privat")) {
                result = CustomerUtilities.loggingIn(accountNumberField.getText(), passwordField.getText());
                jp.removeAll();
                logInResult.setText(result);
                jp.add(logInResult);
                afterLoggingIn(result);
                jp.add(goBack);
                jp.repaint();
                jp.revalidate();
            }
            if (programState.equals("Företag")) {
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


        if (e.getSource().equals(tryAgain) && (programState.equals("Företag") || programState.equals("Privat"))) {
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
                jp.add(seeAllCustomerButton);
                jp.add(lookUpCustomer);
                jp.add(goBack);
                jp.repaint();
                jp.revalidate();
            }
            if (programState.equals("Privat")) {
                jp.removeAll();
                jp.add(addMoney);
                jp.add(takeOutMoney);
                jp.add(goBack);
                jp.repaint();
                jp.revalidate();
            }
            if (programState.equals("Företag")) {
                jp.removeAll();
                jp.add(addBalance);
                jp.add(addShareBalance);
                jp.add(takeOutMoneyCompany);
                jp.add(takeOutShareMoneyCompany);
                jp.add(goBack);
                jp.repaint();
                jp.revalidate();
            }
        }

        if (e.getSource().equals(seeAllCustomerButton)) {
            jp.removeAll();
            jp.setLayout(new BorderLayout());
            allCustomerInfo.setText(BankUtilities.seeAllCustomer());
            jp.add(scrollPane, BorderLayout.CENTER);
            jp.add(goBack, BorderLayout.SOUTH);
            setSize(1000, 700);
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
            jp.setLayout(new GridLayout(3, 2));
            jp.add(kund);
            jp.add(bank);
            setSize(500, 300);
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

        if (e.getSource().equals(addMoney)) {
            jp.removeAll();
            addMoneyField.setText("");
            jp.add(addMoneyField);
            jp.add(addMoneyToAccountButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(addMoneyToAccountButton)) {
            String moneyAddedToAccount = CustomerUtilities.updatePrivateCustomerMoney(accountNumberField.getText(), Integer.parseInt(addMoneyField.getText()));
            jp.removeAll();
            moneyAddedToAccountField.setText("Pengarna har lagts till!");
            jp.add(moneyAddedToAccountField);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(takeOutMoney)) {
            jp.removeAll();
            addMoneyField.setText("");
            jp.add(addMoneyField);
            jp.add(takeOutMoneyFromCustomerAccountButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(takeOutMoneyFromCustomerAccountButton)) {
            String anountTakenFromAccount = CustomerUtilities.updatePrivateCustomerMoney(accountNumberField.getText(), -Integer.parseInt(addMoneyField.getText()));
            jp.removeAll();
            moneyAddedToAccountField.setText("Pengarna har tagits ut");
            jp.add(moneyAddedToAccountField);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(addBalance)) {
            jp.removeAll();
            addMoneyField.setText("");
            jp.add(addMoneyField);
            jp.add(addBalanceToAccountButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(addBalanceToAccountButton)) {
            String balanceAddedToAccount = CustomerUtilities.updateCorporateBalance(accountNumberField.getText(), Integer.parseInt(addMoneyField.getText()));
            jp.removeAll();
            moneyAddedToAccountField.setText("Pengarna har lagts till!");
            jp.add(moneyAddedToAccountField);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(addShareBalance)) {
            jp.removeAll();
            addMoneyField.setText("");
            jp.add(addMoneyField);
            jp.add(addShareBalanceToAccountButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(addShareBalanceToAccountButton)) {
            String anountTakenFromAccount = CustomerUtilities.updateCorporateShareBalance(accountNumberField.getText(), Integer.parseInt(addMoneyField.getText()));
            jp.removeAll();
            moneyAddedToAccountField.setText("Pengarna har lagts till!");
            jp.add(moneyAddedToAccountField);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(takeOutMoneyCompany)) {
            jp.removeAll();
            addMoneyField.setText("");
            jp.add(addMoneyField);
            jp.add(takeOutMoneyFromAccountButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(takeOutMoneyFromAccountButton)) {
            String anountTakenFromAccount = CustomerUtilities.updateCorporateBalance(accountNumberField.getText(), -Integer.parseInt(addMoneyField.getText()));
            jp.removeAll();
            moneyAddedToAccountField.setText("Pengarna har tagits ut");
            jp.add(moneyAddedToAccountField);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(takeOutShareMoneyCompany)) {
            jp.removeAll();
            addMoneyField.setText("");
            jp.add(addMoneyField);
            jp.add(takeOutShareMoneyFromAccountButton);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

        if (e.getSource().equals(takeOutShareMoneyFromAccountButton)) {
            String anountTakenFromAccount = CustomerUtilities.updateCorporateShareBalance(accountNumberField.getText(), -Integer.parseInt(addMoneyField.getText()));
            jp.removeAll();
            moneyAddedToAccountField.setText("Pengarna har tagits ut");
            jp.add(moneyAddedToAccountField);
            jp.add(goBack);
            jp.repaint();
            jp.revalidate();
        }

    }

}
