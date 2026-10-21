package model;

public class BukuCetak extends Koleksi implements Terarsip {
    private int jumlahHalaman;
    private String nomorRak;
    private boolean sedangDiperbaiki;

    public BukuCetak(String kodeKoleksi, String judul, int tahunTerbit, int jumlahHalaman, String nomorRak, boolean sedangDiperbaiki) {
        super(kodeKoleksi, judul, tahunTerbit);
        this.jumlahHalaman = jumlahHalaman;
        this.nomorRak = nomorRak;
        this.sedangDiperbaiki = sedangDiperbaiki;
    }

    public boolean isSedangDiperbaiki() {
        return sedangDiperbaiki;
    }

    @Override
    public boolean isLayakDipinjam() {
        return !sedangDiperbaiki;
    }

    @Override
    public String deskripsi() {
        String statusPerbaiki = sedangDiperbaiki ? "Sedang Diperbaiki" : "Kondisi Baik";
        return super.deskripsi() + String.format(" | Jenis: Buku Cetak | %d Hlm | Status: %s | %s", 
                jumlahHalaman, statusPerbaiki, ringkasanArsip());
    }

    @Override
    public String nomorRak() {
        return nomorRak;
    }

    @Override
    public String lokasiLantai() {
        // Logika penentuan lantai berdasarkan nomor rak
        if (nomorRak.startsWith("A")) return "Lantai 1";
        if (nomorRak.startsWith("B")) return "Lantai 2";
        return "Lantai Utama";
    }
}