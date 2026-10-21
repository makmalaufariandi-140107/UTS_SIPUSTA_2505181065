package layanan;

import java.util.Comparator;
import model.Koleksi;

public class UrutLayakLaluJudul implements Comparator<Koleksi> {
    @Override
    public int compare(Koleksi k1, Koleksi k2) {
        if (k1.isLayakDipinjam() != k2.isLayakDipinjam()) {
            return k1.isLayakDipinjam() ? -1 : 1;
        }
        return k1.getJudul().compareToIgnoreCase(k2.getJudul());
    }
}