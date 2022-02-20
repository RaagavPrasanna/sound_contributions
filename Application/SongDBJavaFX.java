package Application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.*; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SongDBJavaFX extends Application {
    SongDBApplication database1 = new SongDBApplication();

    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root,800, 600);
        scene.setFill(Color.ALICEBLUE);
        stage.setTitle("SongDBJavaFX");
        stage.setScene(scene); 
        stage.show();

        //Login elements
        HBox hLogin = new HBox();
        VBox vLogin = new VBox();
        TextArea taLoginMessages = new TextArea("Login to access the database");
        taLoginMessages.setPrefHeight(75);
        TextField tfUsername = new TextField("A2036159");
        TextField tfPassword = new TextField("Bubbulu2003");
        Button btnLogin = new Button("Login");

        //Creating the pillar
        HBox hMainContent = new HBox();
        VBox vPageContent = new VBox();

        VBox vRetrieve = new VBox();
        VBox vUpdate = new VBox();
        VBox vCreate = new VBox();

        HBox hInsertContributor = new HBox();
        HBox hInsertRecording = new HBox();
        HBox hRetrieveOptions = new HBox();
        VBox vGetContributors = new VBox();
        VBox vGetDetails = new VBox();
        VBox vGetAll = new VBox();

        HBox hUpdateOptions = new HBox();

        //Creating elements

        //Creating insert elements
        TextArea taInsertTitle = new TextArea("INSERT DATA");
        TextField tfRecordingID = new TextField("recording ID"); 
        TextField tfRecordingDate = new TextField("Recording date");
        TextField tfRecordingDuration = new TextField("Recording duration");
        TextField tfContributorRecID = new TextField("Recording ID");
        TextField tfContributorRole = new TextField("Contributor Role");
        TextField tfContributorName = new TextField("Contributor Name");
        TextArea taInsertMessage = new TextArea();
        TextArea queryResult = new TextArea();

        Button btnInsertContributor = new Button("Insert Contributor");
        Button btnInsertRecording = new Button("Insert Recording");

        Label lbGetContributors = new Label("Get contributors roles by... ");
        Label lbGetDetails = new Label("Get details by... ");
        Label lbGetAll = new Label("Get all ... ");        

        //Creating update elements
        TextArea taUpdateTitle = new TextArea("UPDATE DATA");
        TextField oldValue = new TextField("old Value");
        TextField updateValue = new TextField("new Value");

        Button btnUpdate = new Button("Update");
        TextArea updateMessage = new TextArea();

        //Creating retrieve elements
        TextArea taRetrieveTitle = new TextArea("RETRIEVE DATA");//Welcome to the Song Contributions Manager Application
        TextField inpVal = new TextField("---ENTER KEY HERE---");

        Button btnRecKeySub = new Button("recording ID");
        Button btnCompKeySub = new Button("compName");
        Button btnRecDetailsSub = new Button("recording ID"); 
        Button btnCompDetailsSub = new Button("compilation name");
        Button btnCreatorContributionSub = new Button("implications by artist name");
        Button btnCreatorAllRolesSub = new Button("roles by artist name");

        //choice box generation
        ChoiceBox<String> cbUpdateChoicesTable = new ChoiceBox<>();
        cbUpdateChoicesTable.setValue("artist");
        cbUpdateChoicesTable.getItems().addAll("artist", "role", "distribution", "distribution_release", "compilation", "recording", "compilation_component"); 
        ChoiceBox<String> cbUpdateChoicesColumn = new ChoiceBox<>();
        setColumnChoices(cbUpdateChoicesColumn, cbUpdateChoicesTable);

        //style
        tfRecordingDate.setPrefWidth(125);
        tfRecordingDuration.setPrefWidth(125);
        tfRecordingID.setPrefWidth(125);
        tfContributorName.setPrefWidth(125);
        tfContributorRecID.setPrefWidth(125);
        tfContributorRole.setPrefWidth(125);
        oldValue.setPrefWidth(75);
        updateValue.setPrefWidth(75);
        inpVal.setPrefWidth(150);
        taRetrieveTitle.setPrefHeight(50);
        taRetrieveTitle.setPrefWidth(150);
        taInsertTitle.setPrefWidth(150);
        taInsertTitle.setPrefHeight(50);
        taUpdateTitle.setPrefWidth(150);
        taUpdateTitle.setPrefHeight(50);
        cbUpdateChoicesColumn.setPrefWidth(200);
        cbUpdateChoicesTable.setPrefWidth(150);
        btnUpdate.setPrefWidth(500);
        btnLogin.setPrefWidth(500);
        tfUsername.setPrefWidth(250);
        tfPassword.setPrefWidth(250);
        updateMessage.setPrefHeight(100);
        btnInsertContributor.setPrefWidth(150);
        btnInsertRecording.setPrefWidth(150);

        //appending
        root.getChildren().addAll(vPageContent);
        vPageContent.getChildren().addAll(hMainContent);
        hMainContent.getChildren().addAll(vRetrieve, vUpdate, vCreate);
        //inserting elements
        vCreate.getChildren().addAll(taInsertTitle, hInsertRecording, hInsertContributor, taInsertMessage);
        hInsertContributor.getChildren().addAll(tfContributorName, tfContributorRole, tfContributorRecID, btnInsertContributor);
        hInsertRecording.getChildren().addAll(tfRecordingID, tfRecordingDuration, tfRecordingDate, btnInsertRecording);
        //retrieving elements
        vRetrieve.getChildren().addAll(taRetrieveTitle, inpVal, hRetrieveOptions, queryResult);
        hRetrieveOptions.getChildren().addAll(vGetContributors, vGetDetails, vGetAll);
        vGetContributors.getChildren().addAll(lbGetContributors, btnRecKeySub, btnCompKeySub);
        vGetDetails.getChildren().addAll(lbGetDetails, btnRecDetailsSub, btnCompDetailsSub);
        vGetAll.getChildren().addAll(lbGetAll, btnCreatorAllRolesSub, btnCreatorContributionSub);
        //updating elements
        vUpdate.getChildren().addAll(taUpdateTitle, hUpdateOptions, updateMessage, btnUpdate, taLoginMessages, vLogin);
        hUpdateOptions.getChildren().addAll(oldValue, updateValue, cbUpdateChoicesTable, cbUpdateChoicesColumn); 
        //login
        vLogin.getChildren().addAll(hLogin, btnLogin);
        hLogin.getChildren().addAll(tfUsername, tfPassword);

        ArrayList <Button> buttons = new ArrayList<>(Arrays.asList(btnRecKeySub, btnCompKeySub, btnRecDetailsSub, btnCompDetailsSub, btnCreatorAllRolesSub, btnCreatorContributionSub));
        setStyles(queryResult, taRetrieveTitle, buttons);

        //setting the action events for the buttons
        login(tfUsername, tfPassword, btnLogin, taLoginMessages);
        setRecIdSubAction(tfUsername, tfPassword, btnLogin, taLoginMessages, btnRecKeySub, queryResult, inpVal, taRetrieveTitle);
        setCompKeySubAction(tfUsername, tfPassword, btnLogin, taLoginMessages, btnCompKeySub, queryResult, inpVal, taRetrieveTitle);
        setRecDetailsSubAction(tfUsername, tfPassword, btnLogin, taLoginMessages, btnRecDetailsSub, queryResult, inpVal, taRetrieveTitle);
        setCompDetailsSubAction(tfUsername, tfPassword, btnLogin, taLoginMessages, btnCompDetailsSub, queryResult, inpVal, taRetrieveTitle);
        setCreatorAllRolesSubAction(tfUsername, tfPassword, btnLogin, taLoginMessages, btnCreatorAllRolesSub, queryResult, inpVal, taRetrieveTitle);
        setCreatorAllImplicationsSubAction(tfUsername, tfPassword, btnLogin, taLoginMessages, btnCreatorContributionSub, queryResult, inpVal, taRetrieveTitle);
        setUpdateAction(tfUsername, tfPassword, btnLogin, taLoginMessages, oldValue, updateValue, cbUpdateChoicesTable, btnUpdate, updateMessage, cbUpdateChoicesColumn);
        setRecInsAction(tfUsername, tfPassword, btnLogin, taLoginMessages, tfRecordingID, tfRecordingDuration, tfRecordingDate, btnInsertRecording, taInsertMessage);

        setUpdateEvent(tfUsername, tfPassword, btnLogin, taLoginMessages, cbUpdateChoicesTable, btnUpdate, updateMessage, cbUpdateChoicesColumn);
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

    public void setUpdateEvent(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages, ChoiceBox<String> cbUpdateChoicesTable, Button btnUpdate, TextArea updateMessage, ChoiceBox<String> cbUpdateChoicesColumn) {
        cbUpdateChoicesTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                setColumnChoices(cbUpdateChoicesColumn, cbUpdateChoicesTable);
            }
        });
    }

    public void login(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages) {
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    database1.login(tfUsername.getText(), tfPassword.getText());
                    taLoginMessages.setText("Login successful, welcome " + database1.getUsername());
                } catch (SQLException error) {
                    taLoginMessages.setText("Wrong credentials, try again");
                }
            }
        });
    }

    public void setRecInsAction(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages, TextField tfRecordingID, TextField tfRecordingDuration, TextField tfRecordingDate, Button btnInsertRecording, TextArea taInsertMessage) {
        btnInsertRecording.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                    database1.insertRecording(tfRecordingID.getText(), tfRecordingDuration.getText(), tfRecordingDate.getText());
                    taInsertMessage.setText("Recording: " +tfRecordingID.getText() +" succesfully added");
                } 
                catch (SQLException se) {
                    taInsertMessage.setText(se.getMessage());
                }
                catch (Exception all) {
                    taInsertMessage.setText(":(");
                    all.printStackTrace();
                }
            }
        });
    }

    public void setContributorInsAction(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages, TextField tfContributorRecID, TextField tfContributorRole, TextField tfContributorName, Button btnInsertContributor, TextArea taInsertMessage) {
        btnInsertContributor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                taInsertMessage.setText("");
                String contributorName = tfContributorName.getText().replaceAll("[^a-zA-Z0-9]", " ");
                String roleName = tfContributorRole.getText().replaceAll("[^a-zA-Z0-9]", " ");
                String recId = tfContributorRecID.getText().replaceAll("[^a-zA-Z0-9]", " ");
                boolean recordingExists = false;
                try {
                    login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                    database1.insertContributorName(tfContributorName.getText());
                    taInsertMessage.setText(taInsertMessage.getText() +"New artist: " +contributorName +", added." +"\n");
                } catch (SQLException se1) {
                    taInsertMessage.setText(taInsertMessage.getText() +"Artist: " +contributorName +", may already exist." +"\n");
                }
                try {
                    login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                    database1.insertContributorRole(roleName);
                    taInsertMessage.setText(taInsertMessage.getText() +"New role: " +roleName +", added." +"\n"); 
                } catch (SQLException se2) {
                    taInsertMessage.setText(taInsertMessage.getText() +"Role: " +roleName +", may already exist." +"\n");
                }
                try {
                    database1.insertArtistRole(contributorName, roleName);
                    taInsertMessage.setText(taInsertMessage.getText() +"Artist: " +contributorName +", with role: " +roleName +", added." +"\n");
                }
                catch (SQLException se3) {
                    taInsertMessage.setText(taInsertMessage.getText() +"Artist: " +contributorName +", with role: " +roleName +", may already exist." +"\n");
                }
                try {
                    recordingExists = database1.recIdExists(recId);
                }
                catch (SQLException se) {
                    taInsertMessage.setText(taInsertMessage.getText() +"invalid contributor recordingId entry" +"\n");
                }
                if(!recordingExists) {
                    taInsertMessage.setText(taInsertMessage.getText() +"Recording Id: " + recId +", does not exist, please create a recording with this id and then re-add the contriubtor" +"\n");
                }
                else {
                    try {
                        database1.insertRecordingArtistRole(contributorName, roleName, recId);
                        taInsertMessage.setText(taInsertMessage.getText() +"Success! Contributor inserted for recording id: " + recId +"\n");
                    } catch (SQLException se4) {
                        taInsertMessage.setText(taInsertMessage.getText() +"Invalid entry, either the input contributor for this recording already exists, or their was an invalid input."
                            +se4.getMessage() +"\n");
                    }
                }
            }
        });
    }

    public void setColumnChoices(ChoiceBox<String> cbUpdateChoicesColumn, ChoiceBox<String> cbUpdateChoicesTable) {
        cbUpdateChoicesColumn.getItems().clear();
        String table = cbUpdateChoicesTable.getSelectionModel().getSelectedItem();
        if(table.equals("artist") || table.equals("role") || table.equals("distribution")) {
            cbUpdateChoicesColumn.getItems().addAll(table+"_name");
        }
        else if (table.equals("distribution_release")) {
            cbUpdateChoicesColumn.getItems().addAll("distribution_release_date", "distribution_label", "distribution_market");
        }
        else if(table.equals("compilation")) {
            cbUpdateChoicesColumn.getItems().addAll("compilation_name", "compilation_duration");
        }
        else if(table.equals("recording")) {
            cbUpdateChoicesColumn.getItems().addAll("recording_duration");
        }
        else if(table.equals("compilation_component")) {
            cbUpdateChoicesColumn.getItems().addAll("compilation_component_offset", "compilation_component_duration", "component_offset", "component_duration");
        }
        else {
            // System.out.println("This is not supposed to happen, the selected table doesnt correspond to anything");
            throw new IllegalArgumentException("programmer error fix your column choices");
        }
        cbUpdateChoicesColumn.getSelectionModel().selectFirst();
    }
    //Update events handler
    public void setUpdateAction(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages, TextField oldValue, TextField newValue, ChoiceBox<String> cbUpdateChoicesTable, Button btnUpdate, TextArea updateMessage, ChoiceBox<String> cbUpdateChoicesColumn) {
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String table = cbUpdateChoicesTable.getSelectionModel().getSelectedItem();
                String column = cbUpdateChoicesColumn.getSelectionModel().getSelectedItem();
                try {
                    login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                    int count = database1.updateValue(oldValue.getText(), newValue.getText(), table, column);
                    if(count == 0) {
                        throw new IllegalArgumentException("Updated failed");
                    }
                    else {
                        updateMessage.appendText("\nSuccessfully updated the " + column + " in " + table + " from " + oldValue.getText() + " to " + newValue.getText());
                    }
                } catch (IllegalArgumentException error) {
                    updateMessage.appendText("\nFAILED TO UPDATE PLEASE CHECK YOUR INPUTS AND TRY AGAIN");
                } catch (SQLException sqle) {
                    updateMessage.appendText("\nSQL EXCEPTION ");
                }
            }
        });
    }
    //Retrieve events handler
    public void setCreatorAllRolesSubAction(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages, Button btnCreatorAllRolesSub, TextArea queryResult, TextField inpVal, TextArea msgBar) {
        btnCreatorAllRolesSub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                    queryResult.setText(database1.getContributorRoles(inpVal.getText()));
                    queryResult.appendText("\nArtist and roles details retrieved for ARTIST_KEY: " +inpVal.getText());
                } catch (IllegalArgumentException iae) {
                    queryResult.setText(iae.getMessage());
                    queryResult.appendText("\n:(");
                } catch(SQLException se) {
                    queryResult.setText("Invalid query");
                    queryResult.appendText("\n:(");
                } catch(Exception all) {
                    queryResult.setText("There was a run time error: " +all.getMessage() +". Please see console for more details");
                    queryResult.appendText("\n:(");
                } 
            }
        });
    }

    public void setCreatorAllImplicationsSubAction(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages, Button btnCreatorContributionSub, TextArea queryResult, TextField inpVal, TextArea msgBar) {
        btnCreatorContributionSub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                    queryResult.setText(database1.getImplcsByArt(inpVal.getText()));
                    queryResult.appendText("\nArtist and implications details retrieved for ARTIST_KEY: " +inpVal.getText());
                } catch (IllegalArgumentException iae) {
                    queryResult.setText(iae.getMessage());
                    queryResult.appendText("\n:(");
                } catch(SQLException se) {
                    queryResult.setText("Invalid query");
                    queryResult.appendText("\n:(");
                } catch(Exception all) {
                    queryResult.setText("There was a run time error: " +all.getMessage() +". Please see console for more details");
                    queryResult.appendText("\n:(");
                } 
            }
        });
    }

    public void setCompDetailsSubAction(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages, Button btnCompDetailsSub, TextArea queryResult, TextField inpVal, TextArea msgBar) {
        btnCompDetailsSub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                    queryResult.setText(database1.getCompilationDetails(inpVal.getText()));
                    queryResult.appendText("\nCompilation details retrieved for COMPILATION_KEY: " +inpVal.getText());
                } catch (IllegalArgumentException iae) {
                    queryResult.setText(iae.getMessage());
                    queryResult.appendText("\n:(");
                } catch(SQLException se) {
                    queryResult.setText("Invalid query");
                    queryResult.appendText("\n:(");
                } catch(Exception all) {
                    queryResult.setText("There was a run time error: " +all.getMessage() +". Please see console for more details");
                    queryResult.appendText("\n:(");
                } 
            }
        });
    }
    public void setRecDetailsSubAction(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages, Button btnRecDetailsSub, TextArea queryResult, TextField inpVal, TextArea msgBar) {
        btnRecDetailsSub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                    queryResult.setText(database1.getRecordingDetails(inpVal.getText()));
                    queryResult.appendText("\nRecording details retrieved for RECORDING_KEY: " +inpVal.getText());
                } catch (IllegalArgumentException iae) {
                    queryResult.setText(iae.getMessage());
                    queryResult.appendText("\n:(");
                } catch(SQLException se) {
                    queryResult.setText("Invalid query");
                    queryResult.appendText("\n:(");
                } catch(Exception all) {
                    queryResult.setText("There was a run time error: " +all.getMessage() +". Please see console for more details");
                    queryResult.appendText("\n:(");
                } 
            }
        });
    }
    public void setCompKeySubAction(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages, Button btnCompKeySub, TextArea queryResult, TextField inpVal, TextArea msgBar) {
        btnCompKeySub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                    queryResult.setText(database1.getArtistsByCompName(inpVal.getText()));
                    queryResult.appendText("\nArtists data retrieved for COMPILATION_NAME: " +inpVal.getText());
                } catch (IllegalArgumentException iae) {
                    queryResult.setText(iae.getMessage());
                    queryResult.appendText("\n:(");
                }
                catch(SQLException se) {
                    queryResult.setText("Invalid query");
                    queryResult.appendText("\n:(");
                }
                catch(Exception all) {
                    queryResult.setText("There was a run time error: " +all.getMessage() +". Please see console for more details");
                    queryResult.appendText("\n:(");
                }   
            }
        });
    }
    public void setRecIdSubAction(TextField tfUsername, TextField tfPassword, Button btnLogin, TextArea taLoginMessages, Button btnRecKeySub, TextArea queryResult, TextField inpVal, TextArea msgBar) {
        btnRecKeySub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    login(tfUsername, tfPassword, btnLogin, taLoginMessages);
                    queryResult.setText(database1.getArtistsByRecId(inpVal.getText()));
                    queryResult.appendText("\nArtists data retrieved for RECORDING_KEY: " +inpVal.getText());
                } catch (IllegalArgumentException iae) {
                    queryResult.setText("illegal argument exception");
                }
                catch(SQLException se) {
                    queryResult.setText("Invalid query");
                    queryResult.appendText("\n:(");
                }
                catch(Exception all) {
                    msgBar.setText("There was a run time error: " +all.getMessage() +". Please see console for more details");
                    queryResult.appendText("\n:(");
                }   
            }
        });
    }
    public void setStyles(TextArea queryResult, TextArea msgBar, ArrayList <Button> buttons) {
        for (Button button : buttons) {
            button.setPrefWidth(150);
        }
        queryResult.setPrefWidth(450);
    }
}