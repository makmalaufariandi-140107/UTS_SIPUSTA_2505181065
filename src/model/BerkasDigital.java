package model;

public class BerkasDigital extends Koleksi {
    private double ukuranMB;
    private String tautanUnduh;

    public BerkasDigital(String kodeKoleksi, String judul, int tahunTerbit, double ukuranMB, String tautanUnduh) {
        super(kodeKoleksi, judul, tahunTerbit);
        this.ukuranMB = ukuranMB;
        this.tautanUnduh = tautanUnduh;
    }

    @Override
    public boolean isLayakDipinjam() {
        return true;
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + String.format(" | Jenis: Berkas Digital | Ukuran: %.1f MB | Tautan: %s", 
                ukuranMB, tautanUnduh);
    }
}