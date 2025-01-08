package laptop;
import laptop.FrameLogin;

public class Main {
    public static void main(String[] args) {
        FrameLogin frame = new FrameLogin();
        frame.setSize(800, 400); // Atur ukuran frame (lebar: 800px, tinggi: 600px)
        frame.setLocationRelativeTo(null); // Mengatur frame muncul di tengah layar
        frame.setVisible(true);
    }
}