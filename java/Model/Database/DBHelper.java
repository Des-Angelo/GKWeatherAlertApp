package Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.Employee;

import static android.R.attr.id;
import static android.icu.text.MessagePattern.ArgType.SELECT;


/**
 * Created by penai on 30/10/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Static Variables
    private static final String DATABASE_NAME = "Employee.db";
    private static final String DATABASE_TABLE_NAME = "EmployeeInformation";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_TELEPHONE = "telephone";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_EMAIL = "email";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Override this function to create a new table
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " + DATABASE_TABLE_NAME + "("
                + COLUMN_ID + " INTEGER, " + COLUMN_NAME + " TEXT, "
                + COLUMN_ADDRESS + " TEXT, " + COLUMN_CITY + " TEXT, "
                + COLUMN_TELEPHONE + " LONG, " + COLUMN_ROLE + " TEXT PRIMARY KEY, "
                + COLUMN_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

    }

    /**
     * Overide this function to upgrade your table structure.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
        onCreate(db);

    }

    /**
     * Add a new employee to database
     */
    public void addEmployee(Employee employee){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put(COLUMN_NAME, employee.getName());
        c.put(COLUMN_ADDRESS, employee.getAddress());
        c.put(COLUMN_CITY, employee.getCity());
        c.put(COLUMN_TELEPHONE, employee.getTelephone());
        c.put(COLUMN_ROLE, employee.getRole());
        c.put(COLUMN_EMAIL, employee.getEmail());

        db.insert(DATABASE_TABLE_NAME, null, c);
        db.close();
    }

    /**
     * @return return the employee's object if id matches
     */
    public Employee getEmployee(int employeeid){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(DATABASE_TABLE_NAME, new String[]{COLUMN_ID,
        COLUMN_NAME, COLUMN_ADDRESS, COLUMN_CITY, COLUMN_TELEPHONE, COLUMN_ROLE, COLUMN_EMAIL}, COLUMN_ID
        + " = ?", new String[]{String.valueOf(employeeid)}, null, null, null, null);

        if(c != null)
            c.moveToFirst();

        assert c != null;
        Employee employee = new Employee(c.getString(1), c.getString(2), c.getString(3), c.getInt(4), c.getString(5), c.getString(6));
        c.close();
        return employee;
    }

    public String getItEmployee(String employeeRole){

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor c = db.rawQuery("SELECT " + COLUMN_EMAIL + " FROM " + DATABASE_TABLE_NAME
                + " WHERE " + COLUMN_ROLE  + "=" + employeeRole , new String[]{COLUMN_ROLE});

        if(c != null)
            c.moveToFirst();

        assert c != null;
        Employee employee = new Employee(c.getString(1), c.getString(2), c.getString(3), c.getInt(4), c.getString(5), c.getString(6));
        c.close();

        return employee.toString();
    }


    /**
     * @return will return the list of all employees.
     */
    public ArrayList<Employee> getAllEmployee(){

        ArrayList<Employee> employees = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // Traverse table rows and adds to list employee records
        if ((c.moveToFirst())){
            do{
                Employee employee = new Employee(c.getString(1), c.getString(2), c.getString(3), c.getInt(4), c.getString(5), c.getString(6));
                employees.add(employee);
            }

            while (c.moveToNext());
        }
        c.close();
        return employees;
    }

    /**
     * @return the total number of records in the table
     */
    public int getEmployeeCount(){

        String countQuery = "SELECT * FROM " + DATABASE_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(countQuery, null);
        c.close();

        return c.getCount();
    }

    /**
     * Method to update the existing employee record
     */
    public int updateEmployee(Employee employee){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put(COLUMN_NAME, employee.getName());
        c.put(COLUMN_ADDRESS, employee.getAddress());
        c.put(COLUMN_CITY, employee.getCity());
        c.put(COLUMN_TELEPHONE, employee.getTelephone());
        c.put(COLUMN_ROLE, employee.getRole());
        c.put(COLUMN_EMAIL, employee.getEmail());

        return db.update(DATABASE_TABLE_NAME, c, COLUMN_ID + " = ?",
                new String[]{String.valueOf(employee.getId())});
    }

    /**
     * Method to delete the record from the table
     */
    public void deleteEmployee(Employee employee){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(employee.getId())});
        db.close();
    }
}


