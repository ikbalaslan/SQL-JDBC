import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {
        //1.Step: Registration to the driver
        //2.Step: Create connection with database
          JdbcUtils.connectToDatabase("localhost","Techpro","postgres","aslan4646");
        //3.Step Create statement
        JdbcUtils.createStatement();
        //4.Step Execute the query
      JdbcUtils.createTable("Students","name VARCHAR(50)","id int ","address VARCHAR(50)");
      // JdbcUtils.execute("CREATE TABLE workers(worker_id VARCHAR(50),worker_name VARCHAR(20),worker_salary INT )");
      //JdbcUtils.createTable("workers","wan","bom","con");
       // JdbcUtils.dropTable("Students");
        JdbcUtils.insertDataToTable("Students","name 'John'");
        JdbcUtils.insertDataToTable("Students","name 'John'","id 123","address 'Istanbul'");


        //5.Step: Close the connection And statement
        JdbcUtils.closeConnectionAndStatement();



    }
}
