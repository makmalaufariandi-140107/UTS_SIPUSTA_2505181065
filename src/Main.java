import model.*;
import layanan.*;
import eksepsi.*;

public class Main {
    public static void main(String[] args) {
        KatalogPerpustakaan katalog = new KatalogPerpustakaan();

        System.out.println("--> Menambahkan 6 Data Uji Wajib ke Katalog...");
        
        // 1. BukuCetak 1
        katalog.tambahKoleksi(new BukuCetak("BK-001", "Java How to Program", 2022, 620, "A-12", false));
        // 2. BukuCetak 2
        katalog.tambahKoleksi(new BukuCetak("BK-002", "Basis Data Relasional", 2021, 410, "B-04", true));
        // 3. Majalah 1
        katalog.tambahKoleksi(new Majalah("MJ-001", "Info Teknologi Edisi 88", 2026, 88, "Agustus", "B-04", true));
        // 4. Majalah 2
        katalog.tambahKoleksi(new Majalah("MJ-002", "Info Teknologi Edisi 87", 2026, 87, "Juli", "B-04", false));
        // 5. BerkasDigital 1
        katalog.tambahKoleksi(new BerkasDigital("BD-001", "Panduan Pemrograman Java", 2024, 15.0, "unduh.polmed.ac.id/bd001"));
        // 6. BerkasDigital 2
        katalog.tambahKoleksi(new BerkasDigital("BD-002", "Jurnal Rekayasa Perangkat", 2025, 8.0, "unduh.polmed.ac.id/bd002"));

        // Menampilkan status katalog awal (Polimorfisme)
        System.out.println("\n>>> STATUS KATALOG SEBELUM DIURUTKAN <<<");
        katalog.cetakStatus();

        // Pengurutan dengan Comparator UrutLayakLaluJudul
        System.out.println("\n--> Mengurutkan Katalog (Layak Dipinjam Dulu, Lalu Judul)...");
        katalog.urut(new UrutLayakLaluJudul());
        
        System.out.println("\n>>> STATUS KATALOG SETELAH DIURUTKAN <<<");
        katalog.cetakStatus();

        // Pengujian Exception Handling
        System.out.println("\n>>> MENGUJI FITUR PEMINJAMAN DAN EKSEPSI <<<");
        
        // Pengujian 1: Meminjam koleksi yang layak
        try {
            System.out.println("\n1. Meminjam 'BD-001' (Layak Pinjam):");
            katalog.pinjam("BD-001");
        } catch (PustakaException e) {
            System.out.println("EKSEPSI TERTANGKAP: " + e.getMessage());
        }

        // Pengujian 2: Meminjam koleksi yang sudah dipinjam
        try {
            System.out.println("\n2. Meminjam kembali 'BD-001' (Sedang Dipinjam):");
            katalog.pinjam("BD-001");
        } catch (PustakaException e) {
            System.out.println("EKSEPSI TERTANGKAP: " + e.getMessage());
        }

        // Pengujian 3: Meminjam koleksi tidak layak (Buku sedang diperbaiki)
        try {
            System.out.println("\n3. Meminjam 'BK-002' (Sedang Diperbaiki):");
            katalog.pinjam("BK-002");
        } catch (PustakaException e) {
            System.out.println("EKSEPSI TERTANGKAP: " + e.getMessage());
        }

        // Pengujian 4: Meminjam koleksi tidak layak (Majalah edisi terbaru)
        try {
            System.out.println("\n4. Meminjam 'MJ-001' (Edisi Terbaru):");
            katalog.pinjam("MJ-001");
        } catch (PustakaException e) {
            System.out.println("EKSEPSI TERTANGKAP: " + e.getMessage());
        }

        System.out.println("\n>>> MENGEKSPOR KATALOG KE BERKAS TEKS <<<");
        EksporKatalog.eksporKeFile(katalog.getDaftarKoleksi(), "katalog.txt");
    }
}