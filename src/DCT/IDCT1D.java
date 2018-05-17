package DCT;


public class IDCT1D implements Transform {

    double[] source;
    double[] result;

    @Override
    public void setSourceData(Object src) {
        source = (double[]) src;
    }

    @Override
    public void calculate() {
		// perform the calculations and store results in result[]
        int N = source.length;
        result = new double[N];

        for (int x = 0; x < N; x++) {
            double sum = 0.0;
            for (int u = 0; u < N; u++) {
                double alfa = 0.0;
                if (u == 0) {
                    alfa = Math.sqrt(1.0 / N);
                } else {
                    alfa = Math.sqrt(2.0 / N);
                }
                
                double Phi = ((2.0 * x + 1) * u * Math.PI) / (2.0 * N);
                sum += source[u] * alfa * Math.cos(Phi);
            }
            result[x] = sum;
        }

    }

    @Override
    public Object getResult() {
	// return the result data

        return result;
    }

	@Override
	public void setKernel(double[][] kernel) {
		// TODO Auto-generated method stub
		
	}
}
