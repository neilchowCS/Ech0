package echo;

import java.net.Socket;

public class MathHandler extends RequestHandler{
    public MathHandler(){
        super();
    }
    public MathHandler(Socket sock){
        super(sock);
    }

    @Override
    protected String response(String msg) throws Exception {
        //String[] cmmd = msg.trim().split("\\s+");
        String[] cmmd = msg.split("\\s+");

        float ans;
        try {
            if (cmmd[0].equalsIgnoreCase("add")) {
                float[] nums = toNumbers(cmmd);
                ans = nums[0];
                for (int i = 1; i < nums.length; i++){
                    ans += nums[i];
                }
            } else if (cmmd[0].equalsIgnoreCase("sub")) {
                float[] nums = toNumbers(cmmd);
                ans = nums[0];
                for (int i = 1; i < nums.length; i++){
                    ans -= nums[i];
                }
            } else if (cmmd[0].equalsIgnoreCase("mul")) {
                float[] nums = toNumbers(cmmd);
                ans = nums[0];
                for (int i = 1; i < nums.length; i++){
                    ans *= nums[i];
                }
            } else if (cmmd[0].equalsIgnoreCase("div")) {
                float[] nums = toNumbers(cmmd);
                ans = nums[0];
                for (int i = 1; i < nums.length; i++){
                    if (nums[i] == 0){
                        return ("error: div by 0");
                    }
                    ans /= nums[i];
                }
            } else {
                return ("error: unrecognized operator");
            }
        }catch (Exception e){
            return ("error: inputs must be numeric");
        }

        if (cmmd.length < 3){
            return("error: at least 2 inputs needed");
        }
        return ans + "";
    }

    private float[] toNumbers(String[] cmmd) throws Exception{
        float[] output = new float[cmmd.length - 1];
        for (int i = 1; i < cmmd.length; i++){
            output[i-1] = Float.parseFloat(cmmd[i]);
        }
        return output;
    }
}
