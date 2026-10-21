package layanan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Koleksi;
import model.BukuCetak;
import model.Majalah;
import eksepsi.KoleksiSedangDipinjamException;
import eksepsi.KoleksiTidakLayakException;

public class KatalogPerpustakaan {
    private List<Koleksi> daftarKoleksi;

    public KatalogPerpustakaan() {
        this.daftarKoleksi = new ArrayList<>();
    }

    public void tambahKoleksi(Koleksi koleksi) {
        daftarKoleksi.add(koleksi);
    }

    public List<Koleksi> getDaftarKoleksi() {
        return daftarKoleksi;
    }

    public void cetakStatus() {
        System.out.println("==========================================================================================");
        System.out.println("                               STATUS KATALOG PERPUSTAKAAN                                ");
        System.out.println("==========================================================================================");
        for (Koleksi k : daftarKoleksi) {
            String kelayakan = k.isLayakDipinjam() ? "[LAYAK PINJAM]" : "[TIDAK LAYAK PINJAM]";
            System.out.printf("%-20s %s%n", kelayakan, k.deskripsi());
        }
        System.out.println("==========================================================================================");
    }

    public void urut(Comparator<Koleksi> comparator) {
        daftarKoleksi.sort(comparator);
    }

    public void pinjam(String kodeKoleksi) throws KoleksiSedangDipinjamException, KoleksiTidakLayakException {
        Koleksi target = null;
        for (Koleksi k : daftarKoleksi) {
            if (k.getKodeKoleksi().equalsIgnoreCase(kodeKoleksi)) {
                target = k;
                break;
            }
        }

        if (target == null) {
            System.out.println("Koleksi dengan kode " + kodeKoleksi + " tidak ditemukan.");
            return;
        }

        if (target.isSedangDipinjam()) {
            throw new KoleksiSedangDipinjamException(target.getJudul());
        }

        if (!target.isLayakDipinjam()) {
            String alasan = "tidak memenuhi syarat peminjaman";
            if (target instanceof BukuCetak bc && bc.isSedangDiperbaiki()) {
                alasan = "sedang dalam proses perbaikan/restorasi";
            } else if (target instanceof Majalah m && m.isEdisiTerbaru()) {
                alasan = "merupakan edisi terbaru yang hanya boleh dibaca di tempat";
            }
            throw new KoleksiTidakLayakException(target.getJudul(), alasan);
        }

        target.setSedangDipinjam(true);
        System.out.println("BERHASIL: Koleksi '" + target.getJudul() + "' berhasil dipinjam.");
    }
}