package echo;

import java.net.Socket;

public class ProxyHandler extends RequestHandler {

    protected Correspondent peer;

    public ProxyHandler(Socket s) { super(s); }
    public ProxyHandler() { super();  }



    public void initPeer(String host, int port) {
        //System.out.println(port);
        peer = new Correspondent();
        peer.requestConnection(host, port);
    }

    protected String response(String msg) throws Exception {
        // forward msg to peer
        System.out.println("Message: " + msg);
        peer.send(msg);
        // return peer's response
        String response = peer.receive();
        System.out.println("Response: " + response);

        return response;
    }

    //override shutdown -> peer.send quit to kill

    @Override
    protected void shutDown() {
        super.shutDown();
        peer.send("quit");
    }
}
