package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.UnaryOperator;

public class Calculator extends JFrame implements ActionListener {

    JFrame calculatorWindow ;

    int widthOfWindow = 300;
    int heightOfWindow = 300;

    JPanel panel4buttons, panel4backspace;
    JButton button, backspaceButton;
    JTextField textField ;
    ImageIcon icon, scaledIcon, icon4Window;
    Image img, scaledImg;

    double firstNumber = 0;
    boolean isFirstInput = true;
    char operator = ' ';

        Calculator(){

            // Create window
            calculatorWindow = new JFrame("Calculator");
            setTitle("Calculator by Oti");
            setSize(330,450);
            setBackground(Color.WHITE);
            setVisible(true);
            setResizable(false);
            setLayout(null);                                                                                            // Without this a panel would fill everything. Not only the part we want to fill
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Set the image icon for the window
            icon4Window = new ImageIcon("C:\\Users\\otyli\\IdeaProjects\\Java Projects\\src\\Calculator\\0c2d8ea81d052d47eff12b3fd6732a3c.jpg");
            setIconImage(icon4Window.getImage());

            // Set location of window in the centre
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();                                         //Get size of screen
            int x = (screenSize.width - widthOfWindow) / 2;                                                             // Calculate X and Y for centered window
            int y = (screenSize.height - heightOfWindow) / 2;
            setLocation(x, y);

            // Create panel for buttons
            panel4buttons =new JPanel();
            panel4buttons.setBounds(10,100,300,300);                                                 // x-space from left, y-from right, width and height of panel
            panel4buttons.setLayout(new GridLayout(5,4,2,2));                                     // It is 5x4, not 4x4 because if there is too little rows then number of column gets bigger
            panel4buttons.setBackground(Color.WHITE);
            panel4buttons.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));                 // Every number is a thickness of border on the exact border of box
            add(panel4buttons);

            // Create panel for backspace
            panel4backspace = new JPanel();
            panel4backspace.setBounds(246,30,60,45);
            panel4backspace.setLayout(new GridLayout(1,1));
            panel4backspace.setBackground(new Color(255, 192, 203));
            panel4backspace.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            add(panel4backspace);

            // Create names of buttons
            String[] buttonLabels = {
                    "√", "^", "%", "/",
                    "1", "2", "3", "*",
                    "4", "5", "6", "-",
                    "7", "8", "9", "+",
                    ".", "0", "C", "="
            };

            // Create buttons
            for (String label : buttonLabels) {
                button = new JButton(label);
                button.setBackground(new Color(255, 192, 203));
                button.setBorder(null);
                button.addActionListener(this);
                panel4buttons.add(button);

                // Change color of button when entering or exiting button by mouse
                MouseAdapter mouseAdapter = new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        button = (JButton) e.getSource();                                                               //  e.getSource()- checks on which button action is taken (on which one mouse has entered)
                        button.setBackground(new Color(255, 200, 255));
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        button = (JButton) e.getSource();
                        button.setBackground(new Color(255, 192, 203));
                    }
                };button.addMouseListener(mouseAdapter);
            }
            // Add panel of Buttons to Layout
            add(panel4buttons, BorderLayout.CENTER);

            // Add image to button
            icon = new ImageIcon("C:\\Users\\otyli\\IdeaProjects\\Java Projects\\src\\Calculator\\9536088.png");
            img = icon.getImage();                                                                                      // Download image from icon
            scaledImg = img.getScaledInstance(60, 40, Image.SCALE_SMOOTH);                                 // Image size adjustment
            scaledIcon = new ImageIcon(scaledImg);

            backspaceButton = new JButton(scaledIcon);
            backspaceButton.setPreferredSize(new Dimension(60, 40));
            backspaceButton.setBorder(null);
            backspaceButton.addActionListener(this);                                                                  // Adding handling of action for button

            // Make backspace button/image bigger when entered
            MouseAdapter mouseAdapter = new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    panel4backspace.setBackground(new Color(255, 200, 255));
                    button = (JButton) e.getSource();
                    icon = new ImageIcon("C:\\Users\\otyli\\IdeaProjects\\Java Projects\\src\\Calculator\\9536088.png");
                    img = icon.getImage();
                    scaledImg = img.getScaledInstance(65, 45, Image.SCALE_SMOOTH);
                    scaledIcon = new ImageIcon(scaledImg);
                    button.setIcon(scaledIcon);
                    button.setPreferredSize(new Dimension(65, 45));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    panel4backspace.setBackground(new Color(255, 192, 203));
                    button = (JButton) e.getSource();
                    icon = new ImageIcon("C:\\Users\\otyli\\IdeaProjects\\Java Projects\\src\\Calculator\\9536088.png");
                    img = icon.getImage();
                    scaledImg = img.getScaledInstance(60, 40, Image.SCALE_SMOOTH);
                    scaledIcon = new ImageIcon(scaledImg);
                    button.setIcon(scaledIcon);
                    button.setPreferredSize(new Dimension(60, 40));
                }
            };

            // Backspace action
            backspaceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = textField.getText();
                    if (!text.isEmpty()) {
                        text = text.substring(0, text.length() - 1);
                        textField.setText(text);
                    }
                }
            });backspaceButton.addMouseListener(mouseAdapter);
            panel4backspace.add(backspaceButton);

            // Create textField
            textField = new JTextField();
            textField.setBounds(13, 15, 227, 70);
            textField.setFont(new Font("Arial", Font.PLAIN, 25));
            textField.setBorder(BorderFactory.createLineBorder(Color.PINK));
            textField.setCaretColor(Color.WHITE);
            add(textField);
                  }

    // Buttons action
    @Override
    public void actionPerformed(ActionEvent e) {                                                                        // This method is called when there is action for example: pressing button
        String command = e.getActionCommand();                                                                          // Allows to identify which button was pressed

        switch (command) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
                // This code is for every case above because cases don't have breaks
                operator = command.charAt(0);                                                                           // charAt(0) returns first character from chain of characters
                                                                                                                        // Sets operator acording to pressed button
                firstNumber = Double.parseDouble(textField.getText());                                                  // Takes first number from textField
                textField.setText(textField.getText() + command);
                isFirstInput = false;                                                                                   // Sets flag as false in order to enter second number
                break;

            case "=":
                if (!isFirstInput) {                                                                                    // Checks if the second number was entered
                    String equation = textField.getText();
                    String[] parts = equation.split("\\Q" + operator + "\\E");                                    // Cuts equation into parts using operator
                    double secondNumber = Double.parseDouble(parts[1]);
                    double result = 0;
                    switch (operator) {
                        case '+':
                            result = firstNumber + secondNumber;
                            break;
                        case '-':
                            result = firstNumber - secondNumber;
                            break;
                        case '*':
                            result = firstNumber * secondNumber;
                            break;
                        case '/':
                            result = firstNumber / secondNumber;
                            break;
                        case '^':
                            result = Math.pow(firstNumber, secondNumber);
                            break;
                    }
                    textField.setText(Double.toString(result));
                    isFirstInput = true;
                }break;
            case "√":
                applyUnaryOperation(num -> Math.sqrt(num));
                break;
            case "%":
                applyUnaryOperation(num -> num * 0.01);
                break;
            case "C":
                textField.setText("");
                isFirstInput = true;
                break;
            default:
                textField.setText(textField.getText() + command);
                break;
        }
    }
    // Method for % and √
    private void applyUnaryOperation(UnaryOperator<Double> command) {
        double num = Double.parseDouble(textField.getText());
        double result = command.apply(num);
        textField.setText(Double.toString(result));
        isFirstInput = true;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}

/*
SUMMARY

28 // Create window
38 // Set the image icon for the window
42 // Set location of window in the centre
48 // Create panel for buttons
56 // Create panel for backspace
64 // Create names of buttons
73 // Create buttons
81 // Change color of button when entering or exiting button by mouse
95 // Add panel of Buttons to Layout
98 // Add image to button
109 // Make backspace button/image bigger when entered
135 // Backspace action
148 // Create textField
157 // Buttons action
217 // Short Method for % and √

   */