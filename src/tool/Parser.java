package tool;

public class Parser {

    public String comp(String instruction){
        int equalsPlace = instruction.indexOf("=");
        int commaPlace = instruction.indexOf(";");

        //不存在dest
        if(equalsPlace == -1){
            if(commaPlace == -1){
                return instruction;
            }else{
                return instruction.substring(0,commaPlace);
            }
        }else {
            if(commaPlace == -1){
                return instruction.substring(equalsPlace+1);
            }else {
                return instruction.substring(equalsPlace+1,commaPlace);

            }
        }
    }

    public String dest(String instruction){
        int equalsPlace = instruction.indexOf("=");
        if(equalsPlace == -1){
            return null;
        }

        return instruction.substring(0,equalsPlace);
    }

    public String jump(String instruction){
        int commaPlace = instruction.indexOf(";");
        if(commaPlace == -1){
            return null;
        }

        return instruction.substring(commaPlace+1);
    }


    public static void main(String[] args) {
        Parser parser = new Parser();
        String comp = parser.jump("M=D");
        System.out.println(comp);

    }
}
