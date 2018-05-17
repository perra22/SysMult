package DCT;

public class DCT2D implements Transform {

    double[][] source;
    double[][] result;

    @Override
    public void setSourceData(Object src)
    {
        source = (double[][]) src;
    }

    @Override
    public void calculate() {

        int N = source.length;
        result = new double[N][N];

        for (int u = 0; u < N; u++) {
        for (int v = 0; v < N; v++) {
            double alfau = 0.0;
            if (u == 0) {
                alfau = Math.sqrt(1.0 / N);
            } else {
                alfau = Math.sqrt(2.0 / N);
            }
            double alfav = 0.0;
            if (v == 0) {
                alfav = Math.sqrt(1.0 / N);
            } else {
                alfav = Math.sqrt(2.0 / N);
            }

            double sum = 0.0;
            for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                    double Phiu= ((2.0 * x + 1.0) * u * Math.PI)/(2.0 * N);
                    double Phiv= ((2.0 * y + 1.0) * v * Math.PI)/(2.0 * N);
                    sum += source[x][y] * Math.cos(Phiu) * Math.cos(Phiv);
            }
            }
            result[u][v] = sum * alfau * alfav;
        }
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
