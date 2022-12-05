import java.io.*;
import java.util.ArrayList;
import java.util.Objects; //null pointer exception can be handeled , can be used to compare two strings
import java.util.Scanner;

public class Login implements Serializable{ //object can be converted into a stream , has output and input stream used to in take object etc
    static Scanner s=new Scanner(System.in);
    static Scanner s1=new Scanner(System.in);

    static ArrayList<Account> accounts=new ArrayList<>();  //arraylist to store account
    static File acc = new File("Accounts.txt"); //file to store all account info
    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;

    public void ReadAcc() throws Exception {
        ois = new ObjectInputStream(new FileInputStream(acc));
        accounts = (ArrayList<Account>) ois.readObject();
        ois.close();
    }
    public void WriteAcc() throws Exception {
        oos = new ObjectOutputStream(new FileOutputStream(acc));
        oos.writeObject(accounts);
        oos.close();
    }

    public void Insert() throws Exception {
        accounts.add(new Account()); //adding new account to arraylist
        WriteAcc();
        accountInfo();
        Txnmenu();
    }

    public void loginmenu() throws Exception {
        int input = -1;
        System.out.println("Select The operation you want to perform");
        System.out.println("1. Create Account");
        System.out.println("2. Login into Existing Account");
        System.out.println("3. Exit");
        System.out.print("Choice: ");
        input = s.nextInt();
        switch (input) {
            case 1: {
                try {
                    Insert();
                }catch (Exception e){
                    loginmenu();
                }
                break;
            }
            case 2: {
                try {
                    login();
                }catch (Exception e){
                    loginmenu();
                }
                break;
            }
            case 3: {
                System.out.println("Thank you for Using!");
            }

        }
    }

    private void login() throws Exception {
        if(acc.isFile()) {
            boolean found = false;
            System.out.print("Enter your Username: ");
            String tusername = s.next();
            System.out.print("Enter your Password: ");
            String tpassword = s.next();
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getUsername().equals(tusername) && accounts.get(i).getPassword().equals(tpassword)) {
                    found = true;
                }
            }
            if (found) {
                try {
                    Txnmenu();
                } catch (Exception e) {
                    Txnmenu();
                }
            } else {
                System.out.println("Account not found, Try again...");
                loginmenu();
            }
        }
    }

    public void Txnmenu() throws Exception {
        int input = -1,tempAmount=0;
        Account a;
        do{
            System.out.println("1. Check Balance");
            System.out.println("2. Account Info");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Show transactions");
            System.out.println("6. Modify Customer Data");
            System.out.println("7. Transfer Money");
            System.out.println("8. Return to Login Screen");
            System.out.println("9. Exit");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter the choice code : ");
            input = s.nextInt();
            switch (input){
                case 1:
                    Account.checkBalance();
                    break;
                case 2:
                    accountInfo();
                    break;
                case 3:
                    System.out.println("============== Deposit ==============");
                    System.out.print("Enter the amount to be deposited : ");
                    tempAmount = s.nextInt();
                    Account.deposit(tempAmount);
                    break;
                case 4:
                    System.out.println("============== Withdrawal ==============");
                    System.out.print("Enter the amount to be withdrawn : ");
                    tempAmount = s.nextInt();
                    Account.withdraw(tempAmount);
                    break;
                case 5:
                    Account.showTransactions();
                    break;
                case 6:
                    modify();
                    break;
                case 7:
                    transfer();
                    break;
                case 8:
                    loginmenu();
                    break;
                case 9:
                    input=9;
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }while (input != 9);
    }

    private void modify() throws Exception {
        if (acc.isFile()) {
            ReadAcc();
            boolean found = false;
            System.out.print("Enter Username: ");
            String user = s.next();
            System.out.println("-------------------------------------------------------------------------------------------");
            Account temp;
            for (int i = 0; i < accounts.size(); i++) {
                if (Objects.equals(accounts.get(i).getUsername(), user)) {
                    temp = accounts.get(i);
                    System.out.println(temp.toString());
                    System.out.println("-------------------------------------------------------------------------------------------");
                    reassign(temp);
                    found = true;
                }
            }

            if (found) {
                WriteAcc();
                System.out.println("Record Updated Successfully!");
                System.out.println("-------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Record not Found!");
                System.out.println("-------------------------------------------------------------------------------------------");
            }
        } else {
            System.out.println("File does not Exists!");
        }
    }

    public void reassign(Account temp) {
        int choice = -1;
        do {
            System.out.println("Select Data you want to Change!");
            System.out.println("1.Name");
            System.out.println("2.Username");
            System.out.println("3.Address");
            System.out.println("4.Age");
            System.out.println("5.Branch");
            System.out.println("6.Account Type");
            System.out.println("7.PAN Card");
            System.out.println("8.Aadhar Card");
            System.out.println("9.Password");
            System.out.println("10.Mobile No.");
            System.out.println("11.Exit");
            System.out.print("Choice: ");
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Change the Name.");
                    System.out.print("Enter new Name: ");
                    String tname = s1.nextLine();
                    temp.setName(tname);
                    accounts.set(accounts.indexOf(temp), temp);
                    System.out.println("Record Updated Successfully!");
                    System.out.println("-------------------------------------------------------------------------------------------");
                    break;
                case 2:
                    System.out.println("Change the Username.");
                    System.out.print("Enter new Username: ");
                    String tid = s.next();
                    temp.setUsername(tid);
                    accounts.set(accounts.indexOf(temp), temp);
                    System.out.println("Record Updated Successfully!");
                    System.out.println("-------------------------------------------------------------------------------------------");
                    break;
                case 3:
                    System.out.println("Change the Address.");
                    System.out.print("Enter new Address: ");
                    String taddress = s1.nextLine();
                    temp.setAddress(taddress);
                    accounts.set(accounts.indexOf(temp), temp);
                    System.out.println("Record Updated Successfully!");
                    System.out.println("-------------------------------------------------------------------------------------------");
                    break;
                case 4:
                    System.out.println("Change the Age.");
                    System.out.print("Enter new Age: ");
                    int tage = s.nextInt();
                    temp.setAge(tage);
                    accounts.set(accounts.indexOf(temp), temp);
                    System.out.println("Record Updated Successfully!");
                    System.out.println("-------------------------------------------------------------------------------------------");
                    break;
                case 5:
                    System.out.println("Change the Branch.");
                    System.out.print("Enter new Branch: ");
                    String tbranch = temp.Branch();
                    temp.setBranch(tbranch);
                    accounts.set(accounts.indexOf(temp), temp);
                    System.out.println("Record Updated Successfully!");
                    System.out.println("-------------------------------------------------------------------------------------------");
                    break;
                case 6:
                    System.out.println("Change the Account Type.");
                    System.out.print("Enter new Account Type: ");
                    String tacctype =temp.account_type();
                    temp.setAccountType(tacctype);
                    accounts.set(accounts.indexOf(temp), temp);
                    System.out.println("Record Updated Successfully!");
                    System.out.println("-------------------------------------------------------------------------------------------");
                    break;
                case 7:
                    System.out.println("Change the PAN Card.");
                    System.out.print("Enter new PAN Card: ");
                    String tpan = s.next();
                    temp.setPan(tpan);
                    accounts.set(accounts.indexOf(temp), temp);
                    System.out.println("Record Updated Successfully!");
                    System.out.println("-------------------------------------------------------------------------------------------");
                    break;
                case 8:
                    System.out.println("Change the Aadhar Card.");
                    System.out.print("Enter new Aadhar Card: ");
                    String taadhar = s.next();
                    temp.setAadhar(taadhar);
                    accounts.set(accounts.indexOf(temp), temp);
                    System.out.println("Record Updated Successfully!");
                    System.out.println("-------------------------------------------------------------------------------------------");
                    break;
                case 9:
                    System.out.println("Change the Password.");
                    System.out.print("Enter new Password: ");
                    String tpassword = s.next();
                    temp.setPassword(tpassword);
                    accounts.set(accounts.indexOf(temp), temp);
                    System.out.println("Record Updated Successfully!");
                    System.out.println("-------------------------------------------------------------------------------------------");
                    break;
                case 10:
                    System.out.println("Change the Mobile No.");
                    System.out.print("Enter new Mobile No.: ");
                    String tmob = s.next();
                    temp.setMobile(tmob);
                    accounts.set(accounts.indexOf(temp), temp);
                    System.out.println("Record Updated Successfully!");
                    System.out.println("-------------------------------------------------------------------------------------------");
                    break;
                case 11:
                    choice = 11;
                    break;
                default:
                    System.out.println("Enter Valid Input!");
            }


        } while (choice != 11);
    }

    private void transfer() throws Exception {
        System.out.println("-------------------------------------------------------------------------------------------");
        for (int i = 0; i < accounts.size(); i++) {
            if (!Objects.equals(accounts.get(i).getName(), acc.getName())) {
                System.out.println((i+1) + "\t" + accounts.get(i).getName());
                System.out.println("-------------------------------------------------------------------------------------------");
            }
        }

    }


    public void accountInfo() throws Exception {   //method to display account info
        System.out.println("-------------------------------------------------------------------------------------------");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).toString());
            System.out.println("-------------------------------------------------------------------------------------------");
        }

    }




}

