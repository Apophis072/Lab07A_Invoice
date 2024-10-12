import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InvoiceFrame extends JFrame {
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextArea inforArea;
    private JTextArea totalArea;
    private ArrayList<LineItem> items;
    private double amountDue;
    private JPanel mainPnl;
    private JPanel inputPnl;

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
        mainPnl = new JPanel(new BorderLayout());

        // Creates Title Label
        JLabel titleLabel = new JLabel("Invoice", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 48));
        mainPnl.add(titleLabel, BorderLayout.NORTH);

        // Creates Input Panel
        inputPnl = new JPanel(new GridLayout(4, 2));

        inputPnl.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        inputPnl.add(nameField);

        inputPnl.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPnl.add(quantityField);

        inputPnl.add(new JLabel("Price per Unit:"));
        priceField = new JTextField();
        inputPnl.add(priceField);

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

        inputPnl.add(addButton);
        inputPnl.add(exitButton);
        mainPnl.add(inputPnl, BorderLayout.CENTER);

        // Item Details Panel
        JPanel itemDetailsPanel = new JPanel(new BorderLayout());
        inforArea = new JTextArea(10, 50);
        inforArea.setEditable(false);
        itemDetailsPanel.add(new JScrollPane(inforArea), BorderLayout.CENTER);
        mainPnl.add(itemDetailsPanel, BorderLayout.WEST);

        // Amount Due Panel
        JPanel amountDuePanel = new JPanel(new BorderLayout());
        amountDuePanel.setBorder(BorderFactory.createTitledBorder("Amount Due"));
        totalArea = new JTextArea(5, 20);
        totalArea.setEditable(false);
        amountDuePanel.add(new JScrollPane(totalArea), BorderLayout.CENTER);
        mainPnl.add(amountDuePanel, BorderLayout.SOUTH);

        add(mainPnl);
        setVisible(true);
    }


    private void product() {
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        double totalPrice = quantity * price;
        amountDue += totalPrice;

        LineItem item = new LineItem(name, quantity, price, totalPrice);
        items.add(item);

        //Format for how the product is going to be outputted
        //Had some help with formatting
        inforArea.append("Product\t           Price   quantity   total price\n");
        inforArea.append(String.format("%-30s%8.2f%7d%9.2f\n", name, price, quantity, totalPrice));
        inforArea.append("\n");
        //Outputs the final cost
        totalArea.setText(String.format("Total: $%.2f", amountDue));

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

    public LineItem(String name, int quantity, double price, double amountDue) {
        this.product = name;
        this.quantity = quantity;
        this.price = price;
        this.total = amountDue;
    }



}