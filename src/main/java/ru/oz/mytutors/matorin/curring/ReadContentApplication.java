package ru.oz.mytutors.matorin.curring;

import io.vavr.CheckedFunction1;
import io.vavr.control.Try;
import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.vavr.control.Try.failure;
import static io.vavr.control.Try.of;
import static java.net.Proxy.Type.HTTP;

/**
 * Created by Igor Ozol
 * on 03.09.2017.
 * Eldorado LLC
 */
public class ReadContentApplication {

    public static String readContent(String location) {
        try {
            URL urlObj = new URL(location);
            if (!"http".equals(urlObj.getProtocol())) {
                throw new UnsupportedOperationException("Protocol is not http");
            }

            URLConnection con = urlObj.openConnection();

            con.setDoOutput(true); // we want the response
            con.setRequestProperty("Cookie", "myCookie=test123");
            con.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder response = new StringBuilder();
            String inputLine;

            String newLine = System.getProperty("line.separator");
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine + newLine);
            }

            in.close();

            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Try<String> readContentByVavr(final String location) {

        CheckedFunction1<URL, URLConnection> openConnection = url -> url.openConnection() ;


        return Try
                .of(() -> new URL(location))
                .filter(url -> "http".equals(url.getProtocol()))
                .flatMap(url -> Try.of(url::openConnection))
                .flatMap(con -> Try.of(con::getInputStream))
                .map((is) -> {
                    return null;
                    //this::readAndClose
                });

        //
//        CheckedFunction1<URL, URLConnection> readFunction = url -> url::openConnection;
//

        //Try<URL> url = Try.of(() -> new URL(location)); // if (url.isSuccess())

//        return of(() -> new URL(location))
//                .filter(url -> HTTP.equals(url.getProtocol()))
//                .map(url -> openConnection.unchecked())
//                .map(::getInputStream)
//                .map(IOUtils::read);
    }

    public static void main(String[] args) {

        System.out.println(readContent("http://ya.ru"));
    }
}
