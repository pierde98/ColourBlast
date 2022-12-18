package it.unipg.pigdm.colourblast.Logic;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LevelSavings {

    private final static String fileName = "/livelli_sbloccati.json"; //nome del file dove si salva il livello
    private static final int READ_BLOCK_SIZE = 150;
    private File file;
    private int level;
    private static LevelSavings instance = null;

    public LevelSavings(Context context) {
        String path = context.getFilesDir().getAbsolutePath();
        file = new File(path + fileName);
    }

    public void writeFile(int i) {

        try {
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            JSONObject jObject = new JSONObject();
            jObject.put("livello", i);
            String s = jObject.toString();
            this.level = i;
            osw.write(s);
            osw.flush();
            osw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int readFile() {

        int livello = 0;
        try {

            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";

            int charRead;

            while ((charRead = isr.read(inputBuffer)) > 0) {
                s += String.copyValueOf(inputBuffer, 0, charRead);
                inputBuffer = new char[READ_BLOCK_SIZE];
            }

            JSONObject jObject = new JSONObject(s);
            livello = jObject.getInt("livello");


        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return livello;
    }

    public static LevelSavings getSaving(Context context){
        if (instance == null)
            instance = new LevelSavings(context);
        return instance;
    }
}
