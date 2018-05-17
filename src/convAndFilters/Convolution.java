package convAndFilters;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import DCT.Transform;

public class Convolution implements Transform{

	BufferedImage source;
	BufferedImage result;
	double[][] kernel;
	
	
	
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
		
		Transform myConvolution = new Convolution();
		myConvolution.setKernel(RasterFilters.getIdentity(3));
		myConvolution.setSourceData(originalImage);
		myConvolution.calculate();
		processedImage = (BufferedImage) myConvolution.getResult();
		
		
		JLabel label = new JLabel(new ImageIcon(originalImage)); 
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
		f2.setVisible(true);
		

	}

	public void setKernel(double[][] k) {
		kernel = k;
	}
	
	@Override
	public void setSourceData(Object src) {
		// TODO Auto-generated method stub
		source = (BufferedImage)src;
	}

	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		int n = kernel.length;
		//calculate the sum of values in kernel
		double sumK = calculateKernelSum(kernel, n);
		
		result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		double e1 = 1/sumK;
		for(int x=0; x<n;x++) {
			for(int y =0; y<n; y++) {
				if((x-n/2) >= 0 && (y-n/2) >=0) {
					
					double s = 0;
					for(int k = -n/2; k <= n/2; k++) {
						for(int l = -n/2; l <= n/2; l++) {
							s += (double)(source.getRaster().getSample(x + k, y + l, 0))*(double)kernel[k + (n/2)][l + (n/2)];
							
						}
					}
					double res = e1 * s;
					result.getRaster().setSample(x, y, 0, res);
					
				}
			}
		}
		
	}

	private double calculateKernelSum(double[][] kernel, int n) {
		// TODO Auto-generated method stub
		double sum = 0;
		for(int i = 0; i < n; i++) {
			for(int j =0; j< n; j++) {
				sum+=(double)kernel[i][j];
			}
		}
		return sum;
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return result;
	}

}
