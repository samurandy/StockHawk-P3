package com.udacity.stockhawk.mock;

        import java.io.BufferedReader;
        import java.io.ByteArrayInputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.nio.charset.StandardCharsets;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;
        import java.util.Locale;
        import java.util.logging.Level;

        import yahoofinance.Utils;
        import yahoofinance.YahooFinance;
        import yahoofinance.histquotes.HistoricalQuote;
        import yahoofinance.quotes.stock.StockQuote;

public class MockUtils {

    public static String mockHistory =
            "08/05/2017,68.970001,69.559998,68.040001,68.379997,20913200,68.379997\n" +
                    "01/05/2017,68.68,69.709999,68.489998,69,24889100,69\n" +
                    "24/04/2017,67.480003,69.139999,67.099998,68.459999,31679200,68.459999\n" +
                    "17/04/2017,65.040001,66.699997,64.889999,66.400002,21819600,66.400002\n" +
                    "10/04/2017,65.610001,65.860001,64.849998,64.949997,17734200,64.949997\n" +
                    "03/04/2017,65.809998,66.349998,65.190002,65.68,17311600,65.68\n" +
                    "27/03/2017,64.629997,66.190002,64.349998,65.860001,17640600,65.860001\n" +
                    "20/03/2017,64.910004,65.5,64.120003,64.980003,20152300,64.980003\n" +
                    "13/03/2017,65.010002,65.239998,64.150002,64.870003,24768900,64.870003\n" +
                    "06/03/2017,63.970001,65.260002,63.810001,64.93,19210200,64.93\n" +
                    "27/02/2017,64.540001,64.989998,63.619999,64.25,21660900,64.25\n" +
                    "21/02/2017,64.610001,64.949997,64.050003,64.620003,20146200,64.620003\n" +
                    "13/02/2017,64.239998,65.239998,64.019997,64.620003,20932400,64.620003\n" +
                    "06/02/2017,63.5,64.440002,63.139999,64,19797000,63.614338\n" +
                    "30/01/2017,65.690002,65.790001,62.75,63.68,34544400,63.296265\n" +
                    "23/01/2017,62.700001,65.910004,62.57,65.779999,31963100,65.383614\n" +
                    "17/01/2017,62.68,62.98,62.029999,62.740002,22249800,62.361931\n" +
                    "09/01/2017,62.759998,63.400002,61.950001,62.700001,20151400,62.322174\n" +
                    "03/01/2017,62.790001,63.150002,62.029999,62.84,21708200,62.461327\n" +
                    "27/12/2016,63.209999,64.07,62.029999,62.139999,15561700,61.765545\n" +
                    "19/12/2016,62.560001,64.099998,62.419998,63.240002,22408600,62.858921\n" +
                    "12/12/2016,61.82,63.450001,61.720001,62.299999,31278500,61.92458\n" +
                    "05/12/2016,59.700001,61.990002,59.560001,61.970001,24567700,61.596573\n" +
                    "28/11/2016,60.34,61.41,58.799999,59.25,27562500,58.89296\n" +
                    "21/11/2016,60.5,61.259998,60.130001,60.529999,18279400,60.165245\n" +
                    "14/11/2016,59.02,61.139999,57.279999,60.349998,32876800,59.986332\n" +
                    "07/11/2016,59.779999,60.779999,57.630001,59.02,40164500,58.270695\n" +
                    "31/10/2016,60.16,60.419998,58.52,58.709999,24682400,57.96463\n" +
                    "24/10/2016,59.939999,61.369999,59.580002,59.869999,36234000,59.109901\n" +
                    "17/10/2016,57.360001,60.450001,56.66,59.66,39069100,58.902569\n" +
                    "10/10/2016,57.91,58.389999,56.32,57.419998,23917500,56.691006\n" +
                    "03/10/2016,57.41,57.98,56.970001,57.799999,18460600,57.066181\n" +
                    "26/09/2016,57.080002,58.169998,56.68,57.599998,25132900,56.868721\n" +
                    "19/09/2016,57.27,58,56.75,57.43,22361100,56.700882\n" +
                    "12/09/2016,56,57.630001,55.610001,57.25,30989900,56.523167\n" +
                    "06/09/2016,57.779999,57.84,56.209999,56.209999,22257900,55.496368\n" +
                    "29/08/2016,58.18,58.599998,57.009998,57.669998,19836700,56.937832\n" +
                    "22/08/2016,57.599998,58.700001,57.259998,58.029999,18325900,57.293262\n" +
                    "15/08/2016,58.009998,58.5,57.200001,57.619999,18029800,56.888466\n" +
                    "08/08/2016,58.060001,58.5,57.619999,57.939999,18393700,56.850075\n" +
                    "01/08/2016,56.599998,58.209999,56.139999,57.959999,27824700,56.869701\n" +
                    "25/07/2016,56.470001,57.290001,55.720001,56.68,30825200,55.613781\n" +
                    "18/07/2016,53.700001,56.84,52.93,56.57,47919500,55.505848\n" +
                    "11/07/2016,52.5,54,52.470001,53.700001,26302700,52.689838\n" +
                    "05/07/2016,50.830002,52.360001,50.389999,52.299999,25237500,51.316174\n" +
                    "27/06/2016,49.099998,51.720001,48.040001,51.16,33917800,50.197617\n" +
                    "20/06/2016,50.639999,52.060001,49.52,49.830002,52210800,48.892639\n" +
                    "13/06/2016,49.580002,50.720001,49.060001,50.130001,47290300,49.186993\n" +
                    "06/06/2016,51.990002,52.73,51.040001,51.48,21279600,50.511597\n" +
                    "31/05/2016,52.259998,53,51.599998,51.790001,27225000,50.815769\n" +
                    "23/05/2016,50.599998,52.490002,49.98,52.32,25381100,51.335796\n" +
                    "16/05/2016,50.799999,51.959999,49.82,50.619999,24098200,49.667774\n" +
                    "09/05/2016,50.490002,51.900002,50,51.080002,22315300,49.771008\n" +
                    "02/05/2016,50,50.75,49.459999,50.389999,26787700,49.098686\n" +
                    "25/04/2016,51.779999,52.349998,49.349998,49.869999,40335000,48.59201\n" +
                    "18/04/2016,55.490002,56.77,50.77,51.779999,50937200,50.453064\n" +
                    "11/04/2016,54.490002,55.919998,53.759998,55.650002,23369400,54.223896\n" +
                    "04/04/2016,55.43,55.66,54.209999,54.419998,20156400,53.025414\n" +
                    "28/03/2016,54.209999,55.639999,53.330002,55.57,22943400,54.145943\n" +
                    "21/03/2016,53.25,54.330002,52.93,54.209999,21782200,52.820793\n" +
                    "14/03/2016,52.709999,55,52.630001,53.490002,34545900,52.119247\n" +
                    "07/03/2016,51.560001,53.07,50.580002,53.07,34231600,51.710011\n" +
                    "29/02/2016,51.349998,52.970001,50.66,52.029999,30286000,50.696659\n" +
                    "22/02/2016,52.279999,53,50.200001,51.299999,29966700,49.985367\n" +
                    "16/02/2016,50.900002,52.950001,50.130001,51.82,34703800,50.492043\n" +
                    "08/02/2016,49.549999,50.68,48.189999,50.5,45477900,48.855095\n" +
                    "01/02/2016,54.880001,55.09,49.560001,50.16,53415600,48.526169\n" +
                    "25/01/2016,51.939999,55.09,51.02,55.09,49301800,53.295586\n" +
                    "19/01/2016,51.48,52.330002,49.099998,52.290001,46146100,50.586792\n" +
                    "11/01/2016,52.509998,54.07,50.34,50.990002,52608700,49.329136\n" +
                    "04/01/2016,54.32,55.389999,52.07,52.330002,46539100,50.625488\n" +
                    "28/12/2015,55.349998,56.849998,54.98,55.48,24605900,53.672882\n" +
                    "21/12/2015,54.880001,55.959999,54.23,55.669998,25601700,53.856693\n" +
                    "14/12/2015,54.330002,56.790001,53.68,54.130001,50016000,52.366859\n" +
                    "07/12/2015,55.790001,56.099998,54.009998,54.060001,34226200,52.299137\n" +
                    "30/11/2015,54.540001,56.23,53.93,55.91,45212100,54.088879\n" +
                    "23/11/2015,54.25,54.459999,53.580002,53.93,20712500,52.17337\n" +
                    "16/11/2015,53.080002,54.66,52.849998,54.189999,31744600,52.4249\n" +
                    "09/11/2015,54.549999,54.869999,52.529999,52.84,39304400,50.776623\n" +
                    "02/11/2015,52.849998,54.98,52.619999,54.919998,33657800,52.775398\n" +
                    "26/10/2015,52.529999,54.369999,52.5,52.639999,47891100,50.584435\n" +
                    "19/10/2015,47.419998,54.07,47.02,52.869999,55394000,50.80545\n" +
                    "12/10/2015,46.98,47.540001,46.5,47.509998,23618800,45.654755\n" +
                    "05/10/2015,45.75,47.540001,45.700001,47.110001,30294200,45.270378\n" +
                    "28/09/2015,43.830002,45.57,43.049999,45.57,33166600,43.790512\n" +
                    "21/09/2015,43.619999,44.73,43.27,43.939999,25739700,42.224163\n" +
                    "14/09/2015,43.43,45,42.860001,43.48,34364400,41.782127\n" +
                    "08/09/2015,43.299999,44.400002,42.75,43.48,31109600,41.782127\n" +
                    "31/08/2015,43.560001,43.98,41.66,42.610001,37445200,40.946102\n" +
                    "24/08/2015,40.450001,44.150002,39.720001,43.93,60393600,42.214554\n" +
                    "17/08/2015,46.810001,47.450001,43.07,43.07,36481800,41.388138\n" +
                    "10/08/2015,46.950001,47.490002,45.709999,47,25319800,44.868793\n" +
                    "03/08/2015,46.98,48.41,46.259998,46.740002,26204100,44.620583\n" +
                    "27/07/2015,45.939999,47.400002,44.790001,46.700001,37191100,44.582397\n" +
                    "20/07/2015,46.650002,47.330002,45.099998,45.939999,39766600,43.856857\n" +
                    "13/07/2015,44.98,46.779999,44.950001,46.619999,26685400,44.506023\n" +
                    "06/07/2015,43.959999,45.220001,43.32,44.610001,31364200,42.587166\n" +
                    "29/06/2015,45.040001,45.23,43.939999,44.400002,30030700,42.386692\n" +
                    "22/06/2015,46.330002,46.720001,45.029999,45.259998,30311300,43.207691\n" +
                    "15/06/2015,45.450001,46.830002,45.02,46.099998,37104800,44.009602\n" +
                    "08/06/2015,46.299999,46.919998,45.459999,45.970001,25244700,43.885498\n" +
                    "01/06/2015,47.060001,47.77,45.84,46.139999,26294800,44.047791\n" +
                    "26/05/2015,46.830002,48.02,46.189999,46.860001,28180200,44.735142\n" +
                    "18/05/2015,47.98,48.220001,46.82,46.900002,25178100,44.773331";

    public static List<HistoricalQuote> getHistory() throws IOException {
        List<HistoricalQuote> history = new ArrayList<>();
        ByteArrayInputStream byteArrayInputStream
                = new ByteArrayInputStream(MockUtils.mockHistory.getBytes(StandardCharsets.UTF_8));

        InputStreamReader is = new InputStreamReader(byteArrayInputStream);

        BufferedReader br = new BufferedReader(is);
        br.readLine(); // skip the first line
        // Parse CSV
        for (String line = br.readLine(); line != null; line = br.readLine()) {

            YahooFinance.logger.log(Level.INFO, ("Parsing CSV line: " + Utils.unescape(line)));
            HistoricalQuote historicalQuote = MockUtils.parseCSVLine(line);
            history.add(historicalQuote);
        }

        return history;
    }


    private static HistoricalQuote parseCSVLine(String line) {
        String[] data = line.split(YahooFinance.QUOTES_CSV_DELIMITER);
        return new HistoricalQuote("STOCK NAME",
                parseHistDate(data[0]),
                Utils.getBigDecimal(data[1]),
                Utils.getBigDecimal(data[3]),
                Utils.getBigDecimal(data[2]),
                Utils.getBigDecimal(data[4]),
                Utils.getBigDecimal(data[6]),
                Utils.getLong(data[5])
        );
    }

    private static Calendar parseHistDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        try {
            if (isParseable(date)) {
                Calendar c = Calendar.getInstance();
                c.setTime(format.parse(date));
                return c;
            }
        } catch (ParseException ex) {
            YahooFinance.logger.log(Level.WARNING, "Failed to parse hist date: " + date);
            YahooFinance.logger.log(Level.FINEST, "Failed to parse hist date: " + date, ex);
        }
        return null;
    }

    private static boolean isParseable(String data) {
        return !(data == null || data.equals("N/A") || data.equals("-")
                || data.equals("") || data.equals("nan"));
    }
}
