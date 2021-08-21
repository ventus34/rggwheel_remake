package ventus.rggwheel.services.spreadsheet;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class GoogleFormsPostService {

    static private Properties properties;

    public static void init() {
        File config = new File(System.getProperty("user.dir") + "/config.properties");
        properties = new Properties();
        try {
            properties.load(new FileInputStream(config));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String stateToPostParams(String prizeName, String inventory) {
        return "entry." + properties.getProperty("usernameEntryID") + "=" + System.getProperty("user.name")
                + "&entry." + properties.getProperty("prizenameEntryID") + "=" + prizeName
                + "&entry." + properties.getProperty("inventoryEntryID") + "=" + inventory;
    }

    public static void savePrizeToSpreadsheet(String prizeName, String inventory) {
        if(properties==null) init();
        String prePostData = stateToPostParams(prizeName, inventory);
        System.out.println("URL: " + properties.getProperty("googleFormURL"));
        System.out.println("DATA: " + prePostData);
        if (Boolean.parseBoolean(properties.getProperty("sendToForms"))) {
            try {
                URL url = new URL(properties.getProperty("googleFormURL"));
                URLConnection con = url.openConnection();
                HttpURLConnection http = (HttpURLConnection) con;
                con.setRequestProperty("User-Agent", "Java");
                http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.connect();
                byte[] postData = stateToPostParams(prizeName, inventory).getBytes(StandardCharsets.UTF_8);
                try (var wr = new DataOutputStream(con.getOutputStream())) {
                    wr.write(postData);
                }
                StringBuilder content;

                try (var br = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {

                    String line;
                    content = new StringBuilder();

                    while ((line = br.readLine()) != null) {

                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }

//            System.out.println(content.toString());
                http.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
