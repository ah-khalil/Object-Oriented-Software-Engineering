import java.util.*;

public interface Subject{
    public void attach_charac(String charac_name, Character charac);
    public void notify(int diff, char target_type);
}
