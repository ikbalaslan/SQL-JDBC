import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {
 private static  Connection connection;
 private static Statement statement;
    public static Connection connectToDatabase(String hostName,String databaseName,String username,String password){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
             connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+databaseName, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection is success");
           return connection;
        }


        public static Statement createStatement(){
            try {
          statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Statament created");
            return statement;
        }

        public static boolean execute(String query){
           boolean isExecute;
            try {
               isExecute = statement.execute(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Query executed");
            return isExecute;
        }

    //5. Step: Close the connection and statement
    public static void closeConnectionAndStatement(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed()&&statement.isClosed()){
                System.out.println("Connection and statement closed!");
            }else {
                System.out.println("Connection and statement not closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void dropTable(String tableName){
        try {
            statement.execute("DROP TABLE " + tableName);
            System.out.println("Table " + tableName + " Dropped");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(String tableName,String ... columnName_DataType){

        StringBuilder column_DataTypeString = new StringBuilder("");
        for (String w : columnName_DataType){
           column_DataTypeString.append(w).append(",");
        }
        column_DataTypeString.deleteCharAt(column_DataTypeString.length()-1);

        try {
            statement.execute("CREATE TABLE "+tableName+"("+ column_DataTypeString + " )");
            System.out.println("Table " + tableName + " created");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static void insertDataToTable(String tableName , String ... columnName_Values){
StringBuilder columnNames = new StringBuilder("");
StringBuilder values = new StringBuilder("");
        for (String w :columnName_Values){
      columnNames.append(w.split(" ")[0]).append(",");
      values.append(w.split(" ")[1]).append(",");
        }
      columnNames.deleteCharAt(columnNames.length()-1);
      values.deleteCharAt(values.length()-1);

        String query = "INSERT INTO "+tableName+"("+columnNames+")VALUES("+values+")";
        try {
            statement.execute(query);
            System.out.println("Data inserted into table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public static List<Object> getColumnList1(String columnName,String tableName){
        ResultSet resultSet=null;
        String query = "Select "+columnName+" FROM "+tableName+"";
        List<Object> list = new ArrayList<>();
        try {
          resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
              list.add(resultSet.getObject(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    return list;

    }














    public static List<Object> getColumnList(String columnName, String tableName){
        ResultSet resultSet;
        List<Object> columnData = new ArrayList<>();

        String query = "SELECT "+columnName+" FROM "+tableName;

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                columnData.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return columnData;
    }




}
/*
 Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "aslan4646");
        Statement st = connection.createStatement();
 */