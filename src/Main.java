import data.Instruction;
import tool.Parser;
import tool.SymbolMapper;
import tool.Translator;

import java.io.*;
import java.util.HashMap;

public class Main {
    private static final HashMap<String, String> symbolTable = new HashMap<String, String>();

    static {
        symbolTable.put("R0","0");
        symbolTable.put("R1","1");
        symbolTable.put("R2","2");
        symbolTable.put("R3","3");
        symbolTable.put("R4","4");
        symbolTable.put("R5","5");
        symbolTable.put("R6","6");
        symbolTable.put("R7","7");
        symbolTable.put("R8","8");
        symbolTable.put("R9","9");
        symbolTable.put("R10","10");
        symbolTable.put("R11","11");
        symbolTable.put("R12","12");
        symbolTable.put("R13","13");
        symbolTable.put("R14","14");
        symbolTable.put("R15","15");

        symbolTable.put("SP","0");
        symbolTable.put("LCL","1");
        symbolTable.put("ARG","2");
        symbolTable.put("THIS","3");
        symbolTable.put("THAT","4");

        symbolTable.put("SCREEN","16384");
        symbolTable.put("KBD","24576");


    }

    public static void main(String[] args) throws IOException {

        String file = "/Users/limingyang/IdeaProjects/hack-assembler/test/Pong.asm";
        String outputFile = "/Users/limingyang/IdeaProjects/hack-assembler/test/Pong.hack";

        Main main = new Main();
        main.readLines(file, outputFile, symbolTable, 1);
        main.readLines(file, outputFile, symbolTable, 2);
        System.out.println(symbolTable);
    }

    public void readLines(String file, String outputFile, HashMap<String, String> symbolTable, Integer readTimes) throws IOException
    {
        Parser parser = new Parser();
        Translator translator = new Translator();
        SymbolMapper symbolMapper = new SymbolMapper();

        // 1. BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        FileWriter fw=new FileWriter(new File(outputFile));
        //写入中文字符时会出现乱码
        BufferedWriter bw=new BufferedWriter(fw);

        String str = null;
        // 行号
        Integer lineNum = 0;
        // 新变量初始值
        Integer varNum = 16;

        // 2.逐行读取
        while((str = bufferedReader.readLine()) != null)
        {
            str = str.trim();
            System.out.println(lineNum+":" + str);

            // 空行或者注释直接忽略
            if(str.startsWith("//") || str.isEmpty()){
                continue;
            }
            if(readTimes == 1){
                // 第一次扫描,只看(LABEL)和对应的行数，并存储到Table中
                if(str.startsWith("(")){
                    symbolTable.put(str.substring(str.indexOf("(") + 1 ,str.indexOf(")")), String.valueOf(lineNum));
                    continue;
                }


            }else {
                // 第二次扫描,如果为C指令，直接用Parser解析，并用Translator解码，最后拼起来；
                // 如果为A指令，先看@后面的是否为需要替换的变量，先替换symbolTable的变量，再进行解码
                if(str.startsWith("(")){
                    continue;
                }
                if(str.startsWith("@")){

                    Instruction instruction = symbolMapper.mapToNumber(str, symbolTable, varNum);
                    varNum = instruction.getVarNum();
                    bw.write(instruction.getOut()+'\n');

                }else {
                    String out = "111" + translator.comp(parser.comp(str)) + translator.dest(parser.dest(str)) + translator.jump(parser.jump(str));

                    bw.write(out+'\n');
                }
            }

            // 行号更新
            lineNum ++;
        }

        // 3.close
        bw.close();
        fw.close();
        inputStream.close();
        bufferedReader.close();
    }

}