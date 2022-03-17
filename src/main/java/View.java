import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JTextField firstTextField = new JTextField(18);
    private JTextField secondTextField = new JTextField(18);
    private JTextField resultTextField = new JTextField(20);
    private JTextField quotientTextField = new JTextField(20);
    private JButton calculateButton = new JButton("Calculate");
    private JButton resetAllButton = new JButton("Reset all");
    private JButton resetResultButton = new JButton("Reset result");
    private JComboBox<String> operations;

    private Operation operation = new Operation();
    private Controller controller = new Controller(this);

    public View() {
        firstTextField.setText("");
        secondTextField.setText("");
        resultTextField.setText("");
        quotientTextField.setText("");
        operations = new JComboBox<>(operation.getOperationOptions());

        JFrame frame = new JFrame("Polynomial Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 350);

        JPanel firstFieldPanel = new JPanel();
        JLabel firstFieldLabel = new JLabel("First Polynomial : ");
        firstFieldPanel.add(firstFieldLabel);
        firstFieldPanel.add(firstTextField);
        firstFieldPanel.setLayout(new FlowLayout());

        JPanel secondFieldPanel = new JPanel();
        JLabel secondFieldLabel = new JLabel("Second Polynomial : ");
        secondFieldPanel.add(secondFieldLabel);
        secondFieldPanel.add(secondTextField);
        secondFieldPanel.setLayout(new FlowLayout());

        JPanel comboBoxPanel = new JPanel();
        JLabel comboBoxLabel = new JLabel("Operation : ");
        comboBoxPanel.add(comboBoxLabel);
        comboBoxPanel.add(operations);
        comboBoxPanel.setLayout(new FlowLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(calculateButton);
        calculateButton.addActionListener(controller);
        buttonsPanel.add(resetAllButton);
        resetAllButton.addActionListener(controller);
        buttonsPanel.add(resetResultButton);
        resetResultButton.addActionListener(controller);
        buttonsPanel.setLayout(new FlowLayout());

        JPanel resultFieldPanel = new JPanel();
        JLabel resultFieldLabel = new JLabel("Result : ");
        resultFieldPanel.add(resultFieldLabel);
        resultFieldPanel.add(resultTextField);
        resultFieldPanel.setLayout(new FlowLayout());

        JPanel quotientFieldPanel = new JPanel();
        JLabel quotientFieldLabel = new JLabel("Quotient : ");
        quotientFieldPanel.add(quotientFieldLabel);
        quotientFieldPanel.add(quotientTextField);
        quotientFieldPanel.setLayout(new FlowLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.add(firstFieldPanel);
        mainPanel.add(secondFieldPanel);
        mainPanel.add(comboBoxPanel);
        mainPanel.add(buttonsPanel);
        mainPanel.add(resultFieldPanel);
        mainPanel.add(quotientFieldPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public void resetAll() {
        firstTextField.setText("");
        secondTextField.setText("");
        resultTextField.setText("");
        quotientTextField.setText("");
    }

    public void resetResult() {
        resultTextField.setText("");
        quotientTextField.setText("");
    }

    public JComboBox<String> getFromComboBox() {
        return operations;
    }

    public String getFirstTextField() {
        return firstTextField.getText();
    }

    public String getSecondTextField() {
        return secondTextField.getText();
    }

    public JButton getCalculateButton() {
        return calculateButton;
    }

    public JButton getResetAllButton() {
        return resetAllButton;
    }

    public JButton getResetResultButton() {
        return resetResultButton;
    }

    public void setResultTextField(String result) {
        resultTextField.setText(result);
    }

    public void setQuotientTextField(String quotient) {
        quotientTextField.setText(quotient);
    }
}
