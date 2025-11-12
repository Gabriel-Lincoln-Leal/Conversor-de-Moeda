import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String moedaDe, moedaPara;
        double valor;

        Scanner sc = new Scanner(System.in);
        System.out.println("Converter moedaDe:");
        moedaDe = sc.nextLine();
        System.out.println("Converter para:");
        moedaPara = sc.nextLine();
        System.out.println("Quantidade:");
        valor = sc.nextDouble();
        System.out.println(moedaDe);
        System.out.println(moedaPara);
        sc.close();

        String url = "https://v6.exchangerate-api.com/v6/4a7036080647de485e397b77/latest/" + moedaDe;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        CurrencyFrom moeda = gson.fromJson(response.body(), CurrencyFrom.class);

        Map<String, Double> rates = moeda.getConversionRates();

        Double taxa = rates.get(moedaPara);

        System.out.println(valor * taxa);

        }
    }





