package android.helper.contact.bean;

import com.witness.utils.db.annotation.DBColumn;
import com.witness.utils.db.annotation.DBPrimaryKey;
import com.witness.utils.db.annotation.DBTableName;

/**
 * Created by danger on 15/3/29.
 */

@DBTableName(name = Contact.Columns.TABLE_NAME_CONTACT_DATA)
public class IM implements IContactData{
    @DBColumn(name = Contact.Columns.DATA_TYPE)
    private static final int dtype = Type.TYPE_IM;


    @DBPrimaryKey(name = Contact.Columns._ID, autoIncrement = true)
    long id = 0;

    @DBColumn(name = Contact.Columns.CONTACT_ID)
    long cid;

    @DBColumn(name = Contact.Columns.DATA1)
    String data;

    @DBColumn(name = Contact.Columns.DATA2)
    int type;

    @DBColumn(name = Contact.Columns.DATA3)
    String label;

    @DBColumn(name = Contact.Columns.DATA4)
    String protocol;

    @DBColumn(name = Contact.Columns.DATA5)
    String custom_protocol;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getCustom_protocol() {
        return custom_protocol;
    }

    public void setCustom_protocol(String custom_protocol) {
        this.custom_protocol = custom_protocol;
    }
}
