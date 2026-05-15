package handler.utils;

import java.io.*;
import java.net.URL;

public class Utils {

    public static String loadFileAsString(String path)
    {
        StringBuilder builder = new StringBuilder();

        try{
            System.out.println(path);
            URL url = Utils.class.getResource(path);
            System.out.println(url);
            InputStream is = url.openStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = br.readLine()) != null)
            {
                builder.append(line + "\n");
            }
            br.close();
        }catch(IOException e)
        {
           e.printStackTrace();
        }

        return builder.toString();
    }

    public static int parseInt(String number)
    {
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

}
