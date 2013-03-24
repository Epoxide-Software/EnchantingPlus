package eplus.common.localization;

import eplus.common.EnchantingPlus;
import eplus.common.Game;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;

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
        Game.log(Level.INFO, "Loaded Localization: {0}", new Object[] {file});
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
        Game.log(Level.INFO, "Loading Localizations", new Object[]{});

        URL resources = null;

        resources = this.getClass().getResource("/eplus/lang/languages.txt");

        InputStream inputStream = null;
        try {
            inputStream = resources.openStream();
        } catch (Exception e) {
            Game.log(Level.INFO, "Error opening file", new Object[]{});
            e.printStackTrace();
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        try {
            while((line = reader.readLine()) != null) {
                LocalizationRegistry.Instance().addLocalizationFile("/eplus/lang/" + line);
            }
        } catch (Exception e) {
            Game.log(Level.INFO, "Error Reading Line", new Object[]{});
            e.printStackTrace();
        }

    }
}
