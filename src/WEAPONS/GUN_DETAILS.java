package WEAPONS;
import java.security.spec.ECFieldF2m;
import java.sql.*;
import java.util.*;

import com.mysql.cj.protocol.Resultset;

abstract class methods // ABSTRACT CLASS EXTENDED BY GUNS CLASS
{
    abstract void printGun(String gun);
}

class guns extends methods
{
    public int COUNT;
    public String BASE_ID;
    public String GUN_NAME;
    public String GUN_TYPE;
    public String GUN_RANGE;

    public void printGun(String gun) // ABSTRACT METHOD
    {
        System.out.println("SELECTED GUN : "+gun);
    }

    public void printDetails(Resultset rs)
    {
        System.out.println("GUN NAME :"+GUN_NAME+"BASE ID :");
    }
}

public class GUN_DETAILS extends guns
{
    static ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/JAVAPROJECT?allowPublicKeyRetrieval=true&useSSL=false"; // table details
    static String username = "root";
    static String password = "Root@123";
    static Scanner input=new Scanner(System.in);

    public void printDetails(ResultSet rs) throws Exception { //OVER RIDDEN METHOD
        System.out.println("");
        System.out.println("     GUN_NAME \t\t\t      GUN_TYPE \t\t\t COUNT \t\t    GUN_RANGE");
        System.out.println();
        while(rs.next())
        {
            guns GUN=new guns();
            GUN.GUN_NAME= rs.getString(1);
            GUN.GUN_TYPE=rs.getString(2);
            GUN.COUNT=rs.getInt(3);
            GUN.GUN_RANGE=rs.getString(4);


            System.out.printf("|%-20s|",GUN.GUN_NAME);
            System.out.print("           ");
            System.out.printf("|  %-15s|",GUN.GUN_TYPE);
            System.out.print("           ");
            System.out.printf("|  %-5s|",GUN.COUNT);
            System.out.print("           ");
            System.out.printf("|  %-5s|",GUN.GUN_RANGE);
            System.out.print("           ");
            System.out.println();
        }

    }

    public void printDetails1(ResultSet rs) throws Exception {
        while(rs.next())
        {
            guns GUN=new guns();
            GUN.GUN_NAME= rs.getString(1);
            GUN.GUN_TYPE=rs.getString(2);
            GUN.COUNT=rs.getInt(3);
            GUN.GUN_RANGE=rs.getString(4);


            System.out.printf("|%-20s|",GUN.GUN_NAME);
            System.out.print("           ");
            System.out.printf("|  %-15s|",GUN.GUN_TYPE);
            System.out.print("           ");
            System.out.printf("|  %-5s|",GUN.COUNT);
            System.out.print("           ");
            System.out.printf("|  %-5s|",GUN.GUN_RANGE);
            System.out.print("           ");
            System.out.println();
        }

    }


    public void getGunDetails(String b_ID) throws Exception {

        String query = "select * from guns where BASE_ID="+"'"+b_ID+"'";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        rs = st.executeQuery(query);
        printDetails(rs);

    }

    public void updateGunDetails(String b_ID) throws Exception {
        String query ;

        ArrayList<String> actual_base_ids=new ArrayList<>();
        actual_base_ids.add("B1MF50");
        actual_base_ids.add("B2BF70");
        actual_base_ids.add("B3PF90");
        actual_base_ids.add("B4SF60");
        actual_base_ids.add("HEBA55");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        guns GUN=new guns();
        System.out.print("SELECT GUN_NAME TO UPDATE : ");
        GUN.GUN_NAME=input.nextLine();

        query="Select GUN_NAME from GUNS WHERE BASE_ID ='"+b_ID+"'";

        int flag=0;

        rs=st.executeQuery(query);
        while(rs.next())
        {
            if(GUN.GUN_NAME.equalsIgnoreCase(rs.getString(1))) {
                flag=1;
            }
        }
        if(flag==1)
        {
            String myChoice;
            Boolean flagx=true;
            while(flagx==true) {
                System.out.println("| 1.TO UPDATE COUNT  ||  2.TO UPDATE BASE ID  ||  3.EXIT |");
                myChoice=input.nextLine();

                switch (myChoice) {
                case "1":
                    GUN.COUNT=0;
                    Boolean flagy=true;
                    while(flagy==true) {
                        System.out.print("NEW COUNT : ");
                        try {
                            GUN.COUNT=input.nextInt();
                            flagy=false;
                            input.nextLine();
                        } catch(Exception e) {
                            System.out.println();
                            System.out.println("\t\t\t\t \t \t    x x x ERROR OCCURRED DUE TO : "+e+" x x x");
                            System.out.println();
                            input.nextLine();
                        }
                    }

                    String query1="UPDATE GUNS SET COUNT = "+GUN.COUNT+" WHERE GUN_NAME = "+"'"+GUN.GUN_NAME+"'";
                    st.executeUpdate(query1);

                    break;
                case "2":
                    int flag1=0;
                    String query2;
                    System.out.print("ENTER EXISTING BASE_ID : ");
                    GUN.BASE_ID=input.next();
                    while(true) {

                        for(String i:actual_base_ids) {
                            if(GUN.BASE_ID.equalsIgnoreCase(i)) {
                                flag1=1;
                            }
                        }

                        if(flag1==1) {
                            query2="UPDATE GUNS SET BASE_ID = "+GUN.BASE_ID+" WHERE GUN_NAME = "+"'"+GUN.GUN_NAME+"'";
                            st.executeUpdate(query2);
                            break;
                        } else {
                            System.out.print("PLEASE ENTER EXISTING BASE ID : ");
                            GUN.BASE_ID=input.next();
                        }

                    }
                    break;
                case "3":
                    flagx=false;
                    break;
                default:
                    System.out.println();
                    System.out.println("SELECT THE CORRECT CHOICE ");
                    System.out.println();
                    break;
                }
            }

        } else {
            System.out.println("NO GUN FOUND WITH NAME : "+GUN.GUN_NAME);
        }

    }



    public void insertGunDetails(String b_ID) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();
        String query="Select GUN_NAME from GUNS WHERE BASE_ID ='"+b_ID+"'";
        rs=st.executeQuery(query);
        guns GUN=new guns();
        System.out.print("ENTER THE NAME OF THE GUN : ");
        GUN.GUN_NAME=input.nextLine();

        GUN.COUNT=0;
        int flag=0;
        while(rs.next())
        {
            if(GUN.GUN_NAME.equalsIgnoreCase(rs.getString("GUN_NAME"))) {
                flag=1;
                GUN.COUNT=rs.getInt("COUNT");
                GUN.COUNT++;
            }
        }

        if(flag==1)
        {
            String query1="UPDATE GUNS SET COUNT = "+GUN.COUNT+" WHERE GUN_NAME = "+"'"+GUN.GUN_NAME+"'";
            st.executeUpdate(query1);
        } else {
            System.out.print("ENTER THE GUN TYPE        : ");
            GUN.GUN_TYPE=input.nextLine();
            System.out.print("ENTER THE GUN RANGE       : ");
            GUN.GUN_RANGE=input.nextLine();
            GUN.COUNT=0;
            Boolean flagz=true;
            while(flagz==true)
            {
                System.out.print("ENTER THE COUNT           : ");
                try {
                    GUN.COUNT=input.nextInt();
                    flagz=false;
                    input.nextLine();
                } catch(Exception e) {
                    System.out.println();
                    System.out.println("\t\t\t\t \t \t    x x x ERROR OCCURRED DUE TO : "+e+" x x x");
                    System.out.println();
                    input.nextLine();
                }
            }
            String query2="INSERT INTO GUNS VALUES('"+GUN.GUN_NAME+"','"+GUN.GUN_TYPE+"',"+GUN.COUNT+",'"+GUN.GUN_RANGE+"','"+b_ID+"')";
            st.executeUpdate(query2);
        }

    }

    public void deleteGunDetails(String b_ID) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();
        String query="Select GUN_NAME from GUNS WHERE BASE_ID ='"+b_ID+"'";
        rs=st.executeQuery(query);
        guns GUN=new guns();
        System.out.print("ENTER THE NAME OF THE GUN TO DELETE : ");
        GUN.GUN_NAME=input.nextLine();
        int flag=0;
        while(rs.next())
        {
            if(GUN.GUN_NAME.equalsIgnoreCase(rs.getString(1))) {
                flag=1;
            }
        }
        if(flag==1)
        {
            String query2="DELETE FROM GUNS WHERE GUN_NAME = '"+GUN.GUN_NAME+"'";
            st.executeUpdate(query2);
        } else {
            System.out.println("NO GUN FOUND WITH NAME : "+GUN.GUN_NAME);
        }

    }




    public void searchByName(String b_ID) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        System.out.println();
        guns GUN=new guns();
        System.out.print("ENTER THE GUN NAME TO FETCH DETAILS : ");
        GUN.GUN_NAME=input.nextLine();
        String query1="select * from GUNS where BASE_ID='"+b_ID+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        while(rs1.next())
        {
            if(GUN.GUN_NAME.equalsIgnoreCase(rs1.getString(1)))
                count=1;
        }
        if(count==1)
        {
            String query = "select * from GUNS where GUN_NAME="+"'"+GUN.GUN_NAME+"' and BASE_ID='"+b_ID+"'";
            ResultSet rs=st.executeQuery(query);
            printDetails(rs);
        } else
        {
            System.out.println("ERROR : GUN IS NOT FOUND IN THE SELECTED BASE ");
        }

    }


    public void searchByCount(String b_ID) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        guns GUN=new guns();
        GUN.COUNT=0;
        Boolean flag1=true;
        while(flag1==true)
        {
            System.out.print("ENTER THE COUNT TO FETCH DETAILS : ");
            try {
                GUN.COUNT=input.nextInt();
                flag1=false;
                input.nextLine();
            } catch(Exception e) {
                System.out.println();
                System.out.println("\t\t\t\t \t \t    x x x ERROR OCCURRED DUE TO : "+e+" x x x");
                System.out.println();
                input.nextLine();
            }
        }

        String query1="select * from GUNS where BASE_ID='"+b_ID+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        while(rs1.next())
        {
            if(GUN.COUNT==rs1.getInt(3))
                count=1;
        }
        if(count==1)
        {
            String query = "select * from GUNS where COUNT="+"'"+GUN.COUNT+"' and BASE_ID='"+b_ID+"'";
            ResultSet rs=st.executeQuery(query);
            printDetails(rs);
        } else
        {
            System.out.println("ERROR : THIS COUNT OF GUNS IS NOT FOUND IN THE SELECTED BASE ");
        }
    }

    public void searchByType(String b_ID) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        Boolean flag=true;
        while(flag==true)
        {
            guns GUN=new guns();
            System.out.println();
            System.out.print("|  1.SEARCH BY FULL NAME     |");
            System.out.print("|  2.SERACH BY PARTIAL NAME  |");
            System.out.println("|  3.EXIT  |");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE : ");
            String input1=input.nextLine();
            switch(input1) {
            case "1":
                System.out.print("ENTER FULL NAME : ");
                GUN.GUN_TYPE=input.nextLine();
                String query = "select * from GUNS where BASE_ID="+"'"+b_ID+"'";
                ResultSet rs = st.executeQuery(query);
                int count=0;
                while(rs.next()) {
                    if(GUN.GUN_TYPE.equalsIgnoreCase(rs.getString(2))) {
                        count=1;
                    }
                }
                if(count==1) {
                    String query1="select * from guns where gun_type='"+GUN.GUN_TYPE+"' and base_id='"+b_ID+"'";
                    ResultSet rs1=st.executeQuery(query1);
                    printDetails(rs1);
                    System.out.println();
                    System.out.println("\t \t \t \t \t \t         \t --DETAILS FETCHED SUCCESSFULLY--");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("\t \t \t \t  ERROR : GUN TYPE"+GUN.GUN_TYPE+"IS NOT FOUND IN HTE SELECTED BASE ");
                    System.out.println();
                }
                break;

            case "2":
                HashSet<String> arr=new HashSet<>();
                System.out.print("ENTER PARTIAL NAME : ");
                GUN.GUN_TYPE=input.nextLine();
                String query2 = "select * from guns where base_id="+"'"+b_ID+"'";
                ResultSet rs2 = st.executeQuery(query2);
                int count1=0;
                int n=0;
                while(rs2.next()) {
                    if(rs2.getString(2).contains(GUN.GUN_TYPE)) {
                        arr.add(rs2.getString(2));
                        n++;
                        count1=1;
                    }
                }
                if(count1==1) {

                    System.out.println("");
                    System.out.println("     GUN_NAME \t\t\t      GUN_TYPE \t\t\t COUNT \t\t    GUN_RANGE");
                    System.out.println();

                    for(String i : arr) {
                        String query1="select * from guns where gun_type='"+i+"' and base_id='"+b_ID+"'";
                        ResultSet rs1=st.executeQuery(query1);
                        printDetails1(rs1);
                    }

                    System.out.println();
                    System.out.println("\t \t \t \t \t \t \t         --DETAILS FETCHED SUCCESSFULLY--");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("\t \t \t \t \t \t \t      ERROR : GUN TYPE IS NOT FOUND IN THE SELECTED BASE");
                    System.out.println();
                }
                break;

            case "3":
                flag=false;
                break;

            default:
                System.out.println();
                System.out.println("SELECT THE CORRECT CHOICE");
                System.out.println();
                break;
            }

        }
    }


}