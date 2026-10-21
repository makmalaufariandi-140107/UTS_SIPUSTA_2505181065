package layanan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.Koleksi;

public class EksporKatalog {
    public static void eksporKeFile(List<Koleksi> daftarKoleksi, String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            writer.write("====================================================================================================");
            writer.newLine();
            writer.write(String.format("| %-8s | %-32s | %-12s | %-18s | %-12s |", 
                    "KODE", "JUDUL", "TAHUN TERBIT", "STATUS KELAYAKAN", "STATUS PINJAM"));
            writer.newLine();
            writer.write("====================================================================================================");
            writer.newLine();

            for (Koleksi k : daftarKoleksi) {
                String kelayakan = k.isLayakDipinjam() ? "Layak Dipinjam" : "Tidak Layak";
                String dipinjam = k.isSedangDipinjam() ? "Dipinjam" : "Tersedia";
                
                writer.write(String.format("| %-8s | %-32s | %-12d | %-18s | %-12s |", 
                        k.getKodeKoleksi(), 
                        k.getJudul(), 
                        k.getTahunTerbit(), 
                        kelayakan, 
                        dipinjam));
                writer.newLine();
            }

            writer.write("====================================================================================================");
            writer.newLine();
            System.out.println("SUCCESS: Katalog berhasil diekspor dengan rapi ke berkas '" + namaFile + "'.");
        } catch (IOException e) {
            System.err.println("ERROR: Gagal menulis berkas katalog: " + e.getMessage());
        }
    }
}