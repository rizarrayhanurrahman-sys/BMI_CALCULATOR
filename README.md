Kalkulator BMI

Aplikasi desktop sederhana untuk menghitung Body Mass Index (BMI), dibuat menggunakan Java dan JavaFX.

Fitur :

- Pilihan sistem satuan: metrik (kg, cm) atau imperial (lb, inci)
- Menghitung nilai BMI dan menampilkan kategorinya
- Riwayat perhitungan yang bisa dihapus
- Validasi input dengan jendela peringatan
- Tombol Enter untuk menjalankan perhitungan

Kategori BMI :

| BMI         | Kategori |
|-------------|----------|
| < 18,5      | Kurus    |
| 18,5 - 24,9 | Normal   |
| 25 - 29,9   | Berlebih |
| >= 30       | Obesitas |

Rumus :

- Metrik: BMI = berat (kg) / (tinggi (m) x tinggi (m))
- Imperial: BMI = 703 x berat (lb) / (tinggi (inci) x tinggi (inci))

Teknologi :

- Java
- JavaFX
- NetBeans IDE

Cara menjalankan :

1. Pastikan JDK dan library JavaFX sudah terpasang.
2. Buka project di NetBeans.
3. Tambahkan library JavaFX ke project, lalu atur VM Options:
   `--module-path "PATH_JAVAFX_SDK\lib" --add-modules javafx.controls`
4. Jalankan file `bmiChecker.java`.

Pembuat :
Rizar Rayhanurrahman