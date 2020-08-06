package org.pluralsight;

import org.pluralsight.model.Person;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CreatingSpliterator {
    public static void main(String[] args) {
        Path path = Paths.get("files/people.txt");

        try {
            Stream<String> lines = Files.lines(path);
            Spliterator<String> lineSpliterator = lines.spliterator();
            Spliterator<Person> peopleSpliterator = new PersonSpliterator(lineSpliterator);
            Stream<Person> personStream = StreamSupport.stream(peopleSpliterator, false);

            personStream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
