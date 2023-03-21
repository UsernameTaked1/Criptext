public class User {
    char [] c;
public User(){

}
    public String cifrado(String p) {
        c = p.toCharArray();
        p = "";
        for (int i = 0; i < c.length; i++) {
            if(c[i]<48 || c[i]>57){
                p = p + (char)(c[i]+3);
            }else{
                p = p + c[i];
            }
        }
        c = p.toCharArray();
        p = "";
        for (int x = c.length-1; x >= 0; x--) {
            if (x <= ((int)c.length/2)){
                p = p + (char) (c[x]-1);
            }else {
                p = p + (char) c[x];
            }
        }
        return p;
    }

    public String descifrado(String p) {
        c = p.toCharArray();
        p = "";
        for (int i = 0; i < c.length; i++) {
            if(i >= ((int)c.length/2)){
                p = p + (char)(c[i]+1);
            }else{
                p = p + c[i];
            }
        }
        c = p.toCharArray();
        p = "";
        for (int x = c.length-1; x >= 0; x--) {
                p = p + (char) c[x];
        }
        c = p.toCharArray();
        p = "";
        for (int i = 0; i < c.length; i++) {
            if(c[i]<48 || c[i]>57){
                p = p + (char)(c[i]-3);
            }else{
                p = p + c[i];
            }
        }
        return p;
    }

    public static void main(String[] args) {
        User u = new User();
        System.out.println(u.descifrado("1FECedc"));
    }
}