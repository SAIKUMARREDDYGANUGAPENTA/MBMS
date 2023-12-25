package WEAPONS;
import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;


class bombs
{
    public int count;
    public String base_id;
    public String bomb_name;
    public String bomb_range;

    public void print_bomb(String bomb)
    {
        System.out.println("Select Bomb : "+bomb);
    }
    public void print_details(Resultset rs)
    {
        System.out.println("Bomb name :"+bomb_name+"BAse_id:");
    }

}


public class EXPLOSIVE_DETAILS extends bombs
{
    static ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/javaproject?allowPublicKeyRetrieval=true&useSSL=false";
    static String username = "root";
    static String password = "Root@123";
    static Scanner input =new Scanner(System.in);

    public static void printDetails1(ResultSet rs) throws Exception {
        while(rs.next())
        {
            bombs BOMB=new bombs();
            BOMB.bomb_name= rs.getString(1);
            BOMB.bomb_range=rs.getString(2);
            BOMB.count=rs.getInt(3);

            System.out.printf("|%-20s|",BOMB.bomb_name);
            System.out.print("           ");
            System.out.printf("|  %-15s|",BOMB.bomb_range);
            System.out.print("           ");
            System.out.printf("|  %-5s|",BOMB.count);
            System.out.print("           ");
            System.out.println();
        }

    }

    public static void getBombDetails(String b_ID) throws Exception {

        String query = "select * from bomb where BASE_ID="+"'"+b_ID+"'";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        rs = st.executeQuery(query);
        System.out.println("");
        System.out.println("   BOMB_NAME \t \t \t AREA_SPAN \t \t    COUNT");
        System.out.println();
        while(rs.next())
        {
            String bomb_name= rs.getString(1);
            String b_range=rs.getString(2);
            int count=rs.getInt(3);

            System.out.printf("|%-15s|",bomb_name);
            System.out.print("           ");
            System.out.printf("|  %-15s|",b_range);
            System.out.print("           ");
            System.out.printf("|  %-5s|",count);
            System.out.print("           ");
            System.out.println();
        }
    }

    public static void insertBomb(String b_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        System.out.print("ENTER THE EXPLOSIVE NAME : ");
        String g_name=input.nextLine();
        System.out.print("ENTER THE AREA SPAN      :");
        String a_span=input.nextLine();
        System.out.print("ENTER THE count          :");
        try
        {
            int count=input.nextInt();
            input.nextLine();
            String query="insert into bomb values('"+g_name+"','"+a_span+"',"+count+",'"+b_id+"')";
            st.executeUpdate(query);
        } catch (Exception e)
        {
            System.out.println("SOME ERROR OCCURED : "+e);
        }

    }

    public static void deleteBomb(String b_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        String query="Select bomb_name from bomb WHERE base_id ='"+b_id+"'";
        rs=st.executeQuery(query);
        bombs BOMB=new bombs();
        System.out.print("ENTER THE BOMB NAME TO DELETE THE DATA : ");
        BOMB.bomb_name=input.nextLine();
        int flag=0;
        while(rs.next())
        {
            if(BOMB.bomb_name.equalsIgnoreCase(rs.getString(1))) {
                flag=1;
            }
        }
        if(flag==1)
        {
            String query2="delete from bomb where BOMB_NAME ='"+BOMB.bomb_name+"' and BASE_ID='"+b_id+"'";
            st.executeUpdate(query2);
        } else {
            System.out.println("NO BOMB FOUND WITH NAME : "+BOMB.bomb_name);
        }
        //String B_name=input.nextLine();

    }

    public static void searchByName(String b_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        System.out.println();
        bombs BOMB=new bombs();
        System.out.print("ENTER THE BOMB NAME TO FETCH DETAILS : ");
        BOMB.bomb_name=input.nextLine();
        String query1="select * from BOMB where BASE_ID='"+b_id+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        while(rs1.next())
        {
            if(BOMB.bomb_name.equalsIgnoreCase(rs1.getString(1)))
                count=1;
        }
        if(count==1)
        {
            System.out.println("");
            System.out.println("    BOMB_NAME \t \t \t   AREA_SPAN \t \t       COUNT");
            System.out.println();
            String query = "select * from BOMB where bomb_name ='"+BOMB.bomb_name+"' and BASE_ID='"+b_id+"'";
            ResultSet rs=st.executeQuery(query);
            printDetails1(rs);
        } else
        {
            System.out.println("ERROR : BOMB IS NOT FOUND IN THE SELECTED BASE ");
        }

    }

    public static void searchBycount(String b_ID) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        bombs BOMB=new bombs();
        BOMB.count=0;
        Boolean flag1=true;
        while(flag1==true)
        {
            System.out.print("ENTER THE count TO FETCH DETAILS : ");
            try {
                BOMB.count=input.nextInt();
                flag1=false;
                input.nextLine();
            } catch(Exception e) {
                System.out.println();
                System.out.println("\t\t\t\t \t \t    x x x ERROR OCCURRED DUE TO : "+e+" x x x");
                System.out.println();
                input.nextLine();
            }
        }

        String query1="select * from BOMB where BASE_ID='"+b_ID+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        while(rs1.next())
        {
            if(BOMB.count==rs1.getInt(3))
                count=1;
        }
        if(count==1)
        {
            System.out.println("");
            System.out.println("    BOMB_NAME \t \t \t   AREA_SPAN \t \t       COUNT");
            System.out.println();
            String query = "select * from BOMB where count='"+BOMB.count+"' and BASE_ID='"+b_ID+"'";
            ResultSet rs=st.executeQuery(query);
            printDetails1(rs);
        } else
        {
            System.out.println("ERROR : THIS count OF BOMBS IS NOT FOUND IN THE SELECTED BASE ");
        }
    }


    public static void searchByRange(String b_ID) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        Boolean flag=true;
        while(flag==true)
        {
            bombs BOMB=new bombs();
            System.out.println();
            System.out.print("|   1.SEARCH BY RANGE(FULL STRING)    |");
            System.out.print("|  2.SERACH BY RANGE(PARTIAL STRING)  |");
            System.out.println("|  3.EXIT  |");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE : ");
            String input1=input.nextLine();
            switch(input1) {
            case "1":
                System.out.print("ENTER FULL RANGE : ");
                BOMB.bomb_range=input.nextLine();
                String query = "select * from BOMB where BASE_ID='"+b_ID+"'";
                ResultSet rs = st.executeQuery(query);
                int count=0;
                while(rs.next()) {
                    if(BOMB.bomb_range.equalsIgnoreCase(rs.getString(1))) {
                        count=1;
                    }
                }
                if(count==1) {
                    System.out.println("");
                    System.out.println("    BOMB_NAME \t \t \t   AREA_SPAN \t \t       COUNT");
                    System.out.println();
                    String query1="select * from bomb where bomb_range='"+BOMB.bomb_range+"' and base_id='"+b_ID+"'";
                    ResultSet rs1=st.executeQuery(query1);
                    printDetails1(rs1);
                    System.out.println();
                    System.out.println("\t \t \t \t \t \t         \t --DETAILS FETCHED SUCCESSFULLY--");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("\t \t \t \t  ERROR : BOMB range"+BOMB.bomb_range+"IS NOT FOUND IN HTE SELECTED BASE ");
                    System.out.println();
                }
                break;

            case "2":
                HashSet<String> arr=new HashSet<>();
                System.out.print("ENTER PARTIAL RANGE : ");
                BOMB.bomb_range=input.nextLine();
                String query2 = "select * from bomb where base_id='"+b_ID+"'";
                ResultSet rs2 = st.executeQuery(query2);
                int count1=0;
                int n=0;
                while(rs2.next()) {
                    if(rs2.getString(2).contains(BOMB.bomb_range)) {
                        arr.add(rs2.getString(2));
                        n++;
                        count1=1;
                    }
                }
                if(count1==1) {
                    System.out.println("");
                    System.out.println("    BOMB_NAME \t \t \t   AREA_SPAN \t \t       COUNT");
                    System.out.println();
                    for(String i : arr) {
                        String query1="select * from bomb where bomb_range='"+i+"' and base_id='"+b_ID+"'";
                        ResultSet rs1=st.executeQuery(query1);
                        printDetails1(rs1);
                    }

                    System.out.println();
                    System.out.println("\t \t \t \t \t \t \t         --DETAILS FETCHED SUCCESSFULLY--");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("\t \t \t \t \t \t \t      ERROR : BOMB TYPE IS NOT FOUND IN THE SELECTED BASE");
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



    public static void updateBomb(String b_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();

        int count=0;
        String query1="select * from bomb";

        while(count<=0)
        {
            ResultSet rs1 = st.executeQuery(query1);
            System.out.print("ENTER THE EXPLOSIVE NAME TO UPDATE : ");
            String explosive=input.nextLine();

            while(rs1.next()) {
                if(explosive.equalsIgnoreCase(rs1.getString("BOMB_NAME"))) {
                    count=1;
                }
            }

            if(count==1) {
                Boolean flag=true;
                while(flag==true) {
                    System.out.println("                                |--SELECT YOUR CHOICE--|");
                    System.out.print("|   1.UPDATE COUNT     |");
                    System.out.print("|   2.UPDATE BASE ID   |");
                    System.out.println("|   3.EXIT   |");
                    String  n=input.nextLine();
                    switch(n) {
                    case "1":
                        int x;
                        System.out.print("ENTER THE NEW count :");
                        x=input.nextInt();
                        input.nextLine();
                        String query2="update bomb set count="+x+" where BOMB_NAME='"+explosive+"' and BASE_ID='"+b_id+"'";
                        st.executeUpdate(query2);
                        break;
                    case "2":
                        System.out.print("ENTER THE NEW BASE ID :");
                        String new_id=input.nextLine();
                        String query3="update bomb set BASE_ID='"+n+"' where BOMB_NAME='"+explosive+"' and BASE_ID='"+b_id+"'";
                        st.executeUpdate(query3);
                        break;
                    case "3":
                        flag=false;
                        break;
                    default :
                        System.out.println("ENTER THE CORRECT CHOICE : ");
                        break;

                    }
                }
            } else {
                System.out.println("ENTER THE CORRECT EXPLOSIVE NAME : ");
            }
        }
    }

}