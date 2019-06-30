package utility;

public class Constants {
	// Database
	public static final String DB_NAME = "WAREHOUSE";
	public static final String DB_URL = "jdbc:hsqldb:file:database/" + DB_NAME;
	public static final String HSQLDB_DRIVER = "org.hsqldb.jdbc.JDBCDriver";

	// A static class to hold the Items table name and column names
	public static final class ItemsTable {	
		public static final String TB_NAME = "ITEM";
		
		public static final String ID = "ID";
		public static final String NAME = "NAME";
		public static final String MODEL = "MODEL";
	    public static final String PART_NUM = "PART_NUM";
	    public static final String SUPPLY = "SUPPLY"; 
	    public static final String CARRIER = "CARRIER";
	    public static final String DATE = "DATE";
	    public static final String QUANTITY = "QUANTITY";
	    public static final String WORKING = "WORKING";
	    public static final String LOCATION = "LOCATION";
	    public static final String DESCRIPTION = "DESCRIPTION";
	}
}
