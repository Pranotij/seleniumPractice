package utils;

import javax.imageio.metadata.IIOMetadataNode;

public class GifMetadataHelper {

    public static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
        for (int i = 0; i < rootNode.getLength(); i++) {
            if (rootNode.item(i).getNodeName().equalsIgnoreCase(nodeName)) {
                return (IIOMetadataNode) rootNode.item(i);
            }
        }

        IIOMetadataNode newNode = new IIOMetadataNode(nodeName);
        rootNode.appendChild(newNode);
        return newNode;
    }
}
