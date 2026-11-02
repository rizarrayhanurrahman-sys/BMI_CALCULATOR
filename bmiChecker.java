import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class bmiChecker extends Application {

    private final ObservableList<String> riwayat = FXCollections.observableArrayList();

    private final RadioButton rbMetrik = new RadioButton("Metrik (kg, cm)");
    private final RadioButton rbImperial = new RadioButton("Imperial (lb, inci)");

    private final Label lblBerat = new Label("Berat (kg):");
    private final Label lblTinggi = new Label("Tinggi (cm):");
    private final TextField tfBerat = new TextField();
    private final TextField tfTinggi = new TextField();

    private final Label lblHasil = new Label("Masukkan data lalu tekan Hitung");

    @Override
    public void start(Stage stage) {
        Label lblJudul = new Label("Kalkulator BMI + Riwayat");
        lblJudul.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Pilihan sistem satuan
        ToggleGroup grup = new ToggleGroup();
        rbMetrik.setToggleGroup(grup);
        rbImperial.setToggleGroup(grup);
        rbMetrik.setSelected(true);
        grup.selectedToggleProperty().addListener((obs, lama, baru) -> gantiSatuan());
        HBox boxSatuan = new HBox(15, rbMetrik, rbImperial);
        boxSatuan.setAlignment(Pos.CENTER);

        // Baris input
        lblBerat.setMinWidth(100);
        lblTinggi.setMinWidth(100);
        tfBerat.setPromptText("contoh: 65");
        tfTinggi.setPromptText("contoh: 170");
        HBox barisBerat = new HBox(10, lblBerat, tfBerat);
        HBox barisTinggi = new HBox(10, lblTinggi, tfTinggi);
        barisBerat.setAlignment(Pos.CENTER);
        barisTinggi.setAlignment(Pos.CENTER);

        // Tombol
        Button btnHitung = new Button("Hitung");
        Button btnHapus = new Button("Hapus Riwayat");
        btnHitung.setDefaultButton(true);
        btnHitung.setOnAction(e -> hitung());
        btnHapus.setOnAction(e -> riwayat.clear());
        HBox boxTombol = new HBox(10, btnHitung, btnHapus);
        boxTombol.setAlignment(Pos.CENTER);

        // Hasil dan riwayat
        lblHasil.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        Label lblRiwayat = new Label("Riwayat perhitungan:");
        ListView<String> listRiwayat = new ListView<>(riwayat);
        listRiwayat.setPrefHeight(150);

        VBox root = new VBox(12, lblJudul, boxSatuan, barisBerat, barisTinggi,
                boxTombol, lblHasil, lblRiwayat, listRiwayat);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        stage.setTitle("Kalkulator BMI");
        stage.setScene(new Scene(root, 420, 520));
        stage.show();
    }

    // Mengubah label dan petunjuk sesuai sistem satuan yang dipilih
    private void gantiSatuan() {
        if (rbMetrik.isSelected()) {
            lblBerat.setText("Berat (kg):");
            lblTinggi.setText("Tinggi (cm):");
            tfBerat.setPromptText("contoh: 65");
            tfTinggi.setPromptText("contoh: 170");
        } else {
            lblBerat.setText("Berat (lb):");
            lblTinggi.setText("Tinggi (inci):");
            tfBerat.setPromptText("contoh: 143");
            tfTinggi.setPromptText("contoh: 67");
        }
        tfBerat.clear();
        tfTinggi.clear();
    }

    private void hitung() {
        try {
            double berat = Double.parseDouble(tfBerat.getText().trim().replace(',', '.'));
            double tinggi = Double.parseDouble(tfTinggi.getText().trim().replace(',', '.'));
            if (berat <= 0 || tinggi <= 0) {
                throw new NumberFormatException("nilai harus positif");
            }

            double bmi;
            String unitBerat;
            String unitTinggi;
            if (rbMetrik.isSelected()) {
                double tinggiM = tinggi / 100.0;
                bmi = berat / (tinggiM * tinggiM);
                unitBerat = "kg";
                unitTinggi = "cm";
            } else {
                bmi = 703 * berat / (tinggi * tinggi);
                unitBerat = "lb";
                unitTinggi = "inci";
            }

            String kategori = kategoriBmi(bmi);
            lblHasil.setText(String.format("BMI: %.1f (%s)", bmi, kategori));
            riwayat.add(0, String.format("BMI %.1f - %s | %.1f %s, %.1f %s",
                    bmi, kategori, berat, unitBerat, tinggi, unitTinggi));
        } catch (NumberFormatException ex) {
            Alert peringatan = new Alert(Alert.AlertType.WARNING);
            peringatan.setTitle("Input tidak valid");
            peringatan.setHeaderText(null);
            peringatan.setContentText("Masukkan angka yang valid dan lebih dari 0.");
            peringatan.showAndWait();
        }
    }

    private String kategoriBmi(double bmi) {
        if (bmi < 18.5) {
            return "Kurus";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Berlebih";
        }
        return "Obesitas";
    }

    public static void main(String[] args) {
        launch(args);
    }
}