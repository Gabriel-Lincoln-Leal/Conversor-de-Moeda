package Main;

import Model.CurrencyFrom;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        String moedaDe;
        String moedaPara;
        double valor;

        Scanner sc = new Scanner(System.in);

        try {
            while (true) {

                System.out.println("""
               \s
                1. Dolar (USD) para Reais (BRL).
                2. Reais (BRL) para Dolar (USD).
                3. Euros (EUR) para Reais (BRL)
                4. Reais (BRL) para Euros (EUR).
                5. Rublos (RUB) para Reais (BRL).
                6. Reais (BRL) para Rublos (RUB).
               \s
                7. Sair.
               \s
                Escolha uma opção:""");

                int opcao = sc.nextInt();

                switch (opcao) {
                    case 1:
                        moedaDe = "USD";
                        moedaPara = "BRL";
                        break;
                    case 2:
                        moedaDe = "BRL";
                        moedaPara = "USD";
                        break;
                    case 3:
                        moedaDe = "EUR";
                        moedaPara = "BRL";
                        break;
                    case 4:
                        moedaDe = "BRL";
                        moedaPara = "EUR";
                        break;
                    case 5:
                        moedaDe = "RUB";
                        moedaPara = "BRL";
                        break;
                    case 6:
                        moedaDe = "BRL";
                        moedaPara = "RUB";
                        break;
                    case 7:
                        moedaDe = null;
                        moedaPara = null;
                        break;
                    default:
                        System.out.println("Digite apenas números de 1 à 7.\n");
                        continue;
                }
                if (moedaDe == null) {
                    break;
                }
                System.out.println("Informe o valor para converter:");
                valor = sc.nextDouble();

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

                System.out.println("Valor convertido de " + moedaDe + " para " + moedaPara + " é " + valor * taxa);

            }
        } catch (Exception e) {
            System.out.println("Digite apenas números.");
        }
    }
}
