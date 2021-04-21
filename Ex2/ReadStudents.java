package groups;

import java.sql.*;
import java.util.ArrayList;
import groups.Student;

public class ReadStudents {
    private ArrayList<Student> students = new ArrayList<Student>();
    String result;

    ReadStudents(String group) {
        String school_id;
        String name;
        String last_name;
        String subject;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/groups", "root", "");
            Statement sql = db.createStatement();
            ResultSet query = sql.executeQuery("SELECT * FROM students WHERE g = " + group);

            while (query.next()) {

                school_id = query.getString("school_id");
                name = query.getString("name");
                last_name = query.getString("last_name");
                subject = query.getString("subject");

                Student current = new Student(school_id, name, last_name, subject);
                System.out.println(current);
                students.add(current);
            }

            query.close();
            db.close();
            sql.close();
        } catch (ClassNotFoundException e) {
            result = e.getMessage();
            e.printStackTrace();
        } catch (SQLException e) {
            result = e.getMessage();
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getResult() {
        return result;
    }
}
