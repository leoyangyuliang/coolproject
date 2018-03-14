package com.example.a49876.myapplication;



        import java.io.FileOutputStream;
        import java.io.ObjectOutputStream;
        import java.io.*;

public class WriteObject{

    private File path;
    private FileOutputStream outFile;
    private ObjectOutputStream outStream;


    //CONSTUCTOR
    public WriteObject() {
    }

    //METHOD
    public void writeToBinary(Object obj, File path, String fileName) {
        // combind path+filename
        this.path = new File(path,fileName);

        //writing binary file
        try { // try to open and write the file
            outFile = new FileOutputStream(this.path);
            outStream = new ObjectOutputStream(outFile);
            outStream.writeObject(obj);
            outStream.close();
        } // try to open and write the file
        catch (IOException ex) { // catch
            System.out.println(ex); // display error msg in a TextArea, or use S.O.P. to console
        } // catch
    }

}
