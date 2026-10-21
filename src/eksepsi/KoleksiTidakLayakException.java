package eksepsi;

public class KoleksiTidakLayakException extends PustakaException {
    public KoleksiTidakLayakException(String judul, String alasan) {
        super("Mohon maaf, koleksi '" + judul + "' tidak dapat dipinjam karena " + alasan + ".");
    }
}