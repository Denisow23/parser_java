import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Recursive {

    public static ArrayList<File> list(String path) {
        File file = new File(path);
        ArrayList<File> files= new ArrayList<>();
        if (!file.exists()) {
            System.out.println("\nNot found: " + path);
            return null;
        }

        if (!file.isDirectory()) {
            System.out.println(
                    "\nNot directory: " + path);
            return null;
        }

        String[] sDirList = file.list();
        int i;
        for(i = 0; i < Objects.requireNonNull(sDirList).length; i++)
        {
            File f1 = new File(path +
                    File.separator + sDirList[i]);

            if(f1.isFile())
                files.add(f1);
            else
            {
                files.addAll(list(path +
                        File.separator + sDirList[i]));
            }
        }

    return files;
    }
}
