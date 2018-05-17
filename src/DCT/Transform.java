
package DCT;

public interface Transform {
    
    		void setSourceData (Object src);
		void calculate();
		void setKernel(double[][] kernel);
		Object getResult();
    
}
