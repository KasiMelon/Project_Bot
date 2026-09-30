import java.net.URI; //для создания правильного URL-адреса запроса
import java.net.http.HttpClient; //сам браузер, который отправляет запрос
import java.net.http.HttpRequest;//для настройки запроса(ссылка, метод GET)
import java.net.http.HttpResponse;//для получения ответа от сервера
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject; //позволяет превратить текст в удобный объект
import org.json.JSONArray; //если нужно вытаскивать прогноз по часам (массив)
import java.util.Scanner;

public class command_forecast {
    public void execute(Scanner scanner)
    {
        System.out.print("Введите название города: ");
        String city = scanner.nextLine().trim();

        System.out.print("На сколько дней нужен будет прогноз? (0 - на сегодня, максимум на 7 дней): ");
        int daysAhead;
        try {
            daysAhead = Integer.parseInt(scanner.nextLine().trim());
            if(daysAhead < 0 || daysAhead >7) {
                System.out.println("Ошибка: Можно запросить прогноз от 0 до 7 дней. ");
                return;
            }
        }catch(NumberFormatException e) {
            System.out.println("Ошибка: необходимо ввести число. ");
            return;
        }

        String apiKey = "a798293b968b4bae92f93346262509";

        try{
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

            int daysToRequest = daysAhead + 1;

            String url = "http://api.weatherapi.com/v1/forecast.json?key=" + apiKey
                    + "&q=" + encodedCity
                    + "&days=" + daysToRequest
                    + "&lang=ru";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if(response.statusCode() == 200) {
                    // Выводим полный текст ответа в консоль
                    System.out.println("Полный ответ от сервера:\n" + response.body());

                    JSONObject jsonResponse = new JSONObject(response.body());

                    JSONObject forecast = jsonResponse.getJSONObject("forecast");
                    JSONArray forecastday = forecast.getJSONArray("forecastday");

                    JSONObject targetDay = forecastday.getJSONObject(daysAhead);
                    String date = targetDay.getString("date");

                    JSONObject day = targetDay.getJSONObject("day");
                    double avgTemp = day.getDouble("avgtemp_c");
                    int humidity = day.getInt("avghumidity");

                    JSONObject condition = day.getJSONObject("condition");
                    String conditionText = condition.getString("text");

                    System.out.println("\nПрогноз для города " + city + " на " + date + ":");
                    System.out.println("Температура в среднем: " + avgTemp + "°C");
                    System.out.println("Влажность: " + humidity + "%");
                    System.out.println("На улице: " + conditionText + "\n");
                }else {
                    System.out.print("не удалось получить данные. Возможно город указан неверно.");
                }
        }catch(Exception e) {
            System.out.println("Ошибка сети: " + e.getMessage());
        }
    }
}
