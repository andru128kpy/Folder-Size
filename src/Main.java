import java.io.File;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь файла");
        String folderPath = scanner.nextLine();

        try {
            File folder = new File(folderPath);
            long size = calculateFolderSize(folder);
            System.out.println("Размерность файла: " + formatSize(size));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Ошибка пути файла");
        }
    }

    public static long calculateFolderSize(File folder) {
        long size = 0;
        if (folder.isFile()) {
            size = folder.length();
        } else {
            File[] files = folder.listFiles();
            for (File file : files) {
                size += calculateFolderSize(file);
            }
        }
        return size;
    }

    public static String formatSize(long size) {
        String[] units = {"байт", "КБ", "МБ", "ГБ"};
        double sizeValue = size;
        int index = 0;
        while (sizeValue > 1024 && index < units.length) {
            sizeValue /= 1024;
            index++;
        }
        return String.format("%.2f %s", sizeValue, units[index]);
    }
}