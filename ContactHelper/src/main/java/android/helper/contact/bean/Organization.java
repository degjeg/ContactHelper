package android.helper.contact.bean;

import com.witness.utils.db.annotation.DBColumn;
import com.witness.utils.db.annotation.DBPrimaryKey;
import com.witness.utils.db.annotation.DBTableName;

/**
 * Created by danger on 15/3/29.
 */
@DBTableName(name = Contact.Columns.TABLE_NAME_CONTACT_DATA)
public class Organization implements IContactData{
    @DBColumn(name = Contact.Columns.DATA_TYPE)
    private static final int dtype = Type.TYPE_ORGANIZATION;


    @DBPrimaryKey(name = Contact.Columns._ID, autoIncrement = true)
    long id = 0;

    @DBColumn(name = Contact.Columns.CONTACT_ID)
    long cid;

    @DBColumn(name = Contact.Columns.DATA1)
    String company;

    @DBColumn(name = Contact.Columns.DATA2)
    int type;

    @DBColumn(name = Contact.Columns.DATA3)
    String label;

    @DBColumn(name = Contact.Columns.DATA4)
    String title;

    @DBColumn(name = Contact.Columns.DATA5)
    String department;

    @DBColumn(name = Contact.Columns.DATA6)
    String job_description;

    @DBColumn(name = Contact.Columns.DATA7)
    String symbol;

    @DBColumn(name = Contact.Columns.DATA8)
    String phonetic_name;

    @DBColumn(name = Contact.Columns.DATA9)
    String office_location;

    @DBColumn(name = Contact.Columns.DATA10)
    String phonetic_name_style;

    public String getCompany() {

        return company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getCid() {
        return cid;
    }

    @Override
    public void setCid(long cid) {
        this.cid = cid;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPhonetic_name() {
        return phonetic_name;
    }

    public void setPhonetic_name(String phonetic_name) {
        this.phonetic_name = phonetic_name;
    }

    public String getOffice_location() {
        return office_location;
    }

    public void setOffice_location(String office_location) {
        this.office_location = office_location;
    }

    public String getPhonetic_name_style() {
        return phonetic_name_style;
    }

    public void setPhonetic_name_style(String phonetic_name_style) {
        this.phonetic_name_style = phonetic_name_style;
    }
}
