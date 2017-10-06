public class DuplicateAbilityException extends Exception{
    public DuplicateAbilityException(String ab_name){
        super("The following Ability already exists: " + ab_name);
    }
}
