package client;

import java.io.*;
import java.net.Socket;

/**
 * Created by Jary on 7/28/2018.
 */
public class Client {
    public static void main(String [] args) {


        try (Socket clientSocket = new Socket("localhost", 54321);
             OutputStream outPut = clientSocket.getOutputStream();
             PrintWriter writer = new PrintWriter(outPut, true);
             InputStream input = clientSocket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader userInput = new BufferedReader(inputStreamReader);
        ){
            String fromUser=null;
            String serverResult=null;

            while (true) {
                System.out.print("enter command : ");
                try {
                    fromUser = userInput.readLine();

                    if (fromUser.equals("exit")){
                        break;
                    }

                    writer.println(fromUser);
                    writer.flush();
                    serverResult = reader.readLine();
                    System.out.println(serverResult);

                }

                catch (Exception e) {
                    System.out.println("received exception" + e);
                    throw new Exception(e);
                }
            }
        } catch (Exception ex) {
            System.out.println("received Socket exception " + ex);
        }
    }

}
