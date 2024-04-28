package echo;

import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

    protected ServerSocket mySocket;
    protected int myPort;
    public static boolean DEBUG = true;
    protected Class<?> handlerType;

    public Server(int port, String handlerTypeName) {
        try {
            myPort = port;
            mySocket = new ServerSocket(myPort);
            this.handlerType = Class.forName(handlerTypeName);
        } catch(Exception e) {
            System.err.println("Constructor: " + e.getMessage());
            System.exit(1);
        } // catch
    }


    public void listen() {
        while(true) {
            try {
                Socket accept = mySocket.accept();
                Thread newThread = new Thread(makeHandler(accept));
                newThread.start();
            }catch (Exception e){

                System.out.println("Listen:" + e.getMessage());
            }
            // accept a connection
            // make handler
            // start handler in its own thread
        } // while
    }

    public RequestHandler makeHandler(Socket s) {
        RequestHandler handler = null;
        try {

            handler = (RequestHandler) handlerType.getDeclaredConstructor().newInstance();
            // set handler's socket to s
            handler.setSocket(s);
            System.out.println(handlerType + " " + handler);

        }catch (Exception e){
            System.out.println("MakeHandler: " + e.getMessage());
        }
        System.out.println(handler == null);
        return handler;
    }



    public static void main(String[] args) {
        int port = 5555;
        String service = "echo.MathHandler";
        if (1 <= args.length) {
            service = args[0];
        }
        if (2 <= args.length) {
            port = Integer.parseInt(args[1]);
        }

        Server server = new Server(port, service);
        server.listen();
    }
}