package io.github.gravetii.controller;

import io.github.gravetii.pojo.GameTime;
import io.github.gravetii.store.StoreUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

public class GameTimeController implements FxController {

  private Stage stage;
  private GameTimeValidator validator;

  @FXML private TextField minutesTxtField;
  @FXML private TextField secondsTxtField;
  @FXML private Label validationLbl;
  @FXML private Button okBtn;
  @FXML private Button cancelBtn;

  public GameTimeController(Stage stage) {
    this.stage = stage;
    this.validator = new GameTimeValidator();
  }

  @FXML
  public void initialize() {
    GameTime gameTime = StoreUtility.getGameTime();
    this.minutesTxtField.setText(Integer.toString(gameTime.getMinutes()));
    this.secondsTxtField.setText(Integer.toString(gameTime.getSeconds()));
  }

  @FXML
  public void ok(ActionEvent event) {
    GameTime gameTime = this.validate();
    if (gameTime != null) {
      StoreUtility.setGameTime(gameTime);
      this.stage.close();
    } else {
      this.validationLbl.setText("Invalid value!");
    }
  }

  @FXML
  public void cancel(ActionEvent event) {
    this.stage.close();
  }

  private GameTime validate() {
    String minText = this.minutesTxtField.getText();
    String secText = this.secondsTxtField.getText();
    return this.validator.validate(new Pair<>(minText, secText));
  }
}
