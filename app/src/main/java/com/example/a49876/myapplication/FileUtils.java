package com.example.a49876.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A utility class to handle file input and output
 */
public class FileUtils {
    private File path;

    public FileUtils(){}

    public FileUtils(File path){
        this.path = path;
        //get internal storage path
    }

    // Save data into a file
    public void writeFile(File file, String data){


        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (stream != null) {
                stream.write(data.getBytes());
                Log.e("writing files","writed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Read data from a file
    public String readFile(File file) {
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        FileInputStream in = null;
        if (file.exists()) {
            try {
                in = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                in.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert in != null;
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String contents = new String(bytes);
            Log.e("Reading files", contents);
            return contents;
        } else {
            return "";
        }
    }
}
