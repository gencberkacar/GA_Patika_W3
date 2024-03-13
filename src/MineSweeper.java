import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class MineSweeper {
    //Proje Burada MineSweeper Sınıfı İçerisinde Tasarlandı.
    int lineNumber, pillarNumber, size;
    int[][] map;
    int[][] board;
    boolean[][] openBoard;

    Random rand = new Random();
    Scanner input = new Scanner(System.in);

    boolean isGame = true;

    MineSweeper(int lineNumber, int pillarNumber) {
        this.lineNumber = lineNumber;
        this.pillarNumber = pillarNumber;
        this.size = lineNumber * pillarNumber;
        this.map = new int[lineNumber][pillarNumber];
        this.board = new int[lineNumber][pillarNumber];
        this.openBoard = new boolean[lineNumber][pillarNumber];
    }

    public void startGame() {

        int lineChoice, pillarChoice, rightChoice = 0;

        setMines();
        createBoard(map);

        System.out.println("Oyununuz Başladı.");

        while(isGame) {

            createBoard(board); //Kullanıcı her hamle yaptığında alan güncelleniyor.
            //Kullanıcıdan işaretlemek istediği satır ve sütun burada alınıyor.
            System.out.print("Bir Satır Seçiniz : ");
            lineChoice = input.nextInt();
            System.out.print("Bir Sütun Seçiniz : ");
            pillarChoice = input.nextInt();
            //Kullanıcının seçtiği nokta alanın sınırlarındamı diye kontrol ediliyor. Uyarı mesajı veriliyor. Continue komutu ile döngü tekrar dönüyor ve tekrar giriş kısmı geliyor.
            if (lineChoice < 0 || lineChoice > lineNumber - 1) {
                //Oyunun Başlaması ve ardından kazanma kaybetme durumlarının kontrolleri burada bulunuyor.
                System.out.println("Oyun Alanına Ait Olmayan Bir Konum Girdiniz.");
                continue;
            }
            if (pillarChoice < 0 || pillarChoice > pillarNumber - 1) {
                System.out.println("Oyun Alanına Ait Olmayan Bir Konum Girdiniz.");
                continue;
            }
            if (!openBoard[lineChoice][pillarChoice]) {
                if (map[lineChoice][pillarChoice] != -1) {
                    checkMine(lineChoice, pillarChoice); //Girilen noktanın etrafını kontrol edip mayın sayısını giren veya mayın sayısı 0'sa yerine 0 yazan kısım.
                    rightChoice++;
                    if (rightChoice == (size - (size / 4))) { //Tüm noktalar mayınsız seçilirse kazanma kontrolü burada. Kaybetme durumuna uygun mesaj burada.
                        System.out.println("Tebrikler. Tüm Mayınsız Alanları Buldunuz.");
                        createBoard(board);
                        break;
                    }
                } else { //Kullanıcı mayına basarsa oyunu bitiren kısım. Kaybetme durumundaki uygun mesaj burada.
                    System.out.println("Mayına Bastınız !\nOyun Bitti.");
                    createBoard(map);
                    isGame = false;
                }
                openBoard[lineChoice][pillarChoice] = true;
            } else {
                System.out.println("Daha Önce Bu Seçimi Yaptınız.");
            }
        }
    }

    public void setMines() {
        //Diziye uygun sayıda mayın bu kısımda yerleştiriliyor.
        int randLine, randPillar, mine = 0;

        while (mine != (size / 4)) {
            randLine = rand.nextInt(lineNumber);
            randPillar = rand.nextInt(pillarNumber);
            if (map[randLine][randPillar] != -1) {
                map[randLine][randPillar] = -1;
                mine++;
            }
        }
    }

    public void createBoard(int[][] arr) {
        for (int i = 0; i < lineNumber; i++) {
            for (int j = 0; j < pillarNumber; j++) {
                if (!openBoard[i][j]) {
                    System.out.print("- ");
                } else {
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void checkMine(int i, int j) {
        if (map[i][j] == 0) {
            if((i > 0) && (j > 0) && (map[i-1][j-1] == -1)) {
                board[i][j]++;
            }
            if((i > 0) && (map[i-1][j] == -1)) {
                board[i][j]++;
            }
            if((j < pillarNumber - 1) && (i > 0) && (map[i-1][j+1] == -1)) {
                board[i][j]++;
            }
            if((j > 0) && (map[i][j-1] == -1)) {
                board[i][j]++;
            }
            if((j < pillarNumber - 1) && (map[i][j+1] == -1)) {
                board[i][j]++;
            }
            if((i < lineNumber - 1) && (j > 0) && (map[i+1][j-1] == -1)) {
                board[i][j]++;
            }
            if((i < lineNumber - 1) && (map[i+1][j] == -1)) {
                board[i][j]++;
            }
            if((j < pillarNumber - 1) && (i < lineNumber - 1) && (map[i+1][j+1] == -1)) {
                board[i][j]++;
            }
        }
    }
}