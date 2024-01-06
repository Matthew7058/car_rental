package project1.rental;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentalSystemUI extends JFrame {
    private JTextField customerNameField;
    private JTextField carModelField;
    private JComboBox<Integer> priceCodeComboBox;
    private JTextField daysRentedField;
    private JButton addRentalButton;
    private JButton generateStatementButton;
    private JTextArea outputArea;
    private Customer currentCustomer;

    public RentalSystemUI() {
        createUI();
    }

    private void createUI() {
        setTitle("Car Rental System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Customer Name:"));
        customerNameField = new JTextField(20);
        inputPanel.add(customerNameField);

        inputPanel.add(new JLabel("Car Model:"));
        carModelField = new JTextField(20);
        inputPanel.add(carModelField);

        inputPanel.add(new JLabel("Price Code:"));
        priceCodeComboBox = new JComboBox<>(new Integer[]{Car.STANDARD, Car.LUXURY, Car.NEW_MODEL});
        inputPanel.add(priceCodeComboBox);

        inputPanel.add(new JLabel("Days Rented:"));
        daysRentedField = new JTextField(20);
        inputPanel.add(daysRentedField);

        addRentalButton = new JButton("Add Rental");
        inputPanel.add(addRentalButton);

        generateStatementButton = new JButton("Generate Statement");
        inputPanel.add(generateStatementButton);

        add(inputPanel, BorderLayout.NORTH);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Button actions
        addRentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRental();
            }
        });

        generateStatementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateStatement();
            }
        });
    }

    private void addRental() {
        String customerName = customerNameField.getText();
        if (currentCustomer == null || !currentCustomer.getName().equals(customerName)) {
            currentCustomer = new Customer(customerName);
        }

        String carModel = carModelField.getText(); // Using the text field value for car model
        int priceCode = (int) priceCodeComboBox.getSelectedItem();
        int daysRented = Integer.parseInt(daysRentedField.getText());

        Car car = new Car(carModel, priceCode);
        Rental rental = new Rental(car, daysRented);
        currentCustomer.addRental(rental);

        JOptionPane.showMessageDialog(this, "Rental added for " + customerName);
    }

    private void generateStatement() {
        if (currentCustomer != null) {
            String statement = currentCustomer.statement();
            outputArea.setText(statement);
        } else {
            outputArea.setText("No customer selected.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RentalSystemUI().setVisible(true);
            }
        });
    }
}