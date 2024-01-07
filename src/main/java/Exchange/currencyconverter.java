package Exchange;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class currencyconverter {
    public static void main(String[] args) throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the type of currency you want to convert from");
            String from = sc.nextLine();
            System.out.println("Enter 3 favourite currencies you want to convert to");
            String to1 = sc.nextLine();
            String to2 = sc.nextLine();
            String to3 = sc.nextLine();
            String url = "http://api.exchangerate.host/live?access_key=c178786a8ec5fa9c7dd6205157a7d346&source=" + from.toUpperCase() + "&currencies=" + to1.toUpperCase() + "," + to2.toUpperCase() + "," + to3.toUpperCase() + "&format=1";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).get().build();
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            JSONObject jsn = new JSONObject(str);
            JSONObject result = jsn.getJSONObject("quotes");
            System.out.println(result);
            System.out.println("Enter the total Amount to convert");
            BigDecimal amt = sc.nextBigDecimal();
            System.out.println("choose a currency to convert the amount to ");
            System.out.println("select 1 for -" + to1);
            System.out.println("select 2 for -" + to2);
            System.out.println("select 3 for -" + to3);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    BigDecimal t1 = result.getBigDecimal((from + to1).toUpperCase());
                    BigDecimal total = t1.multiply(amt);
                    System.out.println(total);
                    break;
                case 2:
                    BigDecimal t2 = result.getBigDecimal((from + to2).toUpperCase());
                    BigDecimal total2 = t2.multiply(amt);
                    System.out.println(total2);
                    break;
                case 3:
                    BigDecimal t3 = result.getBigDecimal((from + to3).toUpperCase());
                    BigDecimal total3 = t3.multiply(amt);
                    System.out.println(total3);
                    break;
                default:
                    System.out.println("Please enter a valid number between 1 to 3");
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong.Please enter valid currencies");
        }

    }

}
