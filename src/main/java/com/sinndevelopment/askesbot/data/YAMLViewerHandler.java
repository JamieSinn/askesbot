package com.sinndevelopment.askesbot.data;

import com.sinndevelopment.askesbot.points.Viewer;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class YAMLViewerHandler
{
    private static String configDir = "viewers/";
    private static Yaml yaml = new Yaml();

    public static Viewer getViewer(String name)
    {
        File confDir = new File(configDir);
        if(!confDir.exists())
        {
            System.out.println("Data directory does not exist, creating now.");
            confDir.mkdir();
        }

        try (InputStream in = Files.newInputStream(Paths.get(configDir+name+".yaml")))
        {
            Viewer viewer = yaml.loadAs(in, Viewer.class);
            if(viewer == null)
            {
                viewer = new Viewer(0, name, false);
            }

            return viewer;
        }
        catch (NoSuchFileException e)
        {
            System.out.println("Userdata file for " + name + " was not found. Creating now with base data.");
            return new Viewer(0, name, false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return new Viewer(0, name, false);
        }
    }

    public static void saveViewer(Viewer viewer)
    {
        try(FileWriter writer = new FileWriter(new File(configDir + viewer.getUsername() + ".yaml")))
        {
            Map<String, Object> data = new HashMap<>();
            data.put("count", viewer.getCount());
            data.put("username", viewer.getUsername());
            data.put("subscriber", viewer.isSubscriber());

            yaml.dump(data, writer);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
