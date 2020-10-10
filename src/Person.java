/* Alexandros Florides */

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// class with setters for the attributes of a character
public class Person {

    private String Name;
    private String Height;
    private String Mass;
    private String HairC;
    private String SkinC;
    private String EyeC;
    private String BirthY;
    private String Gender;
    private JsonArray Films;

    public void setName(String name) { Name =  name; }

    public void setHeight(String height) { Height =  height; }

    public void setMass(String mass) { Mass =  mass; }

    public void setHairC(String hairC) { HairC =  hairC; }

    public void setSkinC(String skinC) { SkinC =  skinC; }

    public void setEyeC(String eyeC) { EyeC =  eyeC; }

    public void setBirthY(String birthY) { BirthY =  birthY; }

    public void setGender(String gender) { Gender =  gender; }

    public void setFilms(JsonArray films) { Films =  films; }

    // method that returns all relevant details of the selected character
    public String toString() {
        return "Name: "+ Name + "\nHeight: " + Height + "\nMass: " + Mass+ "\nHair Color: " + HairC +
                "\nSkin Color: " + SkinC + "\nEye Color: " + EyeC + "\nBirth Year: " + BirthY + "\nGender: " +
                Gender + "\nFilms: " + returnFilms(Films).toString().replace("[", "").replace("]", "");
    }

    // method that takes as parameter a JsonArray, which holds the url of the films a
    // character starred in and returns list of strings of the film's name
    public List<String> returnFilms(JsonArray film){
        final API apiCalls = new API();
        List<String> filmtitles = new ArrayList<>();

        JsonObject jsonObject;
        try {
            // iterate through the urls of the films and using it on innerRequest method of
            // API to receive and it in a list the title of each film
            for (int k=0; k<film.size(); k++){
                jsonObject = apiCalls.innerRequest(film.get(k).getAsString());
                filmtitles.add(jsonObject.getAsJsonPrimitive("title").getAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmtitles;
    }
}

/* Alexandros Florides */