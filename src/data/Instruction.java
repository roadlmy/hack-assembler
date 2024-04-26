package data;

public class Instruction {

    // 1为A指令，2为C指令
    private Integer type;

    private Integer varNum;

    private String out;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getVarNum() {
        return varNum;
    }

    public void setVarNum(Integer varNum) {
        this.varNum = varNum;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }
}
