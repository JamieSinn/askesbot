package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.data.YAMLViewerHandler;
import com.sinndevelopment.askesbot.points.Viewer;

import java.util.List;

public class AddPointsCommand extends ChatCommand
{

    public AddPointsCommand()
    {
        super("addpoints", PermissionLevel.MODERATOR);
    }

    @Override
    public void onCommand(String channel, String sender, String login, String hostname, List<String> args)
    {
        if(args.size() < 2)
        {

        }
        Viewer v = YAMLViewerHandler.getViewer(args.get(0));

        v.addPoints(Integer.parseInt(args.get(1)));
    }
}
