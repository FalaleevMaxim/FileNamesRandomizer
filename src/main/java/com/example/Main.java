package com.example;

import com.example.args.Args;
import com.example.args.ArgsProcessor;
import com.example.randomizers.*;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Set<String> DEFAULT_TYPES = new HashSet<>(Arrays.asList("jpg", "jpeg"));
    private static final int DEFAULT_LENGTH = 10;

    public static void main(String[] args) {
        Args process = ArgsProcessor.process(args);
        if (process.getPath() == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                process.setPath(fileChooser.getSelectedFile().toString());
            } else {
                return;
            }
        }
        RandomizerCombo randomizerCombo = randomizerByFlags(process.getRandFlags());

        File file = extractFile(process);
        Set<File> files = collectFiles(file, process.getFileTypes().isEmpty() ? DEFAULT_TYPES : process.getFileTypes(), process.isRecursive(), true);
        if (process.getPre() == null) process.setPre("");
        for (File f : files) {
            rename(f, process.getPre() + collectName(randomizerCombo, process.getLength() == null ? DEFAULT_LENGTH : process.getLength()));
        }
    }

    private static File extractFile(Args process) {
        File file;
        if (process.getPath() == null) {
            throw new IllegalArgumentException("Path not set");
        }
        file = new File(process.getPath());
        if (!file.exists()) throw new IllegalStateException("File '" + file + "' does not exist");
        return file;
    }

    private static Set<File> collectFiles(File file, Set<String> extensions, boolean recursive, boolean first) {
        FilenameFilter filter = (dir, name) -> {
            String[] split = name.split("\\.");
            if (split.length == 1) return true;
            return extensions.contains(split[split.length - 1].toLowerCase());
        };
        if (file.isDirectory()) {
            if (recursive || first)
                return Arrays.stream(file.listFiles())
                        .map(f -> collectFiles(f, extensions, recursive, false))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toSet());
            else
                return Collections.emptySet();
        }
        if (filter.accept(file.getParentFile(), file.getName()))
            return Collections.singleton(file);
        return Collections.emptySet();
    }

    private static void rename(File file, String newName) {
        String[] split = file.getName().split("\\.");
        newName = file.getParent() + File.separator + newName + '.' + split[split.length - 1];
        File dest = new File(newName);
        if (!file.renameTo(dest)) {
            System.out.println("Rename failed for " + file);
        }
    }

    private static String collectName(AbstractRandomizer randomizer, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(randomizer.random());
        }
        return sb.toString();
    }

    private static RandomizerCombo randomizerByFlags(Set<String> flags) {
        RandomizerCombo randomizerCombo;
        if (flags.isEmpty()) {
            randomizerCombo = new RandomizerCombo(Arrays.asList(new Numbers(), new LatinLower(), new LatinUpper()));
        } else {
            randomizerCombo = new RandomizerCombo();
            if (flags.contains("ll")) randomizerCombo.addRandomizer(new LatinLower());
            if (flags.contains("lu")) randomizerCombo.addRandomizer(new LatinUpper());
            if (flags.contains("cl")) randomizerCombo.addRandomizer(new CyrillicLower());
            if (flags.contains("cu")) randomizerCombo.addRandomizer(new CyrillicUpper());
            if (flags.contains("n")) randomizerCombo.addRandomizer(new Numbers());
        }
        return randomizerCombo;
    }
}