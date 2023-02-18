import java.util.Arrays;
import java.io.*;
import java.net.*;


public class Server 
{
    public static void main(String args[])
    {
    System.out.println("1");
        try
        {
        System.out.println("2");
        
        //We will put it in a try-catch statement
        ServerSocket ss = new ServerSocket(3827);
        System.out.println("3");
        //Creating F3
        File F3 = new File("F3");
        System.out.println("4");
        try
        {
            if(F3.createNewFile())
            {System.out.println("5");
                System.out.println("Created file " + F3.getName() + " successfully.");
            }
            else
            {
                if(F3.delete())
                {
                    System.out.println("Deleted file: " + F3.getName());
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

            //Open the file for writing
            FileOutputStream fw = new FileOutputStream("F3");
            //Accepting the connection from client 1
            Socket s1 = ss.accept();
            //Creating the input and output stream for client 1
            DataOutputStream dout1 = new DataOutputStream(s1.getOutputStream());
            DataInputStream din1 = new DataInputStream(s1.getInputStream());

        try
        {
            

            //Recieve the files contents from client 1 until the 300 bytes are recieved.
            byte[] str1 = new byte[100];
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
            System.out.println("Error code 2. Failed recieving data fromclient 1 or writing data to F3.");
        }

            //Accepting connection from client 2
            Socket s2 = ss.accept();
            //Creating the input and output stream for client 2
            DataOutputStream dout2 = new DataOutputStream(s2.getOutputStream());
            DataInputStream din2 = new DataInputStream(s2.getInputStream());
        try
        {
            
            //Recieve the files contents from client 2 until the 300 bytes are recieved.
            byte[] str2 = new byte[100];
            for(int j = 0; j < 3; j++)
            {
                int amt =  din2.read(str2);
                //If it is less than 0 then it will stop writing to the file and it is a signal to say there is no more data to send.
                if(amt < 0)
                {
                    break;
                }
                //We need to write the data to F3
                fw.write(str2);
            }
        }
        catch(IOException e)
        {
            System.out.println("Error code 2. Failed recieving data from client 1 or writing data to F3.");
        }
        
        //sending F3 to client 1 and client 2
        
        byte[] fileContent = new byte [(int)F3.length()];
        try 
        {
            FileInputStream fr = new FileInputStream(F3);
            fr.read(fileContent);
        } 
        catch (Exception e) {
            System.out.println("Error reading from F3.");
        }
        for (int i = 0; i < 3; i++) 
        {
            dout1.write(Arrays.copyOfRange(fileContent, i * 200, (i + 1) * 200));
            dout1.flush();
        }
        for (int i = 0; i < 3; i++) 
        {
            dout2.write(Arrays.copyOfRange(fileContent, i * 200, (i + 1) * 200));
            dout2.flush();
        }
        //Closing all the sockets
        dout1.close();
        dout2.close();
        din1.close();
        din2.close();
        s1.close();
        s2.close();
        ss.close();
    }
    catch(Exception e)
    {
        System.out.println("An error has occured.");
    }
    }
}
