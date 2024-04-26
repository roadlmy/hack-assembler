package tool;

import java.util.HashMap;

public class Translator {

    private static final HashMap<String, String> compSet = new HashMap<String, String>();
    static {
        compSet.put("0", "0101010");
        compSet.put("1", "0111111");
        compSet.put("-1", "0111010");
        compSet.put("D", "0001100");
        compSet.put("A", "0110000");
        compSet.put("!D", "0001101");
        compSet.put("!A", "0110001");
        compSet.put("-D", "0001111");
        compSet.put("-A", "0110011");
        compSet.put("D+1", "0011111");
        compSet.put("A+1", "0110111");
        compSet.put("D-1", "0001110");
        compSet.put("A-1", "0110010");
        compSet.put("D+A", "0000010");
        compSet.put("D-A", "0010011");
        compSet.put("A-D", "0000111");
        compSet.put("D&A", "0000000");
        compSet.put("D|A", "0010101");

        compSet.put("M", "1110000");
        compSet.put("!M", "1110001");
        compSet.put("-M", "1110011");
        compSet.put("M+1", "1110111");

        compSet.put("M-1", "1110010");
        compSet.put("D+M", "1000010");
        compSet.put("D-M", "1010011");
        compSet.put("M-D", "1000111");
        compSet.put("D&M", "1000000");
        compSet.put("D|M", "1010101");
    }
    private static final HashMap<String, String> destSet = new HashMap<String, String>();
    static {
        destSet.put(null,"000");
        destSet.put("M","001");
        destSet.put("D","010");
        destSet.put("MD","011");
        destSet.put("A","100");
        destSet.put("AM","101");
        destSet.put("AD","110");
        destSet.put("AMD","111");
    }

    private static final HashMap<String, String> jumpSet = new HashMap<String, String>();
    static {
        jumpSet.put(null,"000");
        jumpSet.put("JGT","001");
        jumpSet.put("JEQ","010");
        jumpSet.put("JGE","011");
        jumpSet.put("JLT","100");
        jumpSet.put("JNE","101");
        jumpSet.put("JLE","110");
        jumpSet.put("JMP","111");
    }

    public String comp(String comp){
        comp = comp.trim();
        String s = compSet.get(comp);
        if(s == null){
            throw new RuntimeException("不存在这种comp的写法，请查看");
        }

        return s;
    }

    public String dest(String dest){
        String s = destSet.get(dest);
        if(s == null){
            throw new RuntimeException("不存在这种dest的写法，请查看");
        }

        return s;
    }

    public String jump(String jump){
        String s = jumpSet.get(jump);
        if(s == null){
            throw new RuntimeException("不存在这种jump的写法，请查看");
        }

        return s;
    }

    public static void main(String[] args) {
        Translator translator = new Translator();
        Parser parser = new Parser();
        System.out.println(translator.jump(parser.jump("M=D")));

    }
}
