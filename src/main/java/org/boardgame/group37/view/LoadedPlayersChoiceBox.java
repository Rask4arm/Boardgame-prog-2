package org.boardgame.group37.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.boardgame.group37.controller.MainController;
import org.boardgame.group37.model.player.Player;

import java.util.ArrayList;

/**
 * LoadedPlayersChoiceBox class
 * This class is responsible for creating a choice box for loaded players.
 */
public class LoadedPlayersChoiceBox {
    public static ChoiceBox LoadedPlayersChoiceBox(ArrayList<Player> loadedPlayers, ChoiceBox colorChoiceBoxP1, TextField textFieldPlayer1, MainController mainController) {
        ChoiceBox playerChoiceBox = new ChoiceBox(FXCollections.observableArrayList(loadedPlayers));

        playerChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                // set the text for the label to the selected color
                if (new_value.intValue() == -1) {
                    return;
                }
                Player player = loadedPlayers.get(new_value.intValue());
                mainController.getPlayerController().changePlayerName(0, player.getName());
                mainController.getPlayerController().changePlayerColor(0, player.getColor());
                textFieldPlayer1.setText(player.getName());
                colorChoiceBoxP1.setValue(player.getColor());
            }
        });
        return playerChoiceBox;
    }
}
