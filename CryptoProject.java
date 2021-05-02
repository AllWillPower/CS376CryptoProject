package project;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class CryptoProject {

    public static void main(String[] args) throws SQLException {

        //Get the full filepath
        String fullFilepath = "/Users/adanvivero/IdeaProjects/NaiveBayesPersonal/src/dictionary.txt";
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fullFilepath));
            while (reader.readLine() != null) lines++;
            reader.close();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        String dictionary[] = new String[lines];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fullFilepath));
            for(int i = 0; i < lines; i++) {
                dictionary[i] = reader.readLine();
            }
            reader.close();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        int spamWordFreq[] = new int[lines];
        int hamWordFreq[] = new int[lines];
        for(int i = 0; i < lines; i++) {
            spamWordFreq[i] = 1;
            hamWordFreq[i] = 1;
        }

        double totalSpamWords = lines;
        double totalSpamMessages = 0;
        double totalHamWords = lines;
        double totalHamMessages = 0;

        Connection dbConnection = null;
        //base connection to database
        try {
            String url = "jdbc:mysql://35.202.111.69:3306/spamFilterData";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "CSUWEC");
            dbConnection = DriverManager.getConnection(url, info);

            if (dbConnection != null) {
                System.out.println("Successfully connected to MySQL database test");
            } } catch (SQLException ex) {
            System.out.println("An error occurred while connecting MySQL databse");
            ex.printStackTrace(); }

        //first query set

        Statement state = dbConnection.createStatement();
        ResultSet rs;
        //query command
        rs = state.executeQuery("SELECT * FROM learnData;");
        while ( rs.next() ) {
            //String id = rs.getString("id");
            String data = rs.getString("message");
            data = data.toLowerCase().replaceAll("[^a-z ]", "");
            String words[] = data.split("\\s+");
            //System.out.println(data);
            String flag = rs.getString("flag");
            if(flag.equals("ham")) {
                totalHamMessages++;
                for(int i = 0; i < words.length; i++) {
                    int j = dictionaryBinSearch(dictionary, 0, dictionary.length-1, words[i]);
                    if(j != -1) {
                        hamWordFreq[j]++;
                        totalHamWords++;
                    }
                }
            }
            else {
                totalSpamMessages++;
                for(int i = 0; i < words.length; i++) {
                    int j = dictionaryBinSearch(dictionary, 0, dictionary.length-1, words[i]);
                    if(j != -1) {
                        spamWordFreq[j]++;
                        totalSpamWords++;
                    }
                }
            }
        }
        int totalSMessages = 0;
        int totalHMessages = 0;
        int aHamCounter = 0;
        int aSpamCounter = 0;
        //query command
        rs = state.executeQuery("SELECT * FROM testData;");
        while ( rs.next() ) {
            String data = rs.getString("message");
            data = data.toLowerCase().replaceAll("[^a-z ]", "");
            String words[] = data.split("\\s+");
            String flag = rs.getString("flag");

            if(flag.equals("ham"))
                totalHMessages++;

            if(flag.equals("spam"))
                totalSMessages++;

            double sLikelihood = .5;
            double hLikelihood = .5;
            String message = rs.getString("message");

            message = message.toLowerCase().replaceAll("[^a-z ]", "");
            String messageWords[] = message.split("\\s+");
            for(int i = 0; i < messageWords.length; i++) {
                int j = dictionaryBinSearch(dictionary, 0, dictionary.length-1, messageWords[i]);
                if(j != -1) {
                    sLikelihood *= (spamWordFreq[j] / totalSpamWords);
                    hLikelihood *= (hamWordFreq[j] / totalHamWords);
                }
            }
            if(sLikelihood > hLikelihood && flag.equals("spam")) {
                aSpamCounter++;
            }
            else if(sLikelihood < hLikelihood && flag.equals("ham")) {
                aHamCounter++;
            }
            else {
            }
        }
        System.out.println("We have " + aHamCounter + "/" + totalHMessages + " correct out of the ham messages");
        System.out.println("We have " + aSpamCounter + "/" + totalSMessages + " correct out of spam messages");

        Scanner scan = new Scanner(System.in);
        double spamLikelihood = totalSpamMessages / (totalHamMessages + totalSpamMessages);
        double hamLikelihood = totalHamMessages / (totalHamMessages + totalSpamMessages);

        System.out.println("Enter a message to classify: ");
        String message = scan.nextLine();

        message = message.toLowerCase().replaceAll("[^a-z ]", "");
        String messageWords[] = message.split("\\s+");
        for(int i = 0; i < messageWords.length; i++) {
            int j = dictionaryBinSearch(dictionary, 0, dictionary.length-1, messageWords[i]);
            if(j != -1) {
                spamLikelihood *= (spamWordFreq[j] / totalSpamWords);
                hamLikelihood *= (hamWordFreq[j] / totalHamWords);
            }
        }
        System.out.println("The spam likelihood is: " + spamLikelihood);
        System.out.println("The ham likelihood is: " + hamLikelihood);
        if(spamLikelihood > hamLikelihood) {
            System.out.println("The message has been classified as spam");
        }
        else if(spamLikelihood < hamLikelihood) {
            System.out.println("The message has been classified as ham");
        }
        else {
            System.out.println("The message cannot be classified as ham or spam");
        }

        spamLikelihood = totalSpamMessages / (totalHamMessages + totalSpamMessages);
        hamLikelihood = totalHamMessages / (totalHamMessages + totalSpamMessages);
        System.out.println("Enter another message to classify: ");
        message = scan.nextLine();

        message = message.toLowerCase().replaceAll("[^a-z ]", "");
        messageWords = message.split("\\s+");
        for(int i = 0; i < messageWords.length; i++) {
            int j = dictionaryBinSearch(dictionary, 0, dictionary.length-1, messageWords[i]);
            if(j != -1) {
                spamLikelihood *= (spamWordFreq[j] / totalSpamWords);
                hamLikelihood *= (hamWordFreq[j] / totalHamWords);
            }
        }
        System.out.println("The spam likelihood is: " + spamLikelihood);
        System.out.println("The ham likelihood is: " + hamLikelihood);
        if(spamLikelihood > hamLikelihood) {
            System.out.println("The message has been classified as spam");
        }
        else if(spamLikelihood < hamLikelihood) {
            System.out.println("The message has been classified as ham");
        }
        else {
            System.out.println("The message cannot be classified as ham or spam");
        }
        scan.close();
        dbConnection.close();
    }
    public static int dictionaryBinSearch(String dic[], int l, int r, String word)
    {
        if(r >= l) {
            int mid = l + (r-l)/2;
            if(dic[mid].compareTo(word)==0) {
                return mid;
            }
            else if(dic[mid].compareTo(word) > 0) {
                return dictionaryBinSearch(dic, l, mid-1, word);
            }
            else {
                return dictionaryBinSearch(dic, mid+1, r, word);
            }
        }
        return -1;
    }
}
