package ie.tpearson.filewatcher;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length < 1 || args[0].isEmpty()){
            System.err.println("A path to watch is required. Please provide it as an argument.");
            System.exit(1);
        }
        String folder = args[0];
        Path path = Paths.get(folder);
        Filewatcher filewatcher = new Filewatcher(path);
        filewatcher.register();
        filewatcher.watch();
    }
}
