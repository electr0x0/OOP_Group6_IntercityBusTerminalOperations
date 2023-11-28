package com.busterminal.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class Database {
    /*
    public static <T> ObservableList<T> getInstanceBinFile(String path),
    it indicates that it's a generic method. The <T> before the return type specifies that 
    the method has a type parameter, and the type parameter is named T.
    */

    public static <T> ObservableList<T> getInstanceBinFile(String path) {
        ObjectInputStream ois = null;
        ObservableList<T> list = FXCollections.observableArrayList();

        try {
            Object obj;
            ois = new ObjectInputStream(new FileInputStream(path));

            while (true) {
                obj = ois.readObject();
                list.add((T) obj); // Cast the read object to type T and add it to the list
            }
        } catch (IOException e) {
            // End of file reached
        } catch (ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return list;
    }
    public static <T> void writeToBinFile(String path, ObservableList<T> collection) {
        try (ObjectOutputStream oos = new AppendableObjectOutputStream(new FileOutputStream(path,true))) {
            for (T obj : collection) {
                oos.writeObject(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   
    
    
}
