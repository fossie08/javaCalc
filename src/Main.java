import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {
    private JTextField display;
    private String current = "";
    private String operator = "";
    private double firstOperand = 0;

    public Main() {
        // Frame setup
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // Buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        System.out.println(cmd);

        if (cmd.matches("[0-9]")) {
            current += cmd;
            display.setText(display.getText() + current);
        } else if (cmd.matches("[+\\-*/]")) {
            if (!current.isEmpty()) {
                firstOperand = Double.parseDouble(current);
                operator = cmd;
                current = "";
                display.setText(display.getText() + operator);
            }
        } else if (cmd.equals("=")) {
            if (!current.isEmpty() && !operator.isEmpty()) {
                double secondOperand = Double.parseDouble(current);
                double result = switch (operator) {
                    case "+" -> firstOperand + secondOperand;
                    case "-" -> firstOperand - secondOperand;
                    case "*" -> firstOperand * secondOperand;
                    case "/" -> secondOperand != 0 ? firstOperand / secondOperand : 0;
                    default -> 0;
                };
                display.setText("" + result);
                current = "";
                operator = "";
            }
        } else if (cmd.equals("C")) {
            current = "";
            operator = "";
            firstOperand = 0;
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
