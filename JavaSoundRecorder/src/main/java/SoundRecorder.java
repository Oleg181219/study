package src.main.java;



import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class SoundRecorder {
    public static void main(String[] args) {
        String ACCESS_TOKEN =  "9l6yrP917bwAAAAAAAAAAThDiDvluysWk-q9uqeX1DdLAu-KTNLjxyHoct6KZdn4";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        JavaSoundRecorder javaSoundRecorder = new JavaSoundRecorder(client);
        javaSoundRecorder.record(5000);

    }
}
