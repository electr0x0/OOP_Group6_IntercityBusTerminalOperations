package com.busterminal.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Administrator {

    public static void employeeCreateNewAccount(DummyEmployee e) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("Employee.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(e);

        } catch (IOException ex) {
            Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void clientCreateNewAccount(Client c) {

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("Client.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(c);

        } catch (IOException ex) {
            Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ObservableList<Client> getClientList() {
        ObjectInputStream ois = null;
        ObservableList<Client> list = FXCollections.observableArrayList();
        try {
            Client c;
            ois = new ObjectInputStream(new FileInputStream("Client.bin"));

            while (true) {
                c = (Client) ois.readObject();
                list.add(c);
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

    public static ObservableList<DummyEmployee> getEmployeeList() {
        ObjectInputStream ois = null;
        ObservableList<DummyEmployee> list = FXCollections.observableArrayList();
        try {
            DummyEmployee e;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                e = (DummyEmployee) ois.readObject();
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

    public static void deleteEmployee(DummyEmployee p) {
        // create a arraylist for storing all intance from bin file
        ArrayList<DummyEmployee> employeelist = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            DummyEmployee c;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                c = (DummyEmployee) ois.readObject();
                if (!(c.getID() == p.getID())) {
                    employeelist.add(c);
                }
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

        // get file path
        File file = new File("Employee.bin");

        // Now delete the file
        file.delete();

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        // again create a new file
        f = new File("Employee.bin");
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for (DummyEmployee e : employeelist) {
                oos.writeObject(e);
            }
        } catch (IOException ex) {
            Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
            };
        }

    }

    public static void updateClientPassword(String email, String newPassword) {
        // create a arraylist for storing all intance from bin file
        ArrayList<Client> clientlist = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            Client c;
            ois = new ObjectInputStream(new FileInputStream("Client.bin"));

            while (true) {
                c = (Client) ois.readObject();
                if (!c.getEmail().toLowerCase().trim().equals(email.toLowerCase().trim())) {
                    clientlist.add(c);
                } else {
                    c.setPassword(newPassword);
                    clientlist.add(c);
                }
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

        // get file path
        File file = new File("Client.bin");

        // Now delete the file
        file.delete();

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        // again create a new file
        f = new File("Client.bin");
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for (Client e : clientlist) {
                oos.writeObject(e);
            }
        } catch (IOException ex) {
            Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void updatEmployeePassword(String email, String newPassword) {
        // create a arraylist for storing all intance from bin file
        ArrayList<DummyEmployee> employeelist = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            DummyEmployee c;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                c = (DummyEmployee) ois.readObject();
                if (!c.getEmail().toLowerCase().trim().equals(email.toLowerCase().trim())) {
                    employeelist.add(c);
                } else {
                    c.setPassword(newPassword);
                    System.out.println("After pass change" + c.getPassword());
                    employeelist.add(c);
                }

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

        // get file path
        File file = new File("Employee.bin");

        // Now delete the file
        file.delete();

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        // again create a new file
        f = new File("Employee.bin");
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for (DummyEmployee e : employeelist) {
                oos.writeObject(e);
            }
        } catch (IOException ex) {
            Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
