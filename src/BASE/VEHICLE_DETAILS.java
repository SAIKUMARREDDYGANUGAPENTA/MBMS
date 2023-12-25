package BASE;
import java.util.*;
import java.sql.*;
import java.util.Scanner;

import javax.xml.transform.Source;

import com.mysql.cj.protocol.Resultset;

abstract class method_vehicle // ABSTRACT CLASS EXTENDED BY VEHICLE CLASS
{
    abstract void printGun(String vehicle);
}

class vehicle extends method_vehicle
{
    public String vehicle_name;
    public String vehicle_type;
    public int count;
    public String VEHICLE_ID;
    public String BASE_ID;
    public void printGun(String vehicle) // ABSTRACT METHOD
    {
        System.out.println("SELECTED VEHICLE : "+vehicle);
    }

    public void printVehicleDetails(Resultset rs)
    {
        System.out.println("VEHICLE NAME :"+vehicle_name+"BASE ID :");
    }
}


public class VEHICLE_DETAILS extends vehicle
{
    static ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/javaproject?allowPublicKeyRetrieval=true&useSSL=false";
    static String username = "root";
    static String password = "Root@123";
    static Scanner input =new Scanner(System.in);

    public static void printVehicleDetails1(ResultSet rs) throws Exception {

        while(rs.next())
        {
            vehicle VEHICLE= new vehicle();
            VEHICLE.vehicle_name = rs.getString(1);
            VEHICLE.vehicle_type =rs.getString(2);
            VEHICLE.count=rs.getInt(3);
            VEHICLE.VEHICLE_ID=rs.getString(4);
            VEHICLE.BASE_ID=rs.getString(5);
            System.out.printf("|%-20s|",VEHICLE.vehicle_name );
            System.out.print("           ");
            System.out.printf("|  %-10s|",VEHICLE.vehicle_type);
            System.out.print("           ");
            System.out.printf("|  %-5s|",VEHICLE.count);
            System.out.print("           ");
            System.out.printf("|  %-10s|",VEHICLE.VEHICLE_ID);
            System.out.print("           ");
            System.out.println();
        }
    }

    public static void printVehicleDetails(ResultSet rs) throws Exception {

        System.out.println("");
        System.out.println("     VEHICLE_NAME \t\t  VEHICLE_TYPE \t\t    COUNT\t\t  VEHICLE_ID");
        while(rs.next())
        {
            vehicle VEHICLE= new vehicle();
            VEHICLE.vehicle_name = rs.getString(1);
            VEHICLE.vehicle_type =rs.getString(2);
            VEHICLE.count=rs.getInt(3);
            VEHICLE.VEHICLE_ID=rs.getString(4);
            VEHICLE.BASE_ID=rs.getString(5);
            System.out.printf("|%-20s|",VEHICLE.vehicle_name );
            System.out.print("           ");
            System.out.printf("|  %-10s|",VEHICLE.vehicle_type);
            System.out.print("           ");
            System.out.printf("|  %-5s|",VEHICLE.count);
            System.out.print("           ");
            System.out.printf("|  %-10s|",VEHICLE.VEHICLE_ID);
            System.out.print("           ");
            System.out.println();
        }
    }
    
    public static void getdetails(String string) throws Exception {
        ResultSet rs;
        String query = "select * from Vehicles where BASE_ID="+"'"+string+"'";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();
        rs = st.executeQuery(query);
        printVehicleDetails(rs);
    }

    public static void insertVehicle(String b_id) throws Exception {
        vehicle VEHICLE=new vehicle();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        String query="Select VEHICLE_NAME from VEHICLES WHERE BASE_ID ='"+b_id+"'";
        rs=st.executeQuery(query);

        System.out.print("ENTER THE VEHICLE NAME : ");
        VEHICLE.vehicle_name=input.nextLine();
        VEHICLE.count=0;
        int flag5=0;
        while(rs.next())
        {
            if(VEHICLE.vehicle_name.equalsIgnoreCase(rs.getString("vehicle_name"))) {
                flag5=1;
                VEHICLE.count=rs.getInt("COUNT");
                VEHICLE.count++;
            }
        }
        if(flag5==1)
        {
            String query1="UPDATE VEHICLES SET COUNT = "+VEHICLE.count+" WHERE VEHICLE_NAME = "+"'"+VEHICLE.vehicle_name+"'";
            st.executeUpdate(query1);
        } else{
            System.out.print("ENTER THE VEHICLE TYPE      :");
            VEHICLE.vehicle_type=input.nextLine();
            System.out.print("ENTER THE VEHICLE ID          :");
            VEHICLE.VEHICLE_ID=input.nextLine();
            // input.nextLine();
            VEHICLE.count=0;
            Boolean flag6=true;
            while(flag6==true)
            {
                System.out.print("ENTER THE COUNT           : ");
                try {
                    VEHICLE.count=input.nextInt();
                    flag6=false;
                    input.nextLine();
                } catch(Exception e) {
                    System.out.println();
                    System.out.println("\t\t\t  x x x ERROR OCCURRED DUE TO : "+e+" x x x");
                    System.out.println();
                    input.nextLine();
                }
            }
            String query6="insert into Vehicles values('"+VEHICLE.vehicle_name+"','"+VEHICLE.vehicle_type+"',"+VEHICLE.count+",'"+VEHICLE.VEHICLE_ID+"','"+b_id+"')";
            st.executeUpdate(query6);
        }
    }

    public static void removeVehicle(String b_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        String query10="Select * from VEHICLES WHERE BASE_ID ='"+b_id+"'";
        // rs=st.executeQuery(query10);
        Boolean flag=true;
        while(flag==true)
        {
            rs=st.executeQuery(query10);
            System.out.println();
            System.out.print("| 1. DELETE BY  NAME  |");
            System.out.print("| 2.DELETE BY  VEHICLE ID  |");
            System.out.println("| 3.EXIT  |");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE : ");
            String input1=input.nextLine();
            vehicle VEHICLE=new vehicle();
            switch(input1) {
            case "1":
                System.out.println("ENTER THE VEHICLE NAME TO REMOVE FROM DATA : ");

                VEHICLE.vehicle_name=input.nextLine();
                int flaga=0;
                while(rs.next()) {
                    if(VEHICLE.vehicle_name.equalsIgnoreCase(rs.getString(1))) {
                        flaga=1;
                    }
                }
                if(flaga==1) {
                    String query="delete from Vehicles where Vehicle_name ='"+VEHICLE.vehicle_name+"' and BASE_ID= '"+b_id+"'";
                    st.executeUpdate(query);
                } else {
                    System.out.println("NO VEHICLE FOUND WITH NAME : "+VEHICLE.vehicle_name);
                }
                break;
            case "2":
                System.out.println("ENTER THE VEHICLE ID TO REMOVE FROM DATA : ");
                //vehicle VEHICLE=new vehicle();
                VEHICLE.VEHICLE_ID=input.nextLine();
                int flagb=0;
                while(rs.next()) {
                    if(VEHICLE.VEHICLE_ID.equalsIgnoreCase(rs.getString(4))) {
                        flagb=1;
                    }
                }
                if(flagb==1) {
                    String query31="delete from Vehicles where Vehicle_id ='"+VEHICLE.VEHICLE_ID+"' and BASE_ID= '"+b_id+"'";
                    st.executeUpdate(query31);
                } else {
                    System.out.println("NO VEHICLE FOUND WITH ID : "+VEHICLE.VEHICLE_ID);
                }

                break;
            case "3":
                flag=false;
                break;

            default:
                System.out.println();
                System.out.println();
                System.out.println("SELECT THE CORRECT CHOICE");
                System.out.println();
                break;
            }


        }
    }

    public static void updateVehicle(String b_id) throws Exception {
        String query ;

        ArrayList<String> BASE_IDS=new ArrayList<>();
        BASE_IDS.add("B1MF50");
        BASE_IDS.add("B2BF70");
        BASE_IDS.add("B3PF90");
        BASE_IDS.add("B4SF60");
        BASE_IDS.add("HEBA55");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        vehicle VEHICLE=new vehicle();
        System.out.print("SELECT VEHICLE_NAME TO UPDATE : ");
        VEHICLE.vehicle_name=input.nextLine();
        query="Select VEHICLE_NAME from VEHICLES WHERE BASE_ID ='"+b_id+"'";
        int count =0;
        rs=st.executeQuery(query);

        while(rs.next())
        {
            if(VEHICLE.vehicle_name.equalsIgnoreCase(rs.getString(1))) {
                count=1;
            }
        }


        if(count==1)
        {

            Boolean flag=true;
            while(flag==true) {
                System.out.println(" \t |--SELECT YOUR CHOICE--|");
                System.out.print("|   1.UPDATE COUNT   |");
                System.out.print("|   2.UPDATE BASE ID   |");
                System.out.println("|   3.EXIT   |");
                String n=input.nextLine();
                switch(n) {
                case "1":
                    VEHICLE.count=0;
                    Boolean flag1=true;
                    while(flag1==true) {
                        System.out.print("ENTER NEW COUNT : ");
                        try {
                            VEHICLE.count=input.nextInt();
                            flag1=false;
                            input.nextLine();
                        } catch(Exception e) {
                            System.out.println();
                            System.out.println("\t\t    x x x ERROR OCCURRED DUE TO : "+e+" x x x");
                            System.out.println();
                            input.nextLine();
                        }
                    }
                    String query2="update Vehicles set count="+VEHICLE.count+" where Vehicle_name='"+VEHICLE.vehicle_name+"' and BASE_ID='"+b_id+"'";
                    st.executeUpdate(query2);
                    break;
                case "2":
                    int flag2=0;
                    String query3;
                    System.out.print("ENTER THE BASE_ID : ");
                    VEHICLE.BASE_ID=input.next();
                    while(true) {

                        for(String i:BASE_IDS) {
                            if(VEHICLE.BASE_ID.equalsIgnoreCase(i)) {
                                flag2=1;
                            }
                        }

                        if(flag2==1) {
                            query3="update Vehicles set BASE_ID='"+VEHICLE.BASE_ID+"' where Vehicle_name='"+VEHICLE.vehicle_name+"'";
                            st.executeUpdate(query3);
                            break;
                        } else {
                            System.out.print("PLEASE ENTER EXISTING BASE ID : ");
                            VEHICLE.BASE_ID=input.next();
                        }
                    }
                    break;
                case "3":
                    flag=false;
                    break;
                default :
                    System.out.println();
                    System.out.println();
                    System.out.println("ENTER THE CORRECT CHOICE ");
                    System.out.println();
                    break;

                }
            }
        } else
        {
            System.out.println();
            System.out.println("NO VEHICLE FOUND WITH NAME  " +VEHICLE.vehicle_name);
            System.out.println();
        }


    }

    public static void searchByCount(String b_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        vehicle VEHICLE=new vehicle();
        VEHICLE.count=0;
        Boolean flag1=true;
        while(flag1==true)
        {
            System.out.print("ENTER THE COUNT TO FETCH DETAILS : ");
            try {

                VEHICLE.count=input.nextInt();
                flag1=false;
                input.nextLine();
            } catch(Exception e) {
                System.out.println();
                System.out.println("\t\t\t\t \t \t    x x x ERROR OCCURRED DUE TO : "+e+" x x x");
                System.out.println();
                input.nextLine();
            }
        }


        String query1="select * from vehicles where base_id='"+b_id+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        while(rs1.next())
        {
            if(VEHICLE.count==rs1.getInt(3))
                count=1;
        }
        if(count==1)
        {
            String query = "select * from vehicles where count >= "+"'"+VEHICLE.count+"' and base_id='"+b_id+"'";
            ResultSet rs=st.executeQuery(query);
            printVehicleDetails(rs);
        } else
        {
            System.out.println();
            System.out.println("ERROR : SORRY THIS MUCH OF VEHICLES NOT PRESENT  IN BASRE ID "+b_id);
            System.out.println();
        }
    }


    public static void searchById(String b_id,String id) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        String vehicle_id=id;
        String query1="select * from vehicles  where base_id='"+b_id+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        while(rs1.next())
        {
            if(vehicle_id.equals(rs1.getString(4)))
                count=1;
        }
        if(count==1)
        {
            String query = "select * from vehicles where vehicle_id="+"'"+vehicle_id+"' and base_id='"+b_id+"'";
            ResultSet rs=st.executeQuery(query);
            printVehicleDetails(rs);
        } else
        {
            System.out.println();
            System.out.println("ERROR :  SORRY VEHICLE IS NOT FOUND ");
            System.out.println();
        }

    }

    public static void searchByName(String b_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        Boolean flag=true;
        while(flag==true)
        {
            vehicle VEHICLE=new vehicle();
            System.out.println();
            System.out.print("| 1. SEARCH BY FULL NAME  |");
            System.out.print("| 2.SERACH BY PARTIAL NAME  |");
            System.out.println("| 3.EXIT  |");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE : ");
            String input1=input.nextLine();
            switch(input1) {
            case "1":
                System.out.print("ENTER FULL NAME : ");
                VEHICLE.vehicle_name=input.nextLine();
                String query = "select * from vehicles where base_id="+"'"+b_id+"'"+"ORDER BY count DESC";
                ResultSet rs = st.executeQuery(query);
                int count=0;
                while(rs.next()) {
                    if(VEHICLE.vehicle_name.equalsIgnoreCase(rs.getString(1))) {

                        count=1;
                    }
                }
                if(count==1) {
                    String query1="select * from vehicles where vehicle_name='"+VEHICLE.vehicle_name+"' and base_id='"+b_id+"'";
                    ResultSet rs1=st.executeQuery(query1);
                    printVehicleDetails(rs1);
                    System.out.println();
                    System.out.println("\t \t \t     \t --DETAILS FETCHED SUCCESSFULLY--");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("\t \t \t  ERROR : SORRY VEHICLE  NAME IS NOT FOUND ");
                    System.out.println();
                }
                break;

            case "2":
                HashSet<String> veh_arr=new HashSet<>();
                System.out.print("ENTER PARTIAL NAME : ");
                String partial_name=input.nextLine();
                String query2 = "select * from vehicLes where base_id="+"'"+b_id+"'"+"ORDER BY count  DESC";
                ResultSet rs2 = st.executeQuery(query2);
                int count1=0;
                int m=0;
                while(rs2.next()) {
                    if(rs2.getString(1).contains(partial_name)) {
                        veh_arr.add(rs2.getString(1));
                        m++;
                        count1=1;
                    }
                }
                if(count1==1) {
                    System.out.println("");
                    System.out.println("     VEHICLE_NAME \t\t  VEHICLE_TYPE \t\t    COUNT\t\t  VEHICLE_ID");
                    System.out.println();

                    for(String i : veh_arr) {
                        String query1="select * from vehicles where vehicle_name='"+i+"' and base_id='"+b_id+"'";
                        ResultSet rs1=st.executeQuery(query1);
                        printVehicleDetails1(rs1);
                    }

                    System.out.println();
                    System.out.println("\t \t \t \t \t      --DETAILS FETCHED SUCCESSFULLY--");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("\t \t \t \t \t     ERROR : SORRY VEHICLE PARTIAL NAME IS NOT FOUND ");
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

    public static void searchById(String b_id) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        System.out.println();
        vehicle VEHICLE=new vehicle();
        System.out.print("ENTER THE VEHICLE  ID TO FETCH DETAILS : ");
        String vehicle_id=input.nextLine();
        String query1="select * from vehicles where base_id='"+b_id+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        while(rs1.next())
        {
            if(vehicle_id.equals(rs1.getString(4)))
                count=1;
        }
        if(count==1)
        {
            String query = "select * from vehicles where vehicle_id="+"'"+vehicle_id+"' and base_id='"+b_id+"'";
            ResultSet rs=st.executeQuery(query);
            printVehicleDetails(rs);
        } else
        {
            System.out.println();
            System.out.println("ERROR : SORRY VEHICLE ID IS NOT FOUND ");
            System.out.println();
        }

    }

}