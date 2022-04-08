package pl.pal.kamil.pt3;

import javafx.concurrent.Task;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;


public class SendFileTask extends Task {

    private final File file;

    public SendFileTask(File file) {
        this.file = file;

    }

    @Override
    protected Object call() throws Exception {


        try (Socket client = new Socket("localhost", 9797)) {

            try (OutputStream os = client.getOutputStream();
                 FileInputStream f = new FileInputStream(file);) {



                Long totalLength = file.length();

                byte[] r;
                long sentLength = 0;

                updateMessage("Initializing");

                do {
                    updateMessage("Sending");
                    r = f.readNBytes(1024);
                    if (r.length != 0) {
                        os.write(r);
                        sentLength += r.length;
                        updateProgress(sentLength,totalLength);
                        Thread.sleep(25); // uspienie dodane po to, by moc zaobserwowac progres wyslania pliku.
                        os.flush();
                    }
                } while (r.length != 0);

                updateMessage("Finishing");


            }
            return null;
        }
    }

}
