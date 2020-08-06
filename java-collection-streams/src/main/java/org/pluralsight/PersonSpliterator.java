package org.pluralsight;

import org.pluralsight.model.Person;

import java.util.Spliterator;
import java.util.function.Consumer;

public class PersonSpliterator implements Spliterator<Person> {
    private final Spliterator<String> linesSpliterator;
    private String name;
    private Integer age;
    private String city;

    public PersonSpliterator(Spliterator<String> lineSpliterator) {
        this.linesSpliterator = lineSpliterator;

    }

    @Override
    public boolean tryAdvance(Consumer<? super Person> action) {
        if (this.linesSpliterator.tryAdvance(line -> this.name = line) &&
            this.linesSpliterator.tryAdvance(line -> this.age = Integer.parseInt(line)) &&
            this.linesSpliterator.tryAdvance(line -> this.city = line)) {
            Person p = new Person(this.name, this.age, this.city);
            action.accept(p);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<Person> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return linesSpliterator.estimateSize() / 3;
    }

    @Override
    public int characteristics() {
        return linesSpliterator.characteristics();
    }
}
