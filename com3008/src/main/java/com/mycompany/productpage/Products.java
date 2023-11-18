package com.mycompany.productpage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Products {

    private DefaultTableModel model;
    private JTable table;

    public Products() {
        // Set up the JFrame
        JFrame frame = new JFrame("Product Table Example");
        frame.setSize(1000, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the table model with column names
        model = new DefaultTableModel();
        model.addColumn("Brands' Name");
        model.addColumn("Name");
        model.addColumn("Code");
        model.addColumn("Price");

        // Create the JTable with the model
        table = new JTable(model);

        // Set the preferred width for the second column
        TableColumn column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(350);

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        frame.add(scrollPane);

        // Add some sample data to the table
        addRowToTable("Hornby", "L&MR No.58 'Tiger'", "P00312", 229.99);
        addRowToTable("Hornby", "EWS Business (era10)", "P00456", 299.99);
        addRowToTable("Hornby", "BR, Class 43 HST", "P00465", 362.99);
        addRowToTable("Hornby", "railRoad Steam Engine", "P00546", 82.99);
        addRowToTable("Hornby", "L&MR centenary 1930 'Lion'", "P00564", 159.99);
        addRowToTable("Hornby", "Red Rover Train Set", "M00123", 119.99);
        addRowToTable("Hornby", "Santa's express Train Set", "M00213", 79.99);
        addRowToTable("Hornby", "The Scotsman Train Set", "M00321", 249.99);
        addRowToTable("Hornby", "Valley drifter Train Set", "M00132", 98.99);
        addRowToTable("Hornby", "iTraveller 6000 Train Set", "M00231", 159.99);
        addRowToTable("Hornby", "BR, Class 50, Co-Co 'Leviathan'", "L00654", 167.99);
        addRowToTable("Hornby", "BR, Class A4 'Falcon'", "L00645", 214.99);
        addRowToTable("Hornby", "BR, Class A3 'Night Hawk'", "L00789", 167.99);
        addRowToTable("Hornby", "Coca-Cola class 121", "L00798", 96.99);
        addRowToTable("Hornby", "Analogue Train and Accessory Controller", "C00879", 19.49);
        addRowToTable("Hornby", "Elite' Digital Controller", "C00897", 326.75);
        addRowToTable("Hornby", "HM 2000 Analogue Controller", "C00987", 145.25);
        addRowToTable("Hornby", "Pullman third class brake no.162", "S00978", 53.49);
        addRowToTable("Hornby", "BR intercity, MK3 Tourist Standard open", "S00147", 37.49);
        addRowToTable("Hornby", "GWR, Mk3 Trailer First", "S00174", 37.49);

        addRowToTable("Bachmann", "Remote crossover turnout left, HO scale", "R00417", 122.00);
        addRowToTable("Bachmann", "Remote crossover turnout right, HO scale", "R01417", 122.00);
        addRowToTable("Bachmann", "Single Crossover turnout left, N scale", "R01471", 120.00);
        addRowToTable("Bachmann", "Single Crossover turnout right, N scale", "R00471", 120.00);
        addRowToTable("Bachmann", "10 inch Straight Track (24pcs)", "R00741", 159.95);
        addRowToTable("Bachmann", "Curved Track Pack", "R00714", 36.95);
        addRowToTable("Bachmann", "Power Pack and Speed Controller", "C00258", 102.00);
        addRowToTable("Bachmann", "Power Pack and Speed Controller (large scale)", "C00285", 105.00);
        addRowToTable("Bachmann", "E-Z Command plus DCC Controller", "C00528", 265.00);
        addRowToTable("Bachmann", "Alaska GP40", "L00582", 135.00);
        addRowToTable("Bachmann", "Boston & Maine GP40", "L00852", 135.00);
        addRowToTable("Bachmann", "Canadian National GP35", "L00825", 239.00);
        addRowToTable("Bachmann", "Conrail GP40", "L00369", 135.00);
        addRowToTable("Bachmann", "Boxcar-Union Pacific", "S00396", 39.99);
        addRowToTable("Bachmann", "Gondola-Bethlehem Steel", "S00693", 31.50);
        addRowToTable("Bachmann", "Quad Hopper-CSX", "S00639", 15.99);
        addRowToTable("Bachmann", "Express Reefer - Pacific Fruit Express", "S00963", 34.99);
        addRowToTable("Bachmann", "Plug Door-Erie Lackawanna", "S00936", 13.99);
        addRowToTable("Bachmann", "Chattanooga Train Set (HO scale)", "M00159", 325.00);
        addRowToTable("Bachmann", "Chessie Special Train Set (HO scale)", "M00195", 319.99);
        addRowToTable("Bachmann", "Coastliner Train Set (HO scale)", "M00519", 279.99);
        addRowToTable("Bachmann", "McKinley Explorer Train Set (HO scale)", "M00591", 325.00);
        addRowToTable("Bachmann", "Pacific Flyer Train Set (HO scale)", "M00951", 209.99);

        addRowToTable("Graham Farish", "Western Rambler Train Set", "M00915", 189.99);
        addRowToTable("Graham Farish", "Suburban Sulzer Train Set", "M00753", 209.99);
        addRowToTable("Graham Farish", "The Thanet Flyer Train Set", "M00735", 274.95);
        addRowToTable("Graham Farish", "Moving Mountains Train Set", "M00357", 259.95);
        addRowToTable("Graham Farish", "Class 90/0 'Financial Times' BR Intercity", "L00375", 194.95);
        addRowToTable("Graham Farish", "Class 90/1 BR Railfreight Distribution Sector", "L00573", 294.95);
        addRowToTable("Graham Farish", "Class 90/0 'Penny Black' Rail Express System", "L00537", 194.95);
        addRowToTable("Graham Farish", "Class 08 BR Engineers Grey", "L00486", 244.95);
        addRowToTable("Graham Farish", "Class 08 Network Rail Yellow", "L00468", 244.95);
        addRowToTable("Graham Farish", "Class 08 BR Blue", "L00846", 249.95);
        addRowToTable("Graham Farish", "7 Plank Wagon End Door BR GREY", "S00864", 19.95);
        addRowToTable("Graham Farish", "5 Plank Wagon Wooden Floor 'J.H.RAINBOW'RED", "S00684", 16.50);
        addRowToTable("Graham Farish", "Stanier brake second BR MAROON Coach", "S00648", 36.50);
        addRowToTable("Graham Farish", "BR Departmental Bogie B Luggage Van", "S00426", 30.95);
        addRowToTable("Graham Farish", "14 TON Tank Wagon 'RONUK' no.43", "S00462", 17.95);
        addRowToTable("Graham Farish", "Gaugemaster Model D Twin Train and Model Railway Controller", "C00246", 144.99);
        addRowToTable("Graham Farish", "Gaugemaster Model Q 4 Track Model Railway Controller", "C00264", 259.99);
        addRowToTable("Graham Farish", "Gaugemaster Single Track Controller with Transformer", "C00624", 51.99);
        addRowToTable("Graham Farish", "Scalextric ARC AIR/PRO Wireless Hand Controller", "C00642", 29.99);
        addRowToTable("Graham Farish", "Scalextric Adjustable Analogue Hand Controller", "C00759", 20.99);
        addRowToTable("Graham Farish", "LGB Right Electric Turnout, R1, 30°", "R00795", 67.49);
        addRowToTable("Graham Farish", "LGB Right Manual Turnout R3, 22.5°", "R00579", 74.25);
        addRowToTable("Graham Farish", "LGB Curved Track R1. 15°", "R00597", 6.75);
        addRowToTable("Graham Farish", "LGB Old Timer Track Buffer Stop", "R00957", 20.03);
        addRowToTable("Graham Farish", "LGB Crossing, R1, 30°", "R00975", 49.49);

        addRowToTable("Peco", "00-9 'Purple Moose Brewery' L&B Box Van", "S00153", 22.90);
        addRowToTable("Peco", "00-9 Box Van Fyffes Bananas", "S00135", 22.90);
        addRowToTable("Peco", "00-9 Box Van Colman's Mustard", "S00513", 22.90);
        addRowToTable("Peco", "00-9 Box Van Bass", "S00531", 22.90);
        addRowToTable("Peco", "10ft WB Wagon Chassis, Steel Type Sole Bars with Disc Wheels", "S00351", 5.26);
        addRowToTable("Peco", "SL-702FB Flexible Track with Concrete Sleeper", "R00426", 12.50);
        addRowToTable("Peco", "SL-U77 Curved Turnout, Small Radius, Left Hand", "R00462", 25.96);
        addRowToTable("Peco", "SL-U76 Curved Turnout, Small Radius, Right Hand", "R00246", 25.96);
        addRowToTable("Peco", "SL-600 Flexible Track, Wooden Sleeper", "R00264", 15.00);
        addRowToTable("Peco", "Bullhead OO Gauge Slip-Double", "R00624", 79.96);

        addRowToTable("Dapol", "Sentinel Y1/Y3 LNER 42", "L00642", 195.62);
        addRowToTable("Dapol", "A4 Valanced Silver King 2551 LNER Silver Gray", "L00183", 172.74);
        addRowToTable("Dapol", "Class 57xx Pannier 9669 BR Black Late Crest", "L00138", 262.68);
        addRowToTable("Dapol", "Class 08 Intercity 08795 Swansea", "L00813", 251.51);
        addRowToTable("Dapol", "Class 68 68014 Chiltern DCC & Sound", "L00831", 286.20);
        addRowToTable("Dapol", "LHT-627 Suburban B 4 Coach Set BR Birmingham #45 C & C Twin Cities", "M00381", 885.60);
        addRowToTable("Dapol", "LHT-626 Suburban B 4 Coach Set BR London #34 Lined Maroon", "M00318", 885.60);
        addRowToTable("Dapol", "LHT 633 No Division & Unnumbered GWR C & C Twin Cities", "M00729", 286.60);
        addRowToTable("Dapol", "2P-003-012 N Gauge B Set Coach Pack Cities Crest Choc & Cream 6445 & 6446", "M00792", 37.80);
        addRowToTable("Dapol", "2P-003-015 N Gauge B Set Coach Pack BR Crimson 66461 & 6464", "M00927", 37.80);
    
        // Make the frame visible
        frame.setVisible(true);
    }

    private void addRowToTable(String brand, String name, String code, double price) {
        Object[] rowData = {brand, name, code, price};
        model.addRow(rowData);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Products::new);
    }
}
