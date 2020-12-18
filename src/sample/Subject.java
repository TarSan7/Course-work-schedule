package sample;
import javafx.beans.property.StringProperty ;
import javafx.beans.property.* ;
import javafx.beans.property.SimpleStringProperty ;

public class Subject {
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

    private final StringProperty name = new SimpleStringProperty(this, "name");
    public StringProperty nameProperty() {
        return name ;
    }
    public final String name() {
        return nameProperty().get();
    }
    public final void setFirstName(String name) {
        nameProperty().set(name);
    }

    public Subject() {}

    public Subject(int id, String name) {
        setId(id);
        setFirstName(name);
    }

}