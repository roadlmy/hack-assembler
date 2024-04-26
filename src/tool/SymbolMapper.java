package tool;

import data.Instruction;

import java.util.HashMap;

public class SymbolMapper {
    public Instruction mapToNumber(String str, HashMap<String,String> symbolTable, Integer varNum) {

        Instruction instruction = new Instruction();

        String data = str.substring(str.indexOf("@")+1);
        // 如果为数字，直接返回
        try {
            Integer.parseInt(data);
            instruction.setVarNum(varNum);
            instruction.setOut(transferDec2Binary(data));

            return instruction;
        } catch (Exception ignored){
        }

        // 如果在symbolTable有，直接替换（为LABEL或者已有变量）

        if(symbolTable.get(data) != null){
            instruction.setVarNum(varNum);
            instruction.setOut(transferDec2Binary(symbolTable.get(data)));
            return instruction;
        }else {
            // 如果没有，说明为新变量，取出最新的symbolTable序号，并加一存入返回
            Integer newMem = varNum + 1;
            symbolTable.put(data, String.valueOf(varNum));
            instruction.setVarNum(newMem);
            instruction.setOut(transferDec2Binary(String.valueOf(varNum)));
            return instruction;
        }

    }

    private String transferDec2Binary(String data) {
        return "0" + toBinary(Integer.parseInt(data), 15);
    }

    /**
     * 将一个int数字转换为二进制的字符串形式。
     * @param num 需要转换的int类型数据
     * @param digits 要转换的二进制位数，位数不足则在前面补0
     * @return 二进制的字符串形式
     */
    public static String toBinary(int num, int digits) {
        int value = 1 << digits | num;
        String bs = Integer.toBinaryString(value); //0x20 | 这个是为了保证这个string长度是6位数
        return  bs.substring(1);
    }


}
