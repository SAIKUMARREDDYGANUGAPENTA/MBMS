package WEAPONS;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;

class outfits
{
    public int COUNT;
    public String BASE_ID;
    public String OUT_FITS;

    public void printArmoury(String out_fi)
    {
        System.out.println("SELETED OUT FIT IS:         "+ out_fi);
    }

    public void printDetails(ResultSet rs)
    {
        System.out.println("OUT_FIT NAME :"+OUT_FITS+"BASE ID :     ");
    }

}

public class ARMOURY_DETAILS extends outfits
{
    static ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/javaproject?allowPublicKeyRetrieval=true&useSSL=false";
    static String username = "root";
    static String password = "Root@123";
    static Scanner input = new Scanner(System.in);

    //METHOD TO PRINT ARMOURY DETAILS

    public static void printDetailsofOF(ResultSet rs) throws Exception {
        System.out.println("");
        System.out.println("     OUT_FITS \t \t \t  COUNT    ");
        System.out.println();

        while(rs.next())
        {
            outfits OF_LIST = new outfits();
            OF_LIST.OUT_FITS = rs.getString(1);
            OF_LIST.COUNT = rs.getInt(2);

            System.out.printf("|%-20s|",OF_LIST.OUT_FITS);
            System.out.print("           ");
            System.out.printf("| %-10d|",OF_LIST.COUNT);
            System.out.print("           ");
            System.out.println();
        }
    }

    //METHOD TO PRINT ARMOURY DETAILS

    public static void printDetailsofOF1(ResultSet rs) throws Exception {
        while(rs.next())
        {
            outfits OF_LIST = new outfits();
            OF_LIST.OUT_FITS = rs.getString(1);
            OF_LIST.COUNT = rs.getInt(2);

            System.out.printf("|%-20s|",OF_LIST.OUT_FITS);
            System.out.print("           ");
            System.out.printf("| %-10d|",OF_LIST.COUNT);
            System.out.print("           ");
            System.out.println();
        }
    }

    //METHOD TO DISPALY ARMOURY DETAILS

    public static void getArmouryDeatils(String b_ID) throws Exception {
        String query = "select * from ARMOURY where BASE_ID = "+"'"+b_ID+"'";
        // query to be run
        Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
        Connection con = DriverManager.getConnection(url, username, password);
        //System.out.println("Connection Established successfully");
        Statement st = con.createStatement();
        rs = st.executeQuery(query); // Execute query
        printDetailsofOF(rs);
    }

    //METHOD TO UPADTE ARMOURY DETAILS

    public static void updateOutfitDetails(String b_ID) throws Exception {
        String query;

        ArrayList<String> actual_base_ids = new ArrayList<>();
        actual_base_ids.add("B1MF50");
        actual_base_ids.add("B2BF70");
        actual_base_ids.add("B3PF90");
        actual_base_ids.add("B4SF60");
        actual_base_ids.add("HEBA55");

        Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
        Connection con = DriverManager.getConnection(url, username, password);
        //System.out.println("Connection Established successfully");
        Statement st = con.createStatement();
        System.out.print("SELECT OUT_FIT TO UPDATE : ");
        String out_fit = input.nextLine();

        query = "Select OUT_FITS from ARMOURY where BASE_ID = '"+b_ID+"'";

        int flag = 0;

        rs = st.executeQuery(query);
        while(rs.next())
        {
            if(out_fit.equalsIgnoreCase(rs.getString(1)))
                flag = 1;
        }

        if(flag == 1)
        {
            System.out.println("|1. TO UPDATE COUNT  ||   2. TO UPDATE BASE ID |");
            String myChoice = input.nextLine();

            switch (myChoice) {
            case "1":
                System.out.print("NEW COUNT:  ");
                int new_count = input.nextInt();


                input.nextLine();
                String query1 = "UPDATE ARMOURY SET COUNT = "+new_count+" WHERE OUT_FITS = '"+out_fit+"'";
                st.executeUpdate(query1);

                break;

            case "2":
                String new_base_id;
                int flag1 = 0;
                String query2;
                System.out.print("ENTER NEW BASE_ID:       ");
                new_base_id = input.next();
                while(true) {
                    for(String i:actual_base_ids) {
                        if(new_base_id.equalsIgnoreCase(i))
                            flag1 = 1;
                    }

                    if(flag1 == 1) {
                        query2 = "UPDATE ARMOURY SET BASE_ID = '"+new_base_id+"' WHERE OUT_FITS = '"+out_fit+"';";
                        st.executeUpdate(query2);
                        break;
                    } else {
                        System.out.print("PLEASE ENTER NEW EXISTING BASE ID:      ");    ;
                        new_base_id = input.next();
                    }
                }
            default:
                break;
            }
        } else
        System.out.println();
            System.out.println("NO OUT FIT FOUND WITH THAT NAME");
            System.out.println();
    }

    //METHOD TO INSERT ARMOURY DETAILS

    public static void insertOutfits(String b_id) throws Exception {
        // query to be run
        // query to be run
        Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
        Connection con = DriverManager.getConnection(url, username, password);
        //System.out.println("Connection Established successfully");
        Statement st = con.createStatement();
        String query="Select * from ARMOURY WHERE BASE_ID ='"+b_id+"'";
        rs=st.executeQuery(query);

        System.out.print("ENTER THE NAME OF THE OUTFIT :  ");
        String out_fit = input.nextLine();

        int count = 0;
        int flag = 0;

        System.out.print("ENTER THE COUNT              :  ");
        count= input.nextInt();
        input.nextLine();

        while(rs.next())
        {
            if(out_fit.equalsIgnoreCase(rs.getString("OUT_FITS"))) {
                flag = 1;
                count = count+rs.getInt(2);
            }
        }

        if(flag == 1)
        {
            String query1 = "UPDATE ARMOURY SET COUNT = "+count+" WHERE OUT_FITS = '"+out_fit+"'";
            st.executeUpdate(query1);
        } else
        {
            String query2 = "INSERT INTO ARMOURY VALUES('"+out_fit+"',"+count+",'"+b_id+"')" ;
            st.executeUpdate(query2);
        }
    }

    //METHOD TO DELETE ARMOURY DETAILS

    public static void deleteOutfits(String b_ID) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
        Connection con = DriverManager.getConnection(url, username, password);
        //System.out.println("Connection Established successfully");
        Statement st = con.createStatement();
        String query="select OUT_FITS from ARMOURY WHERE BASE_ID ='"+b_ID+"'";
        rs=st.executeQuery(query);

        System.out.print("ENTER NAME OF OUT_FIT:      ");;
        String out_fit = input.nextLine();
        int flag = 0;
        while(rs.next())
        {
            if(out_fit.equalsIgnoreCase(rs.getString(1)))
                flag = 1;
        }

        if(flag == 1)
        {
            String query2 = "DELETE FROM ARMOURY WHERE OUT_FITS = '"+out_fit+"'";
            st.executeUpdate(query2);
        } else
        {
            System.out.println();
            System.out.print("NO SUCH OUT FIT IS FOUND WITH NAME:     "+out_fit);
            System.out.println();
        }
    }

    //METHOD TO SEARCH BY NAME / PRIMARY KEY

    public static void search_by_name(String b_ID) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        System.out.println();
        ArrayList<String> arr=new ArrayList<>();
        outfits OF_LIST = new outfits();
        String query1 = "select * from ARMOURY where BASE_ID='"+b_ID+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        Boolean flag=true;
        while(flag==true)
        {
            System.out.println();
            System.out.print("|   1. SEARCH BY FULL NAME   |");
            System.out.print("| 2. SEARCH BY PARTIAL NAME  |");
            System.out.println("|     3. EXIT     |");
            String in=input.nextLine();
            switch(in)
            {
                case "1":System.out.print("ENTER THE FULL OUT FIT NAME TO FETCH DETAILS :   ");
                            OF_LIST.OUT_FITS = input.nextLine();

                            while(rs1.next())
                            {
                                if(OF_LIST.OUT_FITS.equalsIgnoreCase(rs1.getString(1)));
                                count = 1;
                            }
                            if(count == 1)
                            {
                                String query = "select * from ARMOURY where OUT_FITS = '"+OF_LIST.OUT_FITS+"' and BASE_ID='"+b_ID+"'";
                                ResultSet rs=st.executeQuery(query);
                                printDetailsofOF(rs);
                            } else
                            {
                                System.out.println("ERROR: NO OUTFIT IS FOUND IN THE SELECTED BASE");
                            }
                        break;
                case "2":System.out.print("ENTER THE PARTIAL OUTFIT NAME TO FECTH DETAILS :");
                            String in1=input.nextLine();

                            String query2 = "select * from ARMOURY where BASE_ID='"+b_ID+"'";
                            ResultSet rs2=st.executeQuery(query2);

                            while(rs2.next())
                            {
                                if(rs2.getString(1).contains(in1));
                                arr.add(rs2.getString(1));
                                count = 1;
                            }
                            if(count == 1)
                            {
                                System.out.println("");
                                System.out.println("     OUT_FITS \t \t \t  COUNT    ");
                                System.out.println();

                                for(String i:arr)
                                {
                                String query = "select * from ARMOURY where OUT_FITS = '"+i+"' and BASE_ID='"+b_ID+"'";
                                ResultSet rs=st.executeQuery(query);
                                printDetailsofOF1(rs);
                                }
                            } else
                            {
                                System.out.println("ERROR: NO OUTFIT IS FOUND IN THE SELECTED BASE");
                            }
                        break;
                case "3":flag=false;
                break;
                default:
                System.out.println();
                System.out.println("\t SELECT THE CORRECT CHOICE ");
                System.out.println();
                break;
    }
    }
    }

    //METHOD TO SEARCH BY COUNT / INTEGER FIELD 

    public static void searchByCount(String b_ID) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();

        outfits OU_FITS = new outfits();
        OU_FITS.COUNT = 0;
        Boolean flag1 = true;

        while(flag1 == true)
        {
            System.out.print("ENTER THE COUNT TO FETCH DETAILS:       ");
            try {
                OU_FITS.COUNT = input.nextInt();
                input.nextLine();
                flag1 = false;

            } catch(Exception e) {
                System.out.println();
                System.out.println("\t\t\t\t\t\t  x x x ERROR OCCURED DUE TO :  "+e+" x x x");
                System.out.println();
                input.nextLine();
            }

            String query1 = "select * from ARMOURY where BASE_ID = '"+b_ID+"'";
            ResultSet rs1=st.executeQuery(query1);
            int count=0;
            while(rs1.next()) {
                if(OU_FITS.COUNT == rs1.getInt(2));
                count = 1;
            }
            if(count == 1) {
                String query = "select * from ARMOURY where COUNT = '"+OU_FITS.COUNT+"' and base_id='"+b_ID+"'";
                ResultSet rs=st.executeQuery(query);
                printDetailsofOF(rs);
            } else {
                System.out.println();
                System.out.println("ERROR  :  NO OUTFIT IS FOUND WITH THIS COUNT IN SELECTED BASE ");
                System.out.println();
            }
        }
    }
}