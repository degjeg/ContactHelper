package android.helper.contact.bean;

import com.witness.utils.db.annotation.DBColumn;
import com.witness.utils.db.annotation.DBPrimaryKey;
import com.witness.utils.db.annotation.DBTableName;

/**
 * Created by danger on 15/3/29.
 */@DBTableName(name = Contact.Columns.TABLE_NAME_CONTACT_DATA)
public class StructuredName implements IContactData{
    @DBColumn(name = Contact.Columns.DATA_TYPE)
    private static final int dtype = Type.TYPE_STRUCTURED_NAME;



    @DBPrimaryKey(name = Contact.Columns._ID, autoIncrement = true)
    long id = 0;

    @DBColumn(name = Contact.Columns.CONTACT_ID)
    long cid;

    @DBColumn(name = Contact.Columns.DATA1)
    String display_name;

    @DBColumn(name = Contact.Columns.DATA2)
    String given_name;

    @DBColumn(name = Contact.Columns.DATA3)
    String family_name;

    @DBColumn(name = Contact.Columns.DATA4)
    String prefix;

    @DBColumn(name = Contact.Columns.DATA5)
    String middle_name;

    @DBColumn(name = Contact.Columns.DATA6)
    String suffix;

    @DBColumn(name = Contact.Columns.DATA7)
    String phonetic_given_name;

    @DBColumn(name = Contact.Columns.DATA8)
    String phonetic_middle_name;

    @DBColumn(name = Contact.Columns.DATA9)
    String phonetic_family_name;

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

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPhonetic_given_name() {
        return phonetic_given_name;
    }

    public void setPhonetic_given_name(String phonetic_given_name) {
        this.phonetic_given_name = phonetic_given_name;
    }

    public String getPhonetic_middle_name() {
        return phonetic_middle_name;
    }

    public void setPhonetic_middle_name(String phonetic_middle_name) {
        this.phonetic_middle_name = phonetic_middle_name;
    }

    public String getPhonetic_family_name() {
        return phonetic_family_name;
    }

    public void setPhonetic_family_name(String phonetic_family_name) {
        this.phonetic_family_name = phonetic_family_name;
    }
}
