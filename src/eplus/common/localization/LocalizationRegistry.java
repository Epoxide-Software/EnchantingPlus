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

    public void addAllLocaliztionFiles() throws IOException {

        InputStream resources = null;

        resources = Thread.currentThread().getContextClassLoader().getResourceAsStream("eplus/lang/");

        BufferedReader reader = new BufferedReader(new InputStreamReader(resources));

        String line;
        while((line = reader.readLine()) != null) {
            LocalizationRegistry.Instance().addLocalizationFile("/eplus/lang/" + line);
            Game.log(Level.INFO, "Loaded Localization: {0}", new Object[] {line});
        }

    }
}
