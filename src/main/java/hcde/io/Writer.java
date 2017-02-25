package hcde.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Writer {

    public static void write(String filePath, Map<Integer, List<Integer>> result) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = format(result);
        Files.write(path, lines);
    }

    private static List<String> format(Map<Integer, List<Integer>> result) {
        List<String> lines = new LinkedList<>();
        lines.add(Integer.toString(result.size()));

        for(Integer cacheId : result.keySet()) {
            List<Integer> videos = result.get(cacheId);
            StringBuilder string = new StringBuilder(cacheId.toString());
            for(Integer videoId : videos) {
                string.append(" " + videoId);
            }
            lines.add(string.toString());
        }
        return lines;
    }

}
