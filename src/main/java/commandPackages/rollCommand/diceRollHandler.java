package commandPackages.rollCommand;

import commandPackages.coreUtils;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.*;

import static commandPackages.coreUtils.sendHelp;

public class diceRollHandler {
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) throws IllegalArgumentException, IndexOutOfBoundsException{
        String[] argArray = event.getMessage().getContent().split(" ");
        if (argArray.length == 0)
            return;
        if (!argArray[0].startsWith(coreUtils.BOT_PREFIX))
            return;
        String commandStr = argArray[0].substring(1).toLowerCase();
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0);

        switch(commandStr){
            case "roll":
                try {
                    Integer.parseInt(argsList.get(1).substring(1));
                } catch (IllegalArgumentException e) {
                    sendHelp(event.getChannel(), "]roll {1-5} d{Dice Type}", "]roll 5 d20");
                    throw e;
                } catch (IndexOutOfBoundsException e){
                    sendHelp(event.getChannel(), "]roll {1-5} d{Dice Type}", "]roll 5 d20");
                    throw e;
                }
                rollInit(event, argsList);
                System.out.println("[INFO] roll command received");
                System.out.println("    Command sent by " + event.getAuthor().getDisplayName(event.getGuild()));
        }
    }
    private void rollInit(MessageReceivedEvent event, List<String> args) {
        if (args.isEmpty() || args.size() <=1 || args.size() >= 3 || Integer.parseInt(args.get(0)) >= 6 ||
                Integer.parseInt(args.get(0)) <= 0) {
            sendHelp(event.getChannel(), "]roll {1-5} d{Dice Type}", "]roll 5 d20");
        } else {
                dieCall(Integer.parseInt(args.get(0)), args, event);
        }
    }
    private void dieCall(int i, List<String> args, MessageReceivedEvent event) {
        for (int x = 0; x <= Integer.parseInt(args.get(0)) - 1; x++) {
            int dieSize = Integer.parseInt(args.get(1).substring(1));
            coreUtils.sendMessage(event.getChannel(), "```" +
                    event.getAuthor().getDisplayName(event.getGuild()) + " rolled a " +
                    randomService.randomNumber(dieSize, event) + "```");
        }
    }
}
