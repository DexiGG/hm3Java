package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Press 1 to create directory");
                String submenu = in.nextLine();
                switch (submenu) {
                    case "1":
                        System.out.println("Enter directory path in format \"C://SomeDir//NewDir\": ");
                        String path = in.nextLine();
                        System.out.println("Enter file name without extension: ");
                        String fileName = in.nextLine();
                        String tmpFile ="";
                        String tmpText="";
                        try {
                            File dir = new File(path);
                            boolean createdDir = dir.mkdir();
                            File file = new File(dir.getAbsolutePath()+"//"+(fileName+".txt"));
                            boolean createdFile = file.createNewFile();
                            System.out.println("Enter some text");
                            String text = in.nextLine();
                            try (FileWriter writer = new FileWriter(file)) {
                                if (createdFile) writer.append(text);
                                else writer.write(text);
                                System.out.println("Text wrote");
                                tmpFile=file.getAbsoluteFile().toString();

                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }

                        System.out.println("Enter 1 to see text in terminal");
                        System.out.println("Enter 2 to change word order in sentence");
                        String subsubmenu = in.nextLine();
                        switch (subsubmenu) {
                            case "1":
                                try (FileReader reader = new FileReader(new File(tmpFile))) {
                                    char[] buf = new char[256];
                                    int c;
                                    while((c = reader.read(buf))>0){

                                        if(c < 256){
                                            buf = Arrays.copyOf(buf, c);
                                        }
                                        System.out.print(buf);
                                        tmpText=buf.toString();
                                    }
                                    System.out.println();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;

                            case "2":
                                try (FileWriter writer = new FileWriter(tmpFile)){

                                    String[] parts = tmpText.split(" ");
                                    String first = parts[0];
                                    parts[0] = parts[parts.length - 1];
                                    parts[parts.length - 1] = first;
                                    String result = String.join(" ", parts);
                                    writer.write(result);
                                    System.out.println(result);
                                }
                                break;
                        }
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("You get error " + ex.getMessage());
        }
    }
}
