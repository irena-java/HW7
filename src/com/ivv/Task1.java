package com.ivv;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task1 {

    public static void main(String[] args) {
        try {
            Map<String, Integer> map = new HashMap<>();
            File file = new File("/Users/Ira/Desktop/urls.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                String key = "";
                if (str.contains("/")) {
                    int domenEnd = str.indexOf("/");
                    key = str.substring(0, domenEnd);
                } else {
                    key = str;
                }
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
            }
            scanner.close();

            List<String> top = map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .skip(map.size() - 11)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            for (int i = top.size() - 1; i > 0; i--) {
                System.out.println(top.get(i));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}