package ba.unsa.etf.rpr;

import javafx.beans.binding.ObjectExpression;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrzavaController {
    public TextField fieldNaziv;
    public ChoiceBox<Grad> choiceGrad;
    public RadioButton radioIsti;
    public RadioButton radioDrugi;
    public ChoiceBox<Grad> choiceGradNajveci;
    private Drzava drzava;
    private ToggleGroup group = new ToggleGroup();

    private ObservableList<Grad> listGradovi;
    private ObservableList<Drzava> listDrzave;
    private ObservableList<Grad> listNajveciGradovi;

    public DrzavaController(Drzava drzava, ArrayList<Grad> gradovi) {
        this.drzava = drzava;
        listGradovi = FXCollections.observableArrayList(gradovi);

    }

    @FXML
    public void initialize() {
        choiceGrad.setItems(listGradovi);
        choiceGradNajveci.setItems(listGradovi);
        radioIsti.setToggleGroup(group);
        radioDrugi.setToggleGroup(group);

        if (drzava != null) {
            fieldNaziv.setText(drzava.getNaziv());
            choiceGrad.getSelectionModel().select(drzava.getGlavniGrad());
            if (drzava.getNajveciGrad().equals(drzava.getGlavniGrad())) {
                radioIsti.setSelected(true);
            } else {
                radioDrugi.setSelected(true);
            }
            choiceGradNajveci.getSelectionModel().select(drzava.getNajveciGrad());
        } else {
            choiceGrad.getSelectionModel().selectFirst();
            choiceGradNajveci.getSelectionModel().select(choiceGrad.getValue());
        }
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean sveOk = true;

        if (fieldNaziv.getText().trim().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }

        if (!sveOk) return;

        if (drzava == null) drzava = new Drzava();
        drzava.setNaziv(fieldNaziv.getText());
        drzava.setGlavniGrad(choiceGrad.getSelectionModel().getSelectedItem());
        radioIsti.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                choiceGradNajveci.setDisable(true);
        });
        Grad najveciGrad;
        radioDrugi.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                choiceGradNajveci.setDisable(false);
                choiceGradNajveci.getSelectionModel().select(choiceGrad.getValue());
            }
        });
        if (!choiceGradNajveci.isDisabled()) {
            najveciGrad = choiceGradNajveci.getSelectionModel().getSelectedItem();
            drzava.setNajveciGrad(najveciGrad);
        } else {
            drzava.setNajveciGrad(choiceGrad.getSelectionModel().getSelectedItem());
        }
        closeWindow();
    }

    public void clickCancel(ActionEvent actionEvent) {
        drzava = null;
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }
}
