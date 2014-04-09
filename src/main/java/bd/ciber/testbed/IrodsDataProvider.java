package bd.ciber.testbed;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.IRODSGenQueryExecutor;
import org.irods.jargon.core.pub.io.IRODSFileFactory;
import org.irods.jargon.core.query.GenQueryBuilderException;
import org.irods.jargon.core.query.IRODSGenQueryBuilder;
import org.irods.jargon.core.query.IRODSGenQueryFromBuilder;
import org.irods.jargon.core.query.IRODSQueryResultRow;
import org.irods.jargon.core.query.IRODSQueryResultSetInterface;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.query.QueryConditionOperators;
import org.irods.jargon.core.query.RodsGenQueryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IrodsDataProvider implements DataProvider {
	@Autowired
	IRODSFileSystem irodsFileSystem;
	
	@Autowired
	IRODSAccount irodsAccount;

	/* (non-Javadoc)
	 * @see bd.ciber.testbed.DataSelector#select(java.util.Map)
	 */
	@Override
	public Set<String> select(Map<String, Integer> formatSpec) throws IOException {
		Set<String> result = new HashSet<String>();
		for(Entry<String, Integer> spec : formatSpec.entrySet()) {
			queryForSpec(spec.getKey(), spec.getValue().intValue(), result);
		}
		return result;
	}
	
	private void queryForSpec(String extension, int limit, Set<String> result) {
		IRODSGenQueryBuilder builder = new IRODSGenQueryBuilder(true, null);
		IRODSQueryResultSetInterface resultSet;
		try {
			builder.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_COLL_NAME)
				.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_DATA_NAME)
				.addConditionAsGenQueryField(
						RodsGenQueryEnum.COL_DATA_NAME,
						QueryConditionOperators.LIKE,
						"%."+extension);
			IRODSGenQueryFromBuilder irodsQuery = builder.exportIRODSQueryFromBuilder(1);
			IRODSGenQueryExecutor qExecutor = 
					irodsFileSystem.getIRODSAccessObjectFactory()
						.getIRODSGenQueryExecutor(irodsAccount);
			resultSet = qExecutor.executeIRODSQueryAndCloseResult(irodsQuery, 0);
			for(IRODSQueryResultRow row : resultSet.getResults()) {
				String col = row.getColumn(RodsGenQueryEnum.COL_COLL_NAME.getName());
				String data = row.getColumn(RodsGenQueryEnum.COL_DATA_NAME.getName());
				result.add(col+"/"+data);
			}
		} catch(JargonException e) {
			e.printStackTrace(); //FIXME
		} catch (GenQueryBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JargonQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public InputStream stream(String path) throws IOException {
		try {
			IRODSFileFactory iff = irodsFileSystem.getIRODSAccessObjectFactory()
					.getIRODSFileFactory(irodsAccount);
			return iff.instanceSessionClosingIRODSFileInputStream(path);
		} catch(JargonException e) {
			throw new IOException(e);
		}
	}

	@Override
	public String id() {
		try {
			return irodsAccount.toURI(false).toString();
		} catch (JargonException e) {
			throw new Error(e);
		}
	}
	
}
