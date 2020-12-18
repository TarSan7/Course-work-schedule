package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Auditory {
    private final IntegerProperty numOfAud = new SimpleIntegerProperty(this, "numOfAud");
    public IntegerProperty numOfAudProperty() {
            return numOfAud ;
        }
    public final int getNnumOfAud() {
            return numOfAudProperty().get();
        }
    public final void setNumOfAud(int num) {
        numOfAudProperty().set(num);
        }

    private final IntegerProperty numOfCorp = new SimpleIntegerProperty(this, "numOfCorp");
    public IntegerProperty numOfCorpProperty() { return numOfCorp ; }
    public final Integer getNumOfCorp() {
            return numOfCorpProperty().get();
        }
    public final void setNumOfCorp(int nnumOhCorp) {
        numOfCorpProperty().set(nnumOhCorp);
        }

    private final StringProperty state = new SimpleStringProperty(this, "state");
    public StringProperty stateProperty() {
        return state ;
    }
    public final String getState() {
        return stateProperty().get();
    }
    public final void setState(String state) {
        stateProperty().set(state);
    }


    public Auditory() {}

    public Auditory(int n1, int n2, String day) {
        setNumOfAud(n1);
        setNumOfCorp(n2);
        setState(day);
    }
}
