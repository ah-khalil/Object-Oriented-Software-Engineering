import java.util.*;

public class Context{
    public static void main(String[] args){
        Storage storage = new Storage();
        Storage storage_two = new Storage();
        String key = "Testing";
        String key_two = "Testing Two";
        int value = 345;
        int value_two = 456;

        storage.add_to_storage(key, value);
        storage_two.add_to_storage(key_two, value_two);
        storage.print_content();
    }
}
