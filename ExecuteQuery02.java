import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "aslan4646");
        Statement st = con.createStatement();

        //1.Example: Find the company and number_of_employees whose number_of_employees is the second highest from the companies table
        //1.WAY OFFSET and FETCH NEXT
        String sql1 = "SELECT company,number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 row FETCH NEXT 1 ROW only";
        ResultSet resultSet = st.executeQuery(sql1);
        while (resultSet.next()){
            String x =  resultSet.getString("company");
            String y = resultSet.getString("number_of_employees");
            System.out.println(x + " " + y);
        }
        //2.Way : By using subquery
        String sql2 = "Select company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees  = (SELECT MAX(number_of_employees) FROM companies \n" +
                "                              WHERE  number_of_employees < (Select MAX(number_of_employees) FROM companies))";
    ResultSet resultSet1 = st.executeQuery(sql2);
    while(resultSet1.next()){
        String x =  resultSet1.getString("company");
        String y = resultSet1.getString("number_of_employees");
        System.out.println(x + " " + y);
    }

        //2.Example: Find the company names and number of employees whose number of employees is less than the average number of employees
    String sql3 = "SELECT company , number_of_employees FROM companies WHERE number_of_employees < (SELECT avg(number_of_employees) FROM companies)";
    ResultSet resultSet2 = st.executeQuery(sql3);
    while(resultSet2.next()){
        String x =  resultSet2.getString("company");
        String y = resultSet2.getString("number_of_employees");
        System.out.println(x + " " + y);
    }
        con.close();
        st.close();

    }
}
