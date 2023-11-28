package com.busterminal.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BillItems {

    private int BusId;
    private String partsName;
    private String PartsModel;
    private float partsPrice;
    private float quantity;
    private float total;

    public BillItems(int BusId, String partsName, String PartsModel, float partsPrice, float quantity, float total) {
        this.BusId = BusId;
        this.partsName = partsName;
        this.PartsModel = PartsModel;
        this.partsPrice = partsPrice;
        this.quantity = quantity;
        this.total = total;
    }

    public BillItems() {

    }

    public int getBusId() {
        return BusId;
    }

    public void setBusId(int BusId) {
        this.BusId = BusId;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public String getPartsModel() {
        return PartsModel;
    }

    public void setPartsModel(String PartsModel) {
        this.PartsModel = PartsModel;
    }

    public float getPartsPrice() {
        return partsPrice;
    }

    public void setPartsPrice(float partsPrice) {
        this.partsPrice = partsPrice;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BillItems{" + "BusId=" + BusId + ", partsName=" + partsName + ", PartsModel=" + PartsModel + ", partsPrice=" + partsPrice + ", quantity=" + quantity + ", total=" + total + '}';
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

}
