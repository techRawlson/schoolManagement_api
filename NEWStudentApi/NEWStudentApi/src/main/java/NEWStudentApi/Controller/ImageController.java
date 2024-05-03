package NEWStudentApi.Controller;

import NEWStudentApi.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/{studentId}")
    public ResponseEntity<String> uploadImage(@PathVariable Long studentId, @RequestParam("file") MultipartFile file) {
        try {
            imageService.saveImage(studentId, file);
            return ResponseEntity.ok("Image uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image: " + e.getMessage());
        }
    }
    @PutMapping("/{studentId}")
    public ResponseEntity<String> updateImage(@PathVariable Long studentId, @RequestParam("file") MultipartFile file) {
        try {
            imageService.updateImage(studentId, file);
            return ResponseEntity.ok("Image updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update image: " + e.getMessage());
        }
    }

    // GET method for retrieving image data by student ID
    @GetMapping("/{studentId}")
    public ResponseEntity<byte[]> getImageByStudentId(@PathVariable Long studentId) {
        try {
            byte[] imageData = imageService.getImageByStudentId(studentId);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Failed to retrieve image: " + e.getMessage()).getBytes());
        }
    }
}
