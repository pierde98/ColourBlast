package it.unipg.pigdm.colourblast.Logic;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Savings {

    private int liv = 1;
    private int level;
    private File file;
    private String path, s;
    private final static String fileName = "/savings.json";
    private JSONObject jsonObject;
    private Context context;
    private static Savings instance = null;
    private static SharedPreferences pref;
    private static boolean music;
    private static boolean sound;


    public Savings(Context context) {
        this.context = context;
        path = this.context.getFilesDir().getAbsolutePath();  //prendo il percorso assoluto verso la directory in cui è contenuto il context
        file = new File(path + fileName);      //creo un file json nella directory orecedentemente trovata
        s = "";
        getString(); //salvo il testo del file sotto forma di stringa

        if (this.s.equals("")) {            //in questo if controllo se il file json è vuoto o meno, nel caso in cui è vuoto aggiungo "livello" : "1"
            jsonObject = new JSONObject();
            try {
                jsonObject.put("Level", this.liv);
                String userString = jsonObject.toString();  //converto il jsonobject in stringa
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(userString);
                bufferedWriter.close();

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Per salvare lo stato degli switch su musica e suoni
    static void save() {
        SharedPreferences.Editor ed = pref.edit();
        ed.putBoolean("sound", sound);
        ed.putBoolean("music", music);
        ed.apply();
    }

    private void getString() {

        try {
            //this.path = this.context.getFilesDir().getAbsolutePath();
            //this.file = new File(this.path + this.fileName);

            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();

            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            this.s = stringBuilder.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public int readLevelNumber() {
        try {
            this.jsonObject = new JSONObject(this.s);
            this.level = jsonObject.getInt("Level");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.level;
    }

    public void changeLevelNumber(int l){
        this.jsonObject = new JSONObject();
        try {
            this.jsonObject.put("Level", l);
            String userString = jsonObject.toString();  //converto il jsonobject in stringa
            FileWriter fileWriter = new FileWriter(this.file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userString);
            bufferedWriter.close();

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    static void readProp() {
        String mypreference = "Filepref";
        pref = Logic.getInstance().getContext().getSharedPreferences(mypreference, 0);
        sound = pref.getBoolean("sound", false);
        music = pref.getBoolean("music", false);
    }

    //Setta i valori della musica e del suono
    static void setSound(boolean bs){
        sound=bs;
    }

    static void setMusic(boolean bm){
        music=bm;
    }

    //Restituisce i valori della musica e del suono
    static boolean getSound(){
        return sound;
    }

    static boolean getMusic(){
        return music;
    }

    public static Savings getSaving(Context context){
        if (instance == null)
            instance = new Savings(context);
        return instance;
    }
}
