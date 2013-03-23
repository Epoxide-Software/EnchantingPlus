package eplus.common.localization;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Used to register new localizations for the mod
 * 
 * @author odininon
 * 
 */
public class LocalizationRegistry {

	/**
	 * Singleton Instance
	 */
	private static final LocalizationRegistry INSTANCE = new LocalizationRegistry();

	/**
	 * A list of localization files to be loaded
	 */
	private final ArrayList<String> LocalizationFiles = new ArrayList<String>();

	/**
	 * 
	 * @return The Singleton instance of LocalizationRegistry
	 */
	public static LocalizationRegistry Instance()
	{
		return INSTANCE;
	}

	/**
	 * Used to add localization file to the registry
	 * 
	 * @param file
	 *            path to the localization file to be loaded
	 */
	public void addLocalizationFile(String file)
	{
		LocalizationFiles.add(file);
	}

	/**
	 * 
	 * @return A list of localization files to be loaded
	 */
	public ArrayList<String> getLocalizations()
	{
		return LocalizationFiles;
	}

	private LocalizationRegistry() {
	}

    public void addAllLocaliztionFiles() {

        Enumeration<URL> resources = null;
        try {
            resources = this.getClass().getClassLoader().getResources("eplus/lang/");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (resources == null) return;

        if (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            File dir = null;

            try {
                dir = new File(url.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            if (dir == null) return;

            String[] list = dir.list();

            System.out.println(list.toString());

            for(String file : list)
            {
                addLocalizationFile("/eplus/lang/" + file);
            }

        }


    }
}
