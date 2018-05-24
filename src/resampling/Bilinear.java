package resampling;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import DCT.Transform;

public class Bilinear implements Transform {

	BufferedImage source;
	BufferedImage result;
	double M;
	
	public void setRatio(double ratio) {
		M = ratio;
	}
	
	@Override
	public void setSourceData(Object src) {
		// TODO Auto-generated method stub
		source = (BufferedImage)src;

	}

	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		int newWidth = (int)(source.getWidth() * M);
		int newWeight = (int)(source.getHeight() * M);
		result = new BufferedImage(newWidth, newWeight, source.getType());

		for(int x =0 ; x < newWidth ; x++) {
			for(int y = 0; y< newWeight ; y++) {
				if(x + 1 > source.getWidth() || y + 1 > source.getHeight()) {
					result.getRaster().setSample(x, y, 0,source.getRaster().getSample((int)(x/M), (int)(y/M), 0));
				}
				else {
					int x1 = (int) (x/M);
					int y1 = (int) (y/M);
					double a = x/M - x1;
					double b = y/M - y1;

					int res = (int)((1-a)*(1-b)*source.getRaster().getSample(x1, y1, 0))+
							(int)(a*(1-b)*source.getRaster().getSample(x1, y1+1, 0))+
							(int)((1-a)*b*source.getRaster().getSample(x1+1, y1, 0))+
							(int)(a*b*source.getRaster().getSample(x1+1, y1+1, 0)); 
					result.getRaster().setSample(x, y, 0, res);
				}
				
			}
		}
	}

	@Override
	public void setKernel(double[][] kernel) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
BufferedImage originalImage = null, processedImage = null;
		
		try {
			originalImage = ImageIO.read(new File("Sample.jpg"));
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Bilinear n = new Bilinear();
		n.setSourceData(originalImage);
		n.setRatio(2.5);
		n.calculate();
		processedImage = (BufferedImage)n.getResult();
		
		/*JLabel label = new JLabel(new ImageIcon(originalImage)); 
		JFrame f = new JFrame("Original picture"); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		f.getContentPane().add(label);
		f.pack(); f.setLocation(20,20); f.setVisible(true);
		
		JLabel label2 = new JLabel(new ImageIcon(processedImage)); 
		JFrame f2 = new JFrame("Quantized picture"); 
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		f2.getContentPane().add(label2);
		f2.pack();
		f2.setLocation(100,100);
		f2.setVisible(true);*/
		
		try {
			File outputfile = new File("Sample output bilinear.png"); ImageIO.write(processedImage, "png", outputfile);
			} 
		catch (IOException e)
		{ 
			e.printStackTrace();
			
		}
	}
	
}
