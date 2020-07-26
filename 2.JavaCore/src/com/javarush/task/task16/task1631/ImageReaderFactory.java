package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.BmpReader;
import com.javarush.task.task16.task1631.common.ImageReader;
import com.javarush.task.task16.task1631.common.ImageTypes;
import com.javarush.task.task16.task1631.common.JpgReader;
import com.javarush.task.task16.task1631.common.PngReader;

/**
 * Created by aleksandr on 16.03.17.
 */
public class ImageReaderFactory {

    public static ImageReader getImageReader(ImageTypes imageTypes) {
        if (imageTypes == ImageTypes.BMP) {
            return  new BmpReader();
        }
        if (imageTypes == ImageTypes.JPG) {
            return  new JpgReader();
        }
        if (imageTypes == ImageTypes.PNG) {
            return  new PngReader();
        }
        throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
