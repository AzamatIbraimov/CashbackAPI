package kg.nurtelecom.cashbackapi.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

public class UtilBase64Image {

    public static String encoder(MultipartFile multipartFile) {
        try {
            System.out.println(multipartFile.getContentType());
            String base64Image = "data:"+multipartFile.getContentType()+";base64," + Base64.getEncoder().encodeToString(multipartFile.getBytes());
            System.out.println(base64Image);
            return base64Image;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

//    public static void decoder(String base64Image, String pathFile) {
//        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
//            // Converting a Base64 String into Image byte array
//            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
//            imageOutFile.write(imageByteArray);
//        } catch (FileNotFoundException e) {
//            System.out.println("Image not found" + e);
//        } catch (IOException ioe) {
//            System.out.println("Exception while reading the Image " + ioe);
//        }
//    }

}
