package com.example.a49876.myapplication;


        import java.io.FileInputStream;
        import java.io.ObjectInputStream;
        import java.io.*;


public class ReadObject {

    private FileInputStream inFile;
    private ObjectInputStream inStream;
    private String fileName;

    public ReadObject() {

    }

    public Object readFromBinary(File path) {
        Object obj= null;
        try { // try to open and read the file
            inFile = new FileInputStream(path);
            inStream = new ObjectInputStream(inFile);

            while (true) // attempt to read after EOF will cause exception & stop loop
            { // process Patient
                obj = inStream.readObject();
            } // process Patient

        } // try to open and read the file
        catch (FileNotFoundException ex) { // catch File Not Found
            System.out.println("File named " + fileName + " not found.\n");
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
