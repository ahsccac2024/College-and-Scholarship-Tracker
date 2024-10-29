import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import java.io.*;

/**
 * Write a description of class MainClass here.
 *
 * @author (your name)
 * @version 1.0
 */
public class MainClass extends Application
{
    private ObservableList<CollegeInfo> defdatac = FXCollections.observableArrayList();
    private ObservableList<ScholarshipInfo> defdatas = FXCollections.observableArrayList();
    private ObservableList<ReqCheck> defdatar = FXCollections.observableArrayList();
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage appStage) {
        appStage.setTitle("College and Scholarship Cost Personal Tracker v1.0");
        //Home Screen Layout
        GridPane appGrid = new GridPane();
        
        
        appGrid.setAlignment(Pos.TOP_LEFT);
        appGrid.setHgap(5);
        appGrid.setVgap(5);
        appGrid.setPadding(new Insets(20, 20, 20, 20));
        //CollegeScreen Layout
        VBox temp=new VBox();
        temp.setAlignment(Pos.TOP_LEFT);
        temp.setPadding(new Insets(20, 20, 20, 20));
        //ScholarshipScreen Layout.
        VBox temp2=new VBox();
        temp2.setAlignment(Pos.TOP_LEFT);
        temp2.setPadding(new Insets(20, 20, 20, 20));
        
        VBox reqLayout = new VBox();
        Scene scholarshipScene = new Scene(temp2, 400, 400);
        Scene homeScene = new Scene(appGrid, 400, 400);
        Scene CollgeTable = new Scene(temp, 400, 400);
        Scene requirementCheckList = new Scene(reqLayout, 400, 400);
        //Home Screen Design
        Text welcomeText = new Text("College and Scholarship Progress Tracker");
        appGrid.add(welcomeText, 0, 0, 2, 1);
        
        Button collegeButton= new Button("Colleges");
        Button scholarshipButton= new Button("Scholarships");
        Button reqButton = new Button("CheckList");
        HBox collegeButtonHBox = new HBox(20);
        HBox scholarshipButtonHBox = new HBox(20);
        HBox reqButtonHBox = new HBox(20);
        collegeButtonHBox.setAlignment(Pos.CENTER);
        collegeButtonHBox.getChildren().add(collegeButton);
        scholarshipButtonHBox.setAlignment(Pos.BOTTOM_CENTER);
        scholarshipButtonHBox.getChildren().add(scholarshipButton);
        reqButtonHBox.setAlignment(Pos.BOTTOM_CENTER);
        reqButtonHBox.getChildren().add(reqButton);
        
        appGrid.add(collegeButtonHBox, 0, 3);
        appGrid.add(scholarshipButtonHBox, 0, 4);
        appGrid.add(reqButtonHBox, 0, 5);
        //College Screen Design
        TableView collegeTable = new TableView();
        
        collegeTable.setEditable(true);
        TableColumn<CollegeInfo, String> uniName = new TableColumn<>("Name of Universities");
        
        uniName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        uniName.setCellFactory(TextFieldTableCell.<CollegeInfo>forTableColumn());
        uniName.setOnEditCommit (
            new EventHandler<TableColumn.CellEditEvent<CollegeInfo, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<CollegeInfo, String> t) {
                    ((CollegeInfo)t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                }
            }
        );
        
        TableColumn<CollegeInfo, String> uniReq = new TableColumn<>("Requirements");
        
        uniReq.setCellValueFactory(new PropertyValueFactory<>("Req"));
        uniReq.setCellFactory(TextFieldTableCell.<CollegeInfo>forTableColumn());
        uniReq.setOnEditCommit (
            new EventHandler<TableColumn.CellEditEvent<CollegeInfo, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<CollegeInfo, String> t) {
                    ((CollegeInfo)t.getTableView().getItems().get(t.getTablePosition().getRow())).setReq(t.getNewValue());
                }
            }
        );
        
        TableColumn<CollegeInfo, String> uniCost = new TableColumn<>("Estimated Cost");
        
        uniCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        uniCost.setCellFactory(TextFieldTableCell.<CollegeInfo>forTableColumn());
        uniCost.setOnEditCommit (
            new EventHandler<TableColumn.CellEditEvent<CollegeInfo, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<CollegeInfo, String> t) {
                    ((CollegeInfo)t.getTableView().getItems().get(t.getTablePosition().getRow())).setCost(t.getNewValue());
                }
            }
        );
        
        collegeTable.getColumns().add(uniName);
        collegeTable.getColumns().add(uniReq);
        collegeTable.getColumns().add(uniCost);
        readCollege(defdatac);
        collegeTable.setItems(defdatac);
        collegeTable.setPlaceholder(new Label("No colleges here"));
        TableView.TableViewSelectionModel<CollegeInfo> cSelect = collegeTable.getSelectionModel();
        cSelect.setSelectionMode(SelectionMode.SINGLE);
        ObservableList<CollegeInfo> cSelected = cSelect.getSelectedItems();
        
        temp.getChildren().add(collegeTable);
        Button homeButton1 = new Button("Home");
        HBox homeButtonHBox = new HBox(20);
        homeButtonHBox.setAlignment(Pos.BOTTOM_CENTER);
        homeButtonHBox.getChildren().add(homeButton1);
        temp.getChildren().add(homeButtonHBox);
        Button saveCollege = new Button("Save");
        HBox saveCHBox = new HBox(20);
        saveCHBox.getChildren().add(saveCollege);
        saveCHBox.setAlignment(Pos.BOTTOM_CENTER);
        temp.getChildren().add(saveCHBox);
        saveCollege.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    saveCollege(defdatac);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        //Scholarship screen functionality
        TableView scholarTable = new TableView();
        scholarTable.setEditable(true);
        TableColumn<ScholarshipInfo, String> ScholarName = new TableColumn<>("Name of Scholarships");
        
        ScholarName.setCellValueFactory(new PropertyValueFactory<>("NameS"));
        ScholarName.setCellFactory(TextFieldTableCell.<ScholarshipInfo>forTableColumn());
        ScholarName.setOnEditCommit (
            new EventHandler<TableColumn.CellEditEvent<ScholarshipInfo, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<ScholarshipInfo, String> t) {
                    ((ScholarshipInfo)t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                }
            }
        );
        
        
        TableColumn<ScholarshipInfo, String> ScholarReq = new TableColumn<>("Requirements");
        
        ScholarReq.setCellValueFactory(new PropertyValueFactory<>("ReqS"));
        ScholarReq.setCellFactory(TextFieldTableCell.<ScholarshipInfo>forTableColumn());
        ScholarReq.setOnEditCommit (
            new EventHandler<TableColumn.CellEditEvent<ScholarshipInfo, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<ScholarshipInfo, String> t) {
                    ((ScholarshipInfo)t.getTableView().getItems().get(t.getTablePosition().getRow())).setReq(t.getNewValue());
                }
            }
        );
        
        TableColumn<ScholarshipInfo, String> ScholarCost = new TableColumn<>("Estimated Amount");
        
        ScholarCost.setCellValueFactory(new PropertyValueFactory<>("CostS"));
        ScholarCost.setCellFactory(TextFieldTableCell.<ScholarshipInfo>forTableColumn());
        ScholarCost.setOnEditCommit (
            new EventHandler<TableColumn.CellEditEvent<ScholarshipInfo, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<ScholarshipInfo, String> t) {
                    ((ScholarshipInfo)t.getTableView().getItems().get(t.getTablePosition().getRow())).setCost(t.getNewValue());
                }
            }
        );
        
        scholarTable.getColumns().add(ScholarName);
        scholarTable.getColumns().add(ScholarReq);
        scholarTable.getColumns().add(ScholarCost);
        readScholar(defdatas);
        scholarTable.setItems(defdatas);
        scholarTable.setPlaceholder(new Label("No scholarships here"));
        TableView.TableViewSelectionModel<ScholarshipInfo> sSelect = scholarTable.getSelectionModel();
        sSelect.setSelectionMode(SelectionMode.SINGLE);
        ObservableList<ScholarshipInfo> sSelected = sSelect.getSelectedItems();
        
        
        
        temp2.getChildren().add(scholarTable);
         Button homeButton2= new Button("Home");
        HBox homeButton2HBox = new HBox(20);
        homeButton2HBox.setAlignment(Pos.BOTTOM_CENTER);
        homeButton2HBox.getChildren().add(homeButton2);
        temp2.getChildren().add(homeButton2HBox);
        Button saveScholar = new Button("Save");
        HBox saveSHBox = new HBox(20);
        saveSHBox.getChildren().add(saveScholar);
        saveSHBox.setAlignment(Pos.BOTTOM_CENTER);
        temp2.getChildren().add(saveSHBox);
        saveScholar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    saveScholar(defdatas);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        //Req screen design
        TableView CheckList = new TableView();
        CheckList.setEditable(true);
        TableColumn<ReqCheck, String> Requirements = new TableColumn("Requirement");
        Requirements.setCellValueFactory(new PropertyValueFactory<>("ReqR"));
        Requirements.setCellFactory(TextFieldTableCell.<ReqCheck>forTableColumn());
        Requirements.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<ReqCheck, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<ReqCheck, String> t) {
                    ((ReqCheck)t.getTableView().getItems().get(t.getTablePosition().getRow())).setReq(t.getNewValue());
                }
            }
        );
        TableColumn<ReqCheck, String> isComplete =new TableColumn("Is Met? (Yes/No)");
        isComplete.setCellValueFactory(new PropertyValueFactory<>("StatusR"));
        isComplete.setCellFactory(TextFieldTableCell.<ReqCheck>forTableColumn());
        isComplete.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<ReqCheck, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<ReqCheck, String> t) {
                    ((ReqCheck)t.getTableView().getItems().get(t.getTablePosition().getRow())).setStatus(t.getNewValue());
                }
            }
        );
        CheckList.setPlaceholder(new Label("Nothing Listed here"));
        CheckList.getColumns().add(Requirements);
        CheckList.getColumns().add(isComplete);
        readReq(defdatar);
        CheckList.setItems(defdatar);
        TableView.TableViewSelectionModel<ScholarshipInfo> rSelect = CheckList.getSelectionModel();
        rSelect.setSelectionMode(SelectionMode.SINGLE);
        reqLayout.getChildren().add(CheckList);
        
         Button homeButton3= new Button("Home");
        HBox homeButton3HBox = new HBox(20);
        homeButton3HBox.setAlignment(Pos.BOTTOM_CENTER);
        homeButton3HBox.getChildren().add(homeButton3);
        reqLayout.getChildren().add(homeButton3HBox);
        Button saveReq = new Button("Save");
        HBox saveRHBox = new HBox(20);
        saveRHBox.getChildren().add(saveReq);
        saveRHBox.setAlignment(Pos.BOTTOM_CENTER);
        reqLayout.getChildren().add(saveRHBox);
        saveReq.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    saveReq(defdatar);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        //Home Screen Functionality
        collegeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                appStage.setScene(CollgeTable);
            }
        });
        
        scholarshipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                appStage.setScene(scholarshipScene);
            }
        });
        reqButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                appStage.setScene(requirementCheckList);
            }
        });
        
        //Home Buttons
        homeButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                appStage.setScene(homeScene);
            }
        });
        homeButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                appStage.setScene(homeScene);
            }
        });
        homeButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                appStage.setScene(homeScene);
            }
        });
        appStage.setScene(homeScene);
        appStage.show();
        
        
        
        
    }
    public void saveCollege(ObservableList<CollegeInfo> defdatac) throws Exception{
        Writer write = null;
        try {
            File collegeCSV = new File("Colleges.csv");
            write = new BufferedWriter(new FileWriter(collegeCSV));
            for (CollegeInfo college : defdatac) {
                String row = college.getName()+","+college.getReq()+","+college.getCost()+"\n";
                write.write(row);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
         write.flush();
         write.close();
        }
            
    }
    public void readCollege(ObservableList<CollegeInfo> data) {
        String File = "Colleges.csv";
        String Delimiter = ",";
        
        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader(File));
            
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(Delimiter, -1);
                CollegeInfo recorder = new CollegeInfo(fields[0], fields[1], fields[2]);
                data.add(recorder);
            } 
        }catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    public void saveScholar(ObservableList<ScholarshipInfo> defdataS) throws Exception{
        Writer write = null;
        try {
            File collegeCSV = new File("Scholarships.csv");
            write = new BufferedWriter(new FileWriter(collegeCSV));
            for (ScholarshipInfo scholarship : defdataS) {
                String row = scholarship.getNameS()+","+scholarship.getReqS()+","+scholarship.getCostS()+"\n";
                write.write(row);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
         write.flush();
         write.close();
        }
            
    }
    public void readScholar(ObservableList<ScholarshipInfo> data) {
        String File = "Scholarships.csv";
        String Delimiter = ",";
        
        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader(File));
            
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(Delimiter, -1);
                ScholarshipInfo recorder = new ScholarshipInfo(fields[0], fields[1], fields[2]);
                data.add(recorder);
            } 
        }catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    public void saveReq(ObservableList<ReqCheck> defdataR) throws Exception{
        Writer write = null;
        try {
            File collegeCSV = new File("CheckList.csv");
            write = new BufferedWriter(new FileWriter(collegeCSV));
            for (ReqCheck requirement : defdataR) {
                String row = requirement.getReqR()+","+requirement.getStatusR()+"\n";
                write.write(row);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
         write.flush();
         write.close();
        }
            
    }
    public void readReq(ObservableList<ReqCheck> data) {
        String File = "CheckList.csv";
        String Delimiter = ",";
        
        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader(File));
            
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(Delimiter, -1);
                ReqCheck recorder = new ReqCheck(fields[0], fields[1]);
                data.add(recorder);
            } 
        }catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
