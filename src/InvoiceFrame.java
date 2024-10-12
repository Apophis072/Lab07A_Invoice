import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InvoiceFrame extends JFrame {

    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;

    private JTextArea infoArea;
    private JTextArea totalArea;

    private ArrayList<LineItem> items;

    private double amountDue;

    private JPanel mainPnl;
    private JPanel inputPnl;
    private JPanel summaryPnl;
    private JPanel amountDuePnl;

    private JButton addBtn;
    private JButton exitBtn;

    public InvoiceFrame() {
        // Sets up the frame that everything is going to be added to
        setTitle("Invoice");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.75);
        int height = (int) (screenSize.height * 0.75);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        items = new ArrayList<>();

        //uses methods to create the components of the mainPnl then add it to the mainPnl
        //allows changes later directly to the methods rather than changing the entire mainPnl
        mainPnl = new JPanel(new BorderLayout());

        CreateInputPnl();
        mainPnl.add(inputPnl, BorderLayout.NORTH);

        CreateSummaryPnl();
        mainPnl.add(summaryPnl, BorderLayout.CENTER);

        CreateAmountDuePnl();
        mainPnl.add(amountDuePnl, BorderLayout.SOUTH);

        add(mainPnl);
        setVisible(true);
    }

    //Creates the inputPnl where all the user inputs are going to be
    public void CreateInputPnl() {
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

        addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                product();
            }
        });
        inputPnl.add(addBtn);

        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        inputPnl.add(exitBtn);
    }

    //Creates a summmary panel
    //kind of how a person can see how much his/her products are going to be before the recipe is handed to him/her
    public void CreateSummaryPnl()
    {
        summaryPnl = new JPanel(new BorderLayout());
        infoArea = new JTextArea(10, 50);
        infoArea.setEditable(false);
        summaryPnl.add(new JScrollPane(infoArea), BorderLayout.CENTER);
    }

    //creates an amount due panel
    //outputs the cost of everything based on the summary panel
    public void CreateAmountDuePnl()
    {
        amountDuePnl = new JPanel(new BorderLayout());
        amountDuePnl.setBorder(BorderFactory.createTitledBorder("Amount Due"));
        totalArea = new JTextArea(2, 20);
        totalArea.setEditable(false);
        amountDuePnl.add(new JScrollPane(totalArea), BorderLayout.CENTER);
    }

    private void product()
    {
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        double totalPrice = quantity * price;
        amountDue += totalPrice;

        LineItem item = new LineItem(name, quantity, price, totalPrice);
        items.add(item);

        // Format for how the product is going to be outputted
        infoArea.append(String.format("Product: %-15s  Price: %8.2f  Quantity: %4d  Total: %10.2f%n", name, price, quantity, totalPrice));
        totalArea.setText(String.format("Total Amount Due: $%.2f", amountDue));

        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    class LineItem {
        private String product;
        private int quantity;
        private double price;
        private double total;

        public LineItem(String product, int quantity, double price, double total)
        {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
            this.total = total;
        }
    }
}
