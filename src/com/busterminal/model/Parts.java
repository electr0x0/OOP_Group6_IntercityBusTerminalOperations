package com.busterminal.model;
import static com.busterminal.model.MaintenanceTask.getTaskList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Parts implements Serializable {
    private String name;
    private String model;
    private String category;
    private int price;
    private int quantity;

    

    public Parts(String name, String model, int price, String category, int quantity) {
        this.name = name;
        this.model = model;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public Parts() {

    }

    public Parts(String name, String category, int price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    

    public static boolean validateInput(String partsName, String partsModel, String partsPrice, String category) {
        return !partsName.isEmpty() && !partsModel.isEmpty() && !partsPrice.isEmpty() && category != null;
    }
    
     public static void addItems(Parts e) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("PartsList.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(e);

        } catch (IOException ex) {
            Logger.getLogger(Parts.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Parts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     
     public static ObservableList<Parts> getItems() {
        ObjectInputStream ois = null;
        ObservableList<Parts> list = FXCollections.observableArrayList();
        try {
            Parts e;
            ois = new ObjectInputStream(new FileInputStream("PartsList.bin"));

            while (true) {
                e = (Parts) ois.readObject();
                list.add(e);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex1) {
            }
        }
        return list;
    }
     
     public static int totalstaff() {
        return getItems().size();
    }
    
    
}
