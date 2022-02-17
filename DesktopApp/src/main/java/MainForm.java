import javax.swing.*;
import java.util.Arrays;

public class MainForm {

    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton collapseButton;
    private JButton expandButton;
    private JTextField surnameText;
    private JTextField nameText;
    private JTextField patronymicText;
    private JPanel collapsePanel;
    private JPanel expandPanel;
    private JTextField expandText;
    private JButton clearButton;

    public MainForm() {

        clearButton.addActionListener(e -> {
            clearText(surnameText, nameText, patronymicText, expandText);
        });

        collapseButton.addActionListener(e -> {
            String surname = surnameText.getText();
            String name = nameText.getText();
            String patronymic = patronymicText.getText();
            if (!surname.contains(" ") && !name.contains(" ") && !patronymic.contains(" ")) {
                if (!surname.equals("") && !name.equals("")) {
                    clearText(surnameText, nameText, patronymicText);
                    expandText.setText(
                            surname
                                    .concat(" ")
                                    .concat(name)
                                    .concat(" ")
                                    .concat(patronymic)
                    );
                } else {
                    warningMessage("Surname and Name fields must be filled!");
                }
            }
            else {
                warningMessage("Fields can not contain space symbol!");
            }
        });

        expandButton.addActionListener(e -> {
            String expand = expandText.getText();
            String[] collapse = expand.split(" ");
            try {
                surnameText.setText(collapse[0]);
                nameText.setText(collapse[1]);
                patronymicText.setText(collapse[2]);
            } catch (Exception exception) {
                if (collapse[0].equals("")) {
                    warningMessage("Text is missing!");
                }
                else {
                    warningMessage("Patronymic text is missing!");
                }
            }
            clearText(expandText);
        });
    }

    private void warningMessage(String message) {
        JOptionPane.showMessageDialog(
                mainPanel,
                message,
                "Warning!",
                JOptionPane.PLAIN_MESSAGE
        );
    }

    private void clearText(JTextField text1) {
        text1.setText("");
    }

    private void clearText(JTextField text1, JTextField text2, JTextField text3) {
        text1.setText("");
        text2.setText("");
        text3.setText("");
    }

    private void clearText(JTextField text1, JTextField text2, JTextField text3, JTextField text4) {
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
