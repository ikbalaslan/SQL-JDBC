import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "aslan4646");
        Statement st = con.createStatement();

        //1.Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement
        //1. Step: Create Prepared Statement Query
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";
        //2. Step: Create Prepared Statement Object
        PreparedStatement pst1 =con.prepareStatement(sql1);
        //3. Step: Assign the values by using 'setInt(), setString() ...' methods.
        pst1.setInt(1,9999);
        pst1.setString(2,"APPLE");
        //4. Step: Execute the Query
        int numOfRecordsUpdated = pst1.executeUpdate();
        System.out.println(numOfRecordsUpdated);

        String sql2= "SELECT * FROM companies";
        ResultSet resultSet = st.executeQuery(sql2);
        while(resultSet.next()){
            System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3));
        }
        //2.Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement
        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");
        int numOfRecordsUpdated1 = pst1.executeUpdate();
        System.out.println(numOfRecordsUpdated);

        String sql3= "SELECT * FROM companies";
        ResultSet resultSet1 = st.executeQuery(sql2);
        while(resultSet1.next()){
            System.out.println(resultSet1.getString(1) + resultSet1.getString(2) + resultSet1.getString(3));
        }
        con.close();
        st.close();
        pst1.close();
    }
}
