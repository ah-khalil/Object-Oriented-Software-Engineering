import java.util.*;

//Class is responsible for handling player input into the console and responding adequately
public class GameController{
    private View view;
    private Storage store;

    public GameController(){
        view = new View();
        store = new Storage();
    }

    //Reponsible for kickstarting and ending the entire game-playing sequence
    public void master(){
        String[] p_list = store.get_all_character_names('P');
        String[] np_list = store.get_all_character_names('N');
        boolean np_done = false;
        boolean p_done = false;

        view.introduction();

        try{
            while(!p_done && !np_done){
                pre_round();
                view.print_to_console_break("\n");
                view.print_switch_control('P');

                for(String turnholder : p_list){
                    if(check_hp(p_list, 'P') == 0)
                        turn(turnholder, p_list, np_list, 'P', 'N');
                    else
                        p_done = true;
                }

                view.print_switch_control('N');

                for(String turnholder : np_list){
                    if(check_hp(np_list, 'N') == 0)
                        turn(turnholder, np_list, p_list, 'N', 'P');
                    else
                        np_done = true;
                }
            }

            if(p_done && np_done)
                view.congrat_winner('B');
            else if(p_done)
                view.congrat_winner('N');
            else if(np_done)
                view.congrat_winner('P');

        } catch(InputParsingException ipe){
            view.print_to_console_break(ipe.getMessage());
        } finally{
            //System.exit(1);
        }
    }

    private void pre_round(){
        String selected_op = null;
        boolean valid_response = false;

        view.set_help(new String[]{
            "save|Save: saves the state of your game",
            "load|Load: load a game from your device",
            "create|Create new|New: create a new game"
        });
        view.print_to_console_break("Would you like to Save, Load, or Create New?");

        while(!valid_response){
            view.print_to_console("> ");
            selected_op = view.get_user_input();

            switch(selected_op){
                case "save": case "Save":
                    view.print_to_console_break("...saving");
                    view.print_to_console_break("Saved!\n");
                    valid_response = true;
                    break;
                case "load": case "Load":
                    view.print_to_console_break("Feature not implemented yet");
                    valid_response = true;
                    break;
                case "create new": case "Create new": case "Create New": case "create New":
                    view.print_to_console_break("Feature not implemented yet");
                    valid_response = true;
                    break;
                case "help": case "Help": case "HELP":
                    view.print_help();
                    view.print_to_console("\n");
                    break;
                default:
                    view.print_inv_comm();
                    break;
            }
        }
    }

    private void turn(String curr_turn_name, String[] allies, String[] enemies, char curr_side, char foe_side) throws InputParsingException{
        int effect = 0;
        String[] list = null;
        Ability ability = null;
        String selected_op = null;
        Character ally_char = null;
        Character enemy_char = null;
        boolean valid_response = false;
        Character charac = store.get_charac(curr_turn_name, curr_side);

        view.set_help(new String[]{
            "ability-list|ability list: shows all the abilities available to the current turn holder\n",
            "enemy-list|enemy list: shows all the enemies available to target\n",
            "ally-list|ally list: shows all allies available to target\n",
            "<ability name>-><target name>: attempts to affect the target specified with the ability specified (only works with single-target abilities)\n",
            "<ability name>: attempts to affect all target with specified ability (only works with multi-target abilities)\n"
        });
        view.print_to_console_break("Current turn: " + curr_turn_name);
        view.print_to_console_break("HP: " + charac.get_curr_hp());
        view.print_to_console_break("What would you like to do?");

        while(!valid_response){
            view.print_to_console("\n");
            view.print_to_console("> ");
            selected_op = view.get_user_input();

            switch(selected_op){
                case "ability-list": case "ability list":
                    list = charac.get_abs();
                    for(String ab : list){
                        view.print_to_console_break(ab);
                    }
                    break;
                case "enemy-list": case "enemy list":
                    try{
                        for(String enemy : enemies){
                            enemy_char = store.get_charac(enemy, foe_side);
                            view.print_to_console_break("Enemy Name: " + enemy);
                            view.print_to_console_break("\tMax HP: " + enemy_char.get_max_hp());
                            view.print_to_console_break("\tCurrent HP: " + enemy_char.get_curr_hp());
                        }
                    }
                    catch(IllegalArgumentException iae){
                        throw new InputParsingException("An error occurred while processing: enemy-list");
                    }
                    break;
                case "ally-list": case "Ally-List": case "Ally-list":
                    try{
                        for(String ally : allies){
                            ally_char = store.get_charac(ally, curr_side);
                            view.print_to_console_break("Ally Name: " + ally);
                            view.print_to_console_break("\tMax HP: " + ally_char.get_max_hp());
                            view.print_to_console_break("\tCurrent HP: " + ally_char.get_curr_hp());
                        }
                    }
                    catch(IllegalArgumentException iae){
                        throw new InputParsingException("An error occurred while processing: ally-list");
                    }
                    break;
                case "help": case "HELP": case "Help":
                    view.print_help();
                    break;
                default:
                    if(selected_op.contains("->")){
                        list = selected_op.split("->");
                        if(list.length == 2){
                            if(store.check_ab_exist(list[0]) && store.check_charac_exist(list[1])){
                                try{
                                    ability = store.get_ability(list[0]);

                                    System.out.println("Ability: " + ability.get_name() + " has effect type: " + ability.get_hit_type());
                                    if(ability.get_target_type() == ability.MULTI)
                                        view.print_to_console_break("Multi-target ability selected: type the ability by itself");
                                    else{
                                        if(ability.get_hit_type() == ability.DAMAGE){
                                            effect = -1 * ability.calc_effect();
                                            store.get_charac(list[1], foe_side).update_hp(effect);
                                            view.print_get_hit(list[1], effect);
                                        }
                                        else{
                                            effect = ability.calc_effect();
                                            store.get_charac(list[1], curr_side).update_hp(effect);
                                            view.print_get_healed(list[1], effect);
                                        }

                                        valid_response = true;
                                    }
                                } catch(IllegalArgumentException iae){
                                    throw new InputParsingException("An error occurred while processing your input; please make sure to verify that the ability and target exist by using the \"help\"");
                                }
                            }
                        }
                        else{
                            view.print_inv_comm();
                            view.print_cons_comm();
                        }
                    }
                    else{
                        System.out.println(selected_op);
                        if(store.check_ab_exist(selected_op)){
                            try{
                                ability = store.get_ability(selected_op);
                                if(ability.get_target_type() == ability.MULTI){
                                    if(ability.get_hit_type() == ability.DAMAGE)
                                        store.notify(ability.calc_effect(), foe_side);
                                    else
                                        store.notify(ability.calc_effect(), curr_side);

                                    valid_response = true;
                                }
                                else{
                                    view.print_to_console_break("Please add a space between the ability name and arrow, and between the arrow and target name");
                                    view.print_cons_comm();
                                }
                            } catch(IllegalArgumentException iae){
                                throw new InputParsingException("An error occurred while processing your input; please make sure to verify that the ability exist by using the \"help\"");
                            }
                        }
                        else{
                            view.print_inv_comm();
                            view.print_cons_comm();
                        }
                    }
                    break;
            }
        }
    }

    private int check_hp(String[] charac_list, char side){
        Character subject = null;
        int num_dead = 0;

        for(int i = 0; i < charac_list.length; i++){
            subject = store.get_charac(charac_list[i], side);

            if(subject.get_state() == subject.DEAD)
                num_dead++;
        }

        if(num_dead == charac_list.length){
            System.out.println("Check HP for " + side + " side is dead");
            return 1;
        }
        System.out.println("Check HP for " + side + " side is not dead");
        return 0;
    }
}
