import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int line, pillar;
        //Dizinin matris boyutları burada kullanıcıdan alınıyor.
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz !\nOynamak İstediğiniz Tahtanın Boyutları İçin ;");
        System.out.print("Satır Sayısını Giriniz : ");
        line = input.nextInt();
        System.out.print("Sütun Sayısını Giriniz : ");
        pillar = input.nextInt();
        if (line < 2 || pillar < 2) {
            System.out.println("Hata: Satır ve sütun sayıları en az 2 olmalıdır.");
            return;
        }
        System.out.println("NOT : Açtığınız Konum Hala '0' Görünmekte İse Konumun Çevresinde Mayın yoktur !");
        MineSweeper mine = new MineSweeper(line, pillar);
        mine.startGame();
    }
}
