package android.helper.contact.bean;

import com.witness.utils.db.annotation.DBColumn;
import com.witness.utils.db.annotation.DBPrimaryKey;
import com.witness.utils.db.annotation.DBTableName;

/**
 * Created by danger on 15/3/29.
 */
@DBTableName(name = Contact.Columns.TABLE_NAME_CONTACT_DATA)
public class Data {

    @DBColumn(name = Contact.Columns.DATA_TYPE)
    public int dtype;

    @DBPrimaryKey(name = Contact.Columns._ID, autoIncrement = true)
    public long _ID;

    @DBColumn(name = Contact.Columns.CONTACT_ID)
    public String CONTACT_ID;

    @DBColumn(name = Contact.Columns.DATA1)
    public String DATA1;

    @DBColumn(name = Contact.Columns.DATA2)
    public String DATA2;

    @DBColumn(name = Contact.Columns.DATA3)
    public String DATA3;

    @DBColumn(name = Contact.Columns.DATA4)
    public String DATA4;

    @DBColumn(name = Contact.Columns.DATA5)
    public String DATA5;

    @DBColumn(name = Contact.Columns.DATA6)
    public String DATA6;

    @DBColumn(name = Contact.Columns.DATA7)
    public String DATA7;

    @DBColumn(name = Contact.Columns.DATA8)
    public String DATA8;

    @DBColumn(name = Contact.Columns.DATA9)
    public String DATA9;

    @DBColumn(name = Contact.Columns.DATA10)
    public String DATA10;

    @DBColumn(name = Contact.Columns.DATA11)
    public String DATA11;

    @DBColumn(name = Contact.Columns.DATA12)
    public String DATA12;

    @DBColumn(name = Contact.Columns.DATA13)
    public String DATA13;

}
