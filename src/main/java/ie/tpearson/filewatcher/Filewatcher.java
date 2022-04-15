package ie.tpearson.filewatcher;

import java.io.IOException;
import java.nio.file.*;

public class Filewatcher {

    private WatchService watchService;
    private Path target;

    public Filewatcher(Path target) {
        this.target = target;
    }

    public void register() throws IOException {
        watchService = FileSystems.getDefault().newWatchService();
        WatchKey watchKey = this.target.register(watchService,
            StandardWatchEventKinds.ENTRY_MODIFY,
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_DELETE);
        processKey(watchKey);
    }

    private void processKey(WatchKey watchKey) {
        for( WatchEvent<?> event : watchKey.pollEvents()) {
            Path path = (Path) event.context();
            if(path != null) {
                System.out.println(System.currentTimeMillis() + ": File "+ path.toString() +" received event [" + event.kind() + "] " + event.count() + " times.");
            } else {
                System.err.println(System.currentTimeMillis() + ": Event received with null path: " + event.kind());
            }
        }
    }

    public void watch() throws InterruptedException {
        WatchKey watchKey;
        System.out.println("Watcher started watching on " + target.toString());
        while((watchKey = watchService.take()) != null) {
            processKey(watchKey);
            watchKey.reset();
        }
        System.out.println("Watcher finished watching on " + target.toString());
    }
}
