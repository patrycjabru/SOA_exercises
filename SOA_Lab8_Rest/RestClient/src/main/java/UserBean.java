import DTO.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.faces.annotation.FacesConfig;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@RequestScoped
@ManagedBean
@Named("userBean")
public class UserBean
{
    User user;

    String baseUrl = "http://localhost:8080/rest_war_exploded";
    ObjectMapper objectMapper = new ObjectMapper();

    public String getUserById(int id) throws IOException {
        URL url = new URL(baseUrl + "/users/"+id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        String responseStr = response.toString();

        user = objectMapper.readValue(responseStr, User.class);
        in.close();
        return "userdata";
    }

    public String addUser(String name, int age, String avatar) throws IOException {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        user.setAvatar(avatar);

        URL url = new URL(baseUrl + "/users/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

        OutputStream os = con.getOutputStream();
        byte[] input = objectMapper.writeValueAsBytes(user);
        os.write(input, 0, input.length);

        int responseCode = con.getResponseCode();

        return "newuseradded";
    }

    public String deleteUser(int id) throws IOException {
        URL url = new URL(baseUrl + "/users/"+id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        in.close();
        return "userdeleted";
    }

    public String updateUser(int id, String name, int age, String avatar) throws IOException {
        User user = new User();
        user.setId(id);
        user.setAge(age);
        user.setName(name);
        user.setAvatar(avatar);

        URL url = new URL(baseUrl + "/users/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

        OutputStream os = con.getOutputStream();
        byte[] input = objectMapper.writeValueAsBytes(user);
        os.write(input, 0, input.length);

        int responseCode = con.getResponseCode();

        return "userupdated";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    //TODO fix it
    public String patchUserAge(int id, int age) throws IOException {
//
//        URL url = new URL(baseUrl + "/users"+"?id="+id+"&age="+age);
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
//        con.setRequestMethod("POST");
//        con.setDoOutput(true);
//        con.setRequestProperty("Content-Type", "application/json");
//
//        int responseCode = con.getResponseCode();
//        return "userageupdated";
        return null;
    }

    //TODO fix it
    public BufferedImage getAvatar(int id) throws IOException {
        URL url = new URL(baseUrl + "/users/"+id+"/avatar");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        String responseStr = response.toString();

        BufferedImage image = objectMapper.readValue(responseStr, BufferedImage.class);
        in.close();
        return image;
    }
}
