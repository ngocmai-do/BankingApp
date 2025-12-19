import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FindMembers {

    public static Medlem findMembersInFile(String name) {
        try (Scanner in = new Scanner(new File("Medlemmar.txt"))) {
            while (in.hasNextLine()) {
                String currentLine = in.nextLine();
                String[] arraySplit = currentLine.split(";");
                if (arraySplit.length >= 2) {
                    String namn = arraySplit[0];
                    String money = arraySplit[1];

                    if (name.equalsIgnoreCase(namn)) {
                        Medlem foundMember =  new Medlem(namn, money);
                        return foundMember;
                    }
                }
            }
            return null;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public static void addMemberToFile(String name, String money) {

        try (FileWriter fw = new FileWriter("Medlemmar.txt", true)) {
            fw.write(name + ";" + money + "\n");
        } catch (Exception e) {
            System.out.println("Error writing file");
        }
    }

    public static void updateMemberMoney(String name, int amountToAdd) {
        try {
            File file = new File("Medlemmar.txt");
            Scanner in = new Scanner(file);

            StringBuilder sb = new StringBuilder();
            boolean found = false;

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] split = line.split(";");

                if (split.length == 2) {
                    String namn = split[0];
                    int currentMoney = Integer.parseInt(split[1]);

                    if (namn.equalsIgnoreCase(name)) {
                        currentMoney += amountToAdd;
                        found = true;
                        sb.append(namn).append(";").append(currentMoney).append("\n");
                    } else {
                        sb.append(line).append("\n");
                    }
                }
            }

            in.close();

            if (found) {
                FileWriter fw = new FileWriter(file, false); // overwrite
                fw.write(sb.toString());
                fw.close();
            }

        } catch (Exception e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

}
