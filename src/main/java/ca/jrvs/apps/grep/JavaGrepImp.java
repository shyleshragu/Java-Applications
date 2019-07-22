package ca.jrvs.apps.grep;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepImp implements JavaGrep {

    private String rootPath;
    private String regex;
    private String outFile;
    private BufferedWriter wri;

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: regex rootPath outFile");
        }

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void process() throws IOException {

        List<String> matchedLines = new ArrayList<>();

        for (File file : listFiles(getRootPath())) {
            for (String line : readLines(file)) {
                if (containsPattern(line)) {
                    matchedLines.add(line);
                }
            }
        }
        writeToFile(matchedLines);


    }


    @Override
    public List<File> listFiles(String rootDir) {
        List<File> files = new ArrayList<>();

        File f = new File(rootDir);

        //Lambda implementation
        return files = Stream.of(f.listFiles()).collect(Collectors.toList());
    }




    @Override
    public List<String> readLines(File inputFile) throws IOException {
        List<String> lines = new ArrayList<>();

        //Lambda implementation
        BufferedReader readl = new BufferedReader((new FileReader(inputFile)));
        return lines = readl.lines().collect(Collectors.toList());
    }



    @Override
    public boolean containsPattern(String line) {
        return line.matches(regex);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        //BufferedWriter wri;

        wri = new BufferedWriter(new FileWriter(getOutFile()));


        wri.write(String.valueOf(lines));

        wri.newLine();


        wri.close();

    }

    //getter for rootpath
    @Override
    public String getRootPath() {
        return rootPath;
    }

    //setter for rootpath
    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    //getter for regex
    @Override
    public String getRegex() {
        return regex;
    }

    //setter for Regex
    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    //getter for OutFile
    @Override
    public String getOutFile() {
        return outFile;
    }

    //setter for OutFile
    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}


