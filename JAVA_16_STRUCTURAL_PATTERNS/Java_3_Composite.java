package JAVA_16_STRUCTURAL_PATTERNS;

import java.util.ArrayList;
import java.util.List;

/**
 * COMPOSITE PATTERN – FILE SYSTEM EXAMPLE
 */
public class Java_3_Composite {
    public static void main(String[] args) {

        Directory root = new Directory("root");
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        Directory subDir = new Directory("subDir");
        File file3 = new File("file3.txt");

        root.add(file1);
        root.add(file2);
        root.add(subDir);
        subDir.add(file3);

        root.showStructure();
    }
}

// ==================== COMPONENT ====================
interface FileSystemNode {
    void showStructure();
}

// ==================== LEAF ====================
class File implements FileSystemNode {
    private final String name;
    public File(String name) { this.name = name; }
    @Override public void showStructure() { System.out.println("File: " + name); }
}

// ==================== COMPOSITE ====================
class Directory implements FileSystemNode {
    private final String name;
    private final List<FileSystemNode> children = new ArrayList<>();
    public Directory(String name) { this.name = name; }
    public void add(FileSystemNode node) { children.add(node); }
    public void remove(FileSystemNode node) { children.remove(node); }

    @Override
    public void showStructure() {
        System.out.println("Directory: " + name);
        for(FileSystemNode child : children) { child.showStructure(); }
    }
}

/*
================ COMPOSITE PATTERN – FEATURES & DESIGN =================
WHAT:
- Compose objects into tree structures to represent part-whole hierarchy.
- Example: File system (files + directories).
REAL SYSTEM USAGE:
- GUI components, file systems, XML/JSON parsing, org charts
*/
