package bd.ciber.testbed;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

public interface DataProvider {

	/**
	 * Identity of this data provider for logging.
	 * @return
	 */
	public abstract String id();
	
	/**
	 * Get a set of paths that satisfy the given format distribution.
	 * @param formatSpec
	 * @return
	 * @throws IOException
	 */
	public abstract Set<String> select(Map<String, Integer> formatSpec)
			throws IOException;
	
	/**
	 * Open a stream for a path.
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public abstract InputStream stream(String path) throws IOException;

}