package sample;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList ;


public class PersonDataAccessor {
    // in real life, use a connection pool....
    private Connection connection ;

    public PersonDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<Person> getPersonList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from преподаватель");
        ){
            List<Person> personList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String patronymic = rs.getString(4);
                Person person = new Person(id, firstName, lastName, patronymic);
                personList.add(person);
            }
            return personList ;
        }
    }

    // other methods, eg. addPerson(...) etc
}
