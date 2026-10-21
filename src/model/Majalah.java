package model;

public class Majalah extends Koleksi implements Terarsip {
    private int edisi;
    private String bulanTerbit;
    private String nomorRak;
    private boolean edisiTerbaru;

    public Majalah(String kodeKoleksi, String judul, int tahunTerbit, int edisi, String bulanTerbit, String nomorRak, boolean edisiTerbaru) {
        super(kodeKoleksi, judul, tahunTerbit);
        this.edisi = edisi;
        this.bulanTerbit = bulanTerbit;
        this.nomorRak = nomorRak;
        this.edisiTerbaru = edisiTerbaru;
    }

    public boolean isEdisiTerbaru() {
        return edisiTerbaru;
    }

    @Override
    public boolean isLayakDipinjam() {
        return !edisiTerbaru;
    }

    @Override
    public String deskripsi() {
        String statusEdisi = edisiTerbaru ? "Edisi Terbaru (Hanya Baca)" : "Edisi Lama";
        return super.deskripsi() + String.format(" | Jenis: Majalah | Edisi %d (%s) | Info: %s | %s", 
                edisi, bulanTerbit, statusEdisi, ringkasanArsip());
    }

    @Override
    public String nomorRak() {
        return nomorRak;
    }

    @Override
    public String lokasiLantai() {
        if (nomorRak.startsWith("A")) return "Lantai 1";
        if (nomorRak.startsWith("B")) return "Lantai 2";
        return "Lantai Utama";
    }
}