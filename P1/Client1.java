import java.util.Arrays;
import java.util.Random;
import java.net.*;
import java.io.*;

public class Client1 
{
    public static void main(String args[])
    {
        try
        {

        //Creating the file
        File F1 = new File("F1");
        try
        {
            
            if(F1.createNewFile())
            {
                System.out.println("Created file " + F1.getName() + " successfully.");
            }
            else
            {
                if(F1.delete())
                {
                    System.out.println("Deleted file: " + F1.getName());
                }
                else
                {
                    System.out.println("Was unable to delete the file.");
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Error code 1. Failed when creating file.");
        }
        //Need to create the data that will go into the file.

        //First creating an array of byte[300] with random binary contents
        //Creating a file that will be used to send.
        //Making the file into a an byte[100] that will be used to send data.
        Random rd = new Random();
        byte[] randArray = new byte[300];
        rd.nextBytes(randArray);
        System.out.println(randArray);

        //Open the file for writing
        FileOutputStream fw = new FileOutputStream("F1");
        fw.write(randArray);

        //Connecting to the server
        Socket s1 = new Socket("localhost", 3827);
        //Creating the input and output stream for client 1 with the server
        DataOutputStream dout1 = new DataOutputStream(s1.getOutputStream());
        DataInputStream din1 = new DataInputStream(s1.getInputStream());
        

        byte[] fileContent = new byte [(int)F1.length()];
        try 
        {
            FileInputStream fr = new FileInputStream(F1);
            fr.read(fileContent);
        } 
        catch (Exception e) {
            System.out.println("Error reading from F3.");
        }

        //Need to send the file to the server in 100 bytes, total file is 300 bytes.
        for (int i = 0; i < 3; i++) {
            dout1.write(Arrays.copyOfRange(fileContent, i * 100, (i + 1) * 100));
            dout1.flush();
        }

        //Now we need to recieve F3 from the server
        fw = new FileOutputStream("F3");
        try
        {
            //Recieve the files contents from server until the 600 bytes are recieved.
            byte[] str1 = new byte[200];
            for(int j = 0; j < 3; j++)
            {
                int amt =  din1.read(str1);
                //If it is less than 0 then it will stop writing to the file and it is a signal to say there is no more data to send.
                if(amt < 0)
                {
                    break;
                }
                //We need to write the data to F3
                
                fw.write(str1);
            }
        }
        catch(IOException e)
        {
            System.out.println("Error code 4. Failed recieving data from server or writing to F3 in Client.");
        }
    }
    catch(Exception e)
    {
        System.out.println("An error has occurred.");
    }
    }
}
