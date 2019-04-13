package com.splodgebox.youtube;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;

/**
 * Youtube Authenticator
 * This program is to be used to check
 * a users youtube channel to check if
 * they have input the unique code given
 *
 * @Splodgebox - 13/4/19
 */
public class YoutubeAuthenticator {

    @Getter @Setter
    private static UUID userKey;

    public static void main(String[] args) {
        try {
            generateCode();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        validateUser(scanner.next());
    }

    /**
     * Search their youtube about page
     * and check if it contains the generated
     * code
     * @param ID - ID of the youtube channel
     */
    private static void validateUser(String ID){
        try {
            URL url = new URL("https://www.youtube.com/channel/" + ID + "/about");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String inputLine;
            log("Loading...");
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains(getUserKey().toString())) {
                    log("You have been validated!");
                    in.close();
                    break;
                }
            }
            log("You are not a valid user!");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate a youtube key in which the
     * user can place on their 'about' section
     * on youtube so the program can check it
     * @throws InterruptedException
     */
    private static void generateCode() throws InterruptedException {
        log("Generating Code...");
        Thread.sleep(1000);
        UUID uuid = UUID.randomUUID();
        setUserKey(uuid);
        log("Your unique code is " + uuid);
        log("Paste your unique code in the description");
        log("of your youtube channel.");
        log("");
        log("Please input your youtube ID once you are done");
    }

    private static void log(String string) {
        System.out.println(string);
    }

}
