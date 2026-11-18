import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class ProductApp  extends JFrame {
    private JTextField txtCustomerName;
    private JComboBox<String> comboProducts;
    private JTextField txtPrice;
    private JButton btnProcess;
    private ProductCalculations logic;

    public ProductApp(){
        setTitle("Product App");
        setSize(450,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

    //Input Panel
    JPanel inputPanel = new JPanel(new GridLayout(3,2,10,15));
    inputPanel.add(new JLabel("Customer Name: "));
    txtCustomerName = new JTextField();
    inputPanel.add(txtCustomerName);

    inputPanel.add(new JLabel("Select Product: "));
    String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
    comboProducts = new JComboBox<>(products);
    inputPanel.add(comboProducts);

    inputPanel.add(new JLabel("Enter Price: "));
    txtPrice = new JTextField();
    inputPanel.add(txtPrice);

    mainPanel.add(inputPanel,BorderLayout.CENTER);

    //Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnProcess = new JButton("PROCESS");
        btnProcess.setPreferredSize(new Dimension(200,30));
        buttonPanel.add(btnProcess);
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);

        add(mainPanel);
            //Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        //Actions
        exitItem.addActionListener(e -> System.exit(0));

        btnProcess.addActionListener(e -> processSale());
        processItem.addActionListener(e -> processSale());

        clearItem.addActionListener(e ->{ txtCustomerName.setText("");
        comboProducts.setSelectedIndex(0);});

        setVisible(true);
    }
    private void processSale() {
        try {
            String name = txtCustomerName.getText().trim();
            String product = comboProducts.getSelectedItem().toString();
            double price = Double.parseDouble(txtPrice.getText().trim());

            //Validate the inputs
            if (name.isEmpty() || price <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name and price.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Calculate using our logic class
            logic = new ProductCalculations(price);
            double vat = logic.GetVat();
            double commission = logic.GetCommission();
            double total = logic.GetTotal();

            //Format String message
            String message = "Customer Name:" + name + "\n" +
                    "PRODUCT:" + product + "\n" +
                    "VAT:R" + vat + "\n" +
                    "COMMISSION:R" + commission + "\n" +
                    "TOTAL:R" + total;

            //Show popup
            JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);

            //Save to file
            saveToFile(message);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void saveToFile(String message){
        try{
            FileWriter writer = new FileWriter("data.txt");
            writer.write("DATA LOG\n");
            writer.write("*****************************\n");
            writer.write(message+ "\n");
            writer.write("*****************************\n");
            writer.close();
        }
        catch (IOException ex){
            JOptionPane.showMessageDialog(this,"Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
