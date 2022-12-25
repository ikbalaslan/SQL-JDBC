import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "aslan4646");
        Statement st = con.createStatement();

        //1.Example: Update the number of employees to 16000 if the number of employees is less than the average number of employees
    String sql1 = "UPDATE companies SET number_of_employees = 16000 WHERE number_of_employees < (SELECT avg(number_of_employees) FROM companies)";
    int numOfRecordsUpdated = st.executeUpdate(sql1);
        System.out.println(numOfRecordsUpdated);

    String sql2 = "Select * FROM companies";
   ResultSet resultSet = st.executeQuery(sql2);
   while(resultSet.next()){
       System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));

   }
        con.close();
        st.close();










    }
}
