/* Alexandros Florides */

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        List<String> people = new ArrayList<>();
        Map<String, Person> charmap = new HashMap<>();

        // create a frame with appropriate tile and GridLayout of 1 row and 2 columns
        String title = "Star Wars API Characters - Alexandros Florides";
        JFrame f = new JFrame(title);
        f.setLayout(new GridLayout(1, 2));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // method that takes system's look and feel to make the application match with it
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SwingUtilities.updateComponentTreeUI(f);

        // creating a API object, initializing the page and count variables and
        // adding to JsonObject the result of calling getBuilder method of the
        // API object, with parameters "people" as path and the number of the
        // page as searchquery
        final API apiCalls = new API();
        JsonObject jsonObject;
        int page = 1;
        int count = 1;
        jsonObject = apiCalls.getBuilder("people", String.valueOf(page));

        // loop that iterates through each page of people path until the name of pair
        // "next" is null, meaning it's the last page. page variable is incrementing
        // each time by one
        while (jsonObject.get("next") != null && !jsonObject.get("next").isJsonNull()) {
            // adding to JsonObject the result of calling getBuilder method of the API
            // object, with parameters "people" as path and the number of the page as searchquery.
            // in JsonArray object is added the result of the name of pair "results" that holds
            // all details of each character
            jsonObject = apiCalls.getBuilder("people", String.valueOf(page));
            JsonArray characterresults = jsonObject.getAsJsonArray("results");

            // iterating through the JsonArray holding all the attributes of each
            // character and adding their name to a list next to a number indicating
            // its index
            for (int i = 0; i < characterresults.size(); i++) {
                JsonObject character = characterresults.get(i).getAsJsonObject();
                people.add(count + ") " + character.get("name").getAsString());

                // creating a new Person object and setting the necessary attribute
                // of the character to the appropriate setter
                Person person = new Person();
                person.setName(character.get("name").getAsString());
                person.setHeight(character.get("height").getAsString());
                person.setMass(character.get("mass").getAsString());
                person.setHairC(character.get("hair_color").getAsString());
                person.setSkinC(character.get("skin_color").getAsString());
                person.setEyeC(character.get("eye_color").getAsString());
                person.setBirthY(character.get("birth_year").getAsString());
                person.setGender(character.get("gender").getAsString());
                person.setFilms(character.get("films").getAsJsonArray());

                // adding to charmap a pair with key the name of each character
                // and value the Person object
                charmap.put(character.get("name").getAsString(), person);

                count++;
            }
            page++;
        }

        // creating a new JList holding all the retrieved characters, a
        // JScrollPane holding the JList and a JTextArea that will hold
        // the details of each character
        JList list = new JList(people.toArray());
        JScrollPane scrollPane = new JScrollPane(list);
        JTextArea infoarea = new JTextArea();

        // setting JTextArea component to not be editable and enabling
        // LineWrap to automatically change lines in case the text is
        // too long
        infoarea.setEditable(false);
        infoarea.setLineWrap(true);

        // adding the two panels to the frame
        f.add(scrollPane);
        f.add(infoarea);

        // mouse listener for the list containing character's name where on
        // double click it prints the details of the selected character to
        // the panel containing a JTextArea
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                // Double-click detected
                if (evt.getClickCount() == 2) {
                    //remove number that was placed in front of character's name
                    String removeNum = list.getSelectedValue().toString().substring(list.getSelectedValue().toString().indexOf(" ")+1);
                    // gets the value with key the name of a character of charmap
                    // Map, which is a Person object
                    infoarea.setText(charmap.get(removeNum).toString());
                }
            }
        });

        // setting the appropriate size of the frame and setting it to visible
        f.setSize(800, 400);
        f.setVisible(true);
    }
}

/* Alexandros Florides */