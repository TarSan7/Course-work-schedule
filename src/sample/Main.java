package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    private PersonDataAccessor dataAccessor1;
    private SubjectDataAccessor dataAccessor2;
    private AuditoryDataAccessor dataAccessor3;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Первый лист*/
        Stage generalStage = new Stage();           //главная форма

        Label generalTitleLabel = new Label("Здравствуйте, дорогой пользователь!");
        // Font f = new Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 25);
        generalTitleLabel.setFont(new Font("Arial", 27));

        FlowPane paneInGeneralPane = new FlowPane(300, 300);    //прямоугольник в центре панели
        paneInGeneralPane.setPrefSize(300, 300);
        paneInGeneralPane.setStyle("-fx-background-color: white; -fx-border-color: black");

        Label generalLabelInCenterPane = new Label("Что Вы хотите сделать?");
        generalLabelInCenterPane.setFont(new Font(23));

        Button firstGeneralButton = new Button("Добавить данные");
        firstGeneralButton.setPrefSize(300, 50);
        Button secondGeneralButton = new Button("Расписание");
        secondGeneralButton.setPrefSize(300, 50);
        Button thirdGeneralButton = new Button("Дополнительная информация");
        thirdGeneralButton.setPrefSize(300, 50);

        StackPane generalStackPane = new StackPane(generalTitleLabel, paneInGeneralPane, generalLabelInCenterPane,
                firstGeneralButton, secondGeneralButton, thirdGeneralButton);
        generalStackPane.setStyle("-fx-background-color: #B0C4DE; -fx-border-insets: 5; -fx-border-color: black");
        generalStackPane.setAlignment(generalTitleLabel, Pos.TOP_CENTER);
        generalStackPane.setAlignment(paneInGeneralPane, Pos.CENTER);
        generalStackPane.setAlignment(generalLabelInCenterPane, Pos.CENTER);
        generalStackPane.setAlignment(firstGeneralButton, Pos.CENTER);
        generalStackPane.setAlignment(secondGeneralButton, Pos.CENTER);
        generalStackPane.setAlignment(thirdGeneralButton, Pos.CENTER);
        generalStackPane.setMargin(generalTitleLabel, new Insets(40, 0, 0, 0));
        generalStackPane.setMargin(paneInGeneralPane, new Insets(150, 100, 50, 100));
        generalStackPane.setMargin(generalLabelInCenterPane, new Insets(0, 0, 230, 0));
        generalStackPane.setMargin(secondGeneralButton, new Insets(150, 0, 0, 0));
        generalStackPane.setMargin(thirdGeneralButton, new Insets(300, 0, 0, 0));

        Scene generalScene = new Scene(generalStackPane, 600, 600);
        generalStage.setScene(generalScene);
        generalStage.show();

        /*Конец первого листа*/

        dataAccessor1 = new PersonDataAccessor("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/schedule", "root", "root"); // provide driverName, dbURL, user, password...
        dataAccessor2 = new SubjectDataAccessor("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/schedule", "root", "root"); // provide driverName, dbURL, user, password...
        dataAccessor3 = new AuditoryDataAccessor("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/schedule", "root", "root");

        /*------Person-start------*/
        TableView<Person> personTable1 = new TableView<>();
        personTable1.setPrefSize(400, 400);

        TableColumn<Person, Integer> personId = new TableColumn<>("Id");
        personId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> patrCol = new TableColumn<>("Patronymic");
        patrCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        personTable1.getColumns().addAll(personId, firstNameCol, lastNameCol, patrCol);

        personTable1.getItems().addAll(dataAccessor1.getPersonList());
        /*------Person-finish-------*/

        /*------Subject-start-------*/
        TableView<Subject> personTable2 = new TableView<>();
        personTable2.setPrefSize(400, 400);

        TableColumn<Subject, Integer> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Subject, String> firstNameCol2 = new TableColumn<>("Subject");
        firstNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));

        personTable2.getColumns().addAll(id, firstNameCol2);

        personTable2.getItems().addAll(dataAccessor2.getSubjectList());
        /*------Subject-finish-------*/

        Stage errorStage = new Stage();
        FlowPane paneError = new FlowPane();
        paneError.setAlignment(Pos.CENTER);

        paneError.setPrefSize(250, 100);
        Image imgError = new Image("https://e7.pngegg.com/pngimages/980/670/png-clipart-samsung-galaxy-s4-mini-computer-icons-error-high-risk-angle-triangle.png");
        ImageView imgViewError  = new ImageView(imgError);
        imgViewError.setFitWidth(40);
        imgViewError.setFitHeight(40);

        Label labelError = new Label("Введены неверные данные!");
        Button buttonError = new Button("OK");
        buttonError.setPrefSize(60, 30);

        paneError.getChildren().setAll(imgViewError, labelError, buttonError);
        paneError.setHgap(20);

        Scene sceneError = new Scene(paneError, 250, 100);
        errorStage.setScene(sceneError);
        errorStage.show();

        buttonError.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorStage.close();
            }
        });


        /*-----Доп возможности-------*/
        FlowPane paneInDopPane = new FlowPane(500, 500);                //Pane in stackpane
        paneInDopPane.setStyle("-fx-background-color: white; -fx-border-color: black");

        Text textForAuditory = new Text("Для просмотра заполненности аудиторий, заполните поля:");
        textForAuditory.setFont(new Font("Oracle R", 18));

        Button buttonForAuditory = new Button("Вывести таблицу аудиторий");
        buttonForAuditory.setPrefSize(200, 50);

        Label numOfWeekDop = new Label("Номер недели:");
        numOfWeekDop.setPrefSize(150, 20);
        TextField fieldWeekDop = new TextField();
        fieldWeekDop.setPrefSize(150, 50);

        Label dayOfWeekDop = new Label("День недели:");
        dayOfWeekDop.setPrefSize(150, 20);
        TextField fieldDayDop = new TextField();
        fieldDayDop.setPrefSize(150, 50);

        Label pairDop = new Label("Номер пары:");
        dayOfWeekDop.setPrefSize(150, 20);
        TextField fieldPairDop = new TextField();
        fieldPairDop.setPrefSize(150, 50);

        Text textForPerson = new Text("Для просмотра преподавателей нажмите кнопку:");
        textForPerson.setFont(new Font("Oracle R", 18));

        Button buttonForPerson = new Button("Вывести таблицу преподавателей");
        buttonForPerson.setPrefSize(200, 50);

        Text textForSubject = new Text("Для просмотра всех предметов, нажмите кнопку:");
        textForSubject.setFont(new Font("Oracle R", 18));

        Button buttonForSubject = new Button("Вывести таблицу предметов");
        buttonForSubject.setPrefSize(200, 50);

        paneInDopPane.setHgap(40);
        paneInDopPane.setVgap(25);
        paneInDopPane.setAlignment(Pos.CENTER);
        paneInDopPane.setMargin(textForAuditory, new Insets(0, 0, 0, 10));
        paneInDopPane.setMargin(textForSubject, new Insets(0, 0, 0, 10));
        paneInDopPane.setMargin(textForPerson, new Insets(0, 0, 0, 10));

        paneInDopPane.getChildren().addAll(textForAuditory, numOfWeekDop, dayOfWeekDop, pairDop, fieldWeekDop, fieldDayDop, fieldPairDop, buttonForAuditory,
                textForPerson, buttonForPerson, textForSubject, buttonForSubject);

        StackPane dopStackPane = new StackPane(paneInDopPane);                                      //Stack pane
        dopStackPane.setStyle("-fx-background-color: #B0C4DE; -fx-border-insets: 10; -fx-border-color: black");


        Stage personStage = new Stage();                            //Person stage
        Scene first = new Scene(personTable1, 400, 500);
        personStage.setScene(first);

        Stage subjectStage = new Stage();                           //Subject stage
        Scene second = new Scene(personTable2, 400, 450);
        subjectStage.setScene(second);

        Scene scene = new Scene(dopStackPane, 600, 600);               //scene of dop pane
        primaryStage.setScene(scene);

        thirdGeneralButton.setOnAction(new EventHandler<ActionEvent>() {        //вывод дополнительной панели по кнопке
            @Override
            public void handle(ActionEvent event) {
                primaryStage.show();
            }
        });


        buttonForAuditory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String numOfWeek = fieldWeekDop.getText();
                String dayOfWeek;
                switch (fieldDayDop.getText()) {
                    case "понедельник":
                        dayOfWeek = "1";
                        break;
                    case "вторник":
                        dayOfWeek = "2";
                        break;
                    case "среда":
                        dayOfWeek = "3";
                        break;
                    case "четверг":
                        dayOfWeek = "4";
                        break;
                    case "пятница":
                        dayOfWeek = "5";
                        break;
                    case "суббота":
                        dayOfWeek = "6";
                        break;
                    case "воскресенье":
                        dayOfWeek = "7";
                        break;
                    default:
                        dayOfWeek = "1";
                        break;
                }
                String numOfPair = fieldPairDop.getText();
                dataAccessor3.setParam(numOfWeek, dayOfWeek, numOfPair);

                /*------Auditory-start------*/
                Label titleOfAudTable = new Label("Состояние аудиторий в " + fieldWeekDop.getText() + " неделю,\n" + fieldDayDop.getText() + " на " + fieldPairDop.getText()
                        + " паре.");
                titleOfAudTable.setFont(new Font(15));

                TableView<Auditory> auditoryTable = new TableView<>();
                auditoryTable.setPrefSize(400, 400);

                TableColumn<Auditory, Integer> numberOfAuditory = new TableColumn<Auditory, Integer>("Number of auditory");
                numberOfAuditory.setCellValueFactory(new PropertyValueFactory<>("numOfAud"));

                TableColumn<Auditory, Integer> numberOfCorps = new TableColumn<Auditory, Integer>("Number of corps");
                numberOfCorps.setCellValueFactory(new PropertyValueFactory<>("numOfCorp"));

                TableColumn<Auditory, String> employmentOfAuditory = new TableColumn<Auditory, String>("Employment of auditory");
                employmentOfAuditory.setCellValueFactory(new PropertyValueFactory<>("state"));

                auditoryTable.getColumns().addAll(numberOfAuditory, numberOfCorps, employmentOfAuditory);

                try {
                    auditoryTable.getItems().addAll(dataAccessor3.getAuditoryList());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                VBox vBox = new VBox(titleOfAudTable, auditoryTable);
                Stage audStage = new Stage();                            //Person stage
                Scene aud = new Scene(vBox, 400, 500);
                audStage.setScene(aud);
                audStage.show();
                /*------Auditory-finish-------*/
            }
        });


        buttonForPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                personStage.show();
            }
        });

        buttonForSubject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subjectStage.show();
            }
        });

    }



    @Override
    public void stop() throws Exception {
        if (dataAccessor1 != null) {
            dataAccessor1.shutdown();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
