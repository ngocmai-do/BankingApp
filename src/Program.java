import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Random;

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

    JButton seeAllCustomer = new JButton("Se alla kunders information");
    JButton lookUpCustomer = new JButton("Sök kund");

    JButton nyKund = new JButton("Ny kund");
    JButton addMoney = new JButton("Lägg till pengar");
    JButton taUtPengar = new JButton("Ta ut pengar");
    JButton[] buttons = new JButton[]{kund, bank};


    String programState;

    public Program() {

        DatabaseReaderWriter databaseReaderWriter = DatabaseReaderWriter.getInstance(); //Singleton designmönster

        bankInfoField.setText(databaseReaderWriter.getBankInfo());


        this.setTitle("Marah Bank AB");
        this.add(jp, BorderLayout.NORTH);
        jp.setLayout(new GridLayout(3, 2));
        this.add(bankInfoField, BorderLayout.SOUTH);
        jp.add(kund);
        kund.addActionListener(this);
        jp.add(bank);
        bank.addActionListener(this);

        accountNumberField.addActionListener(this);
        passwordField.addActionListener(this);
        logInButton.addActionListener(this);

        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bank)) {
            jp.removeAll();
            jp.add(passwordLabel);
            jp.add(passwordField);
            jp.add(logInButton);
            jp.repaint();
            jp.revalidate();
            programState = "bank";
        }

        if (e.getSource().equals(kund)) {
            jp.removeAll();
            jp.add(accountLabel);
            jp.add(accountNumberField);
            jp.add(passwordLabel);
            jp.add(passwordField);
            jp.add(logInButton);
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
                jp.repaint();
                jp.revalidate();
            }
            if (programState.equals("bank")) {
                result = BankUtilities.bankLogIn(passwordField.getText());
                jp.removeAll();
                logInResult.setText(result);
                jp.add(logInResult);
                jp.repaint();
                jp.revalidate();
            }
        }

        if (e.getSource().equals(nyKund)) {
            String nameInput = JOptionPane.showInputDialog("Vad heter du?");
            String name = nameInput.trim();
            Medlem aMember = FindMembers.findMembersInFile(name);

            if (aMember == null) {
                int pengar = new Random().nextInt(10000);
                String moneyAsString = String.valueOf(pengar);
                FindMembers.addMemberToFile(name, moneyAsString);
            } else {
                JOptionPane.showMessageDialog(null, "Du är redan medlem");
            }
        }
        if (e.getSource().equals(addMoney)) {
            String nameInput = JOptionPane.showInputDialog("Vad är ditt namn?");
            String person = nameInput.trim();
            Medlem aMember = FindMembers.findMembersInFile(person);

            if (aMember != null) {
                String howMuchMoney = JOptionPane.showInputDialog(("Hur mycket pengar vill du lägga till?"));
                int amountToAdd = Integer.parseInt(howMuchMoney.trim());

                if  (amountToAdd <= 0) {
                    JOptionPane.showMessageDialog(null, "Du kan inte lägga till minus eller noll pengar");
                }
                else {
                    FindMembers.updateMemberMoney(person, +amountToAdd);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Du är inte medlem");
            }
        }
        if (e.getSource().equals(taUtPengar)) {
            String nameInput = JOptionPane.showInputDialog("Vad heter du?");
            String person = nameInput.trim();
            Medlem aMember = FindMembers.findMembersInFile(person);

            if (aMember != null) {
                String howMuchMoney = JOptionPane.showInputDialog("Hur mycket pengar vill du ta ut?");
                int amountToWithdraw = Integer.parseInt(howMuchMoney.trim());
                int moneyInPocket = Integer.parseInt(aMember.getMoney());

                if (amountToWithdraw > moneyInPocket) {
                    JOptionPane.showMessageDialog(null, "Du har inte tillräckligt med pengar");
                } else {
                    FindMembers.updateMemberMoney(person, -amountToWithdraw);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Du är inte medlem");
            }
        }
    }

}
