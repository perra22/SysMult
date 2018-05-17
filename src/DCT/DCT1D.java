package DCT;

public class DCT1D implements Transform {

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

        for (int u = 0; u < N; u++) {
            double alfa = 0.0;
            if (u == 0) {
                alfa = Math.sqrt(1.0 / (double) N);
            } else {
                alfa = Math.sqrt(2.0 / (double) N);
            }

            double sum = 0.0;
            double Phi = 0.0;
            for (int x = 0; x < N; x++) {
                Phi = ((2.0*x + 1.0) * u * Math.PI) / (2.0 * N);
                sum += source[x] * Math.cos(Phi);
            }
            result[u] = sum*alfa;
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
