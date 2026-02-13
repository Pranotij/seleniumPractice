package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

public class GifRecorder {

    private static final int FRAME_DELAY = 500; // milliseconds
    private static ImageWriter writer;
    private static ImageOutputStream outputStream;
    private static boolean isRecording = false;

    public static void start(String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // create reports folder

            Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("gif");
            writer = writers.next();

            outputStream = ImageIO.createImageOutputStream(file);
            writer.setOutput(outputStream);

            writer.prepareWriteSequence(null);

            isRecording = true;
            LogUtil.info("GIF recording started: " + filePath);

        } catch (Exception e) {
            LogUtil.error("Failed to start GIF recording: " + e.getMessage());
        }
    }

    public static void captureFrame(WebDriver driver) {
        if (!isRecording) return;

        try {
            BufferedImage image = ImageIO.read(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)
            );

            ImageWriteParam params = writer.getDefaultWriteParam();
            ImageTypeSpecifier type = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);

            IIOMetadata metadata = writer.getDefaultImageMetadata(type, params);

            String metaFormat = metadata.getNativeMetadataFormatName();
            IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree(metaFormat);

            IIOMetadataNode graphicControlExtensionNode = new IIOMetadataNode("GraphicControlExtension");
            graphicControlExtensionNode.setAttribute("delayTime", String.valueOf(FRAME_DELAY / 10));
            graphicControlExtensionNode.setAttribute("disposalMethod", "none");
            graphicControlExtensionNode.setAttribute("userInputFlag", "FALSE");
            graphicControlExtensionNode.setAttribute("transparentColorFlag", "FALSE");
            graphicControlExtensionNode.setAttribute("transparentColorIndex", "0");

            root.appendChild(graphicControlExtensionNode);
            metadata.setFromTree(metaFormat, root);

            writer.writeToSequence(new IIOImage(image, null, metadata), params);

        } catch (Exception e) {
            LogUtil.error("Failed to capture GIF frame: " + e.getMessage());
        }
    }

    public static void stop() {
        if (!isRecording) return;

        try {
            writer.endWriteSequence();
            outputStream.close();
            isRecording = false;

            LogUtil.info("GIF recording stopped");

        } catch (Exception e) {
            LogUtil.error("Failed to stop GIF recording: " + e.getMessage());
        }
    }
}
