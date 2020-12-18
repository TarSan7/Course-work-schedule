package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditoryDataAccessor {
    // in real life, use a connection pool....
    private Connection connection ;
    private String numOfWeek, numOfPair, dayOfWeek;

    public AuditoryDataAccessor( String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }
    public void setParam(String n1, String str, String n2){
        numOfWeek = n1;
        dayOfWeek = str;
        numOfPair = n2;
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<Auditory> getAuditoryList() throws SQLException {
/*UNION  SELECT список_аудиторий.Номер_аудитории, Номер_корпуса, "Занята" FROM список_аудиторий WHERE список_аудиторий.Номер_аудитории NOT IN(SELECT список_аудиторий.Номер_аудитории FROM список_аудиторий LEFT JOIN расписание ON расписание.Номер_аудитории = список_аудиторий.Номер_аудитории WHERE id_дня_недели = ? AND Номер_пары = ? AND Номер_недели = ? )*/
        PreparedStatement preparedStatement = connection.prepareStatement( "SELECT список_аудиторий.Номер_аудитории, Номер_корпуса, \"Занята\" FROM список_аудиторий LEFT JOIN расписание ON расписание.Номер_аудитории = список_аудиторий.Номер_аудитории WHERE id_дня_недели = ? AND Номер_пары = ? AND Номер_недели = ? UNION SELECT список_аудиторий.Номер_аудитории, Номер_корпуса, \"Свободна\" FROM список_аудиторий WHERE список_аудиторий.Номер_аудитории NOT IN(SELECT список_аудиторий.Номер_аудитории FROM список_аудиторий LEFT JOIN расписание ON расписание.Номер_аудитории = список_аудиторий.Номер_аудитории WHERE id_дня_недели = ? AND Номер_пары = ? AND Номер_недели = ?)");
        preparedStatement.setInt(1, Integer.parseInt(dayOfWeek));
        preparedStatement.setInt(2, Integer.parseInt(numOfPair));
        preparedStatement.setInt(3, Integer.parseInt(numOfWeek));
        preparedStatement.setInt(4, Integer.parseInt(dayOfWeek));
        preparedStatement.setInt(5, Integer.parseInt(numOfPair));
        preparedStatement.setInt(6, Integer.parseInt(numOfWeek));

        System.out.println();
        ResultSet rs = preparedStatement.executeQuery();

        List<Auditory> auditoryList = new ArrayList<>();
        while (rs.next()) {
            int numOfAud = rs.getInt(1);
            int dayOfWeek = rs.getInt(2);
            String state = rs.getString(3);
            Auditory auditory = new Auditory(numOfAud, dayOfWeek, state);
            auditoryList.add(auditory);
        }
        return auditoryList ;
    }

}
