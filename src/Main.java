import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String folderPath = "C:/Users/dimas/Desktop/test";
        File file = new File(folderPath);

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
    }

    public static long getFolderSize(File folder){
        if (folder.isFile()){
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file: files){
            sum += getFolderSize(file);
        }
        return sum;
    }

    //TODO: 24B,234Kb,36Mb,34Gb, 42Tb
    public String getHumanReadableSize(long size){
        BigInteger integer = new BigInteger("80000000000");
        if (size< 1024){
            return size + "B";
        } else if (size > 1024 && size < 1000000){
            return size/ 1024 + "Kb";
        } else if (size > 1000000 && size < 1000_000_000){
            return size/1000000 + "Mb";
        }else if (size > 1_000_000_000 && size < integer.longValue()){
            return size/1_000_000_000 + "Gb";
        }
        return size/integer.intValue() + "Tb";
    }

    //TODO: 24B,234Kb,36Mb,34Gb, 42Tb
    // 24B, 234K,36M, 34G, 42T
    // 235k => 240640 (235* 1024)
    public long getSizeFromHumanReadable(String size){
        BigInteger integer = new BigInteger("80000000000");
        String regex = "[^A-z]";
        String abr = size.replace(regex,"");
        switch (abr) {
            case "B":
                return Long.parseLong(size);
            case "Kb":
                return Long.parseLong(size) * 1024;
            case "Mb":
                return Long.parseLong(size) * 1000000;
            case "Gb":
                return Long.parseLong(size) * 1_000_000_000;
            case "Tb":
                return Long.parseLong(size) * integer.longValue();
        }
        return 0;
    }
}
