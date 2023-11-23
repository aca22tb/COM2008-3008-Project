// /* those commented stuff might be needed for staff or manager */

// package com.mycompany.customerpage;

// import javax.swing.*;
// import javax.swing.border.EmptyBorder;

// import java.awt.*;
// import java.awt.event.*;
// import java.util.*;

// public class Customer extends JFrame implements ActionListener {
//     // Product Management components
//     private JLabel /*label1,*/ label2, /*label3,*/ label4;
//     private JTextField /*textField1,*/ textField2, /*textField3,*/ textField4;
//     private JButton addButton, viewButton, /**editButton,**/ deleteButton, clearButton, exitButton;

//     // Shopping Cart components
//     // private DefaultListModel<String> itemListModel;
//     // private JList<String> itemList;
//     // private JButton addToCartButton, removeFromCartButton, viewCartButton;

//     private JPanel productPanel /**, cartPanel**/;

//     private ArrayList<String[]> products = new ArrayList<String[]>();
//     // private java.util.List<OrderLine> orderLines = new ArrayList<>();

//     // private int orderNumber = 1;

//     public Customer() {
//         // Product Management initialization
//         setTitle("Product Management and Shopping Cart");
//         setSize(600, 400);
//         setDefaultCloseOperation(EXIT_ON_CLOSE);

//         // label1 = new JLabel("Product Code");
//         label2 = new JLabel("Product Name: ");
//         label2.setBorder(new EmptyBorder(0, 20, 0, 0));
//         // label3 = new JLabel("Price");
//         label4 = new JLabel("Quantity: ");
//         label4.setBorder(new EmptyBorder(0, 20, 0, 0));

//         // textField1 = new JTextField(10);
//         textField2 = new JTextField(20);
//         // textField3 = new JTextField(10);
//         textField4 = new JTextField(10);

//         addButton = new JButton("Add");
//         viewButton = new JButton("View");
//         // editButton = new JButton("Edit");
//         deleteButton = new JButton("Delete");
//         clearButton = new JButton("Clear");
//         exitButton = new JButton("Exit");

//         addButton.addActionListener(this);
//         viewButton.addActionListener(this);
//         // editButton.addActionListener(this);
//         deleteButton.addActionListener(this);
//         clearButton.addActionListener(this);
//         exitButton.addActionListener(this);

//         productPanel = new JPanel(new GridLayout(10, 2));
//         // productPanel.add(label1);
//         // productPanel.add(textField1);
//         productPanel.add(label2);
//         productPanel.add(textField2);
//         // productPanel.add(label3);
//         // productPanel.add(textField3);
//         productPanel.add(label4);
//         productPanel.add(textField4);
//         productPanel.add(addButton);
//         productPanel.add(viewButton);
//         // productPanel.add(editButton);
//         productPanel.add(deleteButton);
//         productPanel.add(clearButton);
//         productPanel.add(exitButton);

//         // Shopping Cart initialization
//         // itemListModel = new DefaultListModel<>();
//         // itemList = new JList<>(itemListModel);
//         // addToCartButton = new JButton("Add to Cart");
//         // removeFromCartButton = new JButton("Remove from Cart");
//         // viewCartButton = new JButton("View Cart");

//         // addToCartButton.addActionListener(this);
//         // removeFromCartButton.addActionListener(this);
//         // viewCartButton.addActionListener(this);

//         // cartPanel = new JPanel(new BorderLayout());
//         // cartPanel.add(new JScrollPane(itemList), BorderLayout.CENTER);

//         // JPanel cartButtonPanel = new JPanel();
//         // cartButtonPanel.add(addToCartButton);
//         // cartButtonPanel.add(removeFromCartButton);
//         // cartButtonPanel.add(viewCartButton);
//         // cartPanel.add(cartButtonPanel, BorderLayout.SOUTH);

//         // Set up the main layout
//         setLayout(new GridLayout(1, 2));
//         add(productPanel);
//         //add(cartPanel);

//         setVisible(true);
//     }

//     public void actionPerformed(ActionEvent e) {
//         if (e.getSource() == addButton) {
//             // Product Management: Add product
//             String[] product = new String[4];
//             // product[0] = textField1.getText();
//             product[1] = textField2.getText();
//             // product[2] = textField3.getText();
//             product[3] = textField4.getText();
//             products.add(product);
//             JOptionPane.showMessageDialog(this, "Product added successfully");
//             clearProductFields();
//         } else if (e.getSource() == viewButton) {
//             // Product Management: View products
//             showProducts();
//         } /**else if (e.getSource() == editButton) {
//             // Product Management: Edit product
//             editProduct();
//         }**/ else if (e.getSource() == deleteButton) {
//             // Product Management: Delete product
//             deleteProduct();
//         } else if (e.getSource() == clearButton) {
//             // Product Management: Clear fields
//             clearProductFields();
//         } else if (e.getSource() == exitButton) {
//             // Product Management: Exit
//             System.exit(0);
//         } /**else if (e.getSource() == addToCartButton) {
//             // Shopping Cart: Add item to cart
//             addToCart();
//         } else if (e.getSource() == removeFromCartButton) {
//             // Shopping Cart: Remove item from cart
//             removeFromCart();
//         } else if (e.getSource() == viewCartButton) {
//             // Shopping Cart: View cart
//             viewCart();
//         }**/
//     }

//     private void clearProductFields() {
//         // textField1.setText("");
//         textField2.setText("");
//         // textField3.setText("");
//         textField4.setText("");
//     }

//     private void showProducts() {
//         // Define the desired order of columns
//         String[] columns = {"Product Code", "Product Brand", "Product Name", "Quantity", "Price"};
        
//         // Create a 2D array to hold the data
//         Object[][] data = new Object[products.size()][5];
    
//         // Populate the data array with product information
//         for (int i = 0; i < products.size(); i++) {
//             data[i][0] = products.get(i)[0]; // Product Code
//             data[i][1] = ""; // Product Brand (replace with actual data)
//             data[i][2] = products.get(i)[1]; // Product Name
//             data[i][3] = products.get(i)[3]; // Quantity
//             data[i][4] = products.get(i)[2]; // Price
//         }
    
//         // Create the JTable with the updated columns and data
//         JTable table = new JTable(data, columns);
//         JScrollPane scrollPane = new JScrollPane(table);
    
//         // Create and display the JFrame for viewing products
//         JFrame frame = new JFrame("View Products");
//         frame.add(scrollPane);
//         frame.setSize(800, 400);
//         frame.setVisible(true);
//     }

//     // private void editProduct() {
//     //     String productCode = JOptionPane.showInputDialog(this, "Enter product code to edit:");
//     //     for (int i = 0; i < products.size(); i++) {
//     //         if (products.get(i)[0].equals(productCode)) {
//     //             String[] product = new String[4];
//     //             product[0] = productCode;
//     //             product[1] = textField2.getText();
//     //             product[2] = textField3.getText();
//     //             product[3] = textField4.getText();
//     //             products.set(i, product);
//     //             JOptionPane.showMessageDialog(this, "Product edited successfully");
//     //             clearProductFields();
//     //             return;
//     //         }
//     //     }
//     //     JOptionPane.showMessageDialog(this, "Product not found");
//     // }

//     private void deleteProduct() {
//         String productCode = JOptionPane.showInputDialog(this, "Enter product code to delete:");
//         for (int i = 0; i < products.size(); i++) {
//             if (products.get(i)[0].equals(productCode)) {
//                 products.remove(i);
//                 JOptionPane.showMessageDialog(this, "Product deleted successfully");
//                 clearProductFields();
//                 return;
//             }
//         }
//         JOptionPane.showMessageDialog(this, "Product not found");
//     }

//     // private void addToCart() {
//     //     int selectedIndex = itemList.getSelectedIndex();
//     //     if (selectedIndex != -1) {
//     //         String[] selectedProduct = products.get(selectedIndex);
//     //         int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
//     //         if (quantity > 0 && quantity <= Integer.parseInt(selectedProduct[3])) {
//     //             OrderLine orderLine = findOrderLine(selectedProduct);
//     //             if (orderLine != null) {
//     //                 orderLine.increaseQuantity(quantity);
//     //             } else {
//     //                 orderLine = new OrderLine(selectedProduct, quantity, orderNumber);
//     //                 orderLines.add(orderLine);
//     //                 orderNumber++;
//     //             }
//     //             selectedProduct[3] = String.valueOf(Integer.parseInt(selectedProduct[3]) - quantity);
//     //             updateProductList();
//     //         } else {
//     //             JOptionPane.showMessageDialog(this, "Invalid quantity or not enough stock.");
//     //         }
//     //     }
//     // }

//     // private void removeFromCart() {
//     //     int selectedIndex = itemList.getSelectedIndex();
//     //     if (selectedIndex != -1) {
//     //         OrderLine orderLine = orderLines.get(selectedIndex);
//     //         int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity to remove:"));
//     //         if (quantity > 0 && quantity <= orderLine.getQuantity()) {
//     //             orderLine.increaseQuantity(-quantity);
//     //             String[] product = orderLine.getProduct();
//     //             product[3] = String.valueOf(Integer.parseInt(product[3]) + quantity);
//     //             if (orderLine.getQuantity() == 0) {
//     //                 orderLines.remove(orderLine);
//     //             }
//     //             updateProductList();
//     //         } else {
//     //             JOptionPane.showMessageDialog(this, "Invalid quantity.");
//     //         }
//     //     }
//     // }

//     // private void viewCart() {
//     //     StringBuilder cartMessage = new StringBuilder("Shopping Cart:\n");
//     //     for (OrderLine orderLine : orderLines) {
//     //         cartMessage.append(orderLine.getQuantity()).append(" x ").append(orderLine.getProduct()[1])
//     //                 .append(" (Order Number: ").append(orderLine.getOrderNumber()).append(")\n");
//     //     }
//     //     JOptionPane.showMessageDialog(this, cartMessage.toString());
//     // }

//     // private OrderLine findOrderLine(String[] product) {
//     //     for (OrderLine orderLine : orderLines) {
//     //         if (orderLine.getProduct().equals(product)) {
//     //             return orderLine;
//     //         }
//     //     }
//     //     return null;
//     // }

//     // private void updateProductList() {
//     //     itemListModel.clear();
//     //     for (String[] product : products) {
//     //         itemListModel.addElement(product[1] + " (Price: $" + product[2] + ", Quantity: " + product[3] + ")");
//     //     }
//     // }

//     // private class OrderLine {
//     //     private String[] product;
//     //     private int quantity;
//     //     private int orderNumber;

//     //     public OrderLine(String[] product, int quantity, int orderNumber) {
//     //         this.product = product;
//     //         this.quantity = quantity;
//     //         this.orderNumber = orderNumber;
//     //     }

//     //     public int getOrderNumber() {
//     //         return orderNumber;
//     //     }

//     //     public String[] getProduct() {
//     //         return product;
//     //     }

//     //     public int getQuantity() {
//     //         return quantity;
//     //     }

//     //     public void increaseQuantity(int amount) {
//     //         quantity += amount;
//     //     }
//     // }

//     public static void main(String[] args) {
//         new Customer();
//     }
// }

package com.mycompany.orderpage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Order extends JFrame implements ActionListener {
    private JLabel label2, label4;
    private JTextField textField2, textField4;
    private JButton addButton, viewButton, deleteButton, clearButton, exitButton;
    private JPanel productPanel;

    private ArrayList<String[]> products = new ArrayList<String[]>();

    public Order() {
        setTitle("Product Management and Shopping Cart");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        label2 = new JLabel("Product Name: ");
        label2.setBorder(new EmptyBorder(0, 20, 0, 0));

        label4 = new JLabel("Quantity: ");
        label4.setBorder(new EmptyBorder(0, 20, 0, 0));

        textField2 = new JTextField(45);
        textField4 = new JTextField(5);

        addButton = new JButton("Add");
        viewButton = new JButton("View");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Set up the productPanel with GridLayout
        productPanel = new JPanel(new GridLayout(10, 2));

        // Combine label2 and textField2 in a single line using a JPanel with FlowLayout
        JPanel label2TextField2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label2TextField2Panel.add(label2);
        label2TextField2Panel.add(textField2);

        JPanel label4TextField4Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label4TextField4Panel.add(label4);
        label4TextField4Panel.add(textField4);

        // Add the combined label2 and textField2 panel to productPanel
        productPanel.add(label2TextField2Panel);
        productPanel.add(label4TextField4Panel);
        productPanel.add(addButton);
        productPanel.add(viewButton);
        productPanel.add(deleteButton);
        productPanel.add(clearButton);
        productPanel.add(exitButton);

        // Set up the main layout
        setLayout(new GridLayout(1, 2));
        add(productPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String[] product = new String[4];
            product[1] = textField2.getText();
            product[3] = textField4.getText();
            products.add(product);
            JOptionPane.showMessageDialog(this, "Product added successfully");
            clearProductFields();
        } else if (e.getSource() == viewButton) {
            showProducts();
        } else if (e.getSource() == deleteButton) {
            deleteProduct();
        } else if (e.getSource() == clearButton) {
            clearProductFields();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void clearProductFields() {
        textField2.setText("");
        textField4.setText("");
    }

    private void showProducts() {
        String[] columns = {"Product Code", "Product Brand", "Product Name", "Quantity", "Price"};
        Object[][] data = new Object[products.size()][5];

        for (int i = 0; i < products.size(); i++) {
            data[i][0] = products.get(i)[0];
            data[i][1] = "";
            data[i][2] = products.get(i)[1];
            data[i][3] = products.get(i)[3];
            data[i][4] = products.get(i)[2];
        }

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("View Products");
        frame.add(scrollPane);
        frame.setSize(900, 500);
        frame.setVisible(true);
    }

    private void deleteProduct() {
        String productNameToDelete = JOptionPane.showInputDialog(this, "Enter product name to delete:");

        for (Iterator<String[]> iterator = products.iterator(); iterator.hasNext();) {
            String[] product = iterator.next();
            if (product[1].equals(productNameToDelete)) {
                iterator.remove();
                JOptionPane.showMessageDialog(this, "Product deleted successfully");
                showProducts(); // Refresh the view after deleting a product
                clearProductFields();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Product not found");
    }

    public static void main(String[] args) {
        new Order();
    }
}
