package model;

public interface Terarsip {
    String nomorRak();
    String lokasiLantai();

    default String ringkasanArsip() {
        return "Arsip Fisik: Rak " + nomorRak() + " (" + lokasiLantai() + ")";
    }
}