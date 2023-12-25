package CSV_FILES;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

import com.mysql.cj.protocol.Resultset;

public class CSV_TO_DBMS
{

    static String jdbcUrl = "jdbc:mysql://localhost:3306/javaproject?allowPublicKeyRetrieval=true&useSSL=false&jdbcCompliantTruncation=false";
    static String username = "root";
    static String password = "Root@123";


    public static void vehicles_data()
    {

        String filePath = "D:\\arcgis\\data\\shapefiles\\Telegram Desktop\\vehicles.csv";
        int batchSize = 100;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
            String sql2="drop table if exists vehicles ; ";

            String sql1 =" Create table vehicles (VEHICLE_NAME varchar(50),VEHICLE_TYPE varchar(50), COUNT int,VEHICLE_ID varchar(20),BASE_ID varchar(50))";
            String sql = "insert into VEHICLES values(?,?,?,?,?)";
            Statement St = connection.createStatement();
            St.executeUpdate(sql2);
            St.executeUpdate(sql1);
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;
            int count1 = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String VEHICLE_NAME = data[0];
                String VEHICLE_TYPE = data[1];
                int COUNT = Integer.parseInt(data[2]);
                String Vehicle_id=data[3];
                String BASE_ID = data[4];
                statement.setString(1, VEHICLE_NAME);
                statement.setString(2, VEHICLE_TYPE);
                statement.setInt(3, COUNT);
                statement.setString(4, Vehicle_id);
                statement.setString(5, BASE_ID);
                statement.addBatch();
                if (count1 % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void guns_data()
    {
        String filePath = "D:\\arcgis\\data\\shapefiles\\Telegram Desktop\\guns.csv";
        int batchSize = 100;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
            String sql2="drop table if exists guns ; ";

            String sql1 =" Create table guns (GUN_NAME varchar(50),GUN_TYPE varchar(50), COUNT int,GUN_RANGE varchar(20),BASE_ID varchar(50))";
            String sql = "insert into guns values(?,?,?,?,?)";
            Statement St = connection.createStatement();
            St.executeUpdate(sql2);
            St.executeUpdate(sql1);
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;
            int count1 = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String GUN_NAME = data[0];
                String GUN_TYPE = data[1];
                int COUNT = Integer.parseInt(data[2]);
                String GUN_RANGE=data[3];
                String BASE_ID = data[4];
                statement.setString(1, GUN_NAME);
                statement.setString(2, GUN_TYPE);
                statement.setInt(3, COUNT);
                statement.setString(4,GUN_RANGE);
                statement.setString(5, BASE_ID);
                statement.addBatch();
                if (count1 % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void BOMB_data()
    {
        String filePath = "D:\\arcgis\\data\\shapefiles\\Telegram Desktop\\bomb.csv";
        int batchSize = 100;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
            String sql2="drop table if exists bomb ; ";

            String sql1 =" Create table bomb (BOMB_NAME varchar(50),BOMB_RANGE varchar(50), COUNT int,BASE_ID varchar(50))";
            String sql = "insert into bomb values(?,?,?,?)";
            Statement St = connection.createStatement();
            St.executeUpdate(sql2);
            St.executeUpdate(sql1);
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;
            int count1 = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String BOMB_NAME = data[0];
                String BOMB_RANGE = data[1];
                int COUNT = Integer.parseInt(data[2]);
                String BASE_ID = data[3];
                statement.setString(1, BOMB_NAME);
                statement.setString(2, BOMB_RANGE);
                statement.setInt(3, COUNT);
                statement.setString(4, BASE_ID);
                statement.addBatch();
                if (count1 % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void EMPLOYEE_data()
    {
        String filePath = "D:\\arcgis\\data\\shapefiles\\Telegram Desktop\\employee_details.csv";
        int batchSize = 100;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
            String sql2="drop table if exists employee_details ; ";

            String sql1 =" Create table employee_details (SOLIDER_NAME varchar(50),AGE int, cader varchar(50),soilder_id varchar(10),GENDER varchar(10),BATTLE_UNIT_NAME varchar(20),BASE_ID varchar(20))";
            String sql = "insert into employee_details values(?,?,?,?,?,?,?)";
            Statement St = connection.createStatement();
            St.executeUpdate(sql2);
            St.executeUpdate(sql1);
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;
            int count1 = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String SOLDIER_NAME = data[0];
                int AGE = Integer.parseInt(data[1]);
                String CADER = data[2];
                String SOLDIRE_ID=data[3];
                String GENDER = data[4];
                String base_name=data[5];
                String base_id=data[6];
                statement.setString(1, SOLDIER_NAME);
                statement.setInt(2, AGE);
                statement.setString(3, CADER);
                statement.setString(4,SOLDIRE_ID);
                statement.setString(5, GENDER);
                statement.setString(6,base_name);
                statement.setString(7,base_id);
                statement.addBatch();
                if (count1 % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void armoury_data()
    {
        String filePath = "D:\\arcgis\\data\\shapefiles\\Telegram Desktop\\armoury.csv";
        int batchSize = 100;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
            String sql2="drop table if exists armoury ; ";

            String sql1 =" Create table armoury (OUT_FITS varchar(50),COUNT int,BASE_ID varchar(50))";
            String sql = "insert into ARMOURY values(?,?,?)";
            Statement St = connection.createStatement();
            St.executeUpdate(sql2);
            St.executeUpdate(sql1);
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;
            int count1 = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String OUT_FIT = data[0];
                int COUNT = Integer.parseInt(data[1]);
                String BASE_ID = data[2];
                statement.setString(1, OUT_FIT);
                statement.setInt(2, COUNT);
                statement.setString(3, BASE_ID);
                statement.addBatch();
                if (count1 % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void colonel_pass()
    {
        String filePath = "D:\\arcgis\\data\\shapefiles\\Telegram Desktop\\COLONEL_PASS.csv";
        int batchSize = 100;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
            String sql2="drop table if exists colonel_pass ; ";

            String sql1 =" Create table colonel_pass (BASE_ID varchar(15),PASSWORD varchar(15))";
            String sql = "insert into COLONEL_PASS values(?,?)";
            Statement St = connection.createStatement();
            St.executeUpdate(sql2);
            St.executeUpdate(sql1);
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;
            int count1 = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                
                String BASE_ID = data[0];
                String PASSWORD = data[1];
                statement.setString(1, BASE_ID);
                statement.setString(2, PASSWORD);
                statement.addBatch();
                if (count1 % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void GENERAL_pass()
    {
        String filePath = "D:\\arcgis\\data\\shapefiles\\Telegram Desktop\\GENERAL_PASS.csv";
        int batchSize = 100;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
            String sql2="drop table if exists GENERAL_pass ; ";

            String sql1 =" Create table GENERAL_pass (BASE_ID varchar(15),PASSWORD varchar(15))";
            String sql = "insert into GENERAL_PASS values(?,?)";
            Statement St = connection.createStatement();
            St.executeUpdate(sql2);
            St.executeUpdate(sql1);
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;
            int count1 = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                
                String BASE_ID = data[0];
                String PASSWORD = data[1];
                statement.setString(1, BASE_ID);
                statement.setString(2, PASSWORD);
                statement.addBatch();
                if (count1 % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void MAJOR_pass()
    {
        String filePath = "D:\\arcgis\\data\\shapefiles\\Telegram Desktop\\MAJOR_PASS.csv";
        int batchSize = 100;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
            String sql2="drop table if exists MAJOR_pass ; ";

            String sql1 =" Create table MAJOR_pass (BASE_ID varchar(15),PASSWORD varchar(15))";
            String sql = "insert into MAJOR_PASS values(?,?)";
            Statement St = connection.createStatement();
            St.executeUpdate(sql2);
            St.executeUpdate(sql1);
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;
            int count1 = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                
                String BASE_ID = data[0];
                String PASSWORD = data[1];
                statement.setString(1, BASE_ID);
                statement.setString(2, PASSWORD);
                statement.addBatch();
                if (count1 % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void FIELDMARSHAL_pass()
    {
        String filePath = "D:\\arcgis\\data\\shapefiles\\Telegram Desktop\\FIELDMARSHAL_PASS.csv";
        int batchSize = 100;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
            String sql2="drop table if exists FIELDMARSHAL_pass ; ";

            String sql1 =" Create table FIELDMARSHAL_pass (BASE_ID varchar(15),PASSWORD varchar(15))";
            String sql = "insert into FIELDMARSHAL_PASS values(?,?)";
            Statement St = connection.createStatement();
            St.executeUpdate(sql2);
            St.executeUpdate(sql1);
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;
            int count1 = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                
                String BASE_ID = data[0];
                String PASSWORD = data[1];
                statement.setString(1, BASE_ID);
                statement.setString(2, PASSWORD);
                statement.addBatch();
                if (count1 % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}



