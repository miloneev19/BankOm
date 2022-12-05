import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Account implements Serializable {
    private static String name;
    private static String pan;
    private static String address;
    private static String IFSC;
    private static String aadhar;
    private static String mobile;
    private static String accountType;
    private static String Branch;

    private static String accountNumber = String.valueOf(gen_acc());
    private static String username;
    private static String password;
    private static int age;
    private static double balance;
    private static double todaysWithdrawable;
    private static double dailyLimit;
    private static final double minBalance = 10000;

    static Scanner s = new Scanner(System.in);
    static Scanner s1 = new Scanner(System.in);
    private static ArrayList<Transactions> transactions;
    static File txn = new File("Transactions.txt");
    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;

    public static void ReadTxn() throws Exception {
        ois = new ObjectInputStream(new FileInputStream(txn));
        transactions = (ArrayList<Transactions>) ois.readObject();
        ois.close();
    }

    public static void WriteTxn() throws Exception {
        oos = new ObjectOutputStream(new FileOutputStream(txn));
        oos.writeObject(transactions);
        oos.close();
    }

    public Account() {
        System.out.println("You're now creating a new account");
        System.out.println("Create your Account: ");
        System.out.println("Fill out the following details: ");
        System.out.print("Account Type: ");
        account_type();
        System.out.print("Name: ");
        name = s1.nextLine();
        System.out.print("Age: ");
        age = s.nextInt();
        System.out.print("IFSC Code: ");
        Branch();
        System.out.print("Initial Deposit: ");
        balance = s.nextInt();
        System.out.print("Pan Card: ");
        pan = s.next();
        System.out.print("Aadhar No.: ");
        aadhar = s.next();
        System.out.print("Mobile No.:");
        mobile = s.next();
        System.out.print("Address: ");
        address = s1.nextLine();
        System.out.print("Username:");
        username = s.next();
        System.out.print("Password:");
        password = s.next();
        transactions = new ArrayList<>();
        transactions.add(new Transactions(balance, "Deposit"));
        if (accountType.equals("Savings")) {
            dailyLimit = 40000;
            todaysWithdrawable = 40000;
        } else if (accountType.equals("Current")) {
            dailyLimit = 100000;
            todaysWithdrawable = 100000;
        } else {
            dailyLimit = 300000;
            todaysWithdrawable = 300000;
        }
        System.out.println("Account Created Successfully");
        System.out.println("-------------------------------------------------------");
    }

    public Account(String name, int age, String IFSC, double balance, String pan, String aadhar, String mobile, String address, String accountType, String accountNumber) {
        Account.name = name;
        Account.age = age;
        Account.IFSC = IFSC;
        Account.balance = balance;
        Account.pan = pan;
        Account.aadhar = aadhar;
        Account.mobile = mobile;
        Account.address = address;
        Account.accountType = accountType;
        Account.accountNumber = accountNumber;
    }

    public String Branch() {

        System.out.println("Select your Branch");
        System.out.println("1.Shivaji Nagar");
        System.out.println("2.Hinjawadi");
        System.out.println("3.Kothrud");
        System.out.print("Enter choice: ");
        int branch = s.nextInt();
        if (branch == 1) {
            IFSC = "HDFC0098765";
            Branch = "Shivaji Nagar";
            System.out.println("IFSC CODE:  " + IFSC);
        } else if (branch == 2) {
            IFSC = "HDFC0034567";
            Branch = "Hinjawadi";
            System.out.println("IFSC CODE: " + IFSC);
        } else if (branch == 3) {
            IFSC = "HDFC0012345";
            Branch = "Kothrud";
            System.out.println("IFSC CODE: " + IFSC);

        } else {
            System.out.println("Invalid Input !! Please select a branch mentioned above! ");
        }
        return IFSC;
    }

    public String account_type() {
        System.out.println("Select the type of account:");
        System.out.println("1.Saving account");
        System.out.println("2.Current account");
        System.out.println("3.Salary Account");
        System.out.print("Please enter the type of account you want to create: ");
        int account_choice = s.nextInt();
        if (account_choice == 1) {
            System.out.println("You're creating a Savings account.");
            accountType = "Savings";

        } else if (account_choice == 2) {
            System.out.println("You're creating a Current account.");
            accountType = "Current";

        } else if (account_choice == 3) {
            System.out.println("You're creating a Salary account. ");
            accountType = "Salary";

        } else {
            System.out.println("Invalid selection!");
        }
        System.out.println("***************************************************");
        return accountType;
    }

    public static StringBuilder gen_acc() {
        Random rand = new Random();
        StringBuilder card = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            int n = rand.nextInt(10);
            card.append(n);
        }
        return card;
    }

    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nIFSC Code: " + IFSC + "\nBranch: " + Branch + "\nAccount No.: " + accountNumber + "\nAccount Type: " + accountType + "\nBalance: " + balance + "\nPan Card: " + pan + "\nAadhar No.: " + aadhar + "\nMobile No.: " + mobile + "\nAddress: " + address + "\nAccount Type: " + accountType + "\nDaily Limit:" + dailyLimit + "\nToday's Withdrawable Balance: " + todaysWithdrawable;
    }

    public String getName() {
        return name;
    }

    public String getPan() {
        return pan;
    }

    public String getAddress() {
        return address;
    }

    public String getIFSC() {
        return IFSC;
    }

    public String getAadhar() {
        return aadhar;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public static double getBalance() {
        return balance;
    }

    public static double getTodaysWithdrawable() {
        return todaysWithdrawable;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public static void setTodaysWithdrawable() {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).type.equals("Withdrawal")) {
                todaysWithdrawable -= transactions.get(i).amount;
            }
        }
    }

    public static void setBalance(double amount) {
        balance = amount;
    }

    public static void createTransaction(Transactions transactions) {
        Account.transactions.add(transactions);
    }

    public ArrayList<Transactions> getTransactions() {
        return transactions;
    }

    public static double getMinBalance() {
        return minBalance;
    }

    public static void checkBalance() {
        System.out.println("-------------------------------------------------------");
        System.out.println("Total Balance : " + getBalance());
        System.out.println("Available Balance : " + (getBalance() - getMinBalance()));
        System.out.println("Today's remaining Withdrawal Balance : " + getTodaysWithdrawable());
        System.out.println("-------------------------------------------------------");
    }

    public static void deposit(double amount) throws Exception {
        createTransaction(new Transactions(amount, "Deposit")); //creating new transaction in transaction array with type deposit
        setBalance(getBalance() + amount);
        System.out.println("Amount Deposited Successfully!");
        System.out.println("-------------------------------------------------------");
    }

    public static boolean withdraw(double amount) {
        boolean successful = false;
        String message = "";
        boolean insufficientFunds = (getBalance() - amount) < getMinBalance();
        boolean dailyLimitReached = (getTodaysWithdrawable()) <= 0;
        boolean beyondDailyLimit = (getTodaysWithdrawable() - amount) < 0;

        if (!insufficientFunds && !dailyLimitReached) {
            if (!beyondDailyLimit) {
                createTransaction(new Transactions(amount, "Withdrawal"));
                setBalance(getBalance() - amount);
                System.out.println("Withdrawal Successful!");
                System.out.println("----------------------------------------------------------------------------");
                successful = true;
            } else {
                System.out.println("----------------------------------------------------------------------------");
                System.out.println("Withdrawal is beyond daily limit.\nPlease enter a amount less than or equal to " + getTodaysWithdrawable());
                System.out.println("----------------------------------------------------------------------------");

            }

        } else {
            System.out.println("Withdrawal failed!");
            message = (insufficientFunds) ? "Insufficient funds" : dailyLimitReached ? "Daily limit reached." : !beyondDailyLimit ? "Withdrawal is beyond daily limit." : "";
            System.out.println("Error : " + message);
            System.out.println("----------------------------------------------------------------------------");

        }
        setTodaysWithdrawable();
        return successful;

    }

    public static void showTransactions() throws Exception {
        System.out.println("-------------------------------------------------------");
        System.out.println("Sr.No       Amount      Transactions Type");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < transactions.size(); i++) {
            String amt = Double.toString(transactions.get(i).amount);
            if (transactions.size() != 0) {
                System.out.println("-------------------------------------------------------");
                System.out.print("      " + (i + 1) + "    ");
                System.out.println("\t\t\t" + amt + (amt.length() > 5 ? " " : "") + "\t\t\t" + transactions.get(i).type);
            }
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("======================== Total ========================");
        System.out.println("\tTotal Balance:\t" + getBalance());
        System.out.println("-------------------------------------------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println("\n");

    }

    public boolean intraBank(double amount) {
        return withdraw(amount);
    }


    public static void setName(String name) {
        Account.name = name;
    }

    public static void setPan(String pan) {
        Account.pan = pan;
    }

    public static void setAddress(String address) {
        Account.address = address;
    }

    public static void setIFSC(String IFSC) {
        Account.IFSC = IFSC;
    }

    public static void setAadhar(String aadhar) {
        Account.aadhar = aadhar;
    }

    public static void setMobile(String mobile) {
        Account.mobile = mobile;
    }

    public static void setAccountType(String accountType) {
        Account.accountType = accountType;
    }

    public static void setBranch(String branch) {
        Branch = branch;
    }

    public static void setUsername(String username) {
        Account.username = username;
    }

    public static void setPassword(String password) {
        Account.password = password;
    }

    public static void setAge(int age) {
        Account.age = age;
    }
}
