import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InvoiceFrame extends JFrame {
    private JTextField nameField, quantityField, priceField;
    private JTextArea itemDetailsArea, totalArea;
    private ArrayList<LineItem> items;
    private double finalTotal;

    public InvoiceFrame() {
        //Sets up the frame that everything is going to be added to
        setTitle("Invoice");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.75);
        int height = (int) (screenSize.height * 0.75);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        items = new ArrayList<>();

        // Creates Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Creates Title Panel
        JLabel titleLabel = new JLabel("Invoice", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 48));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Creates Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Price per Unit:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        //Creates the buttons to add products and to exit
        //If have extra time then write a code to check if user really want to exit
        JButton addButton = new JButton("Add Product");
        JButton exitButton = new JButton("Exit");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                product();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        inputPanel.add(addButton);
        inputPanel.add(exitButton);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Item Details Panel
        JPanel itemDetailsPanel = new JPanel(new BorderLayout());
        itemDetailsArea = new JTextArea(10, 50);
        itemDetailsArea.setEditable(false);
        itemDetailsPanel.add(new JScrollPane(itemDetailsArea), BorderLayout.CENTER);
        mainPanel.add(itemDetailsPanel, BorderLayout.WEST);

        // Total Panel
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBorder(BorderFactory.createTitledBorder("Total"));
        totalArea = new JTextArea(5, 20);
        totalArea.setEditable(false);
        totalPanel.add(new JScrollPane(totalArea), BorderLayout.CENTER);
        mainPanel.add(totalPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }


    private void product() {
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        double totalPrice = quantity * price;
        finalTotal += totalPrice;

        LineItem item = new LineItem(name, quantity, price, totalPrice);
        items.add(item);

        //Format for how the product is going to be outputted
        //Had some help with formatting
        itemDetailsArea.append("Product\t           Price   quantity   total price\n");
        itemDetailsArea.append(String.format("%-30s%8.2f%7d%9.2f\n", name, price, quantity, totalPrice));
        itemDetailsArea.append("\n");
        //Outputs the final cost
        totalArea.setText(String.format("Total: $%.2f", finalTotal));

        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

}

class LineItem {
    private String product;
    private int quantity;
    private double price;
    private double total;

    public LineItem(String name, int quantity, double price, double total) {
        this.product = name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }



}
