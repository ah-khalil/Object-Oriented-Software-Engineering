import java.util.Scanner;

public class View{
    private Scanner sc;
    private Storage store;

    public View(){
        sc = new Scanner(System.in);
        store = new Storage();
    }

    public void present_option(){
        String rec = null;
        System.out.println("Options Available:\n\t- Save\n\t- Load\n\t- Create New");
        rec = sc.nextLine();

        switch(rec){
            case "Save":
                System.out.println("You have opted to save");
                break;
            case "Load":
                System.out.println("You have loaded");
                break;
            case "Create New":
                System.out.println("You have created a new file");
                break;
            default:
                System.out.println("\"" + rec + "\" is not an option available");
                break;
        }
    }

    public void print_game(String[] pl, String[] npl){
        String rec = null;
        NonPlayableCharacter charac = null;
        String[] ab_list = null;
        String[] attack_split = null;
        boolean turn_over;
        int dmg = 0;

        for(String name : pl){
            turn_over = false;
            System.out.println("Current turn: " + name);


            while(!turn_over){
                System.out.println("\n");
                System.out.println("What would you like to do?");
                rec = sc.nextLine();
                System.out.println("\n");

                switch(rec){
                    case "ability-list":
                        ab_list = store.get_p_charac(name).get_ab_list();
                        for(String ab_name : ab_list){
                            System.out.println("Ability: " + ab_name);
                        }
                        break;
                    case "enemy-list":
                        for(String non_player : npl){
                            System.out.println("Enemy: " + non_player);
                        }
                        break;
                    case "help":
                        System.out.println("For each of the following commands, remove the quotation marks\n");
                        System.out.println("\"ability-list\": lists all abilities available to that character");
                        System.out.println("\"enemy-list\": lists all enemies");
                        System.out.println("<ability name> \"->\" <enemy name>: attack that enemy with that ability");
                        break;
                    default:
                        if(rec.contains("->")){
                            attack_split = rec.split(" ");
                            charac = store.get_np_charac(attack_split[2]);
                            dmg = store.get_ability(attack_split[0]).damage_calc();
                            charac.set_curr_hp(-1 * dmg);
                            System.out.println(attack_split[2] + " is hit for " + dmg + "HP");
                            System.out.println(charac.get_name() + " current health: " + charac.get_curr_hp() + "HP");

                            turn_over = true;
                        }
                        else{
                            System.out.println("The input command is incorrect; type \"help\" for a list of acceptable commands");
                        }
                        break;
                }
            }
        }
    }
}
