package com.busterminal.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class User implements Serializable {

    protected int iD;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDate dateOfBirth;
    protected String password;
    private String phoneNumber;
    private String gender;

    public User(int iD, String firstName, String lastName, String address, String email, LocalDate dateOfBirth, String password, String phoneNumber, String gender) {
        this.iD = iD;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public User(int id, String name) {
        this.iD = id;
        this.firstName = name;

    }

    ;
    

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getID() {
        return iD;
    }

    public void setID(int ID) {
        this.iD = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate DateOfBirth) {
        this.dateOfBirth = DateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // generate Password for Add new Employee
    public static String generateEmployeePassword() {
        String allcharacter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_-+=<>?abcdefghijklmnopqrstuvwxyz0123456789";
        int passwordLength = 8;

        Random random = new SecureRandom();
        //ensure that I'm using a cryptographically secure random number generator.
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            int index = random.nextInt(allcharacter.length());
            password.append(allcharacter.charAt(index));
        }
        return password.toString();
    }

    //Generate Employee id and client ***********************************************
    public static int generateEmployeeID() {
        int lowerBound = 1000000;
        int uperBound = 9999999;
        Set<Integer> usedIds = new HashSet<>();
        //Set interface does not allow duplicate elements, so adding the same element again won't have any effect. 
        ObjectInputStream ois = null;
        try {
            DummyEmployee e;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                e = (DummyEmployee) ois.readObject();
                usedIds.add(e.getID());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
            }
        }
        Random random = new Random();
        int id;
        do {
            id = lowerBound + random.nextInt(uperBound - lowerBound);
        } while (usedIds.contains(id));
        usedIds.add(id);
        return id;
    }

    public static int generateClientID() {
        int lowerBound = 1000000;
        int uperBound = 9999999;
        Set<Integer> usedIds = new HashSet<>();
        ObjectInputStream ois = null;
        try {
            Client e;
            ois = new ObjectInputStream(new FileInputStream("Client.bin"));

            while (true) {
                e = (Client) ois.readObject();
                usedIds.add(e.getID());
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
        Random random = new Random();
        int id;
        do {
            id = lowerBound + random.nextInt(uperBound - lowerBound);
        } while (usedIds.contains(id));
        usedIds.add(id);
        return id;
    }

    //************************************************************************************************
    // check is Employe and Client Id  and email already exist in the binary file********************return boolean
    public static boolean checkEmployeeIdtExistence(int employeeID) {
        ObjectInputStream ois = null;
        boolean result = false;
        try {
            DummyEmployee c;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                c = (DummyEmployee) ois.readObject();
                if (c.getID() == employeeID) {
                    result = true;
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
        return result;
    }

    public static boolean checkEmployeeEmailExistence(String email) {
        ObjectInputStream ois = null;
        boolean result = false;
        try {
            DummyEmployee c;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                c = (DummyEmployee) ois.readObject();
                if (c.getEmail().equals(email)) {
                    result = true;
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
        return result;
    }

    public static boolean checkClientIdExistence(int clientID) {
        ObjectInputStream ois = null;
        boolean result = false;
        try {
            Client c;
            ois = new ObjectInputStream(new FileInputStream("Client.bin"));

            while (true) {
                c = (Client) ois.readObject();
                if (c.getID() == clientID) {
                    result = true;
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
        return result;
    }

    public static boolean checkClientEmailExistence(String email) {
        ObjectInputStream ois = null;
        boolean result = false;
        try {
            Client c;
            ois = new ObjectInputStream(new FileInputStream("Client.bin"));

            while (true) {
                c = (Client) ois.readObject();
                if (c.getEmail().equals(email)) {
                    result = true;
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
        return result;
    }

    //****************************************************************************
    // Get Client existance instance (before added)
    public static Client getClientInstance(String clientEmail) {
        ObjectInputStream ois = null;
        Client ci = null;
        try {
            Client c;
            ois = new ObjectInputStream(new FileInputStream("Client.bin"));

            while (true) {
                c = (Client) ois.readObject();
                if (c.getEmail() == clientEmail) {
                    ci = c;
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
        return ci;
    }

    public static DummyEmployee getEmployeeInstance(String employeeEmail) {
        ObjectInputStream ois = null;
        DummyEmployee oc = null;
        try {
            DummyEmployee c;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                c = (DummyEmployee) ois.readObject();
                if (c.getEmail() == employeeEmail) {
                    oc = c;
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
        return oc;
    }

    //*******************************Ceck "email and pass" for Employee and Client login************************
    public static boolean employeepasswordMatch(String designation, String employeEmail, String Password) {
        ObjectInputStream ois = null;
        boolean result = false;
        try {
            DummyEmployee c;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                c = (DummyEmployee) ois.readObject();
                if (c.getDesignation().equals(designation)) {
                    if (c.getEmail().equals(employeEmail)) {
                        if (c.getPassword().equals(Password)) {
                            result = true;
                        }
                    }
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
        return result;
    }

    public static boolean clientpasswordMatch(String email, String password) {
        ObjectInputStream ois = null;
        boolean result = false;
        try {
            Client c;
            ois = new ObjectInputStream(new FileInputStream("Client.bin"));

            while (true) {
                c = (Client) ois.readObject();
                if (c.getEmail().equals(email)) {
                    if (c.getPassword().equals(password)) {
                        result = true;
                    }
                }
            }
        } catch (RuntimeException e) {
        } catch (IOException | ClassNotFoundException ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex1) {
            }
        }
        return result;
    }

    // Email verify
    public static boolean verifyEmailSuffix(String email) {
        String suffix = "@gmail.com";
        return email.endsWith(suffix);

    }

    public static boolean verifyPasswordLength(String password) {
        return password.length() == 8;
    }

}
