package eu.epptec.genericSetOperations;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GenericSetOperations {
    // Convert class of the collection to specified class
    // Used for casting the HashSet with set operation output back to the original collection class
    private static <T> Collection<T> convertClass (Collection<T> collection, Class<? extends Collection> collectionClass) {

        Collection<T> output;
        try {
            output = collectionClass.getDeclaredConstructor().newInstance();
            output.addAll(collection);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            output = null;
            e.printStackTrace();
        }

        return output;
    }

    // There are n elements in collection1 and m elements in collection2

    // Takes O(n + m) time
    // - Only thing we could probably improve is the constant of the time complexity in specific cases,
    //   because if we, for example, had 2 sorted linked lists, we could do this in O(n + m) time without
    //   the creation of the hash set and casting it back in the first collection.
    public static <T> Collection<T> union (Collection<T> collection1, Collection<T> collection2) {
        // Null protection
        collection1 = Optional.ofNullable(collection1).orElse(new LinkedList<>());
        collection2 = Optional.ofNullable(collection2).orElse(new LinkedList<>());

        // Creation of HashSet
        // - O(n)
        Set<T> elemSet = new HashSet<>(collection1);
        // Adding all the collection2 elements to set
        // - O(m)
        elemSet.addAll(collection2);

        Collection<T> output = convertClass(elemSet, collection1.getClass());

        return output;
    }

    // Takes O(n + m) time
    public static <T> Collection<T> difference (Collection<T> collection1, Collection<T> collection2) {
        // Null protection
        collection1 = Optional.ofNullable(collection1).orElse(new LinkedList<>());
        collection2 = Optional.ofNullable(collection2).orElse(new LinkedList<>());

        // Creation of HashSet should take
        // - O(n)
        Set<T> elemSet = new HashSet<>(collection1);
        // Looks if set made from collection1 contains anything from collection2, if it does, we remove it
        // - O(m)
        collection2.forEach(elemSet::remove);

        Collection<T> output = convertClass(elemSet, collection1.getClass());

        return output;
    }

    // Takes O(n + m) time
    public static <T> Collection<T> intersection (Collection<T> collection1, Collection<T> collection2) {
        // Null protection
        collection1 = Optional.ofNullable(collection1).orElse(new LinkedList<>());
        collection2 = Optional.ofNullable(collection2).orElse(new LinkedList<>());

        // Creation of HashSet should take
        // - O(n)
        Set<T> elemSet = new HashSet<>(collection1);
        // Looks if any element from collection2 is in collection1. If it is, we add it to the set of intersecting elements
        // - O(m)
        Set<T> intersectSet = new HashSet<>();
        collection2.forEach(elem -> {
            if (elemSet.contains(elem)) {
                intersectSet.add(elem);
            }
        });

        Collection<T> output = convertClass(intersectSet, collection1.getClass());

        return output;
    }

    // Again we could save some time by lowering the constant of m + n, but the asymptotic time would remain the same.
    public static <T> Collection<T> symmetricDifference (Collection<T> collection1, Collection<T> collection2) {
        // Null protection
        collection1 = Optional.ofNullable(collection1).orElse(new LinkedList<>());
        collection2 = Optional.ofNullable(collection2).orElse(new LinkedList<>());

        // Does 3 operations, each taking O(m + n) time
        // - O(m + n)
        Collection<T> output = difference(union(collection1, collection2), intersection(collection1, collection2));

        output = convertClass(output, collection1.getClass());

        return output;
    }
}
