package echo;

import java.net.Socket;

public class SecurityProxy extends ProxyHandler{

    private static SafeTable<String, String> users = new SafeTable<>();
    private boolean loggedIn = false;
    public SecurityProxy(Socket s) { super(s);}
    public SecurityProxy() { super();}

    @Override
    protected String response(String msg) throws Exception {

        String[] cmmd = msg.split("\\s+");
        String answer = "";
        if(cmmd[0].equalsIgnoreCase("new")) {
            if (!users.containsKey(cmmd[1])) {
                users.put(cmmd[1], cmmd[2]);
                answer = "ACCOUNT CREATED";
                shutDown();
            } else {
                answer = "error: user exists";
            }
        } else if (cmmd[0].equalsIgnoreCase("login")) {
            if (users.get(cmmd[1]).equals(cmmd[2])){
                loggedIn = true;
                answer = "logged in";
            }else{
                answer = "wrong username or password";
            }
        } else {
            if (loggedIn) {
                answer = super.response(msg);
            } else {
                answer = "must login to access peer";
            }
        }
        return answer;
    }
}
