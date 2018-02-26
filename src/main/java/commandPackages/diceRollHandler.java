package commandPackages;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.*;

public class diceRollHandler {
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event){
        String[] argArray = event.getMessage().getContent().split(" ");
        if (argArray.length == 0)
            return;
        if (!argArray[0].startsWith(coreUtils.BOT_PREFIX))
            return;
        String commandStr = argArray[0].substring(1);
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0);

        switch(commandStr){
            case "roll":
                rollInit(event, argsList);
                System.out.println("[INFO] roll command received");
                System.out.println("    Command sent by" + event.getAuthor().getDisplayName(event.getGuild()));
        }
    }
    private void rollInit(MessageReceivedEvent event, List<String> args) {
        if (args.isEmpty() || args.get(1).isEmpty()) {
            coreUtils.sendMessage(event.getChannel(), "Missing Arguments Received!");
            coreUtils.sendMessage(event.getChannel(), "    ```" + coreUtils.BOT_PREFIX + "roll {rolls 1-4} {dice}```");
        } else if (args.size() >= 3) {
            coreUtils.sendMessage(event.getChannel(), "Too Many Arguments Received!");
            coreUtils.sendMessage(event.getChannel(), "    ```" + coreUtils.BOT_PREFIX + "roll {rolls 1-4} {dice}```");
        } else if (args.get(0).equals("1")){
            randomorgCall(1, args.get(1), event);

        } else if (args.get(0).equals("2")){
            randomorgCall(2, args.get(1), event);

        } else if (args.get(0).equals("3")){
            randomorgCall(3, args.get(1), event);

        } else if (args.get(0).equals("4")){
            randomorgCall(4, args.get(1), event);

        } else { coreUtils.sendMessage(event.getChannel(), "```css [Unknown Error]```"); }
    }
    private void randomorgCall(int i, String die, MessageReceivedEvent event){
        if (!die.equals("d2") || !die.equals("d4") ||
                !die.equals("d6") || !die.equals("d8") ||
                !die.equals("d10") || !die.equals("d12") ||
                !die.equals("d20") || !die.equals("d100")){
            coreUtils.sendMessage(event.getChannel(), "Missing Die Type!");
        } else {
            coreUtils.sendMessage(event.getChannel(), "Checks Passed!");
        }
    }
}
