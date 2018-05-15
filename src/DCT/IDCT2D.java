package DCT;


public class IDCT2D implements Transform {

    double[][] source;
    double[][] result;

    @Override
    public void setSourceData(Object src) {
        source = (double[][]) src;
    }

    @Override
    public void calculate() {

        int N = source.length;
        result = new double[N][N];

        for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            double sum = 0.0;
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

                double Phiu= ((2.0 * x + 1.0) * u * Math.PI)/(2.0 * N);
                double Phiv= ((2.0 * y + 1.0) * v * Math.PI)/(2.0 * N);
                sum += alfau * alfav * source[u][v] * Math.cos(Phiu) * Math.cos(Phiv);
            }
            }
            
            result[x][y] = sum;
        }
        }
    }

    @Override
    public Object getResult() {
        // return the result data

        return result;
    }
}
