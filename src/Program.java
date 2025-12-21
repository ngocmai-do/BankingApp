import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Random;

public class Program extends JFrame implements ActionListener {

    JPanel jp = new JPanel();
    JTextField bankInfoField = new JTextField();
    JButton nyKund = new JButton("Ny kund");
    JButton addMoney = new JButton("Lägg till pengar");
    JButton taUtPengar = new JButton("Ta ut pengar");
    JButton[] buttons = new JButton[]{nyKund, addMoney, taUtPengar};

    public Program() {

        DatabaseReaderWriter databaseReaderWriter = DatabaseReaderWriter.getInstance(); //Singleton designmönster

        //print out lists for testing purpose:
        databaseReaderWriter.printCustomerList(databaseReaderWriter.readPrivateCustomerFile());
        databaseReaderWriter.printCustomerList(databaseReaderWriter.readCorpCustomerFile());
        IO.println(databaseReaderWriter.getBankInfo());

        bankInfoField.setText(databaseReaderWriter.getBankInfo());
        

        this.setTitle("Marah Bank AB");
        this.add(jp, BorderLayout.NORTH);
        jp.setLayout(new GridLayout(2, 3));
        this.add(bankInfoField, BorderLayout.SOUTH);

        placeButtonsInJPanel(buttons, jp);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
        }

        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void placeButtonsInJPanel(JButton[] buttonsArray, JPanel jp) {

        for (int i = 0; i < buttonsArray.length; i++) {
            jp.add(buttonsArray[i]);
        }

        jp.repaint();
        jp.revalidate();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
