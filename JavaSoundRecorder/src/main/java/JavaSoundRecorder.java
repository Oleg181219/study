package src.main.java;

import com.dropbox.core.v2.DbxClientV2;

import javax.sound.sampled.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaSoundRecorder {

    DbxClientV2 client;
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    TargetDataLine line;
    DataLine.Info info;
    AudioFormat format;
    DateFormat simpleDataFormat;
    String namePattern = "yyyyMMdd_HHmmss";
    String pathToFile;
    String reportDate;

    public JavaSoundRecorder(DbxClientV2 clientV2) {
        this.client = clientV2;
        format = getAudioFormat();
        info = new DataLine.Info(TargetDataLine.class, format);
    }

    AudioFormat getAudioFormat() {
        float sampleRate = 128000;
        int sampleSizeInBits = 24;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        return format;
    }

    public void record(int milliseconds) {
        simpleDataFormat = new SimpleDateFormat(namePattern);
        Date now = Calendar.getInstance().getTime();
        reportDate = simpleDataFormat.format(now);
        pathToFile = "/Users/olegprokhorov/Documents/Record_wav/" + reportDate + ".wav";
        File file = new File(pathToFile);
        start(file);
        finish(file, milliseconds);
    }


    public void start(File file) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    if (!AudioSystem.isLineSupported(info)) {
                        System.out.println("Line not supported");
                        System.exit(0);
                    }
                    line = (TargetDataLine) AudioSystem.getLine(info);
                    line.open(format);
                    line.start();
                    AudioInputStream ais = new AudioInputStream(line);
                    AudioSystem.write(ais, fileType, file);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void finish(File file, int millis) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(millis);
                    line.stop();
                    line.close();

                    InputStream in = new FileInputStream(pathToFile);
                    String pathTo = "/" + reportDate + ".wav";
                    client.files().uploadBuilder(pathTo).uploadAndFinish(in);
                    in.close();
                    File file = new File(pathToFile);
                    if (file.delete()) {
                        System.out.println(" файл удален");
                    } else System.out.println(" не обнаружено");
                    record(millis);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }
}