import javax.xml.transform.Result;
import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Step: Registration to the driver
        Class.forName("org.postgresql.Driver");
        //2. Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "aslan4646");

        Statement st = con.createStatement();

        //1.Example: Select the country names whose region id's are 1
        String sql1 = "SELECT country_name FROM countries WHERE region_id =1";
        // If you use execute() method with a DQL you will get true or false as return.But I want to see  the records
        boolean result1 = st.execute(sql1);
        System.out.println("result1 " + result1);

        //  To see the records we have another method which is "executeQuery()".
        ResultSet resultSet = st.executeQuery(sql1);
        while (resultSet.next()) {
            String countries = resultSet.getString("country_name");//We can also type here the column index in the table. Like 1
            System.out.println(countries);
        }

        //2.Example: Select the country_id and country_name whose region_id's are greater than 2

        String sql2 = "SELECT country_name , country_id FROM countries WHERE region_id>2";
        ResultSet resultSet1 = st.executeQuery(sql2);
        //1.way
        while (resultSet1.next()) {
            String name = resultSet1.getString("country_name");
            String ids = resultSet1.getString("country_id");
            System.out.println(ids + " " + name);
        }
        //2.WAY
        while (resultSet1.next()) {

            System.out.println(resultSet1.getString("country_id") + " " + (resultSet1.getString("country_name")));
        }
        //3.Example: Select all columns whose number_of_employees is the lowest from companies table

        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
      ResultSet resultSet2 =  st.executeQuery(sql3);
        while(resultSet2.next()){
            String result = resultSet2.getString(1);
            String result2= resultSet2.getString(2);
            String result3= resultSet2.getString(3);
            System.out.println(result +" " + result2 + " " + result3) ;
        }
        con.close();
        st.close();

    }
}