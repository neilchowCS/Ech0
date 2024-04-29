package echo;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class CacheHandler extends ProxyHandler{

    private static SafeTable<String, String> cache = new SafeTable<>();

    public CacheHandler(Socket s) { super(s);}
    public CacheHandler() { super();}

    protected String response(String msg) throws Exception {
        if (!cache.containsKey(msg)){

            peer.send(msg);
            String response = peer.receive();

            cache.put(msg,response);
            System.out.println("Add " + msg + " | " + response + " to cache");

            return response;
        }else{
            String response = cache.get(msg);
            System.out.println("Retrieved response: " + response + " from cache");
            return response;
        }
    }
}
