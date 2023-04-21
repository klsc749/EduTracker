package util;

import javafx.scene.image.Image;

import java.util.HashMap;

public class ImageCache {
    private static HashMap<String, Image> imageCache = new HashMap<String, Image>();
    public static Image getImage(String path){
        if(imageCache.containsKey(path))
            return imageCache.get(path);
        Image image = new Image(path);
        imageCache.put(path, image);
        return image;
    }
}
