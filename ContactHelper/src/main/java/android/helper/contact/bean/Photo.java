package android.helper.contact.bean;

import com.witness.utils.db.annotation.DBColumn;
import com.witness.utils.db.annotation.DBPrimaryKey;
import com.witness.utils.db.annotation.DBTableName;

/**
 * Created by danger on 15/3/29.
 */
@DBTableName(name = Contact.Columns.TABLE_NAME_CONTACT_DATA)
public class Photo implements IContactData{
    @DBColumn(name = Contact.Columns.DATA_TYPE)
    private static final int dtype = Type.TYPE_PHOTO;


    @DBPrimaryKey(name = Contact.Columns._ID, autoIncrement = true)
    long id = 0;

    @DBColumn(name = Contact.Columns.CONTACT_ID)
    long cid;

    @DBColumn(name = Contact.Columns.DATA1)
    String number;
//    BLOB photo;


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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
