package convAndFilters;

public class RasterFilters {
	
	public static double[][] getIdentity(int size){
		double[][] matrix = new double[size][size];
		for(int i=0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				matrix[i][j] = 0;
			}
		}
		matrix[(size-1)/2][(size-1)/2] = 1.0;
		return matrix;
	}
	
	public static double[][] getLowPass(int size){
		double[][] matrix = new double[size][size];
		for(int i=0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				matrix[i][j] = (double)1.0/(size*size);
			}
		}
		return matrix;
	}
	
	public static double[][] getHighPass(int size){
		double[][] matrix = new double[size][size];
		for(int i=0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				matrix[i][j] = -1.0;
			}
		}
		matrix[(size-1)/2][(size-1)/2] = (size*size)-1;
		return matrix;
	}
	
	public static double[][] getSharpen(int size){
		double[][] matrix = new double[size][size];
		for(int i=0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				matrix[i][j] = -1.0/(size*size);
			}
		}
		matrix[(size-1)/2][(size-1)/2] = (2*size - 1.0)/(size*size);
		return matrix;
	}
	
	public static double[][] getGaussian(int size, double sigma){
		double[][] matrix = new double[size][size];
		for(int i=-size/2; i<=size/2; i++) {
			for(int j = -size/2; j<=size/2; j++) {
				double e1 = 1.0/(2*Math.PI*Math.pow(sigma, 2));
				double e2 = Math.pow(Math.E, -((i^2 + j^2)/(2*Math.pow(sigma, 2))));
				matrix[i+(size/2)][j+(size/2)] = e1*e2;
			}
		}
		return matrix;
	}
	
}
