import java.util.Scanner;

//Represents what is observed on the screen
public class View{
    private Scanner scan;
    private String[] help;
    private String inv_com;
    private String cons_com;

    public View(){
        scan = new Scanner(System.in);
        inv_com = "You have entered an unrecognizable command; please type \"help\" to view available commands\n";
        cons_com = "Consult \"help\" for more information";
    }

    //Introduces the player to the game
    public void introduction(){
        System.out.println("==============================Combat Tool============================");
        System.out.println("========================Ali Khalil [17734503]========================");
        System.out.println("============Please read the README before playing this game==========");
        System.out.println("=====================================================================\n\n\n");
    }

    //Prints the given string to the console with a line break
    public void print_to_console_break(String line){ System.out.println(line); }

    //Prints the given string to the console without a line break
    public void print_to_console(String line){ System.out.print(line); }

    //Prints the Help Consultation message to the screen
    public void print_cons_comm(){ System.out.println(cons_com); }

    //Prints the Unrecognized Commands message to the screen
    public void print_inv_comm(){ System.out.println(inv_com); }

    //Prints the Congratulatory message to the screen
    public void congrat_winner(char type){
        if(type == 'P')
            System.out.println("Congratulations to the Playable Team for defeating the Story Teller!");
        else if(type == 'N')
            System.out.println("Congratulations to the Story Teller for defeating the Playable Team!");
        else if(type == 'B')
            System.out.println("Wow, you all died (how'd you manage that?)");
    }

    //Sets what the help message is to be printed
    public void set_help(String[] help_terms){ this.help = help_terms; }

    //Prints the damage done against the victim
    public void print_get_hit(String victim, int dmg){
        System.out.println(victim + " was hit for " + dmg + "HP\n");
    }

    //Prints the healing done for the patient
    public void print_get_healed(String patient, int heal){
        System.out.println(patient + " was healed for " + heal + "HP\n");
    }

    //Announces the control switch-over
    public void print_switch_control(char type){
        if(type == 'P')
            System.out.println("\nPlayable Team Turn commence\n");
        else
            System.out.println("\nStory Teller Turn commence\n");
    }

    //Prints the set help
    public void print_help(){
        System.out.println("Please remove all quotation marks (\"\")");

        for(String item : help){
            System.out.println(item);
        }

        System.out.println("\n");
    }

    //Takes the user input
    public String get_user_input(){
        return scan.nextLine();
    }
}
