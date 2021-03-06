package com.infodragon.image;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
* 归档章生成
*/
@SuppressWarnings("restriction")
public class ImBuider {
	/**
	* 生成图片
	*
	* @param cellsValue 以二维数组形式存放 表格里面的值
	* @param path 文件保存路径
	*/
	public static void myGraphicsGeneration(String cellsValue[][], String path) {
		//获取字符长度
		int flength = 0;
		for(int i=0; i<2; i++) {
			String[] word = cellsValue[i];
			for(int l=0; l<word.length; l++) {
				if(flength<word[l].length()) {
					flength = word[l].length();
				}
			}
		}
		System.out.println(flength);
		// 字体大小------小五（12px）
		int fontTitileSize = 24;
		// 横线的行数
		int totalrow = 3;
		// 竖线的行数
		int totalcol =cellsValue[0].length+1;
		// 行高------单行8MM
		int rowheight = 56;
		// 图片高度-------高度16MM
		int imageHeight = 118;
		// 起始高度
		int startHeight = 0;
		// 起始宽度
		int startWidth = 0;
		//设置列宽
		int colwidth = 112;
		//设置自动图片宽度
		int imageWidth = colwidth*(totalcol-1)+6;
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
//		graphics.setColor(new Color(255, 255, 255,0));
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(new Color(255, 0, 0,0));
		//画横线
		for (int j = 0; j < totalrow; j++) {
			graphics.setColor(Color.red);
			graphics.drawLine(startWidth, startHeight + (j) * rowheight, startWidth + colwidth * (totalcol-1)+j, startHeight + (j) * rowheight);
			graphics.drawLine(startWidth, startHeight + (j) * rowheight+1, startWidth + colwidth * (totalcol-1)+j, startHeight + (j) * rowheight+1);
			graphics.drawLine(startWidth, startHeight + (j) * rowheight+2, startWidth + colwidth * (totalcol-1)+j, startHeight + (j) * rowheight+2);
			graphics.drawLine(startWidth, startHeight + (j) * rowheight+3, startWidth + colwidth * (totalcol-1)+j, startHeight + (j) * rowheight+3);
			graphics.drawLine(startWidth, startHeight + (j) * rowheight+4, startWidth + colwidth * (totalcol-1)+j, startHeight + (j) * rowheight+4);
			graphics.drawLine(startWidth, startHeight + (j) * rowheight+5, startWidth + colwidth * (totalcol-1)+j, startHeight + (j) * rowheight+5);

		}
		//画竖线
		for (int k = 0; k < totalcol+1; k++) {
			graphics.setColor(Color.red);
			graphics.drawLine(startWidth + k * colwidth, startHeight, startWidth + k * colwidth, startHeight + rowheight * (totalrow-1));
			graphics.drawLine(startWidth+1 + k * colwidth, startHeight, startWidth + k * colwidth+1, startHeight + rowheight * (totalrow-1));
			graphics.drawLine(startWidth+2 + k * colwidth, startHeight, startWidth + k * colwidth+2, startHeight + rowheight * (totalrow));
			graphics.drawLine(startWidth+3 + k * colwidth, startHeight, startWidth + k * colwidth+3, startHeight + rowheight * (totalrow+1));
			graphics.drawLine(startWidth+4 + k * colwidth, startHeight, startWidth + k * colwidth+4, startHeight + rowheight * (totalrow+2));
			graphics.drawLine(startWidth+5 + k * colwidth, startHeight, startWidth + k * colwidth+5, startHeight + rowheight * (totalrow+3));
		}
		//设置字体
		Font font = new Font("宋体", Font.BOLD, fontTitileSize);
		graphics.setFont(font);
		graphics.setColor(Color.RED);
		//写入内容
		for (int n = 0; n < cellsValue.length; n++) {
			for (int l = 0; l < cellsValue[n].length; l++) {
				if (n == 0) {
					font = new Font("宋体", Font.BOLD, fontTitileSize);
					graphics.setFont(font);

				} else {
					font = new Font("宋体", Font.BOLD, fontTitileSize);
					graphics.setFont(font);

				}
				graphics.drawString(cellsValue[n][l].toString(), startWidth + colwidth * l+6, startHeight + rowheight * (n+1)-15);
			}
		}
		// 保存图片
		createImage(image, path);
	}
	/**
	* 将图片保存到指定位置
	*
	* @param image缓冲文件类
	* @param fileLocation 文件位置
	*/
	public static void createImage(BufferedImage image, String fileLocation) {
		try {
			FileOutputStream fos = new FileOutputStream(fileLocation);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(image);
			bos.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	public static void main(String[] args) {
		String tableData1[][] = {{"TIOMN", "2019年","与思维来"},{"abcde", "永久", "になふちせな"}};
        ImBuider.myGraphicsGeneration(tableData1, "C:\\Users\\tm1612\\Desktop\\20190618.png");
        ImgManipulate imgManipulate = new ImgManipulate();
        imgManipulate.transferAlpha2File("C:\\Users\\tm1612\\Desktop\\20190618.png", "C:\\Users\\tm1612\\Desktop\\20190618.png");
	}
}