import java.sql.*;
import java.util.*;

import javax.sound.sampled.SourceDataLine;

import BASE.EMPLOYEE_DETAILS;
import BASE.MILITARY_BASE;
import BASE.VEHICLE_DETAILS;
import WEAPONS.ARMOURY_DETAILS;
import WEAPONS.EXPLOSIVE_DETAILS;
import WEAPONS.GUN_DETAILS;
import CSV_FILES.*;


public class LOGIN
{
    static String url = "jdbc:mysql://localhost:3306/JAVAPROJECT?allowPublicKeyRetrieval=true&useSSL=false";
    static String username = "root";
    static String password = "Root@123";

    static ArrayList<MILITARY_BASE> FIELD_MARSHAL = new ArrayList<>();
    static ArrayList<MILITARY_BASE> GENERAL = new ArrayList<>();
    static ArrayList<MILITARY_BASE> COLONEL = new ArrayList<>();
    static ArrayList<MILITARY_BASE> MAJOR = new ArrayList<>();
    static Scanner input=new Scanner(System.in);
    public static void main() throws Exception 
    {
        CSV_TO_DBMS.vehicles_data();
        CSV_TO_DBMS.BOMB_data();
        CSV_TO_DBMS.EMPLOYEE_data();
        CSV_TO_DBMS.armoury_data();
        CSV_TO_DBMS.guns_data();
        CSV_TO_DBMS.FIELDMARSHAL_pass();
        CSV_TO_DBMS.GENERAL_pass();
        CSV_TO_DBMS.MAJOR_pass();
        CSV_TO_DBMS.colonel_pass();

        INTERFACE OBJ=new INTERFACE();
        OBJ.pageFormat();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        
        Statement st = con.createStatement();

        String query1="select * from employee_details where cader='field marshal' or cader='chef field marshal'";
        String query2="select * from employee_details where cader='general' or cader='chef field marshal'";
        String query3="select * from employee_details where cader='colonel' or cader='chef field marshal'";
        String query4="select * from employee_details where cader='major' or cader='chef field marshal'";

        String query5="select * from fieldmarshal_pass";
        String query6="select * from general_pass";
        String query7="select * from colonel_pass";
        String query8="select * from major_pass";

        HashMap<String,String> fieldmarshal=new HashMap<>();
        HashMap<String,String> general=new HashMap<>();
        HashMap<String,String> colonel=new HashMap<>();
        HashMap<String,String> major=new HashMap<>();

        ResultSet rs5=st.executeQuery(query5);

        while(rs5.next())
        {
            fieldmarshal.put(rs5.getString(1),rs5.getString(2));
        }

        ResultSet rs6=st.executeQuery(query6);

        while(rs6.next())
        {
            general.put(rs6.getString(1),rs6.getString(2));
        }

        ResultSet rs7=st.executeQuery(query7);

        while(rs7.next())
        {
            colonel.put(rs7.getString(1),rs7.getString(2));
        }

        ResultSet rs8=st.executeQuery(query8);

        while(rs8.next())
        {
            major.put(rs8.getString(1),rs8.getString(2));
        }

        ResultSet rs1=st.executeQuery(query1);

        while(rs1.next())
            {
                FIELD_MARSHAL.add(new MILITARY_BASE(rs1.getString(6),rs1.getString(7) ,rs1.getString(4),fieldmarshal.get(rs1.getString(7))));
            }

        ResultSet rs2=st.executeQuery(query2);

        while(rs2.next())
        {
            GENERAL.add(new MILITARY_BASE(rs2.getString(6),rs2.getString(7) ,rs2.getString(4),general.get(rs2.getString(7))));
        }

        ResultSet rs3=st.executeQuery(query3);

        while(rs3.next())
        {
            COLONEL.add(new MILITARY_BASE(rs3.getString(6),rs3.getString(7) ,rs3.getString(4),colonel.get(rs3.getString(7))));
        }

        ResultSet rs4=st.executeQuery(query4);

        while(rs4.next())
        {
            MAJOR.add(new MILITARY_BASE(rs4.getString(6),rs4.getString(7) ,rs4.getString(4),major.get(rs4.getString(7))));
        }

        System.out.print("ENTER BASE NAME   : ");
        String b_name = input.nextLine();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println();
        while(true)
        {
            if(b_name.equalsIgnoreCase("para")||b_name.equalsIgnoreCase("meta")||b_name.equalsIgnoreCase("soul")||b_name.equalsIgnoreCase("beta")||b_name.equalsIgnoreCase("HEAD QUARTERS")) {
                if(b_name.equalsIgnoreCase("HEAD QUARTERS"))
                    OBJ.selectingTheBase(1,b_name);
                else
                    OBJ.selectingTheBase(b_name);
                System.out.println();
                System.out.println("\t\t\t\t \t \t \t \t \t  ---SELECT YOUR COICE---");
                System.out.println();
                System.out.println();
                System.out.print("| 1.EMPLOYEE DETAILS |");
                System.out.print("| 2.WEAPON DETAILS |");
                System.out.print("| 3.ARMORY DETAILS |");
                System.out.print("| 4.VEHICLE DETAILS |");
                System.out.println("| 5.EXIT |");
                System.out.println();
                System.out.print("ENTER YOUR CHOICE : ");
                String required=input.nextLine();
                Boolean flag1=true;
                switch (required) {
                case "1":
                    for(MILITARY_BASE i:FIELD_MARSHAL) {

                        if(b_name.equalsIgnoreCase(i.getBasename())) {
                            System.out.println();
                            if(b_name.equalsIgnoreCase("para")||b_name.equalsIgnoreCase("meta")||b_name.equalsIgnoreCase("soul")||b_name.equalsIgnoreCase("beta"))
                                System.out.print("ENTER FIELD MARSHAL ID        :  ");
                            else if(b_name.equalsIgnoreCase("HEAD QUARTERS"))
                                System.out.print("ENTER CHIEF FIELD MARSHAL ID  : ");
                            String m_id = input.nextLine();

                            if(m_id.equals(i.getEmployeeID())) 
                            {
                                System.out.print("ENTER PASSWORD                :  ");
                                String pwd = input.nextLine();
                                if(pwd.equals(i.getBase_password())) {
                                    System.out.println("\t\t\t\t \t \t \t \t \t ---LOGIN SUCCESSFUL---");
                                    while(flag1==true) {
                                        System.out.println("\t\t\t\t \t \t \t \t \t---SELECT YOUR CHOICE---");
                                        System.out.println();
                                        System.out.print("| 1.READ EMPLOYEE DETAILS   |\t");
                                        System.out.print("| 2.INSERT EMPLOYEE DETAILS |\t");
                                        System.out.print("| 3.UPDATE EMPLOYEE DETAILS |\t");
                                        System.out.println("| 4.DELETE EMPLOYEE DETAILS |");
                                        System.out.println();
                                        System.out.print("|  5.SEARCH BY SOLDIER ID   |\t");
                                        System.out.print("|      6.SEARCH BY AGE      |\t");
                                        System.out.print("|      7.SEARCH BY NAME     |\t");
                                        System.out.println("|           8.EXIT          |");
                                        System.out.println();
                                        System.out.print("ENTER YOUR CHOICE : ");
                                        String n=input.nextLine();
                                        switch(n) {
                                        case "1":
                                            EMPLOYEE_DETAILS.getDetails(b_name);
                                            break;
                                        case "2":
                                            EMPLOYEE_DETAILS.insertEmployee(b_name);
                                            break;
                                        case "3":
                                            EMPLOYEE_DETAILS.updateEmployee(b_name);
                                            break;
                                        case "4":
                                            EMPLOYEE_DETAILS.deleteEmployee(b_name);
                                            break;
                                        case "5":
                                            EMPLOYEE_DETAILS.searchById(b_name);
                                            break;
                                        case "6":
                                            EMPLOYEE_DETAILS.searchByAge(b_name);
                                            break;
                                        case "7":EMPLOYEE_DETAILS.searchByName(b_name);
                                            break;
                                        case "8":
                                            System.exit(0);
                                            break;
                                        default :System.out.println();
                                            System.out.println("SELECT CORRECT CHOICE : ");
                                            System.out.println();
                                            break;
                                        }
                                    }

                                } else
                                {
                                    System.out.println();
                                    System.out.println("ERROR : WRONG PASSWORD");
                                    System.out.println();
                                }
                            }
                             else
                            {   System.out.println();
                                System.out.println("ID IS NOT FOUND IN "+i.getBasename());
                                System.out.println();
                            }
                            break;
                        }
                    }
                    break;

                case "2":
                    GUN_DETAILS GUNS=new GUN_DETAILS();
                    EXPLOSIVE_DETAILS BOMBS=new EXPLOSIVE_DETAILS();
                    for(MILITARY_BASE i:GENERAL) {
                        Boolean flag2=true;
                        if(b_name.equalsIgnoreCase(i.getBasename())) {
                            System.out.println();
                            if(b_name.equalsIgnoreCase("para")||b_name.equalsIgnoreCase("meta")||b_name.equalsIgnoreCase("soul")||b_name.equalsIgnoreCase("beta"))
                                System.out.print("ENTER GENERAL ID              :  ");
                            else
                                System.out.print("ENTER CHIEF FIELD MARSHAL ID  : ");
                            String m_id = input.nextLine();

                            if(m_id.equals(i.getEmployeeID())) {
                                System.out.print("ENTER PASSWORD                :  ");
                                String pwd = input.nextLine();
                                if(pwd.equals(i.getBase_password())) {
                                    System.out.println("\t\t\t\t \t \t \t \t \t ---LOGIN SUCCESSFUL---");
                                    System.out.println();
                                    while(flag2==true) {
                                        System.out.println("\t\t\t\t \t \t \t \t \t ---SELECT YOUR CHOICE--- ");
                                        System.out.println();
                                        System.out.print("| 1.GUN DETAILS |");
                                        System.out.print("| 2.EXPLOSIVE_DETAILS |");
                                        System.out.println("| 3.EXIT |");
                                        System.out.println();
                                        System.out.print("ENTER YOUR CHOICE : ");
                                        String n=input.nextLine();
                                        switch (n) {
                                        case "1":
                                            Boolean flag3=true;
                                            while(flag3==true) {
                                                System.out.println();
                                                System.out.println();
                                                System.out.print("\t\t\t\t \t \t \t \t \t ---SELECT YOUR CHOICE---");
                                                System.out.println();
                                                System.out.print("|   1.READ GUN DETAILS   |");
                                                System.out.print("|  2.INSERT GUN DETAILS  |");
                                                System.out.print("|  3.UPDATE GUN DETAILS  |");
                                                System.out.println("|  4.DELETE GUN DETAILS  |");
                                                System.out.println();
                                                System.out.print("|  5.SEARCH BY GUN NAME  |");
                                                System.out.print("|    6.SEARCH BY COUNT   |");
                                                System.out.print("|  7.SEARCH BY GUN TYPE  |");
                                                System.out.println("|         8.EXIT         |");
                                                System.out.println();
                                                System.out.print("ENTER YOUR CHOICE : ");
                                                String n1=input.nextLine();
                                                switch(n1) {
                                                case "1":
                                                    GUNS.getGunDetails(i.getBaseID());
                                                    break;
                                                case "2":
                                                    GUNS.insertGunDetails(i.getBaseID());
                                                    break;
                                                case "3":
                                                    GUNS.updateGunDetails(i.getBaseID());
                                                    break;
                                                case "4":
                                                    GUNS.deleteGunDetails(i.getBaseID());
                                                    break;
                                                case "5":
                                                    GUNS.searchByName(i.getBaseID());
                                                    break;
                                                case "6":
                                                    GUNS.searchByCount(i.getBaseID());
                                                    break;
                                                case "7":
                                                    GUNS.searchByType(i.getBaseID());
                                                    break;
                                                case "8":
                                                    flag3=false;
                                                    break;
                                                default :
                                                System.out.println();
                                                    System.out.println("SELECT THE CORRECT OPTION ");
                                                    System.out.println();
                                                    break;
                                                }

                                            }
                                            break;
                                        case "2":
                                            Boolean flag4=true;
                                            while(flag4==true) {
                                                System.out.println();
                                                System.out.println();
                                                System.out.print("\t\t\t\t \t \t \t \t \t ---SELECT YOUR CHOICE---");
                                                System.out.println();
                                                System.out.print("|  1.READ BOMB DETAILS  |");
                                                System.out.print("| 2.INSERT BOMB DETAILS |");
                                                System.out.print("| 3.UPDATE BOMB DETAILS |");
                                                System.out.println("| 4.DELETE BOMB DETAILS |");
                                                System.out.println();
                                                System.out.print("|   5.SEARCH BY NAME    |");
                                                System.out.print("|   6.SEARCH BY COUNT   |");
                                                System.out.print("|   7.SEARCH BY RANGE   |");
                                                System.out.println("|        8.EXIT         |");
                                                System.out.println();
                                                System.out.print("ENTER YOUR CHOICE : ");
                                                String n1=input.nextLine();
                                                switch(n1) {
                                                case "1":
                                                    EXPLOSIVE_DETAILS.getBombDetails(i.getBaseID());
                                                    break;
                                                case "2":
                                                    EXPLOSIVE_DETAILS.insertBomb(i.getBaseID());
                                                    break;
                                                case "3":
                                                    EXPLOSIVE_DETAILS.updateBomb(i.getBaseID());
                                                    break;
                                                case "4":
                                                    EXPLOSIVE_DETAILS.deleteBomb(i.getBaseID());
                                                    break;
                                                    case "5":
                                                    EXPLOSIVE_DETAILS.searchByName(i.getBaseID());
                                                    break;
                                                    case "6":
                                                    EXPLOSIVE_DETAILS.searchBycount(i.getBaseID());
                                                    break;
                                                    case "7":
                                                    EXPLOSIVE_DETAILS.searchByRange(i.getBaseID());
                                                    break;
                                                case "8":
                                                    flag4=false;
                                                    break;
                                                default :
                                                System.out.println();
                                                    System.out.println("SELECT THE CORRECT OPTION ");
                                                    System.out.println();
                                                    break;
                                                }

                                            }
                                            break;
                                        case "3":
                                            System.exit(0);
                                            break;
                                        default:System.out.println();
                                            System.out.println("SELECT THE CORRECT OPTION : ");
                                            System.out.println();
                                            break;
                                        }
                                    }
                                } else{System.out.println();
                                    System.out.println("Error : WRONG PASSWORD");
                                    System.out.println();
                                    }
                            } else
                            {   System.out.println();
                                System.out.println("ID NOT FOUND IN "+i.getBasename());
                                System.out.println();
                            }
                                break;
                        }
                    }
                    break;
                case "3":
                    for(MILITARY_BASE i:COLONEL) 
                    {
                        if(b_name.equalsIgnoreCase(i.getBasename())) 
                        {
                            System.out.println();
                            if(b_name.equalsIgnoreCase("para")||b_name.equalsIgnoreCase("meta")||b_name.equalsIgnoreCase("soul")||b_name.equalsIgnoreCase("beta"))
                                System.out.print("ENTER COLONEL ID              :  ");
                            else
                                System.out.print("ENTER CHIEF FIELD MARSHAL ID  : ");
                            String m_id = input.nextLine();

                            if(m_id.equals(i.getEmployeeID())) 
                            {
                                System.out.print("ENTER PASSWORD                :  ");
                                String pwd = input.nextLine();
                                if(pwd.equals(i.getBase_password())) {
                                    System.out.println("\t\t\t\t \t \t \t \t \t ---LOGIN SUCCESSFUL---");
                                    while(flag1==true) {
                                        System.out.println("\t\t\t\t \t \t \t \t \t---SELECT YOUR CHOICE---");
                                        System.out.println();
                                        System.out.print("|  1.READ ARMOURY DETAILS  |");
                                        System.out.print("| 2.INSERT ARMOURY DETAILS |");
                                        System.out.print("| 3.UPDATE ARMOURY DETAILS |");
                                        System.out.println("| 4.DELETE ARMOURY DETAILS |");
                                        System.out.println();
                                        System.out.print("|     5.SEARCH BY NAME     |");
                                        System.out.print("|     6.SEARCH BY COUNT    |");
                                        System.out.println("|          7.EXIT          |");
                                        System.out.println();
                                        System.out.print("ENTER YOUR CHOICE :   ");
                                        String n=input.nextLine();
                                        switch(n) 
                                        {
                                            case "1":
                                                ARMOURY_DETAILS.getArmouryDeatils(i.getBaseID());
                                                break;
                                            case "2":
                                                ARMOURY_DETAILS.insertOutfits(i.getBaseID());
                                                break;
                                            case "3":
                                                ARMOURY_DETAILS.updateOutfitDetails(i.getBaseID());
                                                break;
                                            case "4":
                                                ARMOURY_DETAILS.deleteOutfits(i.getBaseID());
                                                break;
                                            case "5":
                                            ARMOURY_DETAILS.search_by_name(i.getBaseID());
                                            break;
                                            case "6":
                                            ARMOURY_DETAILS.searchByCount(i.getBaseID());
                                            break;
                                            case "7":
                                                System.exit(0);
                                                break;
                                            default :
                                                System.out.println();
                                                System.out.println("SELECT CORRECT CHOICE : ");
                                                break;
                                        }
                                    }

                                } 
                                else
                                {
                                    System.out.println();
                                    System.out.println("ERROR : WRONG PASSWORD");
                                    System.out.println();
                                }
                            } 
                            else
                            {
                                System.out.println();
                                System.out.println("ID IS NOT FOUND IN "+i.getBasename());
                            }
                            break;
                        }
                    }
                    break;

                    case "4":
                    for(MILITARY_BASE i:MAJOR) 
                    {
                        if(b_name.equalsIgnoreCase(i.getBasename())) {
                            System.out.println();
                            if(b_name.equalsIgnoreCase("para")||b_name.equalsIgnoreCase("meta")||b_name.equalsIgnoreCase("soul")||b_name.equalsIgnoreCase("beta"))
                                System.out.print("ENTER MAJOR ID                :  ");
                            else
                                System.out.print("ENTER CHIEF FIELD MARSHAL ID  : ");
                            String m_id = input.nextLine();
                    
                            if(m_id.equals(i.getEmployeeID())) {
                                
                                System.out.print("ENTER PASSWORD                :  ");
                                String pwd = input.nextLine();
                                if(pwd.equals(i.getBase_password())) {
                                    System.out.println("\t\t\t\t \t \t \t \t \t ---LOGIN SUCCESSFUL---");
                                    while(flag1==true) {
                                        System.out.println("\t\t\t\t \t \t \t \t \t---SELECT YOUR CHOICE---");
                                        System.out.println();
                                        System.out.print("|  1. READ VEHICLE DETAILS  |");
                                        System.out.print("| 2. INSERT VEHICLE DETAILS |");
                                        System.out.print("| 3. UPDATE VEHICLE DETAILS |");
                                        System.out.print("| 4. DELETE VEHICLE DETAILS |");
                                        System.out.println();
                                        System.out.println();
                                        System.out.print("|    5. SEARCH BY COUNT     |");
                                        System.out.print("|    6. SEARCH BY NAME      |");
                                        System.out.print("|  7. SEARCH BY VEHICLE ID  |");
                                        System.out.print("|           8. EXIT         |");
                                        System.out.println();
                                        System.out.print("ENTER YOUR CHOICE : ");
                                        String n=input.nextLine();
                                        switch(n) {
                                        case "1":
                                            VEHICLE_DETAILS.getdetails(i.getBaseID());
                                            break;
                                        case "2":
                                            VEHICLE_DETAILS.insertVehicle(i.getBaseID());
                                            break;
                                        case "3":
                                            VEHICLE_DETAILS.updateVehicle(i.getBaseID());
                                            break;
                                        case "4":
                                            VEHICLE_DETAILS.removeVehicle(i.getBaseID());
                                            break;
                                        case "5":
                                            VEHICLE_DETAILS.searchByCount(i.getBaseID());
                                            break;
                                        case "6":
                                            VEHICLE_DETAILS.searchByName(i.getBaseID());
                                            break;
                                        case "7":
                                            VEHICLE_DETAILS.searchById(i.getBaseID());
                                            break;
                                        case "8":
                                            System.exit(0);
                                            break;
                                        default :
                                            System.out.println();
                                            System.out.println("SELECT CORRECT CHOICE : ");
                                            System.out.println();
                                            break;
                                        }
                                    }
                    
                                } else{
                                    System.out.println();
                                    System.out.println("ERROR : WRONG PASSWORD");
                                    System.out.println();
                                     }
                            } else{
                                System.out.println();
                                System.out.println("ID IS NOT FOUND IN "+i.getBasename());
                                }
                            break;
                        }
                    }
                    break;

                case "5":
                    System.exit(0);
                    break;
                default:
                System.out.println();
                System.out.println("\t \t \t \t \t x CHOOSE CORRECT OPTION x");
                    break;
                }
            } else {
                System.out.println();
                System.out.println("\t \t \t \t \t NO BASE FOUND");
                System.out.print("ENTER THE BASE NAME : ");
                b_name=input.nextLine();
                System.out.println();
            }
        }
    }

}