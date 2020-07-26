package com.javarush.task.task31.task3105;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) return;

        HashMap<String, byte[]> zlist = new HashMap<>();

        ZipInputStream zis = new ZipInputStream(new FileInputStream(args[1]));
        ZipEntry temp;
        while ((temp = zis.getNextEntry()) != null) {
            byte[] content = new byte[1024 * 8];
            int count = 0;
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            while ((count = zis.read(content)) > 0) {
                baos.write(content,0,count);
            }
            zlist.put(temp.toString(), baos.toByteArray());
        }
        zis.close();
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1]));
        Path file = Paths.get(args[0]);
        boolean hasfile = false;
        for (HashMap.Entry<String, byte[]> entry : zlist.entrySet()) {
            if (Paths.get(entry.getKey()).getFileName().equals(file.getFileName())) {
                StringBuilder filename = new StringBuilder();
                if (Paths.get(entry.getKey()).getParent() != null)
                    filename.append(Paths.get(entry.getKey()).getParent()).append("/");
                filename.append(file.getFileName());
                zos.putNextEntry(new ZipEntry(filename.toString()));
                Files.copy(file, zos);
                hasfile = true;
            } else {
                zos.putNextEntry(new ZipEntry(entry.getKey()));
                zos.write(entry.getValue());
            }
        }
        if (!hasfile) {
            zos.putNextEntry(new ZipEntry("new/" + file.getFileName()));
            Files.copy(file, zos);
        }
        zos.close();
//        Path inputFile = Paths.get(args[0]);
//        String zipArchive = args[1];
//        List<ZipEntry> zipEntries = new ArrayList<>();
//        List<byte[]> buffers = new ArrayList<>();
//        try(ZipInputStream zis = new ZipInputStream(Files.newInputStream(Paths.get(zipArchive)))) {
//            ZipEntry zipEntry;
//            while ((zipEntry = zis.getNextEntry()) != null) {
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                byte[] buffer = new byte[8 * 1024];
//                int len;
//                while ((len = zis.read(buffer)) > 0) {
//                    out.write(buffer, 0, len);
//                }
//                zipEntries.add(zipEntry);
//                buffers.add(out.toByteArray());
//                out.close();
//            }
//        }
//        try(ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(Paths.get(zipArchive)))) {
//            zos.putNextEntry(new ZipEntry("new/" + inputFile.getFileName().toString()));
//            File file = new File(inputFile.toUri());
//            Files.copy(file.toPath(), zos);
//            zos.closeEntry();
//            for (int i = 0; i < zipEntries.size(); i++) {
//                zos.putNextEntry(zipEntries.get(i));
//                ByteArrayInputStream in = new ByteArrayInputStream(buffers.get(i));
//                byte[] buffer = new byte[8 * 1024];
//                int len;
//                while ((len = in.read(buffer)) > 0) {
//                    zos.write(buffer, 0, len);
//                }
//                zos.closeEntry();
//                in.close();
//            }
//        }
    }
}
