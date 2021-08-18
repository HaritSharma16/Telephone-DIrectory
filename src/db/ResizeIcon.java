package db;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ResizeIcon {
	 
	public static ImageIcon ResizeIcon(String imgPath,JLabel label)
	{
		ImageIcon myImage=new ImageIcon(imgPath);
		Image img=myImage.getImage();
		Image newImg=img.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon image=new ImageIcon(newImg);
		return image;
		
	}
public static ImageIcon resizeImage(Image img,JLabel label)
{
	Image newImg=img.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
	ImageIcon image=new ImageIcon(newImg);
	return image;
}
}
