package echo;

public class SecurityProxy extends ProxyHandler{

    @Override
    protected String response(String msg) throws Exception {

        String[] cmd = msg.split(" ");



        return super.response(msg);
    }
}
