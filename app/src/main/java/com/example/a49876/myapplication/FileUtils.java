package com.example.a49876.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

/**
 * A utility class to handle file input and output
 */
public class FileUtils {
    private File path;
    private FileOutputStream outFile;
    private ObjectOutputStream outStream;
    private FileInputStream inFile;
    private ObjectInputStream inStream;
    public String journal;
    public FileUtils() {
    }

    public FileUtils(File path) {
        this.path = path;
        //get internal storage path
    }

    // Save data into a file

    public static void writeFile(File file, String data) {
        FileOutputStream stream = null;


        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (stream != null) {
                stream.write(data.getBytes());
                Log.e("writing files", "writed");
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
    public static String readFile(File file) {
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


    //write Object
    public void writeToBinary(Object obj, File path, String fileName) {
        // combind path+filename
        path = new File(path, fileName);

        //writing binary file
        try { // try to open and write the file
            outFile = new FileOutputStream(path);
            outStream = new ObjectOutputStream(outFile);
            outStream.writeObject(obj);
            outStream.close();
        } // try to open and write the file
        catch (IOException ex) { // catch
            System.out.println(ex); // display error msg in a TextArea, or use S.O.P. to console
        } // catch
    }

    //read object
    public Object readFromBinary(File path) {
        Object obj = null;
        try { // try to open and read the file
            inFile = new FileInputStream(path);
            inStream = new ObjectInputStream(inFile);

            while (true) // attempt to read after EOF will cause exception & stop loop
            { // process Patient
                obj = inStream.readObject();
            } // process Patient

        } // try to open and read the file
        catch (FileNotFoundException ex) { // catch File Not Found
            System.out.println("File named " + path.getPath() + " not found.\n");
        } // catch File Not Found
        catch (EOFException ex) { // catch EOF
            try { // try close
                inStream.close();
            } // try close
            catch (IOException eexx) { // catch IOException
            } // catch IOException
        } // catch EOF
        catch (IOException ex) { // catch IOException
            System.out.println(ex);
        } // catch IOException
        catch (ClassNotFoundException ex) { // ClassNotFound
            System.out.println(ex);
        } // ClassNotFound
        return obj;
    }


}


