
/*
package com.busterminal.Interface;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import mainPkg.model.Client;
import mainPkg.model.Employee;

public interface Account {

    //Generate Employee id and client ***********************************************
    public static int generateEmployeeID() {
        int lowerBound = 1000000;
        int uperBound = 9999999;
        Set<Integer> usedIds = new HashSet<>();
        //Set interface does not allow duplicate elements, so adding the same element again won't have any effect. 
        ObjectInputStream ois = null;
        try {
            Employee e;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                e = (Employee) ois.readObject();
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
            Employee c;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                c = (Employee) ois.readObject();
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
            Employee c;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                c = (Employee) ois.readObject();
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

    public static Employee getEmployeeInstance(String employeeEmail) {
        ObjectInputStream ois = null;
        Employee oc = null;
        try {
            Employee c;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                c = (Employee) ois.readObject();
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
            Employee c;
            ois = new ObjectInputStream(new FileInputStream("Employee.bin"));

            while (true) {
                c = (Employee) ois.readObject();
                if (c.getDesignation() == designation) {
                    if (c.getEmail() == employeEmail) {
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

    // Email verify
    public static boolean verifyEmailSuffix(String email) {
        String suffix = "@gmail.com";
        return email.endsWith(suffix);

    }
    
   public static boolean verifyPasswordLength(String password){
       return password.length()==8;
   }
    
}
*/