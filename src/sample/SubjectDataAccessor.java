package sample;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList ;


public class SubjectDataAccessor {
    // in real life, use a connection pool....
    private Connection connection ;

    public SubjectDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<Subject> getSubjectList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from `список предметов`");
        ){
            List<Subject> subjectList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Subject subject = new Subject(id, name);
                subjectList.add(subject);
            }
            return subjectList;
        }
    }

    // other methods, eg. addPerson(...) etc
}
