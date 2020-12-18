package sample;
import javafx.beans.property.StringProperty ;
import javafx.beans.property.* ;
import javafx.beans.property.SimpleStringProperty ;

public class Person {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    public IntegerProperty idProperty() {
        return id ;
    }
    public final int getId() {
        return idProperty().get();
    }
    public final void setId(int id) {
        idProperty().set(id);
    }

    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
    public StringProperty firstNameProperty() {
        return firstName ;
    }
    public final String getFirstName() {
        return firstNameProperty().get();
    }
    public final void setFirstName(String firstName) {
        firstNameProperty().set(firstName);
    }

    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
    public StringProperty lastNameProperty() {
        return lastName ;
    }
    public final String getLastName() {
        return lastNameProperty().get();
    }
    public final void setLastName(String lastName) {
        lastNameProperty().set(lastName);
    }

    private final StringProperty patronymic = new SimpleStringProperty(this, "patronymic");
    public StringProperty patronymicProperty() {
        return patronymic ;
    }
    public final String getPatronymic() {
        return patronymicProperty().get();
    }
    public final void setPatronymic(String patronymic) {
        patronymicProperty().set(patronymic);
    }

    public Person(int id, String name) {}

    public Person(int id, String firstName, String lastName, String patronymic) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPatronymic(patronymic);
    }

}