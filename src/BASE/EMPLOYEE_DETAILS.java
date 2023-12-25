package BASE;
import java.sql.*;
import java.util.*;

public class EMPLOYEE_DETAILS
{

    static Scanner input=new Scanner(System.in);
    static String url = "jdbc:mysql://localhost:3306/javaproject?allowPublicKeyRetrieval=true&useSSL=false";
    static String username = "root";
    static String password = "Root@123";

    public static void printDetails(ResultSet rs) throws Exception {
        System.out.println("");
        System.out.println("\t NAME \t \t \t   AGE \t \t \t CADER \t \t \t  S_ID \t \t    GENDER \t        B_N \t           B_ID");
        System.out.println();
        while(rs.next())
        {
            String name= rs.getString(1);
            int age=rs.getInt(2);
            String cader=rs.getString(3);
            String S_ID=rs.getString(4);
            String Gender=rs.getString(5);
            String BUN=rs.getString(6);
            String B_ID=rs.getString(7);

            System.out.printf("|%-20s|",name);
            System.out.print("           ");
            System.out.printf("| %-4s|",age);
            System.out.print("           ");
            System.out.printf("| %-15s|",cader);
            System.out.print("           ");
            System.out.printf("| %-6s|",S_ID);
            System.out.print("           ");
            System.out.printf("| %-4s|",Gender);
            System.out.print("           ");
            System.out.printf("| %-4s|",BUN);
            System.out.print("           ");
            System.out.printf("| %-6s|",B_ID);
            System.out.println();
        }
    }

    public static void printDetails1(ResultSet rs) throws Exception {
        while(rs.next())
        {
            String name= rs.getString(1);
            int age=rs.getInt(2);
            String cader=rs.getString(3);
            String S_ID=rs.getString(4);
            String Gender=rs.getString(5);
            String BUN=rs.getString(6);
            String B_ID=rs.getString(7);

            System.out.printf("|%-20s|",name);
            System.out.print("           ");
            System.out.printf("| %-4s|",age);
            System.out.print("           ");
            System.out.printf("| %-15s|",cader);
            System.out.print("           ");
            System.out.printf("| %-6s|",S_ID);
            System.out.print("           ");
            System.out.printf("| %-4s|",Gender);
            System.out.print("           ");
            System.out.printf("| %-4s|",BUN);
            System.out.print("           ");
            System.out.printf("| %-6s|",B_ID);
            System.out.println();
        }
    }



    public static void getDetails(String b_name) throws Exception {
        ResultSet rs;
        String query = "select * from employee_details where BATTLE_UNIT_NAME="+"'"+b_name+"'"+"ORDER BY AGE DESC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();
        rs = st.executeQuery(query);
        printDetails(rs);
    }

    public static void updateEmployee(String b_name) throws Exception {

        ResultSet rs;
        String query ;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();
        String query1="SELECT SOILDER_ID FROM employee_details";
        int count=0;

        while(count<=1)
        {

            rs = st.executeQuery(query1);

            System.out.print("Enter the Id of the Soldier whose details are to be updated : ");
            String EmpID=input.next();

            while(rs.next()) {
                if(EmpID.equals(rs.getString("SOILDER_ID"))) {
                    count=1;
                }
            }

            if(count==1) {
                System.out.printf("1.To update age\n2.To update Battle unit name\n3.To update Soldier name\n4.To update soldier's cader\nENTER YOUR CHOICE : ");
                int option;
                try {
                    option=input.nextInt();
                    input.nextLine();
                    switch (option) {
                    case 1:
                        System.out.print("AGE TO BE UPDATED : ");
                        int new_age=input.nextInt();
                        input.nextLine();
                        query="UPDATE employee_details SET AGE = "+new_age+" WHERE SOILDER_ID ='"+EmpID+"' and BATTLE_UNIT_NAME='"+b_name+"'" ;

                        st.executeUpdate(query);
                        break;

                    case 2:
                        System.out.print("SELECT EXISTING BATTLE UNIT NAME : ");
                        HashMap<String,String> base_names=new HashMap<>();
                        base_names.put("PARA","B3PF90");
                        base_names.put("META","B1MF50");
                        base_names.put("BETA","B2BF70");
                        base_names.put("SOUL","B4SF60");
                        base_names.put("HEAD QUARTERS","HEBA55");


                        String new_battle_name=input.next();
                        String new_base_id="";
                        int flag=0;
                        while(true) {
                            for (Map.Entry<String,String> e:base_names.entrySet()) {
                                if(new_battle_name.equalsIgnoreCase(e.getKey())) {
                                    flag=1;
                                    new_base_id=e.getValue();
                                }

                            }
                            if(flag==0) {
                                System.out.print("SELECT EXISTING BATTLE UNIT NAME  : ");
                                new_battle_name=input.next();
                            } else if(flag==1) {
                                break;
                            }

                        }


                        query="UPDATE employee_details SET BATTLE_UNIT_NAME ='"+new_battle_name+"', BASE_ID = '"+new_base_id+"' WHERE SOILDER_ID ='"+EmpID+"' and BATTLE_UNIT_NAME='"+b_name+"'" ;
                        st.executeUpdate(query);
                        break;

                    case 3:
                        System.out.print("NAME TO BE UPDATED : ");
                        String new_name=input.nextLine();
                        query="UPDATE employee_details SET SOLIDER_NAME ='"+new_name+"' WHERE SOILDER_ID ='"+EmpID+"' and BATTLE_UNIT_NAME='"+b_name+"'";
                        st.executeUpdate(query);
                        break;

                    case 4:
                        System.out.print("CADER TO BE UPDATED  : ");
                        String new_cader=input.next();
                        query="UPDATE employee_details SET CADER ='"+new_cader+"' WHERE SOILDER_ID ='"+EmpID+"' and BATTLE_UNIT_NAME='"+b_name+"'";
                        st.executeUpdate(query);
                        break;

                    default:
                        break;
                    }
                    count++;
                } catch(Exception e) {
                    System.out.println("Some Error Occured : "+e);
                }

            } else {
                System.out.println("ERROR : SOLIDER ID NOT FOUND");

            }
        }
    }

    public static void deleteEmployee(String b_name) throws Exception {
        ArrayList<String> ids=new ArrayList<>();
        String query ;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();
        query="select * FROM employee_details where BATTLE_UNIT_NAME='"+b_name+"'"+" and  CADER != 'Field Marshal' and CADER !='General' and CADER !='Colonel' and CADER !='Major'";

        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
            ids.add(rs.getString(4));
        }
        System.out.println("ENTER A SOLIDER ID TO DELETE THE DATA : ");
        String n=input.nextLine();
        String query1;
        int flag=0;
        while(flag<=0)
        {
            for(int i=0; i<ids.size(); i++) {
                if(n.equals(ids.get(i))) {
                    query1="delete from employee_details where soilder_id='"+n+"'";
                    st.executeUpdate(query1);
                    flag=1;
                }
            }
            if(flag==0) {
                System.out.println("ERROR : SOLIDER ID NOT FOUND || SOLDIER HAS AUTHORIZATION");
                System.out.println("ENTER THE CORECT SOLIDER ID : ");
                n=input.nextLine();
            }
        }
    }

    public static void insertEmployee(String b_name) throws Exception {
        String query ;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();

        String query1="select soilder_id from employee_details";
        ResultSet rs;
        System.out.print("NAME   : ");
        String name=input.nextLine();
        System.out.print("CADER  : ");
        String cader=input.nextLine();
        System.out.print("S_ID   : ");
        String s_id=input.nextLine();
        int flag;
        while(true)
        {
            flag=0;
            rs=st.executeQuery(query1);
            while(rs.next()) {
                if(s_id.equalsIgnoreCase(rs.getString(1))) {
                    flag=1;
                }
            }

            if(flag==0) {
                System.out.print("GENDER : ");
                String gender=input.nextLine();
                HashMap<String,String> base_names=new HashMap<>();
                base_names.put("PARA","B3PF90");
                base_names.put("META","B1MF50");
                base_names.put("BETA","B2BF70");
                base_names.put("SOUL","B4SF60");
                base_names.put("HEAD QUARTERS","HEBA55");
                String b_id=base_names.get(b_name.toUpperCase());

                try {
                    System.out.print("AGE    : ");
                    int age=input.nextInt();
                    query="insert into employee_details values('"+name+"',"+age+",'"+cader+"','"+s_id+"','"+gender+"','"+b_name+"','"+b_id+"')";
                    st.executeUpdate(query);
                } catch(Exception e) {
                    System.out.println("Some Error Occured : "+e);
                }
                break;
            } else {
                System.out.print("ID ALREADY EXSIST ENTER ANOTHER ID : ");
                s_id=input.nextLine();
            }

        }


    }


    public static void searchById(String b_name) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        System.out.print("ENTER THE EMPLOYEE ID TO FETCH DETAILS : ");
        String emp_id=input.nextLine();
        String query1="select * from employee_details where battle_unit_name='"+b_name+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        while(rs1.next())
        {
            if(emp_id.equals(rs1.getString(4)))
                count=1;
        }
        if(count==1)
        {
            String query = "select * from employee_details where soilder_id="+"'"+emp_id+"' and battle_unit_name='"+b_name+"'";
            ResultSet rs=st.executeQuery(query);
            printDetails(rs);
        } else
        {
            System.out.println("ERROR : EMPLOYEE ID IS NOT FOUND IN "+b_name);
        }

    }


    public static void searchById(String b_name,String id) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        String emp_id=id;
        String query1="select * from employee_details where battle_unit_name='"+b_name+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        while(rs1.next())
        {
            if(emp_id.equals(rs1.getString(4)))
                count=1;
        }
        if(count==1)
        {
            String query = "select * from employee_details where soilder_id="+"'"+emp_id+"' and battle_unit_name='"+b_name+"'";
            ResultSet rs=st.executeQuery(query);
            printDetails(rs);
        } else
        {
            System.out.println("ERROR : EMPLOYEE IS NOT FOUND IN "+b_name);
        }

    }

    public static void searchByN(String b_name,String name) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        String query1="select * from employee_details where battle_unit_name='"+b_name+"'";
        ResultSet rs1=st.executeQuery(query1);
        int count=0;
        while(rs1.next())
        {
            if(name.equals(rs1.getString(1)))
                count=1;
        }
        if(count==1)
        {
            String query = "select * from employee_details where solider_name="+"'"+name+"' and battle_unit_name='"+b_name+"'";
            ResultSet rs=st.executeQuery(query);
            printDetails1(rs);
        } else
        {
            System.out.println("ERROR : EMPLOYEE IS NOT FOUND IN "+b_name);
        }

    }


    public static void searchByAge(String b_name) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        try{
            System.out.print("ENTER THE AGE TO FETCH DETAILS : ");
            int AGE=input.nextInt();
            input.nextLine();
            String query1="select * from employee_details where battle_unit_name='"+b_name+"'";
            ResultSet rs1=st.executeQuery(query1);
            int count=0;
            while(rs1.next())
            {
                if(AGE==rs1.getInt(2))
                    count=1;
            }
            if(count==1)
            {
                String query = "select * from employee_details where age="+"'"+AGE+"' and battle_unit_name='"+b_name+"'";
                ResultSet rs=st.executeQuery(query);
                printDetails1(rs);
            } else
            {
                System.out.println("ERROR : THIS AGE GROUP PERSON IS NOT FOUND IN "+b_name);
            }
        } catch(Exception e)
        {
            System.out.println("SOME ERROR OCCURED : "+e);
        }

    }

    public static void searchByName(String b_name) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        Boolean flag=true;
        while(flag==true)
        {
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
                String full_name=input.nextLine();
                String query = "select * from employee_details where BATTLE_UNIT_NAME="+"'"+b_name+"'"+"ORDER BY AGE DESC";
                ResultSet rs = st.executeQuery(query);
                int count=0;
                while(rs.next()) {
                    if(full_name.equalsIgnoreCase(rs.getString(1))) {
                        count=1;
                    }
                }


                if(count==1) {

                    String query1 = "select * from employee_details where solider_name="+"'"+full_name+"' and battle_unit_name='"+b_name+"'";
                    ResultSet rs1=st.executeQuery(query1);
                    printDetails(rs1);

                    System.out.println();
                    System.out.println("\t \t \t \t \t \t         \t --DETAILS FETCHED SUCCESSFULLY--");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("\t \t \t \t \t \t ERROR : SOLDIER NAME IS NOT FOUND IN "+b_name);
                    System.out.println();
                }
                break;

            case "2":
                String[] arr=new String[100];
                System.out.print("ENTER PARTIAL NAME : ");
                String partial_name=input.nextLine();
                String query2 = "select * from employee_details where BATTLE_UNIT_NAME="+"'"+b_name+"'"+"ORDER BY AGE DESC";
                ResultSet rs2 = st.executeQuery(query2);
                int count1=0;
                int n=0;
                while(rs2.next()) {
                    if(rs2.getString(1).contains(partial_name)) {
                        arr[n]=rs2.getString(1);
                        n++;
                        count1=1;
                    }
                }

                if(count1==1) {

                    System.out.println("");
                    System.out.println("\t NAME \t \t \t   AGE \t \t \t CADER \t \t \t  S_ID \t \t    GENDER \t        BUN \t           B_ID");
                    System.out.println();

                    for(int i=0; i<arr.length; i++) {
                        String query1 = "select * from employee_details where solider_name="+"'"+arr[i]+"' and battle_unit_name='"+b_name+"'";
                        ResultSet rs1=st.executeQuery(query1);
                        printDetails1(rs1);
                    }

                    System.out.println();
                    System.out.println("\t \t \t \t \t \t \t         --DETAILS FETCHED SUCCESSFULLY--");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("\t \t \t \t \t \t \t      ERROR : SOLDIER PARTIAL NAME IS NOT FOUND IN "+b_name);
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