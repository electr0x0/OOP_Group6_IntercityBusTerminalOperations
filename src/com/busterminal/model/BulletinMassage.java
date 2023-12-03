package com.busterminal.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BulletinMassage implements Serializable {

    private String email;
    private String ccEmail;
    private String subject;
    private LocalDate time;
    private String massage;
    private boolean status;

    public BulletinMassage(String email, String ccEmail, String subject, LocalDate time, String massage, boolean status) {
        this.email = email;
        this.ccEmail = ccEmail;
        this.subject = subject;
        this.time = time;
        this.massage = massage;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCcEmail() {
        return ccEmail;
    }

    public void setCcEmail(String ccEmail) {
        this.ccEmail = ccEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static ObservableList<BulletinMassage> getUpComingMassage() {
        ObjectInputStream ois = null;
        ObservableList<BulletinMassage> list = FXCollections.observableArrayList();
        try {
            BulletinMassage e;
            ois = new ObjectInputStream(new FileInputStream("BulletinMassage.bin"));

            while (true) {
                e = (BulletinMassage) ois.readObject();
                list.add(e);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void sendMassageToUser(BulletinMassage e) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("BulletinMassage.bin");
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

    public static int countUpcomingMassage(String email) {
        int count = 0;
        ArrayList<BulletinMassage> list = new ArrayList<>();
        list = showIndivisualMessage(email);
        for (BulletinMassage b : list) {
            if (b.isStatus() == true) {
                count += 1;
            }
        }

        return count;
    }

    public static ArrayList<BulletinMassage> showIndivisualMessage(String email) {
        ArrayList<BulletinMassage> massagelist = new ArrayList<>();
        ObservableList<BulletinMassage> list = FXCollections.observableArrayList();
        list = getUpComingMassage();
        for (BulletinMassage b : list) {
            if (b.getEmail().equals(email) || b.getCcEmail().equals(email)) {
                massagelist.add(b);
            }
        }
        return massagelist;
    }

    public static void readMassage(BulletinMassage b) {
        // create a arraylist for storing all intance from bin file
        ArrayList<BulletinMassage> massageList = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            BulletinMassage c;
            ois = new ObjectInputStream(new FileInputStream("BulletinMassage.bin"));

            while (true) {
                c = (BulletinMassage) ois.readObject();
                if (!c.email.equals(b.email)) {
                    massageList.add(c);
                } else {
                    c.setStatus(false);

                    massageList.add(c);
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
        File file = new File("BulletinMassage.bin");

        // Now delete the file
        file.delete();

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        // again create a new file
        f = new File("BulletinMassage.bin");
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for (BulletinMassage e : massageList) {
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

    public static void deleteMassage(BulletinMassage b) {
        // create a arraylist for storing all intance from bin file
        ArrayList<BulletinMassage> massageList = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            BulletinMassage c;
            ois = new ObjectInputStream(new FileInputStream("BulletinMassage.bin"));

            while (true) {
                c = (BulletinMassage) ois.readObject();
                if (!c.email.equals(b.email)) {
                    massageList.add(c);
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
        File file = new File("BulletinMassage.bin");

        // Now delete the file
        file.delete();

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        // again create a new file
        f = new File("BulletinMassage.bin");
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for (BulletinMassage e : massageList) {
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
